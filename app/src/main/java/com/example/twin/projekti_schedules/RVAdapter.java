package com.example.twin.projekti_schedules;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;
import android.content.Context;


import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder> {

    public static class PersonViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView name;
        ImageView photo;
        Context context;

        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            name = (TextView)itemView.findViewById(R.id.name);
            photo = (ImageView)itemView.findViewById(R.id.photo);
        }
    }





    List<Aktiviteti> activities;
    RVAdapter(List<Aktiviteti> activities){
        this.activities=activities;
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_item_faqjakryesore, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final PersonViewHolder personViewHolder, final int i) {
        personViewHolder.name.setText(activities.get(i).name);
        personViewHolder.photo.setImageResource(activities.get(i).photoid);
        personViewHolder.photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  final Intent intent;
                switch (i){
                    case 0:
                        intent =  new Intent(personViewHolder.itemView.getContext(), AddActivity.class);
                        break;

                    case 1:
                        intent =  new Intent(personViewHolder.itemView.getContext(), AddActivity.class);
                        break;
                    default:
                        intent =  new Intent(personViewHolder.itemView.getContext(), AddActivity.class);
                        break;
                }


                personViewHolder.itemView.getContext().startActivity(intent);




            }
        });
    }

    @Override
    public int getItemCount() {
        return activities.size();
    }
}
