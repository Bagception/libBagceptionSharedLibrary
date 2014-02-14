package de.uniulm.bagception.bundlemessageprotocol.entities.administration;

import java.util.UUID;

import de.uniulm.bagception.bundlemessageprotocol.entities.Activity;
import de.uniulm.bagception.bundlemessageprotocol.entities.administration.AdministrationCommand.Entity;
import de.uniulm.bagception.bundlemessageprotocol.entities.administration.AdministrationCommand.Operation;

public abstract class ActivityCommand {

	
	public static AdministrationCommand<Activity> add(Activity toCreate){
		return new AdministrationCommand<Activity>(Entity.ACTIVITY, Operation.ADD, UUID.randomUUID(), true, "", new Activity[]{toCreate});
	}
	
	public static AdministrationCommand<Activity> remove(Activity toRemove){
		return new AdministrationCommand<Activity>(Entity.ACTIVITY, Operation.DEL, UUID.randomUUID(), true, "", new Activity[]{toRemove});
	}
	
	public static AdministrationCommand<Activity> edit(Activity toEdit,Activity editValues){
		return new AdministrationCommand<Activity>(Entity.ACTIVITY, Operation.EDIT, UUID.randomUUID(), true, "", new Activity[]{toEdit,editValues});
	}
	public static AdministrationCommand<Activity> list(){
		return new AdministrationCommand<Activity>(Entity.ACTIVITY, Operation.LIST, UUID.randomUUID(), true, "", new Activity[]{});
	}
	public static AdministrationCommand<Activity> start(final Activity activity){
		return new AdministrationCommand<Activity>(Entity.ACTIVITY, Operation.START, UUID.randomUUID(), true, "", new Activity[]{activity});
	}
	public static AdministrationCommand<Activity> stop(Activity activity){
		return new AdministrationCommand<Activity>(Entity.ACTIVITY, Operation.STOP, UUID.randomUUID(), true, "", new Activity[]{activity});
	}
	
	public static AdministrationCommand<Activity> reply(AdministrationCommand<Activity> command,boolean isSuccessful,String errorMessage){
		return new AdministrationCommand<Activity>(command.getEntity(),command.getOperation(), command.getStreamId(), isSuccessful, errorMessage, command.getPayloadObjects());
	}
	
	

}
