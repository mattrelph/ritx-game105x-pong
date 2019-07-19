

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
  
 void display()
 {
    fill(0,0,0);         // Paddle fill Color
    rect(xLoc,yLoc, paddleWidth,paddleHeight); // Paddle frame
 }
 
 void move()
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
 
 boolean collide(Ball b)
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
