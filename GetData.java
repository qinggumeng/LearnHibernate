package test;

import java.util.List;

public interface GetData {
	String FIRST_URL = "http://hf.sell.house365.com/district/";
	
	public List<String> getAllDistrictURL();
	
	public List<String> getStreetURLByDistrictURL(String dURL);
	
	public List<List<String>> getAllStreetURL();
	
	public List<List<String>> getHouseURL();
	
	public String parseSQL();
}
