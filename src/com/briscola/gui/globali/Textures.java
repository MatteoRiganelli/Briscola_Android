package com.briscola.gui.globali;

import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import android.graphics.Color;
import android.graphics.Typeface;

//classe di gestione delle texture utilizzate dall'applicazione

public class Textures {
     // classe Texture: classe che rappresente le immagini da utilizzare nell'applicazione
	 //classe TextureRegion: classe che rappresenta la porzione di texture effettivamente usata
     //classe TiledTextureRegion: classe che rappresenta la porzione di texture effettivamente
	 //utilizzata su un oggetto di tipo TiledSprite
	
	//immagini sfondo, pulsante play,pulsante uscita
        public static Texture background_texture, play_texture, 
                        exit_texture;
        
        // texture sfondo,exit e play
        public static TextureRegion background_region;
        public static TiledTextureRegion play_region, exit_region;
        
        //immagini Carte
        public static Texture c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12,
                        c13, c14, c15, c16, c17, c18, c19, c20, c21, c22, c23, c24, c25,
                        c26, c27, c28, c29, c30, c31, c32, c33, c34, c35, c36, c37, c38,
                        c39, c40, retro;
        // texture carte
        public static TiledTextureRegion c1region, c2region, c3region, c4region,
                        c5region, c6region, c7region, c8region, c9region, c10region,
                        c11region, c12region, c13region, c14region, c15region, c16region,
                        c17region, c18region, c19region, c20region, c21region, c22region,
                        c23region, c24region, c25region, c26region, c27region, c28region,
                        c29region, c30region, c31region, c32region, c33region, c34region,
                        c35region, c36region, c37region, c38region, c39region, c40region,
                        retroregion;

        // Caricamento dei Font
        public static Texture font_puntiuno = new Texture(512, 512,
                        TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        public static Texture font_puntidue = new Texture(512, 512,
                        TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        public static Font puntiuno_font = new Font(font_puntiuno, Typeface.create(
                        Typeface.DEFAULT, Typeface.NORMAL), Textures.toScreenY(Global.card_h),
                        true, Color.YELLOW);
        public static Font puntidue_font = new Font(font_puntidue, Typeface.create(
                        Typeface.DEFAULT, Typeface.NORMAL), Textures.toScreenY(Global.card_h),
                        true, Color.BLUE);
       
        
        //istanzia le texture inizializzate precedentemente
        public static void loadTextures(BaseGameActivity activity) {
                // Background
                background_texture = new Texture(512, 1024,
                                TextureOptions.BILINEAR_PREMULTIPLYALPHA);
                background_region = TextureRegionFactory.createFromAsset(
                                background_texture, activity, "home/background.png", 0, 0);

                // Bottoni
                play_texture = new Texture(256, 64,
                                TextureOptions.BILINEAR_PREMULTIPLYALPHA);
                play_region = TextureRegionFactory.createTiledFromAsset(play_texture,
                                activity, "home/play.png", 0, 0, 2, 1);

       

                exit_texture = new Texture(256, 64,
                                TextureOptions.BILINEAR_PREMULTIPLYALPHA);
                exit_region = TextureRegionFactory.createTiledFromAsset(exit_texture,
                                activity, "home/exit.png", 0, 0, 2, 1);

                // Carte
                c1 = new Texture(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
                c1region = TextureRegionFactory.createTiledFromAsset(c1, activity,
                                "cards/c1.png", 0, 0, 2, 1);
                c2 = new Texture(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
                c2region = TextureRegionFactory.createTiledFromAsset(c2, activity,
                                "cards/c2.png", 0, 0, 2, 1);
                c3 = new Texture(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
                c3region = TextureRegionFactory.createTiledFromAsset(c3, activity,
                                "cards/c3.png", 0, 0, 2, 1);
                c4 = new Texture(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
                c4region = TextureRegionFactory.createTiledFromAsset(c4, activity,
                                "cards/c4.png", 0, 0, 2, 1);
                c5 = new Texture(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
                c5region = TextureRegionFactory.createTiledFromAsset(c5, activity,
                                "cards/c5.png", 0, 0, 2, 1);
                c6 = new Texture(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
                c6region = TextureRegionFactory.createTiledFromAsset(c6, activity,
                                "cards/c6.png", 0, 0, 2, 1);
                c7 = new Texture(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
                c7region = TextureRegionFactory.createTiledFromAsset(c7, activity,
                                "cards/c7.png", 0, 0, 2, 1);
                c8 = new Texture(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
                c8region = TextureRegionFactory.createTiledFromAsset(c8, activity,
                                "cards/c8.png", 0, 0, 2, 1);
                c9 = new Texture(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
                c9region = TextureRegionFactory.createTiledFromAsset(c9, activity,
                                "cards/c9.png", 0, 0, 2, 1);
                c10 = new Texture(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
                c10region = TextureRegionFactory.createTiledFromAsset(c10, activity,
                                "cards/c10.png", 0, 0, 2, 1);
                c11 = new Texture(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
                c11region = TextureRegionFactory.createTiledFromAsset(c11, activity,
                                "cards/c11.png", 0, 0, 2, 1);
                c12 = new Texture(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
                c12region = TextureRegionFactory.createTiledFromAsset(c12, activity,
                                "cards/c12.png", 0, 0, 2, 1);
                c13 = new Texture(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
                c13region = TextureRegionFactory.createTiledFromAsset(c13, activity,
                                "cards/c13.png", 0, 0, 2, 1);
                c14 = new Texture(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
                c14region = TextureRegionFactory.createTiledFromAsset(c14, activity,
                                "cards/c14.png", 0, 0, 2, 1);
                c15 = new Texture(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
                c15region = TextureRegionFactory.createTiledFromAsset(c15, activity,
                                "cards/c15.png", 0, 0, 2, 1);
                c16 = new Texture(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
                c16region = TextureRegionFactory.createTiledFromAsset(c16, activity,
                                "cards/c16.png", 0, 0, 2, 1);
                c17 = new Texture(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
                c17region = TextureRegionFactory.createTiledFromAsset(c17, activity,
                                "cards/c17.png", 0, 0, 2, 1);
                c18 = new Texture(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
                c18region = TextureRegionFactory.createTiledFromAsset(c18, activity,
                                "cards/c18.png", 0, 0, 2, 1);
                c19 = new Texture(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
                c19region = TextureRegionFactory.createTiledFromAsset(c19, activity,
                                "cards/c19.png", 0, 0, 2, 1);
                c20 = new Texture(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
                c20region = TextureRegionFactory.createTiledFromAsset(c20, activity,
                                "cards/c20.png", 0, 0, 2, 1);
                c21 = new Texture(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
                c21region = TextureRegionFactory.createTiledFromAsset(c21, activity,
                                "cards/c21.png", 0, 0, 2, 1);
                c22 = new Texture(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
                c22region = TextureRegionFactory.createTiledFromAsset(c22, activity,
                                "cards/c22.png", 0, 0, 2, 1);
                c23 = new Texture(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
                c23region = TextureRegionFactory.createTiledFromAsset(c23, activity,
                                "cards/c23.png", 0, 0, 2, 1);
                c24 = new Texture(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
                c24region = TextureRegionFactory.createTiledFromAsset(c24, activity,
                                "cards/c24.png", 0, 0, 2, 1);
                c25 = new Texture(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
                c25region = TextureRegionFactory.createTiledFromAsset(c25, activity,
                                "cards/c25.png", 0, 0, 2, 1);
                c26 = new Texture(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
                c26region = TextureRegionFactory.createTiledFromAsset(c26, activity,
                                "cards/c26.png", 0, 0, 2, 1);
                c27 = new Texture(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
                c27region = TextureRegionFactory.createTiledFromAsset(c27, activity,
                                "cards/c27.png", 0, 0, 2, 1);
                c28 = new Texture(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
                c28region = TextureRegionFactory.createTiledFromAsset(c28, activity,
                                "cards/c28.png", 0, 0, 2, 1);
                c29 = new Texture(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
                c29region = TextureRegionFactory.createTiledFromAsset(c29, activity,
                                "cards/c29.png", 0, 0, 2, 1);
                c30 = new Texture(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
                c30region = TextureRegionFactory.createTiledFromAsset(c30, activity,
                                "cards/c30.png", 0, 0, 2, 1);
                c31 = new Texture(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
                c31region = TextureRegionFactory.createTiledFromAsset(c31, activity,
                                "cards/c31.png", 0, 0, 2, 1);
                c32 = new Texture(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
                c32region = TextureRegionFactory.createTiledFromAsset(c32, activity,
                                "cards/c32.png", 0, 0, 2, 1);
                c33 = new Texture(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
                c33region = TextureRegionFactory.createTiledFromAsset(c33, activity,
                                "cards/c33.png", 0, 0, 2, 1);
                c34 = new Texture(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
                c34region = TextureRegionFactory.createTiledFromAsset(c34, activity,
                                "cards/c34.png", 0, 0, 2, 1);
                c35 = new Texture(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
                c35region = TextureRegionFactory.createTiledFromAsset(c35, activity,
                                "cards/c35.png", 0, 0, 2, 1);
                c36 = new Texture(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
                c36region = TextureRegionFactory.createTiledFromAsset(c36, activity,
                                "cards/c36.png", 0, 0, 2, 1);
                c37 = new Texture(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
                c37region = TextureRegionFactory.createTiledFromAsset(c37, activity,
                                "cards/c37.png", 0, 0, 2, 1);
                c38 = new Texture(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
                c38region = TextureRegionFactory.createTiledFromAsset(c38, activity,
                                "cards/c38.png", 0, 0, 2, 1);
                c39 = new Texture(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
                c39region = TextureRegionFactory.createTiledFromAsset(c39, activity,
                                "cards/c39.png", 0, 0, 2, 1);
                c40 = new Texture(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
                c40region = TextureRegionFactory.createTiledFromAsset(c40, activity,
                                "cards/c40.png", 0, 0, 2, 1);
                retro = new Texture(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
                retroregion = TextureRegionFactory.createTiledFromAsset(retro,
                                activity, "cards/retro.png", 0, 0, 2, 1);
                //caricamento texture per la scena
                activity.getEngine()
                                .getTextureManager()
                                .loadTextures(play_texture,  exit_texture,
                                                background_texture, c1, c2, c3, c4, c5, c6, c7, c8, c9,
                                                c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20,
                                                c21, c22, c23, c24, c25, c26, c27, c28, c29, c30, c31,
                                                c32, c33, c34, c35, c36, c37, c38, c39, c40, retro,
                                                font_puntiuno, font_puntidue);
                //caricamento font per la scena
                activity.getEngine().getFontManager()
                                .loadFonts(puntiuno_font, puntidue_font);
        }

        //calcola le coordinate di dove mettere un oggetto in proporzione alla risoluzione della telecamera
        public static int toScreenX(int perc) {
                return (Global.CAMERA_WIDTH * perc) / 100;
        }

        public static int toScreenY(int perc) {
                return (Global.CAMERA_HEIGHT * perc) / 100;
        }
        
      //ottieni texture carta in base alle caratteristiche della carta
        public static TiledTextureRegion getCardRegion(String seme, String faccia) {
                if (faccia.equalsIgnoreCase("ASSO") && seme.equalsIgnoreCase("DENARI"))
                        return c1region;
                if (faccia.equalsIgnoreCase("DUE") && seme.equalsIgnoreCase("DENARI"))
                        return c2region;
                if (faccia.equalsIgnoreCase("TRE") && seme.equalsIgnoreCase("DENARI"))
                        return c3region;
                if (faccia.equalsIgnoreCase("QUATTRO") && seme.equalsIgnoreCase("DENARI"))
                        return c4region;
                if (faccia.equalsIgnoreCase("CINQUE") && seme.equalsIgnoreCase("DENARI"))
                        return c5region;
                if (faccia.equalsIgnoreCase("SEI") && seme.equalsIgnoreCase("DENARI"))
                        return c6region;
                if (faccia.equalsIgnoreCase("SETTE") && seme.equalsIgnoreCase("DENARI"))
                        return c7region;
                if (faccia.equalsIgnoreCase("DONNA") && seme.equalsIgnoreCase("DENARI"))
                        return c8region;
                if (faccia.equalsIgnoreCase("CAVALLO") && seme.equalsIgnoreCase("DENARI"))
                        return c9region;
                if (faccia.equalsIgnoreCase("RE") && seme.equalsIgnoreCase("DENARI"))
                        return c10region;

                if (faccia.equalsIgnoreCase("ASSO") && seme.equalsIgnoreCase("COPPE"))
                        return c11region;
                if (faccia.equalsIgnoreCase("DUE") && seme.equalsIgnoreCase("COPPE"))
                        return c12region;
                if (faccia.equalsIgnoreCase("TRE") && seme.equalsIgnoreCase("COPPE"))
                        return c13region;
                if (faccia.equalsIgnoreCase("QUATTRO") && seme.equalsIgnoreCase("COPPE"))
                        return c14region;
                if (faccia.equalsIgnoreCase("CINQUE") && seme.equalsIgnoreCase("COPPE"))
                        return c15region;
                if (faccia.equalsIgnoreCase("SEI") && seme.equalsIgnoreCase("COPPE"))
                        return c16region;
                if (faccia.equalsIgnoreCase("SETTE") && seme.equalsIgnoreCase("COPPE"))
                        return c17region;
                if (faccia.equalsIgnoreCase("DONNA") && seme.equalsIgnoreCase("COPPE"))
                        return c18region;
                if (faccia.equalsIgnoreCase("CAVALLO") && seme.equalsIgnoreCase("COPPE"))
                        return c19region;
                if (faccia.equalsIgnoreCase("RE") && seme.equalsIgnoreCase("COPPE"))
                        return c20region;

                if (faccia.equalsIgnoreCase("ASSO") && seme.equalsIgnoreCase("BASTONI"))
                        return c21region;
                if (faccia.equalsIgnoreCase("DUE") && seme.equalsIgnoreCase("BASTONI"))
                        return c22region;
                if (faccia.equalsIgnoreCase("TRE") && seme.equalsIgnoreCase("BASTONI"))
                        return c23region;
                if (faccia.equalsIgnoreCase("QUATTRO")
                                && seme.equalsIgnoreCase("BASTONI"))
                        return c24region;
                if (faccia.equalsIgnoreCase("CINQUE") && seme.equalsIgnoreCase("BASTONI"))
                        return c25region;
                if (faccia.equalsIgnoreCase("SEI") && seme.equalsIgnoreCase("BASTONI"))
                        return c26region;
                if (faccia.equalsIgnoreCase("SETTE") && seme.equalsIgnoreCase("BASTONI"))
                        return c27region;
                if (faccia.equalsIgnoreCase("DONNA") && seme.equalsIgnoreCase("BASTONI"))
                        return c28region;
                if (faccia.equalsIgnoreCase("CAVALLO")
                                && seme.equalsIgnoreCase("BASTONI"))
                        return c29region;
                if (faccia.equalsIgnoreCase("RE") && seme.equalsIgnoreCase("BASTONI"))
                        return c30region;

                if (faccia.equalsIgnoreCase("ASSO") && seme.equalsIgnoreCase("SPADE"))
                        return c31region;
                if (faccia.equalsIgnoreCase("DUE") && seme.equalsIgnoreCase("SPADE"))
                        return c32region;
                if (faccia.equalsIgnoreCase("TRE") && seme.equalsIgnoreCase("SPADE"))
                        return c33region;
                if (faccia.equalsIgnoreCase("QUATTRO") && seme.equalsIgnoreCase("SPADE"))
                        return c34region;
                if (faccia.equalsIgnoreCase("CINQUE") && seme.equalsIgnoreCase("SPADE"))
                        return c35region;
                if (faccia.equalsIgnoreCase("SEI") && seme.equalsIgnoreCase("SPADE"))
                        return c36region;
                if (faccia.equalsIgnoreCase("SETTE") && seme.equalsIgnoreCase("SPADE"))
                        return c37region;
                if (faccia.equalsIgnoreCase("DONNA") && seme.equalsIgnoreCase("SPADE"))
                        return c38region;
                if (faccia.equalsIgnoreCase("CAVALLO") && seme.equalsIgnoreCase("SPADE"))
                        return c39region;
                if (faccia.equalsIgnoreCase("RE") && seme.equalsIgnoreCase("SPADE"))
                        return c40region;
                return retroregion; //ritorna il dietro della carta
        }
 
}