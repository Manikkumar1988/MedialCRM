package challenge.rt.medibus.mani.ui.salescreate.formfields;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import challenge.rt.medibus.mani.R;
import challenge.rt.medibus.mani.ViewModelFactory;
import challenge.rt.medibus.mani.ui.salescreate.SalesCreateViewModel;

/**
   * A placeholder fragment containing a simple view.
   */
  public class SalesRepNameFragment extends LifecycleFragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARG_SECTION_TITLE = "section_title";

  private SalesCreateViewModel salesRepViewModel;

  @BindView(R.id.value) EditText valueEditText;

  public SalesRepNameFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static SalesRepNameFragment newInstance(int sectionNumber, String title) {
      SalesRepNameFragment fragment = new SalesRepNameFragment();
      Bundle args = new Bundle();
      args.putInt(ARG_SECTION_NUMBER, sectionNumber);
      args.putString(ARG_SECTION_TITLE,title);
      fragment.setArguments(args);
      return fragment;
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      View rootView = inflater.inflate(R.layout.fragment_main, container, false);
      TextView textView = (TextView) rootView.findViewById(R.id.section_label);
      textView.setText(getArguments().getString(ARG_SECTION_TITLE)); /*getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER))*/

      ButterKnife.bind(this, rootView);

      if(getArguments().getInt(ARG_SECTION_NUMBER) == 2){
        valueEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
      }

      return rootView;
    }

    //Receive viewmodel data here

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    initializeViewModel();

    initializeData();
  }

  private void initializeData() {
    valueEditText.setText(salesRepViewModel.getName());

    valueEditText.addTextChangedListener(new TextWatcher() {

      @Override
      public void afterTextChanged(Editable s) {}

      @Override
      public void beforeTextChanged(CharSequence s, int start,
          int count, int after) {
      }

      @Override
      public void onTextChanged(CharSequence s, int start,
          int before, int count) {
          if(s.length() != 0)
            salesRepViewModel.setName(s.toString());
      }
    });
  }

  private void initializeViewModel() {
    ViewModelFactory factory = ViewModelFactory.getInstance(getActivity().getApplication());

    salesRepViewModel = ViewModelProviders.of(getActivity(), factory).get(SalesCreateViewModel.class);

  }
}