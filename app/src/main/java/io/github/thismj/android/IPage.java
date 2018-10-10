package io.github.thismj.android;

import android.support.annotation.LayoutRes;
import android.view.View;

/**
 * 描述
 *
 * @author aero.tang
 * @version 2018/9/26 16:14
 */
public interface IPage {
    @LayoutRes
    int getContent();

    void initComponent(View view);
}
