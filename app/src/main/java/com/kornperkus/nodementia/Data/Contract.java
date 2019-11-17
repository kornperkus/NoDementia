package com.kornperkus.nodementia.Data;

import android.provider.BaseColumns;

public class Contract {
    public static class CardEntry implements BaseColumns {
        public static final String TABLE_NAME = "cards";
        public static final String COLUMN_LEVEL = "level";
        public static final String COLUMN_IMAGE = "image";
        public static final String COLUMN_VALUE = "value";
    }
    public static class AlarmEntry implements BaseColumns {
        public static final String TABLE_NAME = "alarms";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_TIME = "time";
        public static final String COLUMN_STATUS = "status";

        public static final int STATUS_OFF = 0;
        public static final int STATUS_ON = 1;
    }
}
