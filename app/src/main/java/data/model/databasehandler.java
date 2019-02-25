package data.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.SyncStateContract;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;

import model.Mywish;

public class databasehandler extends SQLiteOpenHelper {
private final ArrayList<Mywish> wishlist = new ArrayList<>();
    public databasehandler(Context context) {
        super(context,constants.DATABASE_NAME,null,constants.DATABASE_version) ;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    String CREATE_WISHES_TABLE= "CREATE TABLE "+constants.TABLE_NAME+"("+constants.KEY_ID+
            "INTEGER PRIMARY KEY,"+constants.TABLE_NAME+
            "TEXT,"+ constants.CONTENT_NAME+"TEXT,"+constants.DATE_NAME+
            " LONG);";
    db.execSQL(CREATE_WISHES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+constants.TABLE_NAME);
    onCreate(db);
    }
    //add content
    public void addWishes(Mywish wish){
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(constants.TITLE_NAME,wish.getTitle());
    values.put(constants.CONTENT_NAME,wish.getContext());
    values.put(constants.DATE_NAME,java.lang.System.currentTimeMillis());
    db.insert(constants.TABLE_NAME,null,values);
    Log.v("wxish successfully","yeah!!");
    db.close();
    }
    //get all wishes
    public ArrayList<Mywish> getWishles(){
        String sekectQuery = "SELECT *FROM "+constants.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(constants.TABLE_NAME,new String[]{constants.KEY_ID,
                constants.TABLE_NAME,constants.CONTENT_NAME,constants.DATE_NAME,},
                null,null,null,null,constants.DATE_NAME+"DESC");
        if(cursor.moveToFirst()){
            do{
                Mywish wish = new Mywish();
                wish.setTitle(cursor.getString(cursor.getColumnIndex(constants.TITLE_NAME)));
                wish.setContext(cursor.getString(cursor.getColumnIndex(constants.CONTENT_NAME)));
                java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance();
                String dataData = dateFormat.format(new Date(cursor.getColumnIndex(constants.DATE_NAME)).getTime());
                wish.setRecordDate(dataData);
                wishlist.add(wish);
            }while (cursor.moveToNext());
        }
//hi


        return  wishlist;
    }
}
