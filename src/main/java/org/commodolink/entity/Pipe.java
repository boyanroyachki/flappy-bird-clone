package org.commodolink.entity;

import java.awt.*;

public class Pipe {
    public int x;
    public int y;
    public int width;
    public int height;
    public Image img;
    public boolean passed = false;

    public Pipe(int x, int height, int width, int y, Image img) {
        this.x = x;
        this.height = height;
        this.width = width;
        this.y = y;
        this.img = img;
    }
}
