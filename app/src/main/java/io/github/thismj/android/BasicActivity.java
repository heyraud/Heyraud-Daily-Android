package io.github.thismj.android;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import io.github.heyraud.daily.android.R;

/**
 * base activity
 *
 * @author aero.tang
 * @version 2018/8/3 22:02
 */
@SuppressWarnings("FieldCanBeLocal")
public abstract class BasicActivity extends AppCompatActivity implements IPage {

    private FrameLayout mParent;
    private Toolbar mToolbar;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
        mParent = findViewById(R.id.activity_content);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        //add content view
        View content = getLayoutInflater().inflate(getContent(), mParent, false);
        mParent.addView(content);

        initComponent(content);
    }
}
