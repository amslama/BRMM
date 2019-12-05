package com.example.brmm;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MemberRecyclerAdapter extends RecyclerView.Adapter<MemberRecyclerAdapter.ViewHolder> {

    private ArrayList<BandMember> memberList;

    public MemberRecyclerAdapter(ArrayList<BandMember> modelList) {
        memberList = modelList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.member_list_rview, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final BandMember member = memberList.get(position);
        if (member instanceof Student) {
            TextView fname_textview = holder.fname_textview;
            TextView lname_textview = holder.lname_textview;
            TextView type_textview = holder.type_textview;
            TextView UID_textview = holder.UID_textview;
            TextView section_textview = holder.section_textview;
            TextView instrument_textview = holder.instrument_textview;


            fname_textview.setText(member.getFname());
            lname_textview.setText(member.getLname());
            type_textview.setText("Student");
            UID_textview.setText(member.getUID());
            section_textview.setText(((Student) member).getSection());
            instrument_textview.setText(((Student) member).getInstruments().getName());

        } else {
            TextView fname_textview = holder.fname_textview;
            TextView lname_textview = holder.lname_textview;
            TextView type_textview = holder.type_textview;
            TextView UID_textview = holder.UID_textview;

            fname_textview.setText(member.getFname());
            lname_textview.setText(member.getLname());
            type_textview.setText("Faculty");
            UID_textview.setText(member.getUID());
        }
    }

    @Override
    public int getItemCount() {
        return memberList == null ? 0 : memberList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private View view;
        private TextView fname_textview;
        private TextView lname_textview;
        private TextView type_textview;
        private TextView UID_textview;
        private TextView section_textview;
        private TextView instrument_textview;


        private ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            fname_textview = (TextView) itemView.findViewById(R.id.fname_bm_rv_textview);
            fname_textview = (TextView) itemView.findViewById(R.id.lname_bm_rv_textview);
            fname_textview = (TextView) itemView.findViewById(R.id.type_bm_rv_textview);
            fname_textview = (TextView) itemView.findViewById(R.id.UID_bm_rv_textview);
            fname_textview = (TextView) itemView.findViewById(R.id.section_bm_rv_textview);
            fname_textview = (TextView) itemView.findViewById(R.id.instrument_bm_rv_textview);
        }


    }
}
