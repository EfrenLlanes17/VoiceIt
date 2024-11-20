package com.example.dinehero;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class UserPage extends AppCompatActivity {

    public TextView PPProductName;
    public TextView PPDiscription;
    public ImageView freeImg;
    public ImageView PPImage;
    public TextView PPPrice;
    private TextView PPPercentOff;
    private Button PPButton;
    private Button shareButton;
    private Button saveButton;
    private Button followingButton;

    private ImageView imageBoxinbox;
    private ImageView imageBox;
    private TextView RateTitleText;
    private EditText RatingEditTxt;
    private ImageView Star1;
    private ImageView Star2;

    public static boolean inviting = false;
    public static String invitingText = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);


        Intent intent = getIntent();
//        String text1 = intent.getStringExtra(ProductAdapter.EXTRA_TEXT);
//
//        String text3 = intent.getStringExtra(ProductAdapter.EXTRA_TEXT3);
//        String text7 = intent.getStringExtra(ProductAdapter.EXTRA_TEXT7);
//        Product temp = MainActivity2.findProduct(text1);
        boolean temp2 = ProfileActivity.getCurrentUserUsername().equals("");


//        String text2 = temp.getProductDiscription();
//        String text4 = temp.getProductPrice();
//        int text5 = temp.getProductPercentOff();
//        String text6 = temp.getProductLink();




        imageBoxinbox = findViewById(R.id.ImageBoxInBox);
        imageBox = findViewById(R.id.ImageBox);
        RateTitleText = findViewById(R.id.rateTitleText);
        RatingEditTxt = findViewById(R.id.ratingEditTxt);
        Star1 = findViewById(R.id.star1);
        Star2 = findViewById(R.id.star2);


        PPProductName = findViewById(R.id.txtPPName);
        PPDiscription = findViewById(R.id.txtPPDiscription);
        PPImage = findViewById(R.id.txtPPImage);
        PPPrice = findViewById(R.id.txtPPPrice);
        PPPercentOff = findViewById(R.id.txtPPPercentOff);
        PPButton = findViewById(R.id.sitePPButton);
        saveButton = findViewById(R.id.savePPButton);
        followingButton = findViewById(R.id.followPPButton);
        freeImg = findViewById(R.id.free);


        imageBoxinbox.setVisibility(View.INVISIBLE);
        imageBox.setVisibility(View.INVISIBLE);
        RateTitleText.setVisibility(View.INVISIBLE);
        RatingEditTxt.setVisibility(View.INVISIBLE);
        Star1.setVisibility(View.INVISIBLE);
        Star2.setVisibility(View.INVISIBLE);



        PPProductName.setText(ProductPage.goToUserProfile().getUsername());
        PPDiscription.setText(ProductPage.goToUserProfile().getBio());
        PPPercentOff.setText("" +ProductPage.goToUserProfile().getRating() + "/5.0");
        PPPrice.setText("Posted " +ProductPage.goToUserProfile().getEventsAttended() + " Petitions");
        PPImage.setImageDrawable(ContextCompat.getDrawable(this,ProductPage.goToUserProfile().getDraw()));
//        PPPrice.setText(MainActivity2.findProduct(text1).getLocation());
//        if(MainActivity2.findProduct(text1).getCost() == 0){
//            freeImg.setVisibility(View.VISIBLE);
//            PPPercentOff.setVisibility(View.INVISIBLE);
//        }
//        else{
//            freeImg.setVisibility(View.INVISIBLE);
//            PPPercentOff.setVisibility(View.VISIBLE);
//            PPPercentOff.setText("$" +MainActivity2.findProduct(text1).getCost() + ".00" );
//        }
//
//        PPImage.setImageDrawable(ContextCompat.getDrawable(UserPage.this,MainActivity2.findProduct(text1).getProductImage()));
//
//
//            if (ProfileActivity.inSaved(MainActivity2.findProduct(text1))) {
//                saveButton.setText("    UNSAVE   ");
//            } else {
//                saveButton.setText("     SAVE    ");
//            }
//
//            if (!ProfileActivity.isFollowing(temp)) {
//                followingButton.setText("  ATTEND  ");
//            } else {
//                followingButton.setText("  ATTENDING  ");
//            }








                saveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        if (!ProfileActivity.getCurrentUserUsername().equals("")) {

                                openSearchActivity();
                                invitingText = ProductPage.goToUserProfile().getUsername();
                                inviting = true;
                                Toast.makeText(UserPage.this, "Select Petition To Share " + ProductPage.goToUserProfile().getUsername(), Toast.LENGTH_LONG).show();


                        } else {
                            openProfile();
                            Toast.makeText(UserPage.this, "Sign in to share with Users", Toast.LENGTH_SHORT).show();

                        }



                    }
                });

                PPButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!ProfileActivity.getCurrentUserUsername().equals("")) {
                        RatingEditTxt.setText("");
                        imageBoxinbox.setVisibility(View.VISIBLE);
                        imageBox.setVisibility(View.VISIBLE);
                        RateTitleText.setVisibility(View.VISIBLE);
                        RatingEditTxt.setVisibility(View.VISIBLE);
                        Star1.setVisibility(View.VISIBLE);
                        Star2.setVisibility(View.VISIBLE);
                        } else {
                            Toast.makeText(UserPage.this, "Sign in to Rate Users", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

                RatingEditTxt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                        if ((EditorInfo.IME_MASK_ACTION) != 0) {
                            if(Integer.parseInt(RatingEditTxt.getText().toString()) <=5) {


                                imageBoxinbox.setVisibility(View.INVISIBLE);
                                imageBox.setVisibility(View.INVISIBLE);
                                RateTitleText.setVisibility(View.INVISIBLE);
                                RatingEditTxt.setVisibility(View.INVISIBLE);
                                Star1.setVisibility(View.INVISIBLE);
                                Star2.setVisibility(View.INVISIBLE);

                                Toast.makeText(UserPage.this, "Thank You For Rating", Toast.LENGTH_SHORT).show();
                                return false;
                            }
                            return true;
                        }
                        else {
                            return false;
                        }
                    }
                });




            }
    public void openSearchActivity(){

        Intent intent = new Intent(this, SearchActivity.class);
        this.startActivity(intent);
    }

    public static void setInviting(boolean inviting2, String invitingText2){
        inviting = inviting2;
        invitingText = invitingText2;
    }

    public void openProfile(){
        Intent intent = new Intent(this, ProfileActivity.class);
        this.startActivity(intent);
    }
    public static boolean getInviting(){
        return inviting;
    }
    public static String getInvitingText(){
        return invitingText;
    }



}