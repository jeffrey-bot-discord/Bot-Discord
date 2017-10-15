/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package discord.codingfactory.jeffreybot;

import java.util.Scanner;
import javax.security.auth.login.LoginException;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import java.util.ArrayList;
import net.dv8tion.jda.core.entities.MessageChannel;

/**
 *
 * @author Alan
 */
public class MaintBot {

    private JDA jda;

    private boolean stop = false;

    MaintBot(String token) throws IllegalArgumentException, RateLimitedException{
        try{
            jda = new JDABuilder(AccountType.BOT).setToken(token).addEventListener(new MyListener()).buildAsync();
        } catch (LoginException e) {
            e.printStackTrace();
            System.out.println("Une erreur est survenue vieulliez verifier le token ou votre connection internet");
            return;
        }
        System.out.println("Connecte avec: " + jda.getSelfUser().getName());
        int i;
        System.out.println("Le bot est autorisé sur " + (i = jda.getGuilds().size()) + " serveur" + (i > 1 ? "s" : ""));
        
        //System.out.println("Le bot est actuellement connecte sur le serveur ");
        while (!stop) {
            Scanner scanner = new Scanner(System.in);
            String cmd = scanner.next();
            if (cmd.equalsIgnoreCase("stop")) {
                jda.shutdown();
                stop = true;
            }
        }
        /*ArrayList al = new ArrayList(jda.getTextChannels());
        
        for(int iz = 0; i<al.size(); i++){
            System.out.println(al.get(iz));
        }*/
    }

    public static void main(String[] args)
            throws LoginException, RateLimitedException, InterruptedException {
        if (args.length < 1) {
            System.out.println("Vieulliez indiquer le token du bot");
        }
        new MaintBot(args[0]);
    }


}
