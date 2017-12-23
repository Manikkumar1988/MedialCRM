package challenge.rt.medibus.mani.ui.taskscreate.formfields.map;/**
 * Created by mani on 21/12/17.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import challenge.rt.medibus.mani.R;
import noman.googleplaces.Place;

public class PharmacyViewHolder extends RecyclerView.ViewHolder {

  @BindView(R.id.pharmacy_name) TextView pharmacyName;

  public PharmacyViewHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }

  public void bind(final Place model, final PharmacyListAdapter.OnItemClickListener listener) {

    pharmacyName.setText(model.getName());

    itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        listener.onItemClick(model);
      }
    });
  }
}