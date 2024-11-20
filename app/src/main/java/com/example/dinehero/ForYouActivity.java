package com.example.dinehero;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dinehero.databinding.ActivityMainBinding;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.EventListener;

public class ForYouActivity extends AppCompatActivity {

    private BottomNavigationView BNV;
    private BottomNavigationView TNV;
    private RecyclerView FRV;
    private static ArrayList<Product> forYouList = new ArrayList<>();
    private static  Boolean first = true;

    private Button goToSignIN;
    private TextView createPostTxt;
    private EditText eventName;
    private EditText eventLoc;
    private EditText eventPrice;
    private EditText eventDisc;
    private Button creatBtn;

    private ImageButton ImageTings;

    private EditText eventAttending;
    private EditText eventDate;

    private Uri uriiiii;


    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_for);



        TNV = findViewById(R.id.topNavView2);
        //TNV.setSelectedItemId(findViewById(R.id.btnForYou).getId());
        TNV.setItemActiveIndicatorEnabled(false);

        ImageTings = findViewById(R.id.ImageTing);
        creatBtn = findViewById(R.id.createPostBtn);
        goToSignIN = findViewById(R.id.foryouSignIn);
        createPostTxt = findViewById(R.id.createPostText);
        eventName = findViewById(R.id.edtTextTitle);
        eventLoc = findViewById(R.id.edtTextLoc);
        eventPrice = findViewById(R.id.edtTextPrice);
        eventDisc = findViewById(R.id.edtTextDisc);
        eventAttending = findViewById(R.id.edtTextAttending);
        eventDate = findViewById(R.id.edtTextDate);


        ImageTings.setVisibility(View.INVISIBLE);
        eventAttending.setVisibility(View.INVISIBLE);
        eventDate.setVisibility(View.INVISIBLE);
        creatBtn.setVisibility(View.INVISIBLE);
        goToSignIN.setVisibility(View.VISIBLE);
        createPostTxt.setVisibility(View.INVISIBLE);
        eventDisc.setVisibility(View.INVISIBLE);
        eventPrice.setVisibility(View.INVISIBLE);
        eventLoc.setVisibility(View.INVISIBLE);
        eventName.setVisibility(View.INVISIBLE);
        if(!(ProfileActivity.getCurrentUserUsername().equals(""))){
            goToSignIN.setVisibility(View.INVISIBLE);
            createPostTxt.setVisibility(View.VISIBLE);
            ImageTings.setVisibility(View.VISIBLE);
            eventDisc.setVisibility(View.VISIBLE);
            creatBtn.setVisibility(View.VISIBLE);
            //eventPrice.setVisibility(View.VISIBLE);
            eventLoc.setVisibility(View.VISIBLE);
            eventName.setVisibility(View.VISIBLE);
            eventAttending.setVisibility(View.VISIBLE);
            eventDate.setVisibility(View.VISIBLE);
        }



        goToSignIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProfileActivity();
            }
        });

        creatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!eventName.getText().toString().equals("") && !eventDisc.getText().toString().equals("") && !eventLoc.getText().toString().equals("") && !eventAttending.getText().toString().equals("") && !eventDate.getText().toString().equals("") && uriiiii != null) {
                    ProfileActivity.addToFollowing(new Product(eventName.getText().toString(), eventDisc.getText().toString(), eventLoc.getText().toString(), null, uriiiii, false, eventAttending.getText().toString(), 1, Integer.parseInt(eventDate.getText().toString()), 0));
                    MainActivity2.addToProdList(new Product(eventName.getText().toString(), eventDisc.getText().toString(), eventLoc.getText().toString(), null, uriiiii, false, eventAttending.getText().toString(), 1, Integer.parseInt(eventDate.getText().toString()), 0));

                    eventName.setText("");
                    eventDisc.setText("");
                    eventLoc.setText("");
                    eventPrice.setText("");
                    eventAttending.setText("");
                    eventDate.setText("");
                    ImageTings.setImageDrawable(ContextCompat.getDrawable(ForYouActivity.this, R.drawable.baseline_image_search_24222222222));
                    //ImageTings.setBackground(ContextCompat.getDrawable(ForYouActivity.this,R.drawable.baseline_image_search_24));
                    Toast.makeText(ForYouActivity.this, "Petition Created Successfully", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(ForYouActivity.this, "Not All Requirements Satisfied", Toast.LENGTH_LONG).show();

                }
            }
        });

        ImageTings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(ForYouActivity.this)
                                .crop()
                                .compress(1024)
                                .maxResultSize(1580,1080)
                                .start();

            }
        });

        TNV.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == findViewById(R.id.btnDiscover).getId()) {

                    openMainActivity();
                } else if (item.getItemId() == findViewById(R.id.btnForYou).getId()) {
                    openForYouActivity();
                } else if (item.getItemId() == findViewById(R.id.btnFollowing).getId()) {
                    openProfileActivity();
                }else if (item.getItemId() == findViewById(R.id.btnSearch).getId()) {
                    openSearchActivity();
                }

                return false;
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode, @NonNull Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        Uri uri = data.getData();

        uriiiii = uri;
        ImageTings.setImageURI(uri);
    }
    public void openMainActivity(){

        Intent intent = new Intent(this, MainActivity2.class);
        this.startActivity(intent);
    }
    public void openInboxActivity(){

        Intent intent = new Intent(this, ForYouActivity.class);
        this.startActivity(intent);
    }
    public void openProfileActivity(){

        Intent intent = new Intent(this, ProfileActivity.class);
        this.startActivity(intent);
    }
    public void openForYouActivity(){

        Intent intent = new Intent(this, ForYouActivity.class);
        this.startActivity(intent);
    }

    public void openFollowingActivity(){
        Intent intent = new Intent(this, FollowingActivity.class);
        this.startActivity(intent);
    }
    public void openSearchActivity(){

        Intent intent = new Intent(this, SearchActivity.class);
        this.startActivity(intent);
    }
    public static void setFirst(boolean boo){

        first = boo;
    }
    public static boolean getFirst(){
        return first;
    }
    public static ArrayList<Product> getForYouList(){
        return forYouList;
    }

    public static void setForYouList(ArrayList<Product> array){

        forYouList = array;
    }
}