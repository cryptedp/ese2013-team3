package ch.unibe.scg.team3.game;

import java.util.ArrayList;

import ch.unibe.scg.team3.board.Board;
import ch.unibe.scg.team3.board.WordSelection;

/**
 * This class provides a rudimental structure of a game. A game can be observed
 * by several observers and thus the responsibility of this class is to add,
 * remove and notify observers.
 * 
 * @author adrian
 * 
 */
public abstract class AbstractGame implements IObservable {

	protected int score;
	protected int guesses;
	protected int wordlistId;
	protected int timesPlayed;
	protected ArrayList<IGameObserver> observers;
	protected ArrayList<WordSelection> found;
	protected boolean isPersonal;

	protected AbstractGame() {
		score = 0;
		guesses = 0;
		wordlistId = 0;
		timesPlayed = 0;
		observers = new ArrayList<IGameObserver>();
		found = new ArrayList<WordSelection>();
	}
	
	protected AbstractGame(final SavedGame game){
		this();
		wordlistId = game.getWordlistId();
		timesPlayed = game.getTimesPlayed();
	}

	/**
	 * @return A clone of the board
	 */
	public abstract Board getBoard();

	/**
	 * @return The size of the board on which the game operates
	 */
	public abstract int getBoardSize();

	public abstract String getTime();

	public abstract boolean isOver();

	@Override
	public void addObserver(IGameObserver observer) {
		observers.add(observer);
	}

	@Override
	public void removeObserver(IGameObserver observer) {
		observers.remove(observer);

	}

	@Override
	public void notifyObservers() {
		for (IGameObserver observer : observers) {
			observer.update(this);
		}
	}

	/**
	 * @return The score of the current game, a positive integer
	 */
	public int getScore() {
		assert score >= 0;
		return score;
	}

	/**
	 * @return The total guesses the player made, positive
	 */
	public int getNumberOfAttempts() {
		return guesses;
	}

	/**
	 * @return The total words the player found, positive
	 */
	public int getNumberOfFoundWords() {
		return found.size();
	}

	/**
	 * @return A list of all the words the player found
	 */
	public ArrayList<WordSelection> getFoundWords() {
		return found;
	}

	/**
	 * Returns the id of the wordlist the game uses. It is corresponding to the
	 * id in the database
	 * 
	 * @return The id of the wordlist
	 */
	public int getWordlistId() {
		return wordlistId;
	}

	public int getTimesPlayed() {
		return timesPlayed;
	}

}
