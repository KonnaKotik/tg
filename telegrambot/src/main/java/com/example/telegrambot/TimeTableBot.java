package com.example.telegrambot;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;


@Component
public class TimeTableBot extends TelegramLongPollingBot {

    public final static String BOT_TOKEN = "1198487245:AAGpEWF_9f3xHXwSzu5jBf5v--_4QIJaJug";


    private final static String BOT_USERNAME = "KfuTimeTableBot";

    private final static String mondayText = "Расписание на понедельник:" + "\n" +"17:50 - Робототехника" + "\n" + "19:30 - Робототехника";
    private final static String tuesdayText = "Расписание на вторник:" + "\n" +"8:30 - Основы предпринимательства";
    private final static String wednesdayText = "Расписание на среду:" + "\n" +"8:30 - Физ-ра" + "\n" + "10:10 - Управление проектами" + "\n" + "15:40 - Робототехника (лекция)";
    private final static String thursdayText = "8:30 - Физ-ра" + "\n" + "10:10 - Управление проектами" + "\n" + "15:40 - Робототехника (лекция)";
    private final static String fridayText = "8:30 - Физ-ра" + "\n" + "10:10 - Управление проектами" + "\n" + "15:40 - Робототехника (лекция)";
    private final static  String saturdayText = "8:30 - Физ-ра" + "\n" + "10:10 - Управление проектами" + "\n" + "15:40 - Робототехника (лекция)";;




    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage()) {
            Message message = update.getMessage();
            if(update.getMessage().hasText()) {
                String text = message.getText();
                if(text.equals("/start")) {
                    execute(setButton(message.getChatId()));
                }
            }
        } else if (update.hasCallbackQuery()) {
            try {
                String callbackText = update.getCallbackQuery().getData();
                SendMessage sendMessage = new SendMessage();
                Long chatId = update.getCallbackQuery().getMessage().getChatId();
                sendMessage.setChatId(chatId);

                switch (callbackText) {
                    case "Расписание на понедельник:":
                        sendMessage.setText(mondayText);
                        execute(sendMessage);
                        execute(setReplay(chatId));
                        break;
                    case "Расписание на вторник:":
                        sendMessage.setText(tuesdayText);
                        execute(sendMessage);
                        execute(setReplay(chatId));
                        break;
                    case "Расписание на среду:":
                        sendMessage.setText(wednesdayText);
                        execute(sendMessage);
                        execute(setReplay(chatId));
                        break;
                    case "Да" :
                        try{
                            execute(sendInlineKeyBoardMessage(chatId));
                        }
                        catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                }

            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }


    }

    public SendMessage sendInlineKeyBoardMessage(long chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton mondayButton = new InlineKeyboardButton();
        mondayButton.setText("Понедельник");
        mondayButton.setCallbackData("Расписание на понедельник:");

        InlineKeyboardButton tuesdayButton = new InlineKeyboardButton();
        tuesdayButton.setText("Вторник");
        tuesdayButton.setCallbackData("Расписание на вторник:");

        InlineKeyboardButton wednesdayButton = new InlineKeyboardButton();
        wednesdayButton.setText("Среда");
        wednesdayButton.setCallbackData("Расписание на среду:");

        InlineKeyboardButton thursdayButton = new InlineKeyboardButton();
        thursdayButton.setText("Четверг");
        thursdayButton.setCallbackData("Расписание на среду:");

        InlineKeyboardButton fridayButton = new InlineKeyboardButton();
        fridayButton.setText("Пятница");
        fridayButton.setCallbackData("Расписание на среду:");

        InlineKeyboardButton saturdayButton = new InlineKeyboardButton();
        saturdayButton.setText("Суббота");
        saturdayButton.setCallbackData("Расписание на среду:");

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        List<InlineKeyboardButton> row2 = new ArrayList<>();

        row1.add(mondayButton);
        row1.add(tuesdayButton);
        row1.add(wednesdayButton);

        row2.add(thursdayButton);
        row2.add(fridayButton);
        row2.add(saturdayButton);

        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        rows.add(row1);
        rows.add(row2);

        inlineKeyboardMarkup.setKeyboard(rows);

        return new SendMessage().setChatId(chatId).setText("На какой день? Выберите день недели").setReplyMarkup(inlineKeyboardMarkup);


    }

    public SendMessage setButton(long chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton mondayButton = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        mondayButton.setText("Да");
        mondayButton.setCallbackData("Да");
        inlineKeyboardButton2.setText("Нет");
        inlineKeyboardButton2.setCallbackData("Нет");
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(mondayButton);
        keyboardButtonsRow1.add(inlineKeyboardButton2);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return new SendMessage().setChatId(chatId).setText("Привет) Хочешь узнать расписание?").setReplyMarkup(inlineKeyboardMarkup);
    }

    public SendMessage setReplay(long chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton mondayButton = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        mondayButton.setText("Да");
        mondayButton.setCallbackData("Да");
        inlineKeyboardButton2.setText("Нет");
        inlineKeyboardButton2.setCallbackData("Нет");
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(mondayButton);
        keyboardButtonsRow1.add(inlineKeyboardButton2);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return new SendMessage().setChatId(chatId).setText("Хочешь узнать на другой день?").setReplyMarkup(inlineKeyboardMarkup);
    }

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    private boolean isUserCode(String message) {
        if(message.contains("kfu:")) {
            String userCode = message.concat("kfu:");
            return true;
        } else return false;

    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }
}
