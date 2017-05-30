


package Controller;

import java.util.*;

import Model.Category;
import Model.DataValue;
import Model.Value;

public class Calculator {

	public int calculate(Category category) {
		HashSet<String> property_group = new HashSet<String>();
		ArrayList<Category> category_list = new ArrayList<Category>();
		
		int single_and_error = 0;
		int external_count = 1;
		
		category = category.first();
		for (int i = 0; i < category.size(); i++, category = category.next(category)){				//Single/Error/Valid로만 이루어진 카테고리를 따로 계산한다.
			DataValue dv = category.getDv();
			
			single_and_error += dv.getSingleCount() + dv.getErrorCount();
			if (dv.getPropertyCount() == 0)
				external_count *= Math.max(1, dv.getValidCount());
			else
				category_list.add(category);
		}
		
		//sort
		int i = 0, j = 0;
		while (i < category_list.size()){															//Property 관련 카테고리를 계산하기 쉽게 정렬한다.
			boolean loc = true;
			
			Iterator<String> iter = category_list.get(i).getDv().getIfpropertyMap().keySet().iterator();
			while (iter.hasNext()){
				boolean swap = false;
				
				String key = iter.next();
				for (j = i+1; j < category_list.size(); j++){
					if (category_list.get(j).getDv().getPropertyMap().containsKey(key)){
						swap = true;
						loc = false;
						break;
					}
				}
				if (swap == true){
					Category temp = category_list.get(j);
					category_list.set(j, category_list.get(i));
					category_list.set(i, temp);
				}
			}
			
			if (loc == true)
				i++;
		}
		
		//calculate
		int internal_count = 1;
		if (category_list.size() != 0)
			internal_count = internal_calculate(1, property_group, category_list, 0);						//Property 관련 계산을 실행한다.
		
		int result = external_count * internal_count + single_and_error;
		return result;
	}

	public int internal_calculate(int valid_count, HashSet<String> property_group, ArrayList<Category> category_list, int index){
		DataValue dv = category_list.get(index).getDv();
		ArrayList<Value> list = dv.getList();
		boolean nothing_check = true;
		
		int result = 0;
		int i = 0;
		if ((i = dv.getValidCount()) != 0){															//Valid 값이 있다면 우선 이를 먼저 고려한다.	
			nothing_check = false;
			if (index >= category_list.size()-1)													//마지막 Category라면  Valid 값을 곱하여 result에 더한다.
				result += valid_count*i;
			else																					//Category가 남았다면 다음 Category를 호출한다.
				result += internal_calculate(valid_count*i, property_group, category_list, index+1);
		}
		
		for (i = 0; i < list.size(); i++)																//Property 순회
		{
			if (findKey(property_group, list.get(i))){																		//해당 Value의 If-Property 조건이 충족되었을 경우
				nothing_check = false;

				//System.out.println("Remain Category : "+(category_list.size()-1-index));
				if (index >= category_list.size()-1)												//마지막 Category라면 result에 valid_count 값만을 더한다.
					result += valid_count;
				else{																				//Category가 남았을 경우
					String arr[] = list.get(i).getPropertyArr();
					for (int j = 0; j < arr.length && arr[j] != null; j++)							//해당 Value의 Property를 property_group에 넣고 다음 Category를 호출한 뒤,
						property_group.add(arr[j]);
					result += internal_calculate(valid_count, property_group, category_list, index+1);
					for (int j = 0; j < arr.length && arr[j] != null; j++)							//다시 그 Value의 Property를 제외하여 원상복구시키고 작업을 반복한다.
						property_group.remove(arr[j]);
				}
			}
		}
		
		if (nothing_check == true)
		{
			if (index >= category_list.size()-1)
				return valid_count;
			else
				return internal_calculate(valid_count, property_group, category_list, index+1);
		}
		
		return result;
	}
	
	public boolean findKey(HashSet<String> property_group, Value value)
	{
		String arr[] = value.getIfpropertyArr();
		
		for (int i = 0; i < arr.length && arr[i] != null; i++)
		{
			if (property_group.contains(arr[i]) == false)
				return false;
		}
		return true;
	}
}