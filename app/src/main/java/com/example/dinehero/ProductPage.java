package com.example.dinehero;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileOutputStream;

public class ProductPage extends AppCompatActivity {

    public TextView PPProductName;
    public TextView PPDiscription;
    public ImageView freeImg;
    public ImageView PPImage;
    public TextView PPPrice;

    public TextView txtdaysLeft;
    private TextView PPPercentOff;
    private Button PPButton;
    private Button shareButton;
    private Button saveButton;
    private Button followingButton;
    private static User goingToUser;
    private static Product goingToProduct;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_page);


        Intent intent = getIntent();
        String text1 = intent.getStringExtra(ProductAdapter.EXTRA_TEXT);

        String text3 = intent.getStringExtra(ProductAdapter.EXTRA_TEXT3);
        String text7 = intent.getStringExtra(ProductAdapter.EXTRA_TEXT7);
        Product temp = MainActivity2.findProduct(text1);
        boolean temp2 = ProfileActivity.getCurrentUserUsername().equals("");


        String text2 = temp.getProductDiscription();
        String text4 = temp.getProductPrice();
        int text5 = temp.getProductPercentOff();
        String text6 = temp.getProductLink();
        goingToUser =MainActivity2.findProduct(text1).getProductSeller();
        goingToProduct =MainActivity2.findProduct(text1);

        PPProductName = findViewById(R.id.txtPPName);
        txtdaysLeft = findViewById(R.id.txtdaysLeft);
        PPDiscription = findViewById(R.id.txtPPDiscription);
        PPImage = findViewById(R.id.txtPPImage);
        PPPrice = findViewById(R.id.txtPPPrice);
        PPPercentOff = findViewById(R.id.txtPPPercentOff);
        PPButton = findViewById(R.id.sitePPButton);
        saveButton = findViewById(R.id.savePPButton);
        followingButton = findViewById(R.id.followPPButton);
        shareButton = findViewById(R.id.sharePPButton);



        if(MainActivity2.findProduct(text1).getUserMadeEvent()){
            PPProductName.setText("You posted this Petition");
        }
        else {
            PPProductName.setText(text1);
        }

        PPDiscription.setText(text2);
        PPPrice.setText(MainActivity2.findProduct(text1).getLocation() + " ");
        PPPercentOff.setText(MainActivity2.findProduct(text1).getSignedUp() + " / 92262 Signatures");
        txtdaysLeft.setText(MainActivity2.findProduct(text1).getDate() + " days left");

        if(MainActivity2.findProduct(text1).getUserMadeEvent()){
            saveButton.setVisibility(View.INVISIBLE);
            followingButton.setVisibility(View.INVISIBLE);
            PPPercentOff.setText(MainActivity2.findProduct(text1).getSignedUp() + " / " + MainActivity2.findProduct(text1).getMaxSignedUp());
            PPButton.setVisibility(View.INVISIBLE);
        }

//        if(MainActivity2.findProduct(text1).getCost() == 0){
//            //freeImg.setVisibility(View.VISIBLE);
//            //PPPercentOff.setVisibility(View.INVISIBLE);
//        }
//        else{
//
//            PPPercentOff.setVisibility(View.VISIBLE);
//            PPPercentOff.setText("$" +MainActivity2.findProduct(text1).getCost() + ".00" );
//        }

        if(MainActivity2.findProduct(text1).getUserMadeEvent()){
            PPImage.setImageURI(MainActivity2.findProduct(text1).getUriImage());

        }
        else {
            PPImage.setImageDrawable(ContextCompat.getDrawable(ProductPage.this,MainActivity2.findProduct(text1).getProductImage()));

        }


            if (ProfileActivity.inSaved(MainActivity2.findProduct(text1))) {
                saveButton.setText(" UNSAVE ");
            } else {
                saveButton.setText(" SAVE ");
            }

            if (!ProfileActivity.isFollowing(temp)) {
                followingButton.setText(" SIGN ");
            } else {
                followingButton.setText(" SIGNED ");
            }

            shareButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("message/rfc822");
                    //intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"recipient@example.com"});
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Check Out This Petition on VoiceIt : " + MainActivity2.findProduct(text1).getProductName());
                    intent.putExtra(Intent.EXTRA_TEXT, "" + MainActivity2.findProduct(text1).getProductDiscription());

                    // Check if there is an app that can handle this intent
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(Intent.createChooser(intent, "Choose an email client"));
                    }
                }
            });




        followingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(temp2)) {
                    if (!ProfileActivity.isFollowing(temp)) {
                        ProfileActivity.addToFollowing(temp);
                        MainActivity2.findProduct(text1).changeSignedUpBy(1);
                        followingButton.setText(" SIGNED ");
                    }
                    else{
                        ProfileActivity.removeFromFollowing(temp);
                        MainActivity2.findProduct(text1).changeSignedUpBy(-1);
                        followingButton.setText(" SIGN ");
                    }

                }
                else{
                    openProfile();
                    Toast.makeText(ProductPage.this, "Sign in to sign petitions", Toast.LENGTH_SHORT).show();
                }
            }
        });


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(temp2)) {

                    if (temp.getHasbeenSaved()) {

                        ProfileActivity.removeFromSaved(temp);
                        saveButton.setText(" SAVE ");
                        temp.setHasbeenSaved(false);
                        Toast.makeText(ProductPage.this, "Petition unsaved, reload tab", Toast.LENGTH_SHORT).show();

                    } else {
                        ProfileActivity.addToSaved(temp);
                        saveButton.setText(" UNSAVE ");
                       temp.setHasbeenSaved(true);

                    }
                }
                else{
                    openProfile();
                    Toast.makeText(ProductPage.this, "Sign in to save petitions", Toast.LENGTH_SHORT).show();
                }
            }
        });

        PPButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Uri uri = Uri.parse(text6);
                //startActivity(new Intent(Intent.ACTION_VIEW,uri));
               openUserPage();
            }
        });







    }

public void openUserPage(){
    Intent intent = new Intent(this, UserPage.class);
    this.startActivity(intent);
}
    public void openProfile(){
        Intent intent = new Intent(this, ProfileActivity.class);
        this.startActivity(intent);
    }
public static User goToUserProfile(){
        return  goingToUser;
}

public static Product goToUserProfileProduct(){
        return  goingToProduct;
    }




}