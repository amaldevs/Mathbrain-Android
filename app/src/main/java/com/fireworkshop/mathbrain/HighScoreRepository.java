package com.fireworkshop.mathbrain;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Repository for storing and retrieving high scores.
 * Uses SharedPreferences but is able to migrate legacy
 * file based storage on first run.
 */
public class HighScoreRepository {
    private static final String PREFS_NAME = "high_scores";
    private static final String MIGRATED_KEY = "migrated";
    private static final int MAX_SCORES = 5;
    private static final String[] NAME_KEYS = {
            "first_name",
            "second_name",
            "third_name",
            "fourth_name",
            "fifth_name"};
    private static final String[] SCORE_KEYS = {
            "first_score",
            "second_score",
            "third_score",
            "fourth_score",
            "fifth_score"};

    private final SharedPreferences prefs;
    private final Context context;

    public HighScoreRepository(Context context) {
        this.context = context.getApplicationContext();
        this.prefs = this.context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        migrateIfNecessary();
    }

    /**
     * Populate default high scores if none are stored.
     */
    public void ensureDefaults() {
        if (!prefs.contains(NAME_KEYS[4])) { // not initialised
            setHighScore(5, "Job", "197.27");
            setHighScore(4, "Vikie", "105.50");
            setHighScore(3, "Luis", "88.73");
            setHighScore(2, "Li", "74.41");
            setHighScore(1, "Kidu", "60.15");
        }
    }

    public String getHighScoreName(int position) {
        return prefs.getString(NAME_KEYS[position - 1], "");
    }

    public String getHighScoreScore(int position) {
        return prefs.getString(SCORE_KEYS[position - 1], "");
    }

    public void setHighScoreName(int position, String name) {
        prefs.edit().putString(NAME_KEYS[position - 1], name).apply();
    }

    public void setHighScoreScore(int position, String score) {
        prefs.edit().putString(SCORE_KEYS[position - 1], score).apply();
    }

    public void setHighScore(int position, String name, String score) {
        setHighScoreName(position, name);
        setHighScoreScore(position, score);
    }

    public String getPreviousName() {
        return prefs.getString("prev_name", "");
    }

    public void setPreviousName(String name) {
        prefs.edit().putString("prev_name", name).apply();
    }

    public String getPendingScore() {
        return prefs.getString("pending_score", "");
    }

    public void setPendingScore(String score) {
        prefs.edit().putString("pending_score", score).apply();
    }

    public void clearPendingScore() {
        prefs.edit().remove("pending_score").apply();
    }

    private void migrateIfNecessary() {
        if (prefs.getBoolean(MIGRATED_KEY, false)) {
            return;
        }

        // migrate legacy file based scores
        String[] legacyNames = {"first", "second", "third", "fourth", "fifth"};
        for (int i = 0; i < MAX_SCORES; i++) {
            String name = readLegacyFile(legacyNames[i]);
            String score = readLegacyFile(legacyNames[i] + "score");
            if (!name.isEmpty()) {
                prefs.edit().putString(NAME_KEYS[i], name).apply();
            }
            if (!score.isEmpty()) {
                prefs.edit().putString(SCORE_KEYS[i], score).apply();
            }
        }
        String prev = readLegacyFile("prevname");
        if (!prev.isEmpty()) {
            prefs.edit().putString("prev_name", prev).apply();
        }
        String pending = readLegacyFile("newscore");
        if (!pending.isEmpty()) {
            prefs.edit().putString("pending_score", pending).apply();
        }

        prefs.edit().putBoolean(MIGRATED_KEY, true).apply();
    }

    private String readLegacyFile(String fileName) {
        File file = context.getFileStreamPath(fileName);
        if (!file.exists()) {
            return "";
        }
        FileInputStream fis = null;
        try {
            fis = context.openFileInput(fileName);
            byte[] b = new byte[32];
            int read = fis.read(b);
            if (read != -1) {
                return new String(b, 0, read).trim();
            }
        } catch (IOException ignored) {
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException ignored) {
                }
            }
        }
        return "";
    }
}

