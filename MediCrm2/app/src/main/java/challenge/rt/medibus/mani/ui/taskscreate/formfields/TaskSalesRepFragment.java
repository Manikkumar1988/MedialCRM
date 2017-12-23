package challenge.rt.medibus.mani.ui.taskscreate.formfields;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import challenge.rt.medibus.mani.R;
import challenge.rt.medibus.mani.ViewModelFactory;
import challenge.rt.medibus.mani.data.SalesRepDetail;
import challenge.rt.medibus.mani.data.source.remote.myApi.model.SalesRep;
import challenge.rt.medibus.mani.ui.home.HomeViewModel;
import challenge.rt.medibus.mani.ui.salescreate.SalesCreateViewModel;
import challenge.rt.medibus.mani.ui.taskscreate.TaskCreateViewModel;
import challenge.rt.medibus.mani.util.Util;
import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class TaskSalesRepFragment extends LifecycleFragment {
  /**
   * The fragment argument representing the section number for this
   * fragment.
   */
  private static final String ARG_SECTION_NUMBER = "section_number";
  private static final String ARG_SECTION_TITLE = "section_title";

  @BindView(R.id.status_spinner) Spinner valueEditText;

  private TaskCreateViewModel taskCreateViewModel;
  private HomeViewModel salesCreateViewModel;

  public TaskSalesRepFragment() {
  }

  /**
   * Returns a new instance of this fragment for the given section
   * number.
   */
  public static TaskSalesRepFragment newInstance(int sectionNumber, String title) {
    TaskSalesRepFragment
        fragment = new TaskSalesRepFragment();
    Bundle args = new Bundle();
    args.putInt(ARG_SECTION_NUMBER, sectionNumber);
    args.putString(ARG_SECTION_TITLE,title);
    fragment.setArguments(args);
    return fragment;
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_main_drop_down, container, false);
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
  }

  private void initializeData() {
    final ArrayList<SalesRepDetail> list = new ArrayList<>();


    valueEditText.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        try { //Check for list boundary condition
          taskCreateViewModel.setAssigned(list.get(i).name);
        }catch (Exception e) {
          Util.printStacktrace(e);
        }
      }

      @Override public void onNothingSelected(AdapterView<?> adapterView) {

      }
    });

    salesCreateViewModel.getSalesRepList().observe(this, new Observer<List<SalesRepDetail>>() {
      @Override public void onChanged(@Nullable List<SalesRepDetail> salesRepDetails) {
        list.clear();
        list.addAll(salesRepDetails);

        ArrayAdapter<SalesRepDetail> dataAdapter = new ArrayAdapter<>(getContext(),
            android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        valueEditText.setAdapter(dataAdapter);

        valueEditText.setSelection(list.indexOf(taskCreateViewModel.getAssigned()),false);

      }
    });

  }



  private void initializeViewModel() {
    ViewModelFactory factory = ViewModelFactory.getInstance(getActivity().getApplication());

    taskCreateViewModel = ViewModelProviders.of(getActivity(), factory).get(TaskCreateViewModel.class);
    salesCreateViewModel = ViewModelProviders.of(getActivity(),factory).get(HomeViewModel.class);
  }
}