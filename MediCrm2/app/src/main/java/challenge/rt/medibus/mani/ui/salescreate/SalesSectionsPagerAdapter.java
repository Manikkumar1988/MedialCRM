package challenge.rt.medibus.mani.ui.salescreate;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import challenge.rt.medibus.mani.ui.salescreate.formfields.SalesRepAddressFragment;
import challenge.rt.medibus.mani.ui.salescreate.formfields.SalesRepEmailFragment;
import challenge.rt.medibus.mani.ui.salescreate.formfields.SalesRepExperienceFragment;
import challenge.rt.medibus.mani.ui.salescreate.formfields.SalesRepNameFragment;
import challenge.rt.medibus.mani.ui.salescreate.formfields.SalesRepPhoneFragment;
import java.util.ArrayList;

/**
   * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
   * one of the sections/tabs/pages.
   */
  public class SalesSectionsPagerAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    public SalesSectionsPagerAdapter(FragmentManager fm) {
      super(fm);
      fragmentArrayList.add(SalesRepNameFragment.newInstance(1, "Enter Sales Reprenstative Name"));
      fragmentArrayList.add(SalesRepExperienceFragment.newInstance(2, "Years of Experience"));
      fragmentArrayList.add(SalesRepPhoneFragment.newInstance(2,"Enter Sales Reprenstative Phone No"));
      fragmentArrayList.add(SalesRepEmailFragment.newInstance(1,"Enter Sales Reprenstative Email"));
      fragmentArrayList.add(
          SalesRepAddressFragment.newInstance(1,"Enter Sales Reprenstative Address"));
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