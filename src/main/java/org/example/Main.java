package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.entities.Moedas;
import service.ConversorService;
import service.ListaService;

import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public class Main {

    private static final Logger logInfo = Logger.getLogger(Main.class.getName());


    public static void main(String[] args) throws IOException, InterruptedException {

        logInfo.setLevel(Level.ALL);

        logInfo.info("O sistema iniciou! ");

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
            logInfo.info("As moedas selecionadas foram adicionadas na lista");

            System.out.println(gson.toJson(moedas));
            moedasFiltradas.add(conversorService.getMoedas(base_code, target_code, valor));

            System.out.println("Deseja verificar outras moedas? " + "1-Sim " + "0-Não");
            laco = sc.nextInt();
            if (laco == 0) {
                logInfo.info("Percorrida a lista e printado todas as moedas selecionadas pelo usuário");
                for (Moedas m : moedasFiltradas) {
                    System.out.println(m);

                }
                logInfo.info("O sistema encerrou! ");

            }
        }

    }

}
