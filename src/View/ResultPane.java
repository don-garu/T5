package View;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ResultPane extends JPanel{
	private JPanel resultPanel;
	private JLabel resultLabel, printLabel;
	//색상
	private Color bg= new Color(255,255,255);
	private Color resultC = new Color(126,135,191);
	public ResultPane(){

		setLayout(new BorderLayout());
		
		resultPanel = new JPanel();
		resultLabel = new JLabel("");
		printLabel = new JLabel("RESULT");
		resultLabel.setFont(resultLabel.getFont().deriveFont(60.0f));
		printLabel.setFont(printLabel.getFont().deriveFont(40.0f));
		resultLabel.setForeground(resultC);
		
		setBackground(bg);
		resultPanel.setBackground(bg);
		
		resultPanel.add(resultLabel);
		resultPanel.add(printLabel);
		
		add(resultPanel,BorderLayout.EAST);
	}
	public void setResultPane(String result){
		resultLabel.setText(result);
		printLabel.setText("CASES");
	}
}
