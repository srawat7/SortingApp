package shivamrawat.sortingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import shivamrawat.sortingapp.Sorts.BucketSort;
import shivamrawat.sortingapp.Sorts.HeapSort;

public class MainActivity extends AppCompatActivity {

    private EditText numberTV;
    private Button heapButton;
    private Button bucketButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberTV = (EditText) findViewById(R.id.array_length);
        heapButton = (Button) findViewById(R.id.heap_sort);
        bucketButton = (Button) findViewById(R.id.bucket_sort);

        bucketButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Integer> numbers = generateRandomArray();
                if(numbers != null) {

                    Intent intent = new Intent(MainActivity.this, BucketSort.class);
                    intent.putIntegerArrayListExtra("numbersArray", numbers);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Enter a valid number.", Toast.LENGTH_LONG).show();
                }
            }
        });

        heapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Integer> numbers = generateRandomArray();
                if(numbers != null) {
                    Intent intent = new Intent(MainActivity.this, HeapSort.class);
                    intent.putIntegerArrayListExtra("numbersArray", numbers);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Enter a valid number.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public ArrayList<Integer> generateRandomArray(){
        ArrayList<Integer> list = new ArrayList<Integer>();
        int arrayLength;

        if(numberTV.getText() != null && numberTV.length() != 0){
            arrayLength = Integer.valueOf(numberTV.getText().toString());
        } else {
            arrayLength = -1;
        }

        if(arrayLength != -1){
            Random r = new Random();
            for(int i = 0; i < arrayLength; i++)
            {
                int i1 = r.nextInt(100 - 1);
                list.add(i1);

            }

            return list;
        } else {
            return null;
        }
    }
}
