package crappyBird;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class game extends JPanel{

static int HEIGHT = 800; //height of the window
static int WIDTH = 600; //width of the window

    static void setColor(Color WHITE) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
Crappybird birdy = new Crappybird(); //makes a new bird
wall wall = new wall(WIDTH); //makes the first wall you see
wall wall2 = new wall(WIDTH + (WIDTH / 2)); //makes the second wall you see
static int score = 0; //the score (how many walls you've passed)
int scrollX = 0; //scrolls the background
static boolean dead = false; //used to reset the walls
static String deathMessage = "" ; // "you died, try again";

//grabs the background from Imgur
BufferedImage img = null;{
try {
img = ImageIO.read(new URL("https://imgur.com/EfctVyI.png"));
} catch (IOException e) {
System.out.println("WRONG BACKGROUND"); //prints "WRONG BACKGROUND" if there is an issue obtaining the background
}}


public game(){

//this mouseAdapter just listens for clicks, whereupon it then tells the bird to jump
this.addMouseListener(new MouseAdapter(){

public void mousePressed(MouseEvent arg0) {
birdy.jump();
}

});

}

@SuppressWarnings("static-access")
public void paint(Graphics g){
super.paint(g);

g.drawImage(img, scrollX, 0, null); //there are two backgrounds so you get that seamless transition, this is the first
g.drawImage(img, scrollX + 1800, 0, null); //number 2, exactly one background length away (1800 pixels)

wall.paint(g); //paints the first wall
wall2.paint(g); //the second wall
 birdy.paint(g); //the wee little birdy
 
 g.setFont(new Font("comicsans", Font.BOLD, 40));
 g.drawString("" + score, WIDTH / 2 - 20, 700);
 g.drawString(deathMessage, 200, 200); //paints "" if the player has not just died, paints "you died, try again" if the user just died
}

@SuppressWarnings("static-access")
public void move(){

wall.move(); //moves the first wall
wall2.move(); //moves the second wall
birdy.move(); //moves the wee little birdy

scrollX += wall.speed; //scrolls the wee little background

if (scrollX == -1800) //this loops the background around after it's done
scrollX = 0;

if (dead){ //this block essentially pushes the walls back 600 pixels on birdy death
wall.x = 600;
wall2.x = 600 + (WIDTH / 2);
dead = false;
}

if ( (wall.x == Crappybird.X) || (wall2.x == Crappybird.X) ) //Increments the score when the player passes a wall
score();
}

public static void score(){
score += 1;
}

}