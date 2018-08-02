package io.github.heyraud.daily.android;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import io.github.heyraud.daily.android.utils.CollectionUtil;

/**
 * 描述
 *
 * @author aero.tang
 * @version 2018/8/3 00:10
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainHolder> {

    private Context context;
    private List<MainEntity> entities = MainEntities.Entities;

    public MainAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public MainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MainHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return CollectionUtil.size(entities);
    }

    public class MainHolder extends RecyclerView.ViewHolder {

        public MainHolder(View itemView) {
            super(itemView);
        }
    }
}
