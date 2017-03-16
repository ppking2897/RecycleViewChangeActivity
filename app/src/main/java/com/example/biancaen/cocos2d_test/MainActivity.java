package com.example.biancaen.cocos2d_test;


import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Slide;
import android.transition.Transition;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;


public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private Place place;
    private Person_place person_place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        setContentView(R.layout.activity_main);
        place = new Place();
        person_place = new Person_place();

        //初始fragment畫面呈現
        getFragmentManager().beginTransaction().replace(R.id.fragmentChange,place).commit();



        Transition exitTrans = new Explode();
        getWindow().setExitTransition(exitTrans);

        Transition reenterTrans = new Explode();
        getWindow().setReenterTransition(reenterTrans);

        //------頂部菜單------------
        toolbar = (Toolbar)findViewById(R.id.toolBar);
//        toolbar.setTitle("Title");
//        toolbar.setSubtitle("Title");
        toolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.string.setting:
                        //TODO 通知設定的介面還沒用
                        Log.v("ppking" , " 通知設定 ");
                        break;
                }
                return false;
            }
        });
        //--------------------------

        //底部菜單
        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);

        //通知顯示
        BadgeItem badgeItem = new BadgeItem().setBorderWidth(1).setBackgroundColor(Color.RED).setText("NEW").setHideOnSelect(false);

        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_place_black_24dp, "Place").setBadgeItem(badgeItem))
                .addItem(new BottomNavigationItem(R.drawable.ic_person_pin_circle_black_24dp, "PersonPlace"))
                .addItem(new BottomNavigationItem(R.drawable.ic_ondemand_video_black_24dp, "Video"))
                .initialise();



        //bottombar觸發按鍵事件，並依照按下的position進入不同的fragment畫面呈現
        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                Log.v("ppking" , "onTabSelected" + position);

                switch (position){
                    case 0:
                        getFragmentManager().beginTransaction().replace(R.id.fragmentChange,place).commit();
                        break;
                    case 1:
                        getFragmentManager().beginTransaction().replace(R.id.fragmentChange,person_place).commit();
                        break;
                }

            }

            @Override
            public void onTabUnselected(int position) {
                Log.v("ppking" , "onTabUnselected" + position);
            }

            @Override
            public void onTabReselected(int position) {
                Log.v("ppking" , "onTabReselected" + position);
            }
        });


    }


    //頂部將menu的layout給浮印上去
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main , menu);
        return super.onCreateOptionsMenu(menu);
    }

}
