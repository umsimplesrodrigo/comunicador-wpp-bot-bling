package com.umsimplesrodrigo.comunicacao_bot_bling.service;

import com.umsimplesrodrigo.comunicacao_bot_bling.DTO.BlingTokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import java.util.Base64;
import java.util.Collections;

@Service
public class BlingAuthService {
    @Value("${bling.client-id}")
    private String clientId;

    @Value("${bling.client-secret}")
    private String clientSecret;

    public BlingTokenResponse exchangeCodeForToken(String code) {
        String url = "https://www.bling.com.br/Api/v3/oauth/token";
        String credentials = clientId + ":" + clientSecret;
        String encodeCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", "Basic " + encodeCredentials);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("code", code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<BlingTokenResponse> response = restTemplate.postForEntity(
                    url,
                    request,
                    BlingTokenResponse.class
            );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            System.out.println("Error getting token!" + e.getStatusCode() + "-" + e.getResponseBodyAsString());
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
