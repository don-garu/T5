package Model;

public class Attribute {
	private Attribute prev;
	private Attribute next;
	private String name;
	private int Index;
	private int count=0;
	
	public Attribute(){			//생성자 메소드
		prev=null;
		next=null;
		setName("없음");
		setAttributeIndex(0);
	}
	public void setPrev(Attribute p){	//노드의 prev를 지정해준다.
		prev=p;
	}
	public void setNext(Attribute n){	//노드의 next를 지정해준다.
		next=n;
	}
	
	private Attribute(Attribute _prev, Attribute _next, String str){	//노드를 생성하고 노드의 prev,next,value의 값을 지정해준다.
		prev = _prev;
		next = _next;
		name = str;
	}
	
	public Attribute first(){
		Attribute p = this;		//현재 노드를 참조한다.
		while(p.prev != null){		//노드의 prev가 null일 때까지 계속 전 노드로 간다.
			p = p.prev;
		}
		return p.next;	//마지막 노드가 header가 되므로 하나 앞의 노드를 리턴해준다.
	}
	
	
	public void addLast(String name){
		Attribute AttributeNew;
		Attribute temp=this;
		while(temp.next.name != "없음"){		//tail의 하나전 노드를 가리키게 한다.
			temp=temp.next;
		}
		AttributeNew = new Attribute(temp,temp.next,name);	//새로운 노드의 prev는 temp를, next는 temp의 next를 가리키게 하고 value값을 넣는다.
		temp.next = AttributeNew;	//temp의 next는 새로운 노드를 가리키게 한다.
		temp.next.next = AttributeNew.next;	//새로운 노드의 다음노드는 새로운 노드를 가리키게 한다.
		count++;	//추가한 노드를 세어준다.
	}
	
	public void addAfter(Attribute p, String name){
		Attribute AttributeNew;
		AttributeNew = new Attribute(p,p.next,name);		//새로운 노드의 prev는 p를, next는 p의 next를 가리키게 하고 value값을 넣는다.
		p.next = AttributeNew;		//p의 next는 새로운 노드를 가리키게 한다.
		p.next.next.prev = AttributeNew;	//새로운 노드의 다음 노드는 새로운 노드를 가리키게 한다.
		count++;	//추가한 노드를 세어준다.
	}
	
	public void addBefore(Attribute p, String name){
		Attribute AttributeNew;
		AttributeNew = new Attribute(p.prev,p,name);		//새로운 노드의 prev는 p의 전 노드를, next는 p를 가리키게 하고 value값을 넣는다.
		p.prev.next = AttributeNew;		//p의 전전 노드의 next는 새로운 노드를 기리키게 한다.
		p.prev = AttributeNew.next;		//p의 prev는 새로운 노드를 가리키게한다.
		count++;	//추가한 노드를 세어준다.
	}
	
	private void print(){
		System.out.print("["+name+"]");
	}
	
	public Attribute whereAttribute(Attribute p,String name){	//입력받은 value 위치의 노드를 리턴시킨다.
		for(int i=0; i<count; i++){
			p = p.next;
			if(p.name == name)
				break;
		}
		return p;
	}
	
	public void printAllAttribute(){
		Attribute temp=this;
		while(temp.next.name != "없음"){		//tail 전 노드까지만 읽는다.
			temp=temp.next;
			temp.print();
		}
		System.out.println();
	}
	
	public String remove(Attribute p){
		String val = p.name;		//현재 p의 값을 저장한다.
		p.prev.next = p.next;		//p의 전노드가 p의 다음 노드를 가리키게 한다.
		p.next.prev = p.prev.next;	//p의 다음노드의 prev가 p의 전노드를 가리키게 한다.
		count--;		//제거한 노드를 세어준다.
		return val;
	}
	
	public Attribute prev(Attribute p){		//p의 전 노드를 리턴한다.
		return p.prev;
	}
	
	public Attribute next(Attribute p){		//p의 다음 노드를 리턴한다.
		return p.next;
	}
	
	public String set(Attribute p, String value){		
		String temp = p.name;		//현재 p의 값을 저장한다.
		p.name = value;		//입력받은 value를 p에 저장한다.
		
		return temp;			//처음 저장한 p의 값을 리턴한다.
	}
	
	public int size(){		//header와 tail의 갯수를 제외한 노드의 갯수를 리턴한다.
		return count;
	}
	
	public boolean isEmpty(){		//header와 tail의 갯수를 제외한 노드의 갯수가 0인지 아닌지를 판단한다.
		if(count==0)
			return true;
		else
			return false;
	}
	public int getAttributeIndex() {
		return Index;
	}
	public void setAttributeIndex(int AttributeIndex) {
		this.Index = AttributeIndex;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}