package com.skilldistillery.filmquery.database;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

class DatabaseAccessTests {
  private DatabaseAccessor db;

  @BeforeEach
  void setUp() throws Exception {
    db = new DatabaseAccessorObject();
  }

  @AfterEach
  void tearDown() throws Exception {
    db = null;
  }

  @Test
  @DisplayName("Invalid film id returns null film")
  void test1() {
    Film f = db.findFilmById(-42);
    assertNull(f);
  }

  @Test
  @DisplayName("Valid film id returns correct film")
  void test2() {
	  Film f = db.findFilmById(1);
	  assertEquals("ACADEMY DINOSAUR", f.getTitle());
  }
  
  @Test
  @DisplayName("Invalid actor id returns null actor")
  void test3() {
	  Actor a = db.findActorById(-5);
	  assertNull(a);
  }
  @Test
  @DisplayName("Valid actor id returns correct actor")
  void test4() {
	  Actor a = db.findActorById(100);
	  assertEquals("Spencer Depp", a.getFirstName()+" "+a.getLastName());
  }
  @Test
  @DisplayName("Invalid language id returns null language")
  void test5() {
	  String language = db.findFilmLanguage(-1);
	  assertNull(language);
  }
  @Test
  @DisplayName("Valid language id returns correct language")
  void test6() {
	  String language = db.findFilmLanguage(3);
	  assertEquals("Japanese", language);
  }
  @Test
  @DisplayName("Invalid film id returns null category")
  void test7() {
	  String category = db.findCategoriesByFilmId(-1);
	  assertNull(category);
  }
  @Test
  @DisplayName("Valid film id returns correct category")
  void test8() {
	  String category = db.findCategoriesByFilmId(981);
	  assertEquals("Travel", category);
  }
  @Test
  @DisplayName("Invalid film id returns empty List<Actor>")
  void test9() {
	  List<Actor> actors = db.findActorsByFilmId(-1);
	  assertTrue(actors.isEmpty());
  }
  @Test
  @DisplayName("Valid film id returns correct List<Actor>")
  void test10() {
	  List<Actor> actors = db.findActorsByFilmId(981);
	  assertTrue(actors.size() == 7);
  }
}

//
//public List<Film> findFilmByKeyword(String keyword);
//public List<String> findFilmInInventoryByFilmId(int filmId);