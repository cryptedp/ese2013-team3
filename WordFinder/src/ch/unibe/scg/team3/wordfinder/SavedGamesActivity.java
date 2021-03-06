package ch.unibe.scg.team3.wordfinder;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import ch.unibe.scg.team3.game.SavedGame;
import ch.unibe.scg.team3.localDatabase.SavedGamesHandler;

public class SavedGamesActivity extends Activity {
	private ArrayList<SavedGame> games;
	private SavedGamesHandler handler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_saved_games);

		handler = new SavedGamesHandler(this);
		games = handler.getSavedGames();

		ArrayAdapter<SavedGame> adapter = new ArrayAdapter<SavedGame>(this,
				R.layout.saved_games_list_item, games) {

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {

				LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				
				View element = inflater.inflate(R.layout.saved_games_list_item, parent, false);
				
				TextView name = (TextView) element.findViewById(R.id.saved_game_name);
				TextView score = (TextView) element.findViewById(R.id.saved_game_score);
				TextView date = (TextView) element.findViewById(R.id.saved_game_date);
				
				SavedGame game = getItem(position);
				
				name.setText(game.getName());
				score.setText("Score: " + game.getScore());
				date.setText("Date: " + game.getDate());
				registerForContextMenu(element);
				element.setId(position);
				return element;
			}

		};

		ListView list = (ListView) findViewById(R.id.saved_Games_list);
		list.setAdapter(adapter);
	}

	public void viewSavedGame(View view) {
		SavedGame savedGame = games.get(view.getId());
		Intent intent = new Intent(this, ViewSavedGameActivity.class);

		intent.putExtra("saved_game", savedGame);
		startActivity(intent);
	}

	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.setHeaderTitle("Options");
		menu.add(0, v.getId(), 0, "Share");
		menu.add(0, v.getId(), 0, "Delete");
	}

	@SuppressLint("NewApi")
	public boolean onContextItemSelected(MenuItem item) {
		if (item.getTitle() == "Share") {
			
		} else if (item.getTitle() == "Delete") {
			removeGame(findViewById(item.getItemId()));
		} else {
			return false;
		}
		return true;
	}

	@SuppressLint("NewApi")
	private void removeGame(View view) {
		SavedGame savedGame = games.get(view.getId());
		handler.removeGameByName(savedGame.getName());
		recreate();
		
	}

}
