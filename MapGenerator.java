import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
//mapgenerator for bricks
public class MapGenerator {
    //2d array contains bricks
    public int map[][];
    public int brickWidth;
    public int brickHeight;
    public MapGenerator(int row, int col){ //constructor to know no of rows and cols
           map = new int[row][col]; //instantiate the array
           for(int i=0; i<map.length; i++){
             for(int j=0; j<map[0].length; j++){ //loop for colmns
                    map[i][j] = 1;  //1 will detetct that the brick has not been intersected with the ball
             }                      //if we dont want to draw the brick we will set tot zero
           }
           brickWidth = 540/col;
           brickHeight = 150/row;
    }
    public void draw(Graphics2D g){ //when this is called the bricks are drawn where there is a value of 1, Graphics 2d objects is g
       for(int i=0; i< map.length;i++){
        for(int j=0; j< map[0].length; j++){
            if(map[i][j] >0 ) {
                g.setColor(Color.white);
                g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight); //drawing the rectangle of brick
                //creating border around each brick
                g.setStroke(new BasicStroke(3));
                g.setColor(Color.black);
                g.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
            }
        }
       }
       
    }


    //we need to detect which bricks the ball collides with and set their values to zero
    public void setBrickValue(int value, int row, int col){
        map[row][col]= value;
    }
}
