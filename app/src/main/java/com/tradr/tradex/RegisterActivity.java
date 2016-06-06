package com.tradr.tradex;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.tradr.tradex.request.RegisterRequest;

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
                            boolean success = jsonResponse.getBoolean("success");
                            jsonResponse.toString();

                            if (success)
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Registration Successful").setPositiveButton("OK", null).create().show();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(intent);
                            }
                            else
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Registration Failed").setNegativeButton("Retry", null).create().show();
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

    // Show action bar menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_actionbar_main, menu);
        return true;
    }
    // Determines if Actin Bar item was selected, then do corresponding action
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.home:
                finish();
                return true;

            case R.id.action_bar_about:
                startActivity(new Intent(this, About.class));
                return true;

            case R.id.action_bar_settings:
                startActivity(new Intent(this, Settings.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
