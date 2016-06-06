package com.tradr.tradex.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest
{
    private static final String REQUEST_URL = "http://tradex.esy.es/include/php/register.php";
    private Map<String, String> params;

    public RegisterRequest(String firstName, String lastName, String email, String password, int dateOfBirth, String country, String city, String phoneNo, String skype, Response.Listener<String> listener)
    {
        super(Method.POST, REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("firstName", firstName);
        params.put("lastName", lastName);
        params.put("email", email);
        params.put("password", password);
        params.put("dateOfBirth", dateOfBirth + "");
        params.put("country", country);
        params.put("city", city);
        params.put("phoneNo", phoneNo);
        params.put("skype", skype);
    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}
