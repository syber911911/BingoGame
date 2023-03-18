package GAME;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;

public class Rank {
	private String[] rankList;

	public void runRank(String name, int count) {
		rankList = new String[10];
		Client client = new Client();
		String[] rank = client.runClient(name, Integer.toString(count));
		for(int i=0; i<rankList.length; i++) {
			if(Integer.parseInt(rank[i+10]) < 25565 )
			rankList[i] = "Rank: "+Integer.toString(i+1)+" "+ rank[i] + " Score: " + rank[i+10];
		}
		this.rankLabel();
	}
	
	public void rankLabel() {
		JFrame frame = new JFrame("Rank");
		frame.setLayout(new GridLayout(10,1));
		frame.setLocationRelativeTo(null);
		frame.setPreferredSize(new Dimension(300, 300));
		for(int i=0; i<10; i++) {
			if(rankList[i] != null) {
				JLabel label = new JLabel(rankList[i]);
				frame.add(label);
			}
		}
		
		frame.pack();
		frame.setVisible(true);
	}
}
