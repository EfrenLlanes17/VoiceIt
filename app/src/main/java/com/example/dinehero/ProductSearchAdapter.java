package com.example.dinehero;





import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ProductSearchAdapter extends RecyclerView.Adapter<ProductSearchAdapter.ViewHolder>{

    private ArrayList<Product> products = new ArrayList<>();
    public static final String EXTRA_TEXT = "com.example.dinehero.example.EXTRA_TEXT";
    public static final String EXTRA_TEXT2 = "com.example.dinehero.example.EXTRA_TEXT2";
    public static final String EXTRA_TEXT3 = "com.example.dinehero.example.EXTRA_TEXT3";
    public static final String EXTRA_TEXT4 = "com.example.dinehero.example.EXTRA_TEXT4";
    public static final String EXTRA_TEXT5 = "com.example.dinehero.example.EXTRA_TEXT5";
    public static final String EXTRA_TEXT6 = "com.example.dinehero.example.EXTRA_TEXT6";
    public static final String EXTRA_TEXT7 = "com.example.dinehero.example.EXTRA_TEXT7";

    private Context context;


    public ProductSearchAdapter(Context context) {
     this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_list_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
       holder.txtName.setText(products.get(position).getProductName());
       holder.txtPrice.setText(products.get(position).getLocation() + " ");
       holder.txtPercentOff.setText(products.get(position).getSignedUp() + "");
        if(products.get(position).getUserMadeEvent()){
            holder.image.setImageURI(products.get(position).getUriImage());
        }
        else {
            holder.image.setImageDrawable(ContextCompat.getDrawable(context, products.get(position).getProductImage()));
        }
        holder.parent.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               //Set Up product Pages
               String name = products.get(position).getProductName();
               String discription = products.get(position).getProductDiscription();
               String URL = products.get(position).getProductImageURL();
               holder.image.setImageDrawable(ContextCompat.getDrawable(context,products.get(position).getProductImage()));
               String price = "$" + products.get(position).getLocation();
               String percentOff = (products.get(position).getDate());
               String link = products.get(position).getProductLink();
               String sell = products.get(position).getProductSeller().getUsername().toString();



               if(UserPage.getInviting()){
                   Toast.makeText(context.getApplicationContext(), "Shared " + UserPage.getInvitingText() + " with " +  products.get(position).getProductName() , Toast.LENGTH_LONG).show();
                   UserPage.setInviting(false,"");

               }



               openProductPage(name,discription,URL,price,percentOff,link,sell);


           }
       });



    }

    public void openProductPage(String text1,String text2,String text3,String text4, String text5,String text6,String text7){

        Intent intent = new Intent(context, ProductPage.class);
        intent.putExtra(EXTRA_TEXT,text1);
        intent.putExtra(EXTRA_TEXT2,text2);
        intent.putExtra(EXTRA_TEXT3,text3);
        intent.putExtra(EXTRA_TEXT4,text4);
        intent.putExtra(EXTRA_TEXT5,text5);
        intent.putExtra(EXTRA_TEXT6,text6);
        intent.putExtra(EXTRA_TEXT7,text7);
        context.startActivity(intent);
    }
    @Override
    public int getItemCount() {
        return products.size();
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }
    public void addHashtags(ArrayList<String> stringArrayList){
       for(int x = 0; x < stringArrayList.size();x++){

           User.addToHashtagList(stringArrayList.get(x));
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

        }
    }
}
