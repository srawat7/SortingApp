package shivamrawat.sortingapp.Adapter;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import java.util.ArrayList;

/**
 * Created by Shivam on 1/24/2016.
 */
public class SortAdapter implements ListAdapter {

    private ArrayList<DataSetObserver> observers = new ArrayList<DataSetObserver>();
    private ArrayList<Integer> mNumbserList;

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        observers.add(observer);
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        observers.remove(observer);
    }

    public void notifyDataSetChange() {
        for (DataSetObserver d : observers) {
            d.onChanged();
        }
    }

    public void remove(int position) {
        observers.remove(position);
        notifyDataSetChange();
    }

    @Override
    public int getCount() {
        return mNumbserList.size();
    }

    @Override
    public Object getItem(int position) {
        return mNumbserList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
