///**
// * 
// */
//package com.thingtrack.bustrack.mobile.android.threads;
//
//import android.app.Activity;
//import android.content.Context;
//import android.os.Bundle;
//import android.location.LocationListener;
//import android.location.LocationManager;
//
///**
// * @author thk01
// *
// */
//public class ThreadGPS extends Activity{
//
//	
//	//public void onCreate(Bundle savedInstanceState) {///<<<<<bundle----import android.os.Bundle;
//	// on
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		// TODO Auto-generated method stub
//		super.onCreate(savedInstanceState);
//		/// MyLocationListener
//		
////decirle 'el objeto' del this /B/ o directamente /A/
//		// A.
//		new Thread(new Runnable() { 
//            public void run(){
//            	
////            	txt1.setText("Thread!!"); o las órdenes que toquen
//            }
//        }).start();  // empieza y está corriendo
//		
//		
//				//		new Thread(new Runnable() { 
//		//            public void run(){
//		//            	
//		////            	txt1.setText("Thread!!"); o las órdenes que toquen
//		//            }
//		//        }).setDaemon(on)   // o isDaemon---- si como daemon
//		
//	   // B.
//       // Thread thread = new Thread(this);
//       // thread.start(); // si como Daemon---
//		
////		new Thread(new Runnable() { 
////            public void run(){
////            	
//////            	txt1.setText("Thread!!"); o las órdenes que toquen
////            }
////        }).getPriority() // prioridad
//		
//		
//		
//        // Use the LocationManager class to obtain GPS locations
//        LocationManager mlocManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
//        
//        LocationListener mlocListener = new MyLocationListener(this);
//        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mlocListener);
//	}
//	
//
//}
