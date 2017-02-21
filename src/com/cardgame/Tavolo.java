package com.cardgame;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
//Il tavolo nel nostro gioco è rappresentato da 2 classi: tavolo (e la classe figlia tavolobriscola) eche 
//gestiscolo il lato logico del gioco (regole, mosse) mentre la classe tavolografico si occupa solo di
//implementare dal lato grafico le varie mosse 
public abstract class Tavolo {
        
        protected int numeroDiGiocatori; //numero di giocatori
        protected boolean giocoFinito;  //variabile booleana che indica se la partita è finita o no
        protected List<Carta> cartesultavolo; //lista che contiene le carte giocate in tavola
        protected int posizioni; //posti occupati dai giocatori
        private Mazzo mazzo; //il mazzo
        protected List<Giocatore> giocatori; //lista dei giocatori
        protected Observable osservato;  //oggetto osservabile
        
        
        public Tavolo(int nPlayers) {
                osservato = new TableObservable(); //l'oggetto osserbabile è di classe tableObservable (definita in fondo)
                this.numeroDiGiocatori = nPlayers; 
                this.posizioni = nPlayers;               
                giocatori = new ArrayList<Giocatore>();
                giocoFinito = false;
                cartesultavolo = new ArrayList<Carta>();
        }
        
        //attiva l'osservatore
        public void addObserver(Observer o){
                osservato.addObserver(o);
        }
        
        //il giocatore viene collegato al tavolo
        public int join(Giocatore p1) {
                        int id = numeroDiGiocatori - posizioni + 1;//id casuale
                        posizioni--;//sottraggo uno spazio disponibile al tavolo
                        giocatori.add(p1); //aggiungo un giocatore all'elenco
                        p1.setId(id); //imposto il l'id del giocatore
                        p1.setTable(this); //imposto il tavolo del giocatore (this perchè è quello corrente)
                        return id;   //return id     
        }

        //ritorna il numero di giocatori
        public int getPlayers() {
                return numeroDiGiocatori;
        }

       //restituisci il mazzo
        public Mazzo getMazzo() {
                return mazzo;
        }
        
        //imposta il mazzo in base a quello che viene passato come parametro
        protected void setMazzo(Mazzo deck) {
                this.mazzo = deck;
        }
        
        //ritorna i posti dai giocatori al tavolo
        public int getPosizioni() {
                return posizioni;
        }
         
        //ritorna le carte in tavola
        public List<Carta> carteSulTavolo() {
                return cartesultavolo;
        }


        //inizializza il gioco (verrà definito nella sottoclasse BriscolaTable)
        public abstract boolean inizializzaGioco();
        //gestisci le fasi di gioco (verrà definito in BriscolaTable)
        public abstract boolean gestisciGioco();
        
        //gioca (verrà definito in BriscolaTable)
        public abstract void gioca(int id, Carta card);

        //verifica se il gioco è finito o no
        public boolean isGiocoFinito(){
                return giocoFinito;
        }
        
        //imposta se il gioco è finito o no
        public void setGiocoFinito(boolean finished){
                this.giocoFinito = finished;
        }
        
        
        //ritorna indice  giocatore di cui ho l'id
        public Giocatore player(int id) {
                int i = 0;
                while(i<giocatori.size()){
                        if(giocatori.get(i).getId()==id)//se l'id del giocatore corrisponde
                        	return giocatori.get(i); //ritorna la posizione nell'array
                        i++;
                }
				return null;        
        }
        
        
        //Estendo la classe Observable in modo da impostare il tavolo come oggetto di cui tracciare i cambiamenti
        private class TableObservable extends Observable{
                //metodo per notificare i camhiamenti avvenuti al tavolo e reagire di conseguenza
                public void notifyObservers(Object data){
                        this.setChanged();//notifico cambaimenti al tavolo 
                        super.notifyObservers(data);
                }
        }
        
}
