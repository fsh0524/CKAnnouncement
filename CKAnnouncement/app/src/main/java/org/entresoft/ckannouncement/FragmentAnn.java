package org.entresoft.ckannouncement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

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
         * Making String Request.
         */
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        final TextView mTextView = (TextView) getActivity().findViewById(R.id.bkMsg);

        StringRequest stringRequest = new StringRequest(Request.Method.GET,"http://www.google.com",new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                mTextView.setText("Response is: "+ response.substring(0,300));
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError arg0) {
                mTextView.setText("Error Response");
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }
}
