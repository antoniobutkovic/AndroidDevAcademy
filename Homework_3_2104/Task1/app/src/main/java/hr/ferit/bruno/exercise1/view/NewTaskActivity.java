package hr.ferit.bruno.exercise1.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.ferit.bruno.exercise1.R;
import hr.ferit.bruno.exercise1.TasksRepository;
import hr.ferit.bruno.exercise1.model.Task;

public class NewTaskActivity extends AppCompatActivity {

	TasksRepository tasksRepository;

	@BindView(R.id.edittext_newtask_title)
	EditText titleEt;

	@BindView(R.id.edittext_newtask_summary)
	EditText summaryEt;

	@BindView(R.id.edittext_newtask_importance)
	EditText importanceEt;

	@BindView(R.id.textview_newtask_display)
	TextView displayTv;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newtask);
		ButterKnife.bind(this);

		tasksRepository = TasksRepository.getInstance();
	}


	@OnClick(R.id.button_newtask_save)
	public void onSaveBtnClicked(){

		// ToDo: 	store the task
		// Parse data from the widgets and store it in a task
		// Store the task in the fake database using the repository

		String title = titleEt.getText().toString();
		String summary = summaryEt.getText().toString();
		int importance = Integer.parseInt(importanceEt.getText().toString());

		tasksRepository.save(new Task(importance, title, summary));

		// ToDo:	clear the UI for the new task
		// Clear all of the editText controls

		titleEt.setText("");
		summaryEt.setText("");
		importanceEt.setText("");

		// ToDo: 	define a method
		// Create a method for displaying the tasks in the textview as strings
		// one below the other and call it on each new task.

		displayTasks();
	}

	private void displayTasks() {

		if (displayTv.getText().toString().equals(getString(R.string.newtask_notasks))){
			displayTv.setText("");
		}

		displayTv.setText(tasksRepository.getTasks().toString());

	}
}
