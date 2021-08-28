package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the getScore method in the PlayedWord class.
 * 
 * @author Kimberly Marroquin
 * @version Aug 28, 2021
 */
public class TestPlayedWordGetScore {

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
	 * Tests for an empty word score. Should be zero.
	 */
	@Test
	public void emptyWordShouldHaveScoreOfZero() {
		assertEquals(0, this.newWord.getScore());
	}

	/**
	 * Tests for a one tile word score.
	 */
	@Test
	public void scoreAOneTileWord() {
		this.newWord.append(new Tile('A'));
		assertEquals(1, this.newWord.getScore());
	}

	/**
	 * Tests for a word score with multiple tiles.
	 */
	@Test
	public void scoreAWordWithMultipleDifferingTiles() {
		this.newWord.append(new Tile('A'));
		this.newWord.append(new Tile('L'));
		this.newWord.append(new Tile('P'));
		this.newWord.append(new Tile('S'));
		assertEquals(6, this.newWord.getScore());
	}

	/**
	 * Tests for a word score with duplicate tiles.
	 */
	@Test
	public void scoreAWordContainingDuplicateTiles() {
		this.newWord.append(new Tile('A'));
		this.newWord.append(new Tile('P'));
		this.newWord.append(new Tile('P'));
		this.newWord.append(new Tile('L'));
		this.newWord.append(new Tile('E'));
		assertEquals(9, this.newWord.getScore());
	}

}
