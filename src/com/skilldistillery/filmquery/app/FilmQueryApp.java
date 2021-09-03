package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
//      app.test();
		app.launch();
	}

	private void test() {
		Film film = db.findFilmById(981);
		film.setActors(db.findActorsByFilmId(981));
		System.out.println(film);
		System.out.println(film.getActors());

	}

	private void launch() {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);
		int id = input.nextInt();
		Film film = db.findFilmById(id);
		if (film != null)
			film.setActors(db.findActorsByFilmId(id));
		if (film != null) {
			System.out.println(film);
			System.out.println(film.getActors());
		}
		input.close();
	}

	private void startUserInterface(Scanner input) {
		System.out.println("Which film id?");
	}

}
