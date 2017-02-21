package com.cardgame;

//Classe che rappresenta la singola carta

public  class Carta {
        public final Seme seme; //coppe,denari,spade,bastoni
        public final Faccia faccia; //valore e figura
        
        public Carta(Seme seme, Faccia faccia){
                this.seme = seme;
                this.faccia = faccia;
        }
    
        //metodo per effettuare il confronto fra oggetti carta 
        //esempio per vedere se una carta è uguale a una in input 
      
        public boolean equals(Object obj) {
                if (this == obj)
                        return true;
                if (obj == null)
                        return false;
                if (getClass() != obj.getClass())
                        return false;
                Carta other = (Carta) obj;
                if (faccia == null) {
                        if (other.faccia != null)
                                return false;
                } else if (!faccia.equals(other.faccia))
                        return false;
                if (seme == null) {
                        if (other.seme != null)
                                return false;
                } else if (!seme.equals(other.seme))
                        return false;
                return true;
        }
        
        //visualizza informazioni sugli oggetti della classe (ha senso per la sottoclasse dato
        //come abbiamo detto questa è astratta)
        
        public String toString(){
                return faccia.toString()+" di "+seme.toString();
        }
}
