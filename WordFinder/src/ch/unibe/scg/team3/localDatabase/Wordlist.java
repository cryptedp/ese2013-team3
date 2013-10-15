package ch.unibe.scg.team3.localDatabase;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @author nils
 * 
 */
public class Wordlist implements Serializable{
	private String name;
	private ArrayList<String> content;

	public Wordlist(String name, ArrayList<String> content) {
		this.name = name;
		this.content = content;
		sort();
	}

	public void addWord(String word) {
		content.add(word);
		sort();
	}

	public void removeWord(String word) {
		content.remove(content.indexOf(word));
	}

	// For sorting the list
	public void sort() {

	}
	@Override
	public String toString() {
		String wordlist = name;
		for (int i = 0; i < content.size(); i++) {
			wordlist = wordlist + ", " + content.get(i);
		}
		return wordlist;
	}

	public String getName() {
		return name;
	}

	public String getContent() {
		//Vorerst unschön geht noch besser mit itteration 
		String wordlist =content.get(0);
		for (int i = 1; i < content.size(); i++) {
			wordlist = wordlist + ", " + content.get(i);
		}
		return wordlist;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void setWordlistFromString(String wordlist){
		content = new ArrayList<String>();
		String[] tmp = wordlist.split(";");
		for(int i = 0;i<tmp.length;i++){
			content.add(tmp[i]);
		}
	}

	
}