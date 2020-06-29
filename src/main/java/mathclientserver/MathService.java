package mathclientserver;

import networking.serversocket.interfacepack.MathInterface;

public class MathService implements MathInterface {
    private static final String regexPattern = "(\\d+)([-+/])(\\d+)";
    //private static final String regexPattern = "(\\d+)([-+*.*[/\\]*.])(\\d+)";
   // private static final String regexPattern = "(\\d+)([-+*&&/\\\\])(\\d+)";
    private static final String regexOperator = "[-+/*]";

    public double add(double firstValue, double secondValue){
        return  firstValue + secondValue;
    }
    public double sub(double firstValue, double secondValue){
        return firstValue - secondValue;
    }
    public double div(double firstValue, double secondValue){
        if (secondValue !=0)
            return firstValue / secondValue;
        return Double.MAX_VALUE;
    }
    public double mul (double firstValue, double secondValue){
        return firstValue * secondValue;
    }
    public double pow (double firstValue, double secondValue) {return Math.pow(firstValue, secondValue);}

    public int isNumber(String str){
        boolean input = Character.isDigit(str.charAt(0));
        //System.out.println(input);
        int status =(input == true) ? 1:2; // status is 1 if input is a number, status is 2 if input is not a number
        return status;
    }

    public double returnResult(String str){
        String[] values = splitValues(str);
        char op = determineOperator(str);
        double firstValue = Double.parseDouble(values[0]);
        double secondValue = Double.parseDouble(values[1]);
        return calculate(op,firstValue,secondValue);
    }

    public String[] splitValues(String str){
        String[] values={"0","+","0"};
        if(str.matches(regexPattern)==true){
            values =str.split(regexOperator);
        }
        return values;
    }

    public char determineOperator(String str){
        char op ='+';
        for(int i=0; i<str.length(); i++){
            char c = str.charAt(i);
            if(c=='+'){op = '+'; break;}
            else if(c=='-'){op = '-'; break;}
            else if (c == '*'){op = '*';break;}
            else if(c=='/'){op='/';break;}
            else if(c==':'){op=':';break;}
        }
        return op;
    }

    public double calculate(char op, double firstValue, double secondValue){
        double result = Double.MAX_VALUE;
           if(op=='+'){result = add(firstValue, secondValue);}
           else if(op=='-'){result = sub(firstValue, secondValue);}
           else if(op=='*'){result = mul(firstValue, secondValue);}
           else if(op=='/'){result = div(firstValue, secondValue);}
        return result;
    }
}

/*
*     public double calculate(char op, double firstValue, double secondValue){
        double result = Double.MAX_VALUE;
        switch (op) {
            case '+':
                result = add(firstValue, secondValue);
                break;
            case '-':
                result = sub(firstValue, secondValue);
                break;
            case '*':
                result = mul(firstValue, secondValue);
                break;
            case '/':
                result = div(firstValue, secondValue);
                break;
            case '\\':
                result = div(firstValue, secondValue);
                break;
            case ':':
                result = div(firstValue, secondValue);
                break;
            default:
                throw new IllegalArgumentException("Invalid math operation!");
        }
        return result;
    }
* */