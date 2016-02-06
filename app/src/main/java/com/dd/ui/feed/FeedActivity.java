package com.dd.ui.feed;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.dd.R;
import com.dd.data.FeedItem;

import java.util.List;

public class FeedActivity extends AppCompatActivity {

    private FeedPresenter mPresenter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private FeedAdapter mFeedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feed_activity);

        initToolBar();
        initView();

        mPresenter = new FeedPresenter();
        mPresenter.onAttachActivity(savedInstanceState, this);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetachActivity();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                mPresenter.onToolbarSettingsClicked();
                return true;
            case R.id.action_help:
                mPresenter.onToolbarHelpClicked();
                return true;
            case android.R.id.home:
                mPresenter.onToolbarBackClicked();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setFeedItemList(@NonNull List<FeedItem> feedItemList) {
        mFeedAdapter.setFeedItemList(feedItemList);
        mFeedAdapter.notifyDataSetChanged();
    }

    public void hidePullToRefreshIndicator() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title);
        setSupportActionBar(toolbar);
    }

    private void initView() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.onPullToRefresh();
            }
        });

        mFeedAdapter = new FeedAdapter(getApplicationContext(), new FeedAdapter.Listener() {
            @Override
            public void onFeedItemClicked(@NonNull FeedItem feedItem) {
                mPresenter.onFeedItemClicked(feedItem);
            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mFeedAdapter);
    }

}
