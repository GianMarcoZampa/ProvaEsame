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
import rest.markets.exceptions.NotExistingFieldException;
import rest.markets.exceptions.NotExistingFilterException;
import rest.markets.resources.*;
import rest.markets.utils.filters.Filter;
import rest.markets.utils.filters.RequestConditionalFilter;
import rest.markets.utils.filters.RequestLogicalFilter;
import rest.markets.utils.statistics.*;

/**
 * This class is the implementation of ItinerantMarketService.
 * @see ItinerantMarketService
 */
@Service
public class IMServiceImplementation implements ItinerantMarketService {
	
	@Autowired
	Stats stats;
	@Autowired
	Filter filter;
	
	// This Vector contains all the data from the file ItinerantMarket.csv
	Vector<ItinerantMarket> itMaList = new Vector<ItinerantMarket>();
	
	/** 
	 * This method create a vector of ItinerantMarket object that is used for managing data.
	 * It uses the buffer reader for catching attributes from the file reading it line by line. 
	 * If the line contains an empty field it doesn't add the line in the vector and it increments
	 * the error line variable. 
	 */
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

	/** 
	 * This method return the entire list of ItinerantMarket. 
	 * If the list is empty a new one is created
	 * @return the entire list of ItinerantMarket in the service.
	 */
	@Override
	public Vector<ItinerantMarket> getAll() {
		
		// If the list is empty a new one is created form the file
		if(itMaList.isEmpty()) createList();
		
		return this.itMaList;
	}

	/** 
	 * This method creates a JsonSchema of {@link rest#markets#resources#ItinerantMarket} class.
	 * @return schema which is the description of the attributes of ItinerantMarket class.
	 */
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
	
	/** 
	 * This method obtains statistics from input fields using classes that are in 
	 * {@link rest.markets.utils.statistics}. If the list is empty a new one is created
	 * @param field input fields of which statistics are calculated.
	 * @return a statistics vector which contains all the result for each field.
	 * @throws NotExistingFieldException  if the input field doesn't exist.
	 */
	public Vector<FieldStatistic> getStats(String field) throws NotExistingFieldException {
		
		String[] numFieldRequested = field.split(",");
		String msg = new String("not available in this field");
		Vector<FieldStatistic> returnStatistics = new Vector<FieldStatistic>();
		Vector<Double> toNumStats = new Vector<Double>();
		Vector<String> toStringStats = new Vector<String>();
		
		// If the list is empty a new one is created form the file
		if(itMaList.isEmpty()) createList();
		
		for(String j:numFieldRequested) {
			
		// Switch field for every existing field in ItinerantMarket 
		switch(j) {
			case "year": 
				for(ItinerantMarket i: itMaList) {
					toNumStats.add((double)i.getYear());
				}
				returnStatistics.add(new NumFieldStatistic(j, msg, ((Double)stats.min(toNumStats)).toString(), ((Double)stats.max(toNumStats)).toString(), msg, msg));
				break;
			case "totalStats":
				for(ItinerantMarket i: itMaList) {
					toNumStats.add((double)i.getTotalStats().getTotal());
				}
				returnStatistics.add(new NumFieldStatistic(j + ".total", ((Double)stats.avg(toNumStats)).toString(), ((Double)stats.min(toNumStats)).toString(), ((Double)stats.max(toNumStats)).toString(), ((Double)stats.sum(toNumStats)).toString(), ((Double)stats.std(toNumStats)).toString()));
				for(ItinerantMarket i: itMaList) {
					toNumStats.add((double)i.getTotalStats().getAttivations());
				}
				returnStatistics.add(new NumFieldStatistic(j + ".attivations", ((Double)stats.avg(toNumStats)).toString(), ((Double)stats.min(toNumStats)).toString(), ((Double)stats.max(toNumStats)).toString(), ((Double)stats.sum(toNumStats)).toString(), ((Double)stats.std(toNumStats)).toString()));
				for(ItinerantMarket i: itMaList) {
					toNumStats.add((double)i.getTotalStats().getCessations());
				}
				returnStatistics.add(new NumFieldStatistic(j + ".cessations", ((Double)stats.avg(toNumStats)).toString(), ((Double)stats.min(toNumStats)).toString(), ((Double)stats.max(toNumStats)).toString(), ((Double)stats.sum(toNumStats)).toString(), ((Double)stats.std(toNumStats)).toString()));
				break;
			case "foodStats":
				for(ItinerantMarket i: itMaList) {
					toNumStats.add((double)i.getFoodStats().getTotal());
				}
				returnStatistics.add(new NumFieldStatistic(j + ".total", ((Double)stats.avg(toNumStats)).toString(), ((Double)stats.min(toNumStats)).toString(), ((Double)stats.max(toNumStats)).toString(), ((Double)stats.sum(toNumStats)).toString(), ((Double)stats.std(toNumStats)).toString()));
				for(ItinerantMarket i: itMaList) {
					toNumStats.add((double)i.getFoodStats().getAttivations());
				}
				returnStatistics.add(new NumFieldStatistic(j + ".attivations", ((Double)stats.avg(toNumStats)).toString(), ((Double)stats.min(toNumStats)).toString(), ((Double)stats.max(toNumStats)).toString(), ((Double)stats.sum(toNumStats)).toString(), ((Double)stats.std(toNumStats)).toString()));
				for(ItinerantMarket i: itMaList) {
					toNumStats.add((double)i.getFoodStats().getCessations());
				}
				returnStatistics.add(new NumFieldStatistic(j + ".cessations", ((Double)stats.avg(toNumStats)).toString(), ((Double)stats.min(toNumStats)).toString(), ((Double)stats.max(toNumStats)).toString(), ((Double)stats.sum(toNumStats)).toString(), ((Double)stats.std(toNumStats)).toString()));
				break;
			case "notFoodStats":
				for(ItinerantMarket i: itMaList) {
					toNumStats.add((double)i.getNotFoodStats().getTotal());
				}
				returnStatistics.add(new NumFieldStatistic(j + ".total", ((Double)stats.avg(toNumStats)).toString(), ((Double)stats.min(toNumStats)).toString(), ((Double)stats.max(toNumStats)).toString(), ((Double)stats.sum(toNumStats)).toString(), ((Double)stats.std(toNumStats)).toString()));
				for(ItinerantMarket i: itMaList) {
					toNumStats.add((double)i.getNotFoodStats().getAttivations());
				}
				returnStatistics.add(new NumFieldStatistic(j + ".attivations", ((Double)stats.avg(toNumStats)).toString(), ((Double)stats.min(toNumStats)).toString(), ((Double)stats.max(toNumStats)).toString(), ((Double)stats.sum(toNumStats)).toString(), ((Double)stats.std(toNumStats)).toString()));
				for(ItinerantMarket i: itMaList) {
					toNumStats.add((double)i.getNotFoodStats().getCessations());
				}
				returnStatistics.add(new NumFieldStatistic(j + ".cessations", ((Double)stats.avg(toNumStats)).toString(), ((Double)stats.min(toNumStats)).toString(), ((Double)stats.max(toNumStats)).toString(), ((Double)stats.sum(toNumStats)).toString(), ((Double)stats.std(toNumStats)).toString()));
				break;
			case "prodStats":
				for(ItinerantMarket i: itMaList) {
					toNumStats.add((double)i.getProdStats().getTotal());
				}
				returnStatistics.add(new NumFieldStatistic(j + ".total", ((Double)stats.avg(toNumStats)).toString(), ((Double)stats.min(toNumStats)).toString(), ((Double)stats.max(toNumStats)).toString(), ((Double)stats.sum(toNumStats)).toString(), ((Double)stats.std(toNumStats)).toString()));
				for(ItinerantMarket i: itMaList) {
					toNumStats.add((double)i.getProdStats().getAttivations());
				}
				returnStatistics.add(new NumFieldStatistic(j + ".attivations", ((Double)stats.avg(toNumStats)).toString(), ((Double)stats.min(toNumStats)).toString(), ((Double)stats.max(toNumStats)).toString(), ((Double)stats.sum(toNumStats)).toString(), ((Double)stats.std(toNumStats)).toString()));
				for(ItinerantMarket i: itMaList) {
					toNumStats.add((double)i.getProdStats().getCessations());
				}
				returnStatistics.add(new NumFieldStatistic(j + ".cessations", ((Double)stats.avg(toNumStats)).toString(), ((Double)stats.min(toNumStats)).toString(), ((Double)stats.max(toNumStats)).toString(), ((Double)stats.sum(toNumStats)).toString(), ((Double)stats.std(toNumStats)).toString()));
				break;
			case "comune":
				for(ItinerantMarket i: itMaList) {
					toStringStats.add(i.getComune());
				}
				returnStatistics.add(new StringFieldStatistic(j, stats.repetition(toStringStats)));
				break;
			case "provincia":
				for(ItinerantMarket i: itMaList) {
					toStringStats.add(i.getProvincia());
				}
				returnStatistics.add(new StringFieldStatistic(j, stats.repetition(toStringStats)));
				break;
			default: throw new NotExistingFieldException("Not Existing Field in request");
			}
		
		}
		return returnStatistics;
	}

	/**
	 * This method filters data with a conditional filter. If the list is empty a new one is created
	 * @param requestedFilters is a {@link rest#markets#utils#filters#RequestConditionalFilter} 
	 * with the information desired.
	 * @return a vector that includes the data after filtering.
	 * @throws NotExistingFilterException if the input filter doesn't exist.
	 */
	@Override
	public Vector<ItinerantMarket> getConditionalFilter(Vector<RequestConditionalFilter> requestedFilters) throws NotExistingFilterException {						
		
		Vector<ItinerantMarket> returnIM;

		// If the list is empty a new one is created form the file
		if (itMaList.isEmpty())	createList();
		
		returnIM = getAll();
		
		// for each filter sent from controller this method delete some elements from the returnIM
		for (RequestConditionalFilter i : requestedFilters) {
			String filterType = i.getFilterType();
			String[] filterTypeRequested = filterType.split(",");

			switch (filterTypeRequested[0]) {
			case ("$gt"):
				returnIM = filter.gt(Integer.parseInt(filterTypeRequested[1]), i.getEqual(), returnIM,
						i.getNameField());
				break;
			case ("$lt"):
				returnIM = filter.lt(Integer.parseInt(filterTypeRequested[1]), i.getEqual(), returnIM,
						i.getNameField());
				break;
			case ("$bt"):
				returnIM = filter.bt(Integer.parseInt(filterTypeRequested[1]), Integer.parseInt(filterTypeRequested[2]),
						i.getEqual(), returnIM, i.getNameField());
				break;
			default:
				throw new NotExistingFilterException("Filter doesn't exist");
			}
		}
		return returnIM;
	}

	/**
	 * This method filters data with a logical filter. If the list is empty a new one is created
	 * @param requestedFilter is a {@link rest#markets#utils#filters#RequestLogicalFilter}
	 * with the information desired.
	 * @return a vector that includes the data after filtering.
	 */
	@Override
	public Vector<ItinerantMarket> getLogicalFilter(RequestLogicalFilter requestedFilter) {
		
		Vector<ItinerantMarket> returnIM = new Vector<ItinerantMarket>();
		
		// If the list is empty a new one is created form the file
		if(itMaList.isEmpty()) createList();
		
		// Call filter.in
		for(ItinerantMarket i : requestedFilter.getParam()) {
			returnIM.addAll(filter.in(i, requestedFilter.getIn(), itMaList));
			System.out.println(returnIM);
		}
		
		return returnIM;
	}

}
