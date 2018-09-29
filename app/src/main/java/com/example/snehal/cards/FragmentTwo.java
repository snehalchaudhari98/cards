package com.example.snehal.cards;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTwo extends Fragment {

    RecyclerView recyclerView;
    productadapter adapter;
    List<product> productList;
    ViewGroup.LayoutParams layoutParams;

//GridLayout maingrid;

    Button next;

    public FragmentTwo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =inflater.inflate(R.layout.fragment_fragment_two,container,false);

        //next=(Button)view.findViewById(R.id.next);


        productList=new ArrayList<>();
        productList.add(
                new product(10,10, 88,"Wheat",R.drawable.crop1));
        productList.add(
                new product(58,24, 75,"Corn",R.drawable.c6));

        productList.add(
                new product(38,17, 64,"Rice",R.drawable.c5));
        productList.add(
                new product(98,12, 57,"Cotton",R.drawable.c4));

        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerView);
        //layoutParams=new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        adapter=new productadapter(getContext(),productList);
        recyclerView.setAdapter(adapter);


       /* next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //maingrid=(GridLayout)findViewById(R.id.maingrid);
                //setsingleEvent(maingrid);

                Toast.makeText(getActivity(),"Going to next page",Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(getContext(),gridview.class);
                startActivity(intent);

            }
        });*/


        return inflater.inflate(R.layout.fragment_fragment_two, container, false);
    }

}
