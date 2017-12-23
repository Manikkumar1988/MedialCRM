package challenge.mani.com.retailstore.inventory;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import challenge.mani.com.retailstore.data.Product;
import challenge.mani.com.retailstore.data.source.remote.ProductsRemoteDataSource;

/**
 * Created by mani on 01/12/17.
 */

public class InventoryViewModel extends AndroidViewModel {

  private ProductsRemoteDataSource mProductsRemoteDataSource;

  public InventoryViewModel(Application application, ProductsRemoteDataSource productsRemoteDataSource) {
    super(application);

    this.mProductsRemoteDataSource = productsRemoteDataSource;

  }

  public void addItemListing(Product product) {
    mProductsRemoteDataSource.addProduct(product);
  }
}
