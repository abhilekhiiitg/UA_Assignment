package com.abhilekh.unacademydummy

import java.util.concurrent.ThreadFactory

class MyThreadFactory : ThreadFactory
{
    override fun newThread(runnable: Runnable?) = Thread(runnable)
}