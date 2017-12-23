package challenge.rt.medibus.mani.ui.salescreate;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import challenge.rt.medibus.mani.Injection;
import challenge.rt.medibus.mani.data.SalesRepDetail;
import challenge.rt.medibus.mani.data.source.SalesRepDataSource;
import challenge.rt.medibus.mani.data.source.remote.myApi.MyApi;
import challenge.rt.medibus.mani.data.source.remote.myApi.model.SalesRep;
import challenge.rt.medibus.mani.util.Util;
import com.google.android.gms.location.places.Place;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import java.io.IOException;
import java.util.List;

/**
 * Created by mani on 20/12/17.
 */

public class SalesCreateViewModel extends AndroidViewModel {

  public SalesCreateViewModel(Application application,SalesRepDataSource salesRepDataSource) {
    super(application);
    this.salesRepDataSource = salesRepDataSource;
  }

  private SalesRepDetail salesRepDetail;

  private SalesRepDataSource salesRepDataSource;

  public void setSalesRepDetail(SalesRepDetail salesRepDetail) {
    this.salesRepDetail = salesRepDetail;
  }


  public void setName(String name) {
    salesRepDetail.name = name;
  }

  public void setExperience(int yearsOfExperience) {
    salesRepDetail.experience = yearsOfExperience;
  }

  public void setPhone(int phoneNo) {
    salesRepDetail.phone = phoneNo;
  }

  public void setAddress(Place place) {
    salesRepDetail.address = place.getAddress().toString();
    setLatitude(place.getLatLng().latitude);
    setLongitude(place.getLatLng().longitude);
  }

  public void setLatitude(double latitude) {
    salesRepDetail.latitude = latitude;
  }

  public void setLongitude(double longitude) {
    salesRepDetail.longitude = longitude;
  }

  public String getName() {
    return salesRepDetail.name;
  }

  public String getExperience() {
    return String.valueOf(salesRepDetail.experience);
  }

  public String getPhone() {
    return String.valueOf(salesRepDetail.phone);
  }

  public String getAddress() {
    return salesRepDetail.address;
  }

  public void save() {
    new Thread() {
      @Override public void run() { //TODO: Reuse threads or move as Async operation

        Injection.proviceSalesRepRemoteDataSource().addSalesRep(salesRepDetail);
        salesRepDataSource.addSalesRep(salesRepDetail);

      }
    }.start();
  }

  public String getEmail() {
    return salesRepDetail.email;
  }

  public void setEmail(String email) {
    salesRepDetail.email = email;
  }
}
