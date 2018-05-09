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
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import ada.osc.taskie.R;
import ada.osc.taskie.TaskRepository;
import ada.osc.taskie.database.TaskDatabaseHelper;
import ada.osc.taskie.model.Task;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TasksActivity extends AppCompatActivity {

	private TaskRepository mRepository = TaskRepository.getInstance();
	private TaskAdapter mTaskAdapter;
	private TaskDatabaseHelper mDatabaseHelper;
	private Dao<Task, Integer> taskDao;

	@BindView(R.id.fab_tasks_addNew) FloatingActionButton mNewTask;
	@BindView(R.id.recycler_tasks) RecyclerView mTasksRecycler;

	TaskClickListener mListener = new TaskClickListener() {
		@Override
		public void onClick(Task task) {
			toastTask(task);
		}

		@Override
		public void onLongClick(Task task) {
			try {
				taskDao.delete(task);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			updateTasksDisplay();
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tasks);

		initDao();

		ButterKnife.bind(this);
		setUpRecyclerView();
		updateTasksDisplay();
	}

	@Override
	protected void onResume() {
		super.onResume();
		updateTasksDisplay();
	}

	private void initDao() {
		mDatabaseHelper = OpenHelperManager.getHelper(this, TaskDatabaseHelper.class);
		try {
			taskDao = mDatabaseHelper.getTaskDao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
		List<Task> tasks = null;
		try {
			tasks = taskDao.queryForAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		mTaskAdapter.updateTasks(tasks);
	}

	private void toastTask(Task task) {

		Toast.makeText(
				this,
				task.getTitle() + "\n" + task.getDescription(),
				Toast.LENGTH_SHORT
		).show();
	}

	@OnClick(R.id.fab_tasks_addNew)
	public void startNewTaskActivity(){
		Intent newTask = new Intent();
		newTask.setClass(this, NewTaskActivity.class);
		startActivity(newTask);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		OpenHelperManager.releaseHelper();
	}
}
