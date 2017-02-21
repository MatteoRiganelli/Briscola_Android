package com.briscola.gui;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.StringTokenizer;
import java.util.concurrent.Semaphore;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.SpriteBackground;
import org.anddev.andengine.entity.shape.modifier.MoveModifier;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.sprite.TiledSprite;
import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.ui.activity.BaseGameActivity;
import org.anddev.andengine.util.modifier.ease.EaseBackIn;
import org.anddev.andengine.util.modifier.ease.EaseBackOut;

import android.util.Log;

import com.briscola.BriscolaFactory;
import com.briscola.GiocatoreBriscolaComputer;
import com.briscola.gui.globali.Global;
import com.briscola.gui.globali.Textures;
import com.cardgame.Giocatore;


public class TavoloGrafico extends Scene implements Observer {

        com.cardgame.Tavolo myBriscolaTable; //tavolo della briscola con i metodo di gioco
        Giocatore p1, p2; //giocatore 1 e 2
        TiledSprite sprite_coperto;  //carta coperta
        //le 3 carte dei giocatori
        ACard[] b1 = new ACard[3];
        ACard[] b2 = new ACard[3];
        //la carta briscola
        ACard briscola;
        //array di carde
        ArrayList<ACard> cards = new ArrayList<ACard>();
        //numero giocatori
        int giocatori;
        
        //giocatore corrente
        int current;
        //semaforo
        Semaphore mutex = new Semaphore(1, true);
        
        ChangeableText text_uno, text_due;
        //l'attività
        public BaseGameActivity activity;
        //Thread di gioco
        GameThread game;

        //costruttore che vuole numero di layer, l'attività che invoca la scena e l'id del tabolo
        public TavoloGrafico(int numLayer, BaseGameActivity activity, int tableID) {
                super(numLayer, true, 80);
                this.activity = activity;
             
                // sfondo della scena
                Sprite background_sprite = new Sprite(0, 0, Textures.background_region);
                background_sprite.setSize(Global.CAMERA_WIDTH, Global.CAMERA_HEIGHT);
                background_sprite.setZIndex(-100);
                this.setBackground(new SpriteBackground(background_sprite));
                
                //imposta i font
                text_uno = new ChangeableText(Textures.toScreenX(0), Textures.toScreenY(0),
                                Textures.puntiuno_font, "          ");
                text_uno.setZIndex(100);
                text_due = new ChangeableText(Textures.toScreenX(0),
                                Textures.toScreenY(100 - Global.card_h), Textures.puntidue_font,
                                "          ");
                text_due.setZIndex(100);
                getTopLayer().addEntity(text_due);
                getTopLayer().addEntity(text_uno);

                init();
        }
        //dai il via al thread del gioco e inizializza i principali array
        private void init() {
                game = new GameThread();
                game.start();
                initRooms();
        }
        
        //metti a null l'array delle carte
        private void initRooms() {
                b1[0] = null;
                b1[1] = null;
                b1[2] = null;
                b2[0] = null;
                b2[1] = null;
                b2[2] = null;
        }

        private class GameThread extends Thread {
          //thread del gioco
        	// il codice eseguito è inserito nel metodo run
                public void run() {
                        //crea il tavolo di gioco
                        try {
                        	   //imposta il tavolo con il suo id
                                myBriscolaTable = BriscolaFactory.buildTable();
                                //osservatore dei cambiamenti per il tavolo
                                myBriscolaTable.addObserver(TavoloGrafico.this);
                                // Visualizza il mazzo generato per la partita
                                System.out.println(myBriscolaTable.getMazzo());

                                // crea i due giocatori
                                
                                p1 = BriscolaFactory.buildComputerPlayer();
                                p2 = BriscolaFactory.buildPlayer();
                               
                                // collegamento al tavolo
                               
                                myBriscolaTable.join(p1);
                                myBriscolaTable.join(p2);
                                
                                //invoca l'inizializzatore del gioco
                                boolean gameStarted = myBriscolaTable.inizializzaGioco();
                                
                                //se l'attivazione va a  buon fine
                                if (gameStarted) {
                                        System.out.println("Gioco avviato!");
                                        myBriscolaTable.gestisciGioco();//gestisci il gioco
                                }

                        } catch (Exception re) {
                                Global.exception=re.getMessage();
                        }
                }

        }

        //metodo che restituisce l'indice della prima posizione libera dell'array
        //di ACard cche rapprsenta le carte in mano ai giocatori (ci sono 2 array uno per gicoatore1 e uno per giocatore 2)
        
        private int primaPosizioneLibera(int giocatore) {
                if (giocatore == 1) {  //se giocatore 1
                        for (int i = 0; i < b1.length; i++) { //scandisci la sua mano e 
                                if (b1[i] == null) {//appena trovi una posizione libera 
                                        return i;  //ritorna l'indice della posizione
                                }
                        }
                } else if (giocatore == 2) {  //come sopra ma riferito al giocatore 2
                        for (int i = 0; i < b2.length; i++) {
                                if (b2[i] == null) {
                                        return i;
                                }
                        }
                }
                return -1; //no posizione libere
        }

 
       //metodo che restituisce dato giocatore e posizione libera per mettere una carta
        //le coordinate dove posizionare la carta stessa
        private int[] coordinatePrimoSpazioLibero(int giocatore, int posizione) {
                int[] coo = new int[2];//array che conterrà le 2 coordinate in posizionare la carta
                if (posizione != -1) {  //se è disponibile uno spazio (metodo sopra)
                        coo[0] = Textures.toScreenX(12) + (Textures.toScreenX(25) * posizione);//la coordinata X è uguale a 12+25*posizione
                        if (giocatore == 1) {  //in tal modo posso posizionare le carte sullo schermo sulla base dell'indice
                                coo[1] = 0; //se parlo di giocatore 1 la coordinata y è 0 (infatti le sue carte su schermo stanno in fondo)
                        } else if (giocatore == 2) {//altrimenti se player 2 stanno leggermente sopra
                                coo[1] = Textures.toScreenY(70); //in particolare 70 pixel in riferimento alla telecamera
                        }
                        return coo; //ritorna l'array delle coordinate
                }
                return null; //se non ci sono posizione libere non fa nulla
        }

        //metodo che dato il giocatore, la posizione e la carta mette effettivamente
        //la carta nell'array (mano) del giocatore
        
        private void occupaPosizione(int giocatore, int posizione, ACard carta) {
                if (giocatore == 1) {
                        b1[posizione] = carta;//se giocatore 1 metti la carta nella posizione libera della sua mano
                } else if (giocatore == 2) {
                        b2[posizione] = carta; //come sopra
                }
        }

        //metodo che scarta una carta specifica dalla mano del giocatore 
        
        private void liberaPosizione(int player, ACard card) {
                if (player == 1) {//se giocatore 2
                        if (b1[0] != null && b1[0].equals(card)) //se la cella non è null e la carta corrisponde
                                b1[0] = null;//scarta la carta
                        if (b1[1] != null && b1[1].equals(card))
                                b1[1] = null;
                        if (b1[2] != null && b1[2].equals(card))
                                b1[2] = null;
                } else if (player == 2) {
                        if (b2[0] != null && b2[0].equals(card))
                                b2[0] = null;
                        if (b2[1] != null && b2[1].equals(card))
                                b2[1] = null;
                        if (b2[2] != null && b2[2].equals(card))
                                b2[2] = null;
                }
        }

        //metodi di accesso e settaggio ai campi della classe
        protected Giocatore getP1() {
                return p1;
        }

        protected Giocatore getP2() {
                return p2;
        }

        public Semaphore getMutex() {
                return mutex;
        }

        public int getCurrent() {
                return current;
        }

        //metodo richiamato quando l'oggetto osservato viene aggiornato
        public void update(Observable observable, Object data) {

                Log.e("Briscola", data.toString());
                
                if (data.toString().contains("PLAYERS:")) {
                        StringTokenizer st = new StringTokenizer(data.toString(), ":");
                        st.nextToken();
                        giocatori = Integer.parseInt(st.nextToken());
                }
                if (data.toString().contains("CURRENT:")) {
                        StringTokenizer st = new StringTokenizer(data.toString(), ":");
                        st.nextToken();
                        current = Integer.parseInt(st.nextToken());
                }
                if (data.toString().contains("SHUFFLE")) {
                        // Piazzare la carta coperta che rappresenta il mazzo
                	//oggetto di tipo tiledsprite, i paramtri sono le coordinate di posizione e grandezza
                	//e la texture
                        sprite_coperto = new TiledSprite(Textures.toScreenX(Global.x_mazzo),
                                        Textures.toScreenY(Global.y_mazzo),
                                        Textures.toScreenX(Global.card_w),
                                        Textures.toScreenY(Global.card_h), Textures.retroregion);
                        sprite_coperto.setZIndex(0);
                        getTopLayer().addEntity(sprite_coperto);
                }
                if (data.toString().contains("GIVE:")) {
                        //dare le carte
                        StringTokenizer st = new StringTokenizer(data.toString(), ":");
                        st.nextToken();
                        int giocatore = Integer.parseInt(st.nextToken());
                        st.nextToken();
                        String faccia = st.nextToken();
                        String seme = st.nextToken();
                        try {
                        	 //semaforo acquisito. un giocatore alla volta
                                mutex.acquire();
                                Thread.sleep(300);
                        } catch (InterruptedException e) {
                                e.printStackTrace();
                        }
                        //cerca il posto per mettere le carta nella mano (array del giocatore)
                        int posizione = primaPosizioneLibera(giocatore);
                        //crea la carta
                        ACard card = new ACard(Textures.toScreenX(Global.x_mazzo),
                                        Textures.toScreenY(Global.y_mazzo), Textures.getCardRegion(seme,
                                                        faccia), seme, faccia, false);
                        //se la carta è una briscola allora marcala come briscola
                        if (card.equals(briscola))
                                card = briscola;
                        // cartsa scoperta
                        boolean scoperta = true;
                                 //se il giocatore è il computer però marca la sua carta come  non giocabile
                               // e coprila
                                if (myBriscolaTable.player(giocatore) instanceof GiocatoreBriscolaComputer) {
                                                
                                        card.setPlayable(false);
                                       
                                        scoperta = false;
                                }
                        //posiziona la carta
                        occupaPosizione(giocatore, posizione, card);
                        int[] coo = coordinatePrimoSpazioLibero(giocatore, posizione);
                         
                        //se carta non briscola aggiungila
                        if (!card.equals(briscola)) {
                                addCard(card);
                        } else {//altrimenti rimuovi la carta coperta
                                getTopLayer().removeEntity(sprite_coperto);
                        }

                        card.addShapeModifier(new MoveModifier(0.3f, Textures
                                        .toScreenX(Global.x_mazzo), coo[0], Textures
                                        .toScreenY(Global.y_mazzo), coo[1], EaseBackIn
                                        .getInstance()));
                        card.scoperta(scoperta);

                        mutex.release();
                }
                if (data.toString().contains("BRISCOLA:")) {
                        StringTokenizer st = new StringTokenizer(data.toString(), ":");
                        st.nextToken();
                        String faccia = st.nextToken();
                        String seme = st.nextToken();
                        
                        //costruisci la briscola
                        briscola = new ACard(Textures.toScreenX(Global.x_mazzo),
                                        Textures.toScreenY(Global.y_mazzo), Textures.getCardRegion(seme,
                                                        faccia), seme, faccia, true);
                        briscola.setZIndex(-98);
                        addCard(briscola);
                        //posiziona la briscola  8addShapeModifier permette il movimento delle carte).
                        briscola.addShapeModifier(new MoveModifier(0.3f, Textures
                                        .toScreenX(Global.x_mazzo), Textures.toScreenX(Global.x_mazzo)
                                        + Textures.toScreenX(3 * Global.card_w / 4), Textures
                                        .toScreenY(Global.y_mazzo),
                                        Textures.toScreenY(Global.y_mazzo), EaseBackOut.getInstance()));
                }
                if (data.toString().contains("PLAY:")) {
                        try {
                        	    //gioco. blocca il semaforo
                                mutex.acquire();
                                StringTokenizer st = new StringTokenizer(data.toString(), ":");
                                st.nextToken();
                                int giocatore = Integer.parseInt(st.nextToken());
                                st.nextToken();
                                String faccia = st.nextToken();
                                String seme = st.nextToken();
                                // porta le carte vicino alle altre carte del giocatore
                                ACard tmpCard = (ACard) cards.get(cards.indexOf(new ACard(0, 0,
                                                Textures.retroregion, seme, faccia, true)));
                                liberaPosizione(giocatore, tmpCard);
                                tmpCard.scoperta(true);
                                //muovi le carte verso il centro dello schermo quando gioco
                                int a = (int) (40 + (Math.random() * 20));
                                int b = (int) (40 + (Math.random() * 20));
                                tmpCard.addShapeModifier(new MoveModifier(
                                                1f,
                                                tmpCard.getX(),
                                                Textures.toScreenX(a) + Textures.toScreenX(Global.card_w / 2),
                                                tmpCard.getY(), Textures.toScreenY(b)
                                                                - Textures.toScreenY(Global.card_h / 2),
                                                EaseBackOut.getInstance()));
                                //rilascia il semaforo
                                mutex.release();
                        } catch (Exception e) {
                                e.printStackTrace();
                        }
                }
                if (data.toString().contains("PICKUP:")) {
                        try {
                                mutex.acquire();
                                Thread.sleep(200);
                                StringTokenizer st = new StringTokenizer(data.toString(), ":");
                                st.nextToken();
                                int giocatore = Integer.parseInt(st.nextToken());
                                st.nextToken();
                                String faccia = st.nextToken();
                                String seme = st.nextToken();
                                // porta le carte vicino alle altre carte del giocatore
                                ACard tmpCard = (ACard) cards.get(cards.indexOf(new ACard(0, 0,
                                                Textures.retroregion, seme, faccia, true)));
                                tmpCard.setPlayable(false);
                                //
                                if (giocatore == 1)
                                        giocatore = 0;
                                else if (giocatore == 2)
                                        giocatore = 100 - Global.card_h;
                                mutex.release();
                                tmpCard.addShapeModifier(new MoveModifier(1f, tmpCard.getX(),
                                                Textures.toScreenX(100 - (Global.card_w / 2)), tmpCard
                                                                .getY(), Textures.toScreenY(giocatore), EaseBackOut
                                                                .getInstance()));
                                Thread.sleep(300);
                                tmpCard.scoperta(false);
                        } catch (Exception e) {
                                mutex.release();
                                e.printStackTrace();
                        }
                }// PICKUP
                if (data.toString().contains("END")) {
                        try {
                                Thread.sleep(500);
                        } catch (InterruptedException e1) {
                                e1.printStackTrace();
                        }

                        int uno = 0, due = 0, ics;
                        for (int i = 0; i < cards.size(); i++) {
                                try {
                                        mutex.acquire();
                                        ACard card = cards.get(i);
                                        if (card.getX() == 0) {
                                                uno += 2;
                                                ics = uno;
                                        } else {
                                                due += 2;
                                                ics = due;
                                        }
                                        card.setZIndex(i);
                                        card.scoperta(true);
                                        getTopLayer().sortEntities();
                                        card.addShapeModifier(new MoveModifier(1f, card.getX(),
                                                        card.getX() - Textures.toScreenX(ics), card.getY(),
                                                        card.getY(), EaseBackOut.getInstance()));
                                        Thread.sleep(100);
                                } catch (Exception e) {
                                        e.printStackTrace();
                                }
                                mutex.release();
                        }
                        
                                text_uno.setText("" + myBriscolaTable.player(1).contaPunti(),
                                                true);
                                text_due.setText("" + myBriscolaTable.player(2).contaPunti(),
                                                true);

                        
                }
                if (data.toString().contains("EXCEPTION:")) {
                        StringTokenizer st = new StringTokenizer(data.toString(), ":");
                        st.nextToken();
                        Global.exception=st.nextToken().trim();
                        
                }
                

        }

        //aggiungi la carta al tavolo regolandone le impostazioni
        private void addCard(ACard card) {
                card.setBriscolaTable(myBriscolaTable);
                card.setSize(Textures.toScreenX(Global.card_w),
                                Textures.toScreenY(Global.card_h));
                getTopLayer().addEntity(card);
                registerTouchArea(card);
                getTopLayer().sortEntities();
                cards.add(card);
        }

        //ottieni tavolo
        public com.cardgame.Tavolo getTable() {
                return myBriscolaTable;
        }

}