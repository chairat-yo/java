package org.chaidev.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class TinyUrlController {
    private Logger logger = Logger.getLogger(TinyUrlController.class.getName());
    private Map<String, String> urlMap = new HashMap();

    private static final String URL_PREFIX = "http://localhost:8080//";
    @GetMapping("/getShortURL")
    public ResponseEntity<String> getShortURL(@RequestParam String longUrl) {
        final HttpHeaders httpHeaders= new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        String randomID = getRandomUUID();
        String shortUrlString = URL_PREFIX + randomID;
        urlMap.put(randomID, longUrl);
        return new ResponseEntity<String>("{\"shortLink\": \"" + shortUrlString + "\"}", httpHeaders, HttpStatus.OK);

    }

    @GetMapping("/getFullURL")
    public void getFullURL(@RequestParam String encodedURL, HttpServletResponse response) throws IOException {
        //String encodeURL = uString.substring(URL_PREFIX.length());
        logger.info("getFullURL_encodeURL: " + urlMap.get(encodedURL));
        response.sendRedirect(urlMap.get(encodedURL));
    }

    @GetMapping(value = "//{regex:.*}")
    public void redirect(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String keyToURL = request.getRequestURI().substring(2);
        logger.info("redirect....." + keyToURL);
        response.sendRedirect("/getFullURL?encodedURL=" + keyToURL);
    }

    private String getRandomUUID() {
        return UUID.randomUUID().toString().substring(0, 5);
    }
}