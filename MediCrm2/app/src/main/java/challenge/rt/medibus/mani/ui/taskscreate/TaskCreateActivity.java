package challenge.rt.medibus.mani.ui.taskscreate;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import challenge.rt.medibus.mani.R;
import challenge.rt.medibus.mani.ViewModelFactory;
import challenge.rt.medibus.mani.data.SalesRepDetail;
import challenge.rt.medibus.mani.data.TaskDetail;
import java.util.Calendar;
import me.relex.circleindicator.CircleIndicator;

public class TaskCreateActivity extends AppCompatActivity {

  /**
   * The {@link android.support.v4.view.PagerAdapter} that will provide
   * fragments for each of the sections. We use a
   * {@link FragmentPagerAdapter} derivative, which will keep every
   * loaded fragment in memory. If this becomes too memory intensive, it
   * may be best to switch to a
   * {@link android.support.v4.app.FragmentStatePagerAdapter}.
   */
  private TaskSectionsPagerAdapter mSectionsPagerAdapter;

  /**
   * The {@link ViewPager} that will host the section contents.
   */
  private ViewPager mViewPager;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sales_create);

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    // Create the adapter that will return a fragment for each of the three
    // primary sections of the activity.
    mSectionsPagerAdapter = new TaskSectionsPagerAdapter(getSupportFragmentManager());

    // Set up the ViewPager with the sections adapter.
    mViewPager = (ViewPager) findViewById(R.id.container);
    mViewPager.setAdapter(mSectionsPagerAdapter);

    CircleIndicator indicator = findViewById(R.id.indicator);
    indicator.setViewPager(mViewPager);

    //Receive the viewmodel here
    //if present pass it viewmodel, else initialize n send to viewmodel
    Intent intent = getIntent();
    TaskDetail salesRepDetail = null;
    if(intent!=null && intent.hasExtra("KEY")) {
      salesRepDetail = (TaskDetail) intent.getExtras().getSerializable("KEY");
    }

    initializeViewModel(salesRepDetail);
  }

  private void initializeViewModel(TaskDetail taskDetail) {
    ViewModelFactory factory = ViewModelFactory.getInstance(getApplication());

    TaskCreateViewModel
        salesRepViewModel = ViewModelProviders.of(this, factory).get(TaskCreateViewModel.class);

    if(taskDetail == null) {
      taskDetail = new TaskDetail();
      taskDetail.taskName = "";
      taskDetail.taskDescription = "";
      taskDetail.startDate = Calendar.getInstance().getTimeInMillis();
      taskDetail.endDate = Calendar.getInstance().getTimeInMillis();
      taskDetail.pharmacyName = "";
      taskDetail.taskStatus = 0;

    }
    salesRepViewModel.setTaskDetail(taskDetail);

  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_sales_create, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }




}
