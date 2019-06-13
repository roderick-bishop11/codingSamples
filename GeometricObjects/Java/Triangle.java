/* Author: Roderick Bishop
*Class: CS 170-01
* Due Date: April 8th, 2019, 2:00 PM
* I certify that this lab is entirely my own work
*/
public class Triangle extends GeometricObject{
  double side1 = 1.0;
  double side2 = 1.0;
  double side3 = 1.0;

  public Triangle(){

  }

  public Triangle(double side1, double side2, double side3){
    this.side1 = side1;
    this.side2 = side2;
    this.side3 = side3;
  }

  public double getSide1(){
    return side1;
  }
  public double getSide2(){
    return side2;
  }

  public double getSide3(){
    return side3;
  }

  public double getArea(){
    double s = (this.side1+this.side2+this.side3)/2.0;
		double u = (s*(s-this.side1)*(s-this.side2)*(s-this.side3));
		double area = Math.sqrt(u);
		return area;
  }
  public double getPerimeter(){
    return side1+side2+side3;
  }

  public String toString(){
    return ("Triangle: side1 = " + side1 + " side2 = " + side2 +
" side3 = " + side3);
  }
}
