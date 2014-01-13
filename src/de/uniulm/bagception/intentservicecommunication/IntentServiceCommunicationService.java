package de.uniulm.bagception.intentservicecommunication;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.widget.Toast;

public class IntentServiceCommunicationService extends IntentService{
	public static volatile int counter = 0;
	private Handler displayHandler;
	
	public IntentServiceCommunicationService() {
		super("TestService");
	}
	
	@Override
	public void onCreate() {
		displayHandler = new Handler();
		super.onCreate();
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		String origin = intent.getStringExtra("origin");
		Display d = new Display("I do my work now, "+origin+" asked me to do so!");		
		
		
		ResultReceiver rec = intent.getParcelableExtra("receiverTag");
        Bundle b= new Bundle();
        b.putString("payload","hello "+origin);
        rec.send(0, b);
		
		displayHandler.post(d);
		
	}
	
	
	private class Display implements Runnable{

		private final String toShow;
		
		public Display(String toshow) {
			this.toShow = toshow;
		}
		
		@Override
		public void run() {
			
				Toast.makeText(IntentServiceCommunicationService.this,toShow + " " +counter++, Toast.LENGTH_SHORT ).show();	
		}
		
	}
	

}
