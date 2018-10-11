package io.github.thismj.android.samples.window;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import io.github.thismj.android.BasicActivity;
import io.github.thismj.android.R;

/**
 * 描述
 *
 * @author aero.tang
 * @version 2018/8/3 22:03
 */
public class WindowActivity extends BasicActivity {

    @Override
    public int getContent() {
        return R.layout.activity_window;
    }

    @Override
    public void initComponent(View view) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
