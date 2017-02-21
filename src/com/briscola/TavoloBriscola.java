package com.briscola;

import java.util.List;
import java.util.Observable;
import java.util.concurrent.Semaphore;

import com.cardgame.Carta;
import com.cardgame.Faccia;
import com.cardgame.Giocatore;
import com.cardgame.CreatoreMazzo;
import com.cardgame.Tavolo;

import com.cardgame.eccezioni.EccezioneMazzoVuoto;


//classe estensione del tavolo da gioco generico. implementa  in maniera piu specifica
//le mosse della briscola
public class TavoloBriscola extends Tavolo{

        private int current;//giocatore corrente
        private Carta briscola;  //carta briscola
        private Semaphore sem;  //semaforo
        private boolean primaMano;  //indica se è prima mano

        public TavoloBriscola(int players) {
                super(players); //numero di giocatori
               
                       //imposta il mazzo sul tavolo (guardare costruisciMazzo() della classe CreatoreMazzo)
                        setMazzo(CreatoreMazzo.costruisciMazzo());
                        
                        //Mischia il mazzo (Metodo delle collezioni Java)       
                        getMazzo().mescola(); 
                        
                        //inizializza un semaforo
                        sem = new Semaphore(1, true);
                        try {
                                sem.acquire();//entra nel semaforo
                        } catch (InterruptedException e) {
                                e.printStackTrace();
                        }
        }

        //inizializza il gioco
       
        public boolean inizializzaGioco() {
                //notifica il numero di giocatore (corrispondente alle posizioni)
                osservato.notifyObservers("PLAYERS:"+posizioni);
                //se ho zero posti liberi comincio il gioco
                if (getPosizioni() > 0)
                        return false;
                else {
                        //current = (int) (Math.random() * numberOfPlayers) + 1;
                        current = 2;//inizia il giocatore 2
                        
                        //per vedere cosa fa l'osservatore leggere il metodo update in Table dentro 
                        //com.briscola.gui
                        //in base alla notifica update aggiornerà il tavolo (leggere l'etichetta..PLAY,CURRENT,SHUFFLE,ecc)
                        //segnala all'osservatore che gioca il giocatore corrente
                        osservato.notifyObservers("CURRENT:"+current);
                        //notifica l'avvio della partita per mettere in tavola mazzo e briscola
                        osservato.notifyObservers("SHUFFLE");                        
                        // dai 3 carte ad ogni giocatore
                        try {
                                for (int i = 0; i < numeroDiGiocatori; i++) {
                                        Carta tempCard;
                                        tempCard=getMazzo().prendiPrimaCarta();
                                        player(current).prendiCarta(tempCard);
                                        osservato.notifyObservers("GIVE:"+current+":CARD:"+tempCard.faccia.getFace()+":"+tempCard.seme.getSeme());
                                        tempCard=getMazzo().prendiPrimaCarta();
                                        player(current).prendiCarta(tempCard);
                                        osservato.notifyObservers("GIVE:"+current+":CARD:"+tempCard.faccia.getFace()+":"+tempCard.seme.getSeme());
                                        tempCard=getMazzo().prendiPrimaCarta();
                                        player(current).prendiCarta(tempCard);
                                        osservato.notifyObservers("GIVE:"+current+":CARD:"+tempCard.faccia.getFace()+":"+tempCard.seme.getSeme());
                                        // passa al giocatore successivo per aggiornare current
                                        prossimoGiocatore();
                                }
                        } catch (EccezioneMazzoVuoto e) { //se il mazzo è vuoto segnalalo
                                e.printStackTrace();
                                return false;
                        }

                        // scegli la briscola e mettila sul tavolo
                     
                                // usa come briscola l'ultima  carta  del mazzo
                                briscola = getMazzo().ottieniCarta(getMazzo().numeroDiCarte() - 1);
                                System.out.println("Briscola stabilita: " + briscola);
                                osservato.notifyObservers("BRISCOLA:"+briscola.faccia.getFace()+":"+briscola.seme.getSeme());

                        return true;
                }
        }

        
        //passa dal giocatore corrente al prossimo giocatore cui tocca la mossa 
        //segnala la modifica all'osservatore
        private void prossimoGiocatore() {
        	    //se il giocatore corrente non è l'ultimo
        	  //passa al prossimo
                if (current < numeroDiGiocatori)
                        current++;
                else
                	//altrimenti ritocca al primo
                        current = 1;
                osservato.notifyObservers("CURRENT:"+current);
        }

        @Override
        public boolean gestisciGioco() {
                // clausola d'uscita
                primaMano = true;//all'inizio è prima 
                int countDown = numeroDiGiocatori * 3;
                try {
                       
                        while (!giocoFinito) {
                                if (!primaMano) {
                                        // dai a ogni giocatore una carta dal mazzo
                                        for (int i = 0; i < numeroDiGiocatori; i++) {
                                                
                                                try {
                                                        Carta tempCard = getMazzo().prendiPrimaCarta();
                                                        player(current).prendiCarta(tempCard);
                                                        osservato.notifyObservers("GIVE:"+current+":CARD:"+tempCard.faccia.getFace()+":"+tempCard.seme.getSeme());
                                                } catch (EccezioneMazzoVuoto e) {
                                                        System.out.println("Eccezione "+countDown+" "+current+" ha le carte: "+player(current).getCards());
                                                        if (countDown > 0) {
                                                                countDown--;
                                                        }
                                                        if(countDown == numeroDiGiocatori){
                                                                giocoFinito = true;
                                                                //break;
                                                        }
                                                }
                                                prossimoGiocatore();
                                        }
                                }
                                
                                // ogni giocatore deve giocar una carta
                                for (int i = 0; i < numeroDiGiocatori; i++) {
                                        player(current).notificaTurno();
                                        sem.acquire();
                                        prossimoGiocatore();
                                        if(primaMano){
                                                primaMano = false;
                                        }
                                }
                                
                                
                                //chi è di mano attualmente
                                current = checkCurrentHand(current, cartesultavolo);
                      
                                
                                // rimuovi le carte dal tavolo e dalle al giocatore di mano
                                Carta[] actualPoints = new Carta[numeroDiGiocatori];//carte vinte nella mano
                                cartesultavolo.toArray(actualPoints);
                                cartesultavolo.removeAll(cartesultavolo);
                                for (int i = 0; i < actualPoints.length; i++) {
                                        player(current).ottieniPunti(actualPoints[i]);
                                        osservato.notifyObservers("PICKUP:"+current+":CARD:"+actualPoints[i].faccia.getFace()+":"+actualPoints[i].seme.getSeme());                                   
                                }
                        }
                        
                        osservato.notifyObservers("END");
                        for(Giocatore pl : giocatori){
                                GiocatoreBriscola brpl = (GiocatoreBriscola)pl;
                                osservato.notifyObservers("TOTAL:"+current+":POINTS:"+brpl.contaPunti());
                                prossimoGiocatore();
                        }
                }catch (InterruptedException e) {
                        e.printStackTrace();
                }
                return true;
        }

        //indica se la mano giocata è la prima o no
        protected boolean isFirstHand() {
                return primaMano;
        }
//controlla i 3 casi di chi gioca.
        //1 nessuna delle carte giocate è briscola. chiamo il metodo supera per vedere chi prende
        //2 una cvarta è briscola e l'altra no. vince chi ha la briscola
        //33 2 briscole. chiamo supera per vedere chi prende
        private int checkCurrentHand(int curr, List<Carta> tab) {
                int isTaking = current;
                Carta withCard = tab.get(0);
                for (int i = 1; i < numeroDiGiocatori; i++) {                     
                        prossimoGiocatore();
                        Carta newCard = tab.get(i);
                        //is withCard Briscola
                        if(withCard.seme.equals(briscola.seme)){
                                if(newCard.seme.equals(briscola.seme)){
                                        if(supera(newCard.faccia,withCard.faccia)){
                                                withCard = newCard;
                                                isTaking = current;
                                        }
                                }
                        }
                        else{
                                //withCard not briscola
                                if(newCard.seme.equals(briscola.seme)){
                                        withCard = newCard;
                                        isTaking = current;
                                }
                                else{
                                        //none of them is briscola                                      
                                        if(supera(newCard.faccia,withCard.faccia)&&newCard.seme.equals(withCard.seme)){
                                                withCard = newCard;
                                                isTaking = current;
                                        }
                                }
                        }
                }
                return isTaking;
        }
        
        //metodo che verifica la carta che vince la mano
        public static Carta checkHand(List<Carta> mano, Carta briscola){
                Carta withCard = mano.get(0);
                for (int i = 1; i < mano.size(); i++) {                 
                        Carta newCard = mano.get(i);
                        //is withCard Briscola
                        if(withCard.seme.equals(briscola.seme)){
                                if(newCard.seme.equals(briscola.seme)){
                                        if(supera(newCard.faccia,withCard.faccia)){
                                                withCard = newCard;
                                        }
                                }
                        }
                        else{
                                //withCard not briscola
                                if(newCard.seme.equals(briscola.seme)){
                                        withCard = newCard;
                                }
                                else{
                                        //none of them is briscola                                      
                                        if(supera(newCard.faccia,withCard.faccia)&&newCard.seme.equals(withCard.seme)){
                                                withCard = newCard;
                                        }
                                }
                        }
                }
                return withCard;
        }

         //metodo per verificare quale delle due carte giocate è superiore all'altra
        //se nessuna è briscola vicne quella piu alta
        //se è una è briscola e una no il metodo non viene invocato
        //se sono entrambe briscole vince la piu alta
        public static boolean supera(Faccia face, Faccia face2) {
                int facePoints = 0, face2Points = 0; //dò un punto  2 carte giocate
                //quella col punteggio maggiore vince sull'altra
                
                //l'asso vale piu di tutti..poi in sequenza le altre
                //confronto la carta e vedo che carta e le attribuisco un punteggio
                if(face.getFace().equalsIgnoreCase("ASSO"))facePoints = 10; 
                else if(face.getFace().equalsIgnoreCase("TRE"))facePoints = 9;
                else if(face.getFace().equalsIgnoreCase("RE"))facePoints = 8;
                else if(face.getFace().equalsIgnoreCase("CAVALLO"))facePoints = 7;
                else if(face.getFace().equalsIgnoreCase("DONNA"))facePoints = 6;
                else if(face.getFace().equalsIgnoreCase("SETTE"))facePoints = 5;
                else if(face.getFace().equalsIgnoreCase("SEI"))facePoints = 4;
                else if(face.getFace().equalsIgnoreCase("CINQUE"))facePoints = 3;
                else if(face.getFace().equalsIgnoreCase("QUATTRO"))facePoints = 2;
                else if(face.getFace().equalsIgnoreCase("DUE"))facePoints = 1;
                
                //stessa cosa per la carta 2
                if(face2.getFace().equalsIgnoreCase("ASSO"))face2Points = 10;
                else if(face2.getFace().equalsIgnoreCase("TRE"))face2Points = 9;
                else if(face2.getFace().equalsIgnoreCase("RE"))face2Points = 8;
                else if(face2.getFace().equalsIgnoreCase("CAVALLO"))face2Points = 7;
                else if(face2.getFace().equalsIgnoreCase("DONNA"))face2Points = 6;
                else if(face2.getFace().equalsIgnoreCase("SETTE"))face2Points = 5;
                else if(face2.getFace().equalsIgnoreCase("SEI"))face2Points = 4;
                else if(face2.getFace().equalsIgnoreCase("CINQUE"))face2Points = 3;
                else if(face2.getFace().equalsIgnoreCase("QUATTRO"))face2Points = 2;
                else if(face2.getFace().equalsIgnoreCase("DUE"))face2Points = 1;
                
                //restituisco la carta che risulta vincente
                return facePoints>face2Points;
        }

        //metodo che riguarda l'azione di giocare una carta card da parte del giocatore id
        @Override
        public void gioca(int id, Carta card) {
                try{
                	    //è il turno del giocatore con id specificato
                        System.out.println("Risulta il turno di "+id+"? "+player(id).isMyTurn());
                        
                  //se è il turno del giocatore id ed è possibile giocare carte in tavola     
                if(player(id).isMyTurn() && cartesultavolo.add(card)){
                                //metto il mio turno a false 
                                player(id).setMioTurno(false);
                                //rimuovi una carta dalle sue carte (quella che viene giocata)
                                player(id).getCards().remove(card);
                        //visualizza le carte in tavola
                        System.out.println("Carte sul tavolo: " + cartesultavolo);
                        
                        //avvisa l'osservatore dell'azione di gioco (sul log apparirà che giocatore id ha giocato una certa carta)
                        osservato.notifyObservers("PLAY:"+id+":CARD:"+card.faccia.getFace()+":"+card.seme.getSeme());
                        
                        //rilascia il semaforo (cosi l'altro giocatore può entrare)
                        sem.release();
                }
                }catch(Exception e){e.printStackTrace();}
        }

        //restituisci l'oggetto sorgente (l'oggetto osservato)
        public Observable getSource() {
                return osservato;
        }
        
    //ritorna il giocatore corrente
        public int getCurrent() {
                return current;
        }
      
          //ritorna la briscola
        public Carta getBriscola() {
                return briscola;
        }

}