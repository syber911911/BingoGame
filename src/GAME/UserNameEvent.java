package GAME;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Spliterator;

import javax.swing.JTextField;

public class UserNameEvent implements ActionListener {
	private static String userName;
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO �옄�룞 �깮�꽦�맂 硫붿냼�뱶 �뒪�뀅
		this.userName = ((JTextField) e.getSource()).getText(); 
		System.out.print(userName);
	}
	public static String getUserName() {
		return userName;
	}
	public static String getName() {
		String namelist[] = userName.split(" ");
		return namelist[2];
	}
	public static void resetUserName() {
		userName = null;
	}

}

