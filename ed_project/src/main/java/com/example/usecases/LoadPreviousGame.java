package com.example.usecases;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

import com.example.domain.Game;
import com.example.structures.exceptions.ElementNotFoundException;
import com.example.utils.JSON;

public class LoadPreviousGame {
	public static Game execute() throws FileNotFoundException, IOException, ParseException, ElementNotFoundException {
		Game resumeGame = JSON.resumeGame();

		return resumeGame;
	}
}
