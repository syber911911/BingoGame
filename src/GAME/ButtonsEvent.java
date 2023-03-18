package GAME;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.util.Timer;
import java.util.TimerTask;

public class ButtonsEvent implements ActionListener {
	
	public static JFrame EndFrame;
	private static MyButton[] Buttons;
	private static JLabel EndMessage;
	
	private static int index; // Button�쓽 index �몴�쁽. 0 - 15
	private static int openCount = 0; //�쁽�옱源뚯� �늻瑜� Button �닔.  0 - 2
	private static int ButtonIndexFirstSave = 0; // 泥섏쓬 �늻瑜� Button�쓽 index. 0 - 15
	private  static int ButtonIndexSecondSave = 0; //�몢踰덉㎏ �늻瑜� Button�쓽 index. 0 - 15
	private static int tryCount = 0;	//紐⑤뱺 吏앹쓣 留욎텛湲� �쐞�빐 �떆�룄�븳 �슏�닔.
	private static int SuccessCount = 0; // 吏앹씠 留욎떠吏� Button�쓽 媛��닔. 0 - 8
	
	public static String[] questionImage = {"question.png"};
	
	public static String[] images = {
			"icon1.png", "icon2.png", "icon3.png", "icon4.png",
			"icon5.png", "icon6.png", "icon7.png", "icon8.png",
			"icon1.png", "icon2.png", "icon3.png", "icon4.png",
			"icon5.png", "icon6.png", "icon7.png", "icon8.png"
	};
	
	public ButtonsEvent(int ButtonNumber) {
		// TODO �옄�룞 �깮�꽦�맂 �깮�꽦�옄 �뒪�뀅
		setButtons(new MyButton[ButtonNumber]); //ButtonNumber留뚰겮�쓽 MyButton�쓣 ���옣�븯�뒗 諛곗뿴 �깮�꽦.
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO �옄�룞 �깮�꽦�맂 硫붿냼�뱶 �뒪�뀅
		if(getOpenCount() >= 2) { //�쁽�옱 OpenCount媛� 2硫� �뜑�씠�긽 Button�씠 �닃由ъ� �븡�룄濡� �븿.
			return;
		}
		MyButton Clickedbtn = (MyButton)e.getSource(); //諛쒖깮�맂 Event�쓽 Source瑜� 諛쏆븘 MyButton�쑝濡� 罹먯뒪�똿�븯�뿬 Clickedbtn�뿉 ���옣.
		int index = ClickedbtnIndex( Clickedbtn ); //Clickedbtn�쓽 index瑜� ���옣.
		
		Clickedbtn.setIcon(MyButton.SetImageIcon(images[index])); //�겢由��맂 Button Clickedbtn�뿉  index�뿉 �빐�떦�븯�뒗 image瑜� 吏��젙.
		
		setOpenCount(getOpenCount() + 1); //OpenCount瑜� 1 利앷�.
		
		if(getOpenCount() == 1) {
			setButtonIndexFirstSave(index); //OpenCount媛� 1�씠硫� 泥ル쾲吏� �겢由��맂 Button index瑜� ���옣.
		}
		else if(getOpenCount() == 2) { 
			setButtonIndexSecondSave(index); //OpenCount媛� 2�씠硫� �몢踰덉㎏ �겢由��맂 Button index瑜� ���옣.
			setTryCount(getTryCount() + 1); //TryCount瑜� 1利앷�.
			MainFrame.getMessage().setText("Try Count:  " + getTryCount()); //MainFrame�쓽 �븘�뱶 Message�쓽 �궡�슜�쓣 蹂�寃�. "Try Count: TryCount 媛�"
			
			boolean Bingo = CheckButton(getButtonIndexFirstSave(), getButtonIndexSecondSave()); //CheckButton 硫붿냼�뱶瑜� �넻�빐 泥ル쾲吏� Button index�쓽 �씠誘몄��� �몢踰덉㎏ Button �씠誘몄�媛� 媛숈�吏� 鍮꾧탳�븯怨� boolean媛믪쑝濡� ���옣.
			if(Bingo == true) { //留뚯빟 �몢 Button�쓽 �씠誘몄�媛� 媛숇떎硫�.
				setOpenCount(0); //OpenCount瑜� 0�쑝濡� 蹂�寃�.
				setSuccessCount(getSuccessCount() + 1); //SuccessCount瑜� 1利앷�.
				if(getSuccessCount() == 8) { //SuccessCount媛� 8�씠硫�.
					EndFrame = new JFrame(); //EndFrame �깮�꽦.
					EndFrame.setLayout(new BorderLayout()); //EndFrame Layout 吏��젙.
					EndFrame.setPreferredSize(new Dimension(400, 100)); //EndFrame Size.
					EndFrame.setLocationRelativeTo(null); //EndFrame�쓣 �솕硫� 以묒븰�뿉 �몴�쁽.
					
					JPanel EndNorth = new JPanel();  //EndNorth Panel�쓣 �깮�꽦.
					JPanel EndSouth = new JPanel(); //EndSouth Panel�쓣 �깮�꽦.
					EndMessage = new JLabel("Success " + UserNameEvent.getUserName() +" Score: " + getTryCount()); //EndMessage Label�뿉 �빐�떦 臾몄옄�뿴�쓣 ���옣.
					Rank rank = new Rank();
					rank.runRank(UserNameEvent.getName(), getTryCount());
					EndMessage.setForeground(Color.RED); //EndMessage�쓽 �깋�쓣 吏��젙.
					EndNorth.add(EndMessage); //EndNorth�뿉 EndMessage 異붽�.
					EndFrame.add(EndNorth, BorderLayout.NORTH); //EndFrame�쓽 �긽�떒�뿉 EndNorth 異붽�.
					MyButton RestartButton = new MyButton(); //RestartButton �깮�꽦.
					MyButton ExitButton = new MyButton(); //ExitButton �깮�꽦.
					RestartEvent Restart = new RestartEvent(); //RestartEvent �깮�꽦.
					ExitEvent Exit = new ExitEvent(); //ExitEvent �깮�꽦.
					RestartButton.setText("RESTART"); //RestartButton�뿉 text �꽕�젙.
					ExitButton.setText("EXIT"); //ExitButton�뿉 text �꽕�젙.
					RestartButton.addActionListener(Restart); //RestartButton�뿉 ActionListener�쓣 異붽�.
					ExitButton.addActionListener(Exit); //ExitButton�뿉 ActionListener�쓣 異붽�.
					EndSouth.add(RestartButton, BorderLayout.WEST); //EndSouth Panel�뿉 RestartButton �쇊履쎌뿉 異붽�.
					EndSouth.add(ExitButton, BorderLayout.EAST); //EndSouth Panel�뿉 ExitButton �삤瑜몄そ�뿉 異붽�.
					EndFrame.add(EndSouth, BorderLayout.SOUTH); //EndFrame�뿉 EndSouth Panel 異붽�.
					EndFrame.setVisible(true);
					EndFrame.pack();
					setOpenCount(3); //OpenCount瑜� 3�쑝濡�.  Button�씠 �뜑�씠�긽 �븞�닃由щ룄濡� �꽕�젙.
				}
			}
			else {
				backCard(); //�닃由� Button�쓽 �씠誘몄�瑜� question �씠誘몄�濡� 蹂�寃�.
			}
	}
}
	public static void backCard() { //吏앹씠 留욎� �븡�뒗 Button�쓣 �떎�떆 �릺�룎由ш린 �쐞�븳 硫붿냼�뱶.
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				// TODO �옄�룞 �깮�꽦�맂 硫붿냼�뱶 �뒪�뀅
				setOpenCount(0); //OpenCount瑜� �떎�떆 0�쑝濡� �룎由�.
				getButtons()[getButtonIndexFirstSave()].setIcon(MyButton.SetImageIcon(questionImage[0])); //泥ル쾲吏� �늻瑜� Button�쓽 index�뿉 �엳�뒗 Button�쓽 Icon�쓣 question�쑝濡� 蹂�寃�.
				getButtons()[getButtonIndexSecondSave()].setIcon(MyButton.SetImageIcon(questionImage[0])); //�몢踰덉㎏ �늻瑜� Button�쓽 index�뿉 �엳�뒗 Button�쓽 Icon�쓣 question�쑝濡� 蹂�寃�.
				timer.cancel();
		}	
	};
	timer.schedule(task, 1000); //Task�뿉寃� 1珥덉쓽 �뵜�젅�씠瑜� 以�.
}
	
	public static boolean CheckButton(int Clickedbtn1 , int Clickedbtn2) { // 泥ル쾲吏� �꽑�깮�맂 Button怨� �몢踰덉㎏ �꽑�깮�맂 Button�쓽 index瑜� 鍮꾧탳�븯�뒗 硫붿냼�뱶.
		if(Clickedbtn1 == Clickedbtn2) {
			return false;
		}
		if(images[Clickedbtn1].equals(images[Clickedbtn2])) { //泥ル쾲吏� �꽑�깮�맂 Button怨� �몢踰덉㎏ �꽑�깮�맂 Button�쓽 image媛� �룞�씪.
			return true;
		}
		else {
			return false;
		}
	}
	
	public static void mixButton() {
		Random rand = new Random();
		for(int i = 0; i<1000; i++) { //images �궡遺��쓽 String �뱾�쓣 1000踰� �꽎�뒗�떎.
			int random = rand.nextInt(15) + 1; //1-15
			String temp = images[0]; //images[0] 怨� images[random]怨� �쐞移섎�� 蹂�寃�.
			images[0] = images[random];
			images[random] = temp;
		}
	}
		
	public int ClickedbtnIndex(MyButton Clickedbtn) { //�겢由��븳 Button怨� Buttons 以� �씪移섑븯�뒗 Button�쓣 李얘퀬 �빐�떦 index瑜� 諛섑솚�븯�뒗 硫붿냼�뱶.
		index = 0;
		for(int i = 0; i<16; i++) {
			if(getButtons()[i] == Clickedbtn) { //�겢由��븳 Button怨� Buttons[i]媛� �씪移섑븯�뒗媛�?
					index = i; 
			}
		}
		return index;
	}

	public static MyButton[] getButtons() {
		return Buttons;
	}

	public static void setButtons(MyButton[] buttons) {
		Buttons = buttons;
	}

	public static int getSuccessCount() {
		return SuccessCount;
	}

	public static void setSuccessCount(int successCount) {
		SuccessCount = successCount;
	}

	public static int getOpenCount() {
		return openCount;
	}

	public static void setOpenCount(int openCount) {
		ButtonsEvent.openCount = openCount;
	}

	public static int getButtonIndexFirstSave() {
		return ButtonIndexFirstSave;
	}

	public static void setButtonIndexFirstSave(int buttonIndexFirstSave) {
		ButtonIndexFirstSave = buttonIndexFirstSave;
	}

	public static int getButtonIndexSecondSave() {
		return ButtonIndexSecondSave;
	}

	public static void setButtonIndexSecondSave(int buttonIndexSecondSave) {
		ButtonIndexSecondSave = buttonIndexSecondSave;
	}

	public static int getTryCount() {
		return tryCount;
	}

	public static void setTryCount(int tryCount) {
		ButtonsEvent.tryCount = tryCount;
	}

	public static JLabel getEndMessage() {
		return EndMessage;
	}

	public static void setEndMessage(JLabel endMessage) {
		EndMessage = endMessage;
	}
	

}
