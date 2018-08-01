package io.github.heyraud.daily.android;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述
 *
 * @author aero.tang
 * @version 2018/8/1 23:27
 */
public class MainEntities {

    public static List<Entity> sData = new ArrayList<>();

    static {

    }

    public enum Entity {
        SYSTEM(R.string.title_system, R.drawable.ic_launcher_foreground, Entity.SYSTEM);

        public int name;
        public int icon;

        Entity(@StringRes int name, @DrawableRes int icon, Entity... children) {
            this.name = name;
            this.icon = icon;
        }
    }
}
