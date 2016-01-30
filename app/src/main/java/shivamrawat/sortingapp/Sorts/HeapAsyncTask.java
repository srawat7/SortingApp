package shivamrawat.sortingapp.Sorts;

import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;

import shivamrawat.sortingapp.Sorter;

/**
 * Created by Shivam on 1/24/2016.
 */
public class HeapAsyncTask extends AsyncTask<Object, Object, Object> implements Sorter {

    private static int changeI;
    private static int changeJ;

    private static int n;
    private static int left;
    private static int right;
    private static int largest;

    private static ArrayList<Integer> numbersToSort;

    private static ArrayList<Integer> a;

    @Override
    public void sort(ArrayList<Integer> numbers) {
        a = numbers;
        numbersToSort = numbers;
        buildHeap(numbers);

        for (int i = n; i > 0; i--) {
            exchange(0, i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            n = n - 1;
            maxheap(0);

        }
    }

    public interface SortObserver {
        public void onChange(int i, int j);
    }

    public SortObserver mCallback;

    public HeapAsyncTask(ArrayList<Integer> numbers, SortObserver callback){
        this.mCallback = callback;
        this.numbersToSort = numbers;
        this.a = numbers;
    }

    @Override
    public Object doInBackground(Object... params) {
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

    public void maxheap(int index) {
        Log.d("Maxheap ", String.valueOf(index));
        left = 2 * index;
        right = 2 * index + 1;
        System.out.println(index + " " + left + " " + right);
        if (left <= n && a.get(left) > a.get(index)) {
            largest = left;
        } else {
            largest = index;
        }

        if (right <= n && a.get(right) > a.get(largest)) {
            largest = right;
        }

        if (largest != index) {

            exchange(index, largest);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            maxheap(largest);

        }

    }

    public void exchange(final int i, final int j) {
        changeI = i;
        changeJ = j;
        publishProgress();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.d("Exchange called with ", String.valueOf(i) + " " + String.valueOf(j));
        int t = numbersToSort.get(i);
        numbersToSort.set(i, numbersToSort.get(j));
        numbersToSort.set(j, t);


      //  mCallback.onChange();
        publishProgress();

     //   Log.d("Inside exchange with ", String.valueOf(i) + " " + String.valueOf(j));

    }

    public void buildHeap(ArrayList<Integer> a) {

        n = a.size() - 1;
        for (int i = n / 2; i >= 0; i--) {
            maxheap(i);
        }


    }

}
