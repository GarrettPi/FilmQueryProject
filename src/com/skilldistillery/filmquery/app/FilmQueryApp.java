package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
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
		Scanner sc = new Scanner(System.in);

		startUserInterface(sc);
		sc.close();
		exitProgram();
	}

	private void startUserInterface(Scanner sc) {

		System.out.println("Welcome to videos are we.");
		while (true) {
			System.out.println("\nPlease make a selection:");
			System.out.println("1. Look up a film by its ID" + "\n2. Look up a film by a search keyword"
					+ "\n3. Exit the Application");
			try {
				int input = sc.nextInt();
				switch (input) {
				case 1:
					Film film = findFilmById(sc);
					if(film != null) filmSubMenu(film, sc);
					break;
				case 2:
					findFilmByKeyword(sc);
					break;
				case 3:
					return;
				default:
					System.out.println("Please only choose a number 1-3");
				}

			} catch (InputMismatchException e) {
				System.out.println("That was not a correct entry.  Please enter either a 1, 2, or 3");

			}
		}

	}
	
	private void filmSubMenu(Film film, Scanner sc) {
		System.out.println("\nWould you like more details about the film?");
		System.out.println("1. Yes\n2. No");
		try{
			int input = sc.nextInt();
			sc.nextLine();
			if(input == 1) {
				System.out.println(film.fullDisplay());
				System.out.println("Category: "+db.findCategoriesByFilmId(film.getId()));
				System.out.println("All available inventory: ");
				for (String string : db.findFilmInInventoryByFilmId(film.getId())) {
					System.out.println(string);
				}
			}
			else return;
		} catch (InputMismatchException e) {
			System.out.println("Incorrect entry.  Returning to menu");
			sc.nextLine();
			return;
		}
	}

	private Film findFilmById(Scanner sc) {
		System.out.println("What is the ID of the movie you'd like to look up?");
		int input = sc.nextInt();
		Film film = db.findFilmById(input);
		if (film == null) {
			System.out.println("Im sorry, that film doesn't exist.  Please try again");
		} else {
			film.setActors(db.findActorsByFilmId(input));
			System.out.println(film.displayString());
			System.out.println("Language: " + db.findFilmLanguage(film.getLanguageId()));
			System.out.println("Cast: ");
			for (Actor actor : film.getActors()) {
				System.out.println(actor.displayString());
			}

		}
		return film;
	}

	private void findFilmByKeyword(Scanner sc) {
		System.out.println("What is the keyword you'd like to search for?");
		sc.nextLine();
		String keyword = sc.nextLine();
		List<Film> films = db.findFilmByKeyword(keyword);
		if (films.size() < 1) {
			System.out.println("I'm sorry, no films found using that keyword.  Please try again");
			return;
		} else {
			for (Film film : films) {
				film.setActors(db.findActorsByFilmId(film.getId()));
				System.out.println(film.displayString());
				System.out.println("Language: " + db.findFilmLanguage(film.getLanguageId()));
				System.out.println("Cast: ");
				for(Actor actor : film.getActors()) {
					System.out.println(actor.displayString());
				}
			}
		}
	}

	private void exitProgram() {
		System.out.println("Thank you for trying out this program.  Goodbye!");
		System.exit(1);
	}

}
