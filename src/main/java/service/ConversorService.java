package service;

import com.google.gson.Gson;
import org.example.entities.Moedas;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConversorService {


    public Moedas getMoedas(String base_code , String target_code, float amount) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://v6.exchangerate-api.com/v6/b6c8cf92ef4798d32cd038fb/pair/" + base_code  +"/"+ target_code + "/" + amount))
                .build();

        try {
            HttpResponse<String> response = HttpClient
                    .newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());


            return new Gson().fromJson(response.body(), Moedas.class);


        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Erro ao obter taxas de câmbio", e);
        }

    }

}