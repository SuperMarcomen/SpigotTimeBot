package it.marcodemartino.spigottimebot.commands;

import io.github.ageofwar.telejam.Bot;
import io.github.ageofwar.telejam.commands.Command;
import io.github.ageofwar.telejam.commands.CommandHandler;
import io.github.ageofwar.telejam.messages.TextMessage;
import io.github.ageofwar.telejam.methods.SendMessage;

public class Verificati implements CommandHandler {

    private final Bot bot;

    public Verificati(Bot bot) {
        this.bot = bot;
    }

    public void onCommand(Command command, TextMessage textMessage) throws Throwable {
        SendMessage sendMessage = new SendMessage()
                .text(Set.getMessage())
                .chat(textMessage.getChat());
        bot.execute(sendMessage);
    }

}
