package com.kornperkus.nodementia.Data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Alarm.class}, version = 1, exportSchema = false)
public abstract class AlarmDatabase extends RoomDatabase {
    private static AlarmDatabase instance;

    public abstract AlarmDao alarmDao();

    public static synchronized AlarmDatabase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AlarmDatabase.class, "alarm_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private AlarmDao alarmDao;

        private PopulateDbAsyncTask(AlarmDatabase db) {
            alarmDao = db.alarmDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            alarmDao.insert(new Alarm("รับประทานอาหาร", 1546275600, 0));
            alarmDao.insert(new Alarm("ยาก่อนอาหาร -ยาหลังอาหาร", 1546275600, 0));
            alarmDao.insert(new Alarm("นอนกลางวัน", 1546275600, 0));
            alarmDao.insert(new Alarm("บริหารสมอง", 1546275600, 0));
            alarmDao.insert(new Alarm("ออกกำลังกาย", 1546275600, 0));
            alarmDao.insert(new Alarm("อ่านบทสวด", 1546275600, 0));
            alarmDao.insert(new Alarm("เข้านอน", 1546275600, 0));
            return null;
        }
    }
}