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
import com.tradr.tradex.MainActivity;
import com.tradr.tradex.R;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity
{
    EditText letEmail, letPassword;
    Button bLogin, registerLink;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        letEmail     = (EditText) findViewById(R.id.letEmail);
        letPassword  = (EditText) findViewById(R.id.letPassword);
        bLogin       = (Button) findViewById(R.id.bLogin);
        registerLink = (Button) findViewById(R.id.bRegisterHere);

        registerLink.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent goToRegister = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(goToRegister);
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String email = letEmail.getText().toString();
                String password = letPassword.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        try
                        {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success)
                            {
                                String email = jsonResponse.getString("email");
                                String firstName = jsonResponse.getString("firstName");
                                String lastName = jsonResponse.getString("lastName");

                                Intent goToMain = new Intent(LoginActivity.this, MainActivity.class);
                                goToMain.putExtra("email", email);
                                goToMain.putExtra("firstName", firstName);
                                goToMain.putExtra("lastName", lastName);
                                LoginActivity.this.startActivity(goToMain);
                            }
                            else
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("Login Failed").setNegativeButton("Retry", null).create().show();
                            }
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(email, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });
    }
}
