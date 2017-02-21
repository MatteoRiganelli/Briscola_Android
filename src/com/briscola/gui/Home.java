package com.briscola.gui;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.SpriteBackground;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.sprite.TiledSprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import com.briscola.gui.globali.Global;
import com.briscola.gui.globali.Textures;

public class Home extends Scene {
               
        private final BaseGameActivity activity; //attività
      
    
        public Home(int numLayer, final BaseGameActivity activity) {
                super(numLayer); //numero strati della scena (definito nella sopraclasse Scene)
                this.activity = activity; //attività che invoca la scena home (la principale)
               
        Sprite background_sprite = new Sprite(0, 0, Textures.background_region); //lo sfondo è uno sprite
        
        //gli sprite sono oggetti complessi di una scena 
        background_sprite.setSize(Global.CAMERA_WIDTH, Global.CAMERA_HEIGHT);
        
        //oggetti di classe exitbutton e playbutton. sono i bottoni di inizio gioco e uscita
        //cosi come sopra i primi due parametri indicano la posizione, il terzo la texture da utilizzare
        PlayButton play = new PlayButton(Textures.toScreenX(30), Textures.toScreenY(20), Textures.play_region);
        ExitButton exit = new ExitButton(Textures.toScreenX(30), Textures.toScreenY(60), Textures.exit_region);
        
        //setta la dimensione dei bottoni
        play.setSize(Textures.toScreenX(40), Textures.toScreenY(20)); 
        exit.setSize(Textures.toScreenX(40), Textures.toScreenY(20));
        
        //imposta lo sfondo della scena corrente
        this.setBackground(new SpriteBackground(background_sprite));
        //attiva il sensore di touch
        this.setTouchAreaBindingEnabled(true);
        
        //attiva ascoltatori dei pulsanti touch
        this.registerTouchArea(play);
        this.registerTouchArea(exit);
        
        //posiziona i bottoni sul layer in cima (che è l'unico fra l'altro)
        this.getTopLayer().addEntity(play);
        this.getTopLayer().addEntity(exit);
        
}
      
        
      private class ExitButton extends TiledSprite{
                 
    	        //il costruttore richiama i parametri della superclasse
                public ExitButton(float pX, float pY, TiledTextureRegion region) {
                        super(pX, pY, region);
                }
                
                public boolean onAreaTouched(TouchEvent event, float x, float y){
                        if(event.getAction()==(TouchEvent.ACTION_DOWN)){
                                setCurrentTileIndex(1);//prendi la parte 2 dell'immagine..il bottone premuto
                        }
                        else if(event.getAction()==(TouchEvent.ACTION_UP)){
                                setCurrentTileIndex(0);//l'immagine non premuta
                                if(this.contains(event.getX(), event.getY())){
                                        activity.finish();
                                }
                        }
                        return true;
                }
                
        }
        
      //come sopra
        private class PlayButton extends TiledSprite{

                public PlayButton(float pX, float pY, TiledTextureRegion region) {
                        super(pX, pY, region);
                }
                
                public boolean onAreaTouched(TouchEvent event, float x, float y){
                        if(event.getAction()==(TouchEvent.ACTION_DOWN)){
                                setCurrentTileIndex(1);
                        }
                        else if(event.getAction()==(TouchEvent.ACTION_UP)){
                                setCurrentTileIndex(0);
                                if(this.contains(event.getX(), event.getY())){      
                                    activity.getEngine().setScene(new TavoloGrafico(1, activity, 0));
                                        
                                }
                        }
                        return true;
                }
                
        }

}