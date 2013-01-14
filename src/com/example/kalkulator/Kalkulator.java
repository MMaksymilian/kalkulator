package com.example.kalkulator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kalkulator.enums.ButtonTask;

public class Kalkulator extends Activity {

	private class OnCalculatorButtonClick implements OnClickListener {
		@Override
		public void onClick(View view) {
			Button button = (Button) view;
			int enumNumber = Integer.parseInt((String) button.getTag());
			ButtonTask buttonTask = ButtonTask.values()[enumNumber];
			switch (buttonTask) {
			case ADD:
			case SUBTRACT:
			case DIVIDE:
			case MULTIPLY:
				if (secondNumber != null) {
					countResult();
				}
				operation = buttonTask;
				decimal = false;
				decimalPoint = 0;
				break;
			case DECIMAL:
				decimal = true;
				break;
			case CANCEL:
				initOrResetFields();
				resultText.setText(firstNumber.toString());
				break;
			case EQUALS:
				countResult();
				break;
			case ZERO:
				addDigit(0);
				break;
			case ONE:
				addDigit(1);
				break;
			case TWO:
				addDigit(2);
				break;
			case THREE:
				addDigit(3);
				break;
			case FOUR:
				addDigit(4);
				break;
			case FIVE:
				addDigit(5);
				break;
			case SIX:
				addDigit(6);
				break;
			case SEVEN:
				addDigit(7);
				break;
			case EIGHT:
				addDigit(8);
				break;
			case NINE:
				addDigit(9);
				break;
			}
		}

		private void countResult() {
			if(firstNumber == null || secondNumber == null || operation == null) {
				return;
			}
			Double result = null;
			switch(operation) {
				case ADD:
					result = firstNumber + secondNumber;
					break;
				case SUBTRACT:
					result = firstNumber - secondNumber;
					break;
				case DIVIDE:
					if(secondNumber == 0) {
						secondNumber = 1d;
						Toast.makeText(Kalkulator.this, "Nie dzieli siê przez zero matole!", Toast.LENGTH_LONG).show();
					}
					result = firstNumber / secondNumber;
					break;
				case MULTIPLY:
					result = firstNumber * secondNumber;
					break;
			}
			firstNumber = result;
			resultText.setText(result.toString());
			secondNumber = null;
			operation = null;
			decimal = false;
			decimalPoint = 0;
		}

		private void addDigit(Integer keyValue) {
			if(operation != null && decimal == false) {
				secondNumber = (secondNumber == null ? 0d:secondNumber*10) + keyValue.doubleValue();
				resultText.setText(secondNumber.toString());
			}
			if(operation != null && decimal == true) {
				decimalPoint++;
			    secondNumber = (secondNumber == null ? 0d: secondNumber) + keyValue/ Math.pow(10, decimalPoint);
			    resultText.setText(secondNumber.toString());
			}
			if(operation == null && decimal == false) {
				firstNumber = (firstNumber == null ? 0d: firstNumber*10) + keyValue.doubleValue();
				resultText.setText(firstNumber.toString());
			}
			if(operation == null && decimal == true) {
				decimalPoint++;
				firstNumber = (firstNumber == null ? 0d: firstNumber) + keyValue/Math.pow(10, decimalPoint);
				resultText.setText(firstNumber.toString());
			}
		}
	}

	private TextView resultText;
	private Double firstNumber;
	private Double secondNumber;
	private ButtonTask operation;
	private boolean decimal;
	private int decimalPoint = 0;
	{
		initOrResetFields();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_kalkulator, menu);
		return true;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kalkulator);
		resultText = (TextView) findViewById(R.id.result_text);
		resultText.setText(firstNumber.toString());
		addOnClickListeners();
	}

	private void addOnClickListeners() {
		TableLayout tableLayout = (TableLayout) findViewById(R.id.buttons_table);
		int rowCount = tableLayout.getChildCount();
		for (int rowNum = 0; rowNum < rowCount; rowNum++) {
			TableRow row = ((TableRow) tableLayout.getChildAt(rowNum));
			int colCount = row.getChildCount();
			for (int colNum = 0; colNum < colCount; colNum++) {
				Button button = (Button) row.getChildAt(colNum);
				button.setOnClickListener(new OnCalculatorButtonClick());
			}
		}
	}

	private void initOrResetFields() {
		firstNumber = 0d;
		secondNumber = null;
		operation = null;
		decimal = false;
		decimalPoint = 0;
	}
}
