package rishab.listview.com.mywishlist;

import android.app.Activity;
import android.content.Context;
import android.database.DatabaseUtils;
import android.support.constraint.solver.ArrayLinkedVariables;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import data.model.databasehandler;
import model.Mywish;

public class DisplayWish extends AppCompatActivity {
    private databasehandler dba;
    private ArrayList<Mywish> dbwishes = new ArrayList<>();
private    WishAdapter wishAdapter;
private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_wish);
        listView = findViewById(R.id.list);
        refreshData();
    }
    private  void  refreshData(){
        dbwishes.clear();
        dba = new databasehandler(getApplicationContext());
        ArrayList<Mywish> wishesFromDB = dba.getWishles();

        for( int i =0; i<wishesFromDB.size();i++){
            String title = wishesFromDB.get(i).getTitle();
            String datetext = wishesFromDB.get(i).getRecordDate();
            String content = wishesFromDB.get(i).getContext();
            Mywish mywish = new Mywish();
            mywish.setTitle(title);
            mywish.setRecordDate(datetext);
            mywish.setContext(content);
            dbwishes.add(mywish);

        }
        dba.close();
        wishAdapter = new WishAdapter(DisplayWish.this,R.layout.wishrow,dbwishes);
        listView.setAdapter(wishAdapter);
        wishAdapter.notifyDataSetChanged();
    }


    public class WishAdapter extends ArrayAdapter<Mywish>{
        Activity activity;
        int layoutResource;
        Mywish wish;
        ArrayList<Mywish> mdata  = new ArrayList<>();
        public WishAdapter(Activity act, int resource, ArrayList<Mywish> data) {
            super(act, resource, (List<Mywish>) data);
            activity = act;
            layoutResource  = resource;
            mdata = data;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return  mdata.size();
        }


        @Override
        public Mywish getItem(int position) {
            return  mdata.get(position);
        }

        @Override
        public int getPosition( Mywish item) {
            return super.getPosition(item);
        }

        @Override
        public long getItemId(int position) {
            return super.getItemId(position);
        }


        @Override
        public View getView(int position,View convertView,ViewGroup parent) {
            View row = convertView;
            ViewHolder holder = null;
            if(row == null||(row.getTag())== null){
                LayoutInflater inflater = LayoutInflater.from(activity);
                row = inflater.inflate(layoutResource,null);
                holder = new ViewHolder();
                holder.mtitle = (TextView) row.findViewById(R.id.name);
                holder.mdate = (TextView) row.findViewById(R.id.datetext);

                row.setTag(holder);

            }
            else{
                holder = (ViewHolder) row.getTag();
            }
            holder.mywish = getItem(position);
            holder.mtitle.setText(holder.mywish.getTitle());
            holder.mdate.setText(holder.mywish.getRecordDate());

            return row;
        }


        class  ViewHolder{
            Mywish mywish;
            TextView mtitle;
            TextView mid;
            TextView mcontent;
            TextView mdate;
        }
    }

}
