package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
	public static List<String> getStringFromHTML(String html,String reg){
		if(html==null) return null;
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(html);
		List<String> strs = new ArrayList<String>();
		while (m.find()) {
			strs.add(m.group(1));            
		}    
		return strs;
	}

	public static List<String> getStringFromHTML2(String html,String reg) throws FileNotFoundException{
		if(html==null) return null;
		System.setOut(new PrintStream(new File("c:/2.sql")));
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(html);
		List<String> strs = new ArrayList<String>();
		while (m.find()) {
			
			String str = "insert into house(id,street_id,title,description"
					+ ",type_id,contact,pubdate,price,floorage,user_id) values(house_seq.nextval,";
			for(int i=1;i<=m.groupCount();i++){ 
				String s = m.group(i).trim();
				if(i==1||i==7||i==8||i==6){
					if(i==6){
						s = "to_date('"+s+"','yyyy-mm-dd hh24:mi:ss')";
					}
					str += s+",";
				}
				if(i==2||i==3||i==5){
					
					str += "'"+s+"',";
				}
				if(i==4){
					if(s.startsWith("1")){
						str += "1,";
					}else if(s.startsWith("2")){
						str += "2,";
					}else if(s.startsWith("3")){
						str += "3,";
					}else if(s.startsWith("4")){
						str += "4,";
					}else if(s.startsWith("5")){
						str += "5,";
					}else{
						str += "1,";
					}
				}
				
			}
			str+="1);";
			System.out.println(str);
			if(!str.equals(""))
				strs.add(str);
			
		}    
		
		return strs;
	}

	public static String getHTML(String str) throws IOException{
		URL url = new URL(str);
		HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
		urlConn.addRequestProperty("User-Agent", "Mozilla/4.76");
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
		StringBuffer sb = new StringBuffer();
		String s = "";

		while((s=br.readLine())!=null){
			sb.append(s);
		}
		return sb.toString();
	}
}
