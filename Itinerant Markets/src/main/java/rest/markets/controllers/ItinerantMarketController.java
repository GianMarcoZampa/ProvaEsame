package rest.markets.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import rest.markets.resources.ItinerantMarket;

@RestController
public class ItinerantMarketController {
	
	@PostMapping("/data")
	public ResponseEntity<ItinerantMarket> filterItinerantMarket(@RequestBody ItinerantMarket itinerantMarket) {
		
		return new ResponseEntity(itinerantMarket, HttpStatus.OK);
	}
		
}
