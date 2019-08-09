package it.marcodemartino.spigottimebot.commands;

import io.github.ageofwar.telejam.Bot;
import io.github.ageofwar.telejam.commands.Command;
import io.github.ageofwar.telejam.commands.CommandHandler;
import io.github.ageofwar.telejam.messages.TextMessage;
import io.github.ageofwar.telejam.methods.SendMessage;
import io.github.ageofwar.telejam.text.Text;

import java.util.List;

public class Verificati implements CommandHandler {

    private final Bot bot;
    private Text message;

    public Verificati(Bot bot, Text message) {
        this.bot = bot;
        this.message = message;
    }

    public void onCommand(Command command, TextMessage textMessage) throws Throwable {
        SendMessage sendMessage = new SendMessage()
                .text(message)
                .chat(textMessage.getChat());
        bot.execute(sendMessage);
    }
}
