package ru.javawebinar.topjava.service;

import org.junit.AfterClass;
import org.junit.Rule;
import ru.javawebinar.topjava.Watcher;

public class AbstractTest {
    @Rule
    public Watcher watcher = new Watcher();

    @AfterClass
    public static void afterClass() {
        System.out.println(Watcher.getWatchedLog());
    }
}
