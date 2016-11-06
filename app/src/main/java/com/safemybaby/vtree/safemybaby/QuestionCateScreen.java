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
import com.android.volley.toolbox.JsonArrayRequest;
import com.safemybaby.vtree.safemybaby.adapter.ItemClickListener;
import com.safemybaby.vtree.safemybaby.adapter.QuestionAdapter;
import com.safemybaby.vtree.safemybaby.app.AppController;
import com.safemybaby.vtree.safemybaby.model.Question;

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
 * Created by NamLong on 10/10/2016.
 */

public class QuestionCateScreen extends Activity {
    @BindView(R.id.recycler_view_cate_question)
    RecyclerView recyclerView;
    List<Question> listCate ;
    QuestionAdapter questionAdapter;
    static QuestionCateScreen questionCateScreen  ;
    private String TAG = "QuestionCateScreen";
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    Unbinder unbinder;
    boolean doubleBackToExitPressedOnce = false;
    @OnClick(R.id.btnBackCateQuesntionScreen)
    public void backToPreScreen(){
        Intent  i = new Intent(QuestionCateScreen.this,SelectModePlayScreen.class);
        startActivity(i);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cate_question_screen);
        ButterKnife.bind(this);
        questionAdapter = new QuestionAdapter(this, new ItemClickListener() {
            @Override
            public void clickItemListtener(View view, int possition) {
//                Toast.makeText(getApplicationContext(), possition+"", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(QuestionCateScreen.this, QuestionScreen.class);
                startActivity(i);
                finish();
            }
        });
        listCate = new ArrayList<>();
        getAllCate(Contants.URL_QUESTIONITEM);
        overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
    }
    @Override
    public void onResume(){
        super.onResume();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
    }
    public List<Question> getAllCate(String url) {
        JsonArrayRequest req = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.i(TAG, "onResponse: " + response.length());

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                listCate.add(new Question(response.getJSONObject(i).getString("img")));
                                questionAdapter.updateList(listCate);
                                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                recyclerView.setAdapter(questionAdapter);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
    }
    public static QuestionCateScreen getInstance(){
        return   questionCateScreen;
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
