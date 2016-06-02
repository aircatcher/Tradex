package com.tradr.tradex;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tradr.tradex.MenuActivities.FeedbackActivity;
import com.tradr.tradex.MenuActivities.FriendsActivity;
import com.tradr.tradex.MenuActivities.HelpActivity;
import com.tradr.tradex.MenuActivities.InboxActivity;
import com.tradr.tradex.MenuActivities.SettingsActivity;
import com.tradr.tradex.MenuActivities.TradeActivity;
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
                Intent tradeScreen = new Intent(MainActivity.this, TradeActivity.class);
                MainActivity.this.startActivity(tradeScreen);
            }
        });

        bInbox.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent inboxScreen = new Intent(MainActivity.this, InboxActivity.class);
                MainActivity.this.startActivity(inboxScreen);
            }
        });

        bFriends.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent friendsScreen = new Intent(MainActivity.this, FriendsActivity.class);
                MainActivity.this.startActivity(friendsScreen);
            }
        });

        bHelp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent helpScreen = new Intent(MainActivity.this, HelpActivity.class);
                MainActivity.this.startActivity(helpScreen);
            }
        });

        bFeedback.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent feedbackScreen = new Intent(MainActivity.this, FeedbackActivity.class);
                MainActivity.this.startActivity(feedbackScreen);
            }
        });

        bSettings.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent settingsScreen = new Intent(MainActivity.this, SettingsActivity.class);
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
