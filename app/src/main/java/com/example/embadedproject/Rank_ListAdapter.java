package com.example.embadedproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.embadedproject.model.MonthPercent;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;


public class Rank_ListAdapter extends BaseAdapter {
    private LayoutInflater mInflater;

    private LinkedList<data> RankArray = new LinkedList<>();
    public static String fasion_rank;
    public static String cosmetic_rank;
    public static String digital_rank;
    public static String interior_rank;
    public static String kid_rank;
    public static String food_rank;
    public static String sports_rank;
    public static String life_rank;
    public static String culture_rank;

    public Rank_ListAdapter(Context context, MonthPercent monthper) {
        mInflater = LayoutInflater.from(context);
        fasion_rank = monthper.fashion.getRank();
        cosmetic_rank = monthper.cosmetic.getRank();
        digital_rank = monthper.digital.getRank();
        interior_rank = monthper.interior.getRank();
        kid_rank = monthper.kid.getRank();
        food_rank = monthper.food.getRank();
        sports_rank = monthper.sports.getRank();
        life_rank = monthper.life.getRank();
        culture_rank = monthper.culture.getRank();

        RankArray.add(0,new data(Integer.parseInt(monthper.fashion.getRank()),"패션",Integer.toString(monthper.fashion.getPercent()), R.drawable.fashion));
        RankArray.add(1,new data(Integer.parseInt(monthper.cosmetic.getRank()),"화장품/미용",Integer.toString(monthper.cosmetic.getPercent()), R.drawable.cosmetic));
        RankArray.add(2,new data(Integer.parseInt(monthper.digital.getRank()),"디지털/가전",Integer.toString(monthper.digital.getPercent()), R.drawable.digital1));
        RankArray.add(3,new data(Integer.parseInt(monthper.interior.getRank()),"가구/인테리어",Integer.toString(monthper.interior.getPercent()), R.drawable.interior));
        RankArray.add(4,new data(Integer.parseInt(monthper.kid.getRank()),"출산/육아",Integer.toString(monthper.kid.getPercent()), R.drawable.kid));
        RankArray.add(5,new data(Integer.parseInt(monthper.food.getRank()),"식품",Integer.toString(monthper.food.getPercent()), R.drawable.food));
        RankArray.add(6,new data(Integer.parseInt(monthper.sports.getRank()),"스포츠/레저",Integer.toString(monthper.sports.getPercent()), R.drawable.sports1));
        RankArray.add(7,new data(Integer.parseInt(monthper.life.getRank()),"생활/건강",Integer.toString(monthper.life.getPercent()), R.drawable.life));
        RankArray.add(8,new data(Integer.parseInt(monthper.culture.getRank()),"여행/문화",Integer.toString(monthper.culture.getPercent()), R.drawable.culture));

        Collections.sort(RankArray, new Comparator<data>() {
            @Override
            public int compare(data o1, data o2) {
                if(o1.getRankn()>o2.getRankn()){
                    return 1;

                    }else if(o1.getRankn()<o2.getRankn()){
                    return -1;
                }
                return 0;
            }
        });

    }

    @Override
    public int getCount() {
        return RankArray.size();
    }

    @Override
    public data getItem(int position) {
        return  RankArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return (long) position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.percent_rank_list, parent, false);
        }

        TextView rankn = (TextView) convertView.findViewById(R.id.ranknum);
        TextView rankp = (TextView) convertView.findViewById(R.id.rank_per);
        TextView rankt = (TextView) convertView.findViewById(R.id.rank_title);
        ImageView img = (ImageView) convertView.findViewById(R.id.rank_img);

        data m = RankArray.get(position);
        rankn.setText(getItem(position).rankn + "위");
        rankp.setText(getItem(position).rankp + "%");
        rankt.setText(getItem(position).rankt + "");
        img.setImageResource(m.img);

        return convertView;


    }
    public class data{
        public int img;
        public int rankn;
        public String rankp;
        public String rankt;

        public data(int rankn, String rankt, String rankp, int img){
            this.rankn=rankn;
            this.rankp=rankp;
            this.rankt=rankt;
            this.img = img;
        }
        public int getImg(){return img;}
        public int getRankn() {
            return rankn;
        }

        public String getRankp() {
            return rankp;
        }
        public String getRankt() {
            return rankt;
        }
    }
}

