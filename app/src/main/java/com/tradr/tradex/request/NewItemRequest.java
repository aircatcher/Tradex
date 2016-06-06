package com.tradr.tradex.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class NewItemRequest extends StringRequest
{
    private static final String REQUEST_URL = "http://tradex.esy.es/include/php/add_new_item.php";
    private Map<String, String> params;

    public NewItemRequest(String itemCat, String itemName, String itemDesc, Response.Listener<String> listener)
    {
        super(Method.POST, REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("itemCat", itemCat);
        params.put("itemName", itemName);
        params.put("itemDesc", itemDesc);
    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}
