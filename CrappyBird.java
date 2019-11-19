package crappyBird;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Timer;

public class Crappybird {

static int DIAMETER = 25; //Diameter of the bird
static int X = ( game.WIDTH / 2 ) - ( DIAMETER / 2 ); //The x position of the bird. Does not change at any time. Should be exactly centered
static int y = game.HEIGHT / 2; //The STARTING y position of the bird. Will change constantly
static int acceleration = 1; //Used in the gravity simulation below
static int speed = 2; //The speed at which the bird will fall (constantly increased by acceleration (1) )

//Fetching bird.png from Imgur where it's hosted (not ideal, slower loading times)
static BufferedImage img = null;{
try {
img = ImageIO.read(new URL("https://i.imgur.com/S5s9WHI.png"));
} catch (IOException e) {
System.out.println("WRONG BIRD"); //Prints "WRONG BIRD" if there is an error retrieving the image
}}

public Crappybird(){
//just the constructor, nothing to see here
}

//This is called when the bird jumps (on mouse click). It just temporarily sets the speed to -17 (arbitrary number), then is slowly taken back down because
//of "gravity"
public void jump(){
speed = - 18;
}

//all movement stuff is here
public static void move(){

//only moves if the bird is between the top and bottom of the window
if ( ( y > 0 ) && ( y < game.HEIGHT )) {
speed += acceleration; //Here's the gravity I was talking about the speed is just increased by 1 all the time, even after a jump
y += speed; //The actual movement, y location equals (where it was) + (how far it should go)
}
//or else the game resets (Bird is dead!)
else {
reset(); //rests bird's postion, actual method below
game.dead = true; //bird is dead! This is used in the Main method to reset the walls after a death
}

}

public static void reset(){ //called after the bird dies
y = game.HEIGHT / 2; //resets position, speed, etc.
speed = 2;
game.score = 0;


game.deathMessage = "Dead! Try Again!"; //also shows this lovely message

//This timer just makes the message dissapear after 3000 milliseconds
Timer deathTimer = new Timer(3000, new ActionListener(){
public void actionPerformed(ActionEvent event){
game.deathMessage = "";
};
});

deathTimer.start();
}

public static void paint(Graphics g){
g.drawImage(img, X, y, null); //paints the bird's icon
}

public static Rectangle getBounds(){
return new Rectangle(X, y, DIAMETER, DIAMETER); //Gives a rectangle used to detect collisions in the Wall class
        }}