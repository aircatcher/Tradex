package com.tradr.tradex;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.tradr.tradex.ListView.Adapter;
import com.tradr.tradex.ListView.AppController;
import com.tradr.tradex.ListView.Item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Trade extends AppCompatActivity {
    private static final String url = "http://api.androidhive.info/json/movies.json";
    private ProgressDialog dialog;
    private List<Item> array = new ArrayList<Item>();
    private ListView listView;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.tradeListView);
        adapter = new Adapter(this,array);
        listView.setAdapter(adapter);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();

        //Creat volley request obj
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                hideDialog();
                //parsing json
                for(int i=0;i<response.length();i++)
                {
                    try
                    {
                        JSONObject obj = response.getJSONObject(i);
                        Item item = new Item();
                        item.setTitle(obj.getString("itemName"));
                        item.setImage(obj.getString("itemDesc"));

                        //genre is json array
                        JSONArray jsonResponse = obj.getJSONArray("itemCat");
                        ArrayList<String> itemCat = new ArrayList<String>();
                        for(int j = 0;j<jsonResponse.length();j++)
                        {
                            itemCat.add((String) jsonResponse.get(j));
                        }
                        item.setGenre(itemCat);

                        //add to array
                        array.add(item);
                    }catch(JSONException ex)
                    {
                        ex.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error){

            }
        });
        AppController.getmInstance().addToRequesQueue(jsonArrayRequest);
    }
    public void hideDialog()
    {
        if(dialog != null)
        {
            dialog.dismiss();
            dialog = null;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
