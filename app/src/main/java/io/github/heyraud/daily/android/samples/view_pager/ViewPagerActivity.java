package io.github.heyraud.daily.android.samples.view_pager;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.github.heyraud.daily.android.BasicActivity;
import io.github.heyraud.daily.android.R;

/**
 * ViewPager Sample
 *
 * @author aero.tang
 * @version 2018/9/26 16:02
 */
@SuppressWarnings("FieldCanBeLocal, SetTextI18n, InflateParams")
public class ViewPagerActivity extends BasicActivity {

    private TabLayout mTabs;
    private ViewPager mPagers;
    private ViewPagerAdapter mAdapter;

    @Override
    public int getContent() {
        return R.layout.activity_view_pager;
    }

    @Override
    public void initComponent(View view) {
        mTabs = findViewById(R.id.tabs);
        mPagers = findViewById(R.id.pagers);

        mAdapter = new ViewPagerAdapter(getSupportFragmentManager(), this);
        mPagers.setAdapter(mAdapter);
        mTabs.setupWithViewPager(mPagers);
        mTabs.setTabMode(TabLayout.MODE_SCROLLABLE);

        for (int i = 0; i < mTabs.getTabCount(); i++) {
            TabLayout.Tab tab = mTabs.getTabAt(i);
            if (tab != null) {
                View item = getLayoutInflater().inflate(R.layout.item_tab_with_badge, null);
                TextView title = item.findViewById(R.id.title);
                TextView badge = item.findViewById(R.id.badge);

                title.setText("Page" + i);
                badge.setText("" + i);

                tab.setCustomView(item);
            }
        }
    }
}
