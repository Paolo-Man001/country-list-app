package com.paolomanlunas.countriesapp;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.Before;
import org.junit.Rule;

import java.util.concurrent.Executor;

import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;

public class ListViewModelTest {
   /* InstantTaskExecutorRule:
    *     - this means that Any task-execution must be instantaneous
    *       and NOT be Asynchronous (like RxJava) and SYNCHRONOUSLY.
    * */
   @Rule
   public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

   @Before
   public void setupRxSchedulers() {
      Scheduler immediate = new Scheduler() {
         @Override
         public Worker createWorker() {
            return new ExecutorScheduler.ExecutorWorker(runnable -> runnable.run(), true);
         }
      };

      RxJavaPlugins.setInitNewThreadSchedulerHandler(scheduler -> immediate);
      RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> immediate);
   }

}

