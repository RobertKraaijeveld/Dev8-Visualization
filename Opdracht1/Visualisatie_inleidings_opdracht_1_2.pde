float[] oppervlakteLand = {13661000, 10498000, 8468300, 43608000, 30335000, 25349000, 21069501};

void setup() 
{
   size(640,480);
   noStroke();
   noLoop();
}

void draw() 
{
   background(600);
   float[] inputArray = getPercentageValueOf360Degrees(calculatePercentagesForGivenValues(oppervlakteLand));
   pieChart(300, inputArray);
}

void pieChart(float diameter, float[] data) {
   float lastAngle = 0;
   for(int i = 0; i < data.length; i++){
      float gray = map(i, 0, data.length, 0, 255); 
      fill(gray);
      arc(width/2, height/2, diameter, diameter, lastAngle, lastAngle + radians(data[i]));
      lastAngle += radians(data[i]);
      println(lastAngle);
   }
}

float[] getPercentageValueOf360Degrees(float[] values)
{
  float[] resultArray = new float[values.length];
  
  for(int i = 0; i < values.length; i++)
  {
    resultArray[i] = values[i] * 3.6;
    println(resultArray[i]);
  }
  return resultArray;
}

float[] calculatePercentagesForGivenValues(float[] values)
{
  float combinedValues = 0;
  float[] resultArray = new float[values.length];
  
  for(int i = 0; i < values.length; i++)
  {
    combinedValues += values[i];
  }
  
  for(int i = 0; i < values.length; i++)
  {
    resultArray[i] = (values[i] / combinedValues) * 100;
  }
  
  return resultArray;
}