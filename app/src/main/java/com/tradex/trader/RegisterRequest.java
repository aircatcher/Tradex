package com.tradex.trader;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ferick on 5/27/2016.
 */
public class RegisterRequest extends StringRequest
{
    private static final String REGISTER_REQUEST_URL = "http://killdistance.esy.es/tradex/register.php";
    private Map<String, String> params;

    public RegisterRequest(String email, String firstName, String lastName, String password, int dob, String country, String city, int phoneNo, int skypeNo, Response.Listener<String> listener)
    {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("email", email);
        params.put("firstName", firstName);
        params.put("lastName", lastName);
        params.put("password", password);
        params.put("dateOfBirth", dob + ""); //Converts dob into string
        params.put("country", country);
        params.put("city", city);
        params.put("phoneNo", phoneNo + ""); //Converts phoneNo into string
        params.put("skypeNo", skypeNo + ""); //Converts skypeNo into string
    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}
