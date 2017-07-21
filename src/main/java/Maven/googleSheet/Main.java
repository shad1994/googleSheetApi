package Maven.googleSheet;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

public class Main {
	
	static requests req;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		req=new requests();
		//createSheet();
		getSheetData();
		updateSheetData();

	}
	
	public static void getSheetData() throws Exception
	{
		String response=req.sendGet("https://sheets.googleapis.com/v4/spreadsheets/1rIYYVjb4HBg6hziyD9cXD7vhHajB8cu_ptgAAIdBnWM/values/A2:B4");
		JSONObject resObj=new JSONObject(response);
		JSONArray valuesObj=resObj.getJSONArray("values");
		for(int i=0;i<valuesObj.length();i++)
		{
			JSONArray value=valuesObj.getJSONArray(i);
			System.out.println(value.get(0)+"    "+value.get(1));
		}
		
		//System.out.println("response:" +response);
	}
	
	public static void updateSheetData() throws Exception
	{
		String response=req.sendGet("https://sheets.googleapis.com/v4/spreadsheets/1rIYYVjb4HBg6hziyD9cXD7vhHajB8cu_ptgAAIdBnWM/values/A2:B4");
		JSONObject resObj=new JSONObject(response);
		JSONArray valuesObj=resObj.getJSONArray("values");
		for(int i=0;i<valuesObj.length();i++)
		{
			JSONArray value=valuesObj.getJSONArray(i);
			System.out.println(value.get(0)+"    "+value.get(1));
		}
	}
	

}
