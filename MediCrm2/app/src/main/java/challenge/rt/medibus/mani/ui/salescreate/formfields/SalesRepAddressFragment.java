package challenge.rt.medibus.mani.ui.salescreate.formfields;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.InputType;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import challenge.rt.medibus.mani.R;
import challenge.rt.medibus.mani.ViewModelFactory;
import challenge.rt.medibus.mani.ui.salescreate.SalesCreateViewModel;
import challenge.rt.medibus.mani.util.Util;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBufferResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/**
 * A placeholder fragment containing a simple view.
 */
public class SalesRepAddressFragment extends LifecycleFragment {
  /**
   * The fragment argument representing the section number for this
   * fragment.
   */
  private static final String ARG_SECTION_NUMBER = "section_number";
  private static final String ARG_SECTION_TITLE = "section_title";

  public static final String TAG = "SampleActivityBase";

  protected GeoDataClient mGeoDataClient;

  private PlaceAutocompleteAdapter mAdapter;

  @BindView(R.id.autocomplete_places) AutoCompleteTextView mAutocompleteView;

  @BindView(R.id.save) Button clearButton;


  private SalesCreateViewModel salesRepViewModel;

  public SalesRepAddressFragment() {
  }

  /**
   * Returns a new instance of this fragment for the given section
   * number.
   */
  public static SalesRepAddressFragment newInstance(int sectionNumber, String title) {
    SalesRepAddressFragment fragment = new SalesRepAddressFragment();
    Bundle args = new Bundle();
    args.putInt(ARG_SECTION_NUMBER, sectionNumber);
    args.putString(ARG_SECTION_TITLE,title);
    fragment.setArguments(args);
    return fragment;
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_address, container, false);
    TextView textView = (TextView) rootView.findViewById(R.id.section_label);
    textView.setText(getArguments().getString(ARG_SECTION_TITLE)); /*getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER))*/

    ButterKnife.bind(this, rootView);



    return rootView;
  }

  //Receive viewmodel data here

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    initializeViewModel();

    initializeData();

    initializeView();
  }

  private void initializeView() {
    mGeoDataClient = Places.getGeoDataClient(getContext(), null);

    // Register a listener that receives callbacks when a suggestion has been selected
    mAutocompleteView.setOnItemClickListener(mAutocompleteClickListener);

    // Set up the adapter that will retrieve suggestions from the Places Geo Data Client.
    mAdapter = new PlaceAutocompleteAdapter(getContext(), mGeoDataClient, null, null);
    mAutocompleteView.setAdapter(mAdapter);

    // Set up the 'clear text' button that clears the text in the autocomplete view
    clearButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        salesRepViewModel.save();
        getActivity().finish();
      }
    });
  }

  private void initializeData() {
    mAutocompleteView.setText(salesRepViewModel.getAddress());
  }

  private void initializeViewModel() {
    ViewModelFactory factory = ViewModelFactory.getInstance(getActivity().getApplication());

    salesRepViewModel = ViewModelProviders.of(getActivity(), factory).get(SalesCreateViewModel.class);

  }

  /**
   * Listener that handles selections from suggestions from the AutoCompleteTextView that
   * displays Place suggestions.
   * Gets the place id of the selected item and issues a request to the Places Geo Data Client
   * to retrieve more details about the place.
   *
   * @see GeoDataClient#getPlaceById(String...)
   */
  private AdapterView.OnItemClickListener mAutocompleteClickListener
      = new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            /*
             Retrieve the place ID of the selected item from the Adapter.
             The adapter stores each Place suggestion in a AutocompletePrediction from which we
             read the place ID and title.
              */
      final AutocompletePrediction item = mAdapter.getItem(position);
      final String placeId = item.getPlaceId();
      final CharSequence primaryText = item.getPrimaryText(null);

      Util.Logger(TAG, "Autocomplete item selected: " + primaryText);

            /*
             Issue a request to the Places Geo Data Client to retrieve a Place object with
             additional details about the place.
              */
      Task<PlaceBufferResponse> placeResult = mGeoDataClient.getPlaceById(placeId);
      placeResult.addOnCompleteListener(mUpdatePlaceDetailsCallback);

      Toast.makeText(getContext(), "Clicked: " + primaryText,
          Toast.LENGTH_SHORT).show();
      Util.Logger(TAG, "Called getPlaceById to get Place details for " + placeId);

    }
  };

  /**
   * Callback for results from a Places Geo Data Client query that shows the first place result in
   * the details view on screen.
   */
  private OnCompleteListener<PlaceBufferResponse> mUpdatePlaceDetailsCallback
      = new OnCompleteListener<PlaceBufferResponse>() {
    @Override
    public void onComplete(Task<PlaceBufferResponse> task) {
      try {
        PlaceBufferResponse places = task.getResult();

        // Get the Place object from the buffer.
        final Place place = places.get(0);

        // Format details of the place for display and show it in a TextView.
        mAutocompleteView.setText( place.getAddress());

        salesRepViewModel.setAddress(place);

        places.release();
      } catch (RuntimeRemoteException e) {
        // Request did not complete successfully
        Util.Logger(TAG, "Place query did not complete.");
        Util.printStacktrace(e);
        return;
      }
    }
  };

  private static Spanned formatPlaceDetails(Resources res, CharSequence name, String id,
      CharSequence address, CharSequence phoneNumber, Uri websiteUri) {
    Util.Logger(TAG, res.getString(R.string.place_details, name, id, address, phoneNumber,
        websiteUri));
    return Html.fromHtml(res.getString(R.string.place_details, name, id, address, phoneNumber,
        websiteUri));

  }
}