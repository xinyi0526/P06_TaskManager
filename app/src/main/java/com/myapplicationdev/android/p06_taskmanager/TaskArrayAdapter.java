package com.myapplicationdev.android.p06_taskmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class TaskArrayAdapter extends ArrayAdapter<Task> {

    Context context;
    ArrayList<Task> tasks;
    int resource;

    public TaskArrayAdapter( Context context, int resource, ArrayList<Task> tasks) {
        super(context, resource, tasks);
        this.context = context;
        this.tasks = tasks;
        this.resource = resource;
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(resource, parent, false);

        TextView tvId = (TextView) rowView.findViewById(R.id.tvId);
        TextView tvName = (TextView) rowView.findViewById(R.id.tvTaskName);
        TextView tvDesc = (TextView) rowView.findViewById(R.id.tvDesc);

        Task task = tasks.get(position);
        tvId.setText(""+task.getId());
        tvName.setText(task.getTaskName());
        tvDesc.setText(task.getDescription());
        return rowView;
    }
}
