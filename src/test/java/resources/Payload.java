package resources;
import java.util.ArrayList;
import java.util.List;

import pojo.addPlace;
import pojo.location;

public class Payload {
	
	public addPlace addPlacePayload(String Name, String Language, String Address) {
		
		addPlace AP = new addPlace();
		AP.setAccuracy(50);
		AP.setPhone_number("(+91) 983 893 3937");
		AP.setWebsite("http://google.com");
		AP.setName(Name);
		AP.setLanguage(Language);
		AP.setAddress(Address);
		
		location loc = new location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		AP.setLocation(loc);
		
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shoe");
		AP.setTypes(myList);
		
		return AP;
		
	}

}
