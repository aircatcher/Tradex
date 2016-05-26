package com.tradex.trader;

/**
 * Created by Ferick on 5/27/2016.
 */
public class RegisterRequest extends StringRequest
{
    private static final Stirng REGISTER_REQUEST_URL = "Register.php";
    private Map <String, String> params;

    public RegisterRequest(String email, String firstName, String lastName, String password, String dob, String country, String city, String phoneNo, String skypeNo, Response.Listener<String> listener)
    {
        super{Method.POST, REGISTER_REQUEST_URL, listener, null};
        params = new HashMap<>();
        params.put("email", email);
        params.put("firstName", firstName);
        params.put("lastName", lastName);
        params.put("password", password);
        params.put("dateOfBirth", dob + ""); //Converts dob into string
        params.put("country", country);
        params.put("city", city);
        params.put("phoneNo", phoneNo);
        params.put("skypeNo", skypeNo);
    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}
