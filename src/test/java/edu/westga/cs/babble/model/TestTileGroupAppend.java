package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the TileGroup class append method.
 * 
 * @author Kimberly Marroquin
 * @version Aug 27, 2021
 */
public class TestTileGroupAppend {

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
	 * Tests for null tile group.
	 */
	@Test
	public void shouldNotAllowNull() {
		Exception nullException = assertThrows(IllegalArgumentException.class, () -> {
			this.newTileGroup.append(null);
		});
		assertEquals("tile cannot be null", nullException.getMessage());
	}

	/**
	 * Tests for initial state of TileGroup, which should be empty.
	 */
	@Test
	public void emptyGroupShouldBeEmpty() {
		assertEquals("", this.newTileGroup.getHand());
	}

	/**
	 * Tests for one tile in group.
	 */
	@Test
	public void shouldHaveOneTileInTileGroup() {
		Tile newTile = new Tile('A');
		this.newTileGroup.append(newTile);
		assertEquals("A", this.newTileGroup.getHand());
	}

	/**
	 * Tests for many tiles in group.
	 */
	@Test
	public void shouldHaveManyTilesInTileGroup() {
		Tile aTile = new Tile('A');
		this.newTileGroup.append(aTile);

		Tile bTile = new Tile('B');
		this.newTileGroup.append(bTile);

		Tile cTile = new Tile('C');
		this.newTileGroup.append(cTile);
		assertEquals("ABC", this.newTileGroup.getHand());
	}

	/**
	 * Tests form many tiles in group, including duplicate tiles.
	 */
	@Test
	public void shouldHaveManyTilesIncludingDuplicatesInTileGroup() {
		Tile aTile = new Tile('A');
		this.newTileGroup.append(aTile);

		Tile bTile = new Tile('B');
		this.newTileGroup.append(bTile);

		Tile cTile = new Tile('C');
		this.newTileGroup.append(cTile);
		
		Tile anotherCTile = new Tile('C');
		this.newTileGroup.append(anotherCTile);
		assertEquals("ABCC", this.newTileGroup.getHand());
	}

	/**
	 * Tests for duplicate tiles in group.
	 */
	@Test
	public void canNotAddSameTileTwice() {
		Tile newTile = new Tile('A');
		Exception nullException = assertThrows(IllegalArgumentException.class, () -> {
			this.newTileGroup.append(newTile);
			this.newTileGroup.append(newTile);
		});
		assertEquals("can not add same tile twice", nullException.getMessage());
	}

}
