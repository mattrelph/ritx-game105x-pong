Ball b1;
Paddle p1;

int score;
boolean playOn;
void setup()
{
  size (500,500);        // Q10 - Comment out size() above & use this one
  colorMode(HSB);      //Use Hew Saturation Brightness Color Model
  background(255);         // Q2
  
  b1 = new Ball(2, -3, 50);
  p1 = new Paddle();
  playOn = true;
  score = 0;


}

void draw()
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
