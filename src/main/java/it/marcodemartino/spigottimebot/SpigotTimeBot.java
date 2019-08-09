package it.marcodemartino.spigottimebot;

import io.github.ageofwar.telejam.Bot;
import io.github.ageofwar.telejam.LongPollingBot;
import it.marcodemartino.spigottimebot.commands.Set;
import it.marcodemartino.spigottimebot.commands.Verificati;

import java.io.IOException;

public class SpigotTimeBot extends LongPollingBot {

    public static void main(String... args) throws IOException {
        if (args.length != 1) {
            System.err.println("Pass the bot token as unique program argument");
            System.exit(1);
        }
        String token = args[0];
        Bot bot = Bot.fromToken(token);
        SpigotTimeBot spigotTimeBot = new SpigotTimeBot(bot);
        spigotTimeBot.run();
    }

    public SpigotTimeBot(Bot bot) throws IOException {
        super(bot);

        events.registerCommand(new Verificati(bot), "verificati");
        events.registerCommand(new Set(bot), "set");

    }

}
