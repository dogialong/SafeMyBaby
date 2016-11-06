package com.safemybaby.vtree.safemybaby;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.safemybaby.vtree.safemybaby.adapter.DetailHandbookAdapter;
import com.safemybaby.vtree.safemybaby.adapter.ItemClickListener;
import com.safemybaby.vtree.safemybaby.app.AppController;
import com.safemybaby.vtree.safemybaby.model.Category_Item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import utils.Contants;

public class DetailHandbookScreen extends Activity {
     private String TAG = "DetailHandbookScreen";
    @BindView(R.id.recycler_view_item_handbook)
    RecyclerView recyclerView;
    List<Category_Item.DataBean> listCateItem;
    DetailHandbookAdapter detailAdapter;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    static DetailHandbookScreen detailHandbookScreen;
    Unbinder unbinder;

    boolean doubleBackToExitPressedOnce = false;
    @OnClick(R.id.btnBackHanbookItem)
    public void backToPreScreen(){
        Intent  i = new Intent(DetailHandbookScreen.this,HandbookCateScreen.class);
        startActivity(i);
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handbook_item_screen);
        ButterKnife.bind(this);
        listCateItem = new ArrayList<>();
        detailAdapter = new DetailHandbookAdapter(this, new ItemClickListener() {
            @Override
            public void clickItemListtener(View view, int possition) {
                Intent myIntent = new Intent(DetailHandbookScreen.this,ContentHandbookScreen.class);
                myIntent.putExtra("linkimg",(Serializable) listCateItem.get(possition).getImg_cover());
                myIntent.putExtra("content",(Serializable)listCateItem.get(possition).getContent());
                myIntent.putExtra("name",(Serializable)listCateItem.get(possition).getName());
                startActivity(myIntent);
                finish();
            }
        }) ;
        getAllCate(Contants.URL_HANDBOOKITEM);
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }
    @Override
    public void onResume(){
        super.onResume();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
    }

    public List<Category_Item.DataBean> getAllCate(String url) {
        JsonObjectRequest req = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        //get data
                        JSONObject obj = response;
                        JSONArray array = null;
                        try {
                            array = obj.getJSONArray("data");
                            for(int i = 0 ; i < array.length() ; i++){
                                listCateItem.add(new Category_Item.DataBean(array.getJSONObject(i).getString("icon"),
                                                    array.getJSONObject(i).getString("img"),array.getJSONObject(i).getString("content"),
                                                    array.getJSONObject(i).getString("name")));
//                                Toast.makeText(getApplicationContext(),array.getJSONObject(i).getString("icon"), Toast.LENGTH_SHORT).show();
                                detailAdapter.updateList(listCateItem);
                                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                recyclerView.setAdapter(detailAdapter);
                             ///   recyclerView.setItemAnimator(new DefaultItemAnimator());
                            }
                             Toast.makeText(getApplicationContext(), listCateItem.size()+"", Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());

            }
        });


        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req,
                tag_json_obj);
        // Cancelling request
        AppController.getInstance().getRequestQueue().cancelAll(tag_json_arry);
        Log.d(TAG, "getAllCate: " + listCateItem.size());
        return listCateItem;
    }
    private void loadData(){

    }

    /**
     * DOn rac
     */
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        System.gc();
    }


    public static DetailHandbookScreen getInstance(){
        return   detailHandbookScreen;
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

