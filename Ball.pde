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
    
    accell = 1.1;
    
    //Set initial coordinates
    xAdjust = xV;
    yAdjust = yV;
    
    
    //Set initial color hues
    hueOuter = random(0,255);
    hueInner = random(0,255);

  }
  
  //Methods
    
    //Draws the ball
  void display ()
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
  boolean move (Paddle pad)
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
