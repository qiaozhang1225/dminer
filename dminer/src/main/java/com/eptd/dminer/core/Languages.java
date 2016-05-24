package com.eptd.dminer.core;

import java.util.ArrayList;

public class Languages {
	private ArrayList<Language> list;
	
	public Languages(){
		this.list = new ArrayList<Language>();
	}
	
	public boolean addLanguage(String type, long LOC){
		if(this.list.add(new Language(type,LOC)))
			return true;
		return false;
	}
	
	public long getLanguage(String type){
		for(int i=0;i<this.list.size();i++)
		{
			if(this.list.get(i).getType() == type)
				return this.list.get(i).getLOC();
		}
		return 0;
	}
	
	public boolean compareTo(ArrayList<Language> target){
		// ## to-do
		// return true if this.list is similar with target
		return false;
	}
}
