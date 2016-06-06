package com.tradr.tradex.trade;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Response;
import com.tradr.tradex.About;
import com.tradr.tradex.R;
import com.tradr.tradex.Settings;

public class Trade extends AppCompatActivity
{
    ListView tradeListView;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade);

        // ArrayAdapter for the spinner value
        //String[] listView = getResources().getStringArray(0);
        //List<String> arrayList = Arrays.asList(listView);
        //adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, arrayList);
        tradeListView = (ListView) findViewById(R.id.tradeListView);

        //tradeListView.setAdapter(adapter);

        //TextView itemName = (TextView) convertView.findViewById(R.id.etSearchItemName);
        //TextView itemDesc = (TextView) convertView.findViewById(R.id.etNewItemDescription);

        Response.Listener<String> responseListener = new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                tradeListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                    {
                        //Opens the item on the trade lists
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {}
                });
            }
        };
        //TradeRequest tradeRequest = new TradeRequest(itemCat, itemName, itemDesc, responseListener);
        //RequestQueue queue = Volley.newRequestQueue(Trade.this);
        //queue.add(tradeRequest);
    }

    // Show action bar menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_actionbar_trade, menu);
        return true;
    }
    // Determines if Actin Bar item was selected, then do corresponding action
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.home:
                finishActivity(1);
                return true;

            case R.id.action_bar_add_trade:
                startActivity(new Intent(this, NewItem.class));
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
