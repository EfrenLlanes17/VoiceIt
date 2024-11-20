package com.example.dinehero;





import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SavedAdapter extends RecyclerView.Adapter<SavedAdapter.ViewHolder>{

    private ArrayList<Product> products = new ArrayList<>();
    public static final String EXTRA_TEXT = "com.example.dinehero.example.EXTRA_TEXT";

    public static final String EXTRA_TEXT3 = "com.example.dinehero.example.EXTRA_TEXT3";

    public static final String EXTRA_TEXT7 = "com.example.dinehero.example.EXTRA_TEXT7";

    private Context context;


    public SavedAdapter(Context context) {
     this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.saved_list_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
       holder.txtName.setText(products.get(position).getProductName());
       holder.txtPrice.setText((products.get(position).getSignedUp()) + " / " + products.get(position).getMaxSignedUp() + " Attending");
       holder.txtSeller.setText(products.get(position).getLocation());
       holder.image.setImageDrawable(ContextCompat.getDrawable(context,products.get(position).getProductImage()));
       holder.txtPercentOff.setText(products.get(position).getDate());
       holder.parent.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               //Set Up producot Pages

               String name = products.get(position).getProductName();
               String URL = products.get(position).getProductImageURL();
               String sell = products.get(position).getProductSeller().getUsername().toString();

               //addHashtags(MainActivity2.findProduct(name,URL).getHashtagList());

               if(User.findUser() != null) {
                   if(User.findUser().isNotInViewedProducts(products.get(position))) {
                       User.findUser().addToViewedProducts(products.get(position));
                   }
                   for(int x = position-1; x >= 0; x--) {

                       if(User.findUser().isNotInViewedProducts(products.get(x))) {
                           User.findUser().addToViewedProducts(products.get(x));
                           notifyDataSetChanged();
                       }


                   }
               }

               openProductPage(name,URL,sell);


           }
       });


    }

    public void openProductPage(String text1,String text3,String text7){

        Intent intent = new Intent(context, ProductPage.class);
        intent.putExtra(EXTRA_TEXT,text1);

        intent.putExtra(EXTRA_TEXT3,text3);

        intent.putExtra(EXTRA_TEXT7,text7);
        context.startActivity(intent);
    }
    @Override
    public int getItemCount() {
        if(products == null){
            return 1;
        }
        return products.size();
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }
    public void addHashtags(ArrayList<String> stringArrayList){
        boolean temp = ProfileActivity.getCurrentUserUsername().equals("");
       for(int x = 0; x < stringArrayList.size();x++){

           if (!(temp)) {
              User.addToHashtagList(stringArrayList.get(x));
           }
       }
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtName, txtPrice, txtPercentOff, txtSeller;
        private CardView parent;
        private ImageView image;
        private TextView PPProductName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtPercentOff = itemView.findViewById(R.id.txtPercentOff);
            txtSeller = itemView.findViewById(R.id.txtSeller);
            parent = itemView.findViewById(R.id.parent);
            image = itemView.findViewById(R.id.txtImage);
            PPProductName = itemView.findViewById(R.id.txtPPName);
            txtPercentOff = itemView.findViewById(R.id.txtPercentOff);

        }
    }
}
