package com.example.biancaen.cocos2d_test;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Adapter;

import java.util.ArrayList;

/**
 * Created by BiancaEN on 2017/3/7.
 */

public class Place extends Fragment {
    private RecyclerView place_RecyclerView;
    private Place_Adapter place_adapter;
    private ArrayList<String> placeWeather;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        placeWeather = new ArrayList<>();
        placeWeather.add("北部");
        placeWeather.add("中部");
        placeWeather.add("南部");
        placeWeather.add("東部");
        placeWeather.add("外島");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.place_layout, container , false);

        place_RecyclerView = (RecyclerView)view.findViewById(R.id.place_RecyclerView);

        place_adapter = new Place_Adapter(getActivity(), placeWeather);

        place_RecyclerView.setLayoutManager(new GridLayoutManager(getActivity() , 1));
        place_RecyclerView.setAdapter(place_adapter);


        return view;
    }


}
