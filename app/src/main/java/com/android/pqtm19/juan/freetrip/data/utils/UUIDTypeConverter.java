package com.android.pqtm19.juan.freetrip.data.utils;

import androidx.room.TypeConverter;

import java.util.UUID;

public class UUIDTypeConverter {

    @TypeConverter
    public UUID toUUID(String uuid){ return UUID.fromString(uuid); }

    @TypeConverter
    public String fromUUID(UUID uuid){ return uuid.toString(); }
}
