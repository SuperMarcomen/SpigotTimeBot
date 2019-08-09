package it.marcodemartino.spigottimebot;

import io.github.ageofwar.telejam.Bot;
import io.github.ageofwar.telejam.LongPollingBot;
import io.github.ageofwar.telejam.text.Text;
import it.marcodemartino.spigottimebot.commands.Verificati;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        events.registerCommand(new Verificati(bot, Text.parseHtml(getLinesFromFile("message_verificati.txt"))), "verificati");
    }

    private String getLinesFromFile(String fileName) throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            return stream.collect(Collectors.joining("\n"));
        }
    }
}
