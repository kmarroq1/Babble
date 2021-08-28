package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the getNumberOfTilesNeeded method in TileRack.
 * 
 * @author Kimberly Marroquin
 * @version Aug 27, 2021
 */
public class TestTileRackGetNumberOfTilesNeeded {

	TileRack newTileRack;
	TileBag newBag;

	/**
	 * Creates new TileRack.
	 * 
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUp() throws Exception {
		this.newTileRack = new TileRack();
		this.newBag = new TileBag();
	}

	/**
	 * Tests for tiles needed in empty rack.
	 */
	@Test
	public void emptyTileRackShouldNeedMaxSizeNumberOfTiles() {
		assertEquals(7, this.newTileRack.getNumberOfTilesNeeded());
	}

	/**
	 * Tests for tiles needed in rack with one tile.
	 * @throws EmptyTileBagException 
	 * @throws TileRackFullException 
	 */
	@Test
	public void tileRackWithOneTileShouldNeedMaxSizeMinusOneTiles() throws TileRackFullException, EmptyTileBagException {
		this.newTileRack.append(this.newBag.drawTile());
		assertEquals(6, this.newTileRack.getNumberOfTilesNeeded());
	}

	/**
	 * Tests for tiles needed in rack with several tiles.
	 * @throws EmptyTileBagException 
	 * @throws TileRackFullException 
	 */
	@Test
	public void tileRackWithSeveralTilesShouldNeedSomeTiles() throws TileRackFullException, EmptyTileBagException {
		this.newTileRack.append(this.newBag.drawTile());
		this.newTileRack.append(this.newBag.drawTile());
		this.newTileRack.append(this.newBag.drawTile());
		assertEquals(4, this.newTileRack.getNumberOfTilesNeeded());
	}

	/**
	 * Tests for tiles needed in full rack.
	 * 
	 * @throws EmptyTileBagException
	 * @throws TileRackFullException
	 */
	@Test
	public void fullRackNeedsZeroTiles() throws TileRackFullException, EmptyTileBagException {
		this.newTileRack.append(this.newBag.drawTile());
		this.newTileRack.append(this.newBag.drawTile());
		this.newTileRack.append(this.newBag.drawTile());
		this.newTileRack.append(this.newBag.drawTile());
		this.newTileRack.append(this.newBag.drawTile());
		this.newTileRack.append(this.newBag.drawTile());
		this.newTileRack.append(this.newBag.drawTile());
		assertEquals(0, this.newTileRack.getNumberOfTilesNeeded());
	}

}
