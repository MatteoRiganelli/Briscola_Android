package com.cardgame;

import java.util.ArrayList;
import java.util.List;

import com.cardgame.eccezioni.EccezioneMazzoVuoto;

//classe che rappresenta il mazzo di carte

public  class Mazzo {
         
	    //lista di carte
        protected List<Carta> carte;

        //il mazzo è un arraylist di carte
        public Mazzo(){
            carte = new ArrayList<Carta>();
        }
        
        //metodo per mescolare il mazzo di carte
        public void mescola()  {
                if(carte instanceof List<?>)
                        java.util.Collections.shuffle((List<Carta>)carte);       
        }
        
        //descrizione oggetto carta
        public String toString(){
                return carte.toString();
        }
        
        //metodo per aggiungere la carta al mazzo
        public void mettiCarta(Carta toPush){
                carte.add(toPush);
        }
        
        
        //pesca la prima carta del mazzo (viene usato all0inizio per 3 volte poi 1) 
        public Carta prendiPrimaCarta() throws EccezioneMazzoVuoto{
                if(!carte.isEmpty()){
                        return carte.remove(0);//prendi carta alla posizione 0 dell'arraylist 
                } //la prima insomma
                throw new EccezioneMazzoVuoto();
        }
        
        //numero di carte nel mazzo
        public int numeroDiCarte(){
                return carte.size();
        }
        
        //ottieni la carta di cui specifico la posizione  (usato quando devo pescare
        //l'ultima carta da usare come briscola
        public Carta ottieniCarta(int posizione){          
              return carte.get(posizione);
                
        }

        //ottieni le carte
        public List<Carta> getCarte() {
                return carte;
        }

}
