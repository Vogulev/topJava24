package ru.javawebinar.topjava.util;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;

public final class StringToLocalDateConverter implements Converter<String, LocalDate>{
    @Override
    public LocalDate convert(String source) {
        if (!source.equals("null")) {
            return LocalDate.parse(source);
        } else {
            return null;
        }
    }
}
