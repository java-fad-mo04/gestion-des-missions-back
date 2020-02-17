package dev.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.domain.Holidays;

public class HolidaysService {


	public List<Holidays> getHolidaysW() {
		try {
			URL url = new URL(
					"https://holidayapi.com/v1/holidays?pretty&key=fc002e4b-eb91-418c-8b87-159250d59668&country=FR&year=2020");
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			int responsecode = conn.getResponseCode();
			if(responsecode != 200){
				throw new RuntimeException("HttpResponseCode: " +responsecode);
			}else{
				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(conn.getInputStream()));
				StringBuilder stringBuilder = new StringBuilder();

				String line = null;
				while ((line = bufferedReader.readLine()) != null) {
					stringBuilder.append(line + '\n');
				}
				String jsonString = stringBuilder.toString();
				ObjectMapper mapper = new ObjectMapper();
				List<Holidays> holidays = mapper.readValue(jsonString, new TypeReference<List<Holidays>>() {
				});
				bufferedReader.close();
				conn.disconnect();
				return holidays;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	}
