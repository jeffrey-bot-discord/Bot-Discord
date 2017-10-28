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
import java.util.ArrayList;
import java.util.List;
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
    static private List<Entity> userList = new ArrayList<Entity>();
    
    public void onMessageReceived(MessageReceivedEvent event) {

        Message message = event.getMessage();
        MessageChannel channel = event.getChannel();
        String content = message.getRawContent();
        
        if (event.isFromType(ChannelType.PRIVATE)) {
            System.out.printf("[PM] %s: %s\n", event.getAuthor().getName(),
                    event.getMessage().getContent());
        } else {
            System.out.printf("[%s][%s] %s: %s\n", event.getGuild().getName(),
                    event.getTextChannel().getName(), event.getMember().getEffectiveName(),
                    event.getMessage().getContent());
            
        }
        if (event.getAuthor().isBot()) {
            return;
        }
        
        boolean found = false;
        for(int i = 0;i<userList.size(); i++){
            
       
            if(event.getMember().getEffectiveName().equals(userList.get(i).getPseudo())){
                    found = true;
                        if(userList.get(i).getXp() >= userList.get(i).getXpMax()){
                            userList.get(i).setLvl(userList.get(i).getLvl()+1);
                            userList.get(i).setXp(0);
                            userList.get(i).setXpMax(userList.get(i).getXpMax()+10);
                            System.out.println(userList.get(i).getPseudo()+ " est mintenant niveau " + userList.get(i).getLvl());
                            channel.sendMessage(event.getMember().getAsMention()).queue();
                            channel.sendMessage("`"+userList.get(i).getPseudo()+ " est maintenant niveau " + userList.get(i).getLvl()+ "`").queue();
                            break;
                        } else {
                            userList.get(i).setXp( userList.get(i).getXp()+1);
                            userList.get(i).setNbreMessage(userList.get(i).getNbreMessage()+1);
                            //channel.sendMessage(userList.get(i).getPseudo()+" - "+userList.get(i).getNbreMessage()+" - "+ userList.get(i).getXp()).queue();
                            System.out.printf("[%s] %s a (%s/%s) xp %s",event.getMember().getEffectiveName() , userList.get(i).getPseudo(), userList.get(i).getXp(),  userList.get(i).getXpMax(), userList.get(i).getNbreMessage());
                            break;
                        }
            }
        }
        if(found == false){
            userList.add(new Entity(event.getMember().getEffectiveName()));
            
        }
        /*player = new Entity(event.getMember().getEffectiveName());       
        player.setPseudo(event.getMember().getEffectiveName());
        player.setNbreMessage(player.getNbreMessage()+1);
        player.setXp(player.getXp()+1);*/

        // We don't want to respond to other bot accounts, including ourself
        // getRawContent() is an atomic getter
        // getContent() is a lazy getter which modifies the content for e.g. console view (strip discord formatting)
        if (content.equals("!ping")) {
            channel.sendMessage("Pong!").queue(); // Important to call .queue() on the RestAction returned by sendMessage(...)
        } else if (content.equals("!cmd") | content.equals("!help") | content.equals("!aide") | content.equals("!commande") | content.equals("!command")) {
            channel.sendMessage(event.getMember().getAsMention()).queue();
            channel.sendMessage("```"
                    + "--------------------[ Commande du BOT ]--------------------------\n"
                    + "\n"
                    + "Version Alpha 0.1\n"
                    + "utiliser la commande : !ping , !stats, !allstats\n"
                    + "-----------------------------------------------------------------\n"
                    + "```\n"
            ).queue();
        } else if (content.equals("!channel")) {

            channel.sendMessage("Vous êtes dans le channel " + channel.getName()).queue();

        } else if (content.equals("!stat")) {
            System.out.println("test" + userList.size());
            for (int i = 0; i < userList.size(); i++) {
                
                if (event.getMember().getEffectiveName().equals(userList.get(i).getPseudo())) {
                    channel.sendMessage(event.getMember().getAsMention()).queue();
                    channel.sendMessage("```"
                    + "--------------------[ MY LEVEL ]--------------------------\n"
                    + "\n"
                    + "Pseudo : "+userList.get(i).getPseudo()+" / "+"\n"
                    + "Level : "+userList.get(i).getLvl()+"\n"
                    + "Expérience : "+userList.get(i).getXp()+"/"+ userList.get(i).getXpMax()+"\n"
                    + "Nombre de message : "+userList.get(i).getNbreMessage()+"\n"
                    + "\n"
                    + "-----------------------------------------------------------------\n"
                    + "```"
                    ).queue();
                    System.out.printf("[%s] %s a (%s/%s) xp %s", event.getMember().getEffectiveName(), userList.get(i).getPseudo(), userList.get(i).getXp(), userList.get(i).getXpMax(), userList.get(i).getNbreMessage());
                    break;
                } else if(found == false) {
                    channel.sendMessage("Vous n'avez pas encor de compte utilisateur enregisté").queue();
                    break;
                }
            }

        } else if (content.equals("!allstats")){
            String castMsg, cast;
            castMsg = "``` ";
            for(int i = 0; i < userList.size(); i++){

                cast = "\n Pseudo : "+userList.get(i).getPseudo()+ " , Level : "+ userList.get(i).getLvl()+" , XP : "+ userList.get(i).getXp()+"/"+ userList.get(i).getXpMax()+ " , Msg : "+ userList.get(i).getNbreMessage()+"\n";
                castMsg = castMsg + cast;
            }
            channel.sendMessage(castMsg+" ```").queue();
            
        }

    }

    public void sendMessage(MessageChannel channel, String message) {
        channel.sendMessage(message).queue();
    }
}
