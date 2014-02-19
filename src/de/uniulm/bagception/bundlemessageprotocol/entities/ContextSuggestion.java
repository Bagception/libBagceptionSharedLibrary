package de.uniulm.bagception.bundlemessageprotocol.entities;

import java.util.List;

public class ContextSuggestion{

	private final Item itemToReplace;
	public Item getItemToReplace() {
		return itemToReplace;
	}

	
	private final List<Item> replaceSuggestions;
	public List<Item> getReplaceSuggestions() {
		return replaceSuggestions;
	}
	
	private final CONTEXT reason;
	public CONTEXT getReason() {
		return reason;
	}
	
	
	public enum CONTEXT{WARM,COLD,RAIN,SUNNY,DARK,BRIGHT};
	
	public ContextSuggestion(Item itemToReplItem, List<Item> replaceSuggestions,CONTEXT reason) {
		this.itemToReplace = itemToReplItem;
		this.replaceSuggestions= replaceSuggestions;
		this.reason=reason;
	}
	

	

	//TODO SERIALIZE



}