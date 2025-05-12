package service;

import com.google.gson.Gson;
import org.example.entities.Moedas;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ListaService {


    public Moedas getMoedas() throws IOException, InterruptedException {

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://v6.exchangerate-api.com/v6/b6c8cf92ef4798d32cd038fb/codes"))
                    .build();

            HttpResponse<String> response = client.send(request , HttpResponse.BodyHandlers.ofString());

            return new Gson().fromJson(response.body(), Moedas.class);

        }
    }

