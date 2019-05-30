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

import rest.markets.resources.ItinerantMarket;
import rest.markets.services.ItinerantMarketService;

@RestController
public class ItinerantMarketController {
	
	@Autowired
	ItinerantMarketService itinerantMarketService;
	
	@GetMapping(path = "/data", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Vector<ItinerantMarket>> getAll() {
		Vector<ItinerantMarket> itMarket= itinerantMarketService.getAll(); 
		if (itMarket.isEmpty())	throw new NullPointerException();
		return new ResponseEntity<Vector<ItinerantMarket>>(itMarket,HttpStatus.OK);
	}
	
	// This method create a vector that contains the elements the have the same 
	// parameters of the body request
	@PostMapping(path = "/data", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Vector<ItinerantMarket>> filterItinerantMarket(@RequestBody ItinerantMarket requestedIM) {
		Vector<ItinerantMarket> iMa = itinerantMarketService.getRequestedItinerantMarket(requestedIM);
		if (iMa.isEmpty()) throw new ResourceNotFoundException();
		return new ResponseEntity<Vector<ItinerantMarket>>(iMa, HttpStatus.OK);
	}
	
	@GetMapping(path = "/getdata", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Vector<ItinerantMarket>> filterGETItinerantMarket(@RequestParam String filter)
			throws JsonParseException, JsonMappingException, IOException {
		
		ObjectMapper obMAP = new ObjectMapper();
		ItinerantMarket requestedIM = obMAP.readValue(filter, ItinerantMarket.class);
		Vector<ItinerantMarket> iMa = itinerantMarketService.getRequestedItinerantMarket(requestedIM);
		
		if (iMa.isEmpty()) throw new ResourceNotFoundException();
		return new ResponseEntity<Vector<ItinerantMarket>>(iMa, HttpStatus.OK);
	}
	
	@GetMapping(path = "/metadata", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getMetadata() {
		itinerantMarketService.getMetadata();
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
}
