package ada.osc.taskie.view.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ada.osc.taskie.R;
import ada.osc.taskie.model.Task;
import ada.osc.taskie.model.TaskList;
import ada.osc.taskie.networking.ApiService;
import ada.osc.taskie.networking.RetrofitUtil;
import ada.osc.taskie.persistance.TaskDao;
import ada.osc.taskie.persistance.TaskRoomDatabase;
import ada.osc.taskie.util.NetworkUtil;
import ada.osc.taskie.util.SharedPrefsUtil;
import ada.osc.taskie.view.TaskAdapter;
import ada.osc.taskie.view.TaskClickListener;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AllTasksFragment extends Fragment {

    @BindView(R.id.tasks)
    RecyclerView tasks;

    private static TaskAdapter taskAdapter;
    private static Context context;
    private boolean isLoading;
    private boolean isScrolled;
    private int pageNumber = 1;
    private static List<Task> allTasks;
    private TaskDao mTaskDao;

    TaskClickListener mListener = new TaskClickListener() {

        @Override
        public void onClick(Task task) {
            toastTask(task);
        }

        @Override
        public void onLongClick(Task task) {
            if (NetworkUtil.hasConnection(getActivity())) {
                showDeleteAlertDialog(task);
            } else {
                Toast.makeText(getActivity(), "Please connect to the network", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onSwitchClick(Task task, boolean isChecked) {
            if (isChecked) {
                if (NetworkUtil.hasConnection(getActivity())) {
                    sendTaskToFavorites(task);
                } else {
                    Toast.makeText(getActivity(), "Please connect to the network", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    private void toastTask(Task task) {
        Toast.makeText(
                getActivity(),
                task.getId() + " " + task.getTitle() + "\n" +
                        task.getDescription() + " " + task.getPriority().toString() + " " + String.valueOf(task.isFavorite() + " " + String.valueOf(task.isCompleted())),
                Toast.LENGTH_LONG
        ).show();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tasks, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initDao();

        isScrolled = false;
        isLoading = false;

        allTasks = new ArrayList<>();
        context = getActivity();

        tasks.setLayoutManager(new LinearLayoutManager(getActivity()));
        tasks.setItemAnimator(new DefaultItemAnimator());
        taskAdapter = new TaskAdapter(mListener);
        tasks.setAdapter(taskAdapter);

        tasks.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    int visibleItemCount = recyclerView.getLayoutManager().getItemCount();
                    int totalItemCount = recyclerView.getLayoutManager().getChildCount();
                    int pastVisibleItems = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();

                    if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                        if (!isLoading) {
                            isLoading = true;
                            pageNumber++;
                            isScrolled = true;
                            getTasksFromServer();
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        pageNumber = 1;
        if (NetworkUtil.hasConnection(getActivity())) {
            getTasksFromServer();
        } else {
            updateTasksDisplay(mTaskDao.getAllTasks());
        }
    }

    public void getTasksFromServer() {
        Retrofit retrofit = RetrofitUtil.createRetrofit();
        ApiService apiService = retrofit.create(ApiService.class);

        Call<TaskList> taskListCall = apiService
                .getTasks(SharedPrefsUtil.getPreferencesField(context
                        , SharedPrefsUtil.TOKEN), pageNumber);
        taskListCall.enqueue(new Callback<TaskList>() {
            @Override
            public void onResponse(Call<TaskList> call, Response<TaskList> response) {
                if (response.isSuccessful()) {
                    if (isScrolled || allTasks.isEmpty()) {
                        allTasks.addAll(response.body().mTaskList);
                        updateTasksDisplay(allTasks);
                        isScrolled = false;
                        isLoading = false;
                    } else {
                        updateTasksDisplay(response.body().mTaskList);
                    }
                }
            }

            @Override
            public void onFailure(Call<TaskList> call, Throwable t) {

            }
        });
    }

    private void showDeleteAlertDialog(final Task task) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Warning!");
        builder.setMessage("Are you sure you want to delete " + task.getTitle() + " note?");


        builder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteTask(task);
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void deleteTask(Task task) {
        Retrofit retrofit = RetrofitUtil.createRetrofit();
        ApiService apiService = retrofit.create(ApiService.class);

        mTaskDao.delete(task);

        Call<Task> deleteTaskCall = apiService.deleteTask(SharedPrefsUtil.getPreferencesField(context
                , SharedPrefsUtil.TOKEN), task.getId());

        deleteTaskCall.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful()) {
                    pageNumber = 1;
                    getTasksFromServer();
                    new FavoriteTasksFragment().getTasksFromServer();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
            }
        });
    }

    private void sendTaskToFavorites(Task task) {

        Retrofit retrofit = RetrofitUtil.createRetrofit();
        ApiService apiService = retrofit.create(ApiService.class);

        Call<Task> sendTaskToFavoritesCall = apiService.sendTaskToFavorites(SharedPrefsUtil.getPreferencesField(getActivity()
                , SharedPrefsUtil.TOKEN), task.getId());

        sendTaskToFavoritesCall.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful()) {
                    pageNumber = 1;
                    getTasksFromServer();
                    new FavoriteTasksFragment().getTasksFromServer();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
            }
        });
    }

    private void initDao() {
        TaskRoomDatabase database = TaskRoomDatabase.getDatabase(getActivity());
        mTaskDao = database.taskDao();
    }

    private void updateTasksDisplay(List<Task> taskList) {
        taskAdapter.updateTasks(taskList);
    }

}
