package com.example.priadko.switchmedia.home_screen.interactor;

import android.os.AsyncTask;
import android.util.Log;

import com.example.priadko.switchmedia.home_screen.interactor.retrofit_model.Api;
import com.example.priadko.switchmedia.home_screen.interactor.retrofit_model.PatternItemModel;
import com.example.priadko.switchmedia.home_screen.interactor.retrofit_model.PatternModel;
import com.google.common.cache.Cache;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

import static com.example.priadko.switchmedia.Constants.CACHE_2D_DATA_KEY;
import static com.example.priadko.switchmedia.Constants.INDEX_TITLE;
import static com.example.priadko.switchmedia.Constants.INDEX_URL;
import static com.example.priadko.switchmedia.Constants.INNER_ITEM_COUNT;
import static com.example.priadko.switchmedia.Constants.ITEM_COUNT;

/**
 * SwitchMedia
 * Oleksandr Priadko
 *
 */

class ExecutorHandler extends AsyncTask<Void, Void, String[][]> {
    //link from document.
    private static final String BASE_URL = "http://www.colourlovers.com/api/patterns/";
    private static final String TAG = "ExecutorAsync";
    // in millisecond
    private static final int TERMINATION_PERIOD_MILLIS = 500;

    private LoadDataListener listener;
    private ExecutorService pool;
    private Cache<String, String[][]> cache;
    private Api apiService;

    ExecutorHandler(LoadDataListener listener, Cache<String, String[][]> cache) {
        this.listener = listener;
        this.cache = cache;
        intiRetrofit();
    }

    private void intiRetrofit() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        apiService = retrofit.create(Api.class);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        listener.loadingStarted();
    }

    @Override
    protected final String[][] doInBackground(Void... voids) {
        // if we are here - it is means that cache is expired or empty
        String[][] data = new String[ITEM_COUNT][INNER_ITEM_COUNT];
        pool = Executors.newFixedThreadPool(data.length);
        //execute runnable in ExecutorService
        for (int i = 0; i < ITEM_COUNT; ++i) {
            pool.execute(new SingleRequest(data, i, apiService));
        }
        Log.i(TAG, "started");
        pool.shutdown();
        //check if all runnable is finished
        //NOTE - do not move from AsyncTask's background. Otherwise, Blocks UI thread
        while (!pool.isTerminated()) {
            try {
                if (!pool.awaitTermination(TERMINATION_PERIOD_MILLIS, TimeUnit.MILLISECONDS)) {
                    Log.i(TAG, "processing");
                }
            } catch (InterruptedException ignore) {
                Thread.currentThread().interrupt();
            }
        }
        //return filled array to onPostExecute
        return data;
    }

    @Override
    protected void onPostExecute(String[][] data) {
        super.onPostExecute(data);
        if (!isDataEmpty(data)) {
            cache.put(CACHE_2D_DATA_KEY, data);
            Log.i("ExecutorAsync", "saved to cache");
        }
        listener.loaded(data);
        Log.i(TAG, "done");
    }

    @Override
    protected void onCancelled(String[][] strings) {
        //if canceled - shutdown immediately
        pool.shutdownNow();
        listener = null;
        super.onCancelled(strings);
        Log.i(TAG, "canceled");
        Log.i(TAG, pool.isShutdown() + " isShutDown");
        Log.i(TAG, pool.isTerminated() + " isTerminated");
    }

    private boolean isDataEmpty(String[][] data) {
        String[][] empty = new String[ITEM_COUNT][INNER_ITEM_COUNT];
        return Arrays.deepEquals(data, empty);
    }

    /**
     * Performs request to colorlovers.com
     */
    private class SingleRequest implements Runnable {
        private String[][] data;
        //where to put data from response
        private int index;
        private Api api;

        SingleRequest(final String[][] data, final int index, Api api) {
            this.data = data;
            this.index = index;
            this.api = api;
        }

        @Override
        public void run() {
            //synchronous request to colorlovers
            Call<PatternModel> call = api.getPattern();
            try {
                //if something will be wrong - title and url will not be set
                PatternItemModel pattern = call.execute().body().getPattern();
                data[index][INDEX_TITLE] = pattern.getTitle();
                data[index][INDEX_URL] = pattern.getImageUrl();
            } catch (Exception e) {
                e.printStackTrace();
                Log.i(TAG, "data " + index + " failed");
            }
        }
    }
}
