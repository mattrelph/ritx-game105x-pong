import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class RITx___GAME105x___ch05 extends PApplet {

Ball b1;
Paddle p1;

int score;
boolean playOn;
public void setup()
{
          // Q10 - Comment out size() above & use this one
  colorMode(HSB);      //Use Hew Saturation Brightness Color Model
  background(255);         // Q2
  
  b1 = new Ball(2, -3, 50);
  p1 = new Paddle();
  playOn = true;
  score = 0;


}

public void draw()
{
  background(255);         // Q2
  if (playOn)
  {
    p1.move();
    p1.display();
    if (b1.move(p1))
    {
      b1.display();
    }
    else
    {
      playOn = false;
      score = frameCount / 60;      
      //println ("Game over!");
    }
  }
  else
  {
    textSize(32);
    fill(0, 102, 153);

    String s = "Game over! \n Score= " + score;
    text(s, 150,30);
  }

}
//Class that creates a ball
//Implements creation, drawing, and moving the ball

class Ball
{
  //Ball Attributes
  float xLoc;
  float yLoc;
  float diam;
  float radius;
  float xAdjust;
  float yAdjust;
  float hueOuter;
  float hueInner;  
  float accell;
  
  Ball(float xV, float yV, float di)
  {
    // Code to declare and initialize variables for center coordinates, and
    // diameter and radius of the circle goes here   Q5
    xLoc = width/2;    // Q6
    yLoc = height/2;   // Q6
    diam = di;   // Q7
    radius = diam/2;   // Q7
    
    accell = 1.1f;
    
    //Set initial coordinates
    xAdjust = xV;
    yAdjust = yV;
    
    
    //Set initial color hues
    hueOuter = random(0,255);
    hueInner = random(0,255);

  }
  
  //Methods
    
    //Draws the ball
  public void display ()
  {
    // Code for the cyan circle goes here   Q3
    strokeWeight(5);         // Q8
    ++hueOuter;
    if (hueOuter >=255)
    {
      hueOuter = 0;
    }
    fill(hueOuter,255,255);         // Q8
    ellipse(xLoc,yLoc, diam,diam); // Q8
  
    // Code for the crosshair plus goes here   Q3
    strokeWeight(3);               // Q9
    line(xLoc, yLoc-radius, xLoc, yLoc+radius); // Q9
    line(xLoc-radius, yLoc, xLoc+radius, yLoc); // Q9
    --hueInner;
    if (hueInner <= 0) 
    {
      hueInner = 255;
    }
    fill(hueInner,255,255);         // Q8
    ellipse(xLoc,yLoc, radius,radius); // Q8
  }
  
    //Moves the ball
  public boolean move (Paddle pad)
  {
    
    if (xLoc >= width-radius )    //Collision with right wall test
    {
      xAdjust = xAdjust * -1 * accell;
    }
    else if (pad.collide(this))    //Collision with paddle test
    {
      xAdjust = xAdjust * -1 * accell;      
    }
    else if (xLoc - radius <= 0)  //Collision with left wall test
    {
      //Game is over
      return false;
    }
    
    if ((yLoc >= height-radius)  || (yLoc - radius <= 0))  //Test for collision with top and bottom walls
    {
      yAdjust = yAdjust * -1;
    }
    
    xLoc = xLoc + xAdjust;  
    yLoc = yLoc + yAdjust;
    
    /*
    textSize(32);
    fill(0, 102, 153);
    String s = "x="+ xLoc + " y=" + yLoc;
    text(s, 10,30);
     */
    return true;
    
  }
  

}


class Paddle
{
  //Attributes
  float yLoc;
  float xLoc;
  float paddleWidth;
  float paddleHeight;
  
  //Constructor
  Paddle()
  {
    yLoc = height/2;
    xLoc = 5;
    paddleWidth = 5;
    paddleHeight = 50;
  }
  
  //Methods
  
 public void display()
 {
    fill(0,0,0);         // Paddle fill Color
    rect(xLoc,yLoc, paddleWidth,paddleHeight); // Paddle frame
 }
 
 public void move()
 {
   //println (mouseY, ' ', mouseY+paddleHeight);
    if (mouseY + paddleHeight > height) 
    {
      yLoc = height - paddleHeight;
    }
    else if (mouseY < 0 )
    {
      yLoc = 0;      
    }
    else
    {
      yLoc = mouseY;
    }
   
 }
 
 public boolean collide(Ball b)
 {
   if (b.xLoc  < (this.xLoc + this.paddleWidth + b.radius))
   {
     if ((b.yLoc > this.yLoc) && (b.yLoc < this.yLoc + this.paddleHeight))
     {
       return true;
     }
   }
   return false;
 }
 
}
  public void settings() {  size (500,500); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "RITx___GAME105x___ch05" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
