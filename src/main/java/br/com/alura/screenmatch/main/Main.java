package br.com.alura.screenmatch.main;

import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.service.ConsumoAPI;
import br.com.alura.screenmatch.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private List<DadosTemporada> temporadas = new ArrayList<>();
    private ConverteDados conversor = new ConverteDados();
    private ConsumoAPI consumo = new ConsumoAPI();
    private Scanner scanner = new Scanner(System.in);
    private final String ENDERECO = "http://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=e07ceec0";

    public void exibeMenu(){
        System.out.println("Digite o nome da série: ");
        var nomeSerie = scanner.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dados);

        for (int i = 1; i < dados.totalTemporadas(); i++) {
            json = consumo.obterDados("http://www.omdbapi.com/?t=simpsons&season=" + i + "&apikey=e07ceec0");
            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }
        temporadas.forEach(System.out::println);
    }
}
