package com.example.widgettimedate;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RemoteViews;

import yuku.ambilwarna.AmbilWarnaDialog;

public class MainActivity extends AppCompatActivity  {
    private int mAppWidgetId = 0;
    private CheckBox mCheckbox;
    private CheckBox mCheckbox2;
    private  Button OK;
    private int ColorBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btnSetColor = (Button) findViewById(R.id.btn_set_color);

        // Defining a click event listener for the button "Set Color"
        View.OnClickListener setColorClickedListener  = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                colorPicker();
            }
        };

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras != null) {
            mAppWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
        }else{
            // Making the "Set Color" button invisible when
            // the application is launched directly
            btnSetColor.setVisibility(View.INVISIBLE);
        }

        // Setting the click listener on the "Set Color" button

        btnSetColor.setOnClickListener(setColorClickedListener);


        mCheckbox = (CheckBox)findViewById(R.id.checkBox);
        mCheckbox2 = (CheckBox)findViewById(R.id.checkBox2);
        OK = (Button)findViewById(R.id.btn_ok);

        OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getBaseContext());



                // Instantiating the class RemoteViews with widget_layout
                RemoteViews views = new RemoteViews(getBaseContext().getPackageName(), R.layout.new_app_widget);




                if(mCheckbox.isChecked()){


                    views.setInt(R.id.imgdate,"setVisibility",View.INVISIBLE);


                }else{
                    views.setInt(R.id.imgdate,"setVisibility",View.VISIBLE);

                }

                if(mCheckbox2.isChecked()){


                    views.setInt(R.id.imgtime,"setVisibility",View.INVISIBLE);

                }else{
                    views.setInt(R.id.imgtime,"setVisibility",View.VISIBLE);

                }

                views.setInt(R.id.imgtime, "setColorFilter", ColorBack);
                views.setInt(R.id.imgdate, "setColorFilter", ColorBack);

                //ImageView i;
                //i.setBackgroundColor();
              //  Intent intent = new Intent(MainActivity.this, NewAppWidget.class);
              //  intent.putExtra("Collor", ColorBack);

              //  startActivity(intent);
                appWidgetManager.updateAppWidget(mAppWidgetId, views);


            }
        });



    }
    public void colorPicker() {
        //      initialColor is the initially-selected color to be shown in the rectangle on the left of the arrow.
        //      for example, 0xff000000 is black, 0xff0000ff is blue. Please be aware of the initial 0xff which is the alpha.

        AmbilWarnaDialog dialog = new AmbilWarnaDialog(this, 0x440000ff, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            // Executes, when user click Cancel button
            @Override
            public void onCancel(AmbilWarnaDialog dialog){
            }

            // Executes, when user click OK button
            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                // Getting an instance of WidgetManager
                ColorBack = color;

            }
        });
        dialog.show();
    }
}
// http://wptrafficanalyzer.in/blog/android-home-screen-app-widget-with-onclick-event/