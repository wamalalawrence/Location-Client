package com.goeuro.client.rest;


import com.goeuro.client.rest.domain.Location;
import com.goeuro.client.rest.util.Utils;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import java.io.Console;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author wamalalawrence
 * @apiNote rest client application
 *
 */
public class AppClient
{
    // The entry-point for the client program.
    public static void main( String[] args )
    {
        // if no city was entered, notify the user then exit with an error code
        if(args.length == 0) {
            System.out.println("No city was entered, system exiting....");
            System.exit(1);
        } else
        {
            Console console = System.console();
            System.out.println("Please enter a city name:");
            String city = args[0];

            System.out.println(">>>>>>>>>>>>> city entered: " + city);
            //TODO use log4j
            Client client = Client.create();

            WebResource webResource = client
                    .resource("http://localhost:4567" + Utils.API_CONTEXT + city);

            ClientResponse response = webResource.accept("application/json")
                    .get(ClientResponse.class);

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

            String output = response.getEntity(String.class);

            System.out.println("Output from Location-Service API .... \n");
            System.out.println(output);

                //generate cvs file
            try {
                Utils.generateCsvFile(output);
            } catch (IOException e) {
                System.out.println("Error :-" + e.getMessage());
                System.exit(1);
            }
        }
    }


}
