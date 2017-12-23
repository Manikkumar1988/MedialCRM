package challenge.rt.medibus.mani.ui.home.task;/**
 * Created by mani on 20/12/17.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import challenge.rt.medibus.mani.R;
import challenge.rt.medibus.mani.data.TaskDetail;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TaskViewHolder extends RecyclerView.ViewHolder {

  @BindView(R.id.task_name) TextView taskName;
  @BindView(R.id.pharmacy_name) TextView pharmacyName;
  @BindView(R.id.task_end_Date) TextView taskEndDate;

  public TaskViewHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }

  public void bind(final TaskDetail model, final TaskListAdapter.OnItemClickListener listener) {
    itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        listener.onItemClick(model);
      }
    });

    taskName.setText(model.taskName);
    pharmacyName.setText(model.pharmacyName);

    Date date=new Date(model.endDate);
    SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yy");
    String dateText = df2.format(date);
    taskEndDate.setText(dateText);
  }
}