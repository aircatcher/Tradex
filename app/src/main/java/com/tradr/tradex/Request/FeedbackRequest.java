package com.tradr.tradex.Request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class FeedbackRequest extends StringRequest
{
    private static final String FEEDBACK_REQUEST_URL = "http://killdistance.esy.es/tradex/app_feedback.php";
    private Map<String, String> params;

    public FeedbackRequest(String feedbackText, Response.Listener<String> listener)
    {
        super(Method.POST, FEEDBACK_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("feedbackText", feedbackText);
    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}
