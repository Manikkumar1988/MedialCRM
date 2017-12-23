package challenge.rt.medibus.mani.ui.home.task;/**
 * Created by mani on 20/12/17.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import challenge.rt.medibus.mani.R;
import challenge.rt.medibus.mani.data.TaskDetail;
import java.util.List;

import butterknife.ButterKnife;

public class TaskListAdapter extends RecyclerView.Adapter<TaskViewHolder> {

  private static final String TAG = TaskListAdapter.class.getSimpleName();

  private Context context;
  private List<TaskDetail> list;
  private OnItemClickListener onItemClickListener;

  public TaskListAdapter(Context context, List<TaskDetail> list,
      OnItemClickListener onItemClickListener) {
    this.context = context;
    this.list = list;
    this.onItemClickListener = onItemClickListener;
  }

  @Override public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    Context context = parent.getContext();
    LayoutInflater inflater = LayoutInflater.from(context);

    View view = inflater.inflate(R.layout.task_detail_list_item, parent, false);
    ButterKnife.bind(this, view);

    TaskViewHolder viewHolder = new TaskViewHolder(view);

    return viewHolder;
  }

  @Override public void onBindViewHolder(TaskViewHolder holder, int position) {
    TaskDetail item = list.get(position);
    holder.bind(item, onItemClickListener);
  }

  @Override public int getItemCount() {
    return list.size();
  }

  public void addItems(List<TaskDetail> salesRepDetails) {
    list.clear();
    list.addAll(salesRepDetails);
    notifyDataSetChanged();
  }

  public interface OnItemClickListener {
    void onItemClick(TaskDetail taskDetail);
  }
}
