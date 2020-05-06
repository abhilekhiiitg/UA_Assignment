package com.abhilekh.uaassignment

import java.util.concurrent.Callable
import java.util.concurrent.Future


abstract class Task<T>{

    // Functions to be implemented by the user.
    abstract fun onExecuteTask(): T?
    abstract fun onTaskComplete(valueFromDoInBackground: T)


}

