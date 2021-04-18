package com.rofitness.fitnessapp.Utils;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.rofitness.fitnessapp.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@SuppressWarnings("ALL")
public class Util {
    private static final String TAG = Util.class.getSimpleName();

    public static boolean isNetworkAvailable(Context context) {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isConnected()) {
            return false;
        }
        return true;
    }

    public static String formatNumber(float f) {
        return new DecimalFormat(".##").format((double) f);
    }

    public static String formatName(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        if (str.contains("_")) {
            String[] split = str.split("_");
            for (String str2 : split) {
                sb.append(Character.toUpperCase(str2.charAt(0)));
                sb.append(str2.substring(1, str2.length()));
                sb.append(" ");
            }
        } else {
            sb.append(Character.toUpperCase(str.charAt(0)));
            sb.append(str.substring(1, str.length()));
        }
        return sb.toString();
    }

    public static void playAudio(Context context, int i) {
        try {
            final MediaPlayer create = MediaPlayer.create(context, i);
            create.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                public final MediaPlayer f$0;

                {
                    this.f$0 = create;
                }

                @Override
                public final void onCompletion(MediaPlayer mediaPlayer) {
                    f$0.release();
                }
            });
            create.start();
        } catch (Exception e) {
            String str = TAG;
            Log.e(str, "playAudio: " + e.getLocalizedMessage());
        }
    }

    public static long getCurrentDateInMillis() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        try {
            return simpleDateFormat.parse(simpleDateFormat.format(new Date())).getTime();
        } catch (ParseException e) {
            String str = TAG;
            Log.e(str, "exception in getting current date in millis: " + e.getLocalizedMessage());
            return -1;
        }
    }

    public static String convertLongDateToString(long j) {
        return new SimpleDateFormat("d MMM", Locale.getDefault()).format(new Date(j));
    }

    public static void exportDB(Context context) {
        try {
            File dataDirectory = Environment.getDataDirectory();
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            if (externalStorageDirectory.canWrite()) {
                String str = "/data/" + context.getPackageName() + "/databases/" + context.getString(R.string.app_name);
                File file = new File(dataDirectory, str);
                File file2 = new File(externalStorageDirectory, "/Backup/" + context.getString(R.string.app_name) + ".db");
                FileChannel channel = new FileInputStream(file).getChannel();
                FileChannel channel2 = new FileOutputStream(file2).getChannel();
                channel2.transferFrom(channel, 0, channel.size());
                channel.close();
                channel2.close();
                Log.d(TAG, "DB exported successfully");
            }
        } catch (Exception unused) {
            Log.d(TAG, "Failed to export DB");
        }
    }

    public static String getDateString(int i, int i2, int i3, String str) throws ParseException {
        Locale locale = Locale.ENGLISH;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy", locale);
        return new SimpleDateFormat(str, locale).format(simpleDateFormat.parse(i + "." + i2 + "." + i3));
    }

    public static String getDateString(Date date, String str) throws ParseException {
        return new SimpleDateFormat(str, Locale.ENGLISH).format(date);
    }

    public static String getDateString(long j, String str, String str2) throws ParseException {
        Locale locale = Locale.ENGLISH;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str, locale);
        return new SimpleDateFormat(str2, locale).format(simpleDateFormat.parse("" + j));
    }
}
