package shivamrawat.sortingapp.Sorts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

import shivamrawat.sortingapp.Adapter.HeapSortAdapter;
import shivamrawat.sortingapp.R;

/**
 * Created by Shivam on 1/24/2016.
 */
public class HeapSort extends AppCompatActivity implements HeapAsyncTask.SortObserver {

    private static ArrayList<Integer> numbersToSort;
    private GridView gridView;
    private HeapSortAdapter adapter;
    HeapAsyncTask task;

    public static int setI = -1;
    public static int setJ = -1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heap_sort);

        Intent intent = getIntent();
        numbersToSort = intent.getIntegerArrayListExtra("numbersArray");

        gridView = (GridView) findViewById((R.id.gridViewHeap));
        adapter = new HeapSortAdapter(HeapSort.this, numbersToSort);
        gridView.setAdapter(adapter);

        task =  (HeapAsyncTask) new HeapAsyncTask(numbersToSort, new HeapAsyncTask.SortObserver() {
            @Override
            public void onChange(final int i,final int j) {
                runOnUiThread(new Runnable()
                {
                    public void run()
                    {
                        setI = i;
                        setJ = j;
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).execute();

    }

    @Override
    public void onChange(int i, int j) {
        Toast.makeText(HeapSort.this, "In here", Toast.LENGTH_LONG).show();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed(){
        setI = -1;
        setJ = -1;
        numbersToSort.clear();
        adapter.notifyDataSetChanged();
        task.cancel(true);
        finish();
        super.onBackPressed();
    }
}

