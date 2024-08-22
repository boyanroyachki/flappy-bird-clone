package org.commodolink.util;

import javax.swing.*;
import java.awt.*;

public class ImageLoader {

    public static Image getImageFromPath(String path){
        return new ImageIcon(path).getImage();
    }
}
