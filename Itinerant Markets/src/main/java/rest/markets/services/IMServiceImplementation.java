package rest.markets.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.*;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;

import rest.markets.ItinerantMarketsApplication;
import rest.markets.resources.ItinerantMarket;
import rest.markets.resources.ItinerantMarketStats;
import rest.markets.utils.FieldStatistic;
import rest.markets.utils.Stats;


@Service
public class IMServiceImplementation implements ItinerantMarketService {
	
	@Autowired
	Stats stats;
	
	// This Vector contains all the data from the file ItinerantMarket.csv
	Vector<ItinerantMarket> itMaList = new Vector<ItinerantMarket>();
	
	// This method create the itMaList that is used for managing data
	private void createList() {
		
		File fileOut = new File(ItinerantMarketsApplication.PATH);
		ItinerantMarket itMa;
		String line;
		String[] parsedLine = new String[15];
		boolean flag = true;
		int err;

		// Opening of the buffered input and parsing of the lines
		try {
			BufferedReader buff = new BufferedReader(new FileReader(fileOut));
			err = 0;
			
			do {
				line = buff.readLine();
				if(line != null) {
					line = line.replace("\"", "");
					parsedLine = line.split(";");
				}
				
				// Deleting first row
				if(parsedLine[0].equals("comune")) {
					line = buff.readLine();
					if(line != null) {
						line = line.replace("\"", "");
						parsedLine = line.split(";");
					}
				}
				
				// If the string isn't null and contains the correct number of parameters it
				// creates a new ItinerantMarket element and add it to the Vector
				if(line != null && parsedLine.length == 15) {
					
					itMa = new ItinerantMarket();
					
					itMa.setComune(parsedLine[0]);
					itMa.setProvincia(parsedLine[1]);
					itMa.setYear((int)Integer.parseInt(parsedLine[2]));
					itMa.setTotalStats(new ItinerantMarketStats((int)Integer.parseInt(parsedLine[3]),(int)Integer.parseInt(parsedLine[4]),(int)Integer.parseInt(parsedLine[5])));
					itMa.setFoodStats(new ItinerantMarketStats((int)Integer.parseInt(parsedLine[6]),(int)Integer.parseInt(parsedLine[7]),(int)Integer.parseInt(parsedLine[8])));
					itMa.setNotFoodStats(new ItinerantMarketStats((int)Integer.parseInt(parsedLine[9]),(int)Integer.parseInt(parsedLine[10]),(int)Integer.parseInt(parsedLine[11])));
					itMa.setProdStats(new ItinerantMarketStats((int)Integer.parseInt(parsedLine[12]),(int)Integer.parseInt(parsedLine[13]),(int)Integer.parseInt(parsedLine[14])));
					
					itMaList.add(itMa);
				}
				
				// If some fields are missing increase number of line errors
				else if(parsedLine.length != 15) {
					err++;
				}
				
				// If line is null the reading operation is terminated and it prints the
				// number of lines deleted
				else {
					flag = false;
					System.out.println("Numero di record eliminati: " + err);
				}		
			} while(flag);
			
			buff.close();
			
		} catch (FileNotFoundException e) {
			System.err.println("File Not Found Exception");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("IO Exception");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("General Exception");
			e.printStackTrace();
		}
	}
	
	// This method returns the requested ItinerantMarket if it is present in the file
	@Override
	public Vector<ItinerantMarket> getRequestedItinerantMarket(ItinerantMarket requestedIM) {
		
		Vector<ItinerantMarket> itMaVec = new Vector<ItinerantMarket>();
		
		// If the list is empty a new one is created form the file
		if(itMaList.isEmpty()) createList();
		
		// For-each cycle if it found an ItinerantMarket that accomplish the requested
		// property, it add that value to the vector itMaVec
		for(ItinerantMarket itMarket:itMaList) {
			if(itMarket.paramEquals(requestedIM)) itMaVec.add(itMarket);
		}
		
		return itMaVec;
	}

	// This method return the entire list of ItinerantMarket
	@Override
	public Vector<ItinerantMarket> getAll() {
		
		// If the list is empty a new one is created form the file
		if(itMaList.isEmpty()) createList();
		
		return this.itMaList;
	}

	// This method creates a JsonSchema from ItinerantMarket.class
	@Override
	public JsonSchema getMetadata() {
		ObjectMapper mapper = new ObjectMapper();
        SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
        try {
			mapper.acceptJsonFormatVisitor(ItinerantMarket.class, visitor);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		}
        JsonSchema schema = visitor.finalSchema();
		return schema;
	}
	
	// This method obtains statistics from the input field
	public Vector<FieldStatistic> getStats(String field) {
		
		String msg = new String("not available for this field");
		Vector<FieldStatistic> returnStatistics = new Vector<FieldStatistic>();
		Vector<Double> toNumStats = new Vector<Double>();
		
		// If the list is empty a new one is created form the file
		if(itMaList.isEmpty()) createList();
		
		// Switch field for every existing field in ItinerantMarket 
		switch(field) {
		case "year": 
			for(ItinerantMarket i: itMaList) {
				toNumStats.add((double)i.getYear());
			}
			returnStatistics.add(new FieldStatistic("year", msg, ((Double)stats.min(toNumStats)).toString(), ((Double)stats.max(toNumStats)).toString(), msg));
			break;
		default: return null;//throw NotExistinFieldException();
		}
		
		return returnStatistics;
	}
}
