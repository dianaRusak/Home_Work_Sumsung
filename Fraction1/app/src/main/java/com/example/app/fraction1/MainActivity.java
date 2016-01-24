package com.example.app.fraction1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.*;

public class MainActivity extends AppCompatActivity {
    public int summZn, summCh, chO, znO, ch1, zn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1 = (Button) findViewById(R.id.button);
        final EditText editText = (EditText) findViewById(R.id.editText);
        final EditText editText1 = (EditText) findViewById(R.id.editText2);
        final EditText editText2 = (EditText) findViewById(R.id.editText3);
        final EditText editText3 = (EditText) findViewById(R.id.editText4);
        final TextView text = (TextView) findViewById(R.id.textView4);
        final TextView text1 = (TextView) findViewById(R.id.textView5);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        final String selected = spinner.getSelectedItem().toString();
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = editText.getText().toString();
                chO = Integer.parseInt(a);
                String b = editText1.getText().toString();
                znO = Integer.parseInt(b);
                String c = editText2.getText().toString();
                ch1 = Integer.parseInt(c);
                String d = editText3.getText().toString();
                zn1 = Integer.parseInt(d);
                summZn = gcd(znO, zn1) ;
                text1.setText(Integer.toString(summZn));
                switch (selected) {
                    case "summation":
                        summCh = summZn / znO * chO + summZn / zn1 * ch1;
                        text.setText(Integer.toString(summCh));
                        break;

                    case "subtractionn":
                        summCh = ch1 * chO;
                        text.setText(Integer.toString(summCh));
                        break;

                    case "division":
                        summCh = chO * zn1;
                        text.setText(Integer.toString(summCh));
                        break;

                    case "multiplicatio":
                        summCh = summZn / znO * chO - summZn / zn1 * ch1;
                        text.setText(Integer.toString(summCh));
                        break;
                }
            }
        });
    }

    int gcd (int a, int b) {
        if (b == 0)
            return a;
        else
            return gcd (b, a % b);
    }
}