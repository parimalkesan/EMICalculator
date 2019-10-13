package parimal.examples.emicalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import static java.sql.Types.NULL;

public class MainActivity extends AppCompatActivity {

    Double interestRate;
    Double interestRatepermonth;
    public static final String EMIcalculated="emi";
    public static final String interestcalculated="interest";
    public static final String amountcalculated="amount";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinnerItemSelected();
        setListenerOnButton();
    }

    public void spinnerItemSelected()
    {
        Spinner loanTypeSpinner=(Spinner)findViewById(R.id.loan_type_spinner);
        final TextView interestRateTextView=(TextView)findViewById(R.id.interest_rate_textView);
        loanTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Object selectedItem=parent.getItemAtPosition(position);
                String loanType=selectedItem.toString();
                if(loanType.equals("Home Loan")) {
                    interestRateTextView.setText("10.5");
                    interestRate=10.5;
                }
                else if(loanType.equals("Vehicle Loan")) {
                    interestRateTextView.setText("12.5");
                    interestRate=12.5;
                }
                else if(loanType.equals("Education Loan")) {
                    interestRateTextView.setText("9.5");
                    interestRate=9.5;
                }
                else if(loanType.equals("Personal Loan")) {
                    interestRateTextView.setText("17.5");
                    interestRate = 17.5;
                }

                else if(loanType.equals("Business Loan")) {
                    interestRateTextView.setText("19.5");
                    interestRate=19.5;
                }
                else if(loanType.equals("Gold Loan")){
                    interestRateTextView.setText("15.5");
                    interestRate=15.5;
                }
                interestRatepermonth=interestRate/1200;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    public void setListenerOnButton()
    {
        Button calculateEMIButton=(Button)findViewById(R.id.calculate_EMI_button);
        final EditText loanAmountEdt=(EditText)findViewById(R.id.loan_amount_editText);
        final EditText loanTenureEdt=(EditText)findViewById(R.id.loan_tenure_editText);
        calculateEMIButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try
                {
                    Double amount=Double.parseDouble(loanAmountEdt.getText().toString());
                    Double tenure=(Double.parseDouble(loanTenureEdt.getText().toString()));
                    Double tenureInMonths=tenure*12;
                    if(amount!=null && tenure!=null) {
                        Double power = Math.pow(1 + interestRatepermonth, tenureInMonths);
                        Double power1 = power - 1;
                        Double calculatedEmi = (amount * interestRatepermonth * power) / power1;
                        calculatedEmi=Math.round(calculatedEmi*100.0)/100.0;
                        Double totalAmount = calculatedEmi*tenureInMonths;
                        totalAmount=Math.round(totalAmount*100.0)/100.0;
                        Double calculatedInterest = totalAmount-amount;
                        calculatedInterest=Math.round(calculatedInterest*100.0)/100.0;
                        Intent intent = new Intent(getApplicationContext(), EMIDisplay.class);
                        Bundle args=new Bundle();
                        args.putDouble(EMIcalculated,calculatedEmi);
                        args.putDouble(interestcalculated,calculatedInterest);
                        args.putDouble(amountcalculated,totalAmount);
                        intent.putExtras(args);
                        startActivity(intent);
                    }
                }
                catch(Exception e)
                {
                    Toast.makeText(getApplicationContext(),"Please enter required details",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
