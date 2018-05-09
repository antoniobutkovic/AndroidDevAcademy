package ada.osc.taskie.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

import ada.osc.taskie.R;
import ada.osc.taskie.database.TaskDatabaseHelper;
import ada.osc.taskie.model.Task;
import ada.osc.taskie.model.TaskPriority;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewTaskActivity extends AppCompatActivity {

	@BindView(R.id.edittext_newtask_title)	EditText mTitleEntry;
	@BindView(R.id.edittext_newtask_description) EditText mDescriptionEntry;
	@BindView(R.id.spinner_newtask_priority) Spinner mPriorityEntry;

	private TaskDatabaseHelper mDatabaseHelper;
	private Dao<Task, Integer> taskDao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_task);

		initDao();

		ButterKnife.bind(this);
		setUpSpinnerSource();
	}

	private void initDao() {
		mDatabaseHelper = OpenHelperManager.getHelper(this, TaskDatabaseHelper.class);
		try {
			taskDao = mDatabaseHelper.getTaskDao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void setUpSpinnerSource() {
		mPriorityEntry.setAdapter(
				new ArrayAdapter<TaskPriority>(
						this, android.R.layout.simple_list_item_1, TaskPriority.values()
				)
		);
		mPriorityEntry.setSelection(0);
	}

	@OnClick(R.id.imagebutton_newtask_savetask)
	public void saveTask(){
		String title = mTitleEntry.getText().toString();
		String description = mDescriptionEntry.getText().toString();
		TaskPriority priority = (TaskPriority) mPriorityEntry.getSelectedItem();

		Task newTask = new Task(title, description, priority);
		try {
			taskDao.create(newTask);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		finish();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		OpenHelperManager.releaseHelper();
	}
}
