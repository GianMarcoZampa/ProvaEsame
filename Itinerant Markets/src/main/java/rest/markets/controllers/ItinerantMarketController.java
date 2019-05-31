package rest.markets.controllers;

import java.io.IOException;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;

import rest.markets.resources.ItinerantMarket;
import rest.markets.services.ItinerantMarketService;
import rest.markets.utils.FieldStatistic;

@RestController
public class ItinerantMarketController {
	
	@Autowired
	ItinerantMarketService itinerantMarketService;
	
	// This method returns all the object in the service
	@GetMapping(path = "/data", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Vector<ItinerantMarket>> getAll() {
		Vector<ItinerantMarket> itMarket= itinerantMarketService.getAll(); 
		if (itMarket.isEmpty())	throw new NullPointerException();
		return new ResponseEntity<Vector<ItinerantMarket>>(itMarket,HttpStatus.OK);
	}
	
	// This method creates a vector that contains the elements the have the same 
	// parameters of the body request with a POST request
	@PostMapping(path = "/data", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Vector<ItinerantMarket>> filterItinerantMarket(@RequestBody ItinerantMarket requestedIM) {
		Vector<ItinerantMarket> iMa = itinerantMarketService.getRequestedItinerantMarket(requestedIM);
		if (iMa.isEmpty()) throw new ResourceNotFoundException();
		return new ResponseEntity<Vector<ItinerantMarket>>(iMa, HttpStatus.OK);
	}
	
	// This method creates a vector that contains the elements the have the same 
	// parameters of the body request with a GET request
	@GetMapping(path = "/getdata", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Vector<ItinerantMarket>> filterGETItinerantMarket(@RequestParam String filter)
			throws JsonParseException, JsonMappingException, IOException {
		
		ObjectMapper obMAP = new ObjectMapper();
		ItinerantMarket requestedIM = obMAP.readValue(filter, ItinerantMarket.class);
		Vector<ItinerantMarket> iMa = itinerantMarketService.getRequestedItinerantMarket(requestedIM);
		
		if (iMa.isEmpty()) throw new ResourceNotFoundException();
		return new ResponseEntity<Vector<ItinerantMarket>>(iMa, HttpStatus.OK);
	}
	
	// This method returns a jsonSchema of the ItinerantMarket class
	@GetMapping(path = "/metadata", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonSchema> getMetadata() {
		JsonSchema jSchema = itinerantMarketService.getMetadata();
		return new ResponseEntity<JsonSchema>(jSchema, HttpStatus.OK);
	}
	
	// This method returns statistics of the requested fields (more than one argument must be
	// separated with ',')
	@GetMapping(path = "/stats", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Vector<FieldStatistic>> getStats(@RequestParam String field) {
		Vector<FieldStatistic> fStat = itinerantMarketService.getStats(field);
		return new ResponseEntity<Vector<FieldStatistic>>(fStat, HttpStatus.OK);	
	}
	
}
