package ru.javawebinar.topjava.util;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.LocalTime;

public final class StringToLocalTimeConverter implements Converter<String, LocalTime> {
    @Override
    public LocalTime convert(String source) {
        if (!source.equals("null")) {
            return LocalTime.parse(source);
        } else {
            return null;
        }
    }
}
