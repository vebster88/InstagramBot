/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ru.betry;


import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;

import com.pengrad.telegrambot.model.File;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetFileResponse;
import okio.Utf8;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class App {


    public static void main(String[] args) {

        String TOKEN = "1347256899:AAE5HrH2bRs8fQdSOq9TSTI_nO1Y8eYpxWw";
            //ID   ������������
        Map<Integer, User> users = new HashMap<>();

        TelegramBot bot = new TelegramBot(TOKEN);
        bot.setUpdatesListener(updates -> {
            updates.forEach(System.out::println);
            //����� � ������
            //�����������
            //����� ��� ������������
            //����������

            updates.forEach(update -> {
                Integer userID = update.message().from().id();
                // �������� ������� ������ � ������
                if (!users.containsKey(userID)) {
                    bot.execute(new SendMessage(update.message().chat().id(),
                            "��� ���������� �������� ����� � ������ � ����� ������ ����� ������"));
                    users.put(userID, null);
                } else {
                    if (users.get(userID) == null) { // ������ ������ � ������
                        String[] loginAndPassword = update.message().text().split(" ");
                        User user = new User(loginAndPassword[0], loginAndPassword[1]);
                        users.put(userID, user);
                        bot.execute(new SendMessage(update.message().chat().id(),
                                "��� ��������! ������ �� ������ ��������� ��� �����, �����������, ���������� ��� " +
                                        "��������� (� ����� ���������)"));
                    } else if (update.message().photo().length > 0) {
                        System.out.println(update.toString());
                        Post post = new Post();
                        post.setTitle(update.message().text());
                        GetFileResponse fileResponse = bot.execute(new GetFile(update.message().photo()[0].fileId()));
                        File file = fileResponse.file();
                        String fullPath = bot.getFullFilePath(file);
                        System.out.println(fullPath);
                        try {
                            HttpDownload.downloadFile(fullPath, "./images", "test.jpg");
                        } catch (IOException e) {
                            System.err.println(e.getMessage());
                        }
                    }

                }
            });

            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }




}
