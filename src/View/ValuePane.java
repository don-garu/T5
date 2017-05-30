package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import Controller.MainController;

public class ValuePane extends JPanel{
	private JPanel valueListPane, btnPanel, temp;
	private JPanel valueList[]; 
	private JButton addBtn, delBtn, valueConfirm[];
	private JCheckBox single[],error[];
	private JTextField valueName[];
	private JTextField property[],ifProperty[];
	private JComboBox propertyCombo[],ifPropertyCombo[]; 
	private JScrollPane scrollPane;
	//설정 변수들 
	private int width = 200, height =700;// 크기 
	private int indexTemp =0;
	private int listSize =50; //리스트 크기
	private String tempPname[][];
	private String tempIname[][];
	private int pcount[],icount[];
	private int selIndex;
	private String selName;
	private String calculateValue="";
	//색상
	private Color bg= new Color(255,255,255);
	private Color occupied= new Color(29,52,69);
	private MainController mc;
	private ResultPane rp;
	
	public ValuePane(MainController mc,ResultPane rp) {
		this.mc = mc;
		this.rp = rp;
		//레이아웃 지정
		setLayout(new BorderLayout());
		valueListPane = new JPanel(new GridBagLayout());
		scrollPane = new JScrollPane(valueListPane);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weightx = 1;
		gbc.weighty = 1;
		temp = new JPanel();
		valueListPane.add(temp, gbc);
		add(scrollPane);//panel scroll만들
		
		//components
		addBtn = new JButton("+");
		delBtn = new JButton("-");
		valueConfirm = new JButton[listSize];
		valueList = new JPanel[listSize];
		valueName = new JTextField[listSize];
		single= new JCheckBox[listSize];
		error = new JCheckBox[listSize];
		property = new JTextField[listSize];
		ifProperty= new JTextField[listSize];
		propertyCombo = new JComboBox[listSize];
		ifPropertyCombo = new JComboBox[listSize];
		pcount=new int [listSize];
		icount=new int [listSize];
		btnPanel = new JPanel();
		tempPname = new String[listSize][5];
		tempIname = new String[listSize][5];
		
		//버튼 리스너 
		//add 버
		addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			
				//valuePanel추가
				addValuePanel(indexTemp);
				indexTemp++;
				validate();
				repaint();
			}
		});
		//delete button
		delBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//category삭
				mc.deleteValue(mc.getOngoingCategory(),selName);
				mc.printValueList(mc.getOngoingCategory());
				//페널삭제
				delValuePanel(selIndex);
				validate();
				repaint();
			}
		});
		//
		//패널 설정
		this.setBackground(bg);
		temp.setBackground(bg);
		scrollPane.setBackground(bg);
		valueListPane.setBackground(bg);
		btnPanel.setBackground(bg);
		//패널 구성요소 추가
		add(btnPanel, BorderLayout.SOUTH);
		btnPanel.add(addBtn, BorderLayout.SOUTH);
		btnPanel.add(delBtn, BorderLayout.SOUTH);
	}
	//value adddddddd
	public void addValuePanel(int valueIndex){
		pcount[valueIndex]=0;
		icount[valueIndex]=0;
		//tempPname = new String[5];
		//tempIname = new String[5];
		//value Panel 만들
		valueList[valueIndex] = new JPanel();
		valueList[valueIndex].setBackground(bg);
		valueName[valueIndex] = new JTextField("value name",10);
		single[valueIndex] = new JCheckBox("single");
		error[valueIndex] = new JCheckBox("error");
		property[valueIndex] = new JTextField("property",10);
		propertyCombo[valueIndex] = new JComboBox();
		ifProperty[valueIndex] = new JTextField("if property",10);
		ifPropertyCombo[valueIndex] = new JComboBox();
		valueConfirm[valueIndex] = new JButton("ADD");
		
		propertyCombo[valueIndex].setPreferredSize(new Dimension(50,20));
		ifPropertyCombo[valueIndex].setPreferredSize(new Dimension(50,20));
		
		valueList[valueIndex].add(valueName[valueIndex]);
		valueList[valueIndex].add(single[valueIndex]);
		valueList[valueIndex].add(error[valueIndex]);
		valueList[valueIndex].add(property[valueIndex]);
		valueList[valueIndex].add(propertyCombo[valueIndex]);
		valueList[valueIndex].add(ifProperty[valueIndex]);
		valueList[valueIndex].add(ifPropertyCombo[valueIndex]);
		valueList[valueIndex].add(valueConfirm[valueIndex]);
		
		valueConfirm[valueIndex].setVisible(true);
		valueList[valueIndex].setBorder((Border) new MatteBorder(0, 0, 1, 0, Color.GRAY));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		valueListPane.add(valueList[valueIndex], gbc, 0);
		
		//ACTION LISTENERS
		//value add
		valueConfirm[valueIndex].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String tempProp[] = new String[5];
				String vName = valueName[valueIndex].getText();
				boolean valid = true;
				if(single[valueIndex].isSelected()){
					tempProp[0] ="single";
					valid =false;
				}
				if(error[valueIndex].isSelected()){
					tempProp[1]="error";
					valid =false;
				}
				if(!((property[valueIndex].getText()).equals(""))){
					tempProp[2]="property";
					valid =false;
				}
				if(!((ifProperty[valueIndex].getText()).equals(""))){
					tempProp[3]="ifproperty";
					valid =false;
				}
				if(valid){
					tempProp[4]="valid";
				}
				//프로포티값 넣
				for(int i=0; i<tempPname.length;i++){
					//System.out.println(tempPname[i]);
					//tempPname[i]=property[valueIndex].getText();
					//tempIname[i]=ifProperty[valueIndex].getText();
				}
				mc.addValue(mc.getOngoingCategory(), vName,tempProp, tempPname[valueIndex],tempIname[valueIndex]);
				valueConfirm[valueIndex].setVisible(false);
				mc.printValueList(mc.getOngoingCategory());//벨류 출

				valueList[valueIndex].setBackground(occupied);//변신 
				calculateValue =mc.calculate();///  계산
				rp.setResultPane(calculateValue);/// 꼐
			}
		});
		//textField초기화  
		valueName[valueIndex].addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				selName= valueName[valueIndex].getText();//value index지정 
				selIndex =valueIndex;
			}
		});
		property[valueIndex].addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				property[valueIndex].setText("");
			}
		});
		ifProperty[valueIndex].addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				ifProperty[valueIndex].setText("");
			}
		});
		//textField 
		property[valueIndex].addKeyListener(new KeyListener(){
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){//
					String s= property[valueIndex].getText();
					if(pcount[valueIndex]<5){
						tempPname[valueIndex][pcount[valueIndex]] = s;
						propertyCombo[valueIndex].addItem(s);
						pcount[valueIndex]++;
					}else{
						//예
					}
					//System.out.println(s);
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
			
		});
		ifProperty[valueIndex].addKeyListener(new KeyListener(){
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){//
					String s= ifProperty[valueIndex].getText();
					if(icount[valueIndex]<5){
						tempIname[valueIndex][icount[valueIndex]] = s;
						ifPropertyCombo[valueIndex].addItem(s);
						icount[valueIndex]++;
					}else{
						//예
					}
					//System.out.println(s);
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
			
		});
	}
	//벨류 삭제 
	public void delValuePanel(int valueIndex){
		valueListPane.remove(valueList[valueIndex]);
		calculateValue =mc.calculate();///  계산
		rp.setResultPane(calculateValue);/// 계산 
	}
	
	//value panel 초기화 
	public void refreshPane(){
		int size=0;
		valueListPane.removeAll();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weightx = 1;
		gbc.weighty = 1;
		valueListPane.add(temp, gbc);
		// 불러오기
		//mc.printValueList(mc.getOngoingCategory());
		//System.out.println(mc.getValueSize(mc.getOngoingCategory()));
		size=mc.getValueSize(mc.getOngoingCategory());
		for(int i=0; i<size; i++){
			addValuePanel(i);
			valueList[i].setBackground(occupied);
			valueName[i].setText("VALUE INSERTED");
			valueConfirm[i].setVisible(false);
		}
		validate();
		repaint();
	}
}
