package sample;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;
import java.util.StringTokenizer;
//reverse Polish notation
public class RPN {

//for example: change (2+3)*5 to 2 3 + 5 *
    public String changeEnterToExit(String text){
        String enterText=changeTextWithNegativeNumbers(text);
        StringBuilder exitText= new StringBuilder();
        Stack<String> stack=new Stack<>();
        StringTokenizer stringTokenizer=new StringTokenizer(enterText,"+-*/()",true);
        while (stringTokenizer.hasMoreTokens()){
            String element=stringTokenizer.nextToken();
            switch (element) {
                case "+":
                case "*":
                case "-":
                case "/":
                    while (!stack.empty() && getPriority(stack.peek()) >= getPriority(element))
                        exitText.append(stack.pop()).append(" ");

                    stack.push(element);
                    break;
                case "(":
                    stack.push(element);
                    break;
                case ")":

                    while (!stack.peek().equals("(")) exitText.append(stack.pop()).append(" ");
                    stack.pop();
                    break;
                default:
                    exitText.append(element).append(" ");
                    break;
            }
        }
        while(!stack.empty()) exitText.append(stack.pop()).append(" ");
        return exitText.toString();
    }
    // calculate from reverse expression
    public double calculate(String exitText) throws EmptyStackException {
        Stack<Double>stack=new Stack<>();
        StringTokenizer stringTokenizer=new StringTokenizer(exitText," ");
        while(stringTokenizer.hasMoreTokens()) {
            String element = stringTokenizer.nextToken();
            if (!element.equals("+") && !element.equals("*") && !element.equals("-") && !element.equals("/")) {
                double value = Double.parseDouble(element);
                stack.push(value);
            }
            else {
                double valueOne = stack.pop();
                double valueTwo = stack.pop();
                switch(element.charAt(0)) {
                    case '*': {
                        stack.push(valueTwo * valueOne);
                        break;
                    }
                    case '+': {
                        stack.push(valueTwo + valueOne);
                        break;
                    }
                    case '-': {
                        stack.push(valueTwo - valueOne);
                        break;
                    }
                    case '/': {
                        stack.push(valueTwo / valueOne);
                        break;
                    }
                }
            }
        }
        return stack.pop();
    }
    //priority * / is greater than + -
    private int getPriority(String operator) {

        if(operator.equals("+") || operator.equals("-")) return 1;
        else if(operator.equals("*") || operator.equals("/")) return 2;
        else return 0;
    }
    //check if numbers of brackets "("==numbers of brackets")" and their order
    public boolean checkBrackets(String enterText){
        Stack<String> stackForBrackets = new Stack<>();
        StringTokenizer st = new StringTokenizer(enterText, "()",true);
        while(st.hasMoreTokens()) {
            String elementInBracket = st.nextToken();
            if(elementInBracket.equals("(")) stackForBrackets.push(elementInBracket);

            if(elementInBracket.equals(")")) {
                if (stackForBrackets.isEmpty()) return false;
                if (!stackForBrackets.pop().equals("(")) return false;
            }
        }
        return stackForBrackets.isEmpty();
    }
    //for example: change (-4)*(-2) to (0-4)*(0-2)
    private String changeTextWithNegativeNumbers(String text){
        StringBuilder stringBuilder=new StringBuilder(text);
        ArrayList<Integer> list=new ArrayList<>();
        StringTokenizer stringTokenizer=new StringTokenizer(text,"-(",true);
        int fromIndex=0;
        while(stringTokenizer.hasMoreTokens()){
            String element=stringTokenizer.nextToken();
            if(element.equals("(")){
                if((stringTokenizer.nextToken()).equals("-")){
                    int index=text.indexOf('(',fromIndex);
                    fromIndex=index+1;
                    list.add(index);
                }
                else{
                    fromIndex=text.indexOf("(",fromIndex)+1;
                }
            }
        }
        int counter=1;
        for(Integer indexOfBracket:list){
            stringBuilder.insert(indexOfBracket+counter,'0');
            counter++;

        }
        return stringBuilder.toString();
    }
}
