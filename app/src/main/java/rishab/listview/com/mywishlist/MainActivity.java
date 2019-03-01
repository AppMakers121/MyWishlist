package rishab.listview.com.mywishlist;

import android.content.Intent;
import android.database.DatabaseErrorHandler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import data.model.databasehandler;
import model.Mywish;

public class MainActivity extends AppCompatActivity {
private EditText title;
private EditText content;
private Button savebutton;
private databasehandler dba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dba = new databasehandler(MainActivity.this);
        title = findViewById(R.id.editText);
        content = findViewById(R.id.editText2);
        savebutton = findViewById(R.id.button);
        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToDB();
            }
        });
    }
private void saveToDB(){
    Mywish wis = new Mywish();
    wis.setTitle(title.getText().toString().trim());
    wis.setContext(content.getText().toString().trim());
    dba.addWishes(wis);
    dba.close();
     title.setText("");
     content.setText("");
    Intent i = new Intent(MainActivity.this,wishdetail.class);
     startActivity(i);
}

}
