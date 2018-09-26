package io.github.heyraud.daily.android.samples.constraint_layout;

import android.animation.ValueAnimator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.design.widget.TabLayout;
import android.support.transition.TransitionManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
            "Relative positioning",
            "Margins",
            "Centering positioning",
            "Circular positioning"};
    private int[] examples = new int[]{
            R.layout.example_constraint_relative_positioning,
            R.layout.example_constraint_margins,
            R.layout.example_constraint_centering_positioning,
            R.layout.example_constraint_circular_positioning};

    @Override
    public int getContent() {
        return R.layout.activity_constraint_layout;
    }

    @Override
    public void initComponent(View view) {
        mTab = findViewById(R.id.tab);
        mPager = findViewById(R.id.pager);

        mAdapter = new ConstraintAdapter();
        mPager.setAdapter(mAdapter);
        mTab.setupWithViewPager(mPager);
    }

    private ConstraintLayout inflate(int position) {
        return (ConstraintLayout) getLayoutInflater().inflate(examples[position], mPager, false);
    }

    private View obtainRelativePositioning() {
        final ConstraintLayout item = inflate(0);
        final Button a = item.findViewById(R.id.a);
        final Button b = item.findViewById(R.id.b);

        final ConstraintSet set = new ConstraintSet();
        set.clone(item);

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set.clear(b.getId(), ConstraintSet.START);
                set.clear(b.getId(), ConstraintSet.BOTTOM);
                set.connect(b.getId(), ConstraintSet.START, a.getId(), ConstraintSet.START);
                set.connect(b.getId(), ConstraintSet.TOP, a.getId(), ConstraintSet.TOP);
                TransitionManager.beginDelayedTransition(item);
                set.applyTo(item);

                set.clear(b.getId(), ConstraintSet.START);
                set.clear(b.getId(), ConstraintSet.TOP);
                set.connect(b.getId(), ConstraintSet.START, a.getId(), ConstraintSet.START);
                set.connect(b.getId(), ConstraintSet.TOP, a.getId(), ConstraintSet.BOTTOM);
                TransitionManager.beginDelayedTransition(item);
                set.applyTo(item);
            }
        });

        return item;
    }

    private View obtainMargins() {
        View item = inflate(1);


        return item;
    }

    private View obtainCenteringPositioning() {
        View item = inflate(2);


        return item;
    }

    private View obtainCircularPositioning() {
        View item = inflate(3);


        return item;
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
            View item;
            switch (position) {
                case 0:
                    item = obtainRelativePositioning();
                    break;
                case 1:
                    item = obtainMargins();
                    break;
                case 2:
                    item = obtainCenteringPositioning();
                    break;
                case 3:
                    item = obtainCircularPositioning();
                    break;
                default:
                    throw new IllegalArgumentException("invalid position");
            }
            container.addView(item);
            return item;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }
}
