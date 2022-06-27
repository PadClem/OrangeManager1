package com.example.orangemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private TextView SantaRosa, Manila, Batangas;

    private int selectedTabNumber = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SantaRosa = findViewById(R.id.SantaRosa);
        Manila = findViewById(R.id.Manila);
        Batangas = findViewById(R.id.Batangas);

        //SELECT SANTA ROSA DEFAULT
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainer, SantaRosa_Fragment.class, null)
                        .commit();

        //SANTA ROSA
        SantaRosa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectBranch(1);
            }
        });

        //Manila
        Manila.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectBranch(2);
            }
        });

        //Batangas
        Batangas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectBranch(3);
            }
        });
    }

    private void selectBranch(int tabNumber){

        TextView selectedTextView;

        TextView nonSelectedTextView1;
        TextView nonSelectedTextView2;

        if (tabNumber == 1){
            //Selected Part
            selectedTextView = SantaRosa;

            //Non-Selected Part
            nonSelectedTextView1 = Manila;
            nonSelectedTextView2 = Batangas;

            //Setting fragment to fragment container view
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragmentContainer, SantaRosa_Fragment.class, null)
                    .commit();
        }
        else if (tabNumber == 2){
            selectedTextView = Manila;
            nonSelectedTextView1 = SantaRosa;
            nonSelectedTextView2 = Batangas;

            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragmentContainer, Manila_Fragment.class, null)
                    .commit();
        }
        else{
            selectedTextView = Batangas;
            nonSelectedTextView1 = SantaRosa;
            nonSelectedTextView2 = Manila;

            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragmentContainer, Batangas_Fragment.class, null)
                    .commit();
        }

        float slideTo = (tabNumber - selectedTabNumber) * selectedTextView.getWidth();

        //Creating Transalate Animation
        TranslateAnimation translateAnimation = new TranslateAnimation(0,slideTo,0,0);
        translateAnimation.setDuration(100);

        //Checking for previously selected tab
        if (selectedTabNumber == 1){
            SantaRosa.startAnimation(translateAnimation);
        }
        else if (selectedTabNumber == 2){
            Manila.startAnimation(translateAnimation);
        }
        else{
            Batangas.startAnimation(translateAnimation);
        }

        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //Selected Tab New Format design
                selectedTextView.setBackgroundResource(R.drawable.round_back_white100);
                selectedTextView.setTypeface(null, Typeface.BOLD);
                selectedTextView.setTextColor(Color.BLACK);

                //Selected Tab non Selected Format
                nonSelectedTextView1.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                nonSelectedTextView1.setTextColor(Color.parseColor("#80FFFFFF"));
                nonSelectedTextView1.setTypeface(null,Typeface.NORMAL);

                nonSelectedTextView2.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                nonSelectedTextView2.setTextColor(Color.parseColor("#80FFFFFF"));
                nonSelectedTextView2.setTypeface(null,Typeface.NORMAL);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

}