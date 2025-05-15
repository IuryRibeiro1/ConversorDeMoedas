package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import org.example.entities.Moedas;
import service.ConversorService;
import service.ListaService;

import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {

    private static final Logger logInfo = Logger.getLogger(ListaService.class.getName());


    public static void main(String[] args) throws IOException, InterruptedException {

        logInfo.setLevel(Level.ALL);

        logInfo.info("O sistema iniciou! ");

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

                if(!matcherBase.matches() || !matcherTarget.matches()){
                    System.out.println("Insira uma moeda correta");

                    continue;
                }

                float valor;
                System.out.println("Insira o valor que deseja converter: ");
                valor = sc.nextFloat();
                System.out.println(String.valueOf(valor));
                Matcher matcherNum = patternNum.matcher(String.valueOf(valor));
                if(!matcherNum.matches()){
                    System.out.println("Insira uma valor válido");

                    continue;
                }

                ConversorService conversorService = new ConversorService();
                Moedas moedas = conversorService.getMoedas(base_code, target_code, valor);
                logInfo.info("As moedas selecionadas foram adicionadas na lista");

                System.out.println(gson.toJson(moedas));
                moedasFiltradas.add(conversorService.getMoedas(base_code, target_code, valor));

                System.out.println("Deseja verificar outras moedas? " + "1-Sim " + "0-Não");
                if (sc.nextInt() == 0) {
                    logInfo.info("Percorrida a lista e printado todas as moedas selecionadas pelo usuário");
                    for (Moedas m : moedasFiltradas) {
                        System.out.println(m);

                    }
                    logInfo.info("O sistema encerrou! ");
                    break;
                }

                } catch(RuntimeException e){
                    System.out.println("A execução do programa falhou " + e);
                }

            logInfo.warning("Ocorreu um erro no programa");



            }

        }

}
