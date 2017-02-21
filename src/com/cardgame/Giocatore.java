package com.cardgame;

import java.util.ArrayList;
import java.util.List;

//Classe che rappresenta il giocatore. tale classe è piu generica rispetto
//alle specifiche classi Giocatorebriscola e GiocatoreBriscolaComputer
public abstract class Giocatore {
	
        protected Tavolo table; //tavolo di gioco
        protected int id;  //id dei giocatori
        protected List<Carta> carte;  //Carte dei giocatori
        protected List<Carta> punti; //punti dei giocatori
        protected int idTable;  //id del tavolo di gioco
        protected boolean mioTurno = false;  //variabile che indica se è il turno di un giocatore

        //costruttore. Inizializza 2 arraylist. un che conterrà le carte del giocatore
        //l'altro arraylist serve per gestire i punteggi dei due giocatore
        //utile nel caso in cui volessi aumentare il numero dei giocatori
        public Giocatore() {
                carte = new ArrayList<Carta>();
                punti = new ArrayList<Carta>();
        }

        //setta l'id del giocatore
        public void setId(int id) {
                this.id = id;
        }

        //ottieni l'id del giocatore
        public int getId() {
                return id;
        }

        //imposta il tavolo di gioco
        public void setTable(Tavolo table) {
                this.table = table;
        }
        
        //prendi una carta
        public void prendiCarta(Carta carta){
                carte.add(carta);
                System.out.println("Player "+id+" riceve "+carta);
        }
        
        //ottieni carte di un giocatore
        public List<Carta> getCards() {
                return carte;
        }
        
        //invocato dal giocatore per stabilire il suo turno. verrò implementato nelle sottoclassi
        public abstract void notificaTurno();

        //aggiungi i punti
        public void ottieniPunti(Carta point) {
                System.out.println("Player "+id+" ha ricevuto la carta "+point+" come punto");
                punti.add(point);
        }
        
        public abstract int contaPunti();
        
        //ritorna array punti di un giocatore
        public List<Carta> points() {
                return punti;
        }
        
        //ottieni l'id del tavolo 
        public int getIdTable() {
                return idTable;
        }
  
        //setta l'id del tavolo
        public void setIdTable(int idTable) {
                this.idTable = idTable;
        }

        //verifica se è il turno di un dato giocatore
        public boolean isMyTurn() {
                return mioTurno;
        }

        //setta la variabile mioturno in base al valore del parametro
        //invoco questo metodo all'inizio di ogni turno e alla fine 
        //quanto tocca al giocatore setta a true quando ha finito il turno setta a false
        public void setMioTurno(boolean t) {
                mioTurno = t;
        }
}
