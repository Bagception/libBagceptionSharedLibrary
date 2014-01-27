package de.uniulm.bagception.bundlemessageprotocol.entities.administration;

import java.util.UUID;

import de.uniulm.bagception.bundlemessageprotocol.entities.Category;
import de.uniulm.bagception.bundlemessageprotocol.entities.administration.AdministrationCommand.Entity;
import de.uniulm.bagception.bundlemessageprotocol.entities.administration.AdministrationCommand.Operation;

public abstract class CategoryCommand{


	
	
	public static AdministrationCommand<Category> add(Category toCreate){
		return new AdministrationCommand<Category>(Entity.CATEGORY, Operation.ADD, UUID.randomUUID(), true, "", new Category[]{toCreate});
	}
	
	public static AdministrationCommand<Category> remove(Category toRemove){
		return new AdministrationCommand<Category>(Entity.CATEGORY, Operation.DEL, UUID.randomUUID(), true, "", new Category[]{toRemove});
	}
	
	public static AdministrationCommand<Category> edit(Category toEdit,Category editValues){
		return new AdministrationCommand<Category>(Entity.CATEGORY, Operation.EDIT, UUID.randomUUID(), true, "", new Category[]{toEdit,editValues});
	}
	public static AdministrationCommand<Category> list(){
		return new AdministrationCommand<Category>(Entity.CATEGORY, Operation.LIST, UUID.randomUUID(), true, "", new Category[]{});
	}
	
	public static AdministrationCommand<Category> reply(AdministrationCommand<Category> command,boolean isSuccessful,String errorMessage){
		return new AdministrationCommand<Category>(command.getEntity(),command.getOperation(), command.getStreamId(), isSuccessful, errorMessage, command.getPayloadObjects());
	}
	

}
