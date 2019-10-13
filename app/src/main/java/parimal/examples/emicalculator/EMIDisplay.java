package parimal.examples.emicalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static parimal.examples.emicalculator.MainActivity.EMIcalculated;
import static parimal.examples.emicalculator.MainActivity.amountcalculated;
import static parimal.examples.emicalculator.MainActivity.interestcalculated;

public class EMIDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emidisplay);
        displayResult();
        setButtonListener();
    }

    public void displayResult()
    {
        TextView EMIResult=(TextView)findViewById(R.id.emi_calculated_result);
        TextView interestResult=(TextView)findViewById(R.id.interest_calculated_result);
        TextView totalAmountResult=(TextView)findViewById(R.id.totalamount_calculated_result);
        Bundle b=new Bundle();
        b=getIntent().getExtras();
        if(b!=null)
        {
            Double emi=b.getDouble(EMIcalculated);
            Double interest=b.getDouble(interestcalculated);
            Double t_Amount=b.getDouble(amountcalculated);
            EMIResult.setText(emi.toString());
            interestResult.setText(interest.toString());
            totalAmountResult.setText(t_Amount.toString());
        }
    }

    public void setButtonListener()
    {
        Button newEMIButton=(Button)findViewById(R.id.another_EMI_button);
        newEMIButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent2);
            }
        });
    }
}
