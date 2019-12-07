package com.example.brmm;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class PartRecyclerAdapter extends RecyclerView.Adapter<PartRecyclerAdapter.ViewHolder> {

    private ArrayList<Part> partList;

    public PartRecyclerAdapter(ArrayList<Part> modelList) {
        partList = modelList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.part_list_rview, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Part part = partList.get(position);
        TextView name_textview = holder.name_textview;
        TextView cost_textview = holder.cost_textview;
        TextView sn_textview = holder.sn_textview;

        name_textview.setText(part.getName());
        cost_textview.setText("$" + String.format(Locale.US,"%.2f",part.getCost()));
        sn_textview.setText(part.getSerialNumber());


    }

    @Override
    public int getItemCount() {
        return partList == null ? 0 : partList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private View view;
        private TextView name_textview;
        private TextView cost_textview;
        private TextView sn_textview;


        private ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            name_textview = itemView.findViewById(R.id.name_part_rv_textview);
            cost_textview = itemView.findViewById(R.id.cost_part_rv_textview);
            sn_textview = itemView.findViewById(R.id.sn_part_rv_textview);
        }
    }
}
