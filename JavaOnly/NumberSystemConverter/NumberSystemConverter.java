import jdk.javadoc.internal.doclets.formats.html.SourceToHTMLConverter;

public class NumberSystemConverter{


//TODO: add functionality for decimal to any
//TODO: refactor for cleaner convertions/readability/ optimization
//TODO: Add custom user input for conversions
//TODO: set up in while loop to continuously run


//these are nice to haves to finish this project
//TODO: set up cache for history of past conversions
//TODO: possibly publish to a runnable JAR for use? 




    public static void main(String[]args){
        //binary
        String binary = "1111";
        System.out.println(binary +  " resolves to " + convertToBase(binary, Base.BINARY, Base.DECIMAL) + " in decimal");

        //octal
        String octal1 = "0741";
        System.out.println(octal1 + " resolves to " + convertToBase(octal1, Base.OCTAL, Base.DECIMAL) + " in decimal");

        //hex
        String hex = "AA";
        System.out.println(hex + " resolves to " + convertToBase(hex, Base.HEX, Base.DECIMAL) + " in decimal");

        //Decimal to 
        String dec = "15";
        System.out.println(dec + " resolves to " + convertToBase(dec, Base.DECIMAL, Base.BINARY) + " in binary");
        System.out.println(dec + " resolves to " + convertToBase(dec, Base.DECIMAL, Base.HEX) + " in hex");
        System.out.println(dec + " resolves to " + convertToBase(dec, Base.DECIMAL, Base.OCTAL) + " in octal");

        
        
    }

    public static String convertToBase(String num, Base original, Base desired){
       StringBuilder newStr = new StringBuilder();
        switch(desired){
            case BINARY: 
                newStr = convertToDecimal(num, original);
                newStr.setLength(0); //clears the newstr
                newStr.append(Integer.toString(Integer.parseInt(num), 2)); // integer.parseInt has built in functions to convert
                break;
            case OCTAL: 
                newStr = convertToDecimal(num, original);
                newStr.setLength(0);
                newStr.append(Integer.toString(Integer.parseInt(num), 8));
                break;
            case HEX:
                newStr = convertToDecimal(num, original);
                newStr.setLength(0);
                newStr.append(Integer.toString(Integer.parseInt(num), 16));
                break;
            default:
            newStr = convertToDecimal(num, original);
        }
        return newStr.toString();
    }

    //converts the base to the decimal value 
    public static StringBuilder convertToDecimal(String num, Base base){
        StringBuilder newStr = new StringBuilder(); // this will be the new number that we convert to 
         double exp = (num.length() > 0)? num.length()-1: 0; //rightmost digit is the highest value
         int sum = 0;
           for(int i  = 0; i< num.length(); i++){
             double val = Character.getNumericValue(num.charAt(i)) * Math.pow(retBase(base), exp);
            sum+=(int)val;
               exp--;
           }
           newStr.append(sum);
        return newStr;
    }

    public static double retBase(Base base){
        switch(base){
         case BINARY: return 2;
         case HEX: return 16;
         case OCTAL: return 8;  
         case DECIMAL: return 10;
        }
        return 10;
    }

    public static StringBuilder toHex(String num){
        StringBuilder s = new StringBuilder();
        return s;
    }
    public static StringBuilder toOctal(String num){
        StringBuilder s = new StringBuilder();
        return s;
    }
    public static StringBuilder toBinary(String num){
        StringBuilder s = new StringBuilder();
        return s;
    }

    //pass in a digit and then return the correct digit based on the value. 
    public static String hexDigit(double val){
        String num = "0";
            switch((int)val){
                case 10:
                    num = "A";
                    break;
                case 11:
                    num = "B";
                    break;
                case 12:
                    num = "C";
                    break;
                case 13:
                    num = "D";
                    break;
                case 14:
                    num = "E";
                    break;
                case 15: 
                    num = "F";
                    break;
            }
        return num;
    }

    //this is just a nice way to classify all numbers within a base
    enum Base{
        HEX,
        DECIMAL,
        OCTAL,
        BINARY
    }
}