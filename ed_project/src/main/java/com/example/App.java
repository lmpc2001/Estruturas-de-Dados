package com.example;

import java.io.IOException;

import com.example.usecases.exceptions.EmptyMapException;
import com.example.utils.Menu;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws IOException, EmptyMapException {
        Menu.showMainMenu();
    }
}
