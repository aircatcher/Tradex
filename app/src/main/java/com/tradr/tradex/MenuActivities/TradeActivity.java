package com.tradr.tradex.MenuActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.tradr.tradex.R;

public class TradeActivity extends AppCompatActivity
{
    ListView tradeListView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_trade);

        //tradeListView = (ListView) findViewById(R.id.feed);
    }
}
