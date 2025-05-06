package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.example.entities.Moedas;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;

public class ConversorService {

    public Moedas getMoedas(String dados) throws IOException, InterruptedException {

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://v6.exchangerate-api.com/v6/b6c8cf92ef4798d32cd038fb/latest/" + dados)).build();

        try{
            HttpResponse<String> response = HttpClient
                    .newHttpClient()
                    .send(request , HttpResponse.BodyHandlers.ofString());


            return new Gson().fromJson(response.body(),Moedas.class);
        }catch (IOException e){
            throw new RuntimeException(e);
        }catch (InterruptedException e){
            throw new RuntimeException();
        }

    }

}
