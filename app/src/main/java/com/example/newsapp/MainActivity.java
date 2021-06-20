package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RequestQueue requestQueue;
    ArrayList<News> newsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        requestQueue = MySingleton.getInstance(this).getRequestQueue();
        newsArrayList = new ArrayList<>();
        fetchData();
    }

    private void fetchData(){
//        String url = "https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=f9399b976b2446019374c0f7290fd25b";
        String access_key = "70fb78710e28d062bb8c467ad3cdc361";
        String url = "https://gnews.io/api/v4/search?q=covid19&token=df7187b9b1866f39fd5a49cdaeb3b1f6&lang=en";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("articles");

                    for (int i = 0; i<jsonArray.length(); i++){
                        JSONObject articles = jsonArray.getJSONObject(i);
                        String title = articles.getString("title");
                        String author = articles.getString("content");
                        String desc = articles.getString("description");
                        String poster = articles.getString("image");

                        News news = new News(title, author, desc, poster);
                        newsArrayList.add(news);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                NewsAdapter adapter = new NewsAdapter(MainActivity.this, newsArrayList);
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(jsonObjectRequest);
    }
}