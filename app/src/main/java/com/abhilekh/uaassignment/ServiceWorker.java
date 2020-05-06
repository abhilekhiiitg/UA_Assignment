package com.abhilekh.uaassignment;

import java.util.ArrayDeque;
import java.util.Queue;


public class ServiceWorker {

    Queue<Task> queue = new ArrayDeque<>();

    public void addTask(Task t) {
        queue.add(t);
        while (!queue.isEmpty()) {
            Task t1 = queue.poll();
          // execute t1
        }
    }
}
