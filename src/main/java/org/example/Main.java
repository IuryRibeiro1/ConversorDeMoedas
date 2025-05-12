package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.entities.Moedas;
import service.ConversorService;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


public class Main {


    public static void main(String[] args) throws IOException, InterruptedException {


        Scanner sc = new Scanner(System.in);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String base_code = "";
        String target_code;

        List<Moedas> moedasFiltradas = new ArrayList<>();

        int laco = 1;
        while (laco != 0) {

            System.out.println("BRL: " + "Real Brasileiro");
            System.out.println("USD: " + "Dólar Americano");
            System.out.println("ARS: " + "Peso Argentino");
            System.out.println("BOB: " + "Boliviano Boliviano");
            System.out.println("CLP: " + "Peso Chileno");
            System.out.println("COP: " + "Peso Colombiano");

            System.out.println();

            System.out.println("Insira a moeda base: ");
            base_code = sc.next().toUpperCase();

            System.out.println("Insira para qual moeda deseja converter: ");
            target_code = sc.next().toUpperCase();

            Double valor;
            System.out.println("Insira o valor que deseja converter: ");
            valor = sc.nextDouble();

            ConversorService conversorServiceDois = new ConversorService();
            Moedas moedas = conversorServiceDois.getMoedas(base_code, target_code, valor);

            System.out.println(gson.toJson(moedas));
            moedasFiltradas.add(conversorServiceDois.getMoedas(base_code, target_code, valor));

            System.out.println("Deseja verificar outras moedas? " + "1-Sim " + "0-Não");
            laco = sc.nextInt();
            if (laco == 0) {
                System.out.println("Você converteu as seguintes moedas: ");
                for(Moedas m : moedasFiltradas){
                    System.out.println(m);
                }
                System.out.println();
                System.out.println("Até a próxima");
            }
        }

    }

}
