package ada.osc.taskie.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import ada.osc.taskie.R;
import ada.osc.taskie.database.TaskDao;
import ada.osc.taskie.database.TaskRoomDatabase;
import ada.osc.taskie.model.Task;
import ada.osc.taskie.model.TaskPriority;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewTaskActivity extends AppCompatActivity {

	@BindView(R.id.edittext_newtask_title)	EditText mTitleEntry;
	@BindView(R.id.edittext_newtask_description) EditText mDescriptionEntry;
	@BindView(R.id.spinner_newtask_priority) Spinner mPriorityEntry;

	private TaskDao mTaskDao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_task);
		ButterKnife.bind(this);
		initDao();
		setUpSpinnerSource();
	}

	private void setUpSpinnerSource() {
		mPriorityEntry.setAdapter(
				new ArrayAdapter<TaskPriority>(
						this, android.R.layout.simple_list_item_1, TaskPriority.values()
				)
		);

		SharedPreferences sharedPref = getSharedPreferences(getString(R.string.newtask_sharedPrefs_name), Context.MODE_PRIVATE);
		int priority = sharedPref.getInt(getString(R.string.newtask_priorityPrefs_value), 0);

		switch (priority){
			case 0:
				mPriorityEntry.setSelection(0);
				break;
			case 1:
				mPriorityEntry.setSelection(1);
				break;
			case 2:
				mPriorityEntry.setSelection(2);
				break;
			default:
				mPriorityEntry.setSelection(0);
		}
	}

	private void initDao() {
        TaskRoomDatabase database = TaskRoomDatabase.getDatabase(this);
        mTaskDao = database.taskDao();
    }

	@OnClick(R.id.imagebutton_newtask_savetask)
	public void saveTask(){
		String title = mTitleEntry.getText().toString();
		String description = mDescriptionEntry.getText().toString();
		TaskPriority priority = (TaskPriority) mPriorityEntry.getSelectedItem();

		Task newTask = new Task(title, description, priority);

		mTaskDao.insert(newTask);

		SharedPreferences sharedPref = getSharedPreferences(getString(R.string.newtask_sharedPrefs_name), Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();

		switch (priority){
			case LOW:
				editor.putInt(getString(R.string.newtask_priorityPrefs_value), 0);
				break;
			case MEDIUM:
				editor.putInt(getString(R.string.newtask_priorityPrefs_value), 1);
				break;
			case HIGH:
				editor.putInt(getString(R.string.newtask_priorityPrefs_value), 2);
				break;
		}

		editor.commit();

		finish();
	}
}
