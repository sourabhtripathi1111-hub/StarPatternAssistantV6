package com.starpattern.assistant;
import android.app.*; import android.content.*; import android.os.*;
public class OverlayService extends Service { public IBinder onBind(Intent i){return null;} public int onStartCommand(Intent i,int f,int id){return START_STICKY;} }
