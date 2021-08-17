package com.example.coffeeorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button increase, decrease, order;
    TextView showQuantity, perCup;
    EditText name;
    CheckBox whippedCream, chocolateCream;
    int cup, price;
    String buyer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        increase = findViewById(R.id.increaseButton);
        decrease = findViewById(R.id.decreaseButton);
        order = findViewById(R.id.orderSum);
        whippedCream = findViewById(R.id.whippedCream);
        chocolateCream = findViewById(R.id.chocolateCream);
        name = findViewById(R.id.name);
        showQuantity = findViewById(R.id.showQuantity);
        perCup = findViewById(R.id.perCup);

        price = 5;
        cup = Integer.parseInt(showQuantity.getText().toString());
    }

    public void increaseCup(View view) {
        cup++;
        price = cup * 5;
        showQuantity.setText(cup + "");
        perCup.setText(price + "/-");
    }

    public void decreaseCup(View view) {
        if(cup != 1) {
            cup--;
            price = cup * 5;
        }
        showQuantity.setText(cup + "");
        perCup.setText(price + "/-");
    }

    public void OrderSum(View view) {
        if(whippedCream.isChecked())
            price += 1;
        if(chocolateCream.isChecked())
            price += 2;
        buyer = name.getText().toString();
        String sum = " Thank you " + buyer;
        sum += "\n Whipped cream ? " + whippedCream.isChecked();
        sum += "\n Chocolate cream ? " + chocolateCream.isChecked();
        sum += "\n Quantity " + cup + " cup";
        sum += "\n Total " + price + "/-";
        sum += "\n Enjoy your coffee";

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee for " + buyer);
        intent.putExtra(Intent.EXTRA_TEXT, sum);
        if(intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);
    }

}