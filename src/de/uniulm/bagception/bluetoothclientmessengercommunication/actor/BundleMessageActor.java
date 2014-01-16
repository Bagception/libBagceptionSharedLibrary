package de.uniulm.bagception.bluetoothclientmessengercommunication.actor;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import de.philipphock.android.lib.BroadcastActor;
import de.philipphock.android.lib.logging.LOG;
import de.uniulm.bagception.broadcastconstants.BagceptionBroadcastContants;

public class BundleMessageActor extends BroadcastActor<BundleMessageReactor>{

	public BundleMessageActor(BundleMessageReactor reactor) {
		super(reactor);
	}

	@Override
	public void register(Context a) {
		IntentFilter filter = new IntentFilter();
		filter.addAction(BagceptionBroadcastContants.BROADCAST_BUNDLEMESSAGE_COMMAND_INTENT);
		filter.addAction(BagceptionBroadcastContants.BROADCAST_BUNDLEMESSAGE_MESSAGE_RECV_INTENT);
		filter.addAction(BagceptionBroadcastContants.BROADCAST_BUNDLEMESSAGE_MESSAGE_SEND_INTENT);

		filter.addAction(BagceptionBroadcastContants.BROADCAST_BUNDLEMESSAGE_RESPONSE_INTENT);		
		filter.addAction(BagceptionBroadcastContants.BROADCAST_BUNDLEMESSAGE_STATUS_INTENT);
		filter.addAction(BagceptionBroadcastContants.BROADCAST_BUNDLEMESSAGE_RESPONSE_ANSWER_INTENT);
		
		LocalBroadcastManager.getInstance(a).registerReceiver(this,
			      filter);
		
		
		
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		LOG.out(this, "broadcast recv "+intent.getAction());
		if (BagceptionBroadcastContants.BROADCAST_BUNDLEMESSAGE_COMMAND_INTENT.equals(intent.getAction())){
			reactor.onCommandMessage(intent.getExtras());
		}else if (BagceptionBroadcastContants.BROADCAST_BUNDLEMESSAGE_MESSAGE_RECV_INTENT.equals(intent.getAction())){
			reactor.onBundleMessageRecv(intent.getExtras());
		}else if (BagceptionBroadcastContants.BROADCAST_BUNDLEMESSAGE_MESSAGE_SEND_INTENT.equals(intent.getAction())){
			reactor.onBundleMessageSend(intent.getExtras());
		}else if (BagceptionBroadcastContants.BROADCAST_BUNDLEMESSAGE_RESPONSE_INTENT.equals(intent.getAction())){
			reactor.onResponseMessage(intent.getExtras());
		}else if (BagceptionBroadcastContants.BROADCAST_BUNDLEMESSAGE_STATUS_INTENT.equals(intent.getAction())){
			reactor.onStatusMessage(intent.getExtras());
		}else if (BagceptionBroadcastContants.BROADCAST_BUNDLEMESSAGE_RESPONSE_ANSWER_INTENT.equals(intent.getAction())){
			reactor.onResponseAnswerMessage(intent.getExtras());
		}
		
	}

	
	
	@Override
	public void unregister(Context a){
		LocalBroadcastManager.getInstance(a).unregisterReceiver(this);
	}



}
