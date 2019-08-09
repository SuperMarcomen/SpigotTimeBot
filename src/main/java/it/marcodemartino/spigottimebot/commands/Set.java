package it.marcodemartino.spigottimebot.commands;

import io.github.ageofwar.telejam.Bot;
import io.github.ageofwar.telejam.commands.Command;
import io.github.ageofwar.telejam.commands.CommandHandler;
import io.github.ageofwar.telejam.messages.TextMessage;
import io.github.ageofwar.telejam.methods.SendMessage;
import io.github.ageofwar.telejam.text.Text;
import lombok.Getter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Set implements CommandHandler {

    private final Bot bot;
    @Getter private static Text message;
    private List<Long> admins;

    public Set(Bot bot) throws IOException {
        this.bot = bot;
        admins = getAdmins();
        message = Text.parseHtml(getVerificatiMessage());
    }

    @Override
    public void onCommand(Command command, TextMessage textMessage) throws IOException {
        String[] arguments = textMessage.getText().toString().split(" ");
        if(!admins.contains(textMessage.getSender().getId())) return;

        if(arguments.length <= 2) {
            SendMessage sendMessage = new SendMessage()
                    .text("Uso corretto: /set <verificati:admins> <messvalore>")
                    .chat(textMessage.getChat());
            bot.execute(sendMessage);
            return;
        }

        String message = textMessage.getText().toString().replaceAll("(?i)/set ", "");
        message = message.replaceAll("(?i)verificati ", "");
        message = message.replaceAll("(?i)admins ", "");

        Set.message = Text.parseHtml(message);

        if(arguments[1].equalsIgnoreCase("verificati")) saveFile("message_verificati.txt", message);
        else saveFile("admins.txt", message);

        SendMessage sendMessage = new SendMessage()
                .text("Fatto")
                .chat(textMessage.getChat());
        bot.execute(sendMessage);
    }

    private void saveFile(String fileName, String text) throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter(fileName)) {
            out.println(text);
        }
    }

    private String getVerificatiMessage() throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get("message_verificati.txt"))) {
            return stream.collect(Collectors.joining("\n"));
        }
    }

    private List<Long> getAdmins() throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get("admins.txt"))) {
            return stream.map(Long::valueOf).collect(Collectors.toList());
        }
    }
}
