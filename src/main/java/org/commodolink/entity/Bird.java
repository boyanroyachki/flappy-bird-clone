package org.commodolink.entity;

import java.awt.*;

public class Bird {
    public int x;
    public int y;
    public int width;
    public int height;
    public Image img;

    public Bird(int height, int width, int y, int x, Image img) {
        this.height = height;
        this.width = width;
        this.y = y;
        this.x = x;
        this.img = img;
    }
}
