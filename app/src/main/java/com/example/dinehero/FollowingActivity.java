package com.example.dinehero;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dinehero.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class FollowingActivity extends AppCompatActivity {

    private BottomNavigationView BNV;
    private BottomNavigationView TNV;
    private RecyclerView FollowRV;
    private static ArrayList<Product> followingProductList = new ArrayList();

    ActivityMainBinding binding;

    private Button goToSignINfollowing;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_following);
        TNV = findViewById(R.id.topNavView3);

        FollowRV = findViewById(R.id.FollowRV);
        goToSignINfollowing = findViewById(R.id.followingSignIn);




        goToSignINfollowing.setVisibility(View.VISIBLE);
        if(!(ProfileActivity.getCurrentUserUsername().equals(""))){
            goToSignINfollowing.setVisibility(View.INVISIBLE);
            ArrayList<Product> temp = new ArrayList<>();
            followingProductList = temp;
        }

        //TNV.setSelectedItemId(findViewById(R.id.btnFollowing).getId());
        TNV.setItemActiveIndicatorEnabled(false);

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

        if(User.findUser() != null) {
            for (int x = 0; x < MainActivity2.getProductsList().size(); x++) {
                if ((ProfileActivity.isFollowing(MainActivity2.getProductsList().get(x)))) {
                    if (!(MainActivity2.findProductInFollowingBoolean(MainActivity2.getProductsList().get(x).getProductName(), MainActivity2.getProductsList().get(x).getProductImageURL()))) {
                        if(User.findUser().isNotInViewedProducts(MainActivity2.getProductsList().get(x))) {
                            followingProductList.add(MainActivity2.getProductsList().get(x));
                        }
                    }
                }
            }
        }


        ProductAdapter adapter = new ProductAdapter(this);
        adapter.setProducts(followingProductList);
        FollowRV.setAdapter(adapter);
        FollowRV.setLayoutManager(new LinearLayoutManager(this));

        goToSignINfollowing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProfileActivity();
            }
        });



    }
    public void openMainActivity(){

        Intent intent = new Intent(this, MainActivity2.class);
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
    public static ArrayList<Product> getFollowingProductList(){
        return followingProductList;
    }
    public static void setFollowingProductList(ArrayList<Product> array){
        followingProductList = array;
    }
}