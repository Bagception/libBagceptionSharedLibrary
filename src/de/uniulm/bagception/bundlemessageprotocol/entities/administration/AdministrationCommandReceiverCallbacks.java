package de.uniulm.bagception.bundlemessageprotocol.entities.administration;

import de.uniulm.bagception.bundlemessageprotocol.entities.Activity;
import de.uniulm.bagception.bundlemessageprotocol.entities.Category;
import de.uniulm.bagception.bundlemessageprotocol.entities.Item;
import de.uniulm.bagception.bundlemessageprotocol.entities.Location;

public interface AdministrationCommandReceiverCallbacks{
	
	public void onItemAdd(Item a);
	public void onItemDel(Item a);
	public void onItemEdit(Item toEdit, Item editValues);
	public void onItemList();
	
	public void onActivityAdd(Activity a);
	public void onActivityDel(Activity a);
	public void onActivityEdit(Activity toEdit, Activity editValues);
	public void onActivityList();
	
	public void onLocationAdd(Location a);
	public void onLocationDel(Location a);
	public void onLocationEdit(Location toEdit, Location editValues);
	public void onLocationList();
	
	public void onCategoryAdd(Category a);
	public void onCategoryDel(Category a);
	public void onCategoryEdit(Category toEdit, Category editValues);
	public void onCategoryList();
	
	public void onReply(AdministrationCommand<?> replyCommand);
	
}
