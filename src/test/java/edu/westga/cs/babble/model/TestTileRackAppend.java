package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Tests append method from TileRack class.
 * 
 * @author Kimberly Marroquin
 * @version Aug 27, 2021
 */
public class TestTileRackAppend {

	/**
	 * Tests for appending to a full tile rack. Should throw exception.
	 */
	@Test
	public void shouldNotAppendToFullRack() {
		TileRack newTileRack = new TileRack();
		TileBag newBag = new TileBag();

		assertThrows(TileRackFullException.class, () -> {
			do {
				newTileRack.append(newBag.drawTile());
			} while (newTileRack.getNumberOfTilesNeeded() + 1 > 0);
		});
	}

}
