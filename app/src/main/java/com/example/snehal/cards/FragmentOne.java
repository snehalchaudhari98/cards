package com.example.snehal.cards;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOne extends Fragment {
    GridLayout maingrid;

    public FragmentOne() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment

      //  maingrid=(GridLayout)getView().findViewById(R.id.maingrid) ;

       // maingrid=(GridLayout)findViewById(R.id.maingrid);

        View view =inflater.inflate(R.layout.fragment_fragment_one,container,false);
        maingrid=(GridLayout)view.findViewById(R.id.maingrid);

        setsingleEvent(maingrid);

        return inflater.inflate(R.layout.fragment_fragment_one, container, false);
    }


    private void setsingleEvent(GridLayout maingrid) {

        for(int i=0;i<maingrid.getChildCount();i++){
            CardView cardView=(CardView)maingrid.getChildAt(i);
            final int a=i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //   Toast.makeText(gridview.this,"you just added ::"+a,Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getActivity(),CropEntry.class);
                    startActivity(intent);

                }
            });

        }
    }

}
