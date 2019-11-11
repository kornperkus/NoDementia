package com.kornperkus.nodementia.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.kornperkus.nodementia.R;

public class CardDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "card.db";
    private static final int DATABASE_VERSION = 1;
    private static CardDbHelper mDbHelper;

    private Context mContext;
    private SQLiteDatabase mDb;

    private CardDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    public static synchronized CardDbHelper getInstance(Context context) {
        if (mDbHelper == null) {
            mDbHelper = new CardDbHelper(context.getApplicationContext());
        }
        return mDbHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_TABLE = "CREATE TABLE " + Contract.CardEntry.TABLE_NAME + "("
                + Contract.CardEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Contract.CardEntry.COLUMN_LEVEL + " INTEGER NOT NULL, "
                + Contract.CardEntry.COLUMN_IMAGE + " INTEGER DEFAULT 0, "
                + Contract.CardEntry.COLUMN_VALUE + " INTEGER NOT NULL);";
        db.execSQL(CREATE_TABLE);

        mDb = db;
        addAllData();
    }

    @Override
    public void onUpgrade(SQLiteDatabase p1, int p2, int p3) {

    }

    private void addAllData() {
        //1
        addCard(1, R.drawable.game_1, 1);
        addCard(1, R.drawable.game_3, 3);
        addCard(1, R.drawable.game_2, 2);
        addCard(1, R.drawable.game_3, 3);
        addCard(1, R.drawable.game_4, 4);
        addCard(1, R.drawable.game_5, 5);
        addCard(1, R.drawable.game_6, 6);
        addCard(1, R.drawable.game_4, 4);
        addCard(1, R.drawable.game_1, 1);
        addCard(1, R.drawable.game_2, 2);
        addCard(1, R.drawable.game_5, 5);
        addCard(1, R.drawable.game_6, 6);

        //2
        addCard(2, R.drawable.game_3, 3);
        addCard(2, R.drawable.game_4, 4);
        addCard(2, R.drawable.game_8, 8);
        addCard(2, R.drawable.game_5, 5);
        addCard(2, R.drawable.game_3, 3);
        addCard(2, R.drawable.game_8, 8);
        addCard(2, R.drawable.game_4, 4);
        addCard(2, R.drawable.game_6, 6);
        addCard(2, R.drawable.game_7, 7);
        addCard(2, R.drawable.game_6, 6);
        addCard(2, R.drawable.game_7, 7);
        addCard(2, R.drawable.game_5, 5);

        //3
        addCard(3, R.drawable.game_5, 5);
        addCard(3, R.drawable.game_5, 5);
        addCard(3, R.drawable.game_6, 6);
        addCard(3, R.drawable.game_9, 9);
        addCard(3, R.drawable.game_10, 10);
        addCard(3, R.drawable.game_7, 7);
        addCard(3, R.drawable.game_8, 8);
        addCard(3, R.drawable.game_7, 7);
        addCard(3, R.drawable.game_6, 6);
        addCard(3, R.drawable.game_8, 8);
        addCard(3, R.drawable.game_9, 9);
        addCard(3, R.drawable.game_10, 10);

        //4
        addCard(4, R.drawable.game_7, 7);
        addCard(4, R.drawable.game_11, 11);
        addCard(4, R.drawable.game_12, 12);
        addCard(4, R.drawable.game_11, 11);
        addCard(4, R.drawable.game_8, 8);
        addCard(4, R.drawable.game_7, 7);
        addCard(4, R.drawable.game_9, 9);
        addCard(4, R.drawable.game_10, 10);
        addCard(4, R.drawable.game_12, 12);
        addCard(4, R.drawable.game_9, 9);
        addCard(4, R.drawable.game_10, 10);
        addCard(4, R.drawable.game_8, 8);

        //5
        addCard(5, R.drawable.game_9, 9);
        addCard(5, R.drawable.game_11, 11);
        addCard(5, R.drawable.game_9, 9);
        addCard(5, R.drawable.game_10, 10);
        addCard(5, R.drawable.game_1, 1);
        addCard(5, R.drawable.game_2, 2);
        addCard(5, R.drawable.game_10, 10);
        addCard(5, R.drawable.game_12, 12);
        addCard(5, R.drawable.game_11, 11);
        addCard(5, R.drawable.game_2, 2);
        addCard(5, R.drawable.game_12, 12);
        addCard(5, R.drawable.game_1, 1);

        //6
        addCard(6, R.drawable.game_1, 1);
        addCard(6, R.drawable.game_5, 5);
        addCard(6, R.drawable.game_4, 4);
        addCard(6, R.drawable.game_1, 1);
        addCard(6, R.drawable.game_6, 6);
        addCard(6, R.drawable.game_2, 2);
        addCard(6, R.drawable.game_4, 4);
        addCard(6, R.drawable.game_5, 5);
        addCard(6, R.drawable.game_3, 3);
        addCard(6, R.drawable.game_2, 2);
        addCard(6, R.drawable.game_6, 6);
        addCard(6, R.drawable.game_3, 3);


        //7
        addCard(7, R.drawable.game_3, 3);
        addCard(7, R.drawable.game_8, 8);
        addCard(7, R.drawable.game_6, 6);
        addCard(7, R.drawable.game_7, 7);
        addCard(7, R.drawable.game_5, 5);
        addCard(7, R.drawable.game_4, 4);
        addCard(7, R.drawable.game_6, 6);
        addCard(7, R.drawable.game_5, 5);
        addCard(7, R.drawable.game_7, 7);
        addCard(7, R.drawable.game_3, 3);
        addCard(7, R.drawable.game_4, 4);
        addCard(7, R.drawable.game_8, 8);

        //8
        addCard(8, R.drawable.game_10, 10);
        addCard(8, R.drawable.game_9, 9);
        addCard(8, R.drawable.game_7, 7);
        addCard(8, R.drawable.game_5, 5);
        addCard(8, R.drawable.game_9, 9);
        addCard(8, R.drawable.game_6, 6);
        addCard(8, R.drawable.game_8, 8);
        addCard(8, R.drawable.game_7, 7);
        addCard(8, R.drawable.game_8, 8);
        addCard(8, R.drawable.game_10, 10);
        addCard(8, R.drawable.game_5, 5);
        addCard(8, R.drawable.game_6, 6);

        //9
        addCard(9, R.drawable.game_10, 10);
        addCard(9, R.drawable.game_7, 7);
        addCard(9, R.drawable.game_11, 11);
        addCard(9, R.drawable.game_9, 9);
        addCard(9, R.drawable.game_10, 10);
        addCard(9, R.drawable.game_8, 8);
        addCard(9, R.drawable.game_12, 12);
        addCard(9, R.drawable.game_11, 11);
        addCard(9, R.drawable.game_8, 8);
        addCard(9, R.drawable.game_7, 7);
        addCard(9, R.drawable.game_12, 12);
        addCard(9, R.drawable.game_9, 9);

        //10
        addCard(10, R.drawable.game_9, 9);
        addCard(10, R.drawable.game_1, 1);
        addCard(10, R.drawable.game_10, 10);
        addCard(10, R.drawable.game_2, 2);
        addCard(10, R.drawable.game_12, 12);
        addCard(10, R.drawable.game_11, 11);
        addCard(10, R.drawable.game_2, 2);
        addCard(10, R.drawable.game_1, 1);
        addCard(10, R.drawable.game_11, 11);
        addCard(10, R.drawable.game_9, 9);
        addCard(10, R.drawable.game_10, 10);
        addCard(10, R.drawable.game_12, 12);
    }

    private void addCard(int level, int image, int value) {
        ContentValues content = new ContentValues();
        content.put(Contract.CardEntry.COLUMN_LEVEL, level);
        content.put(Contract.CardEntry.COLUMN_IMAGE, image);
        content.put(Contract.CardEntry.COLUMN_VALUE, value);

        mDb.insert(Contract.CardEntry.TABLE_NAME, null, content);
    }
}
