package ru.javawebinar.topjava;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class Watcher extends TestWatcher {
    private static String watchedLog = "\n";
    Long start;
    Long finish;

    @Override
    protected void starting(Description description) {
        start = System.currentTimeMillis();
    }

    @Override
    protected void finished(Description description) {
        String result = String.format("%s - %dмс. \n", description.getDisplayName(), finish - start);
        watchedLog += result;
    }

    @Override
    protected void succeeded(Description description) {
        finish = System.currentTimeMillis();
    }

    public static String getWatchedLog() {
        return watchedLog;
    }
}
