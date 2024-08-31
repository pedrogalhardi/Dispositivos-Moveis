package com.example.doaoalimentos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class CategoriaExpandableAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> listCategorias;
    private HashMap<String, List<String>> listItems;

    public CategoriaExpandableAdapter(Context context, List<String> listCategorias, HashMap<String, List<String>> listItems) {
        this.context = context;
        this.listCategorias = listCategorias;
        this.listItems = listItems;
    }

    @Override
    public int getGroupCount() {
        return listCategorias.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listItems.get(listCategorias.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listCategorias.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listItems.get(listCategorias.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_categoria_expandable_group, parent, false);
        }
        TextView textView = convertView.findViewById(R.id.groupTextView);
        textView.setText((String) getGroup(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_categoria_expandable_child, null);
        }

        TextView tvItemName = convertView.findViewById(R.id.childTextView);
        TextView tvQuantity = convertView.findViewById(R.id.tvQuantity);
        Button btnIncrement = convertView.findViewById(R.id.btnAddCount);
        Button btnDecrement = convertView.findViewById(R.id.btnDecrement);

        String itemName = (String) getChild(groupPosition, childPosition);
        tvItemName.setText(itemName);

        tvQuantity.setText("0");  // Quantidade inicial

        btnIncrement.setOnClickListener(v -> {
            int quantity = Integer.parseInt(tvQuantity.getText().toString());
            quantity++;
            tvQuantity.setText(String.valueOf(quantity));
        });

        btnDecrement.setOnClickListener(v -> {
            int quantity = Integer.parseInt(tvQuantity.getText().toString());
            if (quantity > 1) {  // Previne a quantidade de ser menor que 1
                quantity--;
                tvQuantity.setText(String.valueOf(quantity));
            }
        });

        return convertView;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
