package bot;

import AI.GroqClient;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class MiBot extends TelegramLongPollingBot {
    public String getBotUsername() {
        return "proyecto_final2025bot";
    }

    public String getBotToken() {
        return "KEY_DEL_BOT_DE_TELEGRAM";
    }

    @Override
    public void clearWebhook() {

    }

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

