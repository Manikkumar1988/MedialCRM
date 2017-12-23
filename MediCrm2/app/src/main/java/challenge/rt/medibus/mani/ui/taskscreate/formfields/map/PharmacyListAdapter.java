package challenge.rt.medibus.mani.ui.taskscreate.formfields.map;/**
 * Created by mani on 21/12/17.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import challenge.rt.medibus.mani.R;
import java.util.List;

import butterknife.ButterKnife;
import noman.googleplaces.Place;

public class PharmacyListAdapter extends RecyclerView.Adapter<PharmacyViewHolder> {

  private static final String TAG = PharmacyListAdapter.class.getSimpleName();

  private Context context;
  private List<Place> list;
  private OnItemClickListener onItemClickListener;

  public PharmacyListAdapter(Context context, List<Place> list, OnItemClickListener onItemClickListener) {
    this.context = context;
    this.list = list;
    this.onItemClickListener = onItemClickListener;
  }

  @Override public PharmacyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    Context context = parent.getContext();
    LayoutInflater inflater = LayoutInflater.from(context);

    View view = inflater.inflate(R.layout.pharmacy_row_item, parent, false);
    ButterKnife.bind(this, view);

    PharmacyViewHolder viewHolder = new PharmacyViewHolder(view);

    return viewHolder;
  }

  @Override public void onBindViewHolder(PharmacyViewHolder holder, int position) {
    Place item = list.get(position);

    //Todo: Setup viewholder for item 
    holder.bind(item, onItemClickListener);
  }

  @Override public int getItemCount() {
    return list.size();
  }

  public void addItems(List<Place> places) {
    list.clear();
    list.addAll(places);
    notifyDataSetChanged();
  }

  public interface OnItemClickListener {
    void onItemClick(Place listModel);
  }
}
