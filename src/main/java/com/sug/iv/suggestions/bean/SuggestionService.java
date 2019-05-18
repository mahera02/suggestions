package com.sug.iv.suggestions.bean;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sug.iv.suggestions.engine.LoadLocations;
import com.sug.iv.suggestions.engine.Location;
import com.sug.iv.suggestions.engine.LocationTrie;
import com.sug.iv.suggestions.engine.SuggestionsResponse;
import com.sug.iv.suggestions.service.ISuggestionService;

@Service
public class SuggestionService implements ISuggestionService {
	private final static Logger LOG = LoggerFactory.getLogger(SuggestionService.class);
	static LocationTrie trie;
	static {
		trie = LoadLocations.load();
	}

	@Override
	public SuggestionsResponse getSuggestions(String q, String latitude, String longitude) {
		LOG.info(String.format("getSuggestions() %s %s %s", q, latitude, longitude));
		List<Location> suggestions = trie.autocompleteL(q);

		if (latitude == null && longitude == null) {

			return new SuggestionsResponse(suggestions);
		} else

		{
			latitude = latitude == null ? "0" : latitude;
			longitude = longitude == null ? "0" : longitude;
			float lat = 0f, lon = 0f;
			try {
				lat = new Float(latitude).floatValue();
			} catch (Exception ex) {
				LOG.error("Bad Input ", ex);
			}

			try {
				lon = new Float(longitude).floatValue();
			} catch (Exception ex) {
				LOG.error("Bad Input ", ex);
			}
			assignOrder(suggestions, lat, lon);
			Collections.sort(suggestions, new Comparator<Location>() {

				@Override
				public int compare(Location o1, Location o2) {
					if (o1.getScore() - o2.getScore() > 0f)
						return -1;
					else if (o1.getScore() - o2.getScore() < 0f)
						return +1;
					else
						return 0;
				}
			});
		}
		return new SuggestionsResponse(suggestions);
	}

	private void assignOrder(List<Location> suggestions, float lat, float lon) {
		for (Location loc : suggestions) {
			float latDiff = Math.abs(lat - loc.getLatitude());
			float lonDiff = Math.abs(lon - loc.getLongitude());
			float dist = latDiff + lonDiff;
			if (dist ==0)
				loc.setScore(1f);
			else if (dist < 10)
				loc.setScore(0.99f - (dist / 100));
			else if (dist < 50)
				loc.setScore(0.9f - (dist / 100));
			else if (dist < 100)
				loc.setScore(0.8f - (dist / 1000));
			else if (dist < 200)
				loc.setScore(0.7f - (dist / 1000));
			else if (dist < 400)
				loc.setScore(0.6f - (dist / 1000));
			else if (dist < 500)
				loc.setScore(0.5f - (dist / 1000));
			else if (dist < 1000)
				loc.setScore(0.4f + (dist / 10000));
			else if (dist < 2000)
				loc.setScore(0.3f + (dist / 10000));
			else if (dist < 3000)
				loc.setScore(0.2f + (dist / 10000));
			else if (dist < 4000)
				loc.setScore(0.1f + (dist / 10000));
			else
				loc.setScore(0f);
		}
	}
}
