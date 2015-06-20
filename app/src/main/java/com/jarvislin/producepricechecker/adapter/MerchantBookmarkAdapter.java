package com.jarvislin.producepricechecker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jarvislin.producepricechecker.R;
import com.jarvislin.producepricechecker.util.Preferences_;
import com.jarvislin.producepricechecker.util.ToolsHelper;

import java.util.ArrayList;

import database.Produce;

/**
 * Created by Jarvis Lin on 2015/6/20.
 */
public class MerchantBookmarkAdapter extends CustomerBookmarkAdapter {
    public MerchantBookmarkAdapter(Context context, ArrayList<Produce> list, String kind, Preferences_ pref) {
        super(context, list, kind, pref);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.cell_bookmark_general, parent, false);
            holder = new ViewHolder();

            //find views
            holder.typeName = (TextView) view.findViewById(R.id.type_name);
            holder.delete = (ImageView) view.findViewById(R.id.delete);
            holder.cell = (LinearLayout) view.findViewById(R.id.cell);
            holder.topMid = (TextView) view.findViewById(R.id.top_mid);
            holder.lowAvg = (TextView) view.findViewById(R.id.low_avg);
            holder.date = (TextView) view.findViewById(R.id.date);

            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }

        Produce data = list.get(position);

        //set views
        holder.cell.setBackgroundColor(context.getResources().getColor((position % 2 == 0) ? R.color.white : R.color.odd_row));
        holder.typeName.setText((data.type.length() <= 1) ? data.name : data.type + "\n" + data.name);
        holder.delete.setVisibility(isEditing > 0 ? View.VISIBLE : View.INVISIBLE);
        holder.delete.setOnClickListener(clickDelete(position));
        holder.topMid.setText(data.topPrice + "\n" + data.mediumPrice);
        holder.lowAvg.setText(data.lowPrice + "\n" + data.averagePrice);
        holder.date.setText(ToolsHelper.getOffsetInWords(ToolsHelper.getOffset(data.date)));
        holder.date.setVisibility(isEditing > 0 ? View.INVISIBLE : View.VISIBLE);

        return view;
    }

    private class ViewHolder {
        LinearLayout cell;
        TextView typeName;
        ImageView delete;
        TextView topMid;
        TextView lowAvg;
        TextView date;
    }

}
