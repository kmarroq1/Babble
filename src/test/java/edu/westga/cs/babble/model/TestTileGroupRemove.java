package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests remove method in TileGroup class.
 * 
 * @author Kimberly Marroquin
 * @version Aug 27, 2021
 */
public class TestTileGroupRemove {

	TestTileGroupClass newTileGroup;

	/**
	 * Creates new TestTileGroupClass.
	 * 
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUp() throws Exception {
		this.newTileGroup = new TestTileGroupClass();
	}

	/**
	 * Tests for removing null tile from group. Should throw exception.
	 */
	@Test
	public void shouldNotAllowNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			this.newTileGroup.remove(null);
		});
	}

	/**
	 * Tests for removing a tile from an empty group. Should throw exception.
	 */
	@Test
	public void canNotRemoveFromEmptyTileGroup() {
		assertThrows(TileNotInGroupException.class, () -> {
			Tile newTile = new Tile('A');
			this.newTileGroup.remove(newTile);
		});
	}

	/**
	 * Tests for removing a tile that is not in the group.
	 */
	@Test
	public void canNotRemoveTileNotInTileGroup() {
		assertThrows(TileNotInGroupException.class, () -> {
			Tile newTile = new Tile('A');
			this.newTileGroup.append(newTile);

			Tile tileNotInGroup = new Tile('B');
			this.newTileGroup.remove(tileNotInGroup);
		});
	}

	/**
	 * Tests for removing the only tile in a group.
	 * 
	 * @throws TileNotInGroupException
	 */
	@Test
	public void canRemoveOnlyTileInTileGroup() throws TileNotInGroupException {
		Tile newTile = new Tile('A');
		this.newTileGroup.append(newTile);
		this.newTileGroup.remove(newTile);
		assertEquals("", this.newTileGroup.getHand());
	}

	/**
	 * Tests removing the first tile from a group of many.
	 * 
	 * @throws TileNotInGroupException
	 */
	@Test
	public void canRemoveFirstOfManyTilesFromTileGroup() throws TileNotInGroupException {
		Tile aTile = new Tile('A');
		this.newTileGroup.append(aTile);

		Tile bTile = new Tile('B');
		this.newTileGroup.append(bTile);

		Tile cTile = new Tile('C');
		this.newTileGroup.append(cTile);
		this.newTileGroup.remove(aTile);
		assertEquals("BC", this.newTileGroup.getHand());
	}

	/**
	 * Test removing the last tile out of a group of many.
	 * 
	 * @throws TileNotInGroupException
	 */
	@Test
	public void canRemoveLastOfManyTilesFromTileGroup() throws TileNotInGroupException {
		Tile aTile = new Tile('A');
		this.newTileGroup.append(aTile);

		Tile bTile = new Tile('B');
		this.newTileGroup.append(bTile);

		Tile cTile = new Tile('C');
		this.newTileGroup.append(cTile);
		this.newTileGroup.remove(cTile);
		assertEquals("AB", this.newTileGroup.getHand());
	}

	/**
	 * Tests removing the middle tile out of a group of many.
	 * 
	 * @throws TileNotInGroupException
	 */
	@Test
	public void canRemoveMiddleOfManyTilesFromTileGroup() throws TileNotInGroupException {
		Tile aTile = new Tile('A');
		this.newTileGroup.append(aTile);

		Tile bTile = new Tile('B');
		this.newTileGroup.append(bTile);

		Tile cTile = new Tile('C');
		this.newTileGroup.append(cTile);
		this.newTileGroup.remove(bTile);
		assertEquals("AC", this.newTileGroup.getHand());
	}

	/**
	 * Tests removing multiple tiles from a group of many.
	 * 
	 * @throws TileNotInGroupException
	 * 
	 */
	@Test
	public void canRemoveMultipleTilesFromTileGroup() throws TileNotInGroupException {
		Tile aTile = new Tile('A');
		this.newTileGroup.append(aTile);

		Tile bTile = new Tile('B');
		this.newTileGroup.append(bTile);

		Tile cTile = new Tile('C');
		this.newTileGroup.append(cTile);
		this.newTileGroup.remove(cTile);
		this.newTileGroup.remove(aTile);
		assertEquals("B", this.newTileGroup.getHand());
	}

	/**
	 * Tests removing duplicate tiles from group.
	 * 
	 * @throws TileNotInGroupException
	 */
	@Test
	public void doesNotRemoveDuplicateTilesFromTileGroup() throws TileNotInGroupException {
		Tile cTile = new Tile('C');
		this.newTileGroup.append(cTile);

		Tile aTile = new Tile('A');
		this.newTileGroup.append(aTile);

		Tile bTile = new Tile('B');
		this.newTileGroup.append(bTile);

		Tile anotherCTile = new Tile('C');
		this.newTileGroup.append(anotherCTile);
		this.newTileGroup.remove(cTile);
		assertEquals("ABC", this.newTileGroup.getHand());
	}

}
