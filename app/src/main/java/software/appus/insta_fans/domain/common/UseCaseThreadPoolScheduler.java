/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package software.appus.insta_fans.domain.common;

import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import software.appus.insta_fans.presentation.likes_service.UiThreadCallback;
import software.appus.insta_fans.presentation.multithreading.Util;

/**
 * Executes asynchronous tasks using a {@link ThreadPoolExecutor}.
 * <p>
 * See also {@link Executors} for a list of factory methods to create common
 * {@link java.util.concurrent.ExecutorService}s for different scenarios.
 */
public class UseCaseThreadPoolScheduler implements UseCaseScheduler {

    private static UseCaseThreadPoolScheduler sInstance = null;
    private static final int DEFAULT_THREAD_POOL_SIZE = 4;
    private static int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
    private static final int KEEP_ALIVE_TIME = 1;
    private static final TimeUnit KEEP_ALIVE_TIME_UNIT;

    private final ExecutorService mExecutorService;
    private final BlockingQueue<Runnable> mTaskQueue;
    private List<Future> mRunningTaskList;

    private WeakReference<UiThreadCallback> uiThreadCallbackWeakReference;

    // The class is used as a singleton
    static {
        KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
        sInstance = new UseCaseThreadPoolScheduler();
    }

    private Handler mHandler = new Handler();

    // Made constructor private to avoid the class being initiated from outside
    private UseCaseThreadPoolScheduler() {
        // initialize a queue for the thread pool. New tasks will be added to this queue
        mTaskQueue = new LinkedBlockingQueue<Runnable>();

        mRunningTaskList = new ArrayList<>();

        Log.e(Util.LOG_TAG, "Available cores: " + NUMBER_OF_CORES);

        /*
            TODO: You can choose between a fixed sized thread pool and a dynamic sized pool
            TODO: Comment one and uncomment another to see the difference.
         */
        //mExecutorService = Executors.newFixedThreadPool(DEFAULT_THREAD_POOL_SIZE, new BackgroundThreadFactory());
        mExecutorService = new ThreadPoolExecutor(NUMBER_OF_CORES, NUMBER_OF_CORES * 2,
                KEEP_ALIVE_TIME,
                KEEP_ALIVE_TIME_UNIT,
                mTaskQueue,
                new UseCaseThreadPoolScheduler.BackgroundThreadFactory());
    }

    public static UseCaseThreadPoolScheduler getsInstance() {
        return sInstance;
    }

    // Add a callable to the queue, which will be executed by the next available thread in the pool

    @Override
    public void execute(Callable callable) {
        Future future = mExecutorService.submit(callable);
        mRunningTaskList.add(future);
    }

    /* Remove all tasks in the queue and stop all running threads
     * Notify UI thread about the cancellation
     */
    public void cancelAllTasks() {
        synchronized (this) {
            mTaskQueue.clear();
            for (Future task : mRunningTaskList) {
                if (!task.isDone()) {
                    task.cancel(true);
                }
            }
            mRunningTaskList.clear();
        }
        sendMessageToUiThread(Util.createMessage(Util.MESSAGE_ID, "All tasks in the thread pool are cancelled"));
    }

    // Keep a weak reference to the UI thread, so we can send messages to the UI thread
    public void setUiThreadCallback(UiThreadCallback uiThreadCallback) {
        this.uiThreadCallbackWeakReference = new WeakReference<UiThreadCallback>(uiThreadCallback);
    }

    // Pass the message to the UI thread
    public void sendMessageToUiThread(Message message) {
        if (uiThreadCallbackWeakReference != null && uiThreadCallbackWeakReference.get() != null) {
            uiThreadCallbackWeakReference.get().publishToUiThread(message);
        }
    }

    @Override
    public <V extends UseCase.ResponseValue> void notifyResponse(V response, UseCase.UseCaseCallback<V> useCaseCallback) {
        mHandler.post(() -> useCaseCallback.onSuccess(response));
    }

    @Override
    public <V extends UseCase.ResponseValue> void onError(UseCase.UseCaseCallback<V> useCaseCallback, Exception e) {
        mHandler.post(() -> useCaseCallback.onError(e));
    }

    /* A ThreadFactory implementation which create new threads for the thread pool.
       The threads created is set to background priority, so it does not compete with the UI thread.
     */
    private static class BackgroundThreadFactory implements ThreadFactory {
        private static int sTag = 1;

        @Override
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName("CustomThread" + sTag);
            thread.setPriority(Process.THREAD_PRIORITY_BACKGROUND);

            // A exception handler is created to log the exception from threads
            thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                @Override
                public void uncaughtException(Thread thread, Throwable ex) {
                    Log.e(Util.LOG_TAG, thread.getName() + " encountered an error: " + ex.getMessage());
                }
            });
            return thread;
        }
    }


//
//    private final Handler mHandler = new Handler();
//
//    public static final int POOL_SIZE = 2;
//
//    public static final int MAX_POOL_SIZE = 4;
//
//    public static final int TIMEOUT = 30;
//
//    ExecutorService mExecutorService;
//
//    private List<Future> mRunningTaskList;
//    
//    public UseCaseThreadPoolScheduler() {
//        mExecutorService = new F(POOL_SIZE, MAX_POOL_SIZE, TIMEOUT,
//                TimeUnit.SECONDS,mRunningTaskList);
//    }
//
//    @Override
//    public void execute(Callable runnable) {
//        mThreadPoolExecutor.execute(runnable);
//    }

}
