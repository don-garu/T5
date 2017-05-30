package Model;

import java.util.*;

public class DataValue {
	private Attribute single_header;
	private Attribute single_tail;
	private Attribute error_header;
	private Attribute error_tail;
	private Attribute valid_header;
	private Attribute valid_tail;
	
	private HashMap<String, Integer> property_map;
	private HashMap<String, Integer> ifproperty_map;
	private ArrayList<Value> value_list;
	
	public DataValue(){
		single_header = new Attribute();
		single_tail = new Attribute();
		single_header.setNext(single_tail);
		single_tail.setPrev(single_header);

		error_header = new Attribute();
		error_tail = new Attribute();
		error_header.setNext(error_tail);
		error_tail.setPrev(error_header);
		
		valid_header = new Attribute();
		valid_tail = new Attribute();
		valid_header.setNext(valid_tail);
		valid_tail.setPrev(valid_header);
		
		property_map = new HashMap<String, Integer>();
		ifproperty_map = new HashMap<String, Integer>();
		value_list = new ArrayList<Value>();
	}
	
	public void addSingle(String name){
		single_header.addLast(name);
	}
	
	public void addError(String name){
		error_header.addLast(name);
	}
	
	public void addValid(String name){
		valid_header.addLast(name);
	}
	
	public void deleteSingle(String name){
		single_header.remove(single_header.whereAttribute(single_header, name));
	}
	
	public void deleteError(String name){
		error_header.remove(error_header.whereAttribute(error_header, name));
	}

	public void deleteValid(String name){
		valid_header.remove(valid_header.whereAttribute(valid_header, name));
	}
	
	public void addDataValue(Value v){
		if(v.isSingle()==true){
			addSingle(v.getName());
		}else if(v.isError()==true){
			addError(v.getName());
		}else if(v.isProperty()==true || v.isIfproperty() == true){
			addProperty(v);
		}else if(v.isValid()==true){
			addValid(v.getName());
			
		}else{
			System.out.println("DataValue 넣기 실패. ");
		}
	}
	
	public void deleteDataValue(Value v){
		if(v.isSingle()==true){
			deleteSingle(v.getName());
		}else if(v.isError()==true){
			deleteError(v.getName());
		}else if(v.isProperty()==true || v.isIfproperty() == true){
			deleteProperty(v);
		}else if(v.isValid()==true){
			deleteValid(v.getName());
		}else{
			System.out.println("DataValue 삭제 실패. ");
		}
	}

	//외부에서 addProperty,addIfproperty 와 deleteProperty, deleteIfproperty가 호출되는 것을 각각 아래의 addProperty와 deleteProperty가 수행된다고 전제했습니다.
	
	public void addProperty(Value value){
		value_list.add(value);
		String arr[];
		arr = value.getPropertyArr();
		for (int i = 0; i < arr.length && arr[i] != null; i++){
			if (property_map.get(arr[i]) == null)
				property_map.put(arr[i], 1);
			else
				property_map.put(arr[i], property_map.get(arr[i])+1);
		}
		arr = value.getIfpropertyArr();
		for (int i = 0; i < arr.length && arr[i] == null; i++){
			if (ifproperty_map.get(arr[i]) == null)
				ifproperty_map.put(arr[i], 1);
			else
				ifproperty_map.put(arr[i], ifproperty_map.get(arr[i])+1);
		}
	}

	public void deleteProperty(Value value){
		value_list.remove(value);
		String arr[];
		arr = value.getPropertyArr();
		for (int i = 0; i < arr.length && arr[i] != null; i++){
			property_map.put(arr[i], property_map.get(arr[i])-1);
			if (property_map.get(arr[i]) == 0)
				property_map.remove(arr[i]);
		}
		arr = value.getIfpropertyArr();
		for (int i = 0; i < arr.length && arr[i] != null; i++){
			ifproperty_map.put(arr[i], ifproperty_map.get(arr[i])-1);
			if (ifproperty_map.get(arr[i]) == 0)
				ifproperty_map.remove(arr[i]);
		}
	}

	public ArrayList<Value> getList(){
		return value_list;
	}

	public HashMap<String, Integer> getPropertyMap(){
		return property_map;
	}

	public HashMap<String, Integer> getIfpropertyMap(){
		return ifproperty_map;
	}

	public int getSingleCount(){
		return single_header.size();
	}

	public int getErrorCount(){		
		return error_header.size();
	}

	public int getPropertyCount(){
		return value_list.size();
	}
	
	public int getValidCount(){
		return valid_header.size();
	}
}