package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {

	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	private String user = "student";
	private String pass = "student";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;
		String sql = "SELECT id, title, description, release_year, language_id, rental_duration, rental_rate, "
				+ "length, replacement_cost, rating, special_features FROM film where id = ?";

		try (Connection conn = DriverManager.getConnection(URL, user, pass);
				PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setInt(1, filmId);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					film = new Film(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5),
							rs.getInt(6), rs.getDouble(7), rs.getInt(8), rs.getDouble(9), rs.getString(10),
							rs.getString(11));
				}
			} catch (SQLException e) {
				System.err.println("Database error: " + e);
			}
		} catch (SQLException e) {
			System.err.println("Database Error: " + e);
			e.printStackTrace();
		}
		return film;
	}

	@Override
	public List<Film> findFilmByKeyword(String keyword) {
		List<Film> films = new ArrayList<>();
		keyword = keyword.trim();
		keyword = "%" + keyword + "%";
		String sql = "SELECT id, title, description, release_year, language_id, rental_duration, "
				+ "rental_rate, length, replacement_cost, rating, special_features FROM film WHERE (title LIKE ? OR description LIKE ?)";
		try (Connection conn = DriverManager.getConnection(URL, user, pass);
				PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setString(1, keyword);
			ps.setString(2, keyword);
			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					films.add(new Film(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5),
							rs.getInt(6), rs.getDouble(7), rs.getInt(8), rs.getDouble(9), rs.getString(10),
							rs.getString(11)));
				}
			} catch (SQLException e) {
				System.err.println("Database Error: " + e);
			}
		} catch (SQLException e) {
			System.err.println("Database Error: " + e);

		}
		return films;
	}

	@Override
	public String findFilmLanguage(int filmId) {
		String language = null;
		String sql = "SELECT name FROM language WHERE id = ?";
		try (Connection conn = DriverManager.getConnection(URL, user, pass);
				PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setInt(1, filmId);
			try (ResultSet rs = ps.executeQuery();) {
				if(rs.next()) {
					language = rs.getString(1);
				}
			} catch (SQLException e) {
				System.err.println("Database Error: " + e);
			}
		} catch (SQLException e) {
			System.err.println("Database Error: " + e);
		}
		return language;
	}

	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;
		String sql = "SELECT id, first_name, last_name FROM actor where id = ?";
		try (Connection conn = DriverManager.getConnection(URL, user, pass);
				PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setInt(1, actorId);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					actor = new Actor(rs.getInt(1), rs.getString(2), rs.getString(3));
				}
			} catch (SQLException e) {
				System.err.println("Database Error: " + e);
			}
		} catch (SQLException e) {
			System.err.println("Database Error: " + e);
		}
		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();
		String sql = "SELECT a.id, a.first_name, a.last_name FROM actor a JOIN film_actor f on a.id = f.actor_id where f.film_id = ?";
		try (Connection conn = DriverManager.getConnection(URL, user, pass);
				PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setInt(1, filmId);
			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					actors.add(new Actor(rs.getInt(1), rs.getString(2), rs.getString(3)));
				}
			} catch (SQLException e) {
				System.err.println("Database Error " + e);
			}
		} catch (SQLException e) {
			System.err.println("Database Error: " + e);
		}
		return actors;
	}

}
