package com.fireworkshop.mathbrain;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 33)
public class HighScoreRepositoryTest {
    private Context context;
    private HighScoreRepository repository;

    @Before
    public void setUp() {
        context = ApplicationProvider.getApplicationContext();
        context.getSharedPreferences("high_scores", Context.MODE_PRIVATE)
                .edit().clear().commit();
        repository = new HighScoreRepository(context);
    }

    @Test
    public void savesAndRetrievesHighScore() {
        repository.setHighScore(1, "Alice", "42");
        assertEquals("Alice", repository.getHighScoreName(1));
        assertEquals("42", repository.getHighScoreScore(1));
    }
}

