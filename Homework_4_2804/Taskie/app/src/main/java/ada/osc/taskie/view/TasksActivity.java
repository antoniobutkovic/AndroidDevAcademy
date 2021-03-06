package ada.osc.taskie.view;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import ada.osc.taskie.R;
import ada.osc.taskie.TaskRepository;
import ada.osc.taskie.model.Task;
import ada.osc.taskie.model.TaskPriority;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TasksActivity extends AppCompatActivity {

	private static final String TAG = TasksActivity.class.getSimpleName();
	private static final int REQUEST_NEW_TASK = 10;
	private static final int REQUEST_EDIT_TASK = 20;
	public static final String EXTRA_TASK = "task";
	public static final String EXTRA_TASK_ID = "extraTaskId";

	TaskRepository mRepository = TaskRepository.getInstance();
	TaskAdapter mTaskAdapter;

	@BindView(R.id.fab_tasks_addNew) FloatingActionButton mNewTask;
	@BindView(R.id.recycler_tasks) RecyclerView mTasksRecycler;

	TaskClickListener mListener = new TaskClickListener() {
		@Override
		public void onClick(Task task) {
			editTask(task);
		}

		@Override
		public void onLongClick(Task task) {
			mRepository.removeTask(task);
			updateTasksDisplay();
		}

		@Override
		public void onToggleClick(Task task) {
			changeTaskState(task);
		}

		@Override
		public void onPriorityImageClick(Task task) {
			task.changePriority();
			updateTasksDisplay();
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tasks);

		ButterKnife.bind(this);
		setUpRecyclerView();
		updateTasksDisplay();
	}

	private void setUpRecyclerView() {

		int orientation = LinearLayoutManager.VERTICAL;

		RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
				this,
				orientation,
				false
		);

		RecyclerView.ItemDecoration decoration =
				new DividerItemDecoration(this, orientation);

		RecyclerView.ItemAnimator animator = new DefaultItemAnimator();

		mTaskAdapter = new TaskAdapter(mListener);

		mTasksRecycler.setLayoutManager(layoutManager);
		mTasksRecycler.addItemDecoration(decoration);
		mTasksRecycler.setItemAnimator(animator);
		mTasksRecycler.setAdapter(mTaskAdapter);
	}

	private void updateTasksDisplay() {
		List<Task> tasks = mRepository.getTasks();
		mTaskAdapter.updateTasks(tasks);
		for (Task t : tasks){
			Log.d(TAG, t.getTitle());
		}
	}

	private void editTask(Task task) {
		Intent editTask = new Intent(this, NewTaskActivity.class);
		editTask.putExtra(NewTaskActivity.EDIT_TASK_ID, task.getId());
		startActivityForResult(editTask, REQUEST_EDIT_TASK);
	}

	private void changeTaskState(Task task) {
		if (task.isCompleted()){
			task.setCompleted(false);
		}else {
			task.setCompleted(true);
		}
	}

	@OnClick(R.id.fab_tasks_addNew)
	public void startNewTaskActivity(){
		Intent newTask = new Intent();
		newTask.setClass(this, NewTaskActivity.class);
		startActivityForResult(newTask, REQUEST_NEW_TASK);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == REQUEST_NEW_TASK && resultCode == RESULT_OK) {
			if (data != null && data.hasExtra(EXTRA_TASK)) {
				Task task = (Task) data.getSerializableExtra(EXTRA_TASK);
				mRepository.saveTask(task);
				updateTasksDisplay();
			}
		}else if (requestCode == REQUEST_EDIT_TASK && resultCode == RESULT_OK){
			if (data != null && data.hasExtra(EXTRA_TASK)) {
				Task task = (Task) data.getSerializableExtra(EXTRA_TASK);
				int taskId = data.getIntExtra(EXTRA_TASK_ID, 0);
				mRepository.editTask(task, taskId);
				updateTasksDisplay();
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()){
			case R.id.item_menu_alltasks:
				updateTasksDisplay();
				break;
			case R.id.item_menu_finishedtasks:
				mTaskAdapter.showFinishedTasks(mRepository.getTasks());
				break;
			case R.id.item_menu_sort:
				mTaskAdapter.sortTasksByPriority(mRepository.getTasks());
				break;
		}
		return true;
	}
}
