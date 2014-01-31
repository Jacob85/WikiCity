package il.ac.GUI;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import com.example.WikiCity.R;
import com.haarman.listviewanimations.swinginadapters.prepared.SwingBottomInAnimationAdapter;
import il.ac.adapters.GridViewImagesAdapter;
import il.ac.shenkar.common.CityEnumType;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Amsalem on 30/01/14.
 */
public class GridViewActivity extends Activity
{
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_layout);

        GridView gridView = (GridView) findViewById(R.id.gridview);
        GridViewImagesAdapter gridViewImagesAdapter = new GridViewImagesAdapter(this,getAllItems());
        SwingBottomInAnimationAdapter swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(gridViewImagesAdapter);
        swingBottomInAnimationAdapter.setAbsListView(gridView);
        swingBottomInAnimationAdapter.setInitialDelayMillis(300);
        gridView.setAdapter(swingBottomInAnimationAdapter);

    }

    public ArrayList<CityEnumType> getAllItems()
    {
        ArrayList<CityEnumType> toReturn;
        toReturn = new ArrayList<CityEnumType>(Arrays.asList(CityEnumType.values()));
        return toReturn;

    }
}