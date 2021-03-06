package com.example.gurusenthil.farmerhelper.JavaCode.Other;

import android.content.Context;
import android.content.Intent;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by BSathvik on 17/04/16.
 */
public class JSONArrayRequest extends Request<JSONArray> {

    private Response.Listener<JSONArray> listener = null;
    private Map<String, String> params = null;
    private Context context = null;
    private Priority priority = Priority.NORMAL;

    public JSONArrayRequest(Context context , int method, String url, Map<String, String> params,
                            Response.Listener<JSONArray> reponseListener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.listener = reponseListener;
        this.params = params;
        this.context = context;
    }

    protected Map<String, String> getParams()
            throws com.android.volley.AuthFailureError {
        return params;
    }

    @Override
    protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
        try {

            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));

            return Response.success(new JSONArray(jsonString), HttpHeaderParser.parseCacheHeaders(response));

        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }



    @Override
    protected void deliverResponse(JSONArray response) {
            listener.onResponse(response);
    }

    @Override
    public Priority getPriority() {
        return priority;
    }
}
