package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

/**
 * Tests tile class constructor.
 * 
 * @author Kimberly Marroquin
 * @version Aug 26, 2021
 */
public class TestTileConstructor {
	Tile newTile;

	/**
	 * Tests for a non-letter tile. Throws exception.
	 */
	@Test
	public void shouldNotAllowNonLetters() {
		Exception thrownException = assertThrows(IllegalArgumentException.class, () -> {
			new Tile('1');
		});
		assertEquals("letter must be between A and Z", thrownException.getMessage());
	}

	/**
	 * Tests all one point tiles.
	 */
	@Test
	public void shouldCreateOnePointTiles() {
		String onePointLetters = "EAIONRTLSU";
		for (int index = 0; index < onePointLetters.length(); index++) {
			this.newTile = new Tile(onePointLetters.charAt(index));
			assertEquals(1, this.newTile.getPointValue());
		}
	}

	/**
	 * Tests all two point tiles.
	 */
	@Test
	public void shouldCreateTwoPointTiles() {
		String twoPointLetters = "DG";
		for (int index = 0; index < twoPointLetters.length(); index++) {
			this.newTile = new Tile(twoPointLetters.charAt(index));
			assertEquals(2, this.newTile.getPointValue());
		}
	}

	/**
	 * Tests all three point tiles.
	 */
	@Test
	public void shouldCreateThreePointTiles() {
		String threePointLetters = "BCMP";
		for (int index = 0; index < threePointLetters.length(); index++) {
			this.newTile = new Tile(threePointLetters.charAt(index));
			assertEquals(3, this.newTile.getPointValue());
		}
	}

	/**
	 * Tests all four point tiles.
	 */
	@Test
	public void shouldCreateFourPointTiles() {
		String fourPointLetters = "FHVWY";
		for (int index = 0; index < fourPointLetters.length(); index++) {
			this.newTile = new Tile(fourPointLetters.charAt(index));
			assertEquals(4, this.newTile.getPointValue());
		}
	}

	/**
	 * Tests all five point tiles.
	 */
	@Test
	public void shouldCreateFivePointTiles() {
		String fivePointLetters = "K";
		for (int index = 0; index < fivePointLetters.length(); index++) {
			this.newTile = new Tile(fivePointLetters.charAt(index));
			assertEquals(5, this.newTile.getPointValue());
		}
	}

	/**
	 * Tests all eight point tiles.
	 */
	@Test
	public void shouldCreateEightPointTiles() {
		String eightPointLetters = "JX";
		for (int index = 0; index < eightPointLetters.length(); index++) {
			this.newTile = new Tile(eightPointLetters.charAt(index));
			assertEquals(8, this.newTile.getPointValue());
		}
	}

	/**
	 * Tests all ten point tiles.
	 */
	@Test
	public void shouldCreateTenPointTiles() {
		String tenPointLetters = "QZ";
		for (int index = 0; index < tenPointLetters.length(); index++) {
			this.newTile = new Tile(tenPointLetters.charAt(index));
			assertEquals(10, this.newTile.getPointValue());
		}
	}

}
