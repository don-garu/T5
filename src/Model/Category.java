package Model;

public class Category {
	private Value header;
	private Value tail;
	private DataValue dv;
	private Category prev;
	private Category next;
	private String name;
	private int categoryIndex;
	private static int count=0;
	private static int indexCount=0;
	
	public Category(){			//생성자 메소드
		prev=null;
		next=null;
		setName("없음");
		setCategoryIndex(0);
		
		/* Value의 헤드,테일 연결 */
		header = new Value();
		tail = new Value();
		header.setNext(tail);
		tail.setPrev(header);
		
		setDv(new DataValue());
	}
	public void setPrev(Category p){	//노드의 prev를 지정해준다.
		prev=p;
	}
	public void setNext(Category n){	//노드의 next를 지정해준다.
		next=n;
	}
	
	private Category(Category _prev, Category _next, String str){	//노드를 생성하고 노드의 prev,next,value의 값을 지정해준다.
		prev = _prev;
		next = _next;
		name = str;
		
		/* Value의 헤드,테일 연결 */
		header = new Value();
		tail = new Value();
		header.setNext(tail);
		tail.setPrev(header);
		setDv(new DataValue());
	}
	
	public Category first(){
		Category p = this;		//현재 노드를 참조한다.
		while(p.prev != null){		//노드의 prev가 null일 때까지 계속 전 노드로 간다.
			p = p.prev;
		}
		return p.next;	//마지막 노드가 header가 되므로 하나 앞의 노드를 리턴해준다.
	}
	
	
	public void addLast(String value){
		Category CategoryNew;
		Category temp=this;
		while(temp.next.name != "없음"){		//tail의 하나전 노드를 가리키게 한다.
			temp=temp.next;
		}
		CategoryNew = new Category(temp,temp.next,value);	//새로운 노드의 prev는 temp를, next는 temp의 next를 가리키게 하고 value값을 넣는다.
		temp.next = CategoryNew;	//temp의 next는 새로운 노드를 가리키게 한다.
		temp.next.next = CategoryNew.next;	//새로운 노드의 다음노드는 새로운 노드를 가리키게 한다.
		CategoryNew.setCategoryIndex(indexCount);
		count++;	//추가한 노드를 세어준다.
		indexCount++;
	}
	
	public void addAfter(Category p, String value){
		Category CategoryNew;
		CategoryNew = new Category(p,p.next,value);		//새로운 노드의 prev는 p를, next는 p의 next를 가리키게 하고 value값을 넣는다.
		p.next = CategoryNew;		//p의 next는 새로운 노드를 가리키게 한다.
		p.next.next.prev = CategoryNew;	//새로운 노드의 다음 노드는 새로운 노드를 가리키게 한다.
		CategoryNew.setCategoryIndex(indexCount);
		count++;	//추가한 노드를 세어준다.
		indexCount++;
	}
	
	public void addBefore(Category p, String value){
		Category CategoryNew;
		CategoryNew = new Category(p.prev,p,value);		//새로운 노드의 prev는 p의 전 노드를, next는 p를 가리키게 하고 value값을 넣는다.
		p.prev.next = CategoryNew;		//p의 전전 노드의 next는 새로운 노드를 기리키게 한다.
		p.prev = CategoryNew.next;		//p의 prev는 새로운 노드를 가리키게한다.
		CategoryNew.setCategoryIndex(indexCount);
		count++;	//추가한 노드를 세어준다.
		indexCount++;
	}
	
	private void print(){
		System.out.print("["+name+"]");
	}
	
	public Category whereCategory(Category p,String name){	//입력받은 value 위치의 노드를 리턴시킨다.
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
	
	public void printAllCategory(){
		Category temp=this;
		while(temp.next.name != "없음"){		//tail 전 노드까지만 읽는다.
			temp=temp.next;
			temp.print();
		}
		System.out.println();
	}
	
	public String remove(Category p){
		String val = p.name;		//현재 p의 값을 저장한다.
		p.prev.next = p.next;		//p의 전노드가 p의 다음 노드를 가리키게 한다.
		p.next.prev = p.prev.next;	//p의 다음노드의 prev가 p의 전노드를 가리키게 한다.
		count--;		//제거한 노드를 세어준다.
		return val;
	}
	
	public Category prev(Category p){		//p의 전 노드를 리턴한다.
		return p.prev;
	}
	
	public Category next(Category p){		//p의 다음 노드를 리턴한다.
		return p.next;
	}
	
	public String set(Category p, String value){		
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
	public int getCategoryIndex() {
		return categoryIndex;
	}
	public void setCategoryIndex(int categoryIndex) {
		this.categoryIndex = categoryIndex;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void addValue(String name,String type[], String pname[], String iname[]){
		dv.addDataValue(header.addLast(name, type, pname, iname));
	}
	
	public void deleteValue(String name){
		dv.deleteDataValue(header.remove(header.whereValue(header, name)));
	}
	public Value getValue(){
		return header;
	}
	public DataValue getDv() {
		return dv;
	}
	public void setDv(DataValue dv) {
		this.dv = dv;
	}
}