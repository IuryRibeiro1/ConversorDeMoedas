package org.example.principal;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.entities.Moedas;
import service.ConversorService;
import service.ListaService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Principal {

    public void converteMoedas() throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);

        ListaService listaService = new ListaService();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String base_code;
        String target_code;
        String regex = "^[a-zA-Z]{3}$";
        String regexNum = "^\\d+\\.?\\d{1,2}?$";

        List<Moedas> moedasFiltradas = new ArrayList<>();

        Pattern pattern = Pattern.compile(regex);
        Pattern patternNum = Pattern.compile(regexNum);

        while (true) {

            Moedas todasMoedas = listaService.getMoedas();

            List<List<String>> moedasExibicao = todasMoedas.getSupported_codes().stream()
                    .filter(m -> Set.of("ARS", "BOB", "BRL", "CLP", "COP",
                            "USD").contains(m.get(0)))
                    .collect(Collectors.toList());
            moedasExibicao.forEach(moeda ->
                    System.out.println(moeda.get(0) + " - " + moeda.get(1)));

            System.out.println();

            try {
                System.out.println("Insira a moeda base: ");
                base_code = sc.next().toUpperCase();
                Matcher matcherBase = pattern.matcher(base_code);

                System.out.println("Insira para qual moeda deseja converter: ");
                target_code = sc.next().toUpperCase();
                Matcher matcherTarget = pattern.matcher(target_code);

                if (!matcherBase.matches() || !matcherTarget.matches()) {
                    System.out.println("Insira uma moeda correta");

                    continue;
                }

                float valor;
                System.out.println("Insira o valor que deseja converter: ");
                valor = sc.nextFloat();
                System.out.println(String.valueOf(valor));
                Matcher matcherNum = patternNum.matcher(String.valueOf(valor));
                if (!matcherNum.matches()) {
                    System.out.println("Insira uma valor válido");

                    continue;
                }

                ConversorService conversorService = new ConversorService();
                Moedas moedas = conversorService.getMoedas(base_code, target_code, valor);


                System.out.println(gson.toJson(moedas));
                moedasFiltradas.add(conversorService.getMoedas(base_code, target_code, valor));

                System.out.println("Deseja verificar outras moedas? " + "1-Sim " + "0-Não");
                if (sc.nextInt() == 0) {
                    for (Moedas m : moedasFiltradas) {
                        System.out.println(m);

                    }
                    break;
                }

            } catch (RuntimeException e) {
                System.out.println("A execução do programa falhou " + e);
            }

        }

    }
}
