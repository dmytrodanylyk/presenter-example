### Activity - Presenter Example

This sample demonstrate **simple** and **clean** way of android project architecture.

`Activity` - contains public methods to deal with UI.<br>
`Presenter` - receive UI events from `Activity` and decided what to do.

### FAQ

**Why not to put all the code inside Activity?**

Because you never know how big it can grow.

**Why not to use MV*whatever pattern?**

Because it comes with `InterfaceStackoverflowException`. Number of interfaces and abstraction is huge, for most of android projects *(where we are not event writing tests)* this is [overengineering](https://en.wikipedia.org/wiki/Overengineering), just admit this.


### Code example

```java
public class FeedActivity extends AppCompatActivity {

    private FeedPresenter mPresenter;

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

    public void onAttachActivity(Bundle savedInstanceState, FeedActivity activity) {
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

    public void onToolbarBackClicked() {
        mActivity.finish();
    }

  // ommited
}
```

Full code is available [here](https://github.com/dmytrodanylyk/presenter-example/tree/master/app/src/main/java/com/dd/ui/feed).
