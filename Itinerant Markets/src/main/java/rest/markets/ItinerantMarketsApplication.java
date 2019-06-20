package rest.markets;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import rest.markets.utils.*;

/**This class contains the main of the program.
 * It is used for copying the link for the file. It calls rest.markets.utils.Download.class
 * for the downloading of the file and it saves its path. After that the Spring Application starts 
 * to run
 */
@SpringBootApplication
public class ItinerantMarketsApplication {
	
	public final static String LINK = new String("http://dati.lazio.it/catalog/"
			+ "dataset/f4407fb8-5e36-4311-8422-02d463741780/resource/"
			+ "0fe222fb-213a-4420-b57f-b17e45dbb03d/download/oscoitineranti.csv");
	public final static String PATH = new String("Itinerant_Market_Lazio.csv");

	public static void main(String[] args) {
		
		File fileOut = new File(PATH);
		Download download = new Download(LINK, fileOut);
		Thread threadDown = new Thread (download);
		threadDown.start();
		
		SpringApplication.run(ItinerantMarketsApplication.class, args);
		
	}

}
