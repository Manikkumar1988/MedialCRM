package challenge.rt.medibus.mani.ui.taskscreate;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import challenge.rt.medibus.mani.ui.taskscreate.formfields.TaskDescriptionFragment;
import challenge.rt.medibus.mani.ui.taskscreate.formfields.TaskEndDateFragment;
import challenge.rt.medibus.mani.ui.taskscreate.formfields.TaskNameFragment;
import challenge.rt.medibus.mani.ui.taskscreate.formfields.TaskSalesRepFragment;
import challenge.rt.medibus.mani.ui.taskscreate.formfields.TaskStartDateFragment;
import challenge.rt.medibus.mani.ui.taskscreate.formfields.TaskStatusFragment;
import challenge.rt.medibus.mani.ui.taskscreate.formfields.map.TaskPharmacyFragment;
import java.util.ArrayList;

/**
   * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
   * one of the sections/tabs/pages.
   */
  public class TaskSectionsPagerAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    public TaskSectionsPagerAdapter(FragmentManager fm) {
      super(fm);

      fragmentArrayList.add(TaskNameFragment.newInstance(1, "Enter Task Name"));
      fragmentArrayList.add(TaskDescriptionFragment.newInstance(1, "Enter Task Description"));
      fragmentArrayList.add(TaskStartDateFragment.newInstance(1,"Enter Start Date"));
      fragmentArrayList.add(TaskEndDateFragment.newInstance(1,"Enter End Date"));
      fragmentArrayList.add(TaskStatusFragment.newInstance(1,"Assign a status"));
      fragmentArrayList.add(TaskSalesRepFragment.newInstance(1,"Choose a Sales Rep"));
      fragmentArrayList.add(TaskPharmacyFragment.newInstance(1,"Choose a Pharmacy"));

    }

    @Override public Fragment getItem(int position) {
      // getItem is called to instantiate the fragment for the given page.
      // Return a TaskNameFragment (defined as a static inner class below).
      return fragmentArrayList.get(position);
    }

    @Override public int getCount() {
      // Show 3 total pages.
      return fragmentArrayList.size();
    }
  }