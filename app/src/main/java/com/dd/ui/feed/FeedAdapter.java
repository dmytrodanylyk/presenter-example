package com.dd.ui.feed;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.dd.R;
import com.dd.data.FeedItem;

import java.util.ArrayList;
import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ItemViewHolder> {

    private List<FeedItem> mFeedItemList;
    private Context mContext;
    private Listener mListener;

    public FeedAdapter(@NonNull Context context, @NonNull Listener listener) {
        mContext = context;
        mListener = listener;
        mFeedItemList = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return mFeedItemList.size();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.feed_item, viewGroup, false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder itemViewHolder, int i) {
        itemViewHolder.setFeedItem(mFeedItemList.get(i));
    }

    public void setFeedItemList(@NonNull List<FeedItem> feedItemList) {
        mFeedItemList = feedItemList;
    }

    public interface Listener {
        void onFeedItemClicked(FeedItem feedItem);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;
        private TextView mTxtDescription;
        private FeedItem mFeedItem;

        public ItemViewHolder(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.txtTitle);
            mTxtDescription = (TextView) view.findViewById(R.id.txtDescription);

            View.OnClickListener onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onFeedItemClicked(mFeedItem);
                }
            };

            view.setOnClickListener(onClickListener);
        }

        public void setFeedItem(@NonNull FeedItem feedItem) {
            mFeedItem = feedItem;
            mTextView.setText(feedItem.getTitle());
            mTxtDescription.setText(feedItem.getDescription());
        }
    }
}
