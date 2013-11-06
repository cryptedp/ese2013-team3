package ch.unibe.scg.team3.game;

import java.io.Serializable;

import ch.unibe.scg.team3.board.Board;

public class SavedGame extends AbstractGame implements Serializable{
	
	private static final long serialVersionUID = 5916020210779611232L;
	
	private int id;
	private String name;
	private String time;
	private String date;
	private boolean isPrivate;
	private int timesPlayed;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumberOfFoundWords() {
		return found.size();
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getWordlistId() {
		return wordlistId;
	}

	public void setWordlistId(int wordlistId) {
		this.wordlistId = wordlistId;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean isPrivate() {
		return isPrivate;
	}

	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	public int getTimesPlayed() {
		return timesPlayed;
	}

	public void setTimesPlayed(int timesPlayed) {
		this.timesPlayed = timesPlayed;
	}

	public void setGuesses(int guesses) {
		this.guesses = guesses;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	@Override
	public boolean isOver() {
		return true;
	}

}
