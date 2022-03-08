package edu.byu.cs.tweeter.client.model.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Service {

    //extend from this class
    public Service(Runnable task){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(task);
    }

}
