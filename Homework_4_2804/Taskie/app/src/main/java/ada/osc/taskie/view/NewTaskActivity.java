package ada.osc.taskie.view;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import ada.osc.taskie.R;
import ada.osc.taskie.model.Task;
import ada.osc.taskie.model.TaskPriority;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewTaskActivity extends AppCompatActivity {

	@BindView(R.id.edittext_newtask_title)	EditText mTitleEntry;
	@BindView(R.id.edittext_newtask_description) EditText mDescriptionEntry;
	@BindView(R.id.edittext_newtask_date) EditText mDateEntry;
	@BindView(R.id.spinner_newtask_priority) Spinner mPriorityEntry;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_task);
		ButterKnife.bind(this);
		setUpSpinnerSource();
		setStartingDate();
	}

	private void setStartingDate() {
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("dd, MMMM yyyy");
		mDateEntry.setText(mSimpleDateFormat.format(Calendar.getInstance().getTime()));
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
		String date = mDateEntry.getText().toString();
		TaskPriority priority = (TaskPriority) mPriorityEntry.getSelectedItem();

		if (TextUtils.isEmpty(title) || TextUtils.isEmpty(description)){
			Toast.makeText(this, getString(R.string.newTask_empty_field_text), Toast.LENGTH_SHORT).show();
		}else {
			Task newTask = new Task(title, description, priority, date);
			Intent saveTaskIntent = new Intent(this, TasksActivity.class);
			saveTaskIntent.putExtra(TasksActivity.EXTRA_TASK, newTask);
			setResult(RESULT_OK, saveTaskIntent);
			finish();
		}
	}

	@OnClick(R.id.edittext_newtask_date)
	public void setEntryDate(){
		DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
			@Override
			public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
				Calendar calendar = Calendar.getInstance();
				calendar.set(year, month, dayOfMonth);
				SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("dd, MMMM yyyy");
				mDateEntry.setText(mSimpleDateFormat.format(calendar.getTimeInMillis()));
			}
		};
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		DatePickerDialog mDatePickerDialog = new DatePickerDialog(this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
		mDatePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
		mDatePickerDialog.show();
	}
}
