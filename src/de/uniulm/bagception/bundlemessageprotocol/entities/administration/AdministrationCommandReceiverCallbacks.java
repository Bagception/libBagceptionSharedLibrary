package de.uniulm.bagception.bundlemessageprotocol.entities.administration;

import de.uniulm.bagception.bundlemessageprotocol.entities.Activity;
import de.uniulm.bagception.bundlemessageprotocol.entities.Category;
import de.uniulm.bagception.bundlemessageprotocol.entities.Item;
import de.uniulm.bagception.bundlemessageprotocol.entities.Location;

public interface AdministrationCommandReceiverCallbacks{
	
	public void onItemAdd(Item a,AdministrationCommand<Item> cmd);
	public void onItemDel(Item a,AdministrationCommand<Item> cmd);
	public void onItemEdit(Item toEdit, Item editValues,AdministrationCommand<Item> cmd);
	public void onItemList(AdministrationCommand<Item> cmd);
	
	public void onActivityAdd(Activity a,AdministrationCommand<Activity> cmd);
	public void onActivityDel(Activity a,AdministrationCommand<Activity> cmd);
	public void onActivityEdit(Activity toEdit, Activity editValues,AdministrationCommand<Activity> cmd);
	public void onActivityList(AdministrationCommand<Activity> cmd);
	
	public void onLocationAdd(Location a,AdministrationCommand<Location> cmd);
	public void onLocationDel(Location a,AdministrationCommand<Location> cmd);
	public void onLocationEdit(Location toEdit, Location editValues,AdministrationCommand<Location> cmd);
	public void onLocationList(AdministrationCommand<Location> cmd);
	
	public void onCategoryAdd(Category a,AdministrationCommand<Category> cmd);
	public void onCategoryDel(Category a,AdministrationCommand<Category> cmd);
	public void onCategoryEdit(Category toEdit, Category editValues,AdministrationCommand<Category> cmd);
	public void onCategoryList(AdministrationCommand<Category> cmd);
	
	
	
}
