package com.example.priadko.switchmedia.home_screen.interactor;

import android.os.AsyncTask;
import android.util.Log;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

import static com.example.priadko.switchmedia.Constants.CACHE_2D_DATA_KEY;

/**
 * SwitchMedia
 * Oleksandr Priadko
 */

public class FragHomeInteractorImpl implements IFragHomeInteractor {
    private static final int CACHE_SIZE_MB = 10;
    private static final int CACHE_EXPIRATION_SECONDS = 10;

    private static Cache<String, String[][]> dataCache;
    // ExecutorHandler Extends AsyncTask. Needed as
    // pool.awaitTermination(TERMINATION_PERIOD, TimeUnit.MILLISECONDS)
    // blocks thread where it was started.
    private ExecutorHandler executorHandler;

    public FragHomeInteractorImpl() {
        if (dataCache == null) {
            dataCache = CacheBuilder.newBuilder()
                    .maximumSize(CACHE_SIZE_MB)
                    .expireAfterWrite(CACHE_EXPIRATION_SECONDS, TimeUnit.SECONDS)
                    .build();
        }
    }

    @Override
    public void loadData(LoadDataListener listener) {
        String[][] fromCache = dataCache.getIfPresent(CACHE_2D_DATA_KEY);
        if (fromCache != null) {
            Log.i("ExecutorAsync", "from cache");
            listener.loaded(fromCache);
            return;
        }
        if (executorHandler == null) {
            executorHandler = new ExecutorHandler(listener, dataCache);
            executorHandler.execute();
        } else {
            //check status. if finished  - create new instance and execute
            AsyncTask.Status status = executorHandler.getStatus();
            Log.i("ExecutorAsync", status + "");
            if (status == AsyncTask.Status.FINISHED) {
                shutDown();
                executorHandler = new ExecutorHandler(listener, dataCache);
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
