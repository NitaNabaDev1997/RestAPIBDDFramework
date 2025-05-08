package stepDefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeDeleteScenario() throws IOException
    {
        //execute this code only when place id is null
        //write a code that will give you place id

        StepDefinition m =new StepDefinition();
        if(StepDefinition.placeid==null)
        {

            m.add_place_payload("Debnath", "Bengali", "Kolkata");
            m.user_calls_with_http_request("AddPlaceAPI", "POST");
            m.verifyPlaceidMappedToUsing("name","Debnath", "getPlaceAPI");
        }
    }


    @Before("@UpdatePlace")
    public void beforeUpdateScenario() throws IOException
    {
        //execute this code only when place id is null
        //write a code that will give you place id

        StepDefinition m =new StepDefinition();
        if(StepDefinition.placeid==null)
        {

            m.add_place_payload("Debnath", "Bengali", "Kolkata");
            m.user_calls_with_http_request("AddPlaceAPI", "POST");
            m.verifyPlaceidMappedToUsing("address","Kolkata", "getPlaceAPI");
        }
    }


}
