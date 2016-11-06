package com.safemybaby.vtree.safemybaby;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.safemybaby.vtree.safemybaby.adapter.CateAdapter;
import com.safemybaby.vtree.safemybaby.adapter.ItemClickListener;
import com.safemybaby.vtree.safemybaby.app.AppController;
import com.safemybaby.vtree.safemybaby.model.Category;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import utils.Contants;

/**
 * Created by NamLong on 10/7/2016.
 */

public class HandbookCateScreen extends Activity {

    @BindView(R.id.recycler_view_cate_handbook)
    RecyclerView recyclerView;
    List<Category> listCate ;
    CateAdapter cateAdapter;
    private String TAG = "HandbookCateScreen";
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    static HandbookCateScreen handbookCateScreen;
    boolean doubleBackToExitPressedOnce = false;
    Unbinder unbinder;
    @OnClick(R.id.btnBackHandbookCateScreen)
    public void backToPreScreen(){
        Intent  i = new Intent(HandbookCateScreen.this,MainActivity.class);
        startActivity(i);
        AppController.getInstance().cancelAllCustom( AppController.getInstance().getRequestQueue());
        finish();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handbook_cate_screen);
        ButterKnife.bind(this);
        cateAdapter = new CateAdapter(this, new ItemClickListener() {
            @Override
            public void clickItemListtener(View view, int possition) {
                Toast.makeText(getApplicationContext(), possition+"", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(HandbookCateScreen.this, DetailHandbookScreen.class);
                startActivity(i);
                finish();
            }
        });
        listCate = new ArrayList<>();
        //getAllCate(Contants.URL_CATEGORY);
        showData();
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
        //cateAdapter.updateList(lisCate);
        //new JsonTask().execute(Contants.URL_CATEGORY);

    }

    @Override
    public void onResume(){
        super.onResume();
        this. overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    public List<Category> getAllCate(String url) {
        JsonArrayRequest req = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.i(TAG, "onResponse: " + response.length());

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                listCate.add(new Category(response.getJSONObject(i).getString("name"), response.getJSONObject(i).getString("img")));
                                cateAdapter.updateList(listCate);
                                recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
                                recyclerView.setAdapter(cateAdapter);
                                recyclerView.setItemAnimator(new DefaultItemAnimator());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "onErrorResponse: " + error.toString());
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req,
                tag_json_obj);
        // Cancelling request
        AppController.getInstance().getRequestQueue().cancelAll(tag_json_arry);
        Log.d(TAG, "getAllCate: " + listCate.size());
        return listCate;
    }

    public boolean loadData() {
        listCate = getAllCate(Contants.URL_CATEGORY);
        return listCate != null;
    }

    /**
     * Show Data
     */
    public void showData() {
        try {
//            showProgress();
            new AsyncTask<Void, Void, Boolean>() {
                @Override
                protected Boolean doInBackground(Void... params) {
                    // Load data from Model
                    return loadData();
                }

                @Override
                protected void onPostExecute(Boolean result) {
                    try {
//                        hideProgress();
                        if (!result) {
                            Log.i(TAG, "onPostExecute: " + result.toString());
                            notifyDataSetChanged();
                        } // Loading error

                        else // success
                            Log.i(TAG, "onPostExecute: " + result.toString());
                        notifyDataSetChanged();

                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }
            }.execute();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

//    public void showProgress() {
//        progressBar.setVisibility(View.VISIBLE);
//    }

//    public void hideProgress() {
//        progressBar.setVisibility(View.INVISIBLE);
//    }

    public void notifyDataSetChanged() {
        cateAdapter.notifyDataSetChanged();
    }
    public static HandbookCateScreen getInstance(){
        return   handbookCateScreen;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
    }
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
