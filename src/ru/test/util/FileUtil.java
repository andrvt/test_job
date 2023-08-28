package ru.test.util;

import static ru.test.util.DateUtil.getData;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import ru.test.dto.Ticket;


public class FileUtil {
	public static List<Ticket> readJson(File file,String TMZ_From,String TMZ_To){
		Gson gson = new Gson();
		List<Ticket> tickets = new ArrayList<Ticket>();

		try(FileReader fileReader=new FileReader(file) ; JsonReader reader = new JsonReader(fileReader);) {
			
			JsonElement data = new JsonParser().parse(reader);
			JsonObject root = data.getAsJsonObject();
			
			JsonArray jsarray = root.getAsJsonArray("tickets");
			for(int i=0;i<jsarray.size();i++){
				Ticket ticket = gson.fromJson(jsarray.get(i), Ticket.class);
				ZonedDateTime departur_date = getData(ticket.getDeparture_date(), ticket.getDeparture_time(),TMZ_From);
				ZonedDateTime arrival_date = getData(ticket.getArrival_date(), ticket.getArrival_time(),TMZ_To);
				Duration period = Duration.between(departur_date,arrival_date);
				ticket.setDuration(period.toMinutes());
				tickets.add(ticket);				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
       return tickets;
	}

}
