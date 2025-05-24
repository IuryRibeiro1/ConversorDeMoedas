package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import org.example.entities.Moedas;
import org.example.principal.Principal;
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

        Principal principal = new Principal();
        principal.converteMoedas();

            logInfo.warning("O programa encerrou");



            }

        }


