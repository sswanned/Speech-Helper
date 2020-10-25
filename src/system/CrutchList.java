package system;

import java.util.ArrayList;
import java.util.Iterator;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CrutchList implements Iterable<String> {

	private class CrutchWord {

		private String word;
		private boolean delete;
		private ArrayList<String> alternates;

		private CrutchWord(String word, boolean delete) {
			this.word = word;
			this.delete = delete;
			alternates = new ArrayList<String>();
		}

		@Override
		public String toString() {
			return word;
		}

		public String listAlternates() {
			String list = "";

			for (String str : alternates) {
				list += str + " ";
			}

			return list.strip();
		}

	}

	private ArrayList<CrutchWord> crutchList;

	public CrutchList() {
		crutchList = new ArrayList<CrutchWord>();
	}

	public CrutchList(String path) throws FileNotFoundException {
		this(); 
		Scanner stream = new Scanner(new File(path));

		while (stream.hasNextLine()) {
			String line = stream.nextLine().toLowerCase().strip();
			String[] list = line.split("/");
			addWord(list[0], list[1].equals("yes") ? true : false); 
			if (list.length > 2) {
				addAlternate(list[0], list[2].split(", "));
			}
		}
		
		stream.close();
	}

	@Override
	public String toString() {
		String str = "";

		for (CrutchWord cw : crutchList) {
			str += cw.toString() + " ";
		}

		return str.strip();
	}

	public int getSize() {
		return crutchList.size();
	}

	public void addWord(String word, boolean delete) {
		for (CrutchWord cw : crutchList) {
			if (cw.toString().equals(word)) {
				return;
			}
		}

		crutchList.add(new CrutchWord(word, delete));
	}
	
	public void deleteWord(String word) {
		for (CrutchWord cw : crutchList) {
			if (cw.toString().equals(word)) {
				crutchList.remove(cw);
				return;
			}
		}

	}

	public void setDelete(String word, boolean delete) {
		for (CrutchWord cw : crutchList) {
			if (cw.toString().equals(word)) {
				cw.delete = delete;
				return;
			}
		}
	}

	public void addAlternate(String word, String[] alts) {
		for (CrutchWord cw : crutchList) {
			if (cw.toString().equals(word)) {
				for (String str : alts) {
					addAlternate(word, str);
				}
				return;
			}
		}
	}

	public void addAlternate(String word, String alt) {
		for (CrutchWord cw : crutchList) {
			if (cw.toString().equals(word)) {
				if (!cw.alternates.contains(alt)) {
					cw.alternates.add(alt);
				}
				return;
			}
		}

	}

	public void removeAlternate(String word, String alt) {
		for (CrutchWord cw : crutchList) {
			if (cw.toString().equals(word)) {
				cw.alternates.remove(alt);
				return;
			}
		}
	}

	public String recommendation(String word) {
		for (CrutchWord cw : crutchList) {
			if (cw.toString().equals(word)) {
				String rec = "";
				boolean or = false;

				if (cw.delete) {
					or = true;
					rec += "Delete";
				}

				if (cw.alternates.size() > 0) {
					if (or) {
						rec += " OR ";
					}

					rec += "Replace with one of the following: " + cw.listAlternates();
				}

				// think about actually throwing an error
				if (rec.equals("")) {
					rec = "ERROR: no recommendation available";
				}
				return rec;
			}
		}

		return "ERROR: word does not exist";
	}

	@Override
	public Iterator<String> iterator() {

		return new Iterator<String>() {
			int index = 0;

			@Override
			public boolean hasNext() {
				if (index < crutchList.size()) {
					return true;
				}
				return false;
			}

			@Override
			public String next() {
				CrutchWord cw = crutchList.get(index);
				index++;

				return cw.toString();
			}

		};
	}

}
