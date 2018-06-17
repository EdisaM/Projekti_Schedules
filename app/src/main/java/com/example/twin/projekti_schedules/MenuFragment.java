package com.example.twin.projekti_schedules;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import static com.example.twin.projekti_schedules.MenuActivity.menuFragment;

/**
 * Created by Admin on 31-05-2017.
 */

public class MenuFragment extends Fragment implements View.OnTouchListener {
    public static boolean fragment=MenuActivity.isFragmentLoaded;

    GestureDetector gestureDetector;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.menudesign, container, false);
        LinearLayout root = (LinearLayout) rootView.findViewById(R.id.rootLayout);

        /*gestureDetector=new GestureDetector(getActivity(),new OnSwipeListener(){
            @Override
            public boolean onSwipe(Direction direction) {
                if (direction==Direction.up){
                    //do your stuff
                    ((MainActivity )  getActivity()).hideFragment();
                }
                if (direction==Direction.down){
                    //do your stuff
                }
                return true;
            }
        });
        root.setOnTouchListener(this);*/



        LinearLayout task=(LinearLayout)rootView.findViewById(R.id.task);
        LinearLayout motication=(LinearLayout)rootView.findViewById(R.id.motication);
        LinearLayout help=(LinearLayout)rootView.findViewById(R.id.help);
        LinearLayout settings=(LinearLayout)rootView.findViewById(R.id.settings);
        LinearLayout statistics=(LinearLayout)rootView.findViewById(R.id.statistics);
        LinearLayout logout=(LinearLayout)rootView.findViewById(R.id.logout);
        task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent i = new Intent(MenuActivity.this,.class);
                //startActivity(i);
            }
        });
        motication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment=false;
                Intent i = new Intent(((MenuActivity )  getActivity()), motivation.class);
                startActivity(i);
            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment=false;
                Intent i = new Intent(getActivity(), aboutus.class);
                startActivity(i);
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getActivity(), Settings.class);
                startActivity(i);
                fragment=false;

            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment=false;

                PreferenceUtils.savePassword(null, (getActivity()));
                PreferenceUtils.saveEmail(null,   getActivity());
                Intent intent1 = new Intent(  getActivity(), LoginActivity.class);
                startActivity(intent1);
                getActivity().finish();




            }
        });
        statistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment=false;

            }
        });


        return rootView;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return true;
    }
}