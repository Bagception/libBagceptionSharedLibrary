package de.uniulm.bagception.bundlemessageprotocol.entities.administration;

import java.util.UUID;

import de.uniulm.bagception.bundlemessageprotocol.entities.Item;
import de.uniulm.bagception.bundlemessageprotocol.entities.administration.AdministrationCommand.Entity;
import de.uniulm.bagception.bundlemessageprotocol.entities.administration.AdministrationCommand.Operation;

public abstract class ItemCommand{


	
	
	public static AdministrationCommand<Item> add(Item toCreate){
		toCreate.serializeImage();
		return new AdministrationCommand<Item>(Entity.ITEM, Operation.ADD, UUID.randomUUID(), true, "", new Item[]{toCreate});
	}
	
	public static AdministrationCommand<Item> remove(Item toRemove){
		return new AdministrationCommand<Item>(Entity.ITEM, Operation.DEL, UUID.randomUUID(), true, "", new Item[]{toRemove});
	}
	
	public static AdministrationCommand<Item> edit(Item toEdit,Item editValues){
		return new AdministrationCommand<Item>(Entity.ITEM, Operation.EDIT, UUID.randomUUID(), true, "", new Item[]{toEdit,editValues});
	}
	public static AdministrationCommand<Item> list(){
		return new AdministrationCommand<Item>(Entity.ITEM, Operation.LIST, UUID.randomUUID(), true, "", new Item[]{});
	}
	
	public static AdministrationCommand<Item> reply(AdministrationCommand<Item> command,boolean isSuccessful,String errorMessage){
		return new AdministrationCommand<Item>(command.getEntity(),command.getOperation(), command.getStreamId(), isSuccessful, errorMessage, command.getPayloadObjects());
	}
	

}
