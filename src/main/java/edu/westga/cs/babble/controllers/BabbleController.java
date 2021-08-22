package edu.westga.cs.babble.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import edu.westga.cs.babble.model.EmptyTileBagException;
import edu.westga.cs.babble.model.Tile;
import edu.westga.cs.babble.model.TileBag;
import edu.westga.cs.babble.model.TileRack;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

/**
 * Defines the Babble GUI behavior.
 * 
 * @author Kimberly Marroquin
 * @version Aug 18, 2021
 */
public class BabbleController implements Initializable {
	@FXML
	private ListView<Tile> tiles;

	@FXML
	private ListView<String> wordCreated;

	@FXML
	private Button resetButton;

	@FXML
	private Button playWordButton;

	@FXML
	private TextField currentScore;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.displayStarterTiles();
	}

	/**
	 * Sets up starter tiles rack.
	 */
	private void displayStarterTiles() {
		TileBag newBag = new TileBag();
		TileRack starterTiles = new TileRack();

		try {
			Tile tileDrawn;
			do {
				tileDrawn = newBag.drawTile();
				starterTiles.append(tileDrawn);
			} while (starterTiles.getNumberOfTilesNeeded() > 0);

		} catch (EmptyTileBagException exception) {
			exception.printStackTrace();
		}

		this.tiles.setItems(starterTiles.tiles());

		this.tiles.setCellFactory(new Callback<ListView<Tile>, ListCell<Tile>>() {
			@Override
			public ListCell<Tile> call(ListView<Tile> tiles) {
				return new TileCell();
			}

		});
	}

	static class TileCell extends ListCell<Tile> {
		@Override
		public void updateItem(Tile item, boolean empty) {
			super.updateItem(item, empty);
			if (!empty && item != null) {
				super.setText(String.valueOf(item.getLetter()));
			}
		}
	}

}
