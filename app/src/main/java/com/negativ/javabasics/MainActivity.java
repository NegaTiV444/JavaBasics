package com.negativ.javabasics;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.negativ.javabasics.Fragments.RecyclerViewFragment;

public class MainActivity extends AppCompatActivity {

    public FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frgmCont, new RecyclerViewFragment());
        fragmentTransaction.commit();
    }
}
