package edu.westga.cs.babble.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import edu.westga.cs.babble.model.EmptyTileBagException;
import edu.westga.cs.babble.model.Tile;
import edu.westga.cs.babble.model.TileBag;
import edu.westga.cs.babble.model.TileRack;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
	private ListView<String> tiles;

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
		this.displayedStarterTiles();
	}

	/**
	 * Sets up both word racks in GUI.
	 */
	private void displayedStarterTiles() {
		TileBag newBag = new TileBag();
		TileRack starterTiles = new TileRack();
		ObservableList<String> tileLetters = FXCollections.observableArrayList();

		try {
			Tile tileDrawn;
			tileDrawn = newBag.drawTile();
			starterTiles.append(tileDrawn);
			tileLetters.add(String.valueOf(tileDrawn.getLetter()));

			Tile secondTile;
			secondTile = newBag.drawTile();
			starterTiles.append(secondTile);
			tileLetters.add(String.valueOf(secondTile.getLetter()));

		} catch (EmptyTileBagException exception) {
			exception.printStackTrace();
		}
		this.tiles.setItems(tileLetters);

		this.tiles.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {

			@Override
			public ListCell<String> call(ListView<String> tiles) {
				return new TileCell();
			}

		});

		System.out.println(starterTiles.getHand());
	}

	static class TileCell extends ListCell<String> {
		public void updateItem(String item, boolean empty) {
			super.updateItem(item, empty);
			super.setText(item);
		}
	}

}
