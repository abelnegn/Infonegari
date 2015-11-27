package com.infonegari.fragment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import com.infonegari.activity.R;
import com.infonegari.activity.SplashScreen;
import com.joanzapata.android.iconify.IconDrawable;
import com.joanzapata.android.iconify.Iconify;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class LanguageFragment extends Fragment{
	private String[] languages = {"English", "Amharic"};
	private CheckBox saveSetting;
	private Spinner sLocal;
	private Button btnOk;
	private static final int MENU_ITEM_BACK = 2000;
	
	public LanguageFragment(){}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
  
		getActivity().setTitle(getString(R.string.menu_language));
        View rootView = inflater.inflate(R.layout.activity_localization, container, false);
        
		sLocal = (Spinner)rootView.findViewById(R.id.localization);
		saveSetting = (CheckBox)rootView.findViewById(R.id.save_language);
		btnOk = (Button)rootView.findViewById(R.id.btn_ok);
		sLocal.setPrompt("Select Language");
		
		btnOk.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				saveSettingFile();
				refreshMenu();
			}
		});
	       
		ArrayAdapter adapter = new ArrayAdapter(getActivity(),
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
						     config.locale = Locale.US;
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
			  
        return rootView;
	}
	
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        MenuItem mItemSearchClient = menu.add(Menu.NONE, MENU_ITEM_BACK, Menu.NONE, "Back");
        mItemSearchClient.setIcon(new IconDrawable(getActivity(), Iconify.IconValue.fa_backward)
        .colorRes(R.color.black)
        .actionBarSize());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            mItemSearchClient.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }
        super.onPrepareOptionsMenu(menu);
    }
    
    private void backMenu(){
		FragmentManager fragmentManager = getFragmentManager();
		HomeFragment fragment = new HomeFragment();
		fragmentManager.beginTransaction()
				.replace(R.id.frame_container, fragment).commit();
    }
    
    private void refreshMenu(){
		Intent backIntent = new Intent(getActivity(), SplashScreen.class);
		startActivity(backIntent);
    }
    
    @Override
    public void onResume() {
       super.onResume();

       getView().setFocusableInTouchMode(true);
       getView().requestFocus();
       getView().setOnKeyListener(new View.OnKeyListener() {
          @Override
          public boolean onKey(View v, int keyCode, KeyEvent event) {

              if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){
            	   backMenu();
                   return true;
               }
               return false;
           }
       });
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == MENU_ITEM_BACK) {
        	backMenu();
        }
        return super.onOptionsItemSelected(item);
    }
    
	private void displaySettingFile(){
    	try {
    		File file = new File(getActivity().getFilesDir(),"settingFile.txt");
    		if(file.exists()){
				FileInputStream fileInputStream = getActivity().openFileInput("settingFile.txt");
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
    		isSaved = "True";
    	
    	String txtLanguage = sLocal.getSelectedItem().toString();
    	
    	String setting = txtLanguage + "," + isSaved;
		FileOutputStream fileOutputStream = null;
		File file = null;

		try {
			file = getActivity().getFilesDir();
			fileOutputStream = getActivity().openFileOutput("settingFile.txt", Context.MODE_PRIVATE);
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
