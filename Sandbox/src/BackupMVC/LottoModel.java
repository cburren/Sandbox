package BackupMVC;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

import OtherClasses.ServiceLocator;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;

public class LottoModel {
	
	ServiceLocator serviceLocator;
	
	final int MAXNR = 35;
	final int MAXZUSATZ = 6;
	final int MAXCHOICE = 6;
	final int MINRICHTIGE = 0;
	final int tipPrice = 15;
	final int MAXTIP = 10;
	
	public int bet = 0;
	
	public int gewinn = 0;
	
	public ArrayList<Integer> luckyNumbers;
	Integer[] treffer;
	Integer[] gewinne;
	BigInteger[] wahr;
	

	public LottoModel(){
		
		serviceLocator = ServiceLocator.getServiceLocator();
		
		
		luckyNumbers = new ArrayList<Integer>();
		wahr = new BigInteger[(MAXCHOICE+1)*2];
		calcWahr();
		treffer = new Integer[(MAXCHOICE+1)*2];
		gewinne = new Integer[(MAXCHOICE+1)*2];
		for(int i = 0; i < treffer.length; i++) {
            treffer[i] = new Integer(0);
        }
		for(int i = 0; i < gewinne.length; i++) {
            gewinne[i] = new Integer((int) Math.pow(i, 5)*i/10/gewinne.length);
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
	
	public BigInteger calulateFak(int anz){
		
		BigInteger result = new BigInteger("1");
		BigInteger temp = new BigInteger("1");
		
		for(int i = 1; i<=anz;i++){
			temp = temp.valueOf(i);
			result = result.multiply(temp);
		}
		return result;
		
	}
	
	public void calcWahr(){
		for(int i = 0; i<=MAXCHOICE; i++){
			
			//= 49! : (6! * (49-6)!) 
			BigInteger fakCHOICE = new BigInteger(""+calulateFak(i));
			BigInteger fakMAXNR = new BigInteger(""+calulateFak(MAXNR));
			BigInteger fakSub = new BigInteger(""+calulateFak(MAXNR-i));
			BigInteger var2 = new BigInteger(""+fakCHOICE.multiply(fakSub));
	
			BigInteger wahrAll = new BigInteger(""+fakMAXNR.divide(var2));
			
			BigInteger tempInt = new BigInteger(""+MAXZUSATZ);
			
			BigInteger wahrZusatz = new BigInteger(""+wahrAll.multiply(tempInt));
			
			wahr[i*2] = wahrAll;
			wahr[i*2+1] = wahrZusatz;	
		}
		
	}
	
	

}
