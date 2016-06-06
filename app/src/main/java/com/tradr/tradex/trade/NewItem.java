package com.tradr.tradex.trade;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.tradr.tradex.About;
import com.tradr.tradex.R;
import com.tradr.tradex.Settings;
import com.tradr.tradex.request.NewItemRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class NewItem extends AppCompatActivity
{
    EditText etSearchItemName, etNewItemDescription;
    Spinner sNewItemCategory;
    Button bSubmitItem;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        // ArrayAdapter for the spinner value
        String[] myResArray = getResources().getStringArray(R.array.item_category);
        List<String> arrayList = Arrays.asList(myResArray);
        adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, arrayList);
        etSearchItemName = (EditText) findViewById(R.id.etSearchItemName);
        sNewItemCategory = (Spinner) findViewById(R.id.sNewItemCategory);
        etNewItemDescription = (EditText) findViewById(R.id.etNewItemDescription);
        bSubmitItem = (Button) findViewById(R.id.bSubmitItem);
        sNewItemCategory.setAdapter(adapter);

        sNewItemCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            { Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " is selected", Toast.LENGTH_LONG).show(); }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        // Submit the Item
        bSubmitItem.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String itemName = etSearchItemName.getText().toString();
                String itemCat = sNewItemCategory.getSelectedItem().toString();
                String itemDesc = etNewItemDescription.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        try
                        {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success)
                            {
                                Intent intent = new Intent(NewItem.this, Trade.class);
                                AlertDialog.Builder builder = new AlertDialog.Builder(NewItem.this);
                                builder.setMessage("Item added to database").setNegativeButton("OK", null).create().show();
                                NewItem.this.startActivity(intent);
                            }
                            else
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(NewItem.this);
                                builder.setMessage("Fails to add your item, please try again").setNegativeButton("Retry", null).create().show();
                            }
                        } catch (JSONException e) { e.printStackTrace(); }
                    }
                };
                NewItemRequest newItemRequest = new NewItemRequest(itemCat, itemName, itemDesc, responseListener);
                RequestQueue queue = Volley.newRequestQueue(NewItem.this);
                queue.add(newItemRequest);
            }
        });
    }

    // Show action bar menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_actionbar_new_item, menu);
        return true;
    }
    // Determines if Action Bar item was selected, then do corresponding action
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.home:
                finishActivity(1);
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
