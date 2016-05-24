package com.eptd.dminer.core;

import java.util.ArrayList;

public class Languages {
	private ArrayList<Language> list;
	
	public Languages(){
		this.list = new ArrayList<Language>();
	}
	
	/**
	 * Add a language used in a repo
	 * @param type the programming language type
	 * @param LOC the number of LOC used in the repo
	 * @return true if Language obj added successfully
	 */
	public boolean addLanguage(String type, long LOC){
		//
		if(this.list.add(new Language(type,LOC)))
			return true;
		return false;
	}
	
	/**
	 * Add a language used in a repo
	 * @param type the programming language type
	 * @return number of LOC of the specific language type used in the repo
	 */
	public long getLanguage(String type){
		for(int i=0;i<this.list.size();i++)
		{
			if(this.list.get(i).getType() == type)
				return this.list.get(i).getLOC();
		}
		return 0;
	}
	
	/**
	 * Add a language used in a repo
	 * @param anthoerList the Language ArrayList to be compared with
	 * @return true if this.list is similar with anotheList
	 */
	public boolean compareTo(ArrayList<Language> anotherList){
		// TODO
		return false;
	}
}
