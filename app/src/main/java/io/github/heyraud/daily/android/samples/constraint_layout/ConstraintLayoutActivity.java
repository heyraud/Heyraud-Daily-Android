package io.github.heyraud.daily.android.samples.constraint_layout;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
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
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import io.github.heyraud.daily.android.BasicActivity;
import io.github.heyraud.daily.android.R;

/**
 * ConstraintLayout Sample
 *
 * @author aero.tang
 * @version 2018/9/17 21:34
 */
@SuppressLint("SetTextI18n")
public class ConstraintLayoutActivity extends BasicActivity {
    private TabLayout mTab;
    private ViewPager mPager;
    private ConstraintAdapter mAdapter;
    private String[] titles = new String[]{
            "Relative positioning",
            "Margins",
            "Centering positioning",
            "Circular positioning",
            "Dimensions constraints"};
    private int[] examples = new int[]{
            R.layout.example_constraint_relative_positioning,
            R.layout.example_constraint_margins,
            R.layout.example_constraint_centering_positioning,
            R.layout.example_constraint_circular_positioning,
            R.layout.example_constraint_dimensions_constraints};

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
        final Button a = item.findViewById(R.id.a);
        final Button c = item.findViewById(R.id.c);
        final Button d = item.findViewById(R.id.d);

        final Button btnA = container.findViewById(R.id.btn_a);
        final Button btnC = container.findViewById(R.id.btn_c);

        final ConstraintSet set = new ConstraintSet();
        set.clone(item);

        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (a.getVisibility() == View.VISIBLE) {
                    set.setVisibility(a.getId(), View.GONE);
                } else {
                    set.setVisibility(a.getId(), View.VISIBLE);
                }

                TransitionManager.beginDelayedTransition(item);
                set.applyTo(item);
            }
        });

        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (c.getVisibility() == View.VISIBLE) {
                    set.setVisibility(c.getId(), View.GONE);

                    set.setGoneMargin(d.getId(), ConstraintSet.START,
                            (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 112, getResources().getDisplayMetrics()));
                } else {
                    set.setVisibility(c.getId(), View.VISIBLE);
                }

                TransitionManager.beginDelayedTransition(item);
                set.applyTo(item);
            }
        });
        return container;
    }

    private int biasH = 50;
    private int biasV = 50;

    private View obtainCenteringPositioning() {
        final View container = inflate(2);
        final ConstraintLayout item = container.findViewById(R.id.parent);
        final Button a = item.findViewById(R.id.a);

        final SeekBar seekBiasH = container.findViewById(R.id.seek_bias_h);
        final SeekBar seekBiasV = container.findViewById(R.id.seek_bias_v);
        seekBiasH.setMax(100);
        seekBiasH.setProgress(biasH);
        seekBiasV.setMax(100);
        seekBiasV.setProgress(biasV);

        final ConstraintSet set = new ConstraintSet();
        set.clone(item);

        SeekBar.OnSeekBarChangeListener changeListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (seekBar.getId() == seekBiasH.getId()) {
                    biasH = progress;
                } else if (seekBar.getId() == seekBiasV.getId()) {
                    biasV = progress;
                }

                StringBuilder builder = new StringBuilder();
                builder.append("H Bias ")
                        .append(biasH)
                        .append("%")
                        .append("\n")
                        .append("V Bias ")
                        .append(biasV)
                        .append("%");
                if (biasH == 50 && biasV == 50) {
                    builder.append("\nCenter");
                }

                a.setText(builder.toString());

                set.setHorizontalBias(a.getId(), biasH / 100f);
                set.setVerticalBias(a.getId(), biasV / 100f);
                set.applyTo(item);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };

        seekBiasH.setOnSeekBarChangeListener(changeListener);
        seekBiasV.setOnSeekBarChangeListener(changeListener);

        return container;
    }

    private int angleValue = 30;
    private int radiusValue;

    private View obtainCircularPositioning() {
        final View container = inflate(3);
        final ConstraintLayout item = container.findViewById(R.id.parent);
        final TextView a = item.findViewById(R.id.a);
        final View anchor = item.findViewById(R.id.center);

        final SeekBar angle = container.findViewById(R.id.seek_circle_angle);
        final SeekBar radius = container.findViewById(R.id.seek_circle_radius);
        angle.setMax(360);
        angle.setProgress(angleValue);
        radius.setMax(500);
        radiusValue = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());
        radius.setProgress(radiusValue);

        final ConstraintSet set = new ConstraintSet();
        set.clone(item);

        SeekBar.OnSeekBarChangeListener changeListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (seekBar.getId() == angle.getId()) {
                    angleValue = progress;
                } else {
                    radiusValue = progress;
                }

                a.setText(angleValue / 30 + "");
                set.constrainCircle(a.getId(), anchor.getId(), radiusValue, angleValue);
                set.applyTo(item);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };

        angle.setOnSeekBarChangeListener(changeListener);
        radius.setOnSeekBarChangeListener(changeListener);

        return container;
    }

    private View obtainDimensionsConstraints() {
        final View container = inflate(4);
        final ConstraintLayout item = container.findViewById(R.id.parent);
        final Button a = item.findViewById(R.id.a);
        final RadioGroup groupW = container.findViewById(R.id.group_w);
        final RadioGroup groupH = container.findViewById(R.id.group_h);
        groupW.check(groupW.getChildAt(1).getId());
        groupH.check(groupH.getChildAt(1).getId());

        final ConstraintSet set = new ConstraintSet();
        set.clone(item);

        groupW.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int index = group.indexOfChild(group.findViewById(checkedId));
                if (index == 0) {
                    set.constrainWidth(a.getId(), ConstraintLayout.LayoutParams.MATCH_PARENT);
                } else if (index == 1) {
                    set.constrainWidth(a.getId(), ConstraintLayout.LayoutParams.WRAP_CONTENT);
                } else if (index == 2) {
                    set.constrainWidth(a.getId(), 0);
                }

                TransitionManager.beginDelayedTransition(item);
                set.applyTo(item);
            }
        });

        groupH.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int index = group.indexOfChild(group.findViewById(checkedId));
                if (index == 0) {
                    set.constrainHeight(a.getId(), ConstraintLayout.LayoutParams.MATCH_PARENT);
                } else if (index == 1) {
                    set.constrainHeight(a.getId(), ConstraintLayout.LayoutParams.WRAP_CONTENT);
                } else if (index == 2) {
                    set.constrainHeight(a.getId(), 0);
                }

                TransitionManager.beginDelayedTransition(item);
                set.applyTo(item);
            }
        });

        return container;
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
                case 4:
                    item = obtainDimensionsConstraints();
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
