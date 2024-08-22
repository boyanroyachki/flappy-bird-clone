package org.commodolink;

import org.commodolink.entity.Bird;
import org.commodolink.entity.Pipe;
import org.commodolink.util.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {

    //frame dimensions
    final int frameWidth;
    final int frameHeight;

    //images
    final Image backgroundImg;
    final Image birdImg;
    final Image topPipeImg;
    final Image bottomPipeImg;

    //bird coordinates
    int birdX;
    int birdY ;

    //bird dimensions
   final int birdWidth;
   final int birdHeight;

    int pipeX;
    int pipeY;
    int pipeWidth;
    int pipeHeight;

    Bird bird;
    Timer gameLoop;
    Timer placePipesTimer;

    boolean gameOver = false;
    double score = 0;

    ArrayList<Pipe> pipes;

    int velocityY = 0;
    final int velocityX = -4;
    final int gravity = 1;

    public FlappyBird(int frameWidth, int frameHeight, int birdWidth, int birdHeight) {
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;

        this.birdWidth = birdWidth;
        this.birdHeight = birdHeight;

        birdX = frameWidth/8;
        birdY = frameHeight/2;

        pipeX = frameWidth;
        pipeY = 0;
        pipeWidth = 64;
        pipeHeight = 512;

        setFocusable(true);
        addKeyListener(this);
        setPreferredSize(new Dimension(frameWidth, frameHeight));
        setBackground(Color.BLUE);

        //load the images
        backgroundImg = ImageLoader.getImageFromPath("src/main/resources/images/flappybirdbg.png");
        birdImg = ImageLoader.getImageFromPath("src/main/resources/images/flappybird.png");
        topPipeImg = ImageLoader.getImageFromPath("src/main/resources/images/toppipe.png");
        bottomPipeImg = ImageLoader.getImageFromPath("src/main/resources/images/bottompipe.png");

        bird = new Bird(birdHeight, birdWidth, birdY, birdX, birdImg);
        pipes = new ArrayList<>();

        placePipesTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipes();
            }
        });

        placePipesTimer.start();
        gameLoop = new Timer(1000/60, this);
        gameLoop.start();

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        g.drawImage(backgroundImg, 0, 0, frameWidth, frameHeight, null);

        g.drawImage(bird.img, bird.x, bird.y, bird.width, bird.height, null);

        for(Pipe pipe : pipes){
            g.drawImage(pipe.img, pipe.x, pipe.y, pipe.width, pipe.height, null);
        }

        g.setColor(Color.white);

        g.setFont(new Font("Arial", Font.PLAIN, 32));
        if (gameOver) {
            g.drawString("Game Over: " + String.valueOf((int) score), 10, 35);
        }
        else {
            g.drawString(String.valueOf((int) score), 10, 35);
        }
    }

    public void move(){
        velocityY += gravity;
        bird.y += velocityY;
        bird.y = Math.max(bird.y, 0);

        for(Pipe pipe : pipes){
           pipe.x += velocityX;

           if (!pipe.passed && bird.x > pipe.x + pipe.width){
               pipe.passed = true;
               score += 0.5;
           }

           if (collision(bird, pipe)){
               gameOver = true;
           }
        }
        if (bird.y > frameHeight){
            gameOver = true;
        }

    }

    public void placePipes(){
        int randomPipeY = (int)(pipeY - pipeHeight / 4 - Math.random()*(pipeHeight / 2));
        int openingHeight = frameHeight/4;
        Pipe topPipe = new Pipe(pipeX, pipeHeight, pipeWidth, pipeY, topPipeImg);

        topPipe.y = randomPipeY;
        pipes.add(topPipe);

        Pipe bottomPipe = new Pipe(pipeX, pipeHeight, pipeWidth, pipeY, bottomPipeImg);
        bottomPipe.y = topPipe.y  + pipeHeight + openingHeight;
        pipes.add(bottomPipe);
    }

    public boolean collision(Bird a, Pipe b){
        return a.x < b.x + b.width &&   //a's top left corner doesn't reach b's top right corner
                a.x + a.width > b.x &&   //a's top right corner passes b's top left corner
                a.y < b.y + b.height &&  //a's top left corner doesn't reach b's bottom left corner
                a.y + a.height > b.y;    //a's bottom left corner passes b's top left corner
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        if (gameOver){
            placePipesTimer.stop();
            gameLoop.stop();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            velocityY = -9;

            if (gameOver) {
                //restart game by resetting conditions
                bird.y = birdY;
                velocityY = 0;
                pipes.clear();
                gameOver = false;
                score = 0;
                gameLoop.start();
                placePipesTimer.start();
            }

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
