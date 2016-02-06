package com.dd.ui.feed;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import com.dd.data.FeedItem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FeedPresenter {

    private FeedActivity mActivity;

    public FeedPresenter() {
    }

    public void onAttachActivity(@NonNull Bundle savedInstanceState, @NonNull FeedActivity activity) {
        mActivity = activity;
        loadData();
    }

    public void onDetachActivity() {
        mActivity = null;
    }

    public void onToolbarSettingsClicked() {
        // handle click
    }

    public void onToolbarHelpClicked() {
        // handle click
    }

    public void onFeedItemClicked(@NonNull FeedItem feedItem) {
        // handle click
    }

    public void onToolbarBackClicked() {
        mActivity.finish();
    }

    public void onPullToRefresh() {
        loadData();
    }

    private void loadData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(TimeUnit.SECONDS.toMillis(5));
                List<FeedItem> feedItemList = generateDummyData();
                updateUI(feedItemList);
            }
        }).start();
    }

    private void updateUI(@NonNull final List<FeedItem> feedItemList) {
        if (mActivity != null) {
            mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mActivity.setFeedItemList(feedItemList);
                    mActivity.hidePullToRefreshIndicator();
                }
            });
        }
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
