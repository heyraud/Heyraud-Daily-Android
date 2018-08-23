package io.github.heyraud.daily.android.main;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import java.util.ArrayList;
import java.util.List;

import io.github.heyraud.daily.android.BasicActivity;
import io.github.heyraud.daily.android.R;
import io.github.heyraud.daily.android.binder.BinderActivity;
import io.github.heyraud.daily.android.document.DocumentProviderActivity;
import io.github.heyraud.daily.android.window.WindowActivity;

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
    private static void addEntity(@StringRes int name, @DrawableRes int icon, Class<? extends BasicActivity> destination) {
        Entities.add(new MainEntity(name, icon, destination));
    }

    static {
        //------------------------------------------------------------------------------------------//
        addEntity(R.string.title_binder, R.drawable.ic_android_black_24dp, BinderActivity.class);

        //------------------------------------------------------------------------------------------//
        addEntity(R.string.title_window, R.drawable.ic_android_black_24dp, WindowActivity.class);

        //------------------------------------------------------------------------------------------//
        addEntity(R.string.title_document_provider, R.drawable.ic_android_black_24dp, DocumentProviderActivity.class);
    }
}
