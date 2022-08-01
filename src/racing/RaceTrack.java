package racing;

 

import java.awt.Color;

import java.awt.Font;

import java.awt.Graphics;

import java.awt.Image;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.util.Random;

 

import javax.swing.ImageIcon;

import javax.swing.JButton;

import javax.swing.JPanel;

 

public class RaceTrack extends JPanel implements ActionListener {

    Random ran = new Random();

 

    final int HORSE_COUNT = 4;

    final int GOAL_LINE = 1300;

    Horse[] horse = new Horse[HORSE_COUNT];

    

    JButton startBtn;

    JButton resetBtn;

    

    int timer;

    int rank;

    

    public RaceTrack() {

        setLayout(null);

        setBackground(Color.WHITE);

        

        Font font = new Font("", Font.BOLD, 25);

        

        startBtn = new JButton("시작");

        startBtn.setBackground(Color.CYAN);

        startBtn.setFont(font);

        startBtn.setBounds(600, 600, 150, 80);

        startBtn.addActionListener(this);

        add(startBtn);

        

        resetBtn = new JButton("리셋");

        resetBtn.setBackground(Color.CYAN);

        resetBtn.setFont(font);

        resetBtn.setBounds(800, 600, 150, 80);

        resetBtn.addActionListener(this);

        add(resetBtn);

        

        setHorses();

    }

    

    public void setHorses() {

        rank = 0;

        for(int i=0; i<HORSE_COUNT;i++) {

            horse[i] = new Horse();

            horse[i].width = 120;                                       
            horse[i].height = 90;                                       

            horse[i].x = 100;                                        
            horse[i].y = 100+i*100;                                      

            horse[i].number = i+1;

            horse[i].speed = ran.nextInt(5)+1;

            horse[i].state = Horse.WAIT;

            horse[i].fileName = String.format("image/horse.png", i+1);

            Image image = new ImageIcon(horse[i].fileName).getImage().getScaledInstance(horse[i].width, horse[i].height, Image.SCALE_SMOOTH);

            horse[i].horseImage = new ImageIcon(image);

        }

    }

    public void update() {

        timer += 1;

        if(timer % 50 == 0) {

            for(int i=0; i<HORSE_COUNT; i++) {

                horse[i].speed = ran.nextInt(5)+1;

            }

        }

        

        for(int i=0; i<HORSE_COUNT; i++) {

            if(horse[i].state == Horse.RUN) {

                horse[i].x += horse[i].speed;

            }

            

            if(horse[i].x >= GOAL_LINE && horse[i].state == Horse.RUN) {

                horse[i].rank = ++rank;

                horse[i].x = GOAL_LINE;

                horse[i].speed = 0;

                horse[i].state = Horse.GOAL;

            }

        }

    }

    

    public void render(Graphics g) {

        Font font = new Font("", Font.BOLD, 50);

        g.setFont(font);

        

        for(int i=0; i<HORSE_COUNT; i++) {

        	g.drawImage(horse[i].horseImage.getImage(), horse[i].x, horse[i].y, null);

            if(horse[i].state == Horse.GOAL) {

                g.drawString(horse[i].rank+"",1450, horse[i].y+60);

            }

        }

        g.drawLine(GOAL_LINE+120, 100, GOAL_LINE+120, 580);

    }

 

    @Override

    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        try {

            Thread.sleep(10);

            repaint();

        } catch (Exception e) {}

        update();

        render(g);

    }

    

    @Override

    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == startBtn) {

            for(int i=0; i<HORSE_COUNT; i++) {

                horse[i].state = Horse.RUN;

            }

        }

        if(e.getSource() == resetBtn) {

            for(int i=0; i<HORSE_COUNT; i++) {

                setHorses();

            }

        }

    }

}

 