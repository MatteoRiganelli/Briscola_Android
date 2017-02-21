package com.briscola.gui.android;


import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import android.view.KeyEvent;

import com.briscola.gui.Home;
import com.briscola.gui.TavoloGrafico;
import com.briscola.gui.globali.Global;
import com.briscola.gui.globali.Textures;


/* Nella nostra app abbiamo usato la libreria grafica andengine basata su opengl per gestire il lato grafic*/
/*Si basa su oggetti quali Engine (livello logico in cui si mette la Scene e  che permette l'evoluzione del gioco nel tempo*/
/*, Scene (contenitore oggetti da disegnare), camera (porzione di scena inquadrata) e Sprite di vario tipo */
/*(sono gli oggetti delle scene...ci sono vari tipi: Tiled,Animated o Sprite semplici che differiscono per le opzioni che offrono*/

public class BriscolaActivity extends BaseGameActivity {
        /*Attività di partenza. all'avvio dell'app viene caricata la scena */
	
        private Camera camera;//variabile telecamera. è la porzione di scena visibile su schermo
     
        public Engine onLoadEngine() {//al caricamento dell'engine
                
        	    //impostazioni della telecamera (posizione (x,y) e dimensioni (risoluzione)
        	    this.camera = new Camera(0, 0, Global.CAMERA_WIDTH,
                                Global.CAMERA_HEIGHT); 
                
        	    //impostazioni Engine (risoluzione, orientamento e telecamera)
        	    EngineOptions eo=new EngineOptions(true, ScreenOrientation.PORTRAIT,
                        new RatioResolutionPolicy(Global.CAMERA_WIDTH,
                                Global.CAMERA_HEIGHT), this.camera);	   
                return new Engine(eo);//ritorna l'engine
        }
        
        
        
        public void onLoadResources() { //risorse necessarie all'engine
                Textures.loadTextures(this); //carica le texture
        }
        
        //scena da caricare
        public Scene onLoadScene() {//al caricamento della scena 
                final Home scene = new Home(1, this); //istanzia l'oggetto scena (gli passo
                //l'attività e il numero di layer)
                
                return scene;  //restituisci la scena
        }

        public void onLoadComplete() {//cosa fare al caricamento completato
                       //nulla
        }
        
        //ascoltatore evento tasto di ritorno indietro cellulare premuto
        
        public boolean onKeyDown(int keyCode, KeyEvent event) {
                if ((keyCode == KeyEvent.KEYCODE_BACK)) { //se ho premuto il tasto indietro del cell
                        System.out.println(this.mEngine.getScene().getClass()); //identifica in quale scena mi trovo
                        if (this.mEngine.getScene() instanceof Home) { //se sono nella home 
                                finish();  //allora termina l'app
                        } else if (this.mEngine.getScene() instanceof TavoloGrafico) { //altrimenti se sono
                                TavoloGrafico t = (TavoloGrafico)this.mEngine.getScene();  //nella scena di gioco
                                t.getTable().setGiocoFinito(true); //setta il gioco come finito 
                                //(creo la variabile tavolo per poter terminare il gioco)
                                this.mEngine.setScene(new Home(1, this)); //e ricarica la home
                        } 
                }
                return true;
        }
   
}

