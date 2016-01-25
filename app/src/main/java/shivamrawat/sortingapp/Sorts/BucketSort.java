package shivamrawat.sortingapp.Sorts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import java.util.ArrayList;

import shivamrawat.sortingapp.Adapter.HeapSortAdapter;
import shivamrawat.sortingapp.R;

/**
 * Created by Shivam on 1/25/2016.
 */
public class BucketSort extends AppCompatActivity implements BucketAsyncTask.SortObserver {

    private static ArrayList<Integer> numbersToSort;
    private static ArrayList<Integer> sorted_sequence;
    private GridView gridView;
    private GridView gridView2;
    private HeapSortAdapter adapter;
    private HeapSortAdapter adapter2;

    private final String BUCKET_TAG = "display_list_fragment_tag";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bucket_sort);

        Intent intent = getIntent();
        numbersToSort = intent.getIntegerArrayListExtra("numbersArray");

        sorted_sequence = new ArrayList<Integer>();
        for (Integer number : numbersToSort) {
            sorted_sequence.add(0);
        }

        gridView = (GridView) findViewById((R.id.gridView));
        adapter = new HeapSortAdapter(BucketSort.this, numbersToSort);
        gridView.setAdapter(adapter);

        gridView2 = (GridView) findViewById(R.id.gridView_sorted);
        adapter2 = new HeapSortAdapter(BucketSort.this, sorted_sequence);
        gridView2.setAdapter(adapter2);

        BucketAsyncTask task = (BucketAsyncTask) new BucketAsyncTask(numbersToSort, sorted_sequence, new BucketAsyncTask.SortObserver() {
            @Override
            public void onChange() {
                runOnUiThread(new Runnable() // while debugging, it comes here, on Step Over it stick for 2 times and then move at the end of method without error
                {
                    public void run() {
                        adapter.notifyDataSetChanged();
                        adapter2.notifyDataSetChanged();
                    }
                });
            }
        }).execute();

    }

    @Override
    public void onChange() {

    }

    @Override
    public void onBackPressed(){
        finish();
        super.onBackPressed();
    }
}
