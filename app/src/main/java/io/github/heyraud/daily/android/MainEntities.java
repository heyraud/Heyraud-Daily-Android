package io.github.heyraud.daily.android;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据列表
 *
 * @author aero.tang
 * @version 2018/8/1 23:27
 */
public class MainEntities {

    public static List<MainEntity> Entities = new ArrayList<>();

    /**
     * add sample
     */
    private static void addEntity(@StringRes int name, @DrawableRes int icon) {
        Entities.add(new MainEntity(name, icon));
    }

    static {
        addEntity(R.string.title_recycler_view, R.drawable.ic_launcher_foreground);
        addEntity(R.string.title_window_manager, R.drawable.ic_launcher_foreground);
    }
}
