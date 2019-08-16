package com.ahmedco.view.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.ahmedco.model.DataModel;
import com.ahmedco.networking.ItemsRepository;
import com.ahmedco.view.adapter.ListMenuAdapter;
import com.ahmedcom.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView totalTxt;

     private ItemsRepository newRepository;
     public static ArrayList<DataModel> NewMenu_person = new ArrayList<>();

     @Override
     protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         newRepository = ViewModelProviders.of(this).get(ItemsRepository.class);
         recyclerView = (RecyclerView) findViewById(R.id.recycler);
         totalTxt = (TextView)findViewById(R.id.total_txt);
         // set a LinearLayoutManager with default vertical orientation ...........................
         LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
         recyclerView.setLayoutManager(linearLayoutManager);

         newRepository.getAllData().observe(this, new Observer<List<DataModel>>() {
             @Override
             public void onChanged(@Nullable List<DataModel> dataModels) {

                 if (dataModels != null) {
                     ListMenuAdapter customAdapter = new ListMenuAdapter(MainActivity.this, dataModels);
                    // Log.i("customAdapter0 ", "" + dataModels);
                     recyclerView.setAdapter(customAdapter);
                 }
             }
         });
    }
}