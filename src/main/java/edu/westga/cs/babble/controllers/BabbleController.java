package edu.westga.cs.babble.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import edu.westga.cs.babble.model.EmptyTileBagException;
import edu.westga.cs.babble.model.PlayedWord;
import edu.westga.cs.babble.model.Tile;
import edu.westga.cs.babble.model.TileBag;
import edu.westga.cs.babble.model.TileNotInGroupException;
import edu.westga.cs.babble.model.TileRack;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javafx.util.converter.NumberStringConverter;

/**
 * Defines the Babble GUI behavior.
 * 
 * @author Kimberly Marroquin
 * @version Aug 18, 2021
 */
public class BabbleController implements Initializable {
	private PlayedWord playedTiles;
	private TileRack starterTiles;
	private int scoreTracker;
	private SimpleIntegerProperty scoreProperty;
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
		this.resetButton();
		this.playWordButton();
		this.scoreProperty = new SimpleIntegerProperty(0);
		this.scoreTracker = 0;
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

	/**
	 * Displays tile rack showing word created by user.
	 */
	private void displayWordCreatedTiles() {
		this.wordCreated.setCellFactory(new Callback<ListView<Tile>, ListCell<Tile>>() {
			@Override
			public ListCell<Tile> call(ListView<Tile> tiles) {
				return new TileCell();
			}
		});

		this.wordCreated.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				Tile tileSelected = BabbleController.this.wordCreated.getSelectionModel().getSelectedItem();
				try {
					BabbleController.this.playedTiles.remove(tileSelected);
					BabbleController.this.starterTiles.append(tileSelected);
				} catch (IllegalArgumentException exception) {
					return;
				} catch (TileNotInGroupException exception) {
					return;
				}
				BabbleController.this.tiles.setItems(BabbleController.this.starterTiles.tiles());
			}
		});
	}

	/**
	 * Sets an action for the play word button. The play word button allows the user
	 * to check if the word they assembled is an actual word.
	 */
	private void playWordButton() {
		WordDictionary newDictionary = new WordDictionary();

		this.playWordButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				if (newDictionary.isValidWord(BabbleController.this.playedTiles.getHand())) {
					BabbleController.this.updateScore();
					BabbleController.this.startNewGame();
				} else {
					Dialog<String> invalidWordAlert = new Dialog<String>();
					invalidWordAlert.setTitle("Message");
					invalidWordAlert.setContentText("Not a valid word");
					invalidWordAlert.getDialogPane().getButtonTypes().add(new ButtonType("Ok", ButtonData.OK_DONE));
					invalidWordAlert.showAndWait();
				}
			}

		});
	}

	/**
	 * Starts a new game.
	 */
	private void startNewGame() {
		this.starterTiles.tiles().clear();
		this.playedTiles.clear();
		this.displayStarterTiles();
	}

	/**
	 * Updates the score displayed.
	 */
	private void updateScore() {
		this.scoreTracker += this.playedTiles.getScore();
		this.scoreProperty.set(this.scoreTracker);
		this.currentScore.textProperty().bindBidirectional(this.scoreProperty, new NumberStringConverter());
	}

	/**
	 * Sets an action for the reset button. The reset button moves all the tiles in
	 * the 'your word' rack back to the starter tile rack.
	 */
	private void resetButton() {
		this.resetButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			/**
			 * Resets current game.
			 */
			@Override
			public void handle(MouseEvent arg0) {
				for (int index = 0; index < BabbleController.this.playedTiles.tiles().size(); index++) {
					Tile currentTile = BabbleController.this.playedTiles.tiles().get(index);
					BabbleController.this.starterTiles.append(currentTile);
				}
				BabbleController.this.playedTiles.clear();
			}

		});
	}

	static class TileCell extends ListCell<Tile> {
		/**
		 * Updates the GUI.
		 */
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
