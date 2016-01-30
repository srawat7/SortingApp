package shivamrawat.sortingapp.Sorts;

import android.os.AsyncTask;
import android.widget.GridView;

import java.util.ArrayList;

import shivamrawat.sortingapp.Adapter.HeapSortAdapter;
import shivamrawat.sortingapp.Sorter;

/**
 * Created by Shivam on 1/25/2016.
 */
public class BucketAsyncTask extends AsyncTask<Object, Object, Object> implements Sorter {

    private static ArrayList<Integer> numbersToSort;
    private GridView gridView;
    private HeapSortAdapter adapter;
    private static ArrayList<Integer> sorted_sequence;
    private int maxValue;

    private static int changeI;
    private static int changeJ;

    @Override
    public void sort(ArrayList<Integer> sequence) {

        for (int i = 0; i < sequence.size(); i++) {
            changeI = i;
            changeJ = sequence.get(i);
            publishProgress();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sorted_sequence.set(sequence.get(i), sorted_sequence.get(sequence.get(i)) + 1);
            numbersToSort.set(i, 0);
            publishProgress();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int outPos = 0;
        for (int i = 0; i < sorted_sequence.size(); i++) {
            int index = sorted_sequence.get(i);
            for (int j = 0; j < index; j++) {
                changeI = outPos;
                changeJ = i;

                publishProgress();

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                numbersToSort.set(outPos++, i);
                sorted_sequence.set(i, sorted_sequence.get(i) - 1);
             //   numbersToSort.set(index, 0);
                publishProgress();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public interface SortObserver {
        public void onChange(int i, int j);
    }

    public static SortObserver mCallback;

    public BucketAsyncTask(ArrayList<Integer> numbersToSort, ArrayList<Integer> sorted_sequence, SortObserver callback){
        this.numbersToSort = numbersToSort;
        this.sorted_sequence = sorted_sequence;
        this.mCallback = callback;
        this.maxValue = maxValue((numbersToSort));
    }

    @Override
    protected Object doInBackground(Object... params) {
        sort(numbersToSort);
        return null;
    }

    @Override
    public void onPostExecute(Object result) {
        //  delegate.processFinish(i, j);
    }

    @Override
    public void onProgressUpdate(Object... result){
        super.onProgressUpdate(result);
        mCallback.onChange(changeI, changeJ);
    }

    static int maxValue(ArrayList<Integer> sequence)
    {
        int maxValue = 0;
        for(Integer value: sequence){
            if(value > maxValue)
                maxValue = value;
        }
        return maxValue;
    }
}
