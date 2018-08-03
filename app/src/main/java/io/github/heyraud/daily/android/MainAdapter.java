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
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<MainEntity> entities = MainEntities.Entities;

    private static final int ITEM_VIEW_TYPE_HEADER = R.layout.item_main_header;
    private static final int ITEM_VIEW_TYPE_ENTITY = R.layout.item_main_entity;
    private static final int ITEM_VIEW_TYPE_FOOTER = R.layout.item_main_footer;

    public MainAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        int header = 0;
        int footer = CollectionUtil.size(entities) + 1;
        if (position == header) {
            return ITEM_VIEW_TYPE_HEADER;
        } else if (position == footer) {
            return ITEM_VIEW_TYPE_FOOTER;
        } else {
            return ITEM_VIEW_TYPE_ENTITY;
        }
    }

    @NonNull
    @Override
    public MainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = inflater.inflate(viewType, parent, false);
        switch (viewType) {
            case ITEM_VIEW_TYPE_HEADER:
                return new HeaderMainHolder(item);
            case ITEM_VIEW_TYPE_FOOTER:
                return new FooterMainHolder(item);
            case ITEM_VIEW_TYPE_ENTITY:
                return new EntityMainHolder(item);
            default:
                throw new IllegalArgumentException("");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MainHolder holder, int position) {
        if (position > 0 && position <= CollectionUtil.size(entities)) {
            holder.bind(position, entities.get(position - 1));
        } else {
            holder.bind();
        }
    }

    @Override
    public int getItemCount() {
        int entitySize = CollectionUtil.size(entities);
        return entitySize == 0 ? 0 : entitySize + 2;
    }

    private class HeaderMainHolder extends MainHolder {
        private HeaderMainHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bind() {
            super.bind();
        }
    }

    private class FooterMainHolder extends MainHolder {
        private FooterMainHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bind() {
            super.bind();
        }
    }

    private class EntityMainHolder extends MainHolder {
        final ImageView icon;
        final TextView name;

        private EntityMainHolder(View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.iv_main_icon);
            name = itemView.findViewById(R.id.iv_main_name);
        }

        @Override
        public void bind(int position, MainEntity entity) {
            icon.setImageResource(entity.icon);
            name.setText(entity.name);
        }
    }

    public abstract class MainHolder extends RecyclerView.ViewHolder {

        private MainHolder(View itemView) {
            super(itemView);
        }

        public void bind(int position, MainEntity entity) {

        }

        public void bind() {

        }
    }
}
