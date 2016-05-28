package com.tradex.trader;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText email       = (EditText) findViewById(R.id.etEmail);
        final EditText firstName   = (EditText) findViewById(R.id.firstName);
        final EditText lastName    = (EditText) findViewById(R.id.lastName);
        final EditText password    = (EditText) findViewById(R.id.etPassword);
        final EditText dateOfBirth = (EditText) findViewById(R.id.dateOfBirth);
        final EditText country     = (EditText) findViewById(R.id.country);
        final EditText city        = (EditText) findViewById(R.id.city);
        final EditText phoneNo     = (EditText) findViewById(R.id.phoneNo);
        final EditText skypeNo     = (EditText) findViewById(R.id.skypeNo);

        final Button button_signup = (Button) findViewById(R.id.button_signup);
        button_signup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final String getEmail     = email.getText().toString();
                final String getFirstName = firstName.getText().toString();
                final String getLastName  = lastName.getText().toString();
                final String getPassword  = password.getText().toString();
                final int getDOB          = Integer.parseInt(dateOfBirth.getText().toString());
                final String getCountry   = country.getText().toString();
                final String getCity      = city.getText().toString();
                final int getPhoneNo      = Integer.parseInt(phoneNo.getText().toString());
                final int getSkypeNo      = Integer.parseInt(skypeNo.getText().toString());

                Response.Listener<String> responseListener = new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        try
                        {
                            //Convert listener to JSON object, so that the PHP can read it
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success)
                            {
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(intent);
                            }
                            else
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Registration Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        }
                        catch (JSONException jse) { jse.printStackTrace(); }
                    }
                };
                RegisterRequest registerRequest = new RegisterRequest(getEmail, getFirstName, getLastName, getPassword, getDOB, getCountry, getCity, getPhoneNo, getSkypeNo, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });
    }
}
