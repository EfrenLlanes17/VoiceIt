package com.example.dinehero;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;


public class settings extends AppCompatActivity {

    private BottomNavigationView BNV;
    private Button signOut;
    private Button privacy;
    private Button terms;
    private Button partner;
    private Button partnerSubmit;
    private CardView partnerCard;
    private Button bug;
    private CardView bugReportCard;
    private Button bugSubmit;
    private EditText partnerName;
    private EditText partnerEmail;
    private EditText partnerPhone;
    private EditText probDis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        signOut = findViewById(R.id.SignOut);
        privacy = findViewById(R.id.privacyPolicy);
        terms = findViewById(R.id.termsOfServices);
        bug = findViewById(R.id.bugReport);
        bugReportCard = findViewById(R.id.bugReportCard);
        bugSubmit = findViewById(R.id.bugSubmit);
         probDis = findViewById(R.id.edtTxtBug);
        partner = findViewById(R.id.becomeAPartner);
        partnerSubmit = findViewById(R.id.partnerSubmit);
        partnerCard = findViewById(R.id.partnerCard);
        partnerName = findViewById(R.id.partnerNameTxtBug);
        partnerEmail = findViewById(R.id.partnerEmailTxtBug);
        partnerPhone = findViewById(R.id.partnerPhoneTxtBug);



        bugReportCard.setVisibility(View.INVISIBLE);
        partnerCard.setVisibility(View.INVISIBLE);



        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPrivacyPolicy();
            }
        });

        terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTermsOfServices();
            }
        });

        bug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bugReportCard.setVisibility(View.VISIBLE);
            }
        });

        partner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                partnerCard.setVisibility(View.VISIBLE);
            }
        });

        bugSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(settings.this, "Thank You", Toast.LENGTH_SHORT).show();
                bugReportCard.setVisibility(View.INVISIBLE);
                probDis.setText("");
            }
        });
        partnerSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(settings.this, "Thank You", Toast.LENGTH_SHORT).show();
                partnerCard.setVisibility(View.INVISIBLE);
                partnerName.setText("");
                partnerEmail.setText("");
                partnerPhone.setText("");
            }
        });


        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User.setHasSignedIn(false);



                ProfileActivity.setCurrentUserUsername("");
                ProfileActivity.setCurrentUserPassword("");

                SharedPreferences.Editor editor = getSharedPreferences(User.hasSignedInString,MODE_PRIVATE).edit();
                editor.putString(User.hasSignedInString,User.isHasSignedInString());
                editor.apply();

                //editor = getSharedPreferences(User.usernameForSaved,MODE_PRIVATE).edit();
                //editor.putString(User.usernameForSaved,User.findUser().getUsername());
                //editor.apply();

                ArrayList<Product> temp = new ArrayList();
                ArrayList<String> temp2 = new ArrayList();

                Toast.makeText(settings.this, "Signed Out Successfully", Toast.LENGTH_SHORT).show();


            }
        });




    }
    public void openMainActivity(){

        Intent intent = new Intent(this, MainActivity2.class);
        this.startActivity(intent);
    }
    public  void openPrivacyPolicy(){
        Intent intent = new Intent(this, privacy.class);
        this.startActivity(intent);
    }
    public  void openTermsOfServices(){
        Intent intent = new Intent(this, terms.class);
        this.startActivity(intent);
    }
    public void openInboxActivity(){

        Intent intent = new Intent(this, FollowingActivity.class);
        this.startActivity(intent);
    }
    public void openProfileActivity(){

        Intent intent = new Intent(this, ProfileActivity.class);
        this.startActivity(intent);
    }
}