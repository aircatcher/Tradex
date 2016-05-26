package com.tradex.trader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;

public class RegisterActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText email       = (EditText) findViewById(R.id.email);
        final EditText firstName   = (EditText) findViewById(R.id.firstName);
        final EditText lastName    = (EditText) findViewById(R.lastName);
        final EditText password    = (EditText) findViewById(R.id.password);
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

                };
                RegisterRequest registerRequest = new RegisterRequest(getEmail, getFirstName, getLastName, getPassword, getDOB, getCountry, getCity, getPhoneNo, getSkypeNo);
            }
        });
    }
}
