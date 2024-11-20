package com.example.dinehero;





import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class FollowingAdapter extends RecyclerView.Adapter<FollowingAdapter.ViewHolder>{

    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<Product> strings = new ArrayList<>();

    public static final String EXTRA_TEXT = "com.example.dinehero.example.EXTRA_TEXT";

    public static final String EXTRA_TEXT3 = "com.example.dinehero.example.EXTRA_TEXT3";

    public static final String EXTRA_TEXT7 = "com.example.dinehero.example.EXTRA_TEXT7";
    private Context context;


    public FollowingAdapter(Context context) {
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       //holder.txtSeller.setText(strings.get(position).getLocation());
       holder.prodName.setText(strings.get(position).getProductName());
       holder.prodPrice.setText(strings.get(position).getLocation()+"  ");
       holder.prodPercent.setText(strings.get(position).getSignedUp() + "");
       if(strings.get(position).getUserMadeEvent()){
           holder.pic.setImageURI(strings.get(position).getUriImage());
       }
       else {
           holder.pic.setImageDrawable(ContextCompat.getDrawable(context, strings.get(position).getProductImage()));
       }
       holder.parent.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {


               String name = strings.get(position).getProductName();
               int image = strings.get(position).getProductImage();

               String sell = "";

               if(strings.get(position).getUserMadeEvent()){
                    sell = "Hosting";
               }
               else {
                    sell = strings.get(position).getProductSeller().getUsername().toString();
               }



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

               openProductPage(name,image,sell);

           }
       });



    }


    @Override
    public int getItemCount() {
        if(strings == null){
            return 1;
        }

        return strings.size();
    }
    public void openProductPage(String text1,int text3,String text7){

        Intent intent = new Intent(context, ProductPage.class);
        intent.putExtra(EXTRA_TEXT,text1);

        intent.putExtra(EXTRA_TEXT3,text3);

        intent.putExtra(EXTRA_TEXT7,text7);
        context.startActivity(intent);
    }

    public void setProducts(ArrayList<Product> strings) {
        this.strings = strings;
        notifyDataSetChanged();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtSeller;
        private CardView parent;
        private TextView prodName;
        private TextView prodPrice;
        private TextView prodPercent;
        private  ImageView pic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSeller = itemView.findViewById(R.id.txtSeller);
            parent = itemView.findViewById(R.id.parent);
            prodName = itemView.findViewById(R.id.txtName);
            prodPrice = itemView.findViewById(R.id.txtPrice);
            prodPercent = itemView.findViewById(R.id.txtPercentOff);
            pic = itemView.findViewById(R.id.txtImage);
        }
    }
}
