package ch.unibe.scg.team3.game;

import java.util.ArrayList;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import ch.unibe.scg.team3.board.*;
import ch.unibe.scg.team3.gameui.*;
import ch.unibe.scg.team3.localDatabase.WordlistManager;
import ch.unibe.scg.team3.token.*;
import ch.unibe.scg.team3.wordfinder.R;
import ch.unibe.scg.team3.wordlist.Wordlist;

/**
 * The Game class is responsible for the proper execution of the game. 
 * The Game can be observed by the components of the user interface.
 * 
 * @author adrian
 * @author faerber
 */

public class Game implements IObservable {

	private final Board board;
	private final Wordlist found;
	private final WordlistManager data;
	
	private final ArrayList<IGameObserver> observers;
	
	private int score;
	
	private static boolean isOver = false;
	private String wordlistname;

	/**
	 * @param boardSize
	 *            The size of the board must be greater than zero
	 * @param data
	 *            A DataManager to access the database, not null
	 */
	public Game(int boardSize, WordlistManager data) {
		this.data = data;
		observers = new ArrayList<IGameObserver>();

		RandomBoardGenerator rnd = new RandomBoardGenerator(boardSize);
		rnd.generate();
		board = rnd.getBoard();

		found = new Wordlist("Found words");
		score = 0;

	}

	public Game(WordlistManager data) {
		this(Board.DEFAULT_SIZE, data);
	}
	public Game(WordlistManager data, String wordlistid){
		this(Board.DEFAULT_SIZE, data);
		
		try {
			this.wordlistname = data.getWordlists()[Integer
					.parseInt(wordlistid) - 1].toString();
		} catch (NumberFormatException e) {
			e.getStackTrace();
		}
	}

	public void submitPath(Path path) {
		assert path != null;
		
		WordSelection selection = makeSelection(path);

		String word = selection.toString();

		if (!data.isWordInWordlist(word, wordlistname)) {

			path.setColor(R.drawable.not_valid_button_animation);

		} else if (found.contains(word)) {
			path.setColor(R.drawable.already_button_animation);

		} else {
			found.addWord(word);
			path.setColor(R.drawable.valid_button_animation);
			updateScore(selection);
		}
		notifyObservers();
	}

	private void updateScore(WordSelection selection) {
		score += selection.getScore();
	}

	private WordSelection makeSelection(Path path) {

		assert path != null;

		WordSelection selection = new WordSelection();

		for (BoardButton b : path) {
			Point p = b.getCoordinates();
			IToken tok = board.getToken(p.getX(), p.getY());
			selection.addToken(tok);
		}

		return selection;
	}

	public static void setIsOver() {
		isOver = true;
	}

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

	public Board getBoard() {
		return board.clone();
	}

	public int getScore() {
		assert score >= 0;
		return score;
	}

	public ArrayList<String> getFoundWords() {
		return found.getContent();
	}
}