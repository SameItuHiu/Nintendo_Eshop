package com.example.nintendo_eshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity{

    BottomSheetDialog donate,viewGame;

    RecyclerView recyclerView;
    ListViewAdapter adapter;
    List<GameItem> gameItem;
    String URL_Data="https://api.esho.pw/games";
    RequestQueue reqQue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.main_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        gameItem = new ArrayList<>();

        loadurl();

    }

    public void loadurl() {
        JsonArrayRequest stringRequest = new JsonArrayRequest(URL_Data, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                getvalue(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        reqQue = Volley.newRequestQueue(this);
        reqQue.add(stringRequest);
    }

    public void getvalue(JSONArray array) {

        for (int i = 0; i < array.length(); i++) {

            GameItem gameList = new GameItem();

            JSONObject json;
            try {
                json = array.getJSONObject(i);

                gameList.setTitle(json.getString("Title"));
                gameList.setCategories(json.getString("Categories"));
                gameList.setImage(json.getString("Image"));

            } catch (JSONException e) {

                e.printStackTrace();
            }
            gameItem.add(gameList);
        }
        adapter = new ListViewAdapter(this, gameItem);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickCallback(new ListViewAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(GameItem data) {
                View view = getLayoutInflater().inflate(R.layout.bot_nav_view_game, null);
                viewGame = new BottomSheetDialog(MainActivity.this);
                viewGame.setContentView(view);
                viewGame.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.Currency:
                item.collapseActionView();
            case R.id.Donate:
                View view = getLayoutInflater().inflate(R.layout.bot_nav_donate, null);
                donate = new BottomSheetDialog(this);
                donate.setContentView(view);
                donate.show();
        }
        return super.onOptionsItemSelected(item);
    }
}
