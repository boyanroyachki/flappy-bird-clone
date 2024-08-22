package org.commodolink;

import javax.swing.*;

import  org.commodolink.constant.Constants;

public class CustomFrame extends JFrame {
    final int frameWidth = Constants.FRAME_WIDTH;
    final int frameHeight = Constants.FRAME_HEIGHT;
    final int birdWidth = Constants.BIRD_WIDTH;
    final int birdHeight = Constants.BIRD_HEIGHT;

    FlappyBird flappyBird = new FlappyBird(frameWidth, frameHeight, birdWidth, birdHeight);
    ImageIcon icon = new ImageIcon("src/main/resources/images/flappybird.png");

    public CustomFrame(String title) {
        setTitle(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(frameWidth, frameHeight);
        setLocationRelativeTo(null);
        setIconImage(icon.getImage());
        setResizable(false);

        add(flappyBird);
        pack();

        requestFocus();
        setVisible(true);
    }
}
