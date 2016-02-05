package com.dd.ui.feed;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.widget.Toast;
import com.dd.data.FeedItem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FeedPresenter {

    private FeedActivity mActivity;

    public FeedPresenter(FeedActivity activity) {
        mActivity = activity;
    }

    public void initPresenter() {
        loadData(0);
    }

    public void onToolbarSettingsClicked() {
        Toast.makeText(mActivity, "Presenter#onToolbarSettingsClicked", Toast.LENGTH_SHORT).show();
    }

    public void onToolbarHelpClicked() {
        Toast.makeText(mActivity, "Presenter#onToolbarHelpClicked", Toast.LENGTH_SHORT).show();
    }

    public void onFeedItemClicked(@NonNull FeedItem feedItem) {
        Toast.makeText(mActivity, String.format("Presenter#onFeedItemClicked %s",
                feedItem.getTitle()), Toast.LENGTH_SHORT).show();
    }

    public void onToolbarBackClicked() {
        mActivity.finish();
    }

    public void onPullToRefresh() {
        loadData(TimeUnit.SECONDS.toMillis(2));
    }

    private void loadData(long delayMillis) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mActivity.setFeedItemList(generateDummyData());
                mActivity.hidePullToRefreshIndicator();
            }
        }, delayMillis);
    }

    @NonNull
    private List<FeedItem> generateDummyData() {
        List<FeedItem> feedItemList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            FeedItem feedItem = new FeedItem();
            feedItem.setTitle(String.format("Feed Title %d", i));
            feedItem.setDescription(String.format("Feed Description %d", i));
            feedItemList.add(feedItem);
        }

        return feedItemList;
    }

}
