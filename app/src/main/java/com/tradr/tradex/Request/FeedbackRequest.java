package com.tradr.tradex.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class FeedbackRequest extends StringRequest
{
    private static final String URL_REQUEST = "http://tradex.esy.es/include/php/app_feedback.php";
    private Map<String, String> params;

    public FeedbackRequest(String feedbackText, Response.Listener<String> listener)
    {
        super(Method.POST, URL_REQUEST, listener, null);
        params = new HashMap<>();
        params.put("feedbackText", feedbackText);
    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}
