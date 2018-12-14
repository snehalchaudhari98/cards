package com.example.snehal.cards;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.util.List;

/**
 * Created by csa on 3/1/2017.
 */

class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.Myholder> {
    List<DataModel> dataModelArrayList;

    public RecycleAdapter(List<DataModel> dataModelArrayList) {
        this.dataModelArrayList = dataModelArrayList;
    }

    class Myholder extends RecyclerView.ViewHolder{
        TextView SSArea,SSDate,SSDeadline;

        public Myholder(View itemView) {
            super(itemView);

            SSArea = (TextView) itemView.findViewById(R.id.sugarcaneitemarea);
            SSDate = (TextView) itemView.findViewById(R.id.sugarcaneitemdate);
            SSDeadline = (TextView) itemView.findViewById(R.id.deadline);

        }
    }


    @Override
    public Myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview,null);
        return new Myholder(view);

    }

    @Override
    public void onBindViewHolder(Myholder holder, int position) {
        DataModel dataModel=dataModelArrayList.get(position);
        holder.SSArea.setText(dataModel.getSuagarArea());
        holder.SSDate.setText(dataModel.getSuargardate());
        try {
            holder.SSDeadline.setText(dataModel.getDeadlinedate());
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

}