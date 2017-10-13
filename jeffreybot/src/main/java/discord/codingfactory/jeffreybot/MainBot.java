/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package discord.codingfactory.jeffreybot;

/**
 *
 * @author alan
 */
import javax.security.auth.login.LoginException;
import java.util.Scanner;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.hooks.ListenerAdapter;


public class MainBot extends ListenerAdapter {

    private JDA jda;

    private boolean stop = false;
    private char reponse = ' ';
    static Scanner keyboard = new Scanner(System.in);
    
    MainBot(String token) throws Exception {
        try {
            jda = new JDABuilder(AccountType.BOT).setToken(token).addEventListener(new MessageListener()).buildBlocking();
        } catch (LoginException | InterruptedException e) {
            e.printStackTrace();
            System.out.println("Une erreur est survenue vieulliez verifier le token ou votre connection internet");
            return;
        }
        System.out.println("Connecte avec: " + jda.getSelfUser().getName());
        int i;
        System.out.println("Le bot est autorisé sur " + (i = jda.getGuilds().size()) + " serveur" + (i > 1 ? "s" : ""));
        
       /*do{
            String message = keyboard.nextLine();
            
       }while(reponse == 'y');*/
        
        while (!stop) {
            Scanner scanner = new Scanner(System.in);
            String cmd = scanner.next();
            if (cmd.equalsIgnoreCase("stop")) {
                jda.shutdown();
                stop = true;
            }
        }
    }
    
    public static void main(String[] args) throws Exception {
        /*if (args.length < 1) {
            System.out.println("Vieulliez indiquer le token du bot");
        }*/
        new MainBot("MzY3NjE5NDUyOTAyMDQ3NzQ0.DMIRxg.Kfn_FerZV09XlpF_hV2mHnouh_w");

    }
    
    public void sendMessage(MessageChannel channel, String message) 
    {
        channel.sendMessage(message).queue();
    }

}
