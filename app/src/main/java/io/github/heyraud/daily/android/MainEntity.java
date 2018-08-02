package io.github.heyraud.daily.android;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

/**
 * 描述
 *
 * @author aero.tang
 * @version 2018/8/2 23:55
 */
public class MainEntity {
    @StringRes
    public int name;
    @DrawableRes
    public int icon;

    public MainEntity(@StringRes int name, @DrawableRes int icon) {
        this.name = name;
        this.icon = icon;
    }
}
