package ada.osc.taskie.networking;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import ada.osc.taskie.model.Task;
import ada.osc.taskie.model.TaskList;
import ada.osc.taskie.persistance.TaskDao;
import ada.osc.taskie.persistance.TaskRoomDatabase;
import ada.osc.taskie.util.NetworkUtil;
import ada.osc.taskie.util.SharedPrefsUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Toni on 5/23/2018.
 */

public class NetworkAvilabilityReceiver extends BroadcastReceiver{

    private ApiService apiService = RetrofitUtil.createRetrofit().create(ApiService.class);

    @Override
    public void onReceive(Context context, Intent intent) {
        if (NetworkUtil.hasConnection(context)){
            for (Task t : getAllTasks(context)){
                if (!t.isCompleted()){
                    sendOfflineTasksToRemoteDatabase(context, t);
                }
            }
        }
    }

    private List<Task> getAllTasks(Context context) {
        TaskDao taskDao = TaskRoomDatabase.getDatabase(context).taskDao();
        return  taskDao.getAllTasks();
    }

    private void sendOfflineTasksToRemoteDatabase(Context context, final Task taskToSave) {
        Call postNewTaskCall = apiService
                .postNewTask(SharedPrefsUtil.getPreferencesField(context
                        , SharedPrefsUtil.TOKEN), taskToSave);

        postNewTaskCall.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }
}
