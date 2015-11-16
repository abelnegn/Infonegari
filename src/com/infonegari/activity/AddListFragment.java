package com.infonegari.activity;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.infonegari.fragment.HomeFragment;
import com.infonegari.objects.db.AddList;
import com.infonegari.objects.db.Location;
import com.infonegari.util.DialogHandler;
import com.joanzapata.android.iconify.IconDrawable;
import com.joanzapata.android.iconify.Iconify;
import com.orm.query.Select;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddListFragment extends Fragment {
	private static final int ACTION_TAKE_PHOTO_B = 1;
	private static final int PICKFILE_RESULT_CODE = 2;
	private static final String BITMAP_STORAGE_KEY = "viewbitmap";
	private static final String IMAGEVIEW_VISIBILITY_STORAGE_KEY = "imageviewvisibility";
	private Bitmap mImageBitmap;
	private String mCurrentPhotoPath;
	View rootView;
	private static final String JPEG_FILE_PREFIX = "IMG_";
	private static final String JPEG_FILE_SUFFIX = ".jpg";

	private AlbumStorageDirFactory mAlbumStorageDirFactory = null;

	List<Location> locationList;
	HashMap<String, Long> locationHashMap = new HashMap<String, Long>();
	private Spinner sp_location;
	private TextView txtImageName;
	private EditText txtTitle, txtDiscription;
	private Button btnChoosePic, btnTakePhoto, btnAddList, btnBack;
	private static final int MENU_ITEM_BACK = 2000;
	private DialogHandler dlgHandler;  
	private String userName = "";
	private String category = "";
	
	/* Photo album for this application */
	private String getAlbumName() {
		return getString(R.string.album_name);
	}

	
	private File getAlbumDir() {
		File storageDir = null;

		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
			
			storageDir = mAlbumStorageDirFactory.getAlbumStorageDir(getAlbumName());

			if (storageDir != null) {
				if (! storageDir.mkdirs()) {
					if (! storageDir.exists()){
						Log.d("CameraSample", "failed to create directory");
						return null;
					}
				}
			}
			
		} else {
			Log.v(getString(R.string.app_name), "External storage is not mounted READ/WRITE.");
		}
		
		return storageDir;
	}

	private File createImageFile() throws IOException {
		// Create an image file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String imageFileName = JPEG_FILE_PREFIX + timeStamp + "_";
		File albumF = getAlbumDir();
		File imageF = File.createTempFile(imageFileName, JPEG_FILE_SUFFIX, albumF);
		return imageF;
	}

	private File setUpPhotoFile() throws IOException {
		
		File f = createImageFile();
		mCurrentPhotoPath = f.getAbsolutePath();
		
		return f;
	}

	private void galleryAddPic() {
		    Intent mediaScanIntent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
			File f = new File(mCurrentPhotoPath);
		    Uri contentUri = Uri.fromFile(f);
		    mediaScanIntent.setData(contentUri);
		    getActivity().sendBroadcast(mediaScanIntent);
	}

	private void dispatchTakePictureIntent(int actionCode) {

		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

		switch(actionCode) {
		case ACTION_TAKE_PHOTO_B:
			File f = null;
			
			try {
				f = setUpPhotoFile();
				mCurrentPhotoPath = f.getAbsolutePath();
				takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
			} catch (IOException e) {
				e.printStackTrace();
				f = null;
				mCurrentPhotoPath = null;
			}
			break;

		default:
			break;			
		} // switch

		startActivityForResult(takePictureIntent, actionCode);
	}

	private void handleBigCameraPhoto() {

		if (mCurrentPhotoPath != null) {
			galleryAddPic();
			txtImageName.setText(mCurrentPhotoPath);
			mCurrentPhotoPath = null;
		}

	}

	Button.OnClickListener mTakePicOnClickListener = 
		new Button.OnClickListener() {
		@Override
		public void onClick(View v) {
			dispatchTakePictureIntent(ACTION_TAKE_PHOTO_B);
		}
	};

	public AddListFragment(){
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_add_list, container, false);
		sp_location = (Spinner) rootView.findViewById(R.id.location);
		txtTitle = (EditText) rootView.findViewById(R.id.title);
		txtDiscription = (EditText) rootView.findViewById(R.id.item_description);
		txtImageName = (TextView) rootView.findViewById(R.id.image_name);
		btnChoosePic = (Button) rootView.findViewById(R.id.choose_photo);
		btnTakePhoto = (Button) rootView.findViewById(R.id.take_photo);
        btnAddList = (Button) rootView.findViewById(R.id.btn_add_list);
        btnBack = (Button) rootView.findViewById(R.id.btn_back);
        
        mImageBitmap = null;
        
        category = getArguments().getString("Add_Category");
        userName = getArguments().getString("User_Name");
        
		getActivity().setTitle("Add " + category);
		
		fetchLocation();
		
		Button picBtn = (Button) rootView.findViewById(R.id.take_photo);
		setBtnListenerOrDisable( 
				picBtn, 
				mTakePicOnClickListener,
				MediaStore.ACTION_IMAGE_CAPTURE
		);
		
	      
		btnChoosePic.setOnClickListener(new Button.OnClickListener(){
		   @Override
		   public void onClick(View arg0) {
		    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		             intent.setType("file/*");
		       startActivityForResult(intent,PICKFILE_RESULT_CODE);
		    
		   }
		});
	       
        btnAddList.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
	    		dlgHandler = new DialogHandler();
	    		dlgHandler.Confirm(getActivity(), getString(R.string.dlg_header_update), getString(R.string.dlg_detail_message), 
	    				getString(R.string.btn_upload_now), getString(R.string.btn_upload_later), updateNow(), updateLater());

			}
		});
 
		btnBack.setOnClickListener(new Button.OnClickListener(){
			   @Override
			   public void onClick(View arg0) {
					FragmentManager fragmentManager = getFragmentManager();
					HomeFragment fragment = new HomeFragment();
					fragmentManager.beginTransaction()
							.replace(R.id.frame_container, fragment).commit();
			    
			   }
			});
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
			mAlbumStorageDirFactory = new FroyoAlbumDirFactory();
		} else {
			mAlbumStorageDirFactory = new BaseAlbumDirFactory();
		}
		
		return rootView;
	}

	private Runnable updateNow(){
		Runnable runnable = new Runnable()
	    {
	        @Override
	        public void run()
	        {        	
	        }
	    };
	    return runnable;
	}
	
	private Runnable updateLater(){
		Runnable runnable = new Runnable()
	    {
	        @Override
	        public void run()
	        {        	
	        	if(txtTitle.getText().toString().equals("") ||
	        			txtDiscription.getText().toString().equals("") ||
	        			txtImageName.getText().toString().equals("") ||
	        			sp_location.getSelectedItem().toString().equals("All Location")){
	        		
	        	}else{
	        		saveAddList();
	        		clearFields();
	        		Toast.makeText(getActivity(), "Data saved locally", Toast.LENGTH_SHORT);
	        	}
	        }
	    };
	    return runnable;
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
			case ACTION_TAKE_PHOTO_B: {
				if (resultCode == getActivity().RESULT_OK) {
					handleBigCameraPhoto();
				}
				break;
			}case PICKFILE_RESULT_CODE:{
			   if(resultCode==getActivity().RESULT_OK){
				    String FilePath = data.getData().getPath();
				    txtImageName.setText(FilePath);
			   }
			   break;				
			}
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putParcelable(BITMAP_STORAGE_KEY, mImageBitmap);
		outState.putBoolean(IMAGEVIEW_VISIBILITY_STORAGE_KEY, (mImageBitmap != null) );
		super.onSaveInstanceState(outState);
	}

	public static boolean isIntentAvailable(Context context, String action) {
		final PackageManager packageManager = context.getPackageManager();
		final Intent intent = new Intent(action);
		List<ResolveInfo> list =
			packageManager.queryIntentActivities(intent,
					PackageManager.MATCH_DEFAULT_ONLY);
		return list.size() > 0;
	}

	private void setBtnListenerOrDisable( 
			Button btn, 
			Button.OnClickListener onClickListener,
			String intentName
	) {
		if (isIntentAvailable(getActivity(), intentName)) {
			btn.setOnClickListener(onClickListener);        	
		} else {
			btn.setText( 
				getText(R.string.cannot).toString() + " " + btn.getText());
			btn.setClickable(false);
		}
	}
	
	private void fetchLocation(){
		List<String> listOfLocations = new ArrayList<String>();
		locationList = Select.from(Location.class).orderBy("Location_Name ASC").list();

		listOfLocations.add("All Location");
		locationHashMap.put("All Location", 0L);
		for (Location location : locationList) {
			listOfLocations.add(location.getLocationName());
			locationHashMap.put(location.getLocationName(), location.getLocationId());
        }
        ArrayAdapter<String> locationAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listOfLocations);

        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_location.setAdapter(locationAdapter);
        sp_location.setSelection(0);
	}
	
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
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
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == MENU_ITEM_BACK) {
			FragmentManager fragmentManager = getFragmentManager();
			HomeFragment fragment = new HomeFragment();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, fragment).commit();
        }
        return super.onOptionsItemSelected(item);
    }
    
//    private void uploadImage(File pngFile) {
//        API.clientService.uploadClientImage(clientId,
//                new TypedFile("image/png", pngFile),
//                new Callback<Response>() {
//                    @Override
//                    public void success(Response response, Response response2) {
//                        Toast.makeText(activity, "Client image updated", Toast.LENGTH_SHORT).show();
//                        imageLoadingAsyncTask = new ImageLoadingAsyncTask();
//                        imageLoadingAsyncTask.execute(clientId);
//
//                    }
//
//                    @Override
//                    public void failure(RetrofitError retrofitError) {
//                        Toast.makeText(activity, "Failed to update image", Toast.LENGTH_SHORT).show();
//                        imageLoadingAsyncTask = new ImageLoadingAsyncTask();
//                        imageLoadingAsyncTask.execute(clientId);
//
//                    }
//                }
//        );
//    }
    
    private void saveAddList(){
    	AddList newAddList = new AddList();
    	newAddList.setTitle(txtTitle.getText().toString());
    	newAddList.setDiscription(txtDiscription.getText().toString());
    	newAddList.setCategory(category);
    	newAddList.setImageUrl(txtImageName.getText().toString());
    	newAddList.setLocationId(locationHashMap.get(sp_location.getSelectedItem().toString()));
    	newAddList.setUserId(userName);
    	
    	newAddList.save();
    }
    
    private void clearFields(){
    	txtTitle.setText("");
    	txtDiscription.setText("");
    	txtImageName.setText("");
    	sp_location.setSelection(0);
    	txtTitle.setFocusable(true);
    }
}