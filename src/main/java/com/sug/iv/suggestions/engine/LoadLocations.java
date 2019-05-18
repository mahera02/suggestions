package com.sug.iv.suggestions.engine;

import java.io.BufferedReader;
import java.io.FileReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoadLocations {
	private final static Logger LOG = LoggerFactory.getLogger(LoadLocations.class);

	public static LocationTrie load() {
		LocationTrie trie = new LocationTrie();
		try {

			BufferedReader TSVFile = new BufferedReader(new FileReader("cities_canada-usa.tsv.txt"));
			String dataRow = TSVFile.readLine(); // Read first line.

			while (dataRow != null) {
				String[] dataArray = dataRow.split("\t");
				try {
					String name = dataArray[1];
					float latitude = new Float(dataArray[4]).floatValue();
					float longitude = new Float(dataArray[5]).floatValue();
					trie.insert(name, latitude, longitude);
				} catch (Exception ex) {
					LOG.warn("Error in loading - " + dataRow, ex.getLocalizedMessage());
				}
				dataRow = TSVFile.readLine(); // Read next line of data.
			}
			// Close the file once all data has been read.
			TSVFile.close();
			LOG.info("Load successful");
			// End the printout with a blank line.
		} catch (Exception ex) {
			LOG.error("Error in loading", ex);
		}
		return trie;
	}
}
