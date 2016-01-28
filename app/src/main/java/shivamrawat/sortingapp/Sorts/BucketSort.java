package shivamrawat.sortingapp.Sorts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import java.util.ArrayList;

import shivamrawat.sortingapp.Adapter.BucketSortAdapter;
import shivamrawat.sortingapp.Adapter.SortAdapter;
import shivamrawat.sortingapp.R;

/**
 * Created by Shivam on 1/25/2016.
 */
public class BucketSort extends AppCompatActivity implements BucketAsyncTask.SortObserver {

    private static ArrayList<Integer> numbersToSort;
    private static ArrayList<Integer> sorted_sequence;
    private GridView gridView;
    private GridView gridView2;
    private BucketSortAdapter adapter;
    private SortAdapter adapter2;

    public static int setI = -1;
    public static int setJ = -1;

    private final String BUCKET_TAG = "display_list_fragment_tag";

    BucketAsyncTask task;

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
        adapter = new BucketSortAdapter(BucketSort.this, numbersToSort);
        gridView.setAdapter(adapter);

        gridView2 = (GridView) findViewById(R.id.gridView_sorted);
        adapter2 = new SortAdapter(BucketSort.this, sorted_sequence);
        gridView2.setAdapter(adapter2);

        task = (BucketAsyncTask) new BucketAsyncTask(numbersToSort, sorted_sequence, new BucketAsyncTask.SortObserver() {
            @Override
            public void onChange(final int i, final int j) {
                runOnUiThread(new Runnable()
                {
                    public void run() {
                        setI = i;
                        setJ = j;
                        adapter.notifyDataSetChanged();
                        adapter2.notifyDataSetChanged();
                    }
                });
            }
        }).execute();

    }

    @Override
    public void onChange(int i, int j) {

    }

    @Override
    public void onBackPressed(){
        setI = -1;
        setJ = -1;
        task.cancel(true);
        numbersToSort.clear();
        adapter.notifyDataSetChanged();
        sorted_sequence.clear();
        adapter2.notifyDataSetChanged();
        finish();
        super.onBackPressed();
    }
}
