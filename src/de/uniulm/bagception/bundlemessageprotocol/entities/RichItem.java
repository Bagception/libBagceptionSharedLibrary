package de.uniulm.bagception.bundlemessageprotocol.entities;

import java.util.ArrayList;

public class RichItem extends Item{

	private final ContextSuggestion sug;
	private final boolean isNeedless;
	
	public RichItem(Item i,ContextSuggestion sug,boolean isNeedless) {
		super(i.getId(),i.getName(),i.getCategory(),i.getContextItem(),i.getIndependentItem(),i.getAttribute(),new ArrayList<String>(i.getIds()));
		this.sug = sug;
		this.isNeedless = isNeedless;
	}
	
	

	public boolean hasContext(){
		return sug.getReason() != null;
	}
	public ContextSuggestion getContextSuggestion(){
		return sug;
	}
	public boolean isNeedless(){
		return isNeedless;
	}
	
	
}
