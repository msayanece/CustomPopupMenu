package com.java.custompopupmenu.genericclass;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.java.custompopupmenu.R;
import com.java.custompopupmenu.adapter.MenuRecyclerAdapter;
import com.java.custompopupmenu.model.PopupItem;

import java.util.List;

public class CustomPopupMenu {

    private Activity activity;
    private RecyclerView mMenuRecycler;
    private MenuRecyclerAdapter adapter;
    private List<PopupItem> popupItems;
    private PopupWindow popupWindow;
    private ImageView menuIcon;

    public CustomPopupMenu(Activity activity, ImageView menuIcon, List<PopupItem> popupItems) {
        this.activity = activity;
        this.menuIcon = menuIcon;
        this.popupItems = popupItems;
    }

    public void showPopupMenu(){
        popupWindow = generatePopupMenuWithIcon();
        popupWindow.showAsDropDown(menuIcon, -40, 18); // where u want show on view click event popupWindow.showAsDropDown(view, x, y);
    }

    public PopupWindow generatePopupMenuWithIcon() {
        popupWindow = new PopupWindow(activity);
//        inflate your layout or dynamically add view
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.popup_menu_list_layout, null);
        mMenuRecycler = view.findViewById(R.id.recyclerView);
        addRecyclerView();
        popupWindow.setFocusable(true);
        popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(view);
        popupWindow.setBackgroundDrawable(null);
        popupWindow.setTouchable(true);
        popupMenuOnClickListener();
        return popupWindow;
    }

    private void addRecyclerView() {
        mMenuRecycler.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        mMenuRecycler.setLayoutManager(linearLayoutManager);
        adapter = new MenuRecyclerAdapter(activity, popupItems);
        mMenuRecycler.setAdapter(adapter);
    }

    private void popupMenuOnClickListener() {
        mMenuRecycler.addOnItemTouchListener(new RecyclerItemClickListener(activity, mMenuRecycler, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(final View view, final int position, MotionEvent motionEvent) {
                Toast.makeText(activity,popupItems.get(position).getItemName(),Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
            }

            @Override
            public void onLongItemClick(View view, int position) {  }


        }));

    }

}
