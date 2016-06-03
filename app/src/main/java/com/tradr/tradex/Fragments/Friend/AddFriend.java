package com.tradr.tradex.Fragments.Friend;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.tradr.tradex.Fragments.About;
import com.tradr.tradex.Fragments.Settings;
import com.tradr.tradex.R;

public class AddFriend extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
    }

    // Show action bar menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_actionbar_friends, menu);
        return true;
    }
    // Determines if Actin Bar item was selected, then do corresponding action
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.home:
                NavUtils.navigateUpFromSameTask(this);
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
