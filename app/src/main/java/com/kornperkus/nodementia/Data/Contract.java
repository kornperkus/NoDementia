package com.kornperkus.nodementia.Data;

import android.provider.BaseColumns;

public class Contract {
    public static class CardEntry implements BaseColumns {
        public static final String TABLE_NAME = "cards";
        public static final String COLUMN_LEVEL = "level";
        public static final String COLUMN_IMAGE = "image";
        public static final String COLUMN_VALUE = "value";
    }
}
