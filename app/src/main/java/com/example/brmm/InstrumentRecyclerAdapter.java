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

public class InstrumentRecyclerAdapter extends RecyclerView.Adapter<InstrumentRecyclerAdapter.ViewHolder> {

    private ArrayList<Instrument> ins_List;

    public InstrumentRecyclerAdapter(ArrayList<Instrument> modelList) {
        ins_List = modelList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.ins_list_rview, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Instrument ins = ins_List.get(position);
        TextView name_textview = holder.name_textview;
        TextView id_textview = holder.id_textview;
        TextView cost_textview = holder.cost_textview;
        TextView section_textview= holder.section_textview;
        TextView owner_textview = holder.owner_textview;


        name_textview.setText(ins.getName());
        id_textview.setText(String.format(Locale.US,"%d",ins.getId()));
        cost_textview.setText("$" + String.format(Locale.US,"%.2f",ins.getCost()));
        section_textview.setText(ins.getSection());
        owner_textview.setText(ins.getCurrentOwner());
    }

    @Override
    public int getItemCount() {
        return ins_List == null ? 0 : ins_List.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private View view;
        TextView name_textview;
        TextView id_textview;
        TextView cost_textview;
        TextView section_textview;
        TextView owner_textview;


        private ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            name_textview = (TextView) itemView.findViewById(R.id.name_ins_rv_textview);
            id_textview = (TextView) itemView.findViewById(R.id.id_ins_rv_textview);
            cost_textview = (TextView) itemView.findViewById(R.id.cost_ins_rv_textview);
            section_textview = (TextView) itemView.findViewById(R.id.section_ins_rv_textview);
            owner_textview = (TextView) itemView.findViewById(R.id.owner_ins_rv_textview);
        }

    }

}
