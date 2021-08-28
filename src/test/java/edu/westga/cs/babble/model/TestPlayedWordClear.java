package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests clear method in PlayedWord class.
 * 
 * @author Kimberly Marroquin
 * @version Aug 28, 2021
 */
public class TestPlayedWordClear {

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
	 * Tests clearing an empty word. Should clear empty word.
	 */
	@Test
	public void shouldClearEmptyWord() {
		this.newWord.clear();
		assertEquals("", this.newWord.getHand());
	}

	/**
	 * Tests for clearing a word with one tile.
	 */
	@Test
	public void shouldClearWordWithOneTile() {
		this.newWord.append(new Tile('A'));
		this.newWord.clear();
		assertEquals("", this.newWord.getHand());
	}

	/**
	 * Tests for clearing a word with many tiles.
	 */
	@Test
	public void shouldClearWordWithManyTiles() {
		this.newWord.append(new Tile('A'));
		this.newWord.append(new Tile('L'));
		this.newWord.append(new Tile('P'));
		this.newWord.clear();
		assertEquals("", this.newWord.getHand());
	}

}
