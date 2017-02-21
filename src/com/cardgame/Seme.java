package com.cardgame;


//classe che rappresenta il seme dellaa carta
public class Seme {
        private String seme;

        public Seme(String seme) {
                this.seme = seme;
        }

        //ritorna il seme
        public String getSeme() {
                return seme;
        }

        public boolean equals(Object obj) {
            if (this == obj)
                    return true;
            if (obj == null)
                    return false;
            if (getClass() != obj.getClass())
                    return false;
            Seme other = (Seme) obj;
            if (seme == null) {
                    if (other.seme != null)
                            return false;
            } else if (!seme.equals(other.seme))
                    return false;
            return true;
    } 
    
        
        //ritorna informaziuoni sulla classe
        public String toString(){
                return seme;
        }
        
}
