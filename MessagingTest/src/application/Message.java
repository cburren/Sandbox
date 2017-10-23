package application;

import java.io.Serializable;
import java.util.ArrayList;

public class Message implements Serializable {
    private static final long serialVersionUID = -5399605122490343339L;

    private Integer A;
    private Integer B;
    private Integer Result;
    private boolean ready;
    private ArrayList<Cards> allCards;

    public Message(Integer firstNumber, Integer secondNumber ){
        this.A = firstNumber; 
        this.B = secondNumber;
        ready = false;
        allCards = new ArrayList<Cards>();
        
        
    }

    public Integer getA() {
        return A;
    }

    public Integer getB() {
        return B;
    }
    
    public Integer getResult(){
    	return Result;
    }
    
    public boolean getReady(){
    	return ready;
    }

    public void setResult(Integer X)  {
        Result = X;
    }
    public void setReady()  {
       ready = true;
    }
    public void addCard(Cards c){
    	allCards.add(c);
    }
    public Cards getallCards(){
    	return allCards.get(0);
    }
}