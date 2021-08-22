package edu.westga.cs.babble.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import edu.westga.cs.babble.model.EmptyTileBagException;
import edu.westga.cs.babble.model.PlayedWord;
import edu.westga.cs.babble.model.Tile;
import edu.westga.cs.babble.model.TileBag;
import edu.westga.cs.babble.model.TileNotInGroupException;
import edu.westga.cs.babble.model.TileRack;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * Defines the Babble GUI behavior.
 * 
 * @author Kimberly Marroquin
 * @version Aug 18, 2021
 */
public class BabbleController implements Initializable {
	private PlayedWord playedTiles;
	private TileRack starterTiles;
	@FXML
	private ListView<Tile> tiles;

	@FXML
	private ListView<Tile> wordCreated;

	@FXML
	private Button resetButton;

	@FXML
	private Button playWordButton;

	@FXML
	private TextField currentScore;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.starterTiles = new TileRack();
		this.playedTiles = new PlayedWord();
		this.displayStarterTiles();
		this.displayWordCreatedTiles();
	}

	/**
	 * Sets up starter tiles rack.
	 */
	private void displayStarterTiles() {
		TileBag newBag = new TileBag();
		Tile tileDrawn;

		try {
			do {
				tileDrawn = newBag.drawTile();
				this.starterTiles.append(tileDrawn);
			} while (this.starterTiles.getNumberOfTilesNeeded() > 0);

		} catch (EmptyTileBagException exception) {
			exception.printStackTrace();
		}

		this.tiles.setItems(this.starterTiles.tiles());

		this.tiles.setCellFactory(new Callback<ListView<Tile>, ListCell<Tile>>() {
			@Override
			public ListCell<Tile> call(ListView<Tile> tiles) {
				return new TileCell();
			}

		});

		this.tiles.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				Tile tileSelected = BabbleController.this.tiles.getSelectionModel().getSelectedItem();
				try {
					BabbleController.this.starterTiles.remove(tileSelected);
					BabbleController.this.playedTiles.append(tileSelected);
				} catch (IllegalArgumentException exception) {
					return;
				} catch (TileNotInGroupException exception) {
					return;
				}
				BabbleController.this.wordCreated.setItems(BabbleController.this.playedTiles.tiles());
			}
		});
	}

	private void displayWordCreatedTiles() {
		this.wordCreated.setCellFactory(new Callback<ListView<Tile>, ListCell<Tile>>() {
			@Override
			public ListCell<Tile> call(ListView<Tile> tiles) {
				return new PlayedTileCell();
			}
		});

		this.wordCreated.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {

			}
		});
	}

	static class TileCell extends ListCell<Tile> {
		@Override
		public void updateItem(Tile item, boolean empty) {
			super.updateItem(item, empty);
			if (empty || item == null) {
				super.setText(null);
				super.setGraphic(null);
			} else {
				super.setText(String.valueOf(item.getLetter()));
			}
		}
	}

	static class PlayedTileCell extends ListCell<Tile> {
		@Override
		public void updateItem(Tile item, boolean empty) {
			super.updateItem(item, empty);
			if (empty || item == null) {
				super.setText(null);
				super.setGraphic(null);
			} else {
				super.setText(String.valueOf(item.getLetter()));
			}
		}
	}

}
