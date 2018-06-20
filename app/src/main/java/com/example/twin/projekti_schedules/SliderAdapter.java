package com.example.twin.projekti_schedules;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Edisa on 6/17/2018.
 */

public class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context=context;
    }

    //arrays

    public int[] slide_images={
            R.mipmap.add,
            R.mipmap.calendar,
            R.mipmap.stats

    };
    public String[] slide_headings={
      "ADD NEW ACTIVITY",
            "TODAY'S SCHEDULE",
            "STATISTICS"
    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==(RelativeLayout) object;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        layoutInflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.slide_layout, container, false);
        ImageView slideImageView=(ImageView)view.findViewById(R.id.slide_image);
        TextView slideHeading=(TextView)view.findViewById(R.id.slide_heading);
        slideImageView.setImageResource(slide_images[position]);
        slideImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                switch (position){
                    case 0:
                        intent= new Intent(view.getContext(),AddActivity.class);
                        break;
                    case 1:
                        intent= new Intent(view.getContext(), ViewActivity.class);
                        break;
                    case 2:
                        intent=new Intent(view.getContext(),StaticticsActivity.class);
                        break;
                    default:
                        intent =  new Intent(view.getContext(), AddActivity.class);
                        break;
                }
                view.getContext().startActivity(intent);

            }
        });
        slideHeading.setText(slide_headings[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
