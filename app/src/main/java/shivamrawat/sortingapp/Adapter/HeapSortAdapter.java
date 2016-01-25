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

/**
 * Created by Shivam on 1/24/2016.
 */
public class HeapSortAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<Integer> mList;

    public HeapSortAdapter(Activity activity, ArrayList<Integer> numberList){
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
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.grid_element, null);
        }
        TextView title = (TextView) convertView.findViewById(R.id.number);
        title.setText(String.valueOf(mList.get(position)));
        return convertView;
    }
}
