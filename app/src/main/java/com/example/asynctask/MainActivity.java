package com.example.asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Button;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    ListView listView;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b = findViewById(R.id.button);
        listView = (ListView) findViewById(R.id.listView1);

        b.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                new TestAsync().execute("lecture.xml");
            }
        });

    }



    class TestAsync extends AsyncTask<String, Integer, String> {
        String TAG = getClass().getSimpleName();
        List<Employee> employees;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
           Toast.makeText(getApplicationContext(),"helllooooooo", Toast.LENGTH_SHORT).show();


        }
        @Override
        protected String doInBackground(String...arg0) {
            try {
                XMLPullParserHandler parser = new XMLPullParserHandler();
                InputStream is=getAssets().open(arg0[0]);
                this.employees = parser.parse(is);



            } catch (Exception e) {e.printStackTrace();}
//  Toast.makeText(getApplicationContext(),"Background", Toast.LENGTH_LONG).show();
            return null;
        }
        protected void onProgressUpdate(Integer...a) {
            super.onProgressUpdate(a);
           Toast.makeText(getApplicationContext(),"en Progress", Toast.LENGTH_SHORT).show();

        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            for (Employee e: employees){
                System.out.println(e);
            }
            ArrayAdapter<Employee> adapter =new ArrayAdapter<Employee>
                    (getApplicationContext(), R.layout.text_view_layout , this.employees);
            listView.setAdapter(adapter);
        }
    }
    //Button btn;
    //AsyncTask<?, ?, ?> runningTask;

}