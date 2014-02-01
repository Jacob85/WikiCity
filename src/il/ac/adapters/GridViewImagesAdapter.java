package il.ac.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;
import il.ac.GUI.MainActivity;
import il.ac.bl.DataAccessObject;
import il.ac.services.QueryWikipediaCallback;
import il.ac.shenkar.common.CityInfo;
import il.ac.shenkar.common.GifWebView;
import il.ac.shenkar.common.Logger;
import il.ac.shenkar.common.cityEnumType;
import il.ac.shenker.wiki.PageSection;
import il.ac.shenker.wiki.WikiConsts;

import java.util.ArrayList;

/**
 * Created by Amsalem on 30/01/14.
 */
public class GridViewImagesAdapter extends BaseAdapter {

    private Context myContext;
    private ArrayList<cityEnumType> enumTypeArrayList;

    public GridViewImagesAdapter(Context myContext, ArrayList<cityEnumType> types)
    {
        this.myContext = myContext;
        enumTypeArrayList = types;
    }


    @Override
    public int getCount()
    {
        return enumTypeArrayList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return enumTypeArrayList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }
//
//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent)
//    {
//        GifWebView webView;
//        if (convertView  == null)
//        {
//            webView = new GifWebView(myContext, "file:///android_asset/aminated.gif");
//        }
//        else
//        {
//            webView = (GifWebView) convertView;
//        }
//        webView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event)
//            {
//                cityEnumType type = enumTypeArrayList.get(position);
//                Toast.makeText(myContext, type.getCityName() + " was pressed, from state " + type.getStateName(), 1500).show();
//                return false;
//            }
//        });
//
//           return webView;
//    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        ImageView imageView;
        if (convertView == null)
        {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(myContext);
            //imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(4, 4, 4, 4);
        }
        else
        {
            imageView = (ImageView) convertView;
        }

        imageView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                cityEnumType type = enumTypeArrayList.get(position);
                Toast.makeText(myContext, type.getCityName() + " was pressed, from state " + type.getStateName(), 1500).show();
                Bundle bundle = new Bundle();
                bundle.putSerializable(WikiConsts.CITY_TYPE,type);

                Intent intent = new Intent(myContext, MainActivity.class);
                intent.putExtra(WikiConsts.CITY_TYPE_BUNDLE, bundle);
                myContext.startActivity(intent);
//                DataAccessObject.getInstance().getCityInfo(type.getCityName(), new QueryWikipediaCallback<CityInfo>() {
//                    @Override
//                    public void done(CityInfo returnedObject, Exception e)
//                    {
//                        if (returnedObject != null)
//                        {
//                            DataAccessObject.getInstance().getWikiSectionInfo(returnedObject.getPageSections().get(0), new QueryWikipediaCallback<PageSection>() {
//                                @Override
//                                public void done(PageSection returnedObject, Exception e)
//                                {
//
//                                    Logger.logInfo(returnedObject.getSecrionContentInHtml());
//                                }
//                            });
//                        }
//
//                    }
//                });
            }
        });

        imageView.setImageResource(enumTypeArrayList.get(position).getImageId());
        return imageView;
    }
}
