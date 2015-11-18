package com.infonegari.activity;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedFile;
import com.infonegari.fragment.HomeFragment;
import com.infonegari.objects.db.AddList;
import com.infonegari.objects.db.CarType;
import com.infonegari.objects.db.ConstructionMachine;
import com.infonegari.objects.db.ConstructionMaterial;
import com.infonegari.objects.db.HallType;
import com.infonegari.objects.db.HouseType;
import com.infonegari.objects.db.Location;
import com.infonegari.objects.db.RestaurantType;
import com.infonegari.objects.db.UsedItemType;
import com.infonegari.service.API;
import com.infonegari.util.DialogHandler;
import com.infonegari.util.Network;
import com.infonegari.util.SafeUIBlockingUtility;
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
	public static final String FILE_UPLOAD_URL = "www.infonegari.com/info_service/fileUpload.php";

	private AlbumStorageDirFactory mAlbumStorageDirFactory = null;

	List<Location> locationList;
	HashMap<String, Long> locationHashMap = new HashMap<String, Long>();
	private Spinner sp_location, sp_type_one, sp_type_two, sp_type_thee;
	private TextView txtImageName;
	private EditText txtTitle, txtDiscription, item_text_one, item_text_two;
	private Button btnChoosePic, btnTakePhoto, btnAddList, btnBack;
	private static final int MENU_ITEM_BACK = 2000;
	private DialogHandler dlgHandler;  
	private String userName = "";
	private String category = "";
	SafeUIBlockingUtility safeUIBlockingUtility;
	long totalSize = 0;
	private String itemType1 = "";
	private String itemType2 = "";
	private String itemType3 = "";
	private String itemType4 = "";
	private int categoryId = 0;
	List<CarType> carTypeList;
	List<HallType> hallList;
	List<UsedItemType> usedItemTypeList;
	List<HouseType> houseTypeList;
	List<RestaurantType> typeList;
	List<ConstructionMachine> conMachineList;
	List<ConstructionMaterial> conMaterialList;
	HashMap<String, Long> conMachineHashMap = new HashMap<String, Long>();
	HashMap<String, Long> conMaterialHashMap = new HashMap<String, Long>();
	HashMap<String, Long> typeHashMap = new HashMap<String, Long>();
	HashMap<String, Long> houseTypeHashMap = new HashMap<String, Long>();
	HashMap<String, Long> hallTypeHashMap = new HashMap<String, Long>();
	HashMap<String, Long> carTypeHashMap = new HashMap<String, Long>();
	HashMap<String, Long> saloonTypeHashMap = new HashMap<String, Long>();
	HashMap<String, Long> usedItemTypeHashMap = new HashMap<String, Long>();
	HashMap<String, String> pharmacyTypeHashMap = new HashMap<String, String>();
	HashMap<String, String> eventTypeHashMap = new HashMap<String, String>();
	HashMap<String, String> weddingClothTypeHashMap = new HashMap<String, String>();
	HashMap<String, String> weddingServiceHashMap = new HashMap<String, String>();
	HashMap<String, String> garageServiceTypeHashMap = new HashMap<String, String>();
	HashMap<String, String> furnitureTypeHashMap = new HashMap<String, String>();
	HashMap<String, String> noRoomsTypeHashMap = new HashMap<String, String>();
	HashMap<String, String> serviceHashMap = new HashMap<String, String>();
	HashMap<String, String> electronicCategoryHashMap = new HashMap<String, String>();
	HashMap<String, String> accessoryTypeHashMap = new HashMap<String, String>();
	HashMap<String, String> shopCatHashMap = new HashMap<String, String>();
	
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

		initialize(inflater, container);
        
        
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

	private void initialize(LayoutInflater inflater, ViewGroup container){
		category = getArguments().getString("Add_Category");
		categoryId = getArguments().getInt("Category_Id");
		if(categoryId == 0 || categoryId == 1 || categoryId == 3 || categoryId == 4 || 
				categoryId == 8 || categoryId == 9 || categoryId == 10 || categoryId == 25 ||
				categoryId == 22 || categoryId == 27 || categoryId == 28){
			rootView = inflater.inflate(R.layout.fragment_add_list_one, container, false);
		}else if(categoryId== 2 || categoryId == 6 || categoryId==7 || categoryId == 12 || categoryId == 17 || 
				categoryId ==20 || categoryId == 21 || categoryId == 24 || categoryId == 26){
			rootView = inflater.inflate(R.layout.fragment_add_list_two, container, false);
			sp_type_one = (Spinner) rootView.findViewById(R.id.type_one);	
			if(categoryId== 2)			
				fetchCarType();
			else if(categoryId == 6)
				fetchHallType();
			else if(categoryId == 7)
				fetchSaloonType();
			else if(categoryId == 12)
				fetchUsedItemType();
			else if(categoryId == 17)
				fetchHouseType();
			else if(categoryId == 20)
				fetchPharmacyType();
			else if(categoryId == 21)
				fetchPharmacyType();
			else if(categoryId == 24)
				fetchRestaurantType();
			else if(categoryId == 26)
				fetchEventType();
		}else if(categoryId == 5 ||  categoryId ==19 || categoryId == 23 || categoryId ==30 || 
				categoryId== 31 || categoryId ==32){
			rootView = inflater.inflate(R.layout.fragment_add_list_three, container, false);
			sp_type_one = (Spinner) rootView.findViewById(R.id.type_one);
			sp_type_two = (Spinner) rootView.findViewById(R.id.type_two);
			if(categoryId == 5){
				fetchWeddingClothType();
				fetchWeddingService();
			}else if(categoryId == 19){
				fetchPharmacyType();
				fetchGarageServiceType();
			}else if(categoryId == 23){
				fetchMachine();
				fetchMaterial();
			}else if(categoryId == 30){
				fetchElectronicCategory();
				fetchService();
			}else if(categoryId == 31){
				fetchFurnitureType();
				fetchFurniturePlace();
			}else if(categoryId == 32){
				fetchAccessoryType();
				fetchService();
			}
		}else if(categoryId == 11 || categoryId==15){
			rootView = inflater.inflate(R.layout.fragment_add_list_four, container, false);
			sp_type_one = (Spinner) rootView.findViewById(R.id.type_one);
			item_text_one = (EditText) rootView.findViewById(R.id.item_text);
			item_text_one.setHint("Year/Model");
			fetchCarType();
		}else if(categoryId == 13 || categoryId ==14 || categoryId == 16 || categoryId == 18){
			rootView = inflater.inflate(R.layout.fragment_add_list_five, container, false);
			sp_type_one = (Spinner) rootView.findViewById(R.id.type_one);
			sp_type_two = (Spinner) rootView.findViewById(R.id.type_two);
			item_text_one = (EditText) rootView.findViewById(R.id.item_text);
			item_text_one.setHint("Lot Size");
			fetchHouseType();
			fetchNoRoomsService();
		}else{
			rootView = inflater.inflate(R.layout.fragment_add_list_seven, container, false);
			sp_type_one = (Spinner) rootView.findViewById(R.id.type_one);
			sp_type_two = (Spinner) rootView.findViewById(R.id.type_two);
			item_text_one = (EditText) rootView.findViewById(R.id.item_text_one);
			item_text_two = (EditText) rootView.findViewById(R.id.item_text_two);
			item_text_one.setHint("Color");
			item_text_two.setHint("Size");
			fetchClothCategory();
			fetchService();
		}
		
		
		sp_location = (Spinner) rootView.findViewById(R.id.location);
		txtTitle = (EditText) rootView.findViewById(R.id.title);
		txtDiscription = (EditText) rootView.findViewById(R.id.item_description);
		txtImageName = (TextView) rootView.findViewById(R.id.image_name);
		btnChoosePic = (Button) rootView.findViewById(R.id.choose_photo);
		btnTakePhoto = (Button) rootView.findViewById(R.id.take_photo);
        btnAddList = (Button) rootView.findViewById(R.id.btn_add_list);
        btnBack = (Button) rootView.findViewById(R.id.btn_back);
        
        mImageBitmap = null;		
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

		listOfLocations.add("Select Location");
		locationHashMap.put("Select Location", 0L);
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
	
	private void fetchCarType(){
		List<String> listOfCarType = new ArrayList<String>();
		carTypeList = Select.from(CarType.class).orderBy("Car_Type_Name ASC").list();

		listOfCarType.add("Select Car Type");
		carTypeHashMap.put("Select Car Type", 0L);
		for (CarType carType : carTypeList) {
			listOfCarType.add(carType.getCarTypeName());
			carTypeHashMap.put(carType.getCarTypeName(), carType.getCarTypeId());
        }
        ArrayAdapter<String> carTypeAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listOfCarType);

        carTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_type_one.setAdapter(carTypeAdapter);
        sp_type_one.setSelection(0);
	}
	
	private void fetchHallType(){
		List<String> listOfType = new ArrayList<String>();

		hallList = Select.from(HallType.class).orderBy("HallType ASC").list();

		listOfType.add("Select Hall Type");
		hallTypeHashMap.put("Select Hall Type", 0L);
		for (HallType type : hallList) {
			listOfType.add(type.getHall_Type());
			hallTypeHashMap.put(type.getHall_Type(), type.getHtId());
        }
			
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listOfType);

        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_type_one.setAdapter(typeAdapter);
        sp_type_one.setSelection(0);
	}
	
	private void fetchSaloonType(){
		List<String> listOfType = new ArrayList<String>();

		listOfType.add("Select Type");		
		listOfType.add("Female");
		listOfType.add("Male");
		listOfType.add("Both");
			
		saloonTypeHashMap.put("Select Type", 0L);
		saloonTypeHashMap.put("Female", 1L);
		saloonTypeHashMap.put("Male", 2L);
		saloonTypeHashMap.put("Both", 3L);
		
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listOfType);

        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_type_one.setAdapter(typeAdapter);
        sp_type_one.setSelection(0);
	}
	
	private void fetchUsedItemType(){
		List<String> listOfType = new ArrayList<String>();
		usedItemTypeList = Select.from(UsedItemType.class).orderBy("Used_Item_Type_Name ASC").list();

		listOfType.add("Select Type");
		usedItemTypeHashMap.put("Select Type", 0L);
		for (UsedItemType type : usedItemTypeList) {
			listOfType.add(type.getUsedItemTypeName());
			usedItemTypeHashMap.put(type.getUsedItemTypeName(), type.getUsedItemTypeId());
        }
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listOfType);

        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_type_one.setAdapter(typeAdapter);
        sp_type_one.setSelection(0);
	}
	
	private void fetchHouseType(){
		List<String> listOfHouseTypes = new ArrayList<String>();
		houseTypeList = Select.from(HouseType.class).orderBy("House_Type_Name ASC").list();

		listOfHouseTypes.add("Select House Type");
		houseTypeHashMap.put("Select House Type", 0L);
		for (HouseType type : houseTypeList) {
			listOfHouseTypes.add(type.getHouseTypeName());
			houseTypeHashMap.put(type.getHouseTypeName(), type.getHouseTypeId());
        }
        ArrayAdapter<String> houseTypeAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listOfHouseTypes);

        houseTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_type_one.setAdapter(houseTypeAdapter);
        sp_type_one.setSelection(0);
	}
	
	private void fetchPharmacyType(){
		List<String> listOfType = new ArrayList<String>();

		listOfType.add("Select Type");		
		listOfType.add("Higher");
		listOfType.add("Meddium");
		listOfType.add("Small");
			
		pharmacyTypeHashMap.put("Select Type", "0");
		pharmacyTypeHashMap.put("Higher", "Heavy");
		pharmacyTypeHashMap.put("Meddium", "Meddium");
		pharmacyTypeHashMap.put("Small", "Small");
		
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listOfType);

        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_type_one.setAdapter(typeAdapter);
        sp_type_one.setSelection(0);
	}
	
	private void fetchRestaurantType(){
		List<String> listOfType = new ArrayList<String>();
		typeList = Select.from(RestaurantType.class).orderBy("Restaurant_Type_Name ASC").list();

		listOfType.add("Select Restaurant Type");
		typeHashMap.put("Select Restaurant Type", 0L);
		for (RestaurantType type : typeList) {
			listOfType.add(type.getRestaurantTypeName());
			typeHashMap.put(type.getRestaurantTypeName(), type.getRestaurantTypeId());
        }
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listOfType);

        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_type_one.setAdapter(typeAdapter);
        sp_type_one.setSelection(0);
	}
	
	private void fetchEventType(){
		List<String> listOfType = new ArrayList<String>();

		listOfType.add("Select Event Type");		
		listOfType.add("Art Exhibitions");
		listOfType.add("Music Concert");
		listOfType.add("Exhibition");
		listOfType.add("Festival");
			
		eventTypeHashMap.put("Select  Event Type", "0");
		eventTypeHashMap.put("Art Exhibitions", "1");
		eventTypeHashMap.put("Music Concert", "2");
		eventTypeHashMap.put("Exhibition", "3");
		eventTypeHashMap.put("Festival", "4");
		
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listOfType);

        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_type_one.setAdapter(typeAdapter);
        sp_type_one.setSelection(0);
	}
	
	private void fetchWeddingClothType(){
		List<String> listOfClothType = new ArrayList<String>();

		listOfClothType.add("Select Cloth Type");		
		listOfClothType.add("Modern");
		listOfClothType.add("Traditional");
			
		weddingClothTypeHashMap.put("Select Cloth Type", "0");
		weddingClothTypeHashMap.put("Modern", "modern");
		weddingClothTypeHashMap.put("Traditional", "traditional");
		
        ArrayAdapter<String> clothTypeAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listOfClothType);

        clothTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_type_one.setAdapter(clothTypeAdapter);
        sp_type_one.setSelection(0);
	}

	private void fetchWeddingService(){
		List<String> listOfService = new ArrayList<String>();

		listOfService.add("Select Service");		
		listOfService.add("Buy");
		listOfService.add("Rent");
		listOfService.add("Sell");
			
		weddingServiceHashMap.put("Select Service", "0");
		weddingServiceHashMap.put("Buy", "buy");
		weddingServiceHashMap.put("Rent", "rent");
		weddingServiceHashMap.put("Sell", "sell");
		
        ArrayAdapter<String> serviceAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listOfService);

        serviceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_type_two.setAdapter(serviceAdapter);
        sp_type_two.setSelection(0);
	}
	
	private void fetchGarageServiceType(){
		List<String> listOfClothType = new ArrayList<String>();

		listOfClothType.add("Select Service Type");		
		listOfClothType.add("Electricity");
		listOfClothType.add("Mechanical");
		listOfClothType.add("Body Part");
		listOfClothType.add("All");
			
		garageServiceTypeHashMap.put("Select Service Type", "0");
		garageServiceTypeHashMap.put("Electriccity", "Electriccity");
		garageServiceTypeHashMap.put("Mechanical", "Mechanical");
		garageServiceTypeHashMap.put("Body Part", "BodyPart");
		garageServiceTypeHashMap.put("All", "All");
		
        ArrayAdapter<String> clothTypeAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listOfClothType);

        clothTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_type_two.setAdapter(clothTypeAdapter);
        sp_type_two.setSelection(0);
	}
	
	private void fetchFurnitureType(){
		List<String> listOfType = new ArrayList<String>();

		listOfType.add("Select Furniture");
		listOfType.add("Home");		
		listOfType.add("Office");

		furnitureTypeHashMap.put("Select Furniture", "0");
		furnitureTypeHashMap.put("Home", "home");
		furnitureTypeHashMap.put("Office", "office");
		
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listOfType);

        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_type_one.setAdapter(typeAdapter);
        sp_type_one.setSelection(0);
	}
	
	private void fetchFurniturePlace(){
		List<String> listOfType = new ArrayList<String>();

		listOfType.add("Ethiopian");		
		listOfType.add("Imported");

		furnitureTypeHashMap.put("Ethiopian", "ethiopian");
		furnitureTypeHashMap.put("Imported", "imported");
		
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listOfType);

        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_type_two.setAdapter(typeAdapter);
        sp_type_two.setSelection(0);
	}
	
	private void fetchNoRoomsService(){
		List<String> listOfService = new ArrayList<String>();

		listOfService.add("Select No Rooms");		
		noRoomsTypeHashMap.put("Select No Rooms", "0");
		
		for(int i =1; i< 20; i++){
			listOfService.add(String.valueOf(i) + " Rooms");
			noRoomsTypeHashMap.put(String.valueOf(i) + " Rooms",String.valueOf(i));
		}
		
        ArrayAdapter<String> serviceAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listOfService);

        serviceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_type_two.setAdapter(serviceAdapter);
        sp_type_two.setSelection(0);
	}
	
	private void fetchMachine(){
		List<String> listOfMachines = new ArrayList<String>();
		conMachineList = Select.from(ConstructionMachine.class).orderBy("Machine ASC").list();

		listOfMachines.add("Select Machine");
		conMachineHashMap.put("Select Machine", 0L);
		for (ConstructionMachine machine : conMachineList) {
			listOfMachines.add(machine.getMachine());
			conMachineHashMap.put(machine.getMachine(), machine.getCmId());
        }
        ArrayAdapter<String> machineAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listOfMachines);

        machineAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_type_one.setAdapter(machineAdapter);
        sp_type_one.setSelection(0);
	}

	private void fetchMaterial(){
		List<String> listOfMaterials = new ArrayList<String>();
		conMaterialList = Select.from(ConstructionMaterial.class).orderBy("Materials ASC").list();

		listOfMaterials.add("Select Material");
		conMaterialHashMap.put("Select Material", 0L);
		for (ConstructionMaterial material : conMaterialList) {
			listOfMaterials.add(material.getMaterials());
			conMaterialHashMap.put(material.getMaterials(), material.getCm_id());
        }
        ArrayAdapter<String> materialAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listOfMaterials);

        materialAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_type_two.setAdapter(materialAdapter);
        sp_type_two.setSelection(0);
	}
	
	private void fetchElectronicCategory(){
		List<String> listOfElectronicCat = new ArrayList<String>();

		listOfElectronicCat.add("Select Electronics");
		listOfElectronicCat.add("TV,DVD,Tape");		
		listOfElectronicCat.add("Refrigrator");
		listOfElectronicCat.add("Mobiles");

		electronicCategoryHashMap.put("Select Electronics", "0");
		electronicCategoryHashMap.put("TV,DVD,Tape", "tv");
		electronicCategoryHashMap.put("Refrigrator", "refrigrator");
		electronicCategoryHashMap.put("Mobiles", "mobile");
		
        ArrayAdapter<String> serviceTypeAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listOfElectronicCat);

        serviceTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_type_one.setAdapter(serviceTypeAdapter);
        sp_type_one.setSelection(0);
	}
	
	private void fetchService(){
		List<String> listOfService = new ArrayList<String>();

		listOfService.add("Select Service");
		listOfService.add("Buy");		
		listOfService.add("Maintenance");

		serviceHashMap.put("Select Service", "0");
		serviceHashMap.put("Buy", "sell");
		serviceHashMap.put("Maintenance", "maintenance");
		
        ArrayAdapter<String> serviceAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listOfService);

        serviceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_type_two.setAdapter(serviceAdapter);
        sp_type_two.setSelection(0);
	}
	
	private void fetchAccessoryType(){
		List<String> listOfType = new ArrayList<String>();

		listOfType.add("Select Type");
		listOfType.add("Brand New");		
		listOfType.add("Assembled");
		listOfType.add("Used");

		accessoryTypeHashMap.put("Select Type", "0");
		accessoryTypeHashMap.put("Brand New", "new");
		accessoryTypeHashMap.put("Assembled", "assembled");
		accessoryTypeHashMap.put("Used", "used");
		
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listOfType);

        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_type_one.setAdapter(typeAdapter);
        sp_type_one.setSelection(0);
	}
	
	private void fetchClothCategory(){
		List<String> listOfClothCat = new ArrayList<String>();

		listOfClothCat.add("Select Category");		
		listOfClothCat.add("Female Cloth");
		listOfClothCat.add("Male Cloth");
		listOfClothCat.add("Kids Cloth");
		listOfClothCat.add("Cloth Designer");
			
		shopCatHashMap.put("Select Category", "0");
		shopCatHashMap.put("Female Cloth", "female_cloth");
		shopCatHashMap.put("Male Cloth", "Male_cloth");
		shopCatHashMap.put("Kids Cloth", "Kids_cloth");
		shopCatHashMap.put("Cloth Designer", "cloth_designer");
		
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listOfClothCat);

        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_type_one.setAdapter(categoryAdapter);
        sp_type_one.setSelection(0);
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
    
    private void saveAddList(){
    	AddList newAddList = new AddList();
    	newAddList.setTitle(txtTitle.getText().toString());
    	newAddList.setDiscription(txtDiscription.getText().toString());
    	newAddList.setCategory(category);
    	newAddList.setItemType1(itemType1);
    	newAddList.setItemType2(itemType2);
    	newAddList.setItemType3(itemType3);
    	newAddList.setItemType4(itemType4);
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
    
    private String getImageName(){
    	String imageName = "";
    	String[] imgs = txtImageName.getText().toString().split("/");
    	if(imgs.length > 0){
    		imageName = imgs[imgs.length - 1];
    	}    	
    	return imageName;
    }
    
    private void callService(String statment){       
		safeUIBlockingUtility = new SafeUIBlockingUtility(getActivity(), 
				"Updating", "Please Wait...");
		safeUIBlockingUtility.safelyBlockUI();
        API.insertAddListService.insertAddList(statment, new Callback<String>() {
                @Override
                public void success(String insert, Response response) {
    	        	String imgFile = txtImageName.getText().toString();
                	File f = new File(imgFile);
                	uploadImage(f);                  	
                }

                @Override
                public void failure(RetrofitError error) {
                	safeUIBlockingUtility.safelyUnBlockUI();
                }

        });  		    		    	
    }
    
	private void uploadImage(File jpgFile) {
	  API.insertAddListService.uploadImage(userName, 
			  new TypedFile("application/octet-stream", jpgFile), new Callback<String>() {
	              @Override
	              public void success(String success, Response response) {
	                  Toast.makeText(getActivity(), "Client image updated", Toast.LENGTH_SHORT).show();
	                  safeUIBlockingUtility.safelyUnBlockUI();
	              }
	
	              @Override
	              public void failure(RetrofitError retrofitError) {
	                  Toast.makeText(getActivity(), "Failed to update image", Toast.LENGTH_SHORT).show();
	                  safeUIBlockingUtility.safelyUnBlockUI();
	              }
	          }
	  );
	}
    
    public void uploadDecorator(){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO decorators(DecoratorName, LocationId, Price, Discription, isFeatured, MemberId, Latitude, Longitude, User_Name, Image, Op1, Is_Removed)" + 
    					  " VALUES('" + txtTitle.getText().toString() + "', " + locationHashMap.get(sp_location.getSelectedItem().toString()) + ", 0, '" + txtDiscription.getText().toString() + 
    					  "', 0, 0, '0.0', '0.0', '" + userName + "', '" + imgUrl + "', '', 0)";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
    
    public void uploadCatererPasteries(){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO caterersnpastries(CnPIdName, ServiceType, LocationId, Price, Discription, isFeatured, MemberId, Latitude, Longitude, User_Name, caterersnpastriesPic, Image, Is_Removed)" + 
    					  " VALUES('" + txtTitle.getText().toString() + "', '', " + locationHashMap.get(sp_location.getSelectedItem().toString()) + ", 0, '" + txtDiscription.getText().toString() + 
    					  "', 0, 0, '0.0', '0.0', '" + userName + "', '', '" + imgUrl + "', 0)";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
    
    public void uploadWeddingCar(){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO weddingcar(weddingCarName, CarTypeId, LocationId, Price, Discription, isFeatured, MemberId, Latitude, Longitude, User_Name, Image, Is_Removed)" + 
    					  " VALUES('" + txtTitle.getText().toString() + "', '" + itemType1 + "', " + locationHashMap.get(sp_location.getSelectedItem().toString()) + ", 0, '" + txtDiscription.getText().toString() + 
    					  "', 0, 0, '0.0', '0.0', '" + userName + "', '" + imgUrl + "', 0)";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
    
    public void uploadBand(){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO band(BandName, LocationId, Price, Discription, isFeatured, MemberId, Latitude, Longitude, User_Name, Image, Is_Removed)" + 
    					  " VALUES('" + txtTitle.getText().toString() + "', " + locationHashMap.get(sp_location.getSelectedItem().toString()) + ", 0, '" + txtDiscription.getText().toString() + 
    					  "', 0, 0, '0.0', '0.0', '" + userName + "', '" + imgUrl + "', 0)";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
    
    public void uploadWeddingCRP(){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO weddingcardringprotocol(WeddingCRPName, LocationId, Price, Discription, isFeatured, MemberId, Latitude, Longitude, User_Name, Image, Is_Removed)" + 
    					  " VALUES('" + txtTitle.getText().toString() + "', " + locationHashMap.get(sp_location.getSelectedItem().toString()) + ", 0, '" + txtDiscription.getText().toString() + 
    					  "', 0, 0, '0.0', '0.0', '" + userName + "', '" + imgUrl + "', 0)";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
    
    public void uploadWeddingGownToxido(){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO weddingcloth(WeddingClothName, LocationId, Price, Discription, isFeatured, MemberId, Latitude, Longitude, User_Name, Image, Is_Removed, Cloth_Type, Service_Type)" + 
    					  " VALUES('" + txtTitle.getText().toString() + "', " + locationHashMap.get(sp_location.getSelectedItem().toString()) + ", 0, '" + txtDiscription.getText().toString() + 
    					  "', 0, 0, '0.0', '0.0', '" + userName + "', '" + imgUrl + "', 0 , '" + itemType1 + "', '" + itemType2 + "')";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
    
    public void uploadBeautySaloon(){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO beautysaloons(beautysaloonsName, LocationId, Price, Discription, isFeatured, MemberId, Latitude, Longitude, User_Name, Image, Is_Removed, beautysaloonsType)" + 
    					  " VALUES('" + txtTitle.getText().toString() + "', " + locationHashMap.get(sp_location.getSelectedItem().toString()) + ", 0, '" + txtDiscription.getText().toString() + 
    					  "', 0, 0, '0.0', '0.0', '" + userName + "', '" + imgUrl + "', 0 , '" + itemType1 + "')";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
    
    public void uploadDJ(){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO dj(DjName, LocationId, Price, Discription, isFeatured, MemberId, Latitude, Longitude, User_Name, Image, Is_Removed)" + 
    					  " VALUES('" + txtTitle.getText().toString() + "', " + locationHashMap.get(sp_location.getSelectedItem().toString()) + ", 0, '" + txtDiscription.getText().toString() + 
    					  "', 0, 0, '0.0', '0.0', '" + userName + "', '" + imgUrl + "', 0)";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
    
    public void uploaHoneyMoonDestination(){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO hdnta(HDnTAName, LocationId, Price, Discription, isFeatured, MemberId, Latitude, Longitude, User_Name, Image, Is_Removed)" + 
    					  " VALUES('" + txtTitle.getText().toString() + "', " + locationHashMap.get(sp_location.getSelectedItem().toString()) + ", 0, '" + txtDiscription.getText().toString() + 
    					  "', 0, 0, '0.0', '0.0', '" + userName + "', '" + imgUrl + "', 0)";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
    
    public void uploaPhotoVideo(){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO photovideo(PhotoVideoName, LocationId, Price, Discription, isFeatured, MemberId, Latitude, Longitude, User_Name, Image, Is_Removed, PhotoVideoPics, WorkType)" + 
    					  " VALUES('" + txtTitle.getText().toString() + "', " + locationHashMap.get(sp_location.getSelectedItem().toString()) + ", 0, '" + txtDiscription.getText().toString() + 
    					  "', 0, 0, '0.0', '0.0', '" + userName + "', '" + imgUrl + "', 0, '', '')";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
    
    public void uploaCarListing(String isSale){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO carlisting(CarName, LocationId, Price, Discription, isFeatured, MemberId, Latitude, Longitude, User_Name, Image, Is_Removed, CarTypeId, Year, isCarSale, CarPictures)" + 
    					  " VALUES('" + txtTitle.getText().toString() + "', " + locationHashMap.get(sp_location.getSelectedItem().toString()) + ", 0, '" + txtDiscription.getText().toString() + 
    					  "', 0, 0, '0.0', '0.0', '" + userName + "', '" + imgUrl + "', 0, " + itemType1 + ", " +itemType2 + "," + isSale + ", '')";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
    
    public void uploadHouseListing(String isSale, String isBusiness){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO houselisting(House_Name, LocationId, HousePrice, HouseDiscription, isFeatured, MemberId, Latitude, Longitude, User_Name, Image, Is_Removed, HouseTypeId, NoRooms, LotSize, HouseImages, isSale, IsBusiness)" + 
    					  " VALUES('" + txtTitle.getText().toString() + "', " + locationHashMap.get(sp_location.getSelectedItem().toString()) + ", 0, '" + txtDiscription.getText().toString() + 
    					  "', 0, 0, '0.0', '0.0', '" + userName + "', '" + imgUrl + "', 0, " + itemType1 + ", " +itemType2 + ", '" + itemType3 + "', '', " +isSale + ", " + isBusiness + ")";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
    
    public void uploadGuestHouse(){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO guesthouse(GuestHouseName, LocationId, Price, GuestHouseDiscripton, isFeatured, Latitude, Longitude, User_Name, Image, Is_Removed, NoRooms)" + 
    					  " VALUES('" + txtTitle.getText().toString() + "', " + locationHashMap.get(sp_location.getSelectedItem().toString()) + ", 0, '" + txtDiscription.getText().toString() + 
    					  "', 0, '0.0', '0.0', '" + userName + "', '" + imgUrl + "', 0, " + itemType1 + ")";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
    
    public void uploadUsedItem(){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO useditems(UsedItemName, LocationId, Price, Discription, isFeatured, MemberId, Latitude, Longitude, User_Name, Image, Is_Removed, UsedItemTypeId, itemPics)" + 
    					  " VALUES('" + txtTitle.getText().toString() + "', " + locationHashMap.get(sp_location.getSelectedItem().toString()) + ", 0, '" + txtDiscription.getText().toString() + 
    					  "', 0, 0, '0.0', '0.0', '" + userName + "', '" + imgUrl + "', 0, " + itemType1 + ", '')";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
    
    public void uploadGarage(){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO garage(Item_Name, LocationId, Price, Discription, isFeatured, Latitude, Longitude, User_Name, Image, Is_Removed, Garage_Type, Job_Type)" + 
    					  " VALUES('" + txtTitle.getText().toString() + "', " + locationHashMap.get(sp_location.getSelectedItem().toString()) + ", 0, '" + txtDiscription.getText().toString() + 
    					  "', 0, '0.0', '0.0', '" + userName + "', '" + imgUrl + "', 0, '" + itemType1 + "', '" + itemType1 + "')";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
    
    public void uploadClinic(){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO clinic(Item_Name, LocationId, Price, Discription, isFeatured, Latitude, Longitude, User_Name, Image, Is_Removed, Clinic_Type, Job_Type)" + 
    					  " VALUES('" + txtTitle.getText().toString() + "', " + locationHashMap.get(sp_location.getSelectedItem().toString()) + ", 0, '" + txtDiscription.getText().toString() + 
    					  "', 0, '0.0', '0.0', '" + userName + "', '" + imgUrl + "', 0, '" + itemType1 + "', " + itemType2 + ")";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
    
    public void uploadPharmacy(){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO pharma(Item_Name, LocationId, Price, Discription, isFeatured, Latitude, Longitude, User_Name, Image, Is_Removed, Pharma_Type)" + 
    					  " VALUES('" + txtTitle.getText().toString() + "', " + locationHashMap.get(sp_location.getSelectedItem().toString()) + ", 0, '" + txtDiscription.getText().toString() + 
    					  "', 0, '0.0', '0.0', '" + userName + "', '" + imgUrl + "', 0, '" + itemType1 + "')";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
    
    public void uploadConstruction(){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO constructionssns(ConstructionTitle, LocationId, Price, Discription, isFeatured, Latitude, Longitude, User_Name, Image, Is_Removed, Profession, ConstructionMachineId, ConstructionMaterialId)" + 
    					  " VALUES('" + txtTitle.getText().toString() + "', " + locationHashMap.get(sp_location.getSelectedItem().toString()) + ", '0', '" + txtDiscription.getText().toString() + 
    					  "', 0, '0.0', '0.0', '" + userName + "', '" + imgUrl + "', 0, '" + itemType1 + "', " + itemType2 + ", '" + itemType3 + ")";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
    
    public void uploadHandyMan(){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO handy(Item_Name, LocationId, Discription, isFeatured, User_Name, Image, Is_Removed, Category)" + 
    					  " VALUES('" + txtTitle.getText().toString() + "', " + locationHashMap.get(sp_location.getSelectedItem().toString()) + ", '" + txtDiscription.getText().toString() + 
    					  "', 0, '0.0', '0.0', '" + userName + "', '" + imgUrl + "', 0, '" + itemType1 + "')";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }

    public void uploadRestaurant(){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO restaurant(Item_Name, LocationId, Discription, isFeatured, MemberId, Latitude, Longitude, User_Name, Image, Is_Removed, RestaurantTypeId)" + 
    					  " VALUES('" + txtTitle.getText().toString() + "', " + locationHashMap.get(sp_location.getSelectedItem().toString()) + ", '" + txtDiscription.getText().toString() + 
    					  "', 0, 0, '0.0', '0.0', '" + userName + "', '" + imgUrl + "', 0, " + itemType1 + ")";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
    
    public void uploadTravelAgent(){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO travel_agent(Item_Name, LocationId, Price, Discription, isFeatured, Latitude, Longitude, User_Name, Image, Is_Removed)" + 
    					  " VALUES('" + txtTitle.getText().toString() + "', " + locationHashMap.get(sp_location.getSelectedItem().toString()) + ", '0', '" + txtDiscription.getText().toString() + 
    					  "', 0, '0.0', '0.0', '" + userName + "', '" + imgUrl + "', 0)";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
    
    public void uploadEvent(){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO event(Item_Name, LocationId, Price, Discription, isFeatured, Latitude, Longitude, User_Name, Image, Is_Removed, Event_Type)" + 
    					  " VALUES('" + txtTitle.getText().toString() + "', " + locationHashMap.get(sp_location.getSelectedItem().toString()) + ", '0', '" + txtDiscription.getText().toString() + 
    					  "', 0, '0.0', '0.0', '" + userName + "', '" + imgUrl + "', 0, '" + itemType1 + "')";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
    
    public void uploadNightClub(){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO night_club(Item_Name, LocationId, Price, Discription, isFeatured, Latitude, Longitude, User_Name, Image, Is_Removed)" + 
    					  " VALUES('" + txtTitle.getText().toString() + "', " + locationHashMap.get(sp_location.getSelectedItem().toString()) + ", 0, '" + txtDiscription.getText().toString() + 
    					  "', 0, '0.0', '0.0', '" + userName + "', '" + imgUrl + "', 0)";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
    
    public void uploadResort(){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO resort(Item_Name, LocationId, Price, Discription, isFeatured, Latitude, Longitude, User_Name, Image, Is_Removed)" + 
    					  " VALUES('" + txtTitle.getText().toString() + "', " + locationHashMap.get(sp_location.getSelectedItem().toString()) + ", 0, '" + txtDiscription.getText().toString() + 
    					  "', 0, '0.0', '0.0', '" + userName + "', '" + imgUrl + "', 0)";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
    
    public void uploadShopCloth(){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO shop_cloth(Item_Name, LocationId, Price, Discription, isFeatured, Latitude, Longitude, User_Name, Image, Is_Removed, Catagory, Type, Color, Size)" + 
    					  " VALUES('" + txtTitle.getText().toString() + "', " + locationHashMap.get(sp_location.getSelectedItem().toString()) + ", '0', '" + txtDiscription.getText().toString() + 
    					  "', 0, '0.0', '0.0', '" + userName + "', '" + imgUrl + "', 0, '" + itemType1 + "', '" + itemType2 + "', '" + itemType3 + "', '" + itemType4 + "')";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
    
    public void uploadShopElectronics(){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO shop_electronics(Item_Name, LocationId, Price, Discription, isFeatured, Latitude, Longitude, User_Name, Image, Is_Removed, Brand_Name, Service_Type, Catagory)" + 
    					  " VALUES('" + txtTitle.getText().toString() + "', " + locationHashMap.get(sp_location.getSelectedItem().toString()) + ", 0, '" + txtDiscription.getText().toString() + 
    					  "', 0, '0.0', '0.0', '" + userName + "', '" + imgUrl + "', 0, '" + itemType1 + "', '" + itemType2 + "', '" + itemType3 + "')";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
    
    public void uploadShopFurniture(){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO shop_furniture(Item_Name, LocationId, Price, Discription, isFeatured, Latitude, Longitude, User_Name, Image, Is_Removed, Country, Item_Type)" + 
    					  " VALUES('" + txtTitle.getText().toString() + "', " + locationHashMap.get(sp_location.getSelectedItem().toString()) + ", 0, '" + txtDiscription.getText().toString() + 
    					  "', 0, '0.0', '0.0', '" + userName + "', '" + imgUrl + "', 0, '" + itemType1 + "', '" + itemType2 + "')";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
    
    public void uploadShopComputers(){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO shop_computer(Item_Name, LocationId, Price, Discription, isFeatured, Latitude, Longitude, User_Name, Image, Is_Removed, Brand_Name, Service_Type, Item_Type)" + 
    					  " VALUES('" + txtTitle.getText().toString() + "', " + locationHashMap.get(sp_location.getSelectedItem().toString()) + ", 0, '" + txtDiscription.getText().toString() + 
    					  "', 0, '0.0', '0.0', '" + userName + "', '" + imgUrl + "', 0, '" + itemType1 + "', '" + itemType2 + "', '" + itemType3 + "')";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
    
    public void uploadWeddingHall(){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO weddinghalls(WeddingHallName, LocationId, Price, Discription, isFeatured, MemberId, Latitude, Longitude, User_Name, Image, Is_Removed, ServiceType, dateAvailable, weddingHallsPics, Hall_Type, Break_Fast, Lunch, Dinner)" + 
    					  " VALUES('" + txtTitle.getText().toString() + "', " + locationHashMap.get(sp_location.getSelectedItem().toString()) + ", 0, '" + txtDiscription.getText().toString() + 
    					  "', 0, 0, '0.0', '0.0', '" + userName + "', '" + imgUrl + "', 0, '" + itemType1 + "', '', '', '" + itemType2 + "', '', '', '')";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
}
