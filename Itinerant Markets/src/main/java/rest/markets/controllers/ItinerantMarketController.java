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

/**The ItinerantMarketController is a class that launch the spring application using a rest controller and it creates all the requests
 */
@RestController
public class ItinerantMarketController {
	
	@Autowired
	ItinerantMarketService itinerantMarketService
	/**This method returns all the object in the service
	 * @return show all the data in the body response
	 * @throws NullPointerException
	 */
	@GetMapping(path = "/data", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Vector<ItinerantMarket>> getAll() throws NullPointerException {
		Vector<ItinerantMarket> itMarket= itinerantMarketService.getAll(); 
		if (itMarket.isEmpty())	throw new NullPointerException("No elements in the ItinerantMarket collection");
		return new ResponseEntity<Vector<ItinerantMarket>>(itMarket,HttpStatus.OK);
	}


	/** This method returns a jsonSchema of the ItinerantMarket class
	 * @return metadata of the ItinerantMarketService class
	 * @throws NullPointerException
	 * */
	@GetMapping(path = "/metadata", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonSchema> getMetadata() throws NullPointerException {
		
		JsonSchema jSchema = itinerantMarketService.getMetadata();
		if(jSchema == null) throw new NullPointerException("No Json schema found");
		return new ResponseEntity<JsonSchema>(jSchema, HttpStatus.OK);
	}

	/**This method returns statistics using the interface itinerantMarketService
	 * @see itinerantMarketService
	 * @param field requested field(more than one argument must be separated with ',')
	 * @return the statics for field 
	 * @throws NullPointerException when field doesn't exist
	 */
	@GetMapping(path = "/stats", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Vector<FieldStatistic>> getStats(@RequestParam String field) 
			throws NullPointerException {
		Vector<FieldStatistic> fStat = itinerantMarketService.getStats(field);
		if(fStat.isEmpty()) throw new NullPointerException("Null statistic field");
		return new ResponseEntity<Vector<FieldStatistic>>(fStat, HttpStatus.OK);	
	}

	/**This method requests a filter object for each field and it returns the complete list of itinerant markets with this filter
	 * 
	 * @param filters 
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@PostMapping(path = "/data/cfilter", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Vector<ItinerantMarket>> getConditionalFilter(@RequestBody Vector<RequestConditionalFilter> filters) 
			throws ResourceNotFoundException {
		Vector<ItinerantMarket> iMa = itinerantMarketService.getConditionalFilter(filters);
		if(iMa.isEmpty()) throw new ResourceNotFoundException("No resources corresponding requested criteria");
		return new ResponseEntity<Vector<ItinerantMarket>>(iMa, HttpStatus.OK);
	}
	
	//Vector<ItinerantMarket> filters, boolean in
	@PostMapping(path = "/data/lfilter", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Vector<ItinerantMarket>> getLogicalFilter(@RequestBody RequestLogicalFilter filter) 
			throws ResourceNotFoundException {
		System.out.println(filter.getParam().toString() + filter.getIn());
		Vector<ItinerantMarket> iMa = itinerantMarketService.getLogicalFilter(filter);
		if (iMa.isEmpty()) throw new ResourceNotFoundException("No resources correspinding requested criteria");
		return new ResponseEntity<Vector<ItinerantMarket>>(iMa, HttpStatus.OK);
	}
	
}
