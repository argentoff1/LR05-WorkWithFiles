package com.example.workwithfiles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private final static String FILE_NAME = "content.txt";
    private float number;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editTextTextPersonName2);
    }

    public void sumInUSD(View view) {
        number = Float.parseFloat(editText.getText().toString());
        number *= 0.016;
        editText.setText(String.valueOf(number));
    }

    public void sumInEuro(View view) {
        number = Float.parseFloat(editText.getText().toString());
        number *= 0.017;
        editText.setText(String.valueOf(number));
    }

    public void sumInJPY(View view) {
        number = Float.parseFloat(editText.getText().toString());
        number *= 2.36;
        editText.setText(String.valueOf(number));
    }

    // сохранение файла
    public void saveText(View view) {
        FileOutputStream fos = null;

        try {
            EditText textBox = (EditText) findViewById(R.id.editTextTextPersonName2);
            String text = textBox.getText().toString();
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(text.getBytes());
            Toast.makeText(this, "Файл сохранен",
                    Toast.LENGTH_SHORT).show();
        } catch (IOException ex) {
            Toast.makeText(this, ex.getMessage(),
                    Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (fos != null) fos.close();
            } catch (IOException ex) {
                Toast.makeText(this, ex.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    // открытие файла
    public void openText(View view) throws FileNotFoundException {
        FileInputStream fin = null;
        TextView textView = (TextView) findViewById(R.id.text);
        try {
            fin = openFileInput(FILE_NAME);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String(bytes);
            textView.setText(text);
        } catch (IOException ex) {
            Toast.makeText(this, ex.getMessage(),
                    Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (fin != null) fin.close();
            } catch (IOException ex) {
                Toast.makeText(this, ex.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }

        }

        FileOutputStream fos = openFileOutput(FILE_NAME,
                MODE_APPEND);
    }
}