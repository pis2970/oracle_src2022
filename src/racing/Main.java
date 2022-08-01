package racing;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		final int WIDTH=1600;
		final int HEIGHT=800;
		
		JFrame frame=new JFrame();
		frame.setTitle("경마게임");
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		
		int x=(int)(screenSize.getWidth()/2-WIDTH/2);
		int y=(int)(screenSize.getHeight()/2-HEIGHT/2);
		frame.setBounds(x, y, WIDTH, HEIGHT);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		RaceTrack race=new RaceTrack();
		frame.setContentPane(race);
		frame.revalidate();
		
	}

}
