package ada.osc.taskie.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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
import ada.osc.taskie.database.TaskDao;
import ada.osc.taskie.database.TaskRoomDatabase;
import ada.osc.taskie.model.Task;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TasksActivity extends AppCompatActivity {

    private static final String TAG = TasksActivity.class.getSimpleName();
    private static final int REQUEST_NEW_TASK = 10;
    public static final String EXTRA_TASK = "task";

    //	TaskRepository mRepository = TaskRepository.getInstance();
    private TaskDao mTaskDao;

    TaskAdapter mTaskAdapter;

    @BindView(R.id.fab_tasks_addNew)
    FloatingActionButton mNewTask;
    @BindView(R.id.recycler_tasks)
    RecyclerView mTasksRecycler;

    TaskClickListener mListener = new TaskClickListener() {
        @Override
        public void onClick(Task task) {
            toastTask(task);
        }

        @Override
        public void onLongClick(Task task) {
            showDeleteAlertDialog(task);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        ButterKnife.bind(this);

        TaskRoomDatabase database = TaskRoomDatabase.getDatabase(this);
        mTaskDao = database.taskDao();

        setUpRecyclerView();
        updateTasksDisplay();
    }

    @Override
    protected void onResume() {
        super.onResume();
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
        List<Task> tasks = mTaskDao.getAllTasksAsc();
        mTaskAdapter.updateTasks(tasks);
        for (Task t : tasks) {
            Log.d(TAG, t.getTitle());
        }
    }

    private void toastTask(Task task) {
        Toast.makeText(
                this,
                task.getTitle() + "\n" + task.getDescription(),
                Toast.LENGTH_SHORT
        ).show();
    }

    @OnClick(R.id.fab_tasks_addNew)
    public void startNewTaskActivity() {
        Intent newTask = new Intent();
        newTask.setClass(this, NewTaskActivity.class);
        startActivity(newTask);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_tasks_sortasc:
                sortTasksAscByPriority();
                break;
            case R.id.item_tasks_sortdesc:
                sortTasksDescByPriority();
                break;
        }
        return true;
    }

    private void sortTasksAscByPriority() {
        List<Task> tasks = mTaskDao.getAllTasksAsc();
        mTaskAdapter.updateTasks(tasks);
    }

    private void sortTasksDescByPriority() {
        List<Task> tasks = mTaskDao.getAllTasksDesc();
        mTaskAdapter.updateTasks(tasks);
    }

    private void showDeleteAlertDialog(final Task task) {
        AlertDialog.Builder builder = new AlertDialog.Builder(TasksActivity.this);

        builder.setTitle(R.string.tasks_warning_message);
        builder.setMessage(R.string.tasks_delete_message);
        builder.setPositiveButton(R.string.tasks_negativebtn_msg, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mTaskDao.delete(task);
                updateTasksDisplay();
                dialog.dismiss();
            }
        });

        builder.setNegativeButton(R.string.tasks_positivebtn_msg, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
