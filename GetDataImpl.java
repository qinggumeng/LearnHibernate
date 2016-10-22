package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class GetDataImpl implements GetData {
	public List<String> get(String urlStr,String reg1,String reg2){
		List<String> list = new ArrayList<String>();
		try {
			String html = Util.getHTML(urlStr);
			List<String> fList = Util.getStringFromHTML
					(html, reg1);
			String str = null;
			if(fList.size()>=1){
				str = fList.get(0);
			}
			list = Util.getStringFromHTML(str, reg2);
			//System.out.println(list);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}


	@Override
	public List<String> getAllDistrictURL() {
		return get(FIRST_URL,
				"<p class=\"title\">区属：</p>(.*?</dl>)",
				"<a   href='(.*?)'.*?</a>");
	}

	public static void main(String[] args) {
		new GetDataImpl().parseSQL();
	}

	@Override
	public List<String> getStreetURLByDistrictURL(String dURL) {
		return get(dURL,
				"<dl class=\"areaTypeInner\">(.*?</dl>)",
				"<a   href='(.*?)'.*?</a>");
	}

	@Override
	public List<List<String>> getAllStreetURL() {
		List<List<String>> sList = new ArrayList<List<String>>();
		List<String> dList = getAllDistrictURL();
		for(String str:dList){
			List<String> list = getStreetURLByDistrictURL(str);
			sList.add(list);
		}
		return sList;
	}

	@Override
	public List<List<String>> getHouseURL() {
		List<List<String>> hList = new ArrayList<List<String>>();
		List<List<String>> sList = getAllStreetURL();//获得所有街区的url
		for(int i=0;i<sList.size();i++){
			List<String> ssList = sList.get(i);
			for(int j=0;j<ssList.size();j++){
				List<String> list = get(ssList.get(j),
						"(<dl id=\"JS_listPag\".*?</dl>)",
						"(<dd class=\"listItem clearfix\">.*?</dd>)");
				if(list!=null){
					list.add(ssList.get(j));
				}
				hList.add(list);
			}
		}
		return hList;
	}

	@Override
	public String parseSQL(){
		try {
			System.setOut(new PrintStream(new File("c://data.txt")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<List<String>> hList = new GetDataImpl().getHouseURL();
		for(int i=0;i<hList.size();i++){
			List<String> list = hList.get(i);
			if(list!=null){
				for(int j=0;j<list.size();j++){
					System.out.println((i+1)+":::"+(j+1));
					System.out.println(list.get(j));
				}
			}
		}
		return null;
	}

}
