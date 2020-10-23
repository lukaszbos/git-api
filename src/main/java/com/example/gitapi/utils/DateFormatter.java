package com.example.gitapi.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class DateFormatter {
    public static LocalDateTime formatLocalDateTime(Map<String, Object> jsonMap) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        return LocalDateTime.parse((String) jsonMap.get("updated_at"), formatter);
    }
}