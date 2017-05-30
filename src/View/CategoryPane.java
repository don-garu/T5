package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import Controller.MainController;

public class CategoryPane extends JPanel{
	private JPanel categoryListPane, btnPanel, temp;
	private JPanel categoryList[]; 
	private JButton addBtn, delBtn;
	private JTextField categoryName[];
	private JScrollPane scrollPane;
	//설정 변수들 
	private int width = 200, height =700;// 크기 
	private int categoryIndex =0, selected=0;// index,현재 고른 index
	private int listSize =50; //리스트 크기 
	private String tempName, selName;// 임시저장 이
	private String calculateValue;
	//색상
	private Color bg= new Color(29,52,69);
	
	private MainController mc;
	private ValuePane vp;
	private ResultPane rp;
	public CategoryPane(MainController mc,ValuePane vp,ResultPane rp) {
		this.mc = mc;
		this.vp = vp;
		this.rp = rp;
		//레이아웃 지정
		setLayout(new BorderLayout());
		categoryListPane = new JPanel(new GridBagLayout());
		scrollPane = new JScrollPane(categoryListPane);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weightx = 1;
		gbc.weighty = 1;
		temp = new JPanel();
		categoryListPane.add(temp, gbc);
		add(scrollPane);//panel scroll만들
		
		//components
		addBtn = new JButton("+");
		delBtn = new JButton("-");
		categoryList = new JPanel[listSize];
		categoryName = new JTextField[listSize];
		btnPanel = new JPanel();
		
		//버튼 리스너 
		addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//category 추가& index 받기
				mc.addCategory(null);
				categoryIndex = mc.getCategoryIndex(null, categoryIndex);
				tempName ="Category"+categoryIndex; // category 이름 만들기;
				mc.editCategory(null,tempName);// 이름 수정
				mc.printList();
				//category panel 지정 index에 추가
				addCategoryPanel(categoryIndex,tempName);
				//repaint
				validate();
				repaint();
			}
		});
		delBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//category 삭제
				mc.deleteCategory(selName);
				mc.printList();
				//panel 삭제
				delCategoryPanel(selected);

				calculateValue =mc.calculate();///  계산
				rp.setResultPane(calculateValue);/// 꼐
				//repaint
				validate();
				repaint();
			}
		});
		
		//패널 설정
		this.setBackground(bg);
		temp.setBackground(bg);
		scrollPane.setBackground(bg);
		categoryListPane.setBackground(bg);
		btnPanel.setBackground(bg);
		//패널 구성요소 추가
		add(btnPanel, BorderLayout.SOUTH);
		btnPanel.add(addBtn, BorderLayout.SOUTH);
		btnPanel.add(delBtn, BorderLayout.SOUTH);
	}
	public void addCategoryPanel(int categoryIndex, String name){
		//name = "category"+categoryIndex;
		//새로운 패널 추가 
		//System.out.println(categoryIndex);
		categoryList[categoryIndex] = new JPanel();
		categoryList[categoryIndex].setBackground(bg);
		categoryName[categoryIndex] = new JTextField(name,10);
		categoryName[categoryIndex].setBackground(bg);
		categoryName[categoryIndex].setForeground(Color.white);;
		categoryList[categoryIndex].add(categoryName[categoryIndex]);
		categoryList[categoryIndex].setBorder((Border) new MatteBorder(0, 0, 0, 0,bg));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		categoryListPane.add(categoryList[categoryIndex], gbc, 0);
		
		//name textField action listener
		categoryName[categoryIndex].addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseReleased(MouseEvent e) {// 카테고리 선택
				makeColorNormal();
		    	makeSelectColor(categoryIndex);
		    	selected = categoryIndex; //선택된인뎃
		    	selName = categoryName[categoryIndex].getText();//선택된 이
		    	mc.setOngoingCategory(selName);
		    	System.out.println("se: "+selName);
		    	vp.refreshPane();//valuePanel 초기
		    }
		    //public void mousePressed(MouseEvent e){
		    //	System.out.println(categoryName[categoryIndex].getText());
		    //	mc.editCategory(selName, categoryName[categoryIndex].getText());
		    //}
		});
		categoryName[categoryIndex].addKeyListener(new KeyListener(){
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){//
					//System.out.println(categoryName[categoryIndex].getText());
				   mc.editCategory(selName, categoryName[categoryIndex].getText());
				   mc.printList();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
			
		});
		
		

	}
	public void delCategoryPanel(int categoryIndex){
		categoryListPane.remove(categoryList[categoryIndex]);
		
	}
	public void makeColorNormal(){
		for(int i=0; i<=categoryIndex; i++){
			categoryList[i].setBackground(bg);
			categoryName[i].setBackground(bg);
			categoryName[i].setForeground(Color.white);
		}
	}
	public void makeSelectColor(int index){
		categoryList[index].setBackground(Color.white);
		categoryName[index].setBackground(Color.white);
		categoryName[index].setForeground(bg);
	}
}
