//it exsists in java x swing package
import javax.swing.JFrame;
public class Main{
    public static void main(String[] args) {
        //jframe is the outer window that has icons like minimize etc  its a built in class we need to import it as use as object
       JFrame obj = new JFrame();

       //creating an object of gameplay panel class //creating another classs for game called gameplay
       Gameplay gamePlay = new Gameplay();

       //porperties for the frame

       obj.setBounds(10, 10,700,600); //size of the window
       obj.setTitle("Brick Breaker");
       obj.setResizable(false);
       obj.setVisible(true); // we neeed to explicitly set to true
       obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       obj.add(gamePlay); //adding gamePlay object to the frame, here the object neeeds to be a panel
       
       


    }
}