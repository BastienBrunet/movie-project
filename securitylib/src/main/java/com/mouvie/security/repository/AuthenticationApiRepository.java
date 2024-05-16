package com.mouvie.security.repository;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class AuthenticationApiRepository {

    public static boolean checkTokenSignatureIsValid(String apiUrl, String token) {
        String fullUrl = apiUrl + "/validate/" + token;
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.getForEntity(fullUrl, String.class);

        return response.getStatusCode() == HttpStatusCode.valueOf(200);
    }
}
