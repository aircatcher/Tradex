package com.tradr.tradex.request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class TradeRequest extends StringRequest
{
    private static final String TRADE_REQUEST_URL = "http://tradex.esy.es/include/php/retrieve_trade.php";
    private Map<String, String> params;

    public TradeRequest(String itemCat, String itemName, String itemDesc, Response.Listener<String> listener) {
        super(Request.Method.POST, TRADE_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("itemCat", itemCat);
        params.put("itemName", itemName);
        params.put("itemDesc", itemDesc);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
