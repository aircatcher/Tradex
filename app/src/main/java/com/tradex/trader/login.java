package com.tradex.trader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class login extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText email       = (EditText) findViewById(R.id.email);
        final EditText password    = (EditText) findViewById(R.id.password);

        final Button button_login = (Button) findViewById(R.id.button_login);
        final Button linkToSignup  = (Button) findViewById(R.id.link_signup);

        linkToSignup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClickView(View v)
            {
                Intent signupIntent = new Intent(login.this, RegisterActivity.class);
                login.this.startActivity(signupIntent);
            }
        });
    }
}
