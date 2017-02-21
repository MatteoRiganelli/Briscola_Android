package com.briscola;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.cardgame.Carta;

public class GiocatoreBriscolaComputer extends GiocatoreBriscola {

        public GiocatoreBriscolaComputer() {
        }
       
        @Override
        //quando è il turno del pc 
        public void notificaTurno() {
                try {
                  
                setMioTurno(true);// è il mio turno
                System.out.println("E' il turno di Player "+id+" "+carte+" Punti: "+ punti);
                Thread.sleep(50);
               
                int[] pot = new int[carte.size()]; //array lungo quanto quello delle carte
                int i = 0;
                //per ogni mia carta
                for(Carta card : carte){
                        if(table.carteSulTavolo().size()==0){//0 carte in tavola quindi la prima mossa è mia
                                pot[i]=120;//punti di partenza 120
                                int opponent = 1; //id avversario 1
                                        List<Carta> opp = table.player(opponent).getCards(); // ottengo carte avversario
                                        //per ogni carta del mio avversario
                                        for(Carta oppCard : opp){
                                        	    //creo arraylist dove mettere le 2 carte che sto scandendo con i cicli
                                                ArrayList<Carta> mano = new ArrayList<Carta>();
                                                mano.add(card);
                                                mano.add(oppCard);
                                                
                                                Carta taking = TavoloBriscola.checkHand(mano, ((TavoloBriscola)table).getBriscola());
                                                if(taking.equals(oppCard)&&oppCard.seme.equals(((TavoloBriscola)table).getBriscola().seme)){
                                                        mano.remove(oppCard);
                                                }
                                                if(taking.equals(card)){
                                                     if(card.seme.equals(((TavoloBriscola)table).getBriscola().seme))
                                                        mano.remove(card);
                                                }
                                                int point = GiocatoreBriscola.contaPunti(mano);
                                                if(taking.equals(oppCard)){point=point*(-1);}
                                               
                                                if(point<pot[i])
                                                	pot[i]=point;
                                        }
                                
                        }
                        else{//se non sono di turno io rispondi. effettua un confronto
                                //con la carta buttata dal giocatore avversario
                                ArrayList<Carta> inTavola = new ArrayList<Carta>(table.carteSulTavolo());
                                inTavola.add(card);
                                Carta taking = TavoloBriscola.checkHand(inTavola,((TavoloBriscola)table).getBriscola());
                                if(taking.equals(card)){
                                        
                                        if(card.seme.equals(((TavoloBriscola)table).getBriscola().seme)) 
                                        	inTavola.remove(card);//rimuovi la carta
                                        pot[i] = GiocatoreBriscola.contaPunti(inTavola);
                                }
                                else{
                                        pot[i] = GiocatoreBriscola.contaPunti(inTavola)*-1;
                                }
                        }
                        
                        if(card.seme.equals(((TavoloBriscola)table).getBriscola().seme)){
                                //se hai una briscola in mano -4                                        
                                //se hai due briscole in mano -2
                                
                        	//conta le briscole in mano
                                int briscolaCount = 0;
                                for(int z = 0; z<carte.size(); z++){
                                        if(carte.get(z).seme.equals(((TavoloBriscola)table).getBriscola().seme)){
                                                briscolaCount++;
                                        }
                                }
                                if(briscolaCount<=1){
                                        //una briscola
                                        pot[i]=pot[i]-4;
                                } else if(briscolaCount>1){
                                        pot[i]=pot[i]-2;
                                }
                        }
             
                i++; 
                }
                //controlla i punti delle varie carte e scebgli quella col punteggio piu alto.
                int j = 0; int max = -120; int chos = 0;
                for(int x : pot){
                        if(x>max){chos = j; max=x;}
                        j++;
                }
                Log.e("IA","Il miglior punteggio e' "+max+", sceglie quindi di giocare la carta "+carte.get(chos));
                table.gioca(id,carte.remove(chos));//fai la tua mossa
                } catch (Exception e) {
                        table.gioca(id,carte.remove(0));
                }
        }

}
