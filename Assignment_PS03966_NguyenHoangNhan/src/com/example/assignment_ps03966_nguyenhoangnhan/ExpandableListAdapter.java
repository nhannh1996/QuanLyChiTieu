package com.example.assignment_ps03966_nguyenhoangnhan;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import model.ThongKe;
import model.TongTien;
 
public class ExpandableListAdapter extends BaseExpandableListAdapter {
 
    public Context _context;
    public List<TongTien> _listDataHeader; // header titles
    // child data in format of header title, child title
    public HashMap<String, List<ThongKe>> _listDataChild;
  
    public ExpandableListAdapter(Context context, List<TongTien> listDataHeader,
            HashMap<String, List<ThongKe>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }
 
    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition).getTen())
                .get(childPosititon);
    }
 
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
 
    @Override
    public View getChildView(int groupPosition, final int childPosition,
            boolean isLastChild, View convertView, ViewGroup parent) {
 
        final ThongKe childText = (ThongKe) getChild(groupPosition, childPosition);
 
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.listview_thongke, null);
        }
      //  Item item = (Item) getChild(groupPosition, childPosition);
        
        TextView tvTen = (TextView) convertView
                .findViewById(R.id.textViewTenThongKe);
        TextView tvTien = (TextView) convertView
                .findViewById(R.id.textViewTienThongKe);
       
        tvTen.setText(childText.getTengiaodich());
        tvTien.setText(String.valueOf(childText.getTiengiaodich())+" $");
        return convertView;
    }
    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition).getTen()).size();
    }
 
    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }
 
    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }
 
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
 
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
            View convertView, ViewGroup parent) {
    	TongTien headerTitle = (TongTien) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.listview_groupex, null);
        }
 
        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.textView1);
        TextView tong = (TextView) convertView.findViewById(R.id.textView2);
        lblListHeader.setTypeface(null, Typeface.BOLD);	
        lblListHeader.setText(headerTitle.getTen());
        tong.setText(String.valueOf(headerTitle.getTongtien()));
        return convertView;
    }
 
    @Override
    public boolean hasStableIds() {
        return false;
    }
 
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}