package de.uniulm.bagception.bundlemessageprotocol.entities.administration;

import de.uniulm.bagception.bundlemessageprotocol.entities.Activity;
import de.uniulm.bagception.bundlemessageprotocol.entities.Category;
import de.uniulm.bagception.bundlemessageprotocol.entities.Item;
import de.uniulm.bagception.bundlemessageprotocol.entities.Location;
import de.uniulm.bagception.bundlemessageprotocol.entities.administration.AdministrationCommand.Entity;


public class AdministrationCommandProcessor implements AdministrationCommandReceiverCallbacks{

	private AdministrationCommandReceiverCallbacks callbacks;
	public AdministrationCommandProcessor(AdministrationCommandReceiverCallbacks callbacks){
		this.callbacks = callbacks;
	}
	
	public AdministrationCommandProcessor(){
		
		callbacks = this;
	}
	
	@SuppressWarnings("unchecked")
	public final void process(AdministrationCommand<?> command){
		
		onReply(command);
		
		if (command.getEntity() == Entity.ITEM){
			AdministrationCommand<Item> c = (AdministrationCommand<Item>) command;
			switch (c.getOperation()){
				case ADD: 
					callbacks.onItemAdd(c.getPayloadObjects()[0]);
				break;
				
				case DEL: 
					callbacks.onItemDel(c.getPayloadObjects()[0]);
				break;
				
				case EDIT: 
					callbacks.onItemEdit(c.getPayloadObjects()[0],c.getPayloadObjects()[1]);
				break;
				
				case LIST: 
					callbacks.onItemList();
				break;
			}
			return;
		}
		
		if (command.getEntity() == Entity.LOCATION){
			AdministrationCommand<Location> c = (AdministrationCommand<Location>) command;
			switch (c.getOperation()){
				case ADD: 
					callbacks.onLocationAdd(c.getPayloadObjects()[0]);
				break;
				
				case DEL: 
					callbacks.onLocationDel(c.getPayloadObjects()[0]);
				break;
				
				case EDIT: 
					callbacks.onLocationEdit(c.getPayloadObjects()[0],c.getPayloadObjects()[1]);
				break;
				
				case LIST: 
					callbacks.onLocationList();
				break;
			}
			return;
		}

		if (command.getEntity() == Entity.ACTIVITY){
			AdministrationCommand<Activity> c = (AdministrationCommand<Activity>) command;
			switch (c.getOperation()){
				case ADD: 
					callbacks.onActivityAdd(c.getPayloadObjects()[0]);
				break;
				
				case DEL: 
					callbacks.onActivityDel(c.getPayloadObjects()[0]);
				break;
				
				case EDIT: 
					callbacks.onActivityEdit(c.getPayloadObjects()[0],c.getPayloadObjects()[1]);
				break;
				
				case LIST: 
					callbacks.onActivityList();
				break;
			}
			return;
		}
				
		if (command.getEntity() == Entity.CATEGORY){
			AdministrationCommand<Category> c = (AdministrationCommand<Category>) command;

			switch (c.getOperation()){
				case ADD: 
					callbacks.onCategoryAdd(c.getPayloadObjects()[0]);
				break;
				
				case DEL: 
					callbacks.onCategoryDel(c.getPayloadObjects()[0]);
				break;
				
				case EDIT: 
					callbacks.onCategoryEdit(c.getPayloadObjects()[0],c.getPayloadObjects()[1]);
				break;
				
				case LIST: 
					callbacks.onCategoryList();
				break;
			}
			return;
		}
				

	}

	@Override
	public void onItemAdd(Item a) {
		
	}

	@Override
	public void onItemDel(Item a) {
		
	}

	@Override
	public void onItemEdit(Item toEdit, Item editValues) {
		
	}

	@Override
	public void onItemList() {
		
	}

	@Override
	public void onActivityAdd(Activity a) {
		
	}

	@Override
	public void onActivityDel(Activity a) {
		
	}

	@Override
	public void onActivityEdit(Activity toEdit, Activity editValues) {
		
	}

	@Override
	public void onActivityList() {
		
	}

	@Override
	public void onLocationAdd(Location a) {
		
	}

	@Override
	public void onLocationDel(Location a) {
		
	}

	@Override
	public void onLocationEdit(Location toEdit, Location editValues) {
		
	}

	@Override
	public void onLocationList() {
		
	}

	@Override
	public void onCategoryAdd(Category a) {
		
	}

	@Override
	public void onCategoryDel(Category a) {
		
	}

	@Override
	public void onCategoryEdit(Category toEdit, Category editValues) {
		
	}

	@Override
	public void onCategoryList() {
		
	}

	@Override
	public void onReply(AdministrationCommand<?> replyCommand) {
		
	}
	

}
