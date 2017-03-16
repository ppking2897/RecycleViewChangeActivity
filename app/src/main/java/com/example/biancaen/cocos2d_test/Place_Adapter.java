package com.example.biancaen.cocos2d_test;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by BiancaEN on 2017/3/10.
 */
//RecyclerView.Adapter<Place_Adapter.ViewHolder> 先繼承recyclerview Adapter 再宣告所要的型別,而這型別由下面將viewholder建構式宣告我們要的
public class Place_Adapter extends RecyclerView.Adapter<Place_Adapter.ViewHolder> {
    private Context context;
    private ArrayList<String> placeWeather;




    public Place_Adapter(Context context , ArrayList<String> placeWeather){

        this.context = context;
        this.placeWeather = placeWeather;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.place_recycleview,parent,false);
        final ViewHolder holder = new ViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                int position = holder.getAdapterPosition();
                switch (position){
                    case 0:
                        Log.v("ppking" , " position  " + position);
                        Intent it = new Intent(context , NorthLocation.class);
                        View textView = holder.textView;
                        String transitionName = "transitionNorthLocation";
                        //取得position後在設定intent跳轉的地方，在設定跳轉過後的動畫，一起跳轉的view
                        ActivityOptions transitionActivityOptions =
                                ActivityOptions.makeSceneTransitionAnimation((MainActivity)context , textView , transitionName);
                        context.startActivity(it ,transitionActivityOptions.toBundle());
                }

            }

        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(placeWeather.get(position));
    }


    @Override
    public int getItemCount() {
        return placeWeather.size();
    }
    //重新定義Viewholder的建構式，將recyclerview內所需要的物件給定義出來
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.place_weather_Text);


        }
    }






}