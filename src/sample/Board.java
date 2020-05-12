package sample;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.control.*;

import java.util.EmptyStackException;


public class Board {
    private TextField result;
    private GridPane gridPane;
    private Button seven;
    private Button eight;
    private Button nine;
    private Button divide;
    private Button four;
    private Button five;
    private Button six;
    private Button multiply;
    private Button one;
    private Button two;
    private Button three;
    private Button minus;
    private Button zero;
    private Button dot;
    private Button plus;
    private Button equalButton;
    private Button clearButton;
    private Button firstBracket;
    private Button secondBracket;
    private EventHandler<ActionEvent> insert;
    private EventHandler<ActionEvent> clear;
    private EventHandler<ActionEvent> equal;
    private RPN rpn;

    public void drawBoard(){
        gridPane =new GridPane();
        insert=new InsertAction();
        clear=new ClearAction();
        equal=new EqualAction();

        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.setAlignment(Pos.CENTER);

        result=new TextField("");
        result.setEditable(true);
        result.setFocusTraversable(true);
        result.setAlignment(Pos.CENTER_RIGHT);
        gridPane.add(result,0,0,5,1);

        seven=new Button("7");
        addButton(insert,seven);
        gridPane.add(seven,0,1,1,1);

        eight=new Button("8");
        addButton(insert,eight);
        gridPane.add(eight,1,1,1,1);

        nine=new Button("9");
        addButton(insert,nine);
        gridPane.add(nine,2,1,1,1);

        divide=new Button("/");
        addButton(insert,divide);
        gridPane.add(divide,3,1,1,1);

        clearButton=new Button("C");
        addButton(clear,clearButton);
        gridPane.add(clearButton,4,1,1,1);

        four=new Button("4");
        addButton(insert,four);
        gridPane.add(four,0,2,1,1);

        five=new Button("5");
        addButton(insert,five);
        gridPane.add(five,1,2,1,1);

        six=new Button("6");
        addButton(insert,six);
        gridPane.add(six,2,2,1,1);

        multiply=new Button("*");
        addButton(insert,multiply);
        gridPane.add(multiply,3,2,1,1);

        firstBracket=new Button("(");
        addButton(insert,firstBracket);
        gridPane.add(firstBracket,4,2,1,1);

        one=new Button("1");
        addButton(insert,one);
        gridPane.add(one,0,3,1,1);

        two=new Button("2");
        addButton(insert,two);
        gridPane.add(two,1,3,1,1);

        three=new Button("3");
        addButton(insert,three);
        gridPane.add(three,2,3,1,1);

        minus=new Button("-");
        addButton(insert,minus);
        gridPane.add(minus,3,3,1,1);

        secondBracket=new Button(")");
        addButton(insert,secondBracket);
        gridPane.add(secondBracket,4,3,1,1);

        zero=new Button("0");
        addButton(insert,zero);
        gridPane.add(zero,0,4,1,1);

        dot=new Button(".");
        addButton(insert,dot);
        gridPane.add(dot,1,4,1,1);

        plus=new Button("+");
        addButton(insert,plus);
        gridPane.add(plus,2,4,1,1);

        equalButton =new Button("=");
        equalButton.setPrefSize(65,30);
        gridPane.add(equalButton,3,4,2,1);
        equalButton.setOnAction(equal);


    }

    private void addButton(EventHandler<ActionEvent> insert,Button button) {
        button.setPrefSize(30,30);
        button.setOnAction(insert);
    }


    private class InsertAction implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent actionEvent) {
            String display=((Button)actionEvent.getSource()).getText();
            result.setText(result.getText()+display);
        }
    }
    private class ClearAction implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent actionEvent) {
            result.setText("");
        }
    }

    private  class EqualAction implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent actionEvent) {
            rpn=new RPN(result.getText());
            if(!rpn.checkBrackets()){
                result.setText("Uncorrect brackets!");
            }
            else{
                try{
                    rpn.changeEnterToExit();
                    double finalResult=rpn.calculate();
                    finalResult*=1000000000;
                    finalResult=Math.round(finalResult);
                    finalResult/=1000000000;
                    if((finalResult-(int)finalResult)==0)result.setText(String.valueOf((int)finalResult));
                    else result.setText(String.valueOf(finalResult));
                }
                catch (EmptyStackException e){
                    result.setText("Invalid expression!");

                }


            }

        }
    }

    public GridPane getGridPane() {
        return gridPane;
    }
}
