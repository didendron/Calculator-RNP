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


    public void drawBoard(){
        gridPane =new GridPane();
        EventHandler<ActionEvent> insert=new InsertAction();
        EventHandler<ActionEvent> clear=new ClearAction();
        EventHandler<ActionEvent> equal=new EqualAction();

        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.setAlignment(Pos.CENTER);

        result=new TextField("");
        result.setEditable(true);
        result.setFocusTraversable(true);
        result.setAlignment(Pos.CENTER_RIGHT);
        gridPane.add(result,0,0,5,1);

        Button seven=new Button("7");
        addButton(insert,seven);
        gridPane.add(seven,0,1,1,1);

        Button eight=new Button("8");
        addButton(insert,eight);
        gridPane.add(eight,1,1,1,1);

        Button nine=new Button("9");
        addButton(insert,nine);
        gridPane.add(nine,2,1,1,1);

        Button divide=new Button("/");
        addButton(insert,divide);
        gridPane.add(divide,3,1,1,1);

        Button clearButton=new Button("C");
        addButton(clear,clearButton);
        gridPane.add(clearButton,4,1,1,1);

        Button four=new Button("4");
        addButton(insert,four);
        gridPane.add(four,0,2,1,1);

        Button five=new Button("5");
        addButton(insert,five);
        gridPane.add(five,1,2,1,1);

        Button six=new Button("6");
        addButton(insert,six);
        gridPane.add(six,2,2,1,1);

        Button multiply=new Button("*");
        addButton(insert,multiply);
        gridPane.add(multiply,3,2,1,1);

        Button firstBracket=new Button("(");
        addButton(insert,firstBracket);
        gridPane.add(firstBracket,4,2,1,1);

        Button one=new Button("1");
        addButton(insert,one);
        gridPane.add(one,0,3,1,1);

        Button two=new Button("2");
        addButton(insert,two);
        gridPane.add(two,1,3,1,1);

        Button three=new Button("3");
        addButton(insert,three);
        gridPane.add(three,2,3,1,1);

        Button minus=new Button("-");
        addButton(insert,minus);
        gridPane.add(minus,3,3,1,1);

        Button secondBracket=new Button(")");
        addButton(insert,secondBracket);
        gridPane.add(secondBracket,4,3,1,1);

        Button zero=new Button("0");
        addButton(insert,zero);
        gridPane.add(zero,0,4,1,1);

        Button dot=new Button(".");
        addButton(insert,dot);
        gridPane.add(dot,1,4,1,1);

        Button plus=new Button("+");
        addButton(insert,plus);
        gridPane.add(plus,2,4,1,1);

        Button equalButton =new Button("=");
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
            RPN rpn=new RPN();
            if(!rpn.checkBrackets(result.getText())){
                result.setText("Uncorrect brackets!");
            }
            else{
                try{
                    double finalResult=rpn.calculate(rpn.changeEnterToExit(result.getText()));
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
