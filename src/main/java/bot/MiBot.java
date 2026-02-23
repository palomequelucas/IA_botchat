package bot;

import AI.GroqClient;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class MiBot extends TelegramLongPollingBot {

    private static final String BOT_TOKEN = System.getenv("TELEGRAM_BOT_TOKEN");

    public MiBot() {
        if (BOT_TOKEN == null || BOT_TOKEN.isEmpty()) {
            throw new RuntimeException("La variable de entorno TELEGRAM_BOT_TOKEN no está configurada.");
        }
    }

    @Override
    public String getBotUsername() {
        return "proyecto_final2025bot";
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            if (update.hasMessage() && update.getMessage().hasText()) {

                String mensaje = update.getMessage().getText();
                long chatId = update.getMessage().getChatId();

                String respuesta = GroqClient.preguntarIA(mensaje);

                SendMessage msg = new SendMessage();
                msg.setChatId(String.valueOf(chatId));
                msg.setText(respuesta);

                this.execute(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
