/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package discord.codingfactory.jeffreybot;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

/**
 *
 * @author alan
 */
public class MessageListener extends ListenerAdapter {

    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }
        MessageChannel channel = event.getChannel();
        // We don't want to respond to other bot accounts, including ourself
        Message message = event.getMessage();
        String content = message.getRawContent();
        // getRawContent() is an atomic getter
        // getContent() is a lazy getter which modifies the content for e.g. console view (strip discord formatting)
        if (content.equals("!ping")) {
            channel.sendMessage("Pong!").queue(); // Important to call .queue() on the RestAction returned by sendMessage(...)
        }
        else if(content.equals("!cmd"))
            channel.sendMessage("``` \n "
                              + " --------[ Commande ]-------- \n"
                              + "\n"
                              + "* Phase Alpga !!!!\n"
                              + "* Aucune fonctionnalité\n"
                              + "\n"
                              + " ----------------------------\n"
                              + "```").queue();
       
    }
}
