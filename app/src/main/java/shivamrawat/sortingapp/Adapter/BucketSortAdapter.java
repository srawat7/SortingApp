package shivamrawat.sortingapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import shivamrawat.sortingapp.R;
import shivamrawat.sortingapp.Sorts.BucketSort;

/**
 * Created by Shivam on 1/28/2016.
 */
public class BucketSortAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<Integer> mList;

    public BucketSortAdapter(Activity activity, ArrayList<Integer> numberList) {
        this.activity = activity;
        this.mList = numberList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.grid_element, null);
        }
        TextView title = (TextView) convertView.findViewById(R.id.number);
        title.setText(String.valueOf(mList.get(position)));
        if(position == BucketSort.setI)
            title.setBackgroundResource(R.drawable.redbox);
        else
            title.setBackgroundResource(R.drawable.box);


        return convertView;
    }
}
