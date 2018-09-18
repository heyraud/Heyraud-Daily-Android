package io.github.heyraud.daily.android.samples.constraint_layout;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;

import io.github.heyraud.daily.android.BasicActivity;
import io.github.heyraud.daily.android.R;

/**
 * ConstraintLayout Sample
 *
 * @author aero.tang
 * @version 2018/9/17 21:34
 */
public class ConstraintLayoutActivity extends BasicActivity {
    private TabLayout mTab;
    private ViewPager mPager;
    private ConstraintAdapter mAdapter;
    private String[] titles = new String[]{
            "edge",
            "chain"};
    private int[] examples = new int[]{
            R.layout.example_constraint_edge,
            R.layout.example_constraint_chain};

    @Override
    protected int getContent() {
        return R.layout.activity_constraint_layout;
    }

    @Override
    protected void initComponent() {
        mTab = findViewById(R.id.tab);
        mPager = findViewById(R.id.pager);

        mAdapter = new ConstraintAdapter();
        mPager.setAdapter(mAdapter);
        mTab.setupWithViewPager(mPager);
    }

    private class ConstraintAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return examples.length;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View item = getLayoutInflater().inflate(examples[position], container, false);
            container.addView(item);
            return item;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }
    }
}
