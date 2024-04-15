package com.android.apnaott;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    private List<DataModel> dataModels;
    private SliderAdapter sliderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Apna OTT");

        // Initialize Firebase
        FirebaseApp.initializeApp(this);

//        RecyclerView recyclerView = findViewById(R.id.webSeRecyclerView);
        ViewPager2 viewPager2 = findViewById(R.id.carousel);

        // Set layout manager in ViewPager2
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        // Initialize dataModels list
        dataModels = new ArrayList<>();

        // Initialize SliderAdapter
        sliderAdapter = new SliderAdapter(this, dataModels);
        viewPager2.setAdapter(sliderAdapter);

        // Load images from Firebase with a delay
                loadFirebaseSlider();

    }


    private void loadFirebaseSlider() {
        myRef.child("trailer").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot contentSlider : snapshot.getChildren()) {
                    String name = contentSlider.child("name").getValue(String.class);
                    String image = contentSlider.child("image").getValue(String.class);
                    String link = contentSlider.child("link").getValue(String.class);
                    DataModel sliderItem = new DataModel(name, image, link);
                    dataModels.add(sliderItem);
                }
                // Notify adapter after updating data
                sliderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase", "Error fetching data: " + error.getMessage());
                Toast.makeText(MainActivity.this, "Error fetching data: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
