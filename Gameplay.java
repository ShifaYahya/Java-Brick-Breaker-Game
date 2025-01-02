
//this is the panel we will run our game in the panel
//inorder to make this class panel we need to extend with jpanel
//we need to implement interfaces as well
//keylistener for detecting arrow keys
//And action listener for moving the ball
import javax.swing.JPanel;
import java.awt.event.KeyListener;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
//adding properties

   private boolean play = false; //when the game starts it shouldn't play by itself
   private int score= 0 ; //starting score should be zero
   private int totalBricks = 21;

   private Timer timer;
   private int delay =8; //speed of timer

   private int playerX = 310; //starting position of slider
   private int ballposX = 120; //starting position of ball , x coordinate
   private int ballposY = 350;// y position y coordinate
   private int ballXdir = -1; //x direction  of ball
   private int ballYdir = -2; //y direction of the ball

   private MapGenerator map;
  //need to create an object of mapgenerator class in constructor of gameplay class
  public Gameplay(){//constructor
      map = new MapGenerator(3, 7);


    addKeyListener(this); // add keylistener to work with keylisteners
    setFocusable(true);
    setFocusTraversalKeysEnabled(false);
    timer = new Timer(delay,this); //speed = delay variable
    timer.start();
  }

public void paint (Graphics g){  //can draw objects like ball etc with graphics object
      //background
      g.setColor(Color.black);
      g.fillRect(1,1, 692, 592); //position and size etc

      //drawing map(bricks)
      map.draw((Graphics2D)g);

      //borders
      //g.setColor(Color.yellow);
      /**g.fillRect(0, 0, 3, 592);    // Left border
      g.fillRect(0, 0, 692, 3);    // Top border
      g.fillRect(692, 0, 3, 592); */

      //scores
     g.setColor(Color.white);
     g.setFont(new Font("serif", Font.BOLD, 25));
     g.drawString(""+score, 590  ,30);


      //the paddle
      g.setColor(Color.green);
      g.fillRect(playerX, 550, 100, 8);

      //the ball
      g.setColor(Color.yellow);
      g.fillOval(ballposX, ballposY, 20, 20);
    

      if(totalBricks <= 0){  //if game is done = al bricks gone
        play= false;
        ballXdir = 0;
        ballYdir = 0;
        g.setColor(Color.RED);
        g.setFont(new Font("serif", Font.BOLD, 30));
        g.drawString("You've Won ", 260, 300);  //190pos because we want to show it in the middle of the screen 
  
        g.setFont(new Font("serif", Font.BOLD, 20));
        g.drawString("Press Enter to Restart: ", 230, 350);
  
      }

      
    if(ballposY >570 ){
      play= false;
      ballXdir = 0;
      ballYdir = 0;
      g.setColor(Color.RED);
      g.setFont(new Font("serif", Font.BOLD, 30));
      g.drawString("Game Over!, Score: "+score, 190, 300);  //190pos because we want to show it in the middle of the screen 

      g.setFont(new Font("serif", Font.BOLD, 20));
      g.drawString("Press Enter to Restart: ", 230, 350);


    }

    
      g.dispose();

}




    //we need to add unimplemented methods for implements
   @Override
    public void actionPerformed(ActionEvent e){
     timer.start();

     if(play) {
      //detecting intersection between the brick and the ball
       if(new Rectangle(ballposX, ballposY,20,20).intersects(new Rectangle(playerX, 550, 100, 8))) {
        ballYdir = -ballYdir;  //ball is moving with a speed of 8 we added in our delay variable
       }

       //iterate through bricks
       A: for(int i=0; i<map.map.length; i++){
          for(int j=0; j<map.map[i].length; j++){  //map first is the var and the second one is the other variable
            if(map.map[i][j] > 0){
              int brickX = j*map.brickWidth +80;
              int brickY = i* map.brickHeight +50;
              int brickWidth = map.brickWidth;
              int brickHeight = map.brickHeight;

              Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
              Rectangle ballRect = new Rectangle(ballposX, ballposY,20,20);
              Rectangle brickRect = rect;

              if(ballRect.intersects(brickRect)){
                map.setBrickValue(0, i, j);
                totalBricks--;
                score +=5;

                if(ballposX +19 <= brickRect.x || ballposX +1 >= brickRect.x + brickRect.width){
                  ballXdir = -ballXdir; //move the ball to opposite direction
                } else {
                  ballYdir = -ballYdir;
                }
                break A; //break label to break out of outer loop
              }

            }
          }
       }

      ballposX += ballXdir;
      ballposY += ballYdir;
      if(ballposX<0 ){
        ballXdir = -ballXdir;
      }
      if(ballposY<0 ){
        ballYdir = -ballYdir;
      }
      if(ballposX > 670 ){
        ballXdir = -ballXdir;
      }
     }
     repaint();   //need to redraw calll paint method again
    
    }

    @Override
    public void keyTyped(KeyEvent e){//for moving the ball we need key listeners if we  pressed the irght arrow key or left
        
    }

    @Override
    public void keyPressed(KeyEvent e){ //can detect arrow keys here
      if(e.getKeyCode() == KeyEvent.VK_RIGHT){
        if (playerX >= 600) {
          playerX =600;
        } else {
          moveRight();
        }
      }
      if(e.getKeyCode() == KeyEvent.VK_LEFT){
        if (playerX < 10) {
          playerX =10;
        } else {
          moveLeft();
        }
      }
      if(e.getKeyCode() == KeyEvent.VK_ENTER ){
        if(!play){ //if game over or the game has finished then run this code
          play = true;
          ballposX = 120;
          ballposY = 350;
          ballXdir = -1;
          ballYdir = -2;
          playerX = 310;   //regenerating redraawing everything after pressing enter key
          score = 0;
          totalBricks = 21;
          map = new MapGenerator(3, 7);
          repaint();
        }
      }
      
    }
   
   
    public void moveRight() {
      play = true; //set to true cuz it was set to false
      playerX+= 20; //move 2o pixels to the right
    }
    public void moveLeft() {
      play = true;   //set to true cuz it was set to false
      playerX-= 20;
    }




    @Override
    public void keyReleased(KeyEvent e){

    }


    
}
