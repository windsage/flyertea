package com.chao.flyertea;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chao.flyertea.bean.Bank;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Bank> list;


    public MyAdapter(Context mContext, ArrayList<Bank> list) {
        this.mContext = mContext;
        this.list = list;
    }

    public void setList(ArrayList<Bank> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return (list != null && list.size() > 0) ? list.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.layout_item, parent, false);
            holder = new ViewHolder();
            holder.icon = view.findViewById(R.id.icon);
            holder.bankName = view.findViewById(R.id.bank_name);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }
        Bank bank = list.get(position);
        holder.bankName.setText(bank.getName());
        Glide.with(mContext).load(bank.getIcon()).into(holder.icon);
        return view;
    }

    class ViewHolder {
        ImageView icon;
        TextView bankName;
    }
}
