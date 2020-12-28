package edu.qc.seclass.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button buttonCompute;
    EditText checkAmountValue;
    EditText partySizeValue;
    TextView fifteenPercentTipValue;
    TextView twentyPercentTipValue;
    TextView twentyfivePercentTipValue;
    TextView fifteenPercentTotalValue;
    TextView twentyPercentTotalValue;
    TextView twentyfivePercentTotalValue;

    Double billInput;
    Integer partyInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.buttonCompute = (Button) findViewById(R.id.buttonCompute);
        this.partySizeValue = (EditText) findViewById(R.id.partySizeValue);
        this.checkAmountValue = (EditText) findViewById(R.id.checkAmountValue);
        this.fifteenPercentTipValue = (TextView) findViewById(R.id.fifteenPercentTipValue);
        this.twentyfivePercentTipValue = (TextView) findViewById(R.id.twentyfivePercentTipValue);
        this.twentyPercentTipValue = (TextView) findViewById(R.id.twentyPercentTipValue);
        this.fifteenPercentTotalValue = (TextView) findViewById(R.id.fifteenPercentTotalValue);
        this.twentyPercentTotalValue = (TextView) findViewById(R.id.twentyPercentTotalValue);
        this.twentyfivePercentTotalValue = (TextView) findViewById(R.id.twentyfivePercentTotalValue);

        buttonCompute.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(this.checkAmountValue.getText().toString().isEmpty() || this.partySizeValue.getText().toString().isEmpty()){
            Context context = getApplicationContext();
            CharSequence text = "Empty or incorrect value(s)!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }
        this.billInput = Double.parseDouble(checkAmountValue.getText().toString());
        this.partyInput = Integer.parseInt(partySizeValue.getText().toString());
        if (this.billInput <= 0 || this.partyInput <= 0){
            Context context = getApplicationContext();
            CharSequence text = "Empty or incorrect value(s)!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }


        this.fifteenPercentTipValue.setText(calculateTip(.15));
        this.twentyPercentTipValue.setText(calculateTip(.2));
        this.twentyfivePercentTipValue.setText(calculateTip(.25));
        this.fifteenPercentTotalValue.setText(calculateTotalPerPerson(.15));
        this.twentyPercentTotalValue.setText(calculateTotalPerPerson(.2));
        this.twentyfivePercentTotalValue.setText(calculateTotalPerPerson(.25));
    }
    public String calculateTip(double percent){
        String answer;
        Double temp = (this.billInput * percent) / (Integer.parseInt(this.partySizeValue.getText().toString()));
        answer = "" + Math.round(temp);
        return answer;
    }
    public String calculateTotalPerPerson(double percent){
        String answer;
        Double temp = Double.parseDouble(calculateTip(percent));
        double perPerson = this.billInput/this.partyInput;
        answer = "" + Math.round(temp+perPerson);
        return answer;
    }
}