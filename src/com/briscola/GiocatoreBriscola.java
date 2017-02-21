package com.briscola;

import java.util.List;

import com.cardgame.Carta;
import com.cardgame.Giocatore;
import com.cardgame.Tavolo;


//classe che discende da Giocatore (generico)
public class GiocatoreBriscola extends Giocatore {
     

        public GiocatoreBriscola() {          
        }
        
        //setta tavolo di gioco
        public void setTable(Tavolo table) {
                super.setTable(table);
        }
        
        //indica che è il turno di giocare
        public void notificaTurno() {
                setMioTurno(true); //metto a true 
        }
        
        
        //conta i punti
        public int contaPunti(){
                int totale = 0;
                for(Carta c : punti){
                        if(c.faccia.getFace().equalsIgnoreCase("ASSO"))totale+=11;
                        else if(c.faccia.getFace().equalsIgnoreCase("TRE"))totale+=10;
                        else if(c.faccia.getFace().equalsIgnoreCase("RE")) totale+=4;
                        else if(c.faccia.getFace().equalsIgnoreCase("CAVALLO")) totale+=3;
                        else if(c.faccia.getFace().equalsIgnoreCase("DONNA")) totale+=2;
                }
                return totale;
        }
        
        //conta i punti ottenuti cone le carte vinte
        public static int contaPunti(List<Carta> points){
                int totale = 0;
                for(Carta c : points){
                        if(c.faccia.getFace().equalsIgnoreCase("ASSO"))totale+=11;
                        else if(c.faccia.getFace().equalsIgnoreCase("TRE"))totale+=10;
                        else if(c.faccia.getFace().equalsIgnoreCase("RE")) totale+=4;
                        else if(c.faccia.getFace().equalsIgnoreCase("CAVALLO")) totale+=3;
                        else if(c.faccia.getFace().equalsIgnoreCase("DONNA")) totale+=2;
                }
                return totale;
        }

        

}