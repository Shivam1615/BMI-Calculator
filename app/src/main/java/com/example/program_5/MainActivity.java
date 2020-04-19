package com.example.program_5;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.math.RoundingMode;
import java.nio.FloatBuffer;
import java.text.DecimalFormat;

/**
 * This is mainActivity class which creates the application for the inputs of weight and height while also giving
 * the value of the BMI based on those weight and height values that users input.
 *
 * @author Shivam Patel
 * @author Kevin Shah
 */
public class MainActivity extends AppCompatActivity {


    TextView BMI_Output;

    EditText Weight_Input;
    EditText Height_Input;

    Button Calculate_Button;
    Button Advice_Button;

    RadioGroup Radio_Group;

    /**
     * This is the onCreate activity that creates an application and includes two activities for buttons
     * such as advice for for BMI and BMI calculation.
     *
     * @param savedInstanceState This is the variable that creates the application screen for the app. It also includes
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Radio_Group = findViewById(R.id.Radio_Group);
        BMI_Output = findViewById(R.id.BMI_Output);
        Weight_Input = findViewById(R.id.Weight_Input);
        Height_Input = findViewById(R.id.Height_Input);
        Calculate_Button = findViewById(R.id.Calculate_button);
        Advice_Button = findViewById(R.id.Advice_button);

        Calculate_Button.setOnClickListener(new View.OnClickListener() {
            /**
             * This method is event driven for clicking the button for BMI calculation based on the values of height
             * and weight. It checks if the radio button is clicked first and then lets user enter data for height and weight.
             *
             * @param v Is the view variable that is an event driven variable for showing the next view of the event after selecting radio button.
             */
            @Override
            public void onClick(View v) {
                int checkId = Radio_Group.getCheckedRadioButtonId();


                if(checkId == -1){
                    Toast.makeText(getApplicationContext(), "Please select one of the two metric unit options before continuing", Toast.LENGTH_SHORT).show();
                }
                else if(Height_Input.getText().toString().isEmpty() || Weight_Input.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please enter the data for both height and weight", Toast.LENGTH_SHORT).show();
                }else if(Height_Input.getText().toString().equals(".") || Weight_Input.getText().toString().equals(".")  || Float.parseFloat(Height_Input.getText().toString()) <= 0){
                    Toast.makeText(getApplicationContext(), "Please enter vaild inputs for weight and height", Toast.LENGTH_SHORT).show();
                }else{
                    English_Metric2(checkId);
                }
            }
        });

        Advice_Button.setOnClickListener(new View.OnClickListener() {
            /**
             * This is the method for selecting option for advice after getting the BMI calculation and checks what value of BMI corresponds to
             * a certain advice.
             * @param v Is the view variable for showing the event of clicking the advice button in the app.
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Advice_Based_on_the_BMI.class);

                if(BMI_Output.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please get your BMI values in order to receive advice", Toast.LENGTH_SHORT).show();
                }else if(Float.isNaN(Float.parseFloat(BMI_Output.getText().toString()))){
                    Toast.makeText(getApplicationContext(), "Please get vaild BMI values in order to get proper advice", Toast.LENGTH_SHORT).show();
                }else {
                    if (Float.parseFloat(BMI_Output.getText().toString()) < 18.5) {
                        intent.putExtra("Key", "Underweight");
                    } else if (Float.parseFloat(BMI_Output.getText().toString()) >= 18.5 && Float.parseFloat(BMI_Output.getText().toString()) < 25) {
                        intent.putExtra("Key", "Normal");
                    } else if (Float.parseFloat(BMI_Output.getText().toString()) >= 25 && Float.parseFloat(BMI_Output.getText().toString()) < 30) {
                        intent.putExtra("Key", "Overweight");
                    } else if (Float.parseFloat(BMI_Output.getText().toString()) >= 30) {
                        intent.putExtra("Key", "Obese");
                    }
                    startActivity(intent);
                    BMI_Output.setText("");
                    Weight_Input.setText("");
                    Height_Input.setText("");
                }
            }
        });

    }

    /**
     * This method is for selecting radio buttons and showing hints of what user should input based on the metric unit selected.
     *
     * @param view Is the view variable for showing the clicking event of the radio buttons and showing the hint after
     * selecting one of those options.
     */
    public void EnglishMetric(View view) {
        int checkId = Radio_Group.getCheckedRadioButtonId();
        switch(checkId){
            case R.id.English_metric:
                Height_Input.setHint("Enter height in inches");
                Weight_Input.setHint("Enter weight in pounds");
                break;
            case R.id.NonEnglish_metric:
                Height_Input.setHint("Enter height in meters");
                Weight_Input.setHint("Enter weight in kilograms");
                break;
        }
    }

    /**
     * This method is for creating the output for event driven for BMI calculation based on the
     * radio button that was clicked. The two options are english metric and non-english metric.
     *
     * @param checkId This is the id of the radio group and is used to group radio button ids together to check which radio button was clicked.
     */
    @SuppressLint("SetTextI18n")
    private void English_Metric2(int checkId){
        switch(checkId){
            case R.id.English_metric:
                DecimalFormat df = new DecimalFormat("0.00");
                df.setRoundingMode(RoundingMode.HALF_EVEN);
                CharSequence weight = Weight_Input.getText();
                CharSequence height = Height_Input.getText();

                float weightCal = Float.parseFloat(weight.toString());
                float heightCal = Float.parseFloat(height.toString());

                float Cal = (weightCal * 703) / (heightCal * heightCal);

                String Answer = df.format(Cal);
                BMI_Output.setText(Answer);
                break;

            case R.id.NonEnglish_metric:
                DecimalFormat df2 = new DecimalFormat("0.00");
                df2.setRoundingMode(RoundingMode.HALF_EVEN);

                CharSequence weight2 = Weight_Input.getText();
                CharSequence height2 = Height_Input.getText();

                float weightCal2 = Float.parseFloat(weight2.toString());
                float heightCal2 = Float.parseFloat(height2.toString());

                float Cal2 = (weightCal2) / (heightCal2 * heightCal2);
                String Answer2 = df2.format(Cal2);

                BMI_Output.setText(Answer2);
                break;
        }
    }

}
