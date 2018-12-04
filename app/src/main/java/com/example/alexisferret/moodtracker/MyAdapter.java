package com.example.alexisferret.moodtracker;

import android.app.AlertDialog;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


        private final List<Pair<String, String>> moods = Arrays.asList(
                Pair.create("Il y a une semaine",""),
                Pair.create("Il y a 6 jours",""),
                Pair.create("Il y a 5 jours",""),
                Pair.create("Il y a 4 jours",""),
                Pair.create("Il y a 3 jours",""),
                Pair.create("Avant-hier",""),
                Pair.create("Hier","")
        );

        @Override
        public int getItemCount() {
            return moods.size();
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                        View view = inflater.inflate(R.layout.list_cells, parent, false);
                        return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
                        Pair<String, String> pair = moods.get(position);
                        holder.display(pair);
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

                private final TextView date;

                private Pair<String, String> currentPair;

                public MyViewHolder(final View itemView) {
                        super(itemView);

                        date = ((TextView) itemView.findViewById(R.id.date));

                        itemView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                        new AlertDialog.Builder(itemView.getContext())
                                                        .setTitle(currentPair.first)
                                                        .setMessage(currentPair.second)
                                                        .show();
                                        }
                                });
                        }

                        public void display(Pair<String, String> pair) {
                                currentPair = pair;
                                date.setText(pair.first);
                        }
                }

        }
