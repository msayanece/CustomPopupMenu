package com.java.custompopupmenu.genericclass;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.java.custompopupmenu.R;
import com.java.custompopupmenu.adapter.MenuRecyclerAdapter;
import com.java.custompopupmenu.model.PopupItem;

import java.util.List;

public class CustomPopupMenu {
    private View view ;
    private Activity activity;
    private RecyclerView mMenuRecycler;
    private MenuRecyclerAdapter adapter;
    private List<PopupItem> popupItems;
    private boolean activeCardView = false;
    private PopupWindow popupWindow;
    private ImageView menuIcon;
    private static PopupMenuClickListener onClickListener;

    public CustomPopupMenu(Activity activity, ImageView menuIcon, List<PopupItem> popupItems) {
        this.activity = activity;
        this.menuIcon = menuIcon;
        this.popupItems = popupItems;
    }

    public CustomPopupMenu(Activity activity, ImageView menuIcon, List<PopupItem> popupItems, boolean activeCardView) {
        this.activity = activity;
        this.menuIcon = menuIcon;
        this.popupItems = popupItems;
        this.activeCardView = activeCardView;
    }

    public void showPopupMenu() {
        popupWindow = generatePopupMenuWithIcon();
        popupWindow.showAsDropDown(menuIcon, -40, 18); // where u want show on view click event popupWindow.showAsDropDown(view, x, y);
        popupMenuOnClickListener();
    }

    private PopupWindow generatePopupMenuWithIcon() {
        popupWindow = new PopupWindow(activity);
//        inflate your layout or dynamically add view
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) {
            if (activeCardView) {
                view = inflater.inflate(R.layout.popup_menu_list_layout, null);
            } else {
                view = inflater.inflate(R.layout.popup_menu_list_layout_wih_card, null);
            }
        }
        mMenuRecycler = view.findViewById(R.id.recyclerView);
        addRecyclerView();
        popupWindow.setFocusable(true);
        popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(view);
        popupWindow.setBackgroundDrawable(null);
        popupWindow.setTouchable(true);
        return popupWindow;
    }

    private void addRecyclerView() {
        mMenuRecycler.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        mMenuRecycler.setLayoutManager(linearLayoutManager);
        if (activeCardView) {
            adapter = new MenuRecyclerAdapter(activity, popupItems,true);
        }else{
            adapter = new MenuRecyclerAdapter(activity, popupItems);
        }
        mMenuRecycler.setAdapter(adapter);
    }

    private void popupMenuOnClickListener() {
        mMenuRecycler.addOnItemTouchListener(new RecyclerItemClickListener(activity, mMenuRecycler, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(final View view, final int position, MotionEvent motionEvent) {
                onClickListener.onClick(position);
                popupWindow.dismiss();
            }

            @Override
            public void onLongItemClick(View view, int position) {
            }

        }));

    }

    public interface PopupMenuClickListener {
        void onClick(int position);
    }

    public CustomPopupMenu setOnClickListener(PopupMenuClickListener onClickListener) {
        CustomPopupMenu.onClickListener = onClickListener;
        return this;
    }

}
