package com.example.priadko.switchmedia.home_screen.interactor;

import android.os.AsyncTask;
import android.util.Log;

/**
 * SwitchMedia
 * Oleksandr Priadko
 */

public class FragHomeInteractorImpl implements IFragHomeInteractor {
    /**
     * Extends AsyncTask. Needed as pool.awaitTermination(TERMINATION_PERIOD, TimeUnit.MILLISECONDS)
     * blocks thread where it started.
     */
    private ExecutorHandler executorHandler;

    @Override
    public void loadData(LoadDataListener listener) {
        if (executorHandler == null) {
            executorHandler = new ExecutorHandler(listener);
            executorHandler.execute();
        } else {
            //check status. if finished  - create new instance and execute
            AsyncTask.Status status = executorHandler.getStatus();
            Log.i("ExecutorAsync", status + "");
            if (status == AsyncTask.Status.FINISHED) {
                shutDown();
                executorHandler = new ExecutorHandler(listener);
                executorHandler.execute();
            }
        }
    }

    @Override
    public void shutDown() {
        if (executorHandler != null) {
            executorHandler.cancel(false);
            executorHandler = null;
        }
    }
}
