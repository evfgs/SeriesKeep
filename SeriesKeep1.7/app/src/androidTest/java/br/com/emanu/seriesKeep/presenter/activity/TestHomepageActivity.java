package br.com.emanu.seriesKeep.presenter.activity;

import android.os.Handler;
import android.os.Looper;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.emanu.seriesKeep.R;
import br.com.emanu.seriesKeep.activities.Homepage_Activity;


import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TestHomepageActivity {

    @Rule
    public ActivityTestRule<Homepage_Activity> mActivityRule = new ActivityTestRule<>(
            Homepage_Activity.class);


    @Test
    public void checkLoadSeries() throws InterruptedException {
        Thread.sleep(2000);
        final RecyclerView series = (RecyclerView) mActivityRule.getActivity().findViewById(R.id.series);

        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                series.smoothScrollToPosition(7);
            }
        });

        Thread.sleep(1500);

        System.out.println("item count: " + series.getAdapter().getItemCount());
        assert (series.getAdapter().getItemCount() > 0);
    }
}
