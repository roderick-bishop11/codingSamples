public class GeometricObject{

protected String color = "black"

public GeometricObject(){
this.color = color;
}

public GeometricObject(String color){
  this.color = color;
}

public String getColor(){
  return color;
}

public void setColor(String color){
  this.color = color;
}



 public String toString() {
 String result = "";

 result+= ("The Geometric Object color is: " + color  + " and\nThe Geometric Figure is: ");
 return result;
 }
 }
