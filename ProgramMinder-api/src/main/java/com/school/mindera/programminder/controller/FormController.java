package com.school.mindera.programminder.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@RequestMapping("/form")
public class FormController {

    /**
     * Send email to Us. 3rd part app
     * @param jsonInputString
     * @return 3rd Part return saying status code and if it is send
     * @throws IOException
     */
    @CrossOrigin
    @PostMapping
    public ResponseEntity<String> sendEmailToUs(@RequestBody String jsonInputString) throws IOException {
        URL url = new URL("https://formspree.io/f/xgervlek");
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        try(OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {

            StringBuilder response = new StringBuilder();
            String responseLine = null;

            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }

            System.out.println(response.toString());

            return new ResponseEntity<>(response.toString(), HttpStatus.OK);
        }
    }
}
