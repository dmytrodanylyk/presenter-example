package com.dd;

import android.widget.Toast;

public class MainPresenter {

    private MainActivity mActivity;

    public MainPresenter(MainActivity activity) {
        mActivity = activity;
    }

    public void initPresenter() {

    }

    public void onToolbarSettingsClicked() {
        Toast.makeText(mActivity, "Presenter#onToolbarSettingsClicked", Toast.LENGTH_SHORT).show();
    }

    public void onToolbarHelpClicked() {
        Toast.makeText(mActivity, "Presenter#onToolbarHelpClicked", Toast.LENGTH_SHORT).show();
    }

    public void onToolbarBackClicked() {
        mActivity.finish();
    }

}
