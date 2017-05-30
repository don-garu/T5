package Controller;

public class MainController {
	private DataController dc;
	private Calculator calculator;
	private String ongoingCategory;
	private boolean needRefresh = false;// valuePanel초기화용
	public MainController(){
		this.dc=new DataController();
		this.calculator = new Calculator();
	}
	public String getOngoingCategory(){
		return ongoingCategory;
	}
	public void setOngoingCategory(String s){
		this.ongoingCategory = s;
	}
	public boolean getneedRefresh(){
		return needRefresh;
	}
	public void setneedRefresh(boolean b){
		this.needRefresh=b;
	}
	//카테고리 관련 매소드  
	public void addCategory(String categoryName){
		dc.addCategory(categoryName);
	}
	public void deleteCategory(String categoryName){
		dc.deleteCategory(categoryName);
	}
	public void editCategory(String oldName,String newName){
		dc.editCategory(oldName, newName);
	}
	public int getCategoryIndex(String categoryName, int index){
		index = dc.getCategoryIndex(categoryName);
		return index;
	}
	// 벨류 관련 메소드 
	public void addValue(String categoryName, String valueName, String type[], String pname[], String iname[]){
		dc.addValue(categoryName, valueName, type, pname, iname);
		//index = dc.getCategoryIndex(categoryName,valueName);
	}
	public void deleteValue(String categoryName, String valueName){
		dc.deleteValue(categoryName, valueName);
	}
	public int getValueSize(String categoryName){
		return dc.getValueSize(categoryName);
	}
	public String getValueName(String categoryName, int valueIndex){
		return dc.getValueName(categoryName, valueIndex);
	}
	//view 용 메소드
	public void displayCategoryList(){
		
	}
	public void printList(){
		dc.printCategory();
	}
	public void printValueList(String categoryName){
		dc.printValue(categoryName);
	}
	public String calculate(){
		Integer i = calculator.calculate(dc.getCategoryHeader());
		System.out.println(calculator.calculate(dc.getCategoryHeader()));
		return i.toString();
	}
}
