package io.github.heyraud.daily.android;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import io.github.heyraud.daily.android.utils.CollectionUtil;

/**
 * 描述
 *
 * @author aero.tang
 * @version 2018/8/3 00:10
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.EntityMainHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<MainEntity> entities = MainEntities.Entities;

    private static final int ITEM_VIEW_TYPE_ENTITY = R.layout.item_main_entity;

    MainAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        return ITEM_VIEW_TYPE_ENTITY;
    }

    @NonNull
    @Override
    public EntityMainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = inflater.inflate(viewType, parent, false);
        return new EntityMainHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull EntityMainHolder holder, int position) {
        holder.bind(position, entities.get(position));
    }

    @Override
    public int getItemCount() {
        return CollectionUtil.size(entities);
    }

    class EntityMainHolder extends RecyclerView.ViewHolder {
        final ImageView icon;
        final TextView name;

        private EntityMainHolder(View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.iv_main_icon);
            name = itemView.findViewById(R.id.iv_main_name);
        }

        private void bind(int position, MainEntity entity) {
            icon.setImageResource(entity.icon);
            name.setText(entity.name);
        }
    }
}
