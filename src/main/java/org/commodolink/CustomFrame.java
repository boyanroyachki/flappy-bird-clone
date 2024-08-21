package org.commodolink;

import javax.swing.*;

import  org.commodolink.constant.Constants;

import java.awt.*;

public class CustomFrame extends JFrame {

    final int frameWidth = Constants.FRAME_WIDTH;
    final int frameHeight = Constants.FRAME_HEIGHT;
    ImageIcon icon = new ImageIcon("src/main/resources/images/flappybird.png");

    public CustomFrame(String title) {
        setTitle(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(frameWidth, frameHeight);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setIconImage(icon.getImage());
    }
}
