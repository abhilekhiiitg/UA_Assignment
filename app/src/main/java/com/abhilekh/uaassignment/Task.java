package com.abhilekh.uaassignment;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;


public abstract class Task<T> {

    private static final String TAG = "TaskNew";

    private static final String DEFAULT_TASK_NAME="Unknown Task";

    private Handler mMainHandler;
    private T result;
    private String taskName;

    public Task(){
        this(DEFAULT_TASK_NAME);
    }
    public Task(String name){
        taskName=name;
        mMainHandler= new Handler(Looper.getMainLooper());
    }

    abstract T onExecuteTask();

    abstract void onTaskComplete(T t);

    void execute(ExecutorService executorService,final ServiceWorker.TaskCompletedCallback taskCompletedCallback)  {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try{
                    result= onExecuteTask();
                }finally {
                    mMainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            onTaskComplete(result);
                            taskCompletedCallback.onTaskCompleted(Task.this);
                        }
                    });
                }
            }
        });
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
