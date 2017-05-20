package LottoMVC;

import java.util.ArrayList;
import java.util.Collections;

import javafx.collections.ObservableList;

public class LottoModel {
	
	public ArrayList<Integer> luckyNumbers;
	final int MAXNR = 42;
	final int MAXZUSATZ = 6;
	final int MAXCHOICE = 6;
	final int MINRICHTIGE = 0;
	
	Integer[] treffer;
	Integer[] gewinne;
	
	
	public LottoModel(){
		luckyNumbers = new ArrayList<Integer>();
		treffer = new Integer[(MAXCHOICE+1)*2];
		gewinne = new Integer[(MAXCHOICE+1)*2];
		for(int i = 0; i < treffer.length; i++) {
            treffer[i] = new Integer(0);
        }
		for(int i = 0; i < gewinne.length; i++) {
            gewinne[i] = new Integer((int) Math.pow(i, 3));
            System.out.println(gewinne[i]);
        }
		
	}
	
	public void generateLuckyNumbers(){
		int random;
		boolean added;
		for(int i = 0; i<MAXCHOICE;i++){
			added = false;
			while(added==false){
				random = (int)(Math.random() * MAXNR + 1);
				if(luckyNumbers.contains(random)==false){
					luckyNumbers.add(random);
					added = true;
				}
			}
		}
		
		random = (int)(Math.random() * MAXZUSATZ+1)*-1;
		luckyNumbers.add(random);
		Collections.sort(luckyNumbers);
	}
	
	public void compareNumbers(ObservableList<ArrayList<Integer>> completeList){
		int anzRichtige=0;
		boolean zusatzOK = false;
		
		for(ArrayList<Integer> list:completeList){
			anzRichtige = 0;
			zusatzOK = false;
			 for(int i = 0;i<list.size();i++){
				 if(luckyNumbers.contains(list.get(i).intValue())){
					 if(list.get(i).intValue()<0)
						 zusatzOK = true;
					 else
						 anzRichtige++;
				 }
			 }
			 
			 System.out.println("AnzRichtige: "+anzRichtige+"  "+zusatzOK);
			 
			 if(zusatzOK){
				 treffer[anzRichtige*2+1] = treffer[anzRichtige*2+1]+1;
			 }else{
				 treffer[anzRichtige*2] = treffer[anzRichtige*2]+1;
			 }
			 
		}
		
		
	}
	
	

}
