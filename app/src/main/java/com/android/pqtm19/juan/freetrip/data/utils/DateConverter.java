package com.android.pqtm19.juan.freetrip.data.utils;

import androidx.room.TypeConverter;

import java.util.Date;

public class DateConverter {
        @TypeConverter
        public Date fromTimestamp(Long value) {
            return value == null ? null : new Date(value);
        }

        @TypeConverter
        public Long dateToTimestamp(Date date) {
            if (date == null) {
                return null;
            } else {
                return date.getTime();
            }
        }
}

