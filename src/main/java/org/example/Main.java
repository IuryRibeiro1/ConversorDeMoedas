package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.entities.Moedas;
import service.ConversorService;
import service.ListaService;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


public class Main {


    public static void main(String[] args) throws IOException, InterruptedException {


        Scanner sc = new Scanner(System.in);

        ListaService listaService = new ListaService();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String base_code;
        String target_code;

        List<Moedas> moedasFiltradas = new ArrayList<>();

        int laco = 1;
        while (laco != 0) {

            Moedas todasMoedas = listaService.getMoedas();

            List<List<String>> moedasExibicao = todasMoedas.getSupported_codes().stream()
                    .filter(m -> Set.of("ARS", "BOB", "BRL", "CLP", "COP",
                            "USD").contains(m.get(0)))
                    .collect(Collectors.toList());
            moedasExibicao.forEach(moeda ->
                    System.out.println(moeda.get(0) + " - " + moeda.get(1)));

            System.out.println();
            System.out.println("Insira a moeda base: ");
            base_code = sc.next().toUpperCase();

            System.out.println("Insira para qual moeda deseja converter: ");
            target_code = sc.next().toUpperCase();

            Double valor;
            System.out.println("Insira o valor que deseja converter: ");
            valor = sc.nextDouble();

            ConversorService conversorService = new ConversorService();
            Moedas moedas = conversorService.getMoedas(base_code, target_code, valor);

            System.out.println(gson.toJson(moedas));
            moedasFiltradas.add(conversorService.getMoedas(base_code, target_code, valor));

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
