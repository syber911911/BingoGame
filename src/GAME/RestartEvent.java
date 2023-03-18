package GAME;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class RestartEvent implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 자동 생성된 메소드 스텁
		for(int i = 0; i<16; i++) {
			ButtonsEvent.getButtons()[i].setIcon(MyButton.SetImageIcon(ButtonsEvent.questionImage[0])); //Bttuons의 이미지를 question으로 다시 지정.
		}
		ButtonsEvent.mixButton(); //이미지들을 다시 섞음.
		ButtonsEvent.setOpenCount(0);
		ButtonsEvent.setSuccessCount(0);
		ButtonsEvent.setTryCount(0);
		ButtonsEvent.setButtonIndexFirstSave(0);
		ButtonsEvent.setButtonIndexSecondSave(0);
		ButtonsEvent.EndFrame.setVisible(false); //EndFrame을 보이지 않도록 함.
		MainFrame.getMessage().setText(" Try Count : 0"); //Message를 초기화.
		MainFrame.getUserName().setText("User Name: "); //UserName TextFeild를 초기화.
	}
}
