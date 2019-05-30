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
		return new ResponseEntity<Vector<ItinerantMarket>>(itinerantMarketService.getAll(),HttpStatus.OK);
	}
	
	//crea un oggetto ItinerantMarket con le specifiche inserite nel Body
	@PostMapping(path = "/data", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ItinerantMarket> filterItinerantMarket(@RequestBody ItinerantMarket itinerantMarket) {
		
		return new ResponseEntity<ItinerantMarket>(itinerantMarket, HttpStatus.OK);
	
		}
		
}
