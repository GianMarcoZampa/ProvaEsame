# Itinerant Market REST API

This API elaborates data from [dati.lazio.it](http://dati.lazio.it/catalog/dataset/f4407fb8-5e36-4311-8422-02d463741780/resource/0fe222fb-213a-4420-b57f-b17e45dbb03d/download/oscoitineranti.csv) which contains the number of itinerant markets in Lazio. Data are sorted by comune, provincia and year. It returns requested data in JSON format.

## UML Diagrams

![Use case Diagram](https://lh3.googleusercontent.com/U0ptTLMbYW2Bze9x1EjsWd_eUhL56Pv0eQUAAXNq1u3IG7VeGd5CMWVVK86WCAG70CL-WjmHnmU "Use Case Diagram")

This UML Use Case Diagram explains how the application works. When a user sends a request to the rest controller, it calls the service. The service elaborates the data in the collection and if no collection exists it creates a new one from the file. The results return to the controller and it sends them back to the user.

![Class Diagram](https://lh3.googleusercontent.com/XJDnXbyIfx2APcS1KCfDwPqUziexK0k85NTsHe2U_Wo1xvcJkm3EIZ9MD4kPFtIqfmzBgfMuQXI)

This UML Class Diagram represents how the application is structured.

## Allowed Request
The allowed http request are:

 - **/data**, which is a GET request that returns all the data in the dataset;
  - **/metadata**, which is a GET request that returns the metadata of the class that encapsulates the data;
 - **/stats**, which is a GET request that passes an array of fields and it returns statistics calculated for the specified fields ;
 -  **/data/lfilter**, which is a POST request for a logical filter (and, or, in, not);
 - **/data/cfilter**, which is a POST request for a conditional filter (Greater than, less than, between, equal ).


All these request accept and return data in a JSON format.

## Stats Examples

This is an example for a **/stats** GET request:

    http://localhost:8080/stats?field=year,provincia

It return the statistics calculated for year and privincia fields

    [
        {
            "fieldName": "year",
            "avg": "not available in this field",
            "min": "2008.0",
            "max": "2013.0",
            "sum": "not available in this field",
            "std": "not available in this field"
        },
        {
            "fieldName": "provincia",
            "repetition": {
                "Latina": 203,
                "Regione Lazio": 5,
                "Rieti": 443,
                "Roma": 725,
                "Frosinone": 551,
                "Comune di Roma": 50,
                "Viterbo": 365
            }
        }
    ]

The calculated stats are:

 - average value;
 - minimum value;
 - maximum value;
 - sum value;
 - standard deviation;
 - word occurency (only available for alfanumeric fields).

 

## Logical Filter Example

 This is an example for a /lfilter POST request

     http://localhost:8080/data/lfilter

 The body of the request may contain an array of ItinerantMarket element and a boolean in
 

    
	{
		"param" : [
		    {
			"comune": "Acquafondata",
	            	"provincia": "Frosinone",
	            	"year": 2009
		    },
		    {
			"comune": "Gaeta",
	            	"provincia": "Latina",
	            	"year": 2011
		    }
		],
    	
	    	"in":true
    }
Sending only one element in the ***param*** arraywill return an array of ItinerantMarket which contains only elements with the specified value in the not null fields.
Sending more than one element will return an array which either the values.
The boolean ***in*** will specify if the request is a not request. In this case the filter will return an array of elements which doesn't contain the values in the request body.

## Conditional Filter Example
This is an example for a /cfilter POST request

    http://localhost:8080/data/cfilter

 The body of  the request may contain an array of conditional filters:

     [
    	{
    		"nameField" : "year",
    		"filterType": "$bt,2009,2011",
    		"equal" : false
    	},
    	{
    		"nameField" : "totalStats.total",
    		"filterType": "$lt,1",
    		"equal" : false
    	},
    	{
    		"nameField" : "foodStats.total",
    		"filterType": "$gt,100",
    		"equal" : true
    	}
    ]
The ***nameField*** parameter will contain the field to which the filter is applied.
The ***filterType*** paramenter will contain the kind of filter requested. It could be: 

 - **$gt,minValue**, greater than the value in minValue;
 - **$lt,maxValue**, less than the value in maxValue;
 - **$bt,minValue,maxValue**, between minValue and maxValue.

The ***equal*** parameter is a boolean. If true all the limits of the filter are included as part of the response, else they are excluded.

Although there is no limits to the applied filters, requesting more than one ilter for each field is not reccomended. 

