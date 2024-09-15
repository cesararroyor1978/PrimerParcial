package com.example.primerparcial;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNumbers;
    private RadioGroup radioGroupOptions;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNumbers = findViewById(R.id.editTextNumbers);
        radioGroupOptions = findViewById(R.id.radioGroupOptions);
        textViewResult = findViewById(R.id.textViewResult);
        Button buttonResult = findViewById(R.id.buttonResult);

        buttonResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation();
            }
        });
    }

    private void performOperation() {
        String input = editTextNumbers.getText().toString();
        List<Integer> numbers = Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        int selectedOptionId = radioGroupOptions.getCheckedRadioButtonId();
        if (selectedOptionId == R.id.radioButtonCountEven) {
            long count = numbers.stream().filter(n -> n % 2 == 0).count();
            textViewResult.setText("Cantidad de números pares: " + count);
        } else if (selectedOptionId == R.id.radioButtonRemoveOdd) {
            List<Integer> evenNumbers = numbers.stream().filter(n -> n % 2 == 0).collect(Collectors.toList());
            textViewResult.setText("Números pares: " + evenNumbers);
        } else if (selectedOptionId == R.id.radioButtonSum) {
            int sum = numbers.stream().mapToInt(Integer::intValue).sum();
            textViewResult.setText("Suma de los números: " + sum);
        }
    }

    private void calculateAverage() {
        String input = editTextNumbers.getText().toString();
        List<Integer> numbers = Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        double average = numbers.stream().mapToInt(Integer::intValue).average().orElse(0);
        textViewResult.setText("Media de los números: " + average);
    }

    private void sortNumbers() {
        String input = editTextNumbers.getText().toString();
        List<Integer> numbers = Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .sorted()
                .collect(Collectors.toList());
        textViewResult.setText("Números ordenados: " + numbers);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mnuMedia) {
            calculateAverage();
        }
        if (id == R.id.mnuOrdenar) {
            sortNumbers();
        }
        return super.onOptionsItemSelected(item);
    }

}
