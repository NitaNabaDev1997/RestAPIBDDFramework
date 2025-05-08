package resources;

import pojo.AddPlace;

public enum APIResources {

    AddPlaceAPI("/maps/api/place/add/json"),
    getPlaceAPI("/maps/api/place/get/json"),
    deletePlaceAPI("/maps/api/place/delete/json"),
    UpdatePlaceAPI("/maps/api/place/update/json");
    private String resource;
     APIResources(String resource)
    {
        this.resource=resource;
    }

    public String getResource()
    {
        return resource;
    }
}
