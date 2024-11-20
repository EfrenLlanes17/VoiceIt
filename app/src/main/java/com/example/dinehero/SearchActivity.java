package com.example.dinehero;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dinehero.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private BottomNavigationView BNV;
    private static ArrayList<Product> searchArrayList = new ArrayList<>();
    private RecyclerView SRV;
    TextView noResults;

   private EditText search;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        SRV = findViewById(R.id.SRV);
        search = findViewById(R.id.searchET);
        noResults = findViewById(R.id.noResults);
        noResults.setVisibility(View.INVISIBLE);



        ProductSearchAdapter adapter = new ProductSearchAdapter(this);

        SRV.setAdapter(adapter);
        SRV.setLayoutManager(new LinearLayoutManager(this));

      search.addTextChangedListener(new TextWatcher() {
          @Override
          public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

          }

          @Override
          public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

          }











          @Override
          public void afterTextChanged(Editable editable) {

              noResults.setVisibility(View.INVISIBLE);
              searchArrayList.clear();
              adapter.setProducts(searchArrayList);
              for (int x = 0; x < MainActivity2.getProductsList().size(); x++) {
                  if (ProfileActivity.isNotInsideSearch(MainActivity2.getProductsList().get(x).getProductName())) {
                      if (MainActivity2.getProductsList().get(x).getProductName().toLowerCase().indexOf(search.getText().toString().toLowerCase()) > -1
                              || MainActivity2.getProductsList().get(x).getDate().indexOf(search.getText().toString().toLowerCase()) > -1
                              || MainActivity2.getProductsList().get(x).getLocation().toLowerCase().indexOf(search.getText().toString().toLowerCase()) > -1) {
                          searchArrayList.add(0, MainActivity2.getProductsList().get(x));
                          adapter.setProducts(searchArrayList);


                      }
                  }
              }
              if(searchArrayList.isEmpty()){
                  noResults.setVisibility(View.VISIBLE);
              }
          }
      });












        }


    public void openMainActivity(){

        Intent intent = new Intent(this, MainActivity2.class);
        this.startActivity(intent);
    }
    public void openInboxActivity(){

        Intent intent = new Intent(this, SearchActivity.class);
        this.startActivity(intent);
    }
    public void openProfileActivity(){

        Intent intent = new Intent(this, ProfileActivity.class);
        this.startActivity(intent);
    }
    public static ArrayList<Product> getSearchList(){
        return searchArrayList;
    }
}