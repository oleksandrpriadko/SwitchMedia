package com.example.priadko.switchmedia.home_screen.interactor;

import android.os.AsyncTask;
import android.util.Log;

/**
 * SwitchMedia
 * Oleksandr Priadko
 */

public class FragHomeInteractor implements IFragHomeInteractor {
    private ExecutorHandler executorHandler;

    public void loadData(LoadDataListener listener) {
        if (executorHandler == null) {
            executorHandler = new ExecutorHandler(listener);
            executorHandler.execute();
        } else {
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
