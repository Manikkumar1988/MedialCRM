package challenge.rt.medibus.mani.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import java.io.Serializable;

import static challenge.rt.medibus.mani.data.SalesRepDetail.TABLE_NAME;

/**
 * Created by mani on 20/12/17.
 */

@Entity(tableName = TABLE_NAME)
public class SalesRepDetail implements Serializable {
  public static final String TABLE_NAME = "sales_table";
  @PrimaryKey
  public String name;
  public String email, address, city, state, country;
  public int experience, phone;
  public double latitude, longitude;

  @Override public boolean equals(Object obj) {
    if(obj instanceof String) {
      return this.name.equals(obj);
    }
    return super.equals(obj);
  }
}
