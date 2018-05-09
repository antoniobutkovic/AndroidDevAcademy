package ada.osc.taskie.view;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ada.osc.taskie.R;
import ada.osc.taskie.database.CategoryDao;
import ada.osc.taskie.database.TaskDao;
import ada.osc.taskie.database.TaskRoomDatabase;
import ada.osc.taskie.model.Category;
import ada.osc.taskie.model.Task;
import ada.osc.taskie.model.TaskPriority;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

public class NewTaskActivity extends AppCompatActivity {

	@BindView(R.id.edittext_newtask_title)	EditText mTitleEntry;
	@BindView(R.id.edittext_newtask_description) EditText mDescriptionEntry;
	@BindView(R.id.spinner_newtask_priority) Spinner mPriorityEntry;
	@BindView(R.id.spinner_newtask_category) Spinner mCategoryEntry;

	private TaskDao mTaskDao;
	private CategoryDao mCategoryDao;

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

		List<Category> categories = mCategoryDao.getAllCategories();
		List<String> categoryNames = new ArrayList<>();

		for (Category c : categories){
			categoryNames.add(c.getName());
		}

		mCategoryEntry.setAdapter(
				new ArrayAdapter<>(
						this, android.R.layout.simple_list_item_1, categoryNames
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
        mCategoryDao = database.categoryDao();
    }

    @OnClick(R.id.button_newtask_addcategory)
	public void onAddCategoryButtonClicked(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.custom_add_category_dialog, null);
		builder.setView(view);
		final DialogViewHolder holder = new DialogViewHolder(view);
		final AlertDialog dialog = builder.create();

		holder.mCategorySave.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				String name = holder.mCategoryName.getText().toString();
				String desc = holder.mCategoryDesc.getText().toString();

				mCategoryDao.insert(new Category(name, desc));
				List<Category> categories = mCategoryDao.getAllCategories();
				List<String> categoryNames = new ArrayList<>();

				for (Category c : categories){
					categoryNames.add(c.getName());
				}

				mCategoryEntry.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, categoryNames));

				dialog.dismiss();
			}
		});

		holder.mCancelSave.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});


		dialog.show();
	}

	@OnClick(R.id.imagebutton_newtask_savetask)
	public void saveTask(){
		String title = mTitleEntry.getText().toString();
		String description = mDescriptionEntry.getText().toString();
		TaskPriority priority = (TaskPriority) mPriorityEntry.getSelectedItem();

		if (mCategoryEntry.getSelectedItem() == null){
			Toast.makeText(this, "Please create new category", Toast.LENGTH_SHORT).show();
			onAddCategoryButtonClicked();
			return;
		}

		Category category = mCategoryDao.getCategoryByName(mCategoryEntry.getSelectedItem().toString());
		Task newTask = new Task(title, description, priority, category);
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

	static class DialogViewHolder{

		@BindView(R.id.textview_newtask_categoryname) EditText mCategoryName;
		@BindView(R.id.textview_newtask_categorydesc) EditText mCategoryDesc;
		@BindView(R.id.button_newtask_savecategory) Button mCategorySave;
		@BindView(R.id.button_newtask_cancel) Button mCancelSave;

		DialogViewHolder(View rootView){
			ButterKnife.bind(this, rootView);
		}
	}

}

