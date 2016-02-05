### Activity - Presenter Example

This sample demonstrate **simple** and **clean** way of android project architecture.

`Activity` - contains public methods to deal with UI.<br>
`Presenter` - receive UI events from `Activity` and decided what to do.

**Code example**

```java
public class FeedActivity extends AppCompatActivity {

    private FeedPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feed_activity);

        initToolBar();
        initView();

        mPresenter = new FeedPresenter(this);
        mPresenter.initPresenter();
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
    
    // omitted
```

```java
public class FeedPresenter {

    private FeedActivity mActivity;

    public FeedPresenter(FeedActivity activity) {
        mActivity = activity;
    }

    public void initPresenter() {
        // omitted
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

  // ommited
}
```
