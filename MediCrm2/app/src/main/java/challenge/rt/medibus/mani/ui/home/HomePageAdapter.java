package challenge.rt.medibus.mani.ui.home;

import android.arch.lifecycle.LifecycleFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import challenge.rt.medibus.mani.ui.home.salesrep.SalesRepListFragment;
import challenge.rt.medibus.mani.ui.home.task.TaskListFragment;
import challenge.rt.medibus.mani.util.Util;
import java.util.ArrayList;

public class HomePageAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> source = new ArrayList<>();

    public HomePageAdapter(FragmentManager fm) {
        super(fm);
        SalesRepListFragment featuredTailFragment = new SalesRepListFragment();
        source.add(featuredTailFragment);

        TaskListFragment myTailFragment = new TaskListFragment();
        source.add(myTailFragment);
    }

    @Override
    public Fragment getItem(int index) {
        return source.get(index);
    }

    @Override
    public int getCount() {
        return 2;
    }
}