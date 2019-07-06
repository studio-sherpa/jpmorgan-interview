package com.sherpastudio.jpmorganalbums.view.detail;

import android.content.Context;
import android.content.Intent;

import com.sherpastudio.jpmorganalbums.Injection;
import com.sherpastudio.jpmorganalbums.R;
import com.sherpastudio.jpmorganalbums.TestUtils;
import com.sherpastudio.jpmorganalbums.view.MainActivity;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static com.sherpastudio.jpmorganalbums.TestUtils.withRecyclerView;

public class AlbumDetailFragmentTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class, true, false);
    private MockWebServer server;

    @Before
    public void setUp() throws Exception {
        server = new MockWebServer();
        server.start();
        Injection.DEBUG_ALBUMS_BASE_URL = server.url("/").toString();
    }

    @Test
    public void testListAlbums() throws Exception {
        String fileName = "albums_ok_response.json";
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        String jsonBody = TestUtils.getStringFromFile(context, fileName);
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(jsonBody));

        Intent intent = new Intent();
        mActivityRule.launchActivity(intent);

        onView(withRecyclerView(R.id.recycler_list).atPosition(0)).
                perform(click());

        onView(withId(R.id.title)).check(matches(withText("quidem molestiae enim")));

    }
}