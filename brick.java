
package desktop.brick_breaker_game.brick;
import java.util.*;

import javax.swing.JFrame;
public class brick{
    public static void main(String args[]){
         JFrame obj= new JFrame();
         GamePlay gameplay=new GamePlay();
         obj.setBounds(10,10,900,800); //setting the height of the frame 
         obj.setTitle("brickbreaker");
         obj.setResizable(false);
         obj.setVisible(true); // so that vidibele on the screen 
         obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         obj.add(gameplay);
         
    }
}