package rishab.listview.com.mywishlist;

import android.app.Activity;
import android.content.Context;
import android.database.DatabaseUtils;
import android.support.constraint.solver.ArrayLinkedVariables;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

            return super.getView(position, convertView, parent);
        }
    }

}
