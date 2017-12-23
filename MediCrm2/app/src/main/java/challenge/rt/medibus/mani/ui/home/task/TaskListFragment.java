package challenge.rt.medibus.mani.ui.home.task;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.Observer;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import challenge.rt.medibus.mani.R;
import challenge.rt.medibus.mani.ViewModelFactory;
import challenge.rt.medibus.mani.data.TaskDetail;
import challenge.rt.medibus.mani.ui.home.HomeViewModel;
import challenge.rt.medibus.mani.ui.salescreate.SalesCreateActivity;
import challenge.rt.medibus.mani.ui.taskscreate.TaskCreateActivity;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaskListFragment extends LifecycleFragment {


  private HomeViewModel homeViewModel;

  private TaskListAdapter mTaskAdapter;

  @BindView(R.id.task_list_fragment) RecyclerView taskListRecyclerView;

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View rootView =  inflater.inflate(R.layout.fragment_task_list, container, false);
    ButterKnife.bind(this, rootView);

    setupRecyclerView(rootView);
    return rootView;
  }

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    initializeViewModel();

    listenForProducts();

    listenForDataLoadingEvent();
  }

  private void listenForDataLoadingEvent() {

  }

  private void listenForProducts() {
    homeViewModel.getTaskList().observe(this, new Observer<List<TaskDetail>>() {
      @Override public void onChanged(@Nullable List<TaskDetail> salesRepDetails) {
        if(salesRepDetails!=null && salesRepDetails.size()>0) {
          mTaskAdapter.addItems(salesRepDetails);
        }
      }
    });
  }

  private void initializeViewModel() {
    ViewModelFactory factory = ViewModelFactory.getInstance(getActivity().getApplication());

    homeViewModel = ViewModelProviders.of(getActivity(), factory).get(HomeViewModel.class);

  }

  private void setupRecyclerView(View v) {
    LinearLayoutManager
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

    taskListRecyclerView.setLayoutManager(layoutManager);

    mTaskAdapter = new TaskListAdapter(getContext(), new ArrayList<TaskDetail>(), new TaskListAdapter.OnItemClickListener() {
      @Override public void onItemClick(TaskDetail salesRepDetail) {

        Intent taskCreateActivityIntent = new Intent(getContext(), TaskCreateActivity.class);
        taskCreateActivityIntent.putExtra("KEY", salesRepDetail);

        startActivity(taskCreateActivityIntent);
      }
    });

    taskListRecyclerView.setAdapter(mTaskAdapter);

    final DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
        taskListRecyclerView.getContext(),
        layoutManager.getOrientation());

    taskListRecyclerView.addItemDecoration(dividerItemDecoration);
  }
}
