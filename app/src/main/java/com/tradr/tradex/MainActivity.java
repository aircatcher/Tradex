package com.tradr.tradex;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tradr.tradex.friend.Friends;
import com.tradr.tradex.mail.Mail;
import com.tradr.tradex.trade.Trade;

public class MainActivity extends AppCompatActivity
{
    Button bTrade, bInbox, bFriends, bHelp, bFeedback, bSettings, bLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tvFullName       = (TextView) findViewById(R.id.tvFullName);
        final TextView tvEmail          = (TextView) findViewById(R.id.tvEmail);
        bTrade    = (Button) findViewById(R.id.bTrade);
        bInbox    = (Button) findViewById(R.id.bInbox);
        bFriends  = (Button) findViewById(R.id.bFriends);
        bHelp     = (Button) findViewById(R.id.bHelp);
        bFeedback = (Button) findViewById(R.id.bFeedback);
        bSettings = (Button) findViewById(R.id.bSettings);
        bLogout   = (Button) findViewById(R.id.bLogout);

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        String firstName = intent.getStringExtra("firstName");
        String lastName = intent.getStringExtra("lastName");
        String fullName = firstName + " " + lastName;

        tvFullName.setText(fullName);
        tvEmail.setText(email);

        bTrade.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent tradeScreen = new Intent(MainActivity.this, Trade.class);
                MainActivity.this.startActivity(tradeScreen);
            }
        });

        bInbox.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent inboxScreen = new Intent(MainActivity.this, Mail.class);
                MainActivity.this.startActivity(inboxScreen);
            }
        });

        bFriends.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent friendsScreen = new Intent(MainActivity.this, Friends.class);
                MainActivity.this.startActivity(friendsScreen);
            }
        });

        bHelp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent helpScreen = new Intent(MainActivity.this, About.class);
                MainActivity.this.startActivity(helpScreen);
            }
        });

        bFeedback.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent feedbackScreen = new Intent(MainActivity.this, AppFeedback.class);
                MainActivity.this.startActivity(feedbackScreen);
            }
        });

        bSettings.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent settingsScreen = new Intent(MainActivity.this, Settings.class);
                MainActivity.this.startActivity(settingsScreen);
            }
        });

        bLogout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent logout = new Intent(MainActivity.this, LoginActivity.class);

                MainActivity.this.startActivity(logout);
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
            case R.id.action_bar_about:
                startActivity(new Intent(this, About.class));
                return true;

            case R.id.action_bar_settings:
                startActivity(new Intent(this, Settings.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Double back button to exit
    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}
