package com.tradr.tradex.Request;

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
import com.tradr.tradex.R;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity
{
    EditText etFirstName, etLastName, etEmail, etPassword, etDOB, etCountry, etCity, etPhoneNo, etSkype;
    Button bRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName  = (EditText) findViewById(R.id.etLastName);
        etEmail     = (EditText) findViewById(R.id.etEmail);
        etPassword  = (EditText) findViewById(R.id.etPassword);
        etDOB       = (EditText) findViewById(R.id.etDOB);
        etCountry   = (EditText) findViewById(R.id.etCountry);
        etCity      = (EditText) findViewById(R.id.etCity);
        etPhoneNo   = (EditText) findViewById(R.id.etPhoneNo);
        etSkype     = (EditText) findViewById(R.id.etSkype);
        bRegister   = (Button) findViewById(R.id.bRegister);

        bRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String firstName = etFirstName.getText().toString();
                String lastName  = etLastName.getText().toString();
                String email     = etEmail.getText().toString();
                String password  = etPassword.getText().toString();
                int dateOfBirth  = Integer.parseInt(etDOB.getText().toString());
                String country   = etCountry.getText().toString();
                String city      = etCity.getText().toString();
                String phoneNo   = etPhoneNo.getText().toString();
                String skype     = etSkype.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        try
                        {
                            JSONObject jsonResponse = new JSONObject(response);
                            jsonResponse.toString();
                            boolean success = jsonResponse.getBoolean("success");

                            if (success)
                            {
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(intent);
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Login Successful").setPositiveButton("OK", null).create().show();
                            }
                            else
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Register Failed").setNegativeButton("Retry", null).create().show();
                            }
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                };
                RegisterRequest registerRequest = new RegisterRequest(firstName, lastName, email, password, dateOfBirth, country, city, phoneNo, skype, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });
    }
}
