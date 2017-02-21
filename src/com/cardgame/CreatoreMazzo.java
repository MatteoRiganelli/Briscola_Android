package com.cardgame;


//Classe che contiene il metodo che permette la creazione del Mazzo di carte
//sfruttande le 2 classi Carta e PiacentineDeck

public class CreatoreMazzo {
	
        //Crea il mazzo di carte e restituiscilo
	
        public static Mazzo costruisciMazzo() {
        	
                Mazzo mazzo = new Mazzo();
                //aggiungi le carte al mazzo (mazzo.pushCard)
                //il metodo richiede in input la carta piacentina (new Carta(new..))
                //la quale a sua volta richiede in input il seme e la faccia (new Seme("seme"), new 
                // Faccia("CAVALLO");
                mazzo.mettiCarta(new Carta(new Seme("DENARI"), new Faccia("ASSO")));
                mazzo.mettiCarta(new Carta(new Seme("DENARI"), new Faccia("DUE")));
                mazzo.mettiCarta(new Carta(new Seme("DENARI"), new Faccia("TRE")));
                mazzo.mettiCarta(new Carta(new Seme("DENARI"), new Faccia("QUATTRO")));
                mazzo.mettiCarta(new Carta(new Seme("DENARI"), new Faccia("CINQUE")));
                mazzo.mettiCarta(new Carta(new Seme("DENARI"), new Faccia("SEI")));
                mazzo.mettiCarta(new Carta(new Seme("DENARI"), new Faccia("SETTE")));
                mazzo.mettiCarta(new Carta(new Seme("DENARI"), new Faccia("DONNA")));
                mazzo.mettiCarta(new Carta(new Seme("DENARI"), new Faccia("CAVALLO")));
                mazzo.mettiCarta(new Carta(new Seme("DENARI"), new Faccia("RE")));
                mazzo.mettiCarta(new Carta(new Seme("SPADE"), new Faccia("ASSO")));
                mazzo.mettiCarta(new Carta(new Seme("SPADE"), new Faccia("DUE")));
                mazzo.mettiCarta(new Carta(new Seme("SPADE"), new Faccia("TRE")));
                mazzo.mettiCarta(new Carta(new Seme("SPADE"), new Faccia("QUATTRO")));
                mazzo.mettiCarta(new Carta(new Seme("SPADE"), new Faccia("CINQUE")));
                mazzo.mettiCarta(new Carta(new Seme("SPADE"), new Faccia("SEI")));
                mazzo.mettiCarta(new Carta(new Seme("SPADE"), new Faccia("SETTE")));
                mazzo.mettiCarta(new Carta(new Seme("SPADE"), new Faccia("DONNA")));
                mazzo.mettiCarta(new Carta(new Seme("SPADE"), new Faccia("CAVALLO")));
                mazzo.mettiCarta(new Carta(new Seme("SPADE"), new Faccia("RE")));
                mazzo.mettiCarta(new Carta(new Seme("BASTONI"), new Faccia("ASSO")));
                mazzo.mettiCarta(new Carta(new Seme("BASTONI"), new Faccia("DUE")));
                mazzo.mettiCarta(new Carta(new Seme("BASTONI"), new Faccia("TRE")));
                mazzo.mettiCarta(new Carta(new Seme("BASTONI"), new Faccia("QUATTRO")));
                mazzo.mettiCarta(new Carta(new Seme("BASTONI"), new Faccia("CINQUE")));
                mazzo.mettiCarta(new Carta(new Seme("BASTONI"), new Faccia("SEI")));
                mazzo.mettiCarta(new Carta(new Seme("BASTONI"), new Faccia("SETTE")));
                mazzo.mettiCarta(new Carta(new Seme("BASTONI"), new Faccia("DONNA")));
                mazzo.mettiCarta(new Carta(new Seme("BASTONI"), new Faccia("CAVALLO")));
                mazzo.mettiCarta(new Carta(new Seme("BASTONI"), new Faccia("RE")));
                mazzo.mettiCarta(new Carta(new Seme("COPPE"), new Faccia("ASSO")));
                mazzo.mettiCarta(new Carta(new Seme("COPPE"), new Faccia("DUE")));
                mazzo.mettiCarta(new Carta(new Seme("COPPE"), new Faccia("TRE")));
                mazzo.mettiCarta(new Carta(new Seme("COPPE"), new Faccia("QUATTRO")));
                mazzo.mettiCarta(new Carta(new Seme("COPPE"), new Faccia("CINQUE")));
                mazzo.mettiCarta(new Carta(new Seme("COPPE"), new Faccia("SEI")));
                mazzo.mettiCarta(new Carta(new Seme("COPPE"), new Faccia("SETTE")));
                mazzo.mettiCarta(new Carta(new Seme("COPPE"), new Faccia("DONNA")));
                mazzo.mettiCarta(new Carta(new Seme("COPPE"), new Faccia("CAVALLO")));
                mazzo.mettiCarta(new Carta(new Seme("COPPE"), new Faccia("RE")));
                
                return mazzo;
        }
}