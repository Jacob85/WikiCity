package com.example.WikiCity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import il.ac.bl.DataAccessObject;
import il.ac.bl.JsonParserUtil;
import il.ac.exceptions.QueryException;
import il.ac.services.QueryWikipediaCallback;
import il.ac.services.QueryWikipediaJson;
import il.ac.shenkar.common.DialogHelper;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

public class MyActivity extends Activity
{
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        QueryWikipediaJson wikipedia = new QueryWikipediaJson();

        try
        {
            DataAccessObject.getInstance().getCityJson(new URL("http://en.wikipedia.org//w/api.php?action=query&prop=revisions&format=json&rvprop=content&rvsection=0&titles=Las%20Vegas"), new QueryWikipediaCallback<JSONObject>()
            {
                @Override
                public void done(JSONObject returnedObject, Exception e)
                {
                    TextView textView = (TextView) findViewById(R.id.justSomeText);
                    if (e == null)
                        textView.setText(returnedObject.toString());
                    else
                        textView.setText(e.getMessage());

                    JsonParserUtil.parseJson(returnedObject);
                    DialogHelper.closeProggresDialog();
                }
            });
        } catch (QueryException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (MalformedURLException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
