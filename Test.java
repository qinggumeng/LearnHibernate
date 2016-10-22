package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Test {
	public static void main(String[] args) {
		try {
			getAllHouse();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static List<House> getAllHouse() throws IOException{
		List<House> hList = new ArrayList<House>();
		BufferedReader br = new BufferedReader(
				new InputStreamReader(
						new FileInputStream(
								new File("c://data.txt"))));
		
		StringBuffer sb = new StringBuffer();
		String str = "";
		while((str=br.readLine())!= null){
			sb.append(str);
		}
		
		br.close();
		
		List<String> list = Util.getStringFromHTML2(sb.toString(), 
				"([0-9]+):::+.*?<h3 class=\"name\"><a href=\".*?>(.*?)</a>.*?</h3>.*?"//title
				+ "<div class=\"item item2\">(.*?)</div>.*?"//description
				+ "<div class=\"item\">.*?</div>.*?"
				+ "<div class=\"item\">(.*?)<span.*?</div>.*?"//typeid
				+ "<div class=\"item\"><a.*?>(.*?)</a>.*?<span.*?"//contact
				+ "<span class=\"time\">(.*?)更新.*?"//pubdate
				+ "<div class=\"price\"> <span class=\"number\">(.*?)</span>万元.*?"
				+ "<div class=\"acreage\">(.*?)㎡ </div>					</dd>");
		System.out.println(list.size());
		
		return hList;
	}
}

class House{
	private String title;
	private String description;
	private int price;
	private String date;
	private int floorAge;
	private String contact;
	private int userId;
	private int typeId;
	private int streetId;
	
	public House() {
		// TODO Auto-generated constructor stub
	}
	
	
	public House(String title, String description, int price, String date,
			int floorAge, String contact, int userId, int typeId, int streetId) {
		super();
		this.title = title;
		this.description = description;
		this.price = price;
		this.date = date;
		this.floorAge = floorAge;
		this.contact = contact;
		this.userId = userId;
		this.typeId = typeId;
		this.streetId = streetId;
	}


	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getFloorAge() {
		return floorAge;
	}
	public void setFloorAge(int floorAge) {
		this.floorAge = floorAge;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public int getStreetId() {
		return streetId;
	}
	public void setStreetId(int streetId) {
		this.streetId = streetId;
	}
	
}
