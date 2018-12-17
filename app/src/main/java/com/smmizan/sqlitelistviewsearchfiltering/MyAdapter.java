package com.smmizan.sqlitelistviewsearchfiltering;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Mizan on 16/12/2018.
 */

public class MyAdapter extends ArrayAdapter<MyModel> {
    public ArrayList<MyModel> MainList;

    public ArrayList<MyModel> myModels;

    public MyAdapter.DataFiltering dataFiltering;

    public MyAdapter(Context context, int id, ArrayList<MyModel> studentArrayList) {

        super(context, id, studentArrayList);

        this.myModels = new ArrayList<MyModel>();

        this.myModels.addAll(studentArrayList);

        this.MainList = new ArrayList<MyModel>();

        this.MainList.addAll(studentArrayList);
    }

    @Override
    public Filter getFilter() {

        if (dataFiltering == null) {

            dataFiltering = new MyAdapter.DataFiltering();
        }
        return dataFiltering;
    }


    public class ViewHolder {

        TextView Name;
        TextView Number;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MyAdapter.ViewHolder holder = null;

        if (convertView == null) {

            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = layoutInflater.inflate(R.layout.custom_layout, null);

            holder = new MyAdapter.ViewHolder();

            holder.Name = (TextView) convertView.findViewById(R.id.textviewName);

            holder.Number = (TextView) convertView.findViewById(R.id.textviewPhoneNumber);

            convertView.setTag(holder);

        } else {

            holder = (MyAdapter.ViewHolder) convertView.getTag();
        }

        MyModel student = myModels.get(position);

        holder.Name.setText(student.getName());

        holder.Number.setText(student.getCode());

        return convertView;

    }

    private class DataFiltering extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            charSequence = charSequence.toString().toLowerCase();

            FilterResults filterResults = new FilterResults();

            if (charSequence != null && charSequence.toString().length() > 0) {

                ArrayList<MyModel> arrayList1 = new ArrayList<MyModel>();

                for (int i = 0, l = MainList.size(); i < l; i++) {
                    MyModel subject = MainList.get(i);

                    if (subject.toString().toLowerCase().contains(charSequence))

                        arrayList1.add(subject);
                }
                filterResults.count = arrayList1.size();

                filterResults.values = arrayList1;
            } else {
                synchronized (this) {
                    filterResults.values = MainList;

                    filterResults.count = MainList.size();
                }
            }
            return filterResults;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

            myModels = (ArrayList<MyModel>) filterResults.values;

            notifyDataSetChanged();

            clear();

            for (int i = 0, l = myModels.size(); i < l; i++)
                add(myModels.get(i));

            notifyDataSetInvalidated();
        }
    }
}
