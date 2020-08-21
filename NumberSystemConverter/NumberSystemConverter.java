public class NumberSystemConverter{

//TODO: figure out how to handle octal numbers
//TODO: add functionality for any to decimal
//TODO: add functionality for decimal to any
//TODO: refactor for cleaner convertions/readability/ optimization
//TODO: Add custom user input for conversions
//TODO: set up in while loop to continuously run


//these are nice to haves to finish this project
//TODO: set up cache for history of past conversions
//TODO: possibly publish to a runnable JAR for use? 




    public static void main(String[]args){
        String binary = "1111";
        System.out.println(binary +  " resolves to " + convertToBase(binary, Base.BINARY, Base.DECIMAL) + " in decimal");
        String binary2 = "1010";
        System.out.println(binary2 +  " resolves to " + convertToBase(binary2, Base.BINARY, Base.DECIMAL) + " in decimal");
        String binary3 = "1000000";
        System.out.println(binary3 +  " resolves to " + convertToBase(binary3, Base.BINARY, Base.DECIMAL) + " in decimal");

        //case for empty string or 
        String binary4 = "";
        System.out.println(binary4 +  " resolves to " + convertToBase(binary4, Base.BINARY, Base.DECIMAL) + " in decimal");


    }


    public static String convertToBase(String num, Base original, Base desired){
       StringBuilder newStr = new StringBuilder();
        if(desired != Base.DECIMAL){
           newStr = convertToDecimal(num, original); //returns  a decimal number as a string 
        }
        else{
            return convertToDecimal(num, original).toString();
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

    //pass in a digit and then return the correct digit based on the value. 
    public static String hexDigit(double val){
        String num = 0;
            switch(val){
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