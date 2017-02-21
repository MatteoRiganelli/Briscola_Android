package com.briscola;

import com.cardgame.Giocatore;
import com.cardgame.Tavolo;


public class BriscolaFactory {
   //costruttore tavolino. passare direttamente i parametri
        public static Tavolo buildTable()  {
                Tavolo table = new TavoloBriscola(2);
                return table;
                
        }
        
        //istanzia un giocatore
        public static Giocatore buildPlayer(){
                Giocatore player = new GiocatoreBriscola();
                return player;
        }

        //istanzia un giocatore pc
        public static Giocatore buildComputerPlayer() {
                Giocatore player = new GiocatoreBriscolaComputer();
                return player;
        }

}