package edu.westga.cs.babble.model;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests matches method in PlayedWord class.
 * 
 * @author Kimberly Marroquin
 * @version Aug 28, 2021
 */
public class TestPlayedWordMatches {

	PlayedWord newWord;

	/**
	 * Creates new PlayedWord.
	 * 
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUp() throws Exception {
		this.newWord = new PlayedWord();
	}

	/**
	 * Tests that a played word can make multi-letter word.
	 */
	@Test
	public void hasTilesForAMultipleLetterWord() {
		this.newWord.append(new Tile('A'));
		this.newWord.append(new Tile('L'));
		this.newWord.append(new Tile('P'));
		this.newWord.append(new Tile('S'));
		assertTrue(this.newWord.matches("ALPS"));
	}

	/**
	 * Tests that played word can make a single letter word.
	 */
	@Test
	public void hasTilesForASingleLetterWord() {
		this.newWord.append(new Tile('A'));
		assertTrue(this.newWord.matches("A"));
	}

	/**
	 * Tests that you can't make a word when tiles are scrambled.
	 */
	@Test
	public void cannotMatchWordWhenTilesAreScrambled() {
		this.newWord.append(new Tile('L'));
		this.newWord.append(new Tile('P'));
		this.newWord.append(new Tile('A'));
		assertFalse(this.newWord.matches("ALP"));
	}

	/**
	 * Tests that you can't make a word if there are not enough tiles.
	 */
	@Test
	public void cannotMatchWordIfInsufficientTiles() {
		this.newWord.append(new Tile('A'));
		this.newWord.append(new Tile('L'));
		assertFalse(this.newWord.matches("ALPS"));
	}

	/**
	 * Tests that you can match a word with duplicate letters.
	 */
	@Test
	public void canMatchWordWithDuplicateLetters() {
		this.newWord.append(new Tile('A'));
		this.newWord.append(new Tile('P'));
		this.newWord.append(new Tile('P'));
		this.newWord.append(new Tile('L'));
		this.newWord.append(new Tile('E'));
		assertTrue(this.newWord.matches("APPLE"));
	}

	/**
	 * Tests that a word can't match an empty text.
	 */
	@Test
	public void nonEmptyWordShouldNotMatchEmptyText() {
		this.newWord.append(new Tile('A'));
		this.newWord.append(new Tile('P'));
		this.newWord.append(new Tile('P'));
		this.newWord.append(new Tile('L'));
		this.newWord.append(new Tile('E'));
		assertFalse(this.newWord.matches(""));
	}

	/**
	 * Tests that an empty word can't match empty text.
	 */
	@Test
	public void emptyWordShouldNotMatchEmptyText() {
		assertFalse(this.newWord.matches(""));
	}

	/**
	 * Tests for null text in match method.
	 */
	@Test
	public void shouldNotAllowNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			this.newWord.matches(null);
		});
	}

}
