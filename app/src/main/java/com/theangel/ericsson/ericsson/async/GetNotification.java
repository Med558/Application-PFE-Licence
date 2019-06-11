package com.theangel.ericsson.ericsson.async;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.theangel.ericsson.R;
import com.theangel.ericsson.ericsson.activities.DetailsMeteoActivity;
import com.theangel.ericsson.ericsson.model.HELBNotification;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.app.PendingIntent.FLAG_ONE_SHOT;
import static android.content.Context.NOTIFICATION_SERVICE;


public class GetNotification extends AsyncTask<Void, Void, HELBNotification> {

    private final int NOTIFID = 9000;
    private final int OUVRIRCODE = 7205;

    private Context ctx;

    public GetNotification(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    protected HELBNotification doInBackground(Void... params) {

        HELBNotification notif = null;

        try {

            String address = "http://samples.openweathermap.org/data/2.5/weather?q=Brussels,uk&appid=b1b15e88fa797225412429c1c50c122a1";

            URL webservice = new URL(address);

            HttpURLConnection connection = (HttpURLConnection) webservice.openConnection();

            InputStream is = connection.getInputStream();

            InputStreamReader reader = new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(reader);

            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line);
            }

            String json = builder.toString();

            JSONObject jsonObject = new JSONObject(json);
            JSONObject jMain = jsonObject.getJSONObject("main");
            JSONObject jWind = jsonObject.getJSONObject("wind");

            double temp = jMain.getDouble("temp");
            int pressure = jMain.getInt("pressure");
            int humidity = jMain.getInt("humidity");
            double windSpeed = jWind.getDouble("speed");

            notif = new HELBNotification(temp, pressure, humidity, windSpeed);

            Log.i("c3trace", json);

            //TODO: déserialiser le json en HELBNotif
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return notif;
    }


    @Override
    protected void onPostExecute(HELBNotification helbNotification) {
        super.onPostExecute(helbNotification);


        PendingIntent pendingIntent = PendingIntent
                .getActivity(ctx, OUVRIRCODE,
                        new Intent(ctx, DetailsMeteoActivity.class), FLAG_ONE_SHOT);

        NotificationCompat.Action.Builder actionBuilder = new NotificationCompat.Action.Builder(R.mipmap.ic_launcher, "Ouvrir", pendingIntent);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(ctx);
        builder.setContentTitle("Mise à jour météo")
                .setContentText("Il fait " + helbNotification.getTemperature() + "°C")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setWhen(System.currentTimeMillis())
                //.setContentIntent()
                .addAction(actionBuilder.build());
        //.setLargeIcon(R.mipmap.ic_launcher);

        Notification notification = builder.build();

        NotificationManager manager = (NotificationManager) ctx.getSystemService(NOTIFICATION_SERVICE);
        manager.notify(NOTIFID, notification);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putFloat("temperature", (float) helbNotification.getTemperature());

        editor.commit();
    }
}

