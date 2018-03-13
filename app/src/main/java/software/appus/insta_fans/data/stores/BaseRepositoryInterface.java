package software.appus.insta_fans.data.stores;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by anatolii.pozniak on 3/13/18.
 */

public interface BaseRepositoryInterface  {

    interface LoadTasksCallback {

        void onTasksLoaded(List<?> tasks);

        void onDataNotAvailable();
    }
    void refreshTasks();

    void deleteAllTasks();

    void deleteTask(@NonNull String taskId);
}
