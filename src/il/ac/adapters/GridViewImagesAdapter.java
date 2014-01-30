package il.ac.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import il.ac.shenkar.common.CityInfo;
import il.ac.shenkar.common.cityEnumType;

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
            }
        });

        imageView.setImageResource(enumTypeArrayList.get(position).getImageId());
        return imageView;
    }
}