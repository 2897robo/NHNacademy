package com.nhnacademy;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CannonGame extends JFrame implements ComponentListener {
    static final int FRAME_WIDTH = 1500;
    static final int FRAME_HEIGHT = 700;
    static final int MIN_RADIUS = 10;
    static final int MAX_RADIUS = 50;
    static final int MIN_WIDTH = 10;
    static final int MAX_WIDTH = 50;
    static final int MIN_HEIGHT = 10;
    static final int MAX_HEIGHT = 50;
    static final int FIXED_BALL_COUNT = 0;
    static final int FIXED_BOX_COUNT = 3;
    static final int BOUNDED_BALL_COUNT = 5;
    static final int MIN_DELTA = 5;
    static final int MAX_DELTA = 7;
    static final int MAX_MOVE_COUNT = 0;
    static final int DT = 50;
    static final int BLOCK_WIDTH = 80;
    static final Color[] COLOR_TABLE = {
            Color.BLACK,
            Color.RED,
            Color.BLUE,
            Color.YELLOW
    };

    Logger logger = LogManager.getLogger();

    CannonWorld world;

    public CannonGame() {
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addComponentListener(this);

        setLayout(null);

        world = new CannonWorld(300, 0, FRAME_WIDTH - 300, FRAME_HEIGHT - 200);
        world.setDT(DT);
        world.setBackground(Color.WHITE);
        add(world);

        JButton fire_button = new JButton("Fire!");
        JButton clear_button = new JButton("Clear");

        fire_button.setBounds(50, 500, 100, 50);
        clear_button.setBounds(180, 500, 100, 50);

        fire_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.start();
            }
        });
        clear_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.clear();
            }
        });

        add(fire_button);
        add(clear_button);

        JSlider windSpeed = new JSlider(-10, 10, 0);
        JSlider gravity_slider = new JSlider(0, 10, 0);
        JSlider degree_slider = new JSlider(0, 90, 45);
        JSlider speed_slider = new JSlider(0, 1000, 500);

        windSpeed.setBounds(50, 380, 200, 100);
        gravity_slider.setBounds(50, 280, 200, 100);
        degree_slider.setBounds(50, 180, 200, 100);
        speed_slider.setBounds(50, 80, 200, 100);

        windSpeed.setPaintTrack(true);
        windSpeed.setPaintTicks(true);
        windSpeed.setPaintLabels(true);
        gravity_slider.setPaintTrack(true);
        gravity_slider.setPaintTicks(true);
        gravity_slider.setPaintLabels(true);
        degree_slider.setPaintTrack(true);
        degree_slider.setPaintTicks(true);
        degree_slider.setPaintLabels(true);
        speed_slider.setPaintTrack(true);
        speed_slider.setPaintTicks(true);
        speed_slider.setPaintLabels(true);

        windSpeed.setMajorTickSpacing(2);
        windSpeed.setMinorTickSpacing(2);
        gravity_slider.setMajorTickSpacing(2);
        gravity_slider.setMinorTickSpacing(2);
        degree_slider.setMajorTickSpacing(20);
        degree_slider.setMinorTickSpacing(5);
        speed_slider.setMajorTickSpacing(200);
        speed_slider.setMinorTickSpacing(50);

        windSpeed.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                world.setWindSpeed(windSpeed.getValue());
            }
        });

        gravity_slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                world.setGravity(gravity_slider.getValue());
            }
        });

        degree_slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                //world.setDegree(degree_slider.getValue());
            }
        });

        speed_slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                world.setSpeed(speed_slider.getValue());
            }
        });

        add(windSpeed);
        add(gravity_slider);
        add(degree_slider);
        add(speed_slider);

        JLabel wind_text = new JLabel("바람");
        JLabel gravity_text = new JLabel("중력");
        JLabel degree_text = new JLabel("각도");
        JLabel speed_text = new JLabel("속도");

        wind_text.setBounds(60, 360, 50, 50);
        gravity_text.setBounds(60, 260, 50, 50);
        degree_text.setBounds(60, 160, 50, 50);
        speed_text.setBounds(60, 60, 50, 50);

        add(wind_text);
        add(gravity_text);
        add(degree_text);
        add(speed_text);
    }

    public void start() {
        setVisible(true);
        setEnabled(true);

        world.run();
    }

    public static void main(String[] args) {
        CannonGame frame = new CannonGame();

        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);

        frame.start();
    }

    public void componentHidden(ComponentEvent event) {
        //
    }

    @Override
    public void componentMoved(ComponentEvent event) {
        //
    }

    @Override
    public void componentResized(ComponentEvent event) {
        if (getWidth() % BLOCK_WIDTH != 0) {
            setSize(getWidth() / BLOCK_WIDTH * BLOCK_WIDTH, getHeight());
        }
    }

    @Override
    public void componentShown(ComponentEvent event) {
        //
    }
}
