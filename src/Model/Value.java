package Model;

public class Value {
	private String name;
	private Value prev;
	private Value next;
	private boolean single;
	private boolean error;
	private boolean property;
	private boolean ifproperty;
	private boolean valid;
	
	private String[] propertyArr;
	private String[] ifpropertyArr;
	private int pcount;
	private int icount;
	
	
	private int valueIndex=0;
	private static int count=0;
	private static int indexCount=0;
	
	public Value(){			//생성자 메소드
		prev = null;
		next = null;
		setValueIndex(0);
		name = "없음";
		
		setSingle(false);
		setError(false);
		setProperty(false);
		setIfproperty(false);
		setValid(false);
		
		propertyArr = new String[5];
		ifpropertyArr = new String[5];
		pcount = 0;
		icount = 0;
	}
	public void setPrev(Value p){	//노드의 prev를 지정해준다.
		prev = p;
	}
	public void setNext(Value n){	//노드의 next를 지정해준다.
		next = n;
	}
	
	private Value(Value _prev, Value _next, String str, String type[], String pname[], String iname[]){	//노드를 생성하고 노드의 prev,next,value의 값을 지정해준다.
		prev = _prev;
		next = _next;
		name = str;
		setSingle(false);
		setError(false);
		setProperty(false);
		setIfproperty(false);
		setValid(false);
		
		propertyArr = new String[5];
		ifpropertyArr = new String[5];
		pcount = 0;
		icount = 0;
		for(int i =0; i<5; i++){
			if(type[i]==null){
				
			}else{
				if(type[i].equals("single")){
					this.setSingle(true);
				}
				if(type[i].equals("error")){
					this.setError(true);
				}
				if(type[i].equals("property")){
					for(int j=0; j<5; j++){
						if(pname[j]==null)
							break;
						propertyArr[pcount] = pname[j];
						System.out.println("pro: "+propertyArr[pcount]);
						pcount++;
					}
					this.setProperty(true);
				}
				if(type[i].equals("ifproperty")){
					for(int j=0; j<5; j++){
						if(iname[j]==null)
							break;
						ifpropertyArr[icount] = iname[j];
						System.out.println("ifpro:"+ ifpropertyArr[icount]);
						icount++;
					}
					this.setIfproperty(true);
				}
				if(type[i].equals("valid")){		
					this.setValid(true);
				}
			}
			
		}
	}
	
	public Value first(){
		Value p = this;		//현재 노드를 참조한다.
		while(p.prev != null){		//노드의 prev가 null일 때까지 계속 전 노드로 간다.
			p = p.prev;
		}
		return p.next;	//마지막 노드가 header가 되므로 하나 앞의 노드를 리턴해준다.
	}
	
	
	public Value addLast(String name, String type[], String pname[], String iname[]){
		Value ValueNew;
		Value temp=this;
		while(temp.next.name != "없음"){		//tail의 하나전 노드를 가리키게 한다.
			temp=temp.next;
		}
		ValueNew = new Value(temp,temp.next,name,type,pname,iname);	//새로운 노드의 prev는 temp를, next는 temp의 next를 가리키게 하고 value값을 넣는다.
		temp.next = ValueNew;	//temp의 next는 새로운 노드를 가리키게 한다.
		temp.next.next = ValueNew.next;	//새로운 노드의 다음노드는 새로운 노드를 가리키게 한다.
		ValueNew.setValueIndex(indexCount);
		count++;	//추가한 노드를 세어준다.
		indexCount++;
		
		return ValueNew;
	}
	private void print(){
		System.out.print("["+name+"]");
	}
	
	public Value whereValue(Value p, String name){	//입력받은 숫자번호 위치의 노드를 리턴시킨다.
		for(int i=0; i<count; i++){
			p = p.next;
			if(p.name == name){
				break;
			}
			if(p.name.equals(name)){
				break;
			}
		}
		return p;
	}

	public void printAllValue(){
		Value temp=this;
		while(temp.next.name != "없음"){		//tail 전 노드까지만 읽는다.
			temp=temp.next;
			temp.print();
		}
		System.out.println();
	}
	
	public Value remove(Value p){
		Value temp = p;		//현재 p의 값을 저장한다.
		p.prev.next = p.next;		//p의 전노드가 p의 다음 노드를 가리키게 한다.
		p.next.prev = p.prev.next;	//p의 다음노드의 prev가 p의 전노드를 가리키게 한다.
		count--;		//제거한 노드를 세어준다.
		return temp;
	}
	
	public Value prev(Value p){		//p의 전 노드를 리턴한다.
		return p.prev;
	}
	
	public Value next(Value p){		//p의 다음 노드를 리턴한다.
		return p.next;
	}
	
	public String set(Value p, String value){		
		String temp = p.name;		//현재 p의 값을 저장한다.
		p.name = value;		//입력받은 value를 p에 저장한다.
		
		return temp;			//처음 저장한 p의 값을 리턴한다.
	}
	
	public int size(){		//header와 tail의 갯수를 제외한 노드의 갯수를 리턴한다.
		Value temp=this;
		int tempCount=0;
		while(temp.next.name != "없음"){		//tail 전 노드까지만 읽는다.
			temp=temp.next;
			tempCount++;
		}
		return tempCount;
	}
	
	public boolean isEmpty(){		//header와 tail의 갯수를 제외한 노드의 갯수가 0인지 아닌지를 판단한다.
		if(count==0)
			return true;
		else
			return false;
	}
	public int getValueIndex() {
		return valueIndex;
	}
	public void setValueIndex(int valueIndex) {
		this.valueIndex = valueIndex;
	}
	
	public String[] getPropertyArr() {
		return propertyArr;
	}
	public void setPropertyArr(String[] propertyArr) {
		this.propertyArr = propertyArr;
	}
	public String[] getIfpropertyArr() {
		return ifpropertyArr;
	}
	public void setIfpropertyArr(String[] ifpropertyArr) {
		this.ifpropertyArr = ifpropertyArr;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public boolean isSingle() {
		return single;
	}
	public void setSingle(boolean single) {
		this.single = single;
	}
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	
	public boolean isProperty() {
		return property;
	}
	public void setProperty(boolean property) {
		this.property = property;
	}
	
	public boolean isIfproperty() {
		return ifproperty;
	}
	public void setIfproperty(boolean ifproperty) {
		this.ifproperty = ifproperty;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
}