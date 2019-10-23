package com.example.jsonlisview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<Json> {

    private Context context;
    private int resource;
    private List<Json> arrjson;


    public CustomAdapter(Context context, int resource, List<Json> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.arrjson = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listview,parent,false);

            viewHolder = new ViewHolder();
            viewHolder.tvName = (TextView)convertView.findViewById(R.id.tvName);
            viewHolder.tvUserName = (TextView)convertView.findViewById(R.id.tvUserName);
            viewHolder.tvEmail = (TextView)convertView.findViewById(R.id.tvEmail);
            viewHolder.tvPhone = (TextView)convertView.findViewById(R.id.tvPhone);
            viewHolder.tvAddress = (TextView)convertView.findViewById(R.id.tvAddress);
            viewHolder.tvCompany = (TextView)convertView.findViewById(R.id.tvCompany);
            viewHolder.tvId= (TextView)convertView.findViewById(R.id.tvId);
            viewHolder.tvWebsite = (TextView)convertView.findViewById(R.id.tvWebsite);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Json json = arrjson.get(position);
        viewHolder.tvId.setText(json.getId());
        viewHolder.tvName.setText(json.getName());
        viewHolder.tvUserName.setText(json.getUsername());
        viewHolder.tvPhone.setText(json.getPhone());
        viewHolder.tvEmail.setText(json.getEmail());
        viewHolder.tvWebsite.setText(json.getWebsite());
        viewHolder.tvCompany.setText(json.getCompany());
        viewHolder.tvAddress.setText(json.getAddrres());

        return convertView;
    }

    public class ViewHolder {
        TextView tvName, tvUserName, tvPhone, tvCompany, tvAddress, tvEmail, tvId, tvWebsite;
    }

}

