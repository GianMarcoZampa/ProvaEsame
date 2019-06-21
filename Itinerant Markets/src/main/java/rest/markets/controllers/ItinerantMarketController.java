package rest.markets.controllers;

import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.module.jsonSchema.JsonSchema;


import rest.markets.resources.ItinerantMarket;
import rest.markets.services.ItinerantMarketService;
import rest.markets.utils.statistics.FieldStatistic;
import rest.markets.utils.filters.RequestConditionalFilter;
import rest.markets.utils.filters.RequestLogicalFilter;

/**
 * This class gets the request sent from user and invokes the 
 * {@link ItinerantMarketService} to get the response.
 * @see rest.markets.services.ItinerantMarketService
 * @see rest.markets.services.IMServiceImplementation
 */
@RestController
public class ItinerantMarketController {
	
	@Autowired ItinerantMarketService itinerantMarketService;
	
	/**
	 * This method invokes {@link ItinerantMarketService} and
	 * it returns all the object in the service.
	 * @return all the data in the data set
	 * @throws NullPointerException if no elements are in the data set
	 */
	@GetMapping(path = "/data", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Vector<ItinerantMarket>> getAll() throws NullPointerException {
		Vector<ItinerantMarket> itMarket= itinerantMarketService.getAll(); 
		if (itMarket.isEmpty())	throw new NullPointerException("No elements in the ItinerantMarket collection");
		return new ResponseEntity<Vector<ItinerantMarket>>(itMarket,HttpStatus.OK);
	}


	/** 
	 * This method invokes {@link ItinerantMarketService} and
	 * it returns a jsonSchema of the {@link ItinerantMarket} class.
	 * @return metadata of the ItinerantMarket class
	 * @throws NullPointerException if errors occurs in the creation of the JSON schema
	 */
	@GetMapping(path = "/metadata", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonSchema> getMetadata() throws NullPointerException {
		
		JsonSchema jSchema = itinerantMarketService.getMetadata();
		if(jSchema == null) throw new NullPointerException("No Json schema found");
		return new ResponseEntity<JsonSchema>(jSchema, HttpStatus.OK);
	}

	/**
	 * This method invokes {@link ItinerantMarketService} and
	 * it returns the statistics of the requested fields.
	 * @see itinerantMarketService
	 * @param field requested parameters (more than one argument must be separated with ',')
	 * @return the statics for field 
	 * @throws NullPointerException if field has no statistics
	 */
	@GetMapping(path = "/stats", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Vector<FieldStatistic>> getStats(@RequestParam String field) 
			throws NullPointerException {
		Vector<FieldStatistic> fStat = itinerantMarketService.getStats(field);
		if(fStat.isEmpty()) throw new NullPointerException("Null statistic field");
		return new ResponseEntity<Vector<FieldStatistic>>(fStat, HttpStatus.OK);	
	}

	/**
	 * This method invokes {@link ItinerantMarketService} and 
	 * it returns all the {@link ItinerantMarket} objects that 
	 * matches the requested conditional filter.
	 * @param filters this vector contains all the filter requested
	 * @return all the data that matches the filter
	 * @throws ResourceNotFoundException if no resources correspond to requested criteria
	 */
	@PostMapping(path = "/data/cfilter", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Vector<ItinerantMarket>> getConditionalFilter(@RequestBody Vector<RequestConditionalFilter> filters) 
			throws ResourceNotFoundException {
		Vector<ItinerantMarket> iMa = itinerantMarketService.getConditionalFilter(filters);
		if(iMa.isEmpty()) throw new ResourceNotFoundException("No resources corresponding requested criteria");
		return new ResponseEntity<Vector<ItinerantMarket>>(iMa, HttpStatus.OK);
	}
	
	/**
	 * This method invokes {@link ItinerantMarketService} and 
	 * it returns all the {@link ItinerantMarket} objects that 
	 * matches the requested logical filter.
	 * @param filter it contains all the filter requested
	 * @return all the data that matches the filter
	 * @throws ResourceNotFoundException if no resources correspond to requested criteria
	 */
	@PostMapping(path = "/data/lfilter", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Vector<ItinerantMarket>> getLogicalFilter(@RequestBody RequestLogicalFilter filter) 
			throws ResourceNotFoundException {
		System.out.println(filter.getParam().toString() + filter.getIn());
		Vector<ItinerantMarket> iMa = itinerantMarketService.getLogicalFilter(filter);
		if (iMa.isEmpty()) throw new ResourceNotFoundException("No resources correspinding requested criteria");
		return new ResponseEntity<Vector<ItinerantMarket>>(iMa, HttpStatus.OK);
	}
	
}
