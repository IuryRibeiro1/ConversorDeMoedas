package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.entities.Moedas;
import service.ConversorService;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String texto;
        System.out.println("Insira a moeda que deseja converter: ");

        texto = sc.next();

        ConversorService conversorService = new ConversorService();
        Moedas moedas = conversorService.getMoedas(texto);

        System.out.println(gson.toJson(moedas));

    }

}