package com.ivoiceafrica.ivoiceafrica.utility;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivoiceafrica.ivoiceafrica.models.CountryCodesBean;

public class ReadFromJsonFile {
	
	public CountryCodesBean[] readFromJson(String filePath) {
		
		ObjectMapper mapper = new ObjectMapper();
		CountryCodesBean[] countryCode = null;
		try {
			countryCode = mapper.readValue(new File(filePath), CountryCodesBean[].class);
		} catch (StreamReadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return countryCode;
	}
	
	
	
}
