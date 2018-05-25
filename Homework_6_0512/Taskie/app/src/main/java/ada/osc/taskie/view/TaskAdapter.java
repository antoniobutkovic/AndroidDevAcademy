package ada.osc.taskie.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ada.osc.taskie.R;
import ada.osc.taskie.model.Task;
import ada.osc.taskie.util.NetworkUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>{

	private List<Task> mTasks;
	private TaskClickListener mListener;
	private Context context;

	public TaskAdapter(Context context, TaskClickListener listener) {
		mListener = listener;
		this.context = context;
		mTasks = new ArrayList<>();
	}

	public void updateTasks(List<Task> tasks){
		mTasks.clear();
		mTasks.addAll(tasks);
		notifyDataSetChanged();
	}


	@NonNull
	@Override
	public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.item_task, parent, false);
		return new TaskViewHolder(itemView, mListener);
	}

	@Override
	public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {

		Task current = mTasks.get(position);
		holder.mTitle.setText(current.getTitle());
		holder.mDescription.setText(current.getDescription());

		if (current.isFavorite()){
			holder.mFavoriteSwitch.setChecked(true);
		}else {
			holder.mFavoriteSwitch.setChecked(false);
		}

		int color = R.color.taskPriority_Unknown;
		switch (current.getPriority()){
			case LOW: color = R.color.taskpriority_low; break;
			case MEDIUM: color = R.color.taskpriority_medium; break;
			case HIGH: color = R.color.taskpriority_high; break;
		}

		holder.mPriority.setImageResource(color);
	}

	@Override
	public int getItemCount() {
		return mTasks.size();
	}

    class TaskViewHolder extends RecyclerView.ViewHolder {

		@BindView(R.id.textview_task_title) TextView mTitle;
		@BindView(R.id.textview_task_description) TextView mDescription;
		@BindView(R.id.imageview_task_priority) ImageView mPriority;
		@BindView(R.id.switch_task_favorite) Switch mFavoriteSwitch;

		public TaskViewHolder(View itemView, TaskClickListener listener) {
			super(itemView);
			ButterKnife.bind(this, itemView);
		}

		@OnClick
		public void onTaskClick(){
			mListener.onClick(mTasks.get(getAdapterPosition()));
		}

		@OnLongClick
		public boolean onTaskLongClick(){
			mListener.onLongClick(mTasks.get(getAdapterPosition()));
			return true;
		}

		@OnClick(R.id.switch_task_favorite)
		public void onSwitchClick(){
			if (NetworkUtil.hasConnection(context)) {
				mListener.onSwitchClick(mTasks.get(getAdapterPosition()), mFavoriteSwitch.isChecked());
			} else {
				mFavoriteSwitch.setChecked(false);
				Toast.makeText(context, "Please connect to the network", Toast.LENGTH_SHORT).show();
			}
		}

	}
}
