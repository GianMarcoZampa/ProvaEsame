package rest.markets.controllers;

import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import rest.markets.resources.ItinerantMarket;
import rest.markets.services.ItinerantMarketService;

@RestController
public class ItinerantMarketController {
	
	@Autowired
	ItinerantMarketService itinerantMarketService;
	
	@GetMapping(path = "/data", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Vector<ItinerantMarket>> getAll(){
		Vector<ItinerantMarket> itMarket= itinerantMarketService.getAll(); 
		if (itMarket.isEmpty())
			return new ResponseEntity<Vector<ItinerantMarket>>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<Vector<ItinerantMarket>>(itMarket,HttpStatus.OK);
	}
	
	// This method create a vector that contains the elements the have the same 
	// parameters of the body request
	@PostMapping(path = "/data", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Vector<ItinerantMarket>> filterItinerantMarket(@RequestBody ItinerantMarket requestedIM) {
		Vector<ItinerantMarket> iMa = itinerantMarketService.getRequestedItinerantMarket(requestedIM);
		if (iMa.equals(null)) return new ResponseEntity<Vector<ItinerantMarket>>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Vector<ItinerantMarket>>(iMa, HttpStatus.OK);
	
		}
	
	
}
