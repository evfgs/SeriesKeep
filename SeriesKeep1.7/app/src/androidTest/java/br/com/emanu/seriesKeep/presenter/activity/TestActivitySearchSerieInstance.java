package br.com.emanu.seriesKeep.presenter.activity;

import android.os.Handler;
import android.os.Looper;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.emanu.seriesKeep.R;
import br.com.emanu.seriesKeep.activities.SearchSerie_Activity;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class TestActivitySearchSerieInstance {

    @Rule
    public ActivityTestRule<SearchSerie_Activity> mActivityRule = new ActivityTestRule<>(
            SearchSerie_Activity.class);

    @Test
    public void startSearchActivity() throws InterruptedException {
        final TextView query = (TextView) mActivityRule.getActivity().findViewById(R.id.query_container);
        final RecyclerView results = (RecyclerView) mActivityRule.getActivity().findViewById(R.id.series);
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                query.setText("Engineer");
            }
        });
        Thread.sleep(1500);
        assert (results.getAdapter().getItemCount() > 0);
    }
}
