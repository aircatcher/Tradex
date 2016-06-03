package com.tradr.tradex;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tradr.tradex.Fragments.AppFeedback;
import com.tradr.tradex.Fragments.Friend.Friends;
import com.tradr.tradex.Fragments.About;
import com.tradr.tradex.Fragments.Inbox;
import com.tradr.tradex.Fragments.Settings;
import com.tradr.tradex.Fragments.Trade;
import com.tradr.tradex.Request.LoginActivity;

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
                Intent inboxScreen = new Intent(MainActivity.this, Inbox.class);
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
    private boolean doubleBackToExitPressedOnce;
    private Handler mHandler = new Handler();
    private final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            doubleBackToExitPressedOnce = false;
        }
    };

    @Override
    protected void onDestroy()
    {
        System.exit(0);
        if (mHandler != null) { mHandler.removeCallbacks(mRunnable); }
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please back again to exit", Toast.LENGTH_SHORT).show();
        mHandler.postDelayed(mRunnable, 2000);
    }

    /*@SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getFragmentManager();

        if (id == R.id.nav_trade) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new TradeFragment()).commit();
        } else if (id == R.id.nav_inbox) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new InboxFragment()).commit();
        } else if (id == R.id.nav_friends) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new FriendsFragment()).commit();
        } else if (id == R.id.nav_help) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new HelpFragment()).commit();
        } else if (id == R.id.nav_feedback) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new FeedbackFragment()).commit();
        } else if (id == R.id.nav_settings) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new SettingsFragment()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }*/
}
