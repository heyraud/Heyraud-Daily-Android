package io.github.thismj.android.samples.view_pager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import io.github.thismj.android.BasicFragment;
import io.github.heyraud.daily.android.R;

/**
 * ViewPager Sample
 *
 * @author aero.tang
 * @version 2018/9/26 16:11
 */
public class ViewPagerFragment extends BasicFragment {

    TextView mText;

    public static ViewPagerFragment newInstance(int position) {
        ViewPagerFragment fragment = new ViewPagerFragment();
        Bundle argument = new Bundle();
        argument.putInt("position", position);
        fragment.setArguments(argument);
        return fragment;
    }

    @Override
    public int getContent() {
        return R.layout.fragment_view_pager;
    }

    @Override
    public void initComponent(View view) {
        mText = view.findViewById(R.id.text);
        Bundle argument = getArguments();
        if (argument != null) {
            int position = argument.getInt("position");
            mText.setText(String.valueOf(position));
        }

    }
}
