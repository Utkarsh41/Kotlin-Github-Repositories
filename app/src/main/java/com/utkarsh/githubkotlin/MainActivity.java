package com.utkarsh.githubkotlin;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.utkarsh.githubkotlin.AboutMe.AboutMe;
import com.utkarsh.githubkotlin.Services.ApiClient;
import com.utkarsh.githubkotlin.Services.ApiInterface;

import java.io.File;
import java.io.IOException;
import java.util.List;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LanguageItems items;
    private ProgressDialog pd;
    static Context context;

    int cacheSize = 100 * 1024 * 1024; // 10 mb cache

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initViews();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ut, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.action_AboutUs){
            Intent i = new Intent(MainActivity.this, AboutMe.class);
            startActivity(i);
            finish();
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initViews() {
        pd=new ProgressDialog(this);
        pd.setMessage("Fetching Data");
        pd.setCancelable(false);
        pd.show();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerMain);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        loadJSON();
    }
//    Internet connection is not available
    private boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo !=null && activeNetworkInfo.isConnected();
    }

//    New

    private void loadJSON(){
        try {

/*
//            my way
             File httpCacheDirectory = new File(getCacheDir(), "http-cache");
//          Caching implementation
            Cache cache = new Cache(httpCacheDirectory, cacheSize);

            OkHttpClient okHttpClient = new OkHttpClient.Builder().cache(cache)
                    .addInterceptor(new Interceptor() {
                        @Override
                        public okhttp3.Response intercept(Chain chain) throws IOException {
                            Request request = chain.request();
                            if (!isNetworkAvailable()) {
                                int maxStale = 15; // 15 minutes timeline
                                request = request.newBuilder().header("Cache-Control",
                                                "public, only-if-cached, max-stale=" + maxStale)
                                        .build();
                            }
                            return chain.proceed(request);
                        }
                    }).build();

            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create());

            Retrofit retrofit = builder.build();
            ApiInterface apiService = retrofit.create(ApiInterface.class); */

//            *** Simple When no caching required ***
           ApiClient Client = new ApiClient();
            ApiInterface apiService =
                    ApiClient.getClient().create(ApiInterface.class);
            Call<LanguageModel> call = apiService.getItems();
            call.enqueue(new Callback<LanguageModel>() {
                @Override
                public void onResponse(Call<LanguageModel> call, Response<LanguageModel> response) {
                    List<LanguageItems> items = response.body().getLanguageItemsList();
                    recyclerView.setAdapter(new ItemAdapter(getApplicationContext(), items));
                    recyclerView.smoothScrollToPosition(0);
                    pd.hide();
                }

                @Override
                public void onFailure(Call<LanguageModel> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(MainActivity.this, "Error Fetching Data!Please check your Internet!", Toast.LENGTH_LONG).show();
                    pd.hide();
                }

            });

        }catch (Exception e){
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }


}
