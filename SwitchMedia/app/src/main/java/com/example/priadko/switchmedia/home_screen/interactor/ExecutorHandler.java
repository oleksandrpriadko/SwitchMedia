package com.example.priadko.switchmedia.home_screen.interactor;

import android.os.AsyncTask;
import android.util.Log;

import com.example.priadko.switchmedia.home_screen.interactor.retrofit_model.Api;
import com.example.priadko.switchmedia.home_screen.interactor.retrofit_model.PatternItemModel;
import com.example.priadko.switchmedia.home_screen.interactor.retrofit_model.PatternModel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * SwitchMedia
 * Oleksandr Priadko
 */

class ExecutorHandler extends AsyncTask<Void, Void, String[][]> {
    private static final String BASE_URL = "http://www.colourlovers.com/api/patterns/";
    private static final String TAG = "ExecutorAsync";
    private static final int ITEM_COUNT = 40;
    private static final int INNER_ITEM_COUNT = 2;
    private static final int TITLE = 0;
    private static final int URL = 1;
    private static final int TERMINATION_PERIOD = 100;

    private LoadDataListener listener;
    private ExecutorService pool;
    private Api apiService;
    private String[][] data;

    ExecutorHandler(LoadDataListener listener) {
        this.listener = listener;
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

    private boolean isDataNull() {
        return data == null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        listener.loadingStarted();
    }

    @Override
    protected String[][] doInBackground(Void... voids) {
        data = new String[ITEM_COUNT][INNER_ITEM_COUNT];
        pool = Executors.newFixedThreadPool(data.length);
        for (int i = 0; i < ITEM_COUNT; ++i) {
            pool.execute(new SingleRequest(data, i, apiService));
        }
        Log.i(TAG, "started");
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                if (!pool.awaitTermination(TERMINATION_PERIOD, TimeUnit.MILLISECONDS)) {
                    Log.i(TAG, "processing");
                }
            } catch (InterruptedException ignore) {
            }
        }
        return data;
    }

    @Override
    protected void onPostExecute(String[][] data) {
        super.onPostExecute(data);
        if (isDataNull()) {
            listener.loadingDataFailed();
        } else {
            listener.loaded(data);
        }
        Log.i(TAG, "done");
    }

    @Override
    protected void onCancelled(String[][] strings) {
        pool.shutdownNow();
        listener = null;
        super.onCancelled(strings);
        Log.i(TAG, "canceled");
        Log.i(TAG, pool.isShutdown() + " isShutDown");
        Log.i(TAG, pool.isTerminated() + " isTerminated");
    }

    private class SingleRequest implements Runnable {
        private String[][] data;
        private int index;
        private Api api;

        SingleRequest(final String[][] data, final int index, Api api) {
            this.data = data;
            this.index = index;
            this.api = api;
        }

        @Override
        public void run() {
            Call<PatternModel> call = api.getPattern();
            try {
                PatternItemModel pattern = call.execute().body().getPattern();
                data[index][TITLE] = pattern.getTitle();
                data[index][URL] = pattern.getImageUrl();
            } catch (Exception e) {
                e.printStackTrace();
                Log.i(TAG, "data " + index + " failed");
            }
        }
    }
}
