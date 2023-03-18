package GAME;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MyButton extends JButton {
	public static ImageIcon SetImageIcon(String filename) { //이미지를 입력 받아 해당 이미지의 사이즈를 재지정하는 메소드.
		ImageIcon icon = new ImageIcon(filename); //filename에 해당하는 파일로 ImageIcon객체 생성.
		Image changedIcon = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH); //icon에서 이미지를 로딩하여 100*100 사이즈로 지정(SCALE_SMOOTH는 해상도 유지를 위함.) changedicon에 저장.
		ImageIcon icon_new = new ImageIcon(changedIcon); //크기가 조정된 이미지를 다시 ImageIcon으로 만듬.
		return icon_new;
	}
}
