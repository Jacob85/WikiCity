package com.example.WikiCity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import com.google.android.gms.maps.GoogleMap;
import il.ac.bl.DataAccessObject;
import il.ac.bl.JsonParserUtil;
import il.ac.exceptions.QueryException;
import il.ac.services.QueryWikipediaCallback;
import il.ac.shenkar.common.DialogHelper;
import il.ac.shenker.wiki.WikiConsts;
import il.ac.shenker.wiki.WikiImageInfo;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

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

//        Button showMain = (Button) findViewById(R.id.showMainQuery);
//        showMain.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                        try
//        {
//            DataAccessObject.getInstance().queryCityJson(new URL("http://en.wikipedia.org//w/api.php?action=query&prop=revisions&format=json&rvprop=content&rvsection=0&titles=Houston"), new QueryWikipediaCallback<JSONObject>()
//            {
//                @Override
//                public void done(JSONObject returnedObject, Exception e)
//                {
//                    TextView textView = (TextView) findViewById(R.id.story_text);
//                    if (e == null)
//                        textView.setText(returnedObject.toString());
//                    else
//                        textView.setText(e.getMessage());
//
//                    HashMap<String, String> cityParsedJson = JsonParserUtil.parseJsonToHashMap(returnedObject);
//                    printToLog(cityParsedJson);
//
//
//                    DialogHelper.closeProggresDialog();
//
//
//                }
//
//            });
//
//        } catch (QueryException e)
//        {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        } catch (MalformedURLException e)
//        {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
//
//            }
//        });
//
//        Button showImage = (Button) findViewById(R.id.showimage);
//        showImage.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                try
//                {
//                    DataAccessObject.getInstance().queryImageFromCity(new URL("https://en.wikipedia.org/w/api.php?action=query&prop=imageinfo&format=json&iiprop=url&titles=File%3AFlag%20of%20Los%20Angeles%2C%20California.svg%20%7C%20File%3ASeal%20of%20Los%20Angeles%2C%20California.svg"), new QueryWikipediaCallback<JSONObject>()
//                    {
//                        @Override
//                        public void done(JSONObject returnedObject, Exception e)
//                        {
//                            Log.i(MyActivity.class.getSimpleName(), returnedObject.toString());
//                            try
//                            {
//                                ArrayList<WikiImageInfo> infos = (ArrayList<WikiImageInfo>) JsonParserUtil.parseJsonToImagesList(returnedObject);
//                                Log.i(MyActivity.class.getSimpleName(), returnedObject.toString());
//                                TextView textView = (TextView) findViewById(R.id.justSomeText);
//                                if (e == null)
//                                    textView.setText(returnedObject.toString());
//                                else
//                                    textView.setText(e.getMessage());
//                            } catch (JSONException e1)
//                            {
//                                e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//                            }
//
//                        }
//                    });
//                } catch (MalformedURLException e1)
//                {
//                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//                }
//            }
//        });





    }

    private void printToLog(HashMap<String, String> cityParsedJson) {

        Log.i(MyActivity.class.getSimpleName(), "total population: " + cityParsedJson.get(WikiConsts.CITY_POPULATION));
        Log.i(MyActivity.class.getSimpleName(), "timezone offset : " + cityParsedJson.get(WikiConsts.TIME_ZONE_OFFSET));
        Log.i(MyActivity.class.getSimpleName(), "leader title : " + cityParsedJson.get(WikiConsts.LEADER_TITLE));
    }
}
