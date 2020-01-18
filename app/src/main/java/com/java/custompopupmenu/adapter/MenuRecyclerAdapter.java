package com.java.custompopupmenu.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.java.custompopupmenu.R;
import com.java.custompopupmenu.model.PopupItem;

import java.util.List;

public class MenuRecyclerAdapter extends RecyclerView.Adapter<MenuRecyclerAdapter.MenuRecyclerViewHolder> {

    private Activity activity;
    private List<PopupItem> popupItems;
    private boolean activeCardView = false;

    public MenuRecyclerAdapter(Activity activity, List<PopupItem> popupItems) {
        this.activity = activity;
        this.popupItems = popupItems;
    }

    public MenuRecyclerAdapter(Activity activity, List<PopupItem> popupItems, boolean activeCardView) {
        this.activity = activity;
        this.popupItems = popupItems;
        this.activeCardView = activeCardView;
    }

    @NonNull
    @Override
    public MenuRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        if (activeCardView) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_layout_with_card, parent, false);
        } else {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_layout, parent, false);
        }
        return new MenuRecyclerAdapter.MenuRecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuRecyclerViewHolder holder, int position) {
        PopupItem item = popupItems.get(position);

        if (item.getItemName() != null) {
            holder.mName.setText(item.getItemName());
        }

        holder.mImage.setImageResource(item.getItemIcon());

        if (!activeCardView) {
            if (position == popupItems.size() - 1) {
                holder.mBorder.setVisibility(View.GONE);
            } else {
                holder.mBorder.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return popupItems.size();
    }

    class MenuRecyclerViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImage;
        private TextView mName;
        private TextView mBorder;


        MenuRecyclerViewHolder(View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.image);
            mName = itemView.findViewById(R.id.text);
            if(!activeCardView) {
                mBorder = itemView.findViewById(R.id.border);
            }
        }
    }

}
