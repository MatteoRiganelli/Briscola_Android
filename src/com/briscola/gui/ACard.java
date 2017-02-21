package com.briscola.gui;

import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

import com.briscola.TavoloBriscola;
import com.cardgame.Faccia;
import com.cardgame.Seme;
import com.cardgame.Carta;

//classe che rappresenta la carta (oggetto grafico con varie propriet�) intesa come oggetto grafico animato
//la classe Carta definita nell'altro package rappresenta la carta come oggetto logico

public class ACard extends AnimatedSprite {
        
	  
        private String seme, faccia; //seme della carta e valore
        private boolean scoperta, giocabile; //indica se � coperta o no, indica se � giocabile o no
        private com.cardgame.Tavolo myBriscolaTable; //riferimento al tavolo di gioco

        public ACard(float pX, float pY, TiledTextureRegion pTiledTextureRegion, String seme, String faccia, boolean scoperta) {
                super(pX, pY, pTiledTextureRegion); //attributi della sopraclasse(posizione x e y e texture)
                //posizione su x, y e texture dell'oggetto
                this.seme = seme;
                this.faccia = faccia;
                scoperta(scoperta);
                giocabile = true;//giocabile
        }
        
        
        public void scoperta(boolean scoperta) {
                this.scoperta = scoperta;//assegna alla carta corrente il valore passato come parametro
                if(scoperta)//se la carta � scoperta
                	this.setCurrentTileIndex(0); //giocatore.assegna alla carta tileindex 0 
                //cio� dai alla carta  l'index 0 della texture che indica di prendere l'immagine scoperta della texture
                else 
                	this.setCurrentTileIndex(1); //android.index (risulter� coperta  la carta del pc)
                //infatti index 1 corrisponde alla parte coperta della carta
        }
        
        //indica se la carta � coperta o scoperta
        public boolean isScoperta() {
                return scoperta;
        }

        //verifica se l'oggetto passato come parametro �  uguale all'oggetto corrente (acard)
        //� un sistema per vedere se un oggetto � uguale a un altro e si usa quando == non funziona
        //quando si lavora con arraylist gli oggetti da mettere dentro devono sovrascrivere il metodo equals
        //"Core Java 2 Volume 2"
        
        @Override
        public boolean equals(Object o){
                if(o instanceof ACard){
                        ACard comp = (ACard)o;
                        if(seme.equalsIgnoreCase(comp.getSeme())&&faccia.equalsIgnoreCase(comp.getFaccia()))
                                return true;
                }
                return false;
        }

        //ritorna il valore della faccia
        private String getFaccia() {
                return faccia;
        }
     
        //ritorna il seme della carta
        private String getSeme() {
                return seme;
        }
        
        //ritorna il puntatore al tavolo di gioco
        public com.cardgame.Tavolo getBriscolaTable() {
                return myBriscolaTable;
        }
  
        //setta il tavolo di gioco
        public void setBriscolaTable(com.cardgame.Tavolo myBriscolaTable) {
                this.myBriscolaTable = myBriscolaTable;
        }

        //ritorna se una carta � giocabile o no
        public boolean isPlayable() {
                return giocabile;
        }
  
        //setta la giocabilit� di una carta
        public void setPlayable(boolean playable) {
                this.giocabile = playable;
        }

        //Ascoltatore evento clic sulla carta
        @Override
        public boolean onAreaTouched(TouchEvent event, float x, float y){
                if(isPlayable()) { //se la carta � giocabile
                        if(event.getAction()==(TouchEvent.ACTION_DOWN)){
                                ;
                        }
                        else if(event.getAction()==(TouchEvent.ACTION_UP)){
                                if(this.contains(event.getX(), event.getY())){
                                        //se l'area toccata � la carta
                                        TavoloBriscola tab = (TavoloBriscola)myBriscolaTable;
                                        //creo la variabile che fa riferimento al tavolo
                                        //creo  una variabile di tipo carta che contiene la carta premuta
                                                Carta card = new Carta(new Seme(
                                                                getSeme()), new Faccia(
                                                                getFaccia()));
                                                int current = tab.getCurrent(); //rileva quale due 2 giocatori � di mano
                                                if (myBriscolaTable.player(current).getCards()
                                                                .contains(card)) //se la carta premuta � del giocatore
                                                	{
                                                        myBriscolaTable.gioca(current, card);//il giocatore fa la mossa
                                                }
                                        
                                
                                }
                        }
                        return true;
                }
                return true;
        }

}