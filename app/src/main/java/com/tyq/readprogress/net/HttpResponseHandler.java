package com.tyq.readprogress.net;



import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONObject;

/**
 * Created by tyq on 2015/9/7.
 */
public abstract class HttpResponseHandler extends JsonHttpResponseHandler {
    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        super.onSuccess(statusCode, headers, response);
        jsonSuccess(response);
    }
    public abstract void jsonSuccess(JSONObject resp);
    public abstract void jsonFail(JSONObject resp);
    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
        super.onFailure(statusCode, headers, throwable, errorResponse);
        Log.i("tyq", "statusCode:" + statusCode);
        jsonFail(errorResponse);
    }
}
