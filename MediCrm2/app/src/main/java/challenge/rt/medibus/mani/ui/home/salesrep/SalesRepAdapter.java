package challenge.rt.medibus.mani.ui.home.salesrep;/**
 * Created by mani on 20/12/17.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import challenge.rt.medibus.mani.R;
import challenge.rt.medibus.mani.data.SalesRepDetail;
import java.util.List;

import butterknife.ButterKnife;

public class SalesRepAdapter extends RecyclerView.Adapter<SalesRepViewHolder> {

  private static final String TAG = SalesRepAdapter.class.getSimpleName();

  private Context context;
  private List<SalesRepDetail> list;
  private OnItemClickListener onItemClickListener;

  public SalesRepAdapter(Context context, List<SalesRepDetail> list,
      OnItemClickListener onItemClickListener) {
    this.context = context;
    this.list = list;
    this.onItemClickListener = onItemClickListener;
  }

  @Override public SalesRepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    Context context = parent.getContext();
    LayoutInflater inflater = LayoutInflater.from(context);

    View view = inflater.inflate(R.layout.sales_rep_detail_item, parent, false);
    ButterKnife.bind(this, view);

    SalesRepViewHolder viewHolder = new SalesRepViewHolder(view);

    return viewHolder;
  }

  @Override public void onBindViewHolder(SalesRepViewHolder holder, int position) {
    SalesRepDetail item = list.get(position);
    holder.bind(item, onItemClickListener);
  }

  @Override public int getItemCount() {
    return list.size();
  }

  public void addItems(List<SalesRepDetail> salesRepDetails) {
    list.clear();
    list.addAll(salesRepDetails);
    notifyDataSetChanged();
  }

  public interface OnItemClickListener {
    void onItemClick(SalesRepDetail salesRepDetail);
  }
}
