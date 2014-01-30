package il.ac.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import il.ac.shenkar.common.StateInfo;

import java.util.ArrayList;

/**
 * Created by Amsalem on 30/01/14.
 */
public class DataBaseHealper extends SQLiteOpenHelper
{
    private static DataBaseHealper instance = null;
    private StateInfo stateInfo;

    private final static int DATABASE_VERSION = 1;
    private final static String DATABASE_NAME = "WikiCityDB";
    private final static String TABLE_NAME  = "StateInfo";
    private final static String STATE_NAME  = "stateName";
    private final static String STATE_POPULATION = "statePopulation";
    private final static String STATE_AREA = "stateArea";
    private final static String STATE_CURRENCY_CODE = "stateCurrencyCode";
    private final static String STATE_CAPITAL = "stateCapital";




    private void createTable(SQLiteDatabase db)
    {
        if (db == null)
            db = getWritableDatabase();
        String CREATE_LOCATION_TABLE = "CREATE TABLE " + TABLE_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                STATE_NAME + " TEXT, " +
                STATE_POPULATION + " REAL," +
                STATE_AREA + " REAL," +
                STATE_CURRENCY_CODE + " TEXT," +
                STATE_CAPITAL + " TEXT)";
        db.execSQL(CREATE_LOCATION_TABLE);

    }

    private void initStateInfo()
    {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst())
        {
            do
            {
                stateInfo = new StateInfo();
                stateInfo.setStateName(cursor.getString(1));
                stateInfo.setStatePopulation(cursor.getLong(2));
                stateInfo.setStateArea(cursor.getDouble(3));
                stateInfo.setStateCurrencyCode(cursor.getString(4));
                stateInfo.setStateCapitalCity(cursor.getString(5));
            }
            while (cursor.moveToNext()); // move to the next row in the DB
        }
    cursor.close();
    db.close();
    }




    private DataBaseHealper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        initStateInfo();
    }

    /*
     * Static Function to get the instance for the data structure
     */
    public static DataBaseHealper getInstance(Context context)
    {
        if (instance == null)
            instance = new DataBaseHealper(context);
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        createTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }
}
