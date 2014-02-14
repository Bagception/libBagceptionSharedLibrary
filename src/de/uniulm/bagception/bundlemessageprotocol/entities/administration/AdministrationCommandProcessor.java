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
		
		
		if (command.getEntity() == Entity.ITEM){
			AdministrationCommand<Item> c = (AdministrationCommand<Item>) command;
			switch (c.getOperation()){
				case ADD: 
					callbacks.onItemAdd(c.getPayloadObjects()[0],c);
				break;
				
				case DEL: 
					callbacks.onItemDel(c.getPayloadObjects()[0],c);
				break;
				
				case EDIT: 
					callbacks.onItemEdit(c.getPayloadObjects()[0],c.getPayloadObjects()[1],c);
				break;
				
				case LIST: 
					callbacks.onItemList(c);
				break;
				
				default: break;

			}
			return;
		}
		
		if (command.getEntity() == Entity.LOCATION){
			AdministrationCommand<Location> c = (AdministrationCommand<Location>) command;
			switch (c.getOperation()){
				case ADD: 
					callbacks.onLocationAdd(c.getPayloadObjects()[0],c);
				break;
				
				case DEL: 
					callbacks.onLocationDel(c.getPayloadObjects()[0],c);
				break;
				
				case EDIT: 
					callbacks.onLocationEdit(c.getPayloadObjects()[0],c.getPayloadObjects()[1],c);
				break;
				
				case LIST: 
					callbacks.onLocationList(c);
				break;
				default: break;

			}
			return;
		}

		if (command.getEntity() == Entity.ACTIVITY){
			AdministrationCommand<Activity> c = (AdministrationCommand<Activity>) command;
			switch (c.getOperation()){
				case ADD: 
					callbacks.onActivityAdd(c.getPayloadObjects()[0],c);
				break;
				
				case DEL: 
					callbacks.onActivityDel(c.getPayloadObjects()[0],c);
				break;
				
				case EDIT: 
					callbacks.onActivityEdit(c.getPayloadObjects()[0],c.getPayloadObjects()[1],c);
				break;
				
				case LIST: 
					callbacks.onActivityList(c);
				break;
				
				case START:
					callbacks.onActivityStart(c.getPayloadObjects()[0], c);
					break;
					
				case STOP:
					callbacks.onActivityStop(c.getPayloadObjects()[0], c);
					break;

			}
			return;
		}
				
		if (command.getEntity() == Entity.CATEGORY){
			AdministrationCommand<Category> c = (AdministrationCommand<Category>) command;

			switch (c.getOperation()){
				case ADD: 
					callbacks.onCategoryAdd(c.getPayloadObjects()[0],c);
				break;
				
				case DEL: 
					callbacks.onCategoryDel(c.getPayloadObjects()[0],c);
				break;
				
				case EDIT: 
					callbacks.onCategoryEdit(c.getPayloadObjects()[0],c.getPayloadObjects()[1],c);
				break;
				
				case LIST: 
					callbacks.onCategoryList(c);
				break;
				default: break;

			}
			return;
		}
				

	}

	@Override
	public void onItemAdd(Item a,AdministrationCommand<Item> i) {
		
	}

	@Override
	public void onItemDel(Item a,AdministrationCommand<Item> i) {
		
	}

	@Override
	public void onItemEdit(Item toEdit, Item editValues,AdministrationCommand<Item> i) {
		
	}

	@Override
	public void onItemList(AdministrationCommand<Item> i) {
		
	}

	@Override
	public void onActivityAdd(Activity a,AdministrationCommand<Activity> i) {
		
	}

	@Override
	public void onActivityDel(Activity a,AdministrationCommand<Activity> i) {
		
	}

	@Override
	public void onActivityEdit(Activity toEdit, Activity editValues,AdministrationCommand<Activity> i) {
		
	}

	@Override
	public void onActivityList(AdministrationCommand<Activity> i) {
		
	}

	@Override
	public void onLocationAdd(Location a,AdministrationCommand<Location> i) {
		
	}

	@Override
	public void onLocationDel(Location a,AdministrationCommand<Location> i) {
		
	}

	@Override
	public void onLocationEdit(Location toEdit, Location editValues,AdministrationCommand<Location> i) {
		
	}

	@Override
	public void onLocationList(AdministrationCommand<Location> i) {
		
	}

	@Override
	public void onCategoryAdd(Category a,AdministrationCommand<Category> i) {
		
	}

	@Override
	public void onCategoryDel(Category a,AdministrationCommand<Category> i) {
		
	}

	@Override
	public void onCategoryEdit(Category toEdit, Category editValues,AdministrationCommand<Category> i) {
		
	}

	@Override
	public void onCategoryList(AdministrationCommand<Category> i) {
		
	}

	@Override
	public void onActivityStart(Activity a, AdministrationCommand<Activity> cmd) {
		
	}

	@Override
	public void onActivityStop(Activity a, AdministrationCommand<Activity> cmd) {
		
	}



}
