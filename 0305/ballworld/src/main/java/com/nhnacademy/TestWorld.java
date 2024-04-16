package com.nhnacademy;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class TestWorld {
    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame();

        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        MovableWorld world = new MovableWorld();
        world.setSize(500, 400);
        
        frame.add(world);

        Color[] tmp = {Color.BLUE, Color.RED, Color.WHITE, Color.BLACK, Color.GREEN};
        try {
            for(int i=0; i<10; i++) {
                MovableBall tmpball = new MovableBall((int) (Math.random()*500)+10, (int) (Math.random()*400)+10, (int) (Math.random()*50)+10, tmp[(int)(Math.random()*5)]);
                world.add(tmpball);
            }
            
        }
        catch(IllegalArgumentException e) {
            
        }


        frame.setEnabled(true);
        frame.setVisible(true);

        world.run();
    }
}