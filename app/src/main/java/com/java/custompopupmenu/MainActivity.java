package com.java.custompopupmenu;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;
import com.java.custompopupmenu.genericclass.CustomPopupMenu;
import com.java.custompopupmenu.model.PopupItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ImageView menuOne;
    private ImageView menuTwo;
    private ImageView menuThree;
    private ImageView menuFour;
    private ImageView menuFive;
    private List<PopupItem> popupItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeView();
        setData();
        onClickListener();

    }

    private void initializeView() {
        menuOne = findViewById(R.id.menuIcon1);
        menuTwo = findViewById(R.id.menuIcon2);
        menuThree = findViewById(R.id.menuIcon3);
        menuFour = findViewById(R.id.menuIcon4);
        menuFive = findViewById(R.id.menuIcon5);
    }

    private void setData() {
        popupItems = new ArrayList<>();
        popupItems.add(new PopupItem("1", "Home", R.drawable.ic_home));
        popupItems.add(new PopupItem("2", "Setting", R.drawable.ic_settings));
        popupItems.add(new PopupItem("3", "Call", R.drawable.ic_call));
        popupItems.add(new PopupItem("4", "Log Out", R.drawable.ic_power));
    }

    private void onClickListener() {
        menuOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNestedPopupMenu();
            }
        });

        menuTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomPopupMenu customPopupMenu = new CustomPopupMenu(MainActivity.this,menuTwo,popupItems);
                customPopupMenu.showPopupMenu();
            }
        });

        menuThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        menuFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        menuFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private void showNestedPopupMenu() {
//        Creating the instance of PopupMenu
        PopupMenu popup = new PopupMenu(MainActivity.this, menuOne);

//        Inflating the Popup using xml file
        popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

//        registering popup with OnMenuItemClickListener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(MainActivity.this, "You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();return true;
            }
        });

//        showing popup menu
        popup.show();
    }

}
