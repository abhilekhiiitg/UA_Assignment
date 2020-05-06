package com.abhilekh.uaassignment;

import android.util.Log;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ServiceWorker {

    private static final String TAG = "ServiceWorker";

    private int count = 0;
    private boolean isAnyTaskRunning =false;

    //This single thread executor will let all the task execute on one single thread in a sequential manner
    private ExecutorService executorService;
    private Queue<Task> queue;

    public ServiceWorker() {
        executorService = Executors.newSingleThreadExecutor();
        queue = new ArrayDeque<>();
    }


    public void addTask(Task newTask) {
        queue.add(newTask);

        //if the tasks are already in execution state there is no need to call it
        if(!isAnyTaskRunning)
            executeTasksInOrder();
        else
            Log.i(TAG, "addTask: A task is already executing. Queued up this task.");
    }

    private Task getNextTask() {
            return queue.poll();
    }

    private void executeTasksInOrder() {
        if (!queue.isEmpty()) {

            Task task = getNextTask();
            task.setTaskName("Task:" + (++count));
            task.execute(executorService, new TaskCompletedCallback() {
                @Override
                public void onTaskCompleted(Task task) {
                    executeTasksInOrder();
                    isAnyTaskRunning =false;
                }
            });
            isAnyTaskRunning =true;
        }else
            isAnyTaskRunning =false;
    }


    public interface TaskCompletedCallback {
        void onTaskCompleted(Task task);
    }
}
