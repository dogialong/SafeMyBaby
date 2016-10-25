package com.safemybaby.vtree.safemybaby;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

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
import utils.Contants;


/**
 * Created by NamLong on 10/10/2016.
 */

public class QuestionCateScreen extends Activity {
    @BindView(R.id.recycler_view_cate_question)
    RecyclerView recyclerView;
    List<Question> listCate ;
    QuestionAdapter questionAdapter;
    private String TAG = "QuestionCateScreen";
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
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
            }
        });
        listCate = new ArrayList<>();
        getAllCate(Contants.URL_QUESTIONITEM);
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
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
    private static void unbindDrawable(Drawable d){
        if(d!=null){
            d.setCallback(null);
        }
    }
//    @Override
//    public void onDetachedFromWindow() {
//        if (AnyApplication.DEBUG)
//            Log.d(TAG, "onDetachedFromWindow");
//        super.onDetachedFromWindow();
//        AnyApplication.getConfig().removeChangedListener(this);
//
//        //cleaning up memory
//        unbindDrawable(mPreviewPopup.getBackground());
//        unbindDrawable();
//        //...
//    }
//    public final void setCallback(Drawable.Callback cb) {
//        mCallback = new WeakReference<Drawable.Callback>(cb);
//    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (dialog != null)
//            dialog.dismiss();
//        if (progressDialog != null)
//            progressDialog.dismiss();
//        mNavigationManager.addTabChangeListener(this);
//        mNavigationManager.terminate();
//        doUnbindUploadService();
//        mNetworkListener.unRegister();
    }
}
