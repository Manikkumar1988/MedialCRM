package challenge.rt.medibus.mani.ui.home.salesrep;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import challenge.rt.medibus.mani.R;
import challenge.rt.medibus.mani.data.SalesRepDetail;

public class SalesRepViewHolder extends RecyclerView.ViewHolder {

  @BindView(R.id.sales_rep_name) TextView salesRepName;
  @BindView(R.id.sales_address) TextView salesAddress;
  @BindView(R.id.sales_rep_phoneno) TextView salesRepPhoneno;

  public SalesRepViewHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }

  public void bind(final SalesRepDetail model, final SalesRepAdapter.OnItemClickListener listener) {

    salesRepName.setText(model.name);
    salesRepPhoneno.setText(String.valueOf(model.phone));
    salesAddress.setText(model.address);

    itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        listener.onItemClick(model);
      }
    });
  }
}