package com.example.snehal.cards;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentThree extends Fragment {


    public FragmentThree() {
        // Required empty public constructor
    }


    DatabaseHelper myDb;
    EditText sugarcaneSowingDate,sugarcaneSowingArea;
    Button sugarcaneEnter,btnviewAll;

    private DatePickerDialog.OnDateSetListener mDateSetListener;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =inflater.inflate(R.layout.fragment_fragment_two,container,false);

        myDb = new DatabaseHelper(getContext());

        sugarcaneSowingDate = (EditText)view.findViewById(R.id.sugarcanesowingdate);
        sugarcaneSowingArea = (EditText)view.findViewById(R.id.suagarcanesowingarea);
        sugarcaneEnter = (Button)view.findViewById(R.id.sugarcanesowingenter);
        btnviewAll = (Button) view.findViewById(R.id.button_viewAll);


            sugarcaneEnter.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            boolean isInserted = myDb.insertData(sugarcaneSowingDate.getText().toString(),sugarcaneSowingArea.getText().toString());
                            sugarcaneSowingArea.setText("");
                            sugarcaneSowingDate.setText("");
                            if(isInserted ==true)
                            {
                                Toast.makeText(getContext(), "Data  Inserted", Toast.LENGTH_LONG).show();

                            }
                            else {
                                Toast.makeText(getContext(), "Oopps!!Data not Inserted", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
            );



        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(getContext(),viewall.class);
                        startActivity(intent);
                    }
                }
        );

        sugarcaneSowingDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c=Calendar.getInstance();
                int month=c.get(Calendar.MONTH);
                int day=c.get(Calendar.DAY_OF_MONTH);
                int year=c.get(Calendar.YEAR);

                DatePickerDialog dialog=new DatePickerDialog(getContext(),android.R.style.Theme_Holo_Light_Dialog_MinWidth,mDateSetListener,year,month,day);
                dialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });
        mDateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                String date=dayOfMonth+"/"+month+"/"+year;
                sugarcaneSowingDate.setText(date);
            }
        };



        return inflater.inflate(R.layout.fragment_fragment_three, container, false);
    }





    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }



}
