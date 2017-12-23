package challenge.mani.com.retailstore.inventory;

import android.os.Bundle;
import challenge.mani.com.retailstore.R;
import challenge.mani.com.retailstore.util.ActivityUtils;
import challenge.mani.com.retailstore.util.BaseActivity;

public class InventoryActivity extends BaseActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_inventory);

    setupViewFragment();
  }

  private void setupViewFragment() {
    InventoryFragment inventoryFragment =
        (InventoryFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

    if (inventoryFragment == null) {
      // Create the fragment
      inventoryFragment = InventoryFragment.newInstance();

      ActivityUtils.replaceFragmentInActivity(
          getSupportFragmentManager(), inventoryFragment, R.id.contentFrame);
    }
  }
}
