package com.varunmishra.temperaturecorrection;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TemperatureCorrectionMain extends Activity  implements OnClickListener{
    public static final String TAG = TemperatureCorrectionMain.class.getName();
public static Button start,stop;
public static TextView t,temp1,temp2,humidity1,humidity2,pressure1,pressure2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        start = (Button) findViewById(R.id.button1);
        stop = (Button) findViewById(R.id.button2);
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
//        t = (TextView)findViewById(R.id.textView1);
        temp1 = (TextView)findViewById(R.id.textView3);
        temp2 = (TextView)findViewById(R.id.textView4);
        humidity1 = (TextView)findViewById(R.id.textView6);
        humidity2 = (TextView)findViewById(R.id.textView7);
        pressure1 = (TextView)findViewById(R.id.textView9);
        pressure2 = (TextView)findViewById(R.id.textView10);
        if(isMyServiceRunning())
        {
        	refreshdisplay();
        	start.setEnabled(false);
        }
        else
        {
        	start.setEnabled(true);
        }
    }
    public static void refreshdisplay()
    {

    	temp1.setText("Computed Temp:  " + SamplingService.temp + " deg C");
    	humidity1.setText("Raw Temp: " + SamplingService.rawtemp+ " deg C");
    	pressure1.setText("Battery Temp: " + SamplingService.phonebat+ " deg C");

    }
    private boolean isMyServiceRunning() {
	    ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
	    for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
	        if (SamplingService.class.getName().equals(service.service.getClassName())) {
	            return true;
	        }
	    }
	    return false;
	}
    @Override
    public void onResume() {
        super.onResume();

       
    }

	@Override
	public void onClick(View src) {
		// TODO Auto-generated method stub
		 switch (src.getId()) {
		    case R.id.button1:
		      Log.d(TAG, "onClick: starting srvice");
		      startService(new Intent(this, SamplingService.class));
		      
		        
		        	start.setEnabled(false);
		        refreshdisplay();
		      break;
		    case R.id.button2:
		      Log.d(TAG, "onClick: stopping srvice");
		      stopService(new Intent(this, SamplingService.class));
		    	start.setEnabled(true);
		      break;
		    	
	      
		 }
	}

}
