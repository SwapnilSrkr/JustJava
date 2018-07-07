package com.example.android.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.android.justjava.R.id.spinner1;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    int quantity = 1;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner mySpinner = (Spinner) findViewById(spinner1);

        ArrayAdapter<CharSequence> myAdapter = ArrayAdapter.createFromResource(this, R.array.names, android.R.layout.simple_spinner_item);

        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);
        mySpinner.setOnItemSelectedListener(this);


    }


    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText nameField = (EditText) findViewById(R.id.name_field);
        String name = nameField.getText().toString();

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.check1);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox ChocolateCheckBox = (CheckBox) findViewById(R.id.check2);
        boolean hasChocolate = ChocolateCheckBox.isChecked();

        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String priceMessage = createOrderSummary(name, price, hasWhippedCream, hasChocolate);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee order for " + name);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "xyzcoffeeshop@gmail.com" });
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

   private String createOrderSummary(String name, int price, boolean addWhippedCream, boolean addChocolate){
        String priceMessage = "Name: " + name;
        priceMessage = priceMessage + "\nAdd whipped cream? " + addWhippedCream;
        priceMessage = priceMessage + "\nAdd chocolate? " + addChocolate;
        priceMessage = priceMessage + "\nQuantity: " + quantity;
        priceMessage = priceMessage + "\nTotal: $" + price;
        priceMessage = priceMessage + "\nThank you!";
        return priceMessage;
    }

    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        int price = 0;
        if (text.equals("Espresso")) {
            if(addWhippedCream == true && addChocolate == true)
            {
                price = quantity * 5;
            }
            else if(addWhippedCream == true && addChocolate == false) {

                price = quantity * 3;
            }
            else if(addWhippedCream == false && addChocolate == true) {

                price = quantity * 4;
            }
            else {
                price = (quantity * 2);
            }
        } else if (text.equals("Macchiato")) {
            if(addWhippedCream == true && addChocolate == true)
            {
                price = quantity * 6;
            }
            else if(addWhippedCream == true && addChocolate == false) {

                price = quantity * 4;
            }
            else if(addWhippedCream == false && addChocolate == true) {

                price = quantity * 5;
            }
            else {
                price = (quantity * 3);
            }
        } else if (text.equals("Cafe Latte")) {
            if(addWhippedCream == true && addChocolate == true)
            {
                price = quantity * 7;
            }
            else if(addWhippedCream == true && addChocolate == false) {

                price = quantity * 5;
            }
            else if(addWhippedCream == false && addChocolate == true) {

                price = quantity * 6;
            }
            else {
                price = (quantity * 4);
            }
        } else if (text.equals("Cappuccino")) {
            if(addWhippedCream == true && addChocolate == true)
            {
                price = quantity * 8;
            }
            else if(addWhippedCream == true && addChocolate == false) {

                price = quantity * 6;
            }
            else if(addWhippedCream == false && addChocolate == true) {

                price = quantity * 7;
            }
            else {
                price = (quantity * 5);
            }
        } else if (text.equals("Mocha")) {
            if(addWhippedCream == true && addChocolate == true)
            {
                price = quantity * 9;
            }
            else if(addWhippedCream == true && addChocolate == false) {

                price = quantity * 7;
            }
            else if(addWhippedCream == false && addChocolate == true) {

                price = quantity * 8;
            }
            else {
                price = (quantity * 6);
            }
        }
        return price;
    }

    public void increment(View view) {
        if (quantity == 100) {
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

   public void decrement(View view) {
        if (quantity == 1) {
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }
            quantity = quantity - 1;
            displayQuantity(quantity);

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Spinner mySpinner = (Spinner)findViewById(R.id.spinner1);
        text = mySpinner.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

}