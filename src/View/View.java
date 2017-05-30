package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import Controller.MainController;

public class View{

	private Color borderCr = new Color(87,171,150);
	private int border = 15;
	private MainController mc;
	CategoryPane categoryPane;
	ValuePane valuePane;
    ResultPane resultPane;
	
	public View(){
		this.mc = new MainController();
		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception ex) {
                }
                
                JFrame frame = new JFrame("Test");
                resultPane = new ResultPane();
                valuePane = new ValuePane(mc,resultPane);
                categoryPane = new CategoryPane(mc,valuePane,resultPane);
                
                
                categoryPane.setBorder((Border) new MatteBorder(border, border, border, border,borderCr));
                valuePane.setBorder((Border) new MatteBorder(border, border, border, border,borderCr));
                resultPane.setBorder((Border) new MatteBorder((border/2), border, border, border,borderCr));
              
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(categoryPane,BorderLayout.WEST);
                frame.add(valuePane,BorderLayout.CENTER);
                frame.add(resultPane,BorderLayout.SOUTH);
                frame.setSize(1000, 700);
                //frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                
                //action manager
                
                
            }
        });
	}
	public static void main(String[] args){
		new View();
	}
	
}