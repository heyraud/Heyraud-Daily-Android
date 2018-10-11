package io.github.thismj.android.main;

import android.support.annotation.StringRes;

import java.util.ArrayList;
import java.util.List;

import io.github.thismj.android.BasicActivity;
import io.github.thismj.android.R;
import io.github.thismj.android.binder.BinderActivity;
import io.github.thismj.android.samples.constraint_layout.ConstraintLayoutActivity;
import io.github.thismj.android.samples.document_provider.DocumentProviderActivity;
import io.github.thismj.android.samples.recycler_view.RecyclerViewActivity;
import io.github.thismj.android.samples.view_pager.ViewPagerActivity;
import io.github.thismj.android.samples.window.WindowActivity;

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
    private static void addEntity(@StringRes int name, Class<? extends BasicActivity> destination) {
        Entities.add(new MainEntity(name, destination));
    }

    static {
        //------------------------------------------------------------------------------------------//
        addEntity(R.string.title_binder, BinderActivity.class);

        //------------------------------------------------------------------------------------------//
        addEntity(R.string.title_window, WindowActivity.class);

        //------------------------------------------------------------------------------------------//
        addEntity(R.string.title_constraint_layout, ConstraintLayoutActivity.class);

        //------------------------------------------------------------------------------------------//
        addEntity(R.string.title_recycler_layout, RecyclerViewActivity.class);

        //------------------------------------------------------------------------------------------//
        addEntity(R.string.title_view_pager, ViewPagerActivity.class);

        //------------------------------------------------------------------------------------------//
        addEntity(R.string.title_document_provider, DocumentProviderActivity.class);
    }
}
