package com.example.program_5;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

/**
 * This class is the second activity that shows the result of the advice that is received from the BMI calculation from
 * the main activity.
 *
 * @author Shivam Patel
 * @author Kevin Shah
 */
public class Advice_Based_on_the_BMI extends AppCompatActivity {

    TextView Advice_Id;
    ImageView image;

    /**
     * This is the onCreate method that creates the activity for advice and it shows the pictures as well as the advice based on the
     * BMI calculation. It also passes the data from the onCreate of first activity to this activity to show the advice.
     *
     * @param savedInstanceState Is the variable for the instance state that saves the state of the app from the previous encounter of opening it.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        Advice_Id = findViewById(R.id.Advice_Id);
        image = findViewById(R.id.Image_Id);


        int underWeight = getResources().getIdentifier("@drawable/underweight", null, this.getPackageName());
        int normal = getResources().getIdentifier("drawable/normal", null, this.getPackageName());
        int overWeight = getResources().getIdentifier("drawable/overweight", null, this.getPackageName());
        int Obese = getResources().getIdentifier("drawable/obese", null, this.getPackageName());

        Intent intent = getIntent();
        final String value = intent.getStringExtra("Key");
        Advice_Id.setText(value);
        if(value.equals("Underweight")){
            image.setImageResource(underWeight);
        }else if(value.equals("Normal")){
            image.setImageResource(normal);
        }else if(value.equals("Overweight")){
            image.setImageResource(overWeight);
        }else if(value.equals("Obese")){
            image.setImageResource(Obese);
        }

    }

}
