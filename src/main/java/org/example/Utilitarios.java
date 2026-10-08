package org.example;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Scanner;

public class Utilitarios {
    private static final Scanner sc = new Scanner(System.in);
    private static final Gson gson = new Gson();

    public static <T> T retornarUmJson(String url, Class<T> objetoDaClasseModelo) throws IOException, InterruptedException {
        HttpClient cliente = HttpClient
                .newBuilder()
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();

        HttpRequest requisicao = HttpRequest
                .newBuilder()
                .uri(URI.create(url))
                .GET()
                .header("Content-Type","application/json")
                .timeout(Duration.ofSeconds(5))
                .build();

        HttpResponse<String> resposta = cliente.send(requisicao,HttpResponse.BodyHandlers.ofString());

        return gson.fromJson(resposta.body(),objetoDaClasseModelo);
    }

    public static <T> T[] retornarTodosJsons(String url,Class<T[]> arrayDeObjetosDaClasseModelo) throws IOException, InterruptedException {
        HttpClient cliente = HttpClient
                .newBuilder()
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();

        HttpRequest requisicao = HttpRequest
                .newBuilder()
                .uri(URI.create(url))
                .GET()
                .header("Content-Type","application/json")
                .timeout(Duration.ofSeconds(5))
                .build();

        HttpResponse<String> resposta = cliente.send(requisicao,HttpResponse.BodyHandlers.ofString());

        return gson.fromJson(resposta.body(), arrayDeObjetosDaClasseModelo);
    }

    public static String escolherID(){
        String id;
        do {
            System.out.print("Digite o código que representa o id da instituição que você deseja visualizar:");
            id = sc.nextLine();
        }while (id.equalsIgnoreCase(""));

        return id;
    }
}
