/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package discord.codingfactory.jeffreybot;

/**
 *
 * @author Alan
 */
public class Entity {
    
    private String pseudo;
    private int xp;
    private int xpMax = 10;
    private int lvl;
    private int nbreMessage;
    
    public Entity(String pseudo){
        this.pseudo = pseudo;
        this.lvl = 0;
        this.xp = 0;
        this.nbreMessage = 0;
    }

    /*
    * -------------------------------------------
    *   GETTER
    * -----------------------------------------*
    */ 
    public int getLvl() {
        return lvl;
    }

    public int getNbreMessage() {
        return nbreMessage;
    }

    public String getPseudo() {
        return pseudo;
    }

    public int getXp() {
        return xp;
    }

    public int getXpMax() {
        return xpMax;
    }
    
   /*
    * -------------------------------------------
    *   SETTER
    * -----------------------------------------*
    */ 

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public void setNbreMessage(int nbreMessage) {
        this.nbreMessage = nbreMessage;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public void setXpMax(int xpMax) {
        this.xpMax = xpMax;
    }
    
    
    
}
