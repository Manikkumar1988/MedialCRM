package challenge.rt.medibus.mani.ui.home.salesrep;

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
import challenge.rt.medibus.mani.data.SalesRepDetail;
import challenge.rt.medibus.mani.data.TaskDetail;
import challenge.rt.medibus.mani.ui.home.HomeViewModel;
import challenge.rt.medibus.mani.ui.salescreate.SalesCreateActivity;
import challenge.rt.medibus.mani.ui.salescreate.SalesCreateViewModel;
import challenge.rt.medibus.mani.ui.taskscreate.TaskCreateViewModel;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SalesRepListFragment extends LifecycleFragment {

  private HomeViewModel homeViewModel;

  private SalesRepAdapter mSalesRepAdapter;

  @BindView(R.id.sales_rep_recycler_View) RecyclerView salesRepListRecyclerView;

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View rootView =  inflater.inflate(R.layout.fragment_sales_rep_list, container, false);
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
    homeViewModel.getSalesRepList().observe(this, new Observer<List<SalesRepDetail>>() {
      @Override public void onChanged(@Nullable List<SalesRepDetail> salesRepDetails) {
        if(salesRepDetails!=null && salesRepDetails.size()>0) {
          mSalesRepAdapter.addItems(salesRepDetails);
        }
      }
    });
  }

  private void initializeViewModel() {
    ViewModelFactory factory = ViewModelFactory.getInstance(getActivity().getApplication());

    homeViewModel = ViewModelProviders.of(getActivity(), factory).get(HomeViewModel.class);

   /* SalesCreateViewModel salesCreateViewModel = ViewModelProviders.of(getActivity(),factory).get(SalesCreateViewModel.class);
    SalesRepDetail salesRepDetail = new SalesRepDetail();

    salesRepDetail.name = "dummy"+Calendar.getInstance().getTimeInMillis();
    salesRepDetail.email = "sad@ds";
    salesRepDetail.phone = 3;
    salesRepDetail.experience = 3;
    salesRepDetail.address = "address";
    salesRepDetail.latitude = 13;
    salesRepDetail.longitude = 80;

    salesCreateViewModel.setSalesRepDetail(salesRepDetail);
    salesCreateViewModel.save();*/

    /*TaskCreateViewModel taskCreateViewModel = ViewModelProviders.of(getActivity(),factory).get(TaskCreateViewModel.class);
    TaskDetail taskDetail = new TaskDetail();

    taskDetail.taskName = "taskName"+Calendar.getInstance().getTimeInMillis();
    taskDetail.taskDescription = "description";
    taskDetail.taskStatus = 1;
    taskDetail.startDate = Calendar.getInstance().getTimeInMillis();
    taskDetail.endDate = Calendar.getInstance().getTimeInMillis()  +10000;
    taskDetail.pharmacyName = "pharmacy name";
    taskDetail.salesRep = "asd";

    taskCreateViewModel.setTaskDetail(taskDetail);
    taskCreateViewModel.save();*/
  }

  private void setupRecyclerView(View v) {
    LinearLayoutManager
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

    salesRepListRecyclerView.setLayoutManager(layoutManager);

    mSalesRepAdapter = new SalesRepAdapter(getContext(), new ArrayList<SalesRepDetail>(), new SalesRepAdapter.OnItemClickListener() {
      @Override public void onItemClick(SalesRepDetail salesRepDetail) {

        Intent salesCreateActivityIntent = new Intent(getContext(), SalesCreateActivity.class);
        salesCreateActivityIntent.putExtra("KEY", salesRepDetail);
        startActivity(salesCreateActivityIntent);
      }
    });

    salesRepListRecyclerView.setAdapter(mSalesRepAdapter);

    final DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
        salesRepListRecyclerView.getContext(),
        layoutManager.getOrientation());

    salesRepListRecyclerView.addItemDecoration(dividerItemDecoration);
  }
}
