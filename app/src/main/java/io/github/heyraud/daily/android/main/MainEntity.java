package io.github.heyraud.daily.android.main;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import io.github.heyraud.daily.android.BasicActivity;

/**
 * 描述
 *
 * @author aero.tang
 * @version 2018/8/2 23:55
 */
public class MainEntity {
    @StringRes
    public int name;

    public Class<? extends BasicActivity> destination;

    public MainEntity(@StringRes int name, Class<? extends BasicActivity> destination) {
        this.name = name;
        this.destination = destination;
    }
}
