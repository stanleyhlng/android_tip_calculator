package com.stanleyhlng.demo.android_tip_calculator;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TipCalculatorActivity extends Activity {

	private static final String TAG = "TipCalculatorActivity";
	private EditText checkTotalView;
	private TextView tipView;
	private TextView totalView;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);
        
        Log.d(TAG, "Activity has been started");
        
        checkTotalView = (EditText) findViewById(R.id.etCheckTotal);
        tipView = (TextView) findViewById(R.id.tvTip);
        totalView = (TextView) findViewById(R.id.tvTotal);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tip_calculator, menu);
        return true;
    }

    public void calculateTip(View v) {
    	Log.d(TAG, String.format("Calculate Tip"));

    	// Check for valid check total.
    	String checkTotalString = checkTotalView.getText().toString().trim();
    	if (TextUtils.isEmpty(checkTotalString)) {
        	Log.e(TAG, String.format("check_total = %s", checkTotalString));
    		checkTotalView.setError(getString(R.string.error_field_required));
    		return;
    	}
    	Log.d(TAG, String.format("check_total = %s", checkTotalString));

    	// Get check total
    	float checkTotal = Float.valueOf(checkTotalString);
    	
    	// Get tip percentage
    	String tipPercentageString = ((Button) v).getText().toString().trim();
    	Log.d(TAG, String.format("tip_percentage: %s", tipPercentageString));

    	// Get tip
    	float tip = 0;    	
    	if (tipPercentageString.equals("10%")) {
    		tip = checkTotal * 10 / 100;
    	} else if (tipPercentageString.equals("15%")) {
    		tip = checkTotal * 15 / 100;   		
    	} else if (tipPercentageString.equals("20%")) {
    		tip = checkTotal * 20 / 100;
    	}
    	
    	// Get total
    	float total = checkTotal + tip;
    	
    	// Update tip
    	tipView.setText(String.format("$%.2f", tip));
    
    	// Update total
    	totalView.setText(String.format("$%.2f", total));
    }
}
