package de.uniulm.bagception.bundlemessageprotocol.entities.administration;

import java.util.UUID;

import de.uniulm.bagception.bundlemessageprotocol.entities.Location;
import de.uniulm.bagception.bundlemessageprotocol.entities.administration.AdministrationCommand.Entity;
import de.uniulm.bagception.bundlemessageprotocol.entities.administration.AdministrationCommand.Operation;

public abstract class LocationCommand{



	
	
	public static AdministrationCommand<Location> add(Location toCreate){
		return new AdministrationCommand<Location>(Entity.LOCATION, Operation.ADD, UUID.randomUUID(), true, "", new Location[]{toCreate});
	}
	
	public static AdministrationCommand<Location> remove(Location toRemove){
		return new AdministrationCommand<Location>(Entity.LOCATION, Operation.DEL, UUID.randomUUID(), true, "", new Location[]{toRemove});
	}
	
	public static AdministrationCommand<Location> edit(Location toEdit,Location editValues){
		return new AdministrationCommand<Location>(Entity.LOCATION, Operation.EDIT, UUID.randomUUID(), true, "", new Location[]{toEdit,editValues});
	}
	public static AdministrationCommand<Location> list(){
		return new AdministrationCommand<Location>(Entity.LOCATION, Operation.LIST, UUID.randomUUID(), true, "", new Location[]{});
	}
	
	public static AdministrationCommand<Location> reply(AdministrationCommand<Location> command,boolean isSuccessful,String errorMessage){
		return new AdministrationCommand<Location>(command.getEntity(),command.getOperation(), command.getStreamId(), isSuccessful, errorMessage, command.getPayloadObjects());
	}
	

}
