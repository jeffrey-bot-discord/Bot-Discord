/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package discord.codingfactory.jeffreybot;

import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.auth.login.LoginException;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
/**
 *
 * @author Alan
 */
public class MyListener extends ListenerAdapter {
    
    private Entity player;
    
    public void onMessageReceived(MessageReceivedEvent event) {

        
        if (event.isFromType(ChannelType.PRIVATE)) {
            System.out.printf("[PM] %s: %s\n", event.getAuthor().getName(),
                    event.getMessage().getContent());
        } else {
            System.out.printf("[%s][%s] %s: %s\n", event.getGuild().getName(),
                    event.getTextChannel().getName(), event.getMember().getEffectiveName(),
                    event.getMessage().getContent());
            
              player = new Entity(event.getMember().getEffectiveName());       
              player.setPseudo(event.getMember().getEffectiveName());
              player.setNbreMessage(player.getNbreMessage()+1);
              player.setXp(player.getXp()+1);
        }
        if (event.getAuthor().isBot()) {
            return;
        }
        // We don't want to respond to other bot accounts, including ourself
        Message message = event.getMessage();
        MessageChannel channel = event.getChannel();
        String content = message.getRawContent();
        // getRawContent() is an atomic getter
        // getContent() is a lazy getter which modifies the content for e.g. console view (strip discord formatting)
        if (content.equals("!ping")) {
            channel.sendMessage("Pong!").queue(); // Important to call .queue() on the RestAction returned by sendMessage(...)
        } 
        else if (content.equals("!cmd")) {
            channel.sendMessage("```"
                    + "--------------------[ Commande du BOT ]--------------------------\n"
                    + "\n"
                    + "Version Alpha 0.1\n"
                    + "Aucune focntionnamité n'a était implementer\n"
                    + "-----------------------------------------------------------------\n"
                    + "```\n"
            ).queue();
        }
        else if (content.equals("!channel")) {

            channel.sendMessage("Vous êtes dans le channel " + channel.getName()).queue();

        } 
        else if (content.equals("!mylevel")) {
            channel.sendMessage("```"
                    + "------------------------[ MY LEVEL ]----------------------------\n"
                    + "\n"
                    + player.getPseudo()+"\n"
                    + player.getLvl()+" lvl\n"
                    + player.getXp()+"/"+player.getXpMax()+" xp\n"
                    + player.getNbreMessage()+" messages.\n"
                    + "\n"
                    + "-----------------------------------------------------------------\n"
                    + "```"
            ).queue();
        }
    }

    public void sendMessage(MessageChannel channel, String message) {
        channel.sendMessage(message).queue();
    }
}
