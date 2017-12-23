package challenge.rt.medibus.mani.ui.taskscreate.formfields.map;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import challenge.rt.medibus.mani.R;
import challenge.rt.medibus.mani.ViewModelFactory;
import challenge.rt.medibus.mani.data.SalesRepDetail;
import challenge.rt.medibus.mani.ui.home.salesrep.SalesRepAdapter;
import challenge.rt.medibus.mani.ui.salescreate.SalesCreateActivity;
import challenge.rt.medibus.mani.ui.taskscreate.TaskCreateViewModel;
import challenge.rt.medibus.mani.ui.taskscreate.formfields.TaskDescriptionFragment;
import challenge.rt.medibus.mani.util.Util;
import java.util.ArrayList;
import java.util.List;
import noman.googleplaces.NRPlaces;
import noman.googleplaces.Place;
import noman.googleplaces.PlaceType;
import noman.googleplaces.PlacesException;
import noman.googleplaces.PlacesListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaskPharmacyFragment extends LifecycleFragment implements PlacesListener {

  private PharmacyListAdapter mSalesRepAdapter;

  @BindView(R.id.pharmacy_recycler_View) RecyclerView salesRepListRecyclerView;

  private TaskCreateViewModel taskCreateViewModel;

  private static final String ARG_SECTION_NUMBER = "section_number";
  private static final String ARG_SECTION_TITLE = "section_title";


  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View rootView =  inflater.inflate(R.layout.fragment_task_pharmacy, container, false);
    TextView textView = (TextView) rootView.findViewById(R.id.section_label);
    textView.setText(getArguments().getString(ARG_SECTION_TITLE)); /*getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER))*/

    ButterKnife.bind(this,rootView);
    setupRecyclerView(rootView);
    return rootView;
  }

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    initializeViewModel();
    initializeData();
  }

  private void initializeData() {

    new NRPlaces.Builder()
        .listener(this)
        .key("AIzaSyCrdKry3VNsdAVlM5Spo1mZNRuuYBgvz_U")
        .latlng(33.721328, 73.057838)
        .type(PlaceType.PHARMACY)
        .radius(1500)
        .build()
        .execute();
  }

  private void initializeViewModel() {
    ViewModelFactory factory = ViewModelFactory.getInstance(getActivity().getApplication());

    taskCreateViewModel = ViewModelProviders.of(getActivity(), factory).get(TaskCreateViewModel.class);
  }

  @Override public void onPlacesFailure(PlacesException e) {

  }

  @Override public void onPlacesStart() {

  }

  @Override public void onPlacesSuccess(final List<Place> places) {
    try {
      getActivity().runOnUiThread(new Runnable() {
        @Override public void run() {
          mSalesRepAdapter.addItems(places);
        }
      });
    }catch (Exception e) {
      Util.printStacktrace(e);
    }
  }

  @Override public void onPlacesFinished() {

  }

  private void setupRecyclerView(View v) {
    LinearLayoutManager
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

    salesRepListRecyclerView.setLayoutManager(layoutManager);

    mSalesRepAdapter = new PharmacyListAdapter(getContext(), new ArrayList<Place>(), new PharmacyListAdapter.OnItemClickListener() {
      @Override public void onItemClick(Place salesRepDetail) {

        taskCreateViewModel.setPharmacyName(salesRepDetail.getName());
        taskCreateViewModel.save();
        getActivity().finish();

      }
    });

    salesRepListRecyclerView.setAdapter(mSalesRepAdapter);

    final DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
        salesRepListRecyclerView.getContext(),
        layoutManager.getOrientation());

    salesRepListRecyclerView.addItemDecoration(dividerItemDecoration);
  }

  public static TaskPharmacyFragment newInstance(int sectionNumber, String title) {
    TaskPharmacyFragment
        fragment = new TaskPharmacyFragment();
    Bundle args = new Bundle();
    args.putInt(ARG_SECTION_NUMBER, sectionNumber);
    args.putString(ARG_SECTION_TITLE,title);
    fragment.setArguments(args);
    return fragment;
  }
}
