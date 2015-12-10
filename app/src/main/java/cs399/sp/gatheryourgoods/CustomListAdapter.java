package cs399.sp.gatheryourgoods;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Alex on 11/14/2015.
 */
public class CustomListAdapter extends BaseAdapter {
    public ArrayList<Item> listData;
    public LayoutInflater layoutInflater;

    public CustomListAdapter(Context aContext, ArrayList<Item> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_row_layout, null);
            holder = new ViewHolder();
            holder.itemNameView = (TextView) convertView.findViewById(R.id.name);
            holder.itemCategoryView = (TextView) convertView.findViewById(R.id.category);
            holder.itemAmountView = (TextView) convertView.findViewById(R.id.amount);
            holder.checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);
            holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int getPosition = (Integer) buttonView.getTag();
                    listData.get(getPosition).setSelected(buttonView.isChecked());

                }
            });
            convertView.setTag(holder);
            convertView.setTag(R.id.checkBox, holder.checkBox);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.checkBox.setTag(position);
        holder.checkBox.setChecked(listData.get(position).isSelected());
        holder.itemNameView.setText(listData.get(position).getItemName());
        holder.itemCategoryView.setText(listData.get(position).getItemCategory());
        holder.itemAmountView.setText(listData.get(position).getItemAmount());


        return convertView;
    }

    static class ViewHolder {
        TextView itemNameView;
        TextView itemCategoryView;
        TextView itemAmountView;
        protected CheckBox checkBox;

    }
}
