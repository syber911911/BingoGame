package GAME;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainFrame extends JFrame{ //게임 시작 시 보이는 첫 화면 
	
	private static JLabel Message = new JLabel(" Try Count:  0 ");
	private static JTextField UserName;
	private UserNameEvent Name;
	private MyButton ExitButton;
	private ExitEvent Exit;
	private JPanel NorthPanel;
	private JPanel MainPanel;
	
	public MainFrame(String title) {
		this.setTitle(title); //Frame의 Title지정 
		this.setSize(400,500); //Frame Size 지정.
		this.setVisible(true); //Frame을 화면에 표현 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Frame의 EXIT 버튼을 눌렀을 때 동작 지정 
		this.setLocationRelativeTo(null); //Frame이 화면 중앙에 표현 
		this.setLayout(new BorderLayout());//Frame의 Layout을 BorderLayout으로 지정.
	}
	
	public void MakeUI(MainFrame Frame) { //Main Frame의 UI을 만들어주는 메소드 
		
		UserName = new JTextField("User Name: ", 15); //UserName을 기록하기 위한 JTextField를 생성.
		getUserName().setBackground(new Color(159, 97, 49)); //JTextField의 배경색을 지정.
		getUserName().setForeground(Color.WHITE); //JTextField의 글자색을 지정.
		getUserName().setBorder(null); //JTextField의 테두리 제거.
		Name = new UserNameEvent(); //UserNameEvent 객체 생성.
		getUserName().addActionListener(Name); //ActionListener UserNameEvent를 UserName에 추가.
		
		ExitButton = new MyButton(); //Exit 버튼 생성. 
		ExitButton.setText("Exit"); //Exit 버튼의 Text지정 .
		Exit = new ExitEvent(); //Exit 동작을 위한 ExitEvent 객체 생성.
		ExitButton.addActionListener(Exit); //ActionListener ExitEvent를 ExitButton에 추가.
		
		NorthPanel = new JPanel(); //Frame에 최상단을 표현하기 위한 NorthPanel.
		NorthPanel.setLayout(new BorderLayout()); //North Panel의 Layout 설정.
		NorthPanel.setPreferredSize(new Dimension(400, 100)); //NorthPanel의 Size를 지정.
		NorthPanel.setBackground(new Color(159, 97, 49)); //NorthPanel의 배경색을 지정.
		
		Message.setPreferredSize(new Dimension(400, 100)); //필드인 JLable Message의 사이즈를 지정.
		Message.setForeground(Color.WHITE); //Message의 색을 지정.
		Message.setHorizontalAlignment(JLabel.CENTER); //Message가 왼쪽부터 출력되는 것을 중앙으로 변경.
		
		NorthPanel.add(Message, BorderLayout.CENTER); //Message를 NorthPanel의 중앙에 추가.
		NorthPanel.add(ExitButton, BorderLayout.SOUTH); //ExitButton을 NorthPanel의 아래쪽에 추가.
		NorthPanel.add(getUserName(), BorderLayout.EAST); //UserName을 NorthPanel의 오른쪽에 추가.
		Frame.add(NorthPanel , BorderLayout.NORTH);      //Frame의 상단에 NorthPanel을 추가.
		
		
		MainPanel = new JPanel(); //MainPanel을 생성.
		MainPanel.setLayout(new GridLayout(4,4)); //MainPanel의 Layout을 4*4 GridLayout으로 설정.
		MainPanel.setPreferredSize(new Dimension(400,400)); //MainPanel의 Size를 지정.
		
		ButtonsEvent ButtonsAction = new ButtonsEvent(16); //ButtonsEvent 객체와 ButtonsEvent 생성자를 통해 MyButton객체를 저장 할 수 있는 16칸 배열 생성.
		ButtonsEvent.mixButton(); //ButtonsEvent의 메소드 mixButton을 이용해 Buttons의 그림을 mix.
		
		for(int i = 0; i < 16; i++) { //생성된 16개의 버튼에 questionImage 지정을 위한 반복문.
			ButtonsEvent.getButtons()[i] = new MyButton(); //ButtonsEvent의 필드인 MyButton을 저장하는 배열에서 한개씩 MyButton을 생성.
			ButtonsEvent.getButtons()[i].setPreferredSize(new Dimension(100,100)); //생성된 Buttons들의 Size를 지정.
			ButtonsEvent.getButtons()[i].setIcon(ButtonsEvent.getButtons()[i].SetImageIcon(ButtonsEvent.questionImage[0])); //MyButton의 메소드 SetImagIcon을 통해 question 이미지를 Button의 사이즈에 맞춰 변환 후 지정.
			ButtonsEvent.getButtons()[i].addActionListener(ButtonsAction); //각 Buttons들에 ActionListener인 ButtonsAction 추가.
			MainPanel.add(ButtonsEvent.getButtons()[i]); //MainPanel에 Buttons를 추가.
		}
		Frame.add(MainPanel, BorderLayout.CENTER); //MainPanel을 Frame 중앙에 추가.
		Frame.pack();
	}
	public static JLabel getMessage() { //필드인 Message를 반환하기 위한 메소드.
		return Message;
	}

	public static JTextField getUserName() {
		return UserName;
	}
	
}
