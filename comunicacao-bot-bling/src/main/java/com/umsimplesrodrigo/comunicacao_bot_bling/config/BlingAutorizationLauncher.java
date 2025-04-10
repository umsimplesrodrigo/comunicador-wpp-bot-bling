package com.umsimplesrodrigo.comunicacao_bot_bling.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.net.URI;
import java.util.UUID;

@Component
public class BlingAutorizationLauncher implements ApplicationRunner {

    @Value("${bling.client-id}")
    private String mixcomId;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String state = UUID.randomUUID().toString().replace("-", "");
        String url = String.format("https://www.bling.com.br/Api/v3/oauth/authorize?response_type=code&cliente_id=%s&state=%s", mixcomId, state);

        System.out.println("Opening browser for authentication on Bling...");
        openBrowser(url);
    }

    private void openBrowser(String url) {
        try  {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new URI(url));
            } else {
                System.out.println("Browser supported!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
