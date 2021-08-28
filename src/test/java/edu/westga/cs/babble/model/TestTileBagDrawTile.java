package edu.westga.cs.babble.model;

import static java.util.Map.entry;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests draw tile method in TileBag class.
 * 
 * @author Kimberly Marroquin
 * @version Aug 27, 2021
 */
public class TestTileBagDrawTile {

	TileBag newBag;

	/**
	 * Creates a new TileBag.
	 * 
	 * @throws Exception
	 */
	@BeforeEach
	public void setUp() throws Exception {
		this.newBag = new TileBag();
	}

	@Test
	public void canDrawAllTiles() throws EmptyTileBagException {
		for (int index = 0; index < 98; index++) {
			this.newBag.drawTile();
		}
		assertTrue(this.newBag.isEmpty());
	}

	/**
	 * Tests for drawing more than 98 tiles.
	 */
	@Test
	public void canNotDrawTooManyTiles() {
		assertThrows(EmptyTileBagException.class, () -> {
			for (int index = 0; index < 99; index++) {
				this.newBag.drawTile();
			}
		});
	}

	/**
	 * Tests that there is the right tile distribution.
	 * 
	 * @throws EmptyTileBagException
	 */
	@Test
	public void hasProperTileDistribution() throws EmptyTileBagException {
		ArrayList<Character> tiles = new ArrayList<>();
		for (int index = 0; index < 98; index++) {
			tiles.add(this.newBag.drawTile().getLetter());
		}
		Map<Character, Integer> expectedCharacterDistributions = Map.ofEntries(entry('A', 9), entry('B', 2),
				entry('C', 2), entry('D', 4), entry('E', 12), entry('F', 2), entry('G', 3), entry('H', 2),
				entry('I', 9), entry('J', 1), entry('K', 1), entry('L', 4), entry('M', 2), entry('N', 6), entry('O', 8),
				entry('P', 2), entry('Q', 1), entry('R', 6), entry('S', 4), entry('T', 6), entry('U', 4), entry('V', 2),
				entry('W', 2), entry('X', 1), entry('Y', 2), entry('Z', 1));
		for (Entry<Character, Integer> entry : expectedCharacterDistributions.entrySet()) {
			Character character = entry.getKey();
			int expectedNumber = entry.getValue();

			long numberOfCharacters = tiles.stream().filter(tileCharacter -> tileCharacter.equals(character)).count();
			assertEquals(expectedNumber, numberOfCharacters);
		}
	}

}
