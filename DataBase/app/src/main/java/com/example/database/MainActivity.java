package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.ContentValues;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.util.Log;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        context=this;
        // Create DatabaseHelper instance
        DBHelper dataHelper=new DBHelper(context);
        // Insert sample data
        String[] names = {"Atazhanov","Atuchin","Bazhin","Berezhinskii","Semin","Nedobitkova","Belashov","Kazimirchik"};
        Random r = new Random();
        int i = r.nextInt(7 - 0) + 0;
        int w = r.nextInt(120 - 60) + 60;
        int h = r.nextInt(210 - 160) + 160;
        int a = r.nextInt(23 - 18) + 18;

        dataHelper.insertData(names[i], w,h,a);

        i = r.nextInt(7 - 0) + 0;
        w = r.nextInt(120 - 60) + 60;
        h = r.nextInt(210 - 160) + 160;
        a = r.nextInt(23 - 18) + 18;
        dataHelper.insertData(names[i], w,h,a);
        i = r.nextInt(7 - 0) + 0;
        w = r.nextInt(120 - 60) + 60;
        h = r.nextInt(210 - 160) + 160;
        a = r.nextInt(23 - 18) + 18;
        dataHelper.insertData(names[i], w,h,a);
        i = r.nextInt(7 - 0) + 0;
        w = r.nextInt(120 - 60) + 60;
        h = r.nextInt(210 - 160) + 160;
        a = r.nextInt(23 - 18) + 18;
        dataHelper.insertData(names[i], w,h,a);

        // Reference to TableLayout
        TableLayout tableLayout=(TableLayout)findViewById(R.id.tablelayout);
        // Add header row
        TableRow rowHeader = new TableRow(context);
        rowHeader.setBackgroundColor(Color.parseColor("#c0c0c0"));
        rowHeader.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
        String[] headerText={"ID","NAME","WEIGHT","HEIGHT","AGE"};
        for(String c:headerText) {
            TextView tv = new TextView(this);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(16);
            tv.setPadding(5, 5, 5, 5);
            tv.setText(c);
            rowHeader.addView(tv);
        }
        tableLayout.addView(rowHeader);

        // Get data from sqlite database and add them to the table
        // Open the database for reading
        SQLiteDatabase db = dataHelper.getReadableDatabase();
        // Start the transaction.
        db.beginTransaction();

        try
        {
            String selectQuery = "SELECT * FROM "+ DBHelper.TABLE_STUDENTS;
            Cursor cursor = db.rawQuery(selectQuery,null);
            if(cursor.getCount() >0)
            {
                while (cursor.moveToNext()) {
                    // Read columns data
                    int _id= cursor.getInt(cursor.getColumnIndex(dataHelper.KEY_ID));
                    String name= cursor.getString(cursor.getColumnIndex(dataHelper.KEY_NAME));
                    int weight= cursor.getInt(cursor.getColumnIndex(dataHelper.KEY_WEIGHT));
                    int height= cursor.getInt(cursor.getColumnIndex(dataHelper.KEY_HEIGHT));
                    int age= cursor.getInt(cursor.getColumnIndex(dataHelper.KEY_AGE));

                    // dara rows
                    TableRow row = new TableRow(context);
                    row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                            TableLayout.LayoutParams.WRAP_CONTENT));
                    String[] colText={_id+"",name, weight+"", height+"", age+""};
                    for(String text:colText) {
                        TextView tv = new TextView(this);
                        tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                                TableRow.LayoutParams.WRAP_CONTENT));
                        tv.setGravity(Gravity.CENTER);
                        tv.setTextSize(16);
                        tv.setPadding(5, 5, 5, 5);
                        tv.setText(text);
                        row.addView(tv);
                    }
                    tableLayout.addView(row);

                }

            }
            db.setTransactionSuccessful();
        }
        catch (SQLiteException e)
        {
            e.printStackTrace();

        }
        finally
        {
            db.endTransaction();
            // End the transaction.
            db.close();
            // Close database
        }








    }
    /*Создать базу данных студентов (Имя, вес, рост, возраст - сгенерировать случайно).
     Вывести из базы данных все записи, отсортированные по возрасту, в таблицу (TableLayout).*/

    public static class DBHelper extends SQLiteOpenHelper {

        public static final int DATABASE_VERSION = 8;
        public static final String DATABASE_NAME = "SibsutisDB";
        public static final String TABLE_STUDENTS = "Students";

        public static final String KEY_ID = "_id";
        public static final String KEY_NAME = "name";
        public static final String KEY_WEIGHT = "weight";
        public static final String KEY_HEIGHT = "height";
        public static final String KEY_AGE = "age";


        public DBHelper( Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            String qwery ="create table " + TABLE_STUDENTS + "(" + KEY_ID
                    + " integer primary key," + KEY_NAME + " text,"
                    + KEY_WEIGHT + " integer," + KEY_HEIGHT + " integer," + KEY_AGE + " integer"
                    +")";

            db.execSQL(qwery);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("drop table if exists " + TABLE_STUDENTS);
            onCreate(db);

        }
        public void insertData(String name,int weight,int height,int age ){

            // Open the database for writing
            SQLiteDatabase db = this.getWritableDatabase();
            // Start the transaction.
            db.beginTransaction();
            ContentValues values;

            try
            {
                values = new ContentValues();
                values.put(KEY_NAME,name);
                values.put(KEY_WEIGHT,weight);
                values.put(KEY_HEIGHT,height);
                values.put(KEY_AGE,age);
                // Insert Row
                long i = db.insert(TABLE_STUDENTS, null, values);
                Log.i("Insert", i + "");
                // Insert into database successfully.
                db.setTransactionSuccessful();

            }
            catch (SQLiteException e)
            {
                e.printStackTrace();

            }
            finally
            {
                db.endTransaction();
                // End the transaction.
                db.close();
                // Close database
            }

        }

    }
}
