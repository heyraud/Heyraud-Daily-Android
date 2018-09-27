package io.github.heyraud.daily.android.samples.constraint_layout;

import android.animation.ValueAnimator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.design.widget.TabLayout;
import android.support.transition.Transition;
import android.support.transition.TransitionManager;
import android.support.transition.TransitionSet;
import android.support.transition.TransitionValues;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;

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

    private View inflate(int position) {
        return getLayoutInflater().inflate(examples[position], mPager, false);
    }

    private int startSideHorizontalV;
    private int anchorHorizontalV;
    private int endSideHorizontalV;
    private int startSideVerticalV;
    private int anchorVerticalV;
    private int endSideVerticalV;

    private View obtainRelativePositioning() {
        final View container = inflate(0);

        final RadioGroup startSideH = container.findViewById(R.id.start_side_horizontal);
        final RadioGroup anchorH = container.findViewById(R.id.anchor_horizontal);
        final RadioGroup endSideH = container.findViewById(R.id.end_side_horizontal);
        final RadioGroup startSideV = container.findViewById(R.id.start_side_vertical);
        final RadioGroup anchorV = container.findViewById(R.id.anchor_vertical);
        final RadioGroup endSideV = container.findViewById(R.id.end_side_vertical);

        startSideH.check(startSideH.getChildAt(0).getId());
        anchorH.check(anchorH.getChildAt(0).getId());
        endSideH.check(endSideH.getChildAt(0).getId());
        startSideV.check(startSideV.getChildAt(0).getId());
        anchorV.check(anchorV.getChildAt(0).getId());
        endSideV.check(endSideV.getChildAt(0).getId());

        final ConstraintLayout item = container.findViewById(R.id.parent);
        final Button a = item.findViewById(R.id.a);
        final Button b = item.findViewById(R.id.b);
        final ConstraintSet set = new ConstraintSet();
        set.clone(item);

        startSideHorizontalV = ConstraintSet.START;
        anchorHorizontalV = a.getId();
        endSideHorizontalV = ConstraintSet.START;
        startSideVerticalV = ConstraintSet.TOP;
        anchorVerticalV = a.getId();
        endSideVerticalV = ConstraintSet.TOP;

        RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                set.clear(b.getId(), startSideHorizontalV);
                set.clear(b.getId(), startSideVerticalV);

                String tag = (String) group.findViewById(checkedId).getTag();
                int value = Integer.parseInt(tag);
                switch (group.getId()) {
                    case R.id.start_side_horizontal:
                        startSideHorizontalV = value;
                        break;
                    case R.id.anchor_horizontal:
                        if (value == 0) {
                            anchorHorizontalV = a.getId();
                        } else if (value == 1) {
                            anchorHorizontalV = ConstraintSet.PARENT_ID;
                        }
                        break;
                    case R.id.end_side_horizontal:
                        endSideHorizontalV = value;
                        break;
                    case R.id.start_side_vertical:
                        startSideVerticalV = value;
                        break;
                    case R.id.anchor_vertical:
                        if (value == 0) {
                            anchorVerticalV = a.getId();
                        } else if (value == 1) {
                            anchorVerticalV = ConstraintSet.PARENT_ID;
                        }
                        break;
                    case R.id.end_side_vertical:
                        endSideVerticalV = value;
                        break;
                }

                set.connect(b.getId(), startSideHorizontalV, anchorHorizontalV, endSideHorizontalV);
                if (startSideVerticalV == ConstraintSet.BASELINE) {
                    set.connect(b.getId(), ConstraintSet.BASELINE, anchorVerticalV, ConstraintSet.BASELINE);
                } else {
                    set.connect(b.getId(), startSideVerticalV, anchorVerticalV, endSideVerticalV);
                }

                TransitionManager.beginDelayedTransition(item);
                set.applyTo(item);
            }
        };

        startSideH.setOnCheckedChangeListener(onCheckedChangeListener);
        anchorH.setOnCheckedChangeListener(onCheckedChangeListener);
        endSideH.setOnCheckedChangeListener(onCheckedChangeListener);
        startSideV.setOnCheckedChangeListener(onCheckedChangeListener);
        anchorV.setOnCheckedChangeListener(onCheckedChangeListener);
        endSideV.setOnCheckedChangeListener(onCheckedChangeListener);

        return container;
    }

    private View obtainMargins() {
        final View container = inflate(1);
        final ConstraintLayout item = container.findViewById(R.id.parent);


        return item;
    }

    private View obtainCenteringPositioning() {
        final View container = inflate(2);
        final ConstraintLayout item = container.findViewById(R.id.parent);


        return item;
    }

    private View obtainCircularPositioning() {
        final View container = inflate(3);
        final ConstraintLayout item = container.findViewById(R.id.parent);


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
