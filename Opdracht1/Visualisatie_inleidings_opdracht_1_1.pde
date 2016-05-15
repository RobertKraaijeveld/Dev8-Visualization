void setup () 
{
   size(640,480); 
   stroke(0);
   background(255);
   textSize(10);
}

void draw ()
{
   
   
   line(50, 200, 50, 0);
   
   for(int i=0; i<201; i++)
   {
       if(i % 20 == 0)
       {
         text(200 - i, 10, i+8); 
         line(48, i, 400, i);
       }
   }
   
   line(400, 200, 400, 0);
   
   rect(80, 80 , 50 , 120);
   rect(160, 50 , 50 , 150);
   rect(240, 20 , 50 , 180);
   rect(320, 140 , 50 , 60);
   
   text("kwartaal 1", 80, 210); 
   text("kwartaal 2", 160, 210); 
   text("Kwartaal 3", 240, 210); 
   text("Kwartaal 4", 320, 210); 
   
   fill(140);
}