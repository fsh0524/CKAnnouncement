package org.entresoft.ckannouncement;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.util.ArrayList;

public class FragmentAnn extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return initView(inflater, container);
    }

    private View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_ann, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        /**
         * Making Request.
         */
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        final ListView mListView = (ListView) getActivity().findViewById(R.id.annListView);
        final ArrayList<String> annList = new ArrayList<String>();
        final ArrayAdapter<String> listAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, annList);
        // Refresh
        mListView.setAdapter(listAdapter);

        final ProgressDialog mDialog = new ProgressDialog(getActivity()) ;
        mDialog.setIndeterminateDrawable(getActivity().getResources().getDrawable(R.drawable.suika_loading));
        mDialog.setMessage("少女祈禱中...");
        mDialog.show();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("http://twcl.ck.tp.edu.tw/api/announce", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        mDialog.dismiss();
                        try {
                            JSONArray jArray = response.getJSONArray("anns");
                            for (int i = 0 ; i < jArray.length() ; i++) {
                                annList.add(jArray.getJSONObject(i).getString("title"));
                                Log.d("TAG", "jizzzzzzzzzz" + i);
                                mListView.setAdapter(listAdapter);
                                // Pulling items from the array
                            }

                        } catch (JSONException e) {
                            // Oops
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mDialog.dismiss();
                annList.add("Request ERROR");
            }
        });

        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);


    }
}
