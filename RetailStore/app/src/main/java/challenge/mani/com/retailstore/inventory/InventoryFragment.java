package challenge.mani.com.retailstore.inventory;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import butterknife.BindView;
import butterknife.ButterKnife;
import challenge.mani.com.retailstore.R;
import challenge.mani.com.retailstore.ViewModelFactory;
import challenge.mani.com.retailstore.cartlisting.CategoryListAdapter;
import challenge.mani.com.retailstore.data.Category;
import challenge.mani.com.retailstore.data.ElectronicsProduct;
import challenge.mani.com.retailstore.data.FurnitureProduct;
import challenge.mani.com.retailstore.data.Product;
import challenge.mani.com.retailstore.productlisting.ProductListingViewModel;
import challenge.mani.com.retailstore.util.BaseFragment;
import java.util.Arrays;

public class InventoryFragment extends BaseFragment {

  @BindView(R.id.category_spinner) Spinner mCategorySpinner;

  @BindView(R.id.add_item_inventory) Button mAddItemToInvtory;

  @BindView(R.id.product_price) EditText productPrice;

  @BindView(R.id.product_name) EditText ProductName;

  protected InventoryViewModel mInventoryViewModel;


  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    View rootView = inflater.inflate(R.layout.fragment_inventory, container, false);

    ButterKnife.bind(this, rootView);

    setupSpinner();

    setUpClickListener();

    return rootView;
  }

  private void setUpClickListener() {
    mAddItemToInvtory.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {

        Product product = null;
        switch (mCategorySpinner.getSelectedItemPosition()) {
          case 0:
            product = new ElectronicsProduct(ProductName.getText().toString(),Category.ELECTRONICS.name(),"","3",Float.valueOf(productPrice.getText().toString()));
            break;
          case 1:
            product = new FurnitureProduct(ProductName.getText().toString(),Category.ELECTRONICS.name(),"","3",Float.valueOf(productPrice.getText().toString()));
            break;
        }

        mInventoryViewModel.addItemListing(product);
      }
    });
  }

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    initializeViewModel();
  }

  private void setupSpinner() {

    ArrayAdapter<CharSequence> adapter = new CategoryListAdapter(getContext(), android.R.layout.simple_spinner_item,
        android.R.id.text1, Arrays.asList(Category.values()));

    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    mCategorySpinner.setAdapter(adapter);

  }


  private void initializeViewModel() {
    // Use a Factory to inject dependencies into the ViewModel
    ViewModelFactory factory = ViewModelFactory.getInstance(getActivity().getApplication());

    mInventoryViewModel = ViewModelProviders.of(getActivity(), factory).get(InventoryViewModel.class);
  }

  public static InventoryFragment newInstance() {
    return new InventoryFragment();
  }

  public InventoryFragment() {
    // Required empty public constructor
  }

}
