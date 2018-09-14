package com.example.snehal.cards;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
//import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

public class productadapter extends RecyclerView.Adapter<productadapter.productviewholder>{

    private Context mctx;
    private List<product> productList;


    public productadapter(Context mctx, List<product> productList) {
        this.mctx = mctx;
        this.productList = productList;
    }

    @NonNull
    @Override
    public productviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(mctx);
        View view=inflater.inflate(R.layout.card_items,null);
        productviewholder holder =new productviewholder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull productviewholder productviewholder, int i) {
        product product=productList.get(i);

        productviewholder.n.setText("N :: "+String.valueOf(product.getN())+"%");
        productviewholder.k.setText("K :: "+String.valueOf(product.getK())+"%");
        productviewholder.p.setText("P :: "+String.valueOf(product.getP())+"%");
        productviewholder.title.setText(product.getCrop());
        productviewholder.imageView.setImageDrawable(mctx.getResources().getDrawable(product.getImage()));
       // productviewholder.progressBar.setProgress(product.getProgress());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class productviewholder extends RecyclerView.ViewHolder{

        ImageView imageView;
        //ProgressBar progressBar;
        TextView n,p,k,title;



        public productviewholder(@NonNull View itemView) {
            super(itemView);
        imageView=itemView.findViewById(R.id.thumbnail);
        n=itemView.findViewById(R.id.nn);
        p=itemView.findViewById(R.id.pp);
        k=itemView.findViewById(R.id.kk);
        title=itemView.findViewById(R.id.title);
       // progressBar=itemView.findViewById(R.id.progressBar);

        }


    }

}
