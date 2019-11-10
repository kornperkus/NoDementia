package com.kornperkus.nodementia.Data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class CardProvider {

    private Context mContext;
    private SQLiteDatabase db;

    public CardProvider(Context context) {
        mContext = context;
        CardDbHelper dbHelper = CardDbHelper.getInstance(mContext);
        db = dbHelper.getReadableDatabase();
    }

    public List<Card> getCardLists(int level) {
        List<Card> cards = new ArrayList<>();

        String selection = Contract.CardEntry.COLUMN_LEVEL+"=?";
        String[] selectionArg = new String[] { Integer.toString(level) };
        Cursor cursor = db.query(Contract.CardEntry.TABLE_NAME, null, selection, selectionArg, null, null, null);

        int imageColumn = cursor.getColumnIndex(Contract.CardEntry.COLUMN_IMAGE);
        int valueColumn = cursor.getColumnIndex(Contract.CardEntry.COLUMN_VALUE);

        if(cursor.moveToFirst()) {
            int i = 0;
            do {
                int imageResId = cursor.getInt(imageColumn);
                int value = cursor.getInt(valueColumn);
                cards.add(new Card(i, imageResId, value, false, false));
                i++;
            } while(cursor.moveToNext());
        }

        return cards;
    }
}
