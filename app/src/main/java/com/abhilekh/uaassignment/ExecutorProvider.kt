package com.abhilekh.unacademydummy

import java.util.concurrent.*

public object ExecutorProvider
{
    private val NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors()
    private var backgroundTasksThreadFactory: ThreadFactory = MyThreadFactory()

    var backgroundTaskExecutor: ThreadPoolExecutor? = null
    var mainThreadTaskExecutor: Executor? = null

    init {
        backgroundTaskExecutor = ThreadPoolExecutor(1,
            1,
            60L,
            TimeUnit.SECONDS,
            LinkedBlockingDeque<Runnable>(),
            backgroundTasksThreadFactory
        )
        mainThreadTaskExecutor = MainThreadExecutor()
    }
}