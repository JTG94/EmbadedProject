package com.example.embadedproject;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.embadedproject.model.CategoryData;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustomExpandableListViewAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private CategoryData mParentList;
    public ArrayList<ArrayList<CategoryData.Categorylist_mobile.purchaselist>> pList=new ArrayList<>();
    public ArrayList<CategoryData.Categorylist_mobile.purchaselist> cList=new ArrayList<>();


    // CustomExpandableListViewAdapter 생성자
    public CustomExpandableListViewAdapter(Context context, CategoryData categoryinfo){
        this.mContext = context;
        if(categoryinfo.categorylist_mobile.daylist1!=null)
            pList.add(categoryinfo.categorylist_mobile.daylist1);
        if(categoryinfo.categorylist_mobile.daylist2!=null)
            pList.add(categoryinfo.categorylist_mobile.daylist2);
        if(categoryinfo.categorylist_mobile.daylist3!=null)
            pList.add(categoryinfo.categorylist_mobile.daylist3);
        if(categoryinfo.categorylist_mobile.daylist4!=null)
            pList.add(categoryinfo.categorylist_mobile.daylist4);
        if(categoryinfo.categorylist_mobile.daylist5!=null)
            pList.add(categoryinfo.categorylist_mobile.daylist5);
        if(categoryinfo.categorylist_mobile.daylist6!=null)
            pList.add(categoryinfo.categorylist_mobile.daylist6);
        if(categoryinfo.categorylist_mobile.daylist7!=null)
            pList.add(categoryinfo.categorylist_mobile.daylist7);
        if(categoryinfo.categorylist_mobile.daylist8!=null)
            pList.add(categoryinfo.categorylist_mobile.daylist8);
        if(categoryinfo.categorylist_mobile.daylist9!=null)
            pList.add(categoryinfo.categorylist_mobile.daylist9);
        if(categoryinfo.categorylist_mobile.daylist10!=null)
            pList.add(categoryinfo.categorylist_mobile.daylist10);
        if(categoryinfo.categorylist_mobile.daylist11!=null)
            pList.add(categoryinfo.categorylist_mobile.daylist11);
        if(categoryinfo.categorylist_mobile.daylist12!=null)
            pList.add(categoryinfo.categorylist_mobile.daylist12);
        if(categoryinfo.categorylist_mobile.daylist13!=null)
            pList.add(categoryinfo.categorylist_mobile.daylist13);
        if(categoryinfo.categorylist_mobile.daylist14!=null)
            pList.add(categoryinfo.categorylist_mobile.daylist14);
        if(categoryinfo.categorylist_mobile.daylist15!=null)
            pList.add(categoryinfo.categorylist_mobile.daylist15);
        if(categoryinfo.categorylist_mobile.daylist16!=null)
            pList.add(categoryinfo.categorylist_mobile.daylist16);
        if(categoryinfo.categorylist_mobile.daylist17!=null)
            pList.add(categoryinfo.categorylist_mobile.daylist17);
        if(categoryinfo.categorylist_mobile.daylist18!=null)
            pList.add(categoryinfo.categorylist_mobile.daylist18);
        if(categoryinfo.categorylist_mobile.daylist19!=null)
            pList.add(categoryinfo.categorylist_mobile.daylist19);
        if(categoryinfo.categorylist_mobile.daylist20!=null)
            pList.add(categoryinfo.categorylist_mobile.daylist20);
        if(categoryinfo.categorylist_mobile.daylist21!=null)
            pList.add(categoryinfo.categorylist_mobile.daylist21);
        if(categoryinfo.categorylist_mobile.daylist22!=null)
            pList.add(categoryinfo.categorylist_mobile.daylist22);
        if(categoryinfo.categorylist_mobile.daylist23!=null)
            pList.add(categoryinfo.categorylist_mobile.daylist23);
        if(categoryinfo.categorylist_mobile.daylist24!=null)
            pList.add(categoryinfo.categorylist_mobile.daylist24);
        if(categoryinfo.categorylist_mobile.daylist25!=null)
            pList.add(categoryinfo.categorylist_mobile.daylist25);
        if(categoryinfo.categorylist_mobile.daylist26!=null)
            pList.add(categoryinfo.categorylist_mobile.daylist26);
        if(categoryinfo.categorylist_mobile.daylist27!=null)
            pList.add(categoryinfo.categorylist_mobile.daylist27);
        if(categoryinfo.categorylist_mobile.daylist28!=null)
            pList.add(categoryinfo.categorylist_mobile.daylist28);
        if(categoryinfo.categorylist_mobile.daylist29!=null)
            pList.add(categoryinfo.categorylist_mobile.daylist29);
        if(categoryinfo.categorylist_mobile.daylist30!=null)
            pList.add(categoryinfo.categorylist_mobile.daylist30);
        if(categoryinfo.categorylist_mobile.daylist31!=null)
            pList.add(categoryinfo.categorylist_mobile.daylist31);

        for(int i=0;i<pList.size();i++){
            for(int k=0;k<pList.get(i).size();k++)
                cList.add(pList.get(i).get(k));
        }
    }


    @Override
    public String getGroup(int groupPosition) { // ParentList의 position을 받아 해당 TextView에 반영될 String을 반환
        return pList.get(groupPosition).get(0).purchase_date;
    }

    @Override
    public int getGroupCount() { // ParentList의 원소 개수를 반환
        return pList.size();
    }

    @Override
    public long getGroupId(int groupPosition) { // ParentList의 position을 받아 long값으로 반환
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) { // ParentList의 View
        if(convertView == null){
            LayoutInflater groupInfla = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // ParentList의 layout 연결. root로 argument 중 parent를 받으며 root로 고정하지는 않음
            convertView = groupInfla.inflate(R.layout.detail_list_item, parent, false);
        }

        // ParentList의 Layout 연결 후, 해당 layout 내 TextView를 연결
        TextView parentText = (TextView)convertView.findViewById(R.id.parenttext);
        parentText.setText(getGroup(groupPosition));
        return convertView;
    }


    @Override
    public CategoryData.Categorylist_mobile.purchaselist getChild(int groupPosition, int childPosition) { // groupPostion과 childPosition을 통해 childList의 원소를 얻어옴
        return pList.get(groupPosition).get(childPosition);

    }

    @Override
    public int getChildrenCount(int groupPosition) { // ChildList의 크기를 int 형으로 반환
        return pList.get(groupPosition).size();

    }

    @Override
    public long getChildId(int groupPosition, int childPosition) { // ChildList의 ID로 long 형 값을 반환
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View v = convertView;

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.mContext.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_in_list, null);
        }
        TextView text=convertView.findViewById(R.id.childtext);
        text.setText(getChild(groupPosition,childPosition).item_name);
        TextView text2=convertView.findViewById(R.id.childprice);
        text2.setText(getChild(groupPosition,childPosition).price);


        return convertView;

    }

    @Override
    public boolean hasStableIds() { return true; } // stable ID인지 boolean 값으로 반환

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) { return true; } // 선택여부를 boolean 값으로 반환




}

