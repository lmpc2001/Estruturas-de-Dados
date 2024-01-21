package com.example;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import com.example.domain.exceptions.InvalidStrategyException;
import com.example.structures.exceptions.ElementNotFoundException;
import com.example.structures.exceptions.EmptyListException;
import com.example.usecases.exceptions.EmptyMapException;
import com.example.utils.Menu;

/**
 * A classe principal do projeto onde se encontra o método principal para
 * inicialização da app
 * 
 * @author Luís Costa [8200737]
 */
public class App {

    /**
     * O método principal para inicialização da aplicação.
     * 
     * @param args Os argumentos da linha de comando (não são utilizados neste
     *             momento).
     * @throws IOException              Se ocorrer um erro de leitura/escrita no
     *                                  ficheiro.
     * @throws EmptyMapException        Se o mapa estiver vazio.
     * @throws ParseException           Se ocorrer um erro de análise durante a
     *                                  execução.
     * @throws ElementNotFoundException Se um elemento não for encontrado.
     * @throws EmptyListException       Se a lista estiver vazia.
     * @throws InvalidStrategyException Se a estratégia escolhida for inválida.
     */
    public static void main(String[] args)
            throws IOException, EmptyMapException, ParseException, ElementNotFoundException, EmptyListException,
            InvalidStrategyException {
        Menu menu = new Menu();

        menu.showMainMenu();
    }
}
