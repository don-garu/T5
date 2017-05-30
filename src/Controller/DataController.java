package Controller;

import java.util.ArrayList;

import Model.Category;

public class DataController {
	private Category headerCategory;
	private Category tailCategory;
	
	public DataController(){
		headerCategory = new Category();
		tailCategory = new Category();
		headerCategory.setNext(tailCategory);
		tailCategory.setPrev(headerCategory);
	}
	public void addCategory(String categoryName){
		headerCategory.addLast(categoryName);
	}
	public void deleteCategory(String categoryName){
		headerCategory.remove((headerCategory.whereCategory(headerCategory, categoryName)));
	}
	public void editCategory(String oldName, String newName){
		(headerCategory.whereCategory(headerCategory, oldName)).setName(newName);
	}
	public int getCategoryIndex(String categoryName){
		int temp = (headerCategory.whereCategory(headerCategory, categoryName)).getCategoryIndex();
		return temp;
	}
	public void addValue(String categoryName, String valueName, String type[], String pname[], String iname[]){
		(headerCategory.whereCategory(headerCategory, categoryName)).addValue(valueName,type, pname, iname);
	}
	public void deleteValue(String categoryName,String valueName){
		(headerCategory.whereCategory(headerCategory, categoryName)).deleteValue(valueName);
	}
	public String getValueName(String categoryName, int valueIndex){
		((headerCategory.whereCategory(headerCategory, categoryName)).getValue()).getName();
		return categoryName;
	}
	public int getValueSize(String categoryName){
		//((headerCategory.whereCategory(headerCategory, categoryName)).getValue()).printAllValue();
		return ((headerCategory.whereCategory(headerCategory, categoryName)).getValue()).size();
	}
	public void printCategory(){
		headerCategory.printAllCategory();
	}
	public void printValue(String categoryName){
		(headerCategory.whereCategory(headerCategory, categoryName)).getValue().printAllValue();
	
	}
	public Category getCategoryHeader(){
		return headerCategory;
	}
}
