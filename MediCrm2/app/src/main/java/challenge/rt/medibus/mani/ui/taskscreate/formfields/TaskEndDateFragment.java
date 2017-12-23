package challenge.rt.medibus.mani.ui.taskscreate.formfields;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import challenge.rt.medibus.mani.R;
import challenge.rt.medibus.mani.ViewModelFactory;
import challenge.rt.medibus.mani.ui.taskscreate.TaskCreateViewModel;
import java.util.Calendar;

/**
   * A placeholder fragment containing a simple view.
   */
  public class TaskEndDateFragment extends LifecycleFragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARG_SECTION_TITLE = "section_title";

    @BindView(R.id.start_date) DatePicker valueEditText;

  private TaskCreateViewModel taskCreateViewModel;

  public TaskEndDateFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static TaskEndDateFragment newInstance(int sectionNumber, String title) {
      TaskEndDateFragment
          fragment = new TaskEndDateFragment();
      Bundle args = new Bundle();
      args.putInt(ARG_SECTION_NUMBER, sectionNumber);
      args.putString(ARG_SECTION_TITLE,title);
      fragment.setArguments(args);
      return fragment;
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      View rootView = inflater.inflate(R.layout.fragment_main_date, container, false);
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
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(taskCreateViewModel.getEndDate());

    valueEditText.updateDate(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));

    valueEditText.init(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH),
        new DatePicker.OnDateChangedListener() {

          @Override
          public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {

            Calendar calendar = Calendar.getInstance();
            calendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(),
                0, 0, 0);
            long startTime = calendar.getTimeInMillis();

            taskCreateViewModel.setEndDate(startTime);
          }
        });
  }



  private void initializeViewModel() {
    ViewModelFactory factory = ViewModelFactory.getInstance(getActivity().getApplication());

    taskCreateViewModel = ViewModelProviders.of(getActivity(), factory).get(TaskCreateViewModel.class);

  }

  @Override public void onResume() {
    super.onResume();

  }
}