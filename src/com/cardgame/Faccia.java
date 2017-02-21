package com.cardgame;

//classe che rappresenta la faccia di una carta
public class Faccia {
        private String faccia;

        public Faccia(String faccia) {
                this.faccia = faccia;
        }
   
        //ritorna il valore della faccia
        public String getFace() {
                return faccia;
        }
       //confronta se la faccia di una carta è uguale alla faccia dell'altra
        public boolean equals(Object obj) {
            if (this == obj)
                    return true;
            if (obj == null)
                    return false;
            if (getClass() != obj.getClass())
                    return false;
            Faccia other = (Faccia) obj;
            if (faccia == null) {
                    if (other.faccia != null)
                            return false;
            } else if (!faccia.equals(other.faccia))
                    return false;
            return true;
    } 
        //ritorna il valore dell'oggetto
        public String toString(){
                return faccia;
        }
        
}
