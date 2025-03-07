package desktop.brick_breaker_game.brick;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePlay extends JPanel implements KeyListener, ActionListener {
    private boolean play = false;
    private int score = 0;
    private int totalBricks = 21;
    private Timer timer;
    private int delay = 8; 
    private int playerX = 310;
    private int ballPosX = 120;
    private int ballPosY = 350;
    private int ballYDir = -2;
    private int ballXDir = -1;
    private MapGenerator map;

    public GamePlay() {
        map = new MapGenerator(3, 7);
        addKeyListener(this); // become active if the user entered key on keyboard
        setFocusable(true); //ehich key is pressed
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this); 
        timer.start();
    }

    public void paint(Graphics g) {
        super.paint(g); // added super.paint(g)
        g.setColor(Color.white);
        g.fillRect(1, 1, 692, 592);
        map.draw((Graphics2D) g);
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);
        g.setColor(Color.black);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("" + score, 590, 30);
        g.setColor(Color.yellow);
        g.fillRect(playerX, 550, 100, 8);
        g.setColor(Color.green);
        g.fillOval(ballPosX, ballPosY, 20, 20);
        if (ballPosY > 570) {
            play = false;
            ballXDir = 0;
            ballYDir = 0;
            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 10));
            g.drawString("GAME OVER score :" + score, 190, 300);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("press enter to RESTART ", 190, 340);
        }
        if (totalBricks == 0) {
            play = false;
            ballXDir = -1;
            ballYDir = -2;
            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("GAME OVER score :" + score, 190, 300);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("press enter to RESTART ", 190, 340);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (play) {
            if (new Rectangle(ballPosX, ballPosY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
                ballYDir = -ballYDir;
            }
            A:
            for (int i = 0; i < map.map.length; i++) {
                for (int j = 0; j < map.map[0].length; j++) {
                    if (map.map[i][j] > 0) {
                        int brickX = j * map.brickWidth + 80;
                        int brickY = i * map.brickHeight + 50;
                        int brickWidth = map.brickWidth;
                        int brickHeight = map.brickHeight;

                        Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle(ballPosX, ballPosY, 20, 20);
                        Rectangle brickRect = rect;
                        if (ballRect.intersects(brickRect)) {
                            map.setBrickValue(0, i, j);
                            totalBricks--;
                            score += 5;
                            if (ballPosX + 19 <= brickRect.x || ballPosX + 1 >= brickRect.x + brickWidth) {
                                ballXDir = -ballXDir;
                            } else {
                                ballYDir = -ballYDir;
                            }
                            break A;
                        }
                    }
                }
            }
            ballPosX += ballXDir;
            ballPosY += ballYDir;
            if (ballPosX < 0) {
                ballXDir = -ballXDir;
            }
            if (ballPosY < 0) {
                ballYDir = -ballYDir;
            }
            if(ballPosX>670){
                ballXDir=-ballXDir;
            }

            
        }
        repaint();
        }
     
    
    @Override
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            if(playerX>=600){
                playerX=600;
            }
            else{
                moveright();
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            if(playerX<10){
                playerX=10;
            }
            else{
                moveleft();
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            if(!play){
                ballPosX=120;
                ballPosY=350;
                ballXDir=-1;
                ballYDir=-2;
                score=0;
                playerX=310;
                totalBricks=21;
                map=new MapGenerator(3,7);
            }
            
        }
        repaint();
    }
    public void moveleft(){
play=true;
playerX-=20;
    }
    public void moveright(){
        play=true;
        playerX+=20;
    }
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }
    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
    }
}

