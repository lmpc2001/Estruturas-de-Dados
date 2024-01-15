package com.example;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import com.example.structures.exceptions.ElementNotFoundException;
import com.example.structures.exceptions.EmptyListException;
import com.example.usecases.exceptions.EmptyMapException;
import com.example.utils.Menu;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args)
            throws IOException, EmptyMapException, ParseException, ElementNotFoundException, EmptyListException {
        Menu menu = new Menu();

        menu.showMainMenu();
    }
}
