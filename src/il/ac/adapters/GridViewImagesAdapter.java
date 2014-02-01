package il.ac.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import il.ac.GUI.GridViewActivity;
import il.ac.GUI.MainActivity;
import il.ac.bl.DataAccessObject;
import il.ac.services.QueryWikipediaCallback;
import il.ac.shenkar.common.*;
import il.ac.shenker.wiki.PageSection;
import il.ac.shenker.wiki.WikiConsts;

import java.util.ArrayList;

/**
 * Created by Amsalem on 30/01/14.
 */
public class GridViewImagesAdapter extends BaseAdapter {

    private Context myContext;
    private ArrayList<cityEnumType > enumTypeArrayList;

    public GridViewImagesAdapter(Context myContext, ArrayList<cityEnumType > types)
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

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        ImageView imageView;
        if (convertView == null)
        {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(myContext);
            //imageView.setLayoutParams(new GridView.LayoutParams(200, 400));
            //imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
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
                if (isInternetAvailable())
                {
                    cityEnumType  type = enumTypeArrayList.get(position);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(WikiConsts.CITY_TYPE,type);

                    Intent intent = new Intent(myContext, MainActivity.class);
                    intent.putExtra(WikiConsts.CITY_TYPE_BUNDLE, bundle);
                    myContext.startActivity(intent);
                }
                else
                {
                    DialogHelper.showAlertDialog("No Connectivity", "Internet connection is required", myContext);
                    return;
                }
            }
        });

        imageView.setImageResource(enumTypeArrayList.get(position).getImageId());
        return imageView;
    }

    public boolean isInternetAvailable()
    {
        ConnectivityManager cm = (ConnectivityManager) myContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork == null)
            return false;
        return true;

    }
}
