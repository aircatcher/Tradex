package com.tradr.tradex.MenuActivities;

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
import com.tradr.tradex.Request.FeedbackRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class FeedbackActivity extends AppCompatActivity
{
    EditText etFeedbackText;
    Button bSubmitFeedback, bGoBack;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_feedback);

        etFeedbackText    = (EditText) findViewById(R.id.etFeedbackText);
        bSubmitFeedback = (Button) findViewById(R.id.bSubmitFeedback);
        bGoBack         = (Button) findViewById(R.id.bGoBack);

        bSubmitFeedback.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String feedbackText = etFeedbackText.getText().toString();

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

                            if(success)
                            {
                                String feedbackText = jsonResponse.getString("feedbackText");
                                AlertDialog.Builder builder = new AlertDialog.Builder(FeedbackActivity.this);
                                builder.setMessage("Feedback sent!").setPositiveButton("OK", null).create().show();

                                Intent goToMain = new Intent(FeedbackActivity.this, MainActivity.class);
                                goToMain.putExtra("feedbackText", feedbackText);
                                FeedbackActivity.this.startActivity(goToMain);
                            }
                            else
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(FeedbackActivity.this);
                                builder.setMessage("Fails to send the feedback, please try again").setNegativeButton("Retry", null).create().show();
                            }
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                };
                FeedbackRequest feedbackRequest = new FeedbackRequest(feedbackText, responseListener);
                RequestQueue queue = Volley.newRequestQueue(FeedbackActivity.this);
                queue.add(feedbackRequest);
            }
        });

        bGoBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent goToMain = new Intent(FeedbackActivity.this, MainActivity.class);
                FeedbackActivity.this.startActivity(goToMain);
            }
        });
    }
}
