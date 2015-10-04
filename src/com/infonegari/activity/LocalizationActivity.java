package com.infonegari.activity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;

public class LocalizationActivity extends Activity {
	private String[] languages = {"Amharic", "English", "Francais" };
	private CheckBox saveSetting;
	private Spinner sLocal;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_localization);
		
		sLocal = (Spinner)findViewById(R.id.localization);
		saveSetting = (CheckBox)findViewById(R.id.save_language);
		sLocal.setPrompt("Select Language");
		
		ArrayAdapter adapter = new ArrayAdapter(this,
			    android.R.layout.simple_spinner_item, languages);
			  adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			  sLocal.setAdapter(adapter);

			  sLocal.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView arg0, View arg1,
						     int arg2, long arg3) {
						    Configuration config = new Configuration();
						    switch (arg2) {
						    case 0:
						     config.locale = Locale.ENGLISH;
						     break;
						    case 1:
						     config.locale = Locale.ITALIAN;
						     break;
						    case 2:
						     config.locale = Locale.FRENCH;
						     break;
						    default:
						     config.locale = Locale.ENGLISH;
						     break;
						    }
						    getResources().updateConfiguration(config, null);
					}
		
					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
		
					}
			  });
			  
			  displaySettingFile();
	}
	
	public void onClick(View v){	
		saveSettingFile();
		startActivity(new Intent(getBaseContext(), MainActivity.class));	
	}

	private void displaySettingFile(){
    	try {
    		File file = new File(getFilesDir(),"settingFile.txt");
    		if(file.exists()){
				FileInputStream fileInputStream = openFileInput("settingFile.txt");
	        	BufferedReader reader;
	        	if(fileInputStream != null){
	        		reader = new BufferedReader(new InputStreamReader(fileInputStream));
		       		String line;
		       		while ((line = reader.readLine()) != null) {
		       			String[] locValue = line.split(",");
		       			if(locValue[1].equals("True")){
		       				saveSetting.setChecked(true);
		       				
		       				for(int i=0; i < languages.length; i++){
		       					if(sLocal.getItemAtPosition(i).equals(locValue[0])){
		       						sLocal.setSelection(i);
		       					}
		       				}
						}
		       		}
	        	}
    		}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
    }		

    private void saveSettingFile(){
    	String isSaved = "";
    	if(saveSetting.isChecked()==true)
    		isSaved = "True";
    	else
    		isSaved = "False";
    	
    	String txtLanguage = sLocal.getSelectedItem().toString();
    	
    	String setting = txtLanguage + "," + isSaved;
		FileOutputStream fileOutputStream = null;
		File file = null;

		try {
			file = getFilesDir();
			fileOutputStream = openFileOutput("settingFile.txt", Context.MODE_PRIVATE);
			fileOutputStream.write(setting.getBytes());
            PrintWriter pw = new PrintWriter(fileOutputStream);
            pw.flush();
            pw.close();
			fileOutputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) { 
			e.printStackTrace();
		}
    }
    
}
