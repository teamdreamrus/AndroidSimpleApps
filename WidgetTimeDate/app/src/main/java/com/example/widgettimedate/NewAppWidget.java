package com.example.widgettimedate;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.text.format.Time;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.os.Bundle;

import java.time.Instant;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {



    Timer myTimer = new Timer();
    private void startTimer(final Context context, final AppWidgetManager appWidgetManager, final int[] appWidgetIds){
        myTimer.schedule(new TimerTask() {
            @Override
                    public void run() {
                updateAppWidget(context, appWidgetManager, appWidgetIds[0]);
            }
        }, 0, 60000);
    }

    public static Bitmap BuildUpdate(String txttime, int size, Context context){
        Paint paint = new Paint();
        paint.setTextSize(size);
        Typeface ourCustomTypeFace = Typeface.createFromAsset(context.getAssets(),"fonts/Lato-Light.ttf");
        paint.setTypeface(ourCustomTypeFace);
       // Bundle arg = getIntent().getExtras();

        int colorr =  Color.WHITE;
        paint.setColor(context.getResources().getColor(R.color.textcolor));
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setSubpixelText(true);
        paint.setAntiAlias(true);
        float baseline = -paint.ascent();
        int width = (int) (paint.measureText(txttime)+0.5f);
        int height = (int) (baseline + paint.descent()+0.5f);
        Bitmap image = Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(image);
        canvas.drawText(txttime,0,baseline,paint);
        return image;
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);

        Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();
        String time = today.format("%k:%M");
        String date = today.monthDay + "." + today.month + "." + today.year;


       // views.setString(R.id.imgdate,"setText",date);

      //  views.setString(R.id.imgtime,"setText",time);
        views.setImageViewBitmap(R.id.imgtime, BuildUpdate(time,100,context));
        views.setImageViewBitmap(R.id.imgdate, BuildUpdate(date,25,context));

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them

        for (int appWidgetId : appWidgetIds) {
            Intent intent = new Intent(context, MainActivity.class);

            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);

            // This is needed to make this intent different from its previous intents
            intent.setData(Uri.parse("tel:/"+ (int)System.currentTimeMillis()));

            // Creating a pending intent, which will be invoked when the user
            // clicks on the widget

            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                    intent,PendingIntent.FLAG_UPDATE_CURRENT);

            // Get the layout for the App Widget
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);

            //  Attach an on-click listener to the clock
            views.setOnClickPendingIntent(R.id.all, pendingIntent);


            // Tell the AppWidgetManager to perform an update on the current app widget
            appWidgetManager.updateAppWidget(appWidgetId, views);

            updateAppWidget(context, appWidgetManager, appWidgetId);



        }
        startTimer(context, appWidgetManager, appWidgetIds);
    }
    private void stopTimer() {
        myTimer.cancel();
    }

    @Override
    public void onEnabled(Context context) {

        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
        stopTimer();
    }
}

