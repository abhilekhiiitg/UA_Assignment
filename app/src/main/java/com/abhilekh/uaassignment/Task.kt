package com.abhilekh.uaassignment

import com.abhilekh.unacademydummy.ExecutorProvider
import java.util.concurrent.Callable
import java.util.concurrent.Future


abstract class Task<ResultTypeWeWantOnPostExecute>{

    // Functions to be implemented by the user.


    abstract fun onExecuteTask(): ResultTypeWeWantOnPostExecute?
    abstract fun onTaskComplete(valueFromDoInBackground: ResultTypeWeWantOnPostExecute)

    fun execute() {

        ExecutorProvider.backgroundTaskExecutor?.let {
            val future: Future<ResultTypeWeWantOnPostExecute> = it.submit(Callable<ResultTypeWeWantOnPostExecute>
            {
                onExecuteTask()
            })
            val valueFromDoInBackground = future.get()

            onTaskComplete(valueFromDoInBackground)
        }
    }
}

