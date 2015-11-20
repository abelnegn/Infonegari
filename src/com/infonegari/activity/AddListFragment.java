package com.infonegari.activity;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
import android.graphics.BitmapFactory;
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
	private Spinner sp_location, sp_type_one, sp_type_two;
	private TextView txtImageName;
	private EditText txtTitle, txtDiscription, item_text_one, item_text_two;
	private Button btnChoosePic, btnTakePhoto, btnAddList, btnBack;
	private static final int MENU_ITEM_BACK = 2000;
	private DialogHandler dlgHandler;  
	private DialogHandler appdialog = new DialogHandler();
	private String userName = "";
	private String category = "";
	SafeUIBlockingUtility safeUIBlockingUtility;
	long totalSize = 0;
	private String itemTitle = "";
	private String itemLocation = "";
	private String itemDiscription = "";
	private String itemImage = "";
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
	HashMap<String, Long> restaurantTypeHashMap = new HashMap<String, Long>();
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
	HashMap<String, String> furniturePlaceHashMap = new HashMap<String, String>();
	
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
        
       List<AddList> addList = Select.from(AddList.class).list();
       if(addList.size() > 0){
           dlgHandler = new DialogHandler();
           dlgHandler.Confirm(getActivity(), getString(R.string.btn_upload_now) + "?", getString(R.string.dlg_upload_message), 
    				getString(R.string.btn_later), getString(R.string.btn_ok), cancel(), okUpload());    	   
       }
	       
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

    private Runnable okUpload(){
		Runnable runnable = new Runnable()
	    {
	        @Override
	        public void run()
	        {
	        	uploadAddList();
	        }
	    };
	    return runnable;
    }
   
    private Runnable cancel(){
		Runnable runnable = new Runnable()
	    {
	        @Override
	        public void run()
	        {
	        }
	    };
	    return runnable;
    }
	   
    private void uploadAddList(){
    	List<AddList> addList = Select.from(AddList.class).list();
    	if(addList.size() > 0){
    		for(AddList adds : addList){
    			itemTitle = adds.getTitle();
    			itemDiscription = adds.getDiscription();
    			itemImage = adds.getImageUrl();
    			itemLocation = String.valueOf(adds.getLocationId());
    			userName = adds.getUserId();
        		itemType1 = adds.getItemType1();
        		itemType2 = adds.getItemType2();
        		itemType3 = adds.getItemType3();
        		itemType4 = adds.getItemType4();
        		
    			if(adds.getCategory().equals("0")){
    				uploadCatererPasteries();
    			}else if(adds.getCategory().equals("1")){
    				uploadDecorator();
    			}else if(adds.getCategory().equals("2")){
    				uploadWeddingCar();
    			}else if(adds.getCategory().equals("3")){
    				uploadBand();
    			}else if(adds.getCategory().equals("4")){
    				uploadWeddingCRP();
    			}else if(adds.getCategory().equals("5")){
    				uploadWeddingGownToxido();
    			}else if(adds.getCategory().equals("6")){
    				uploadWeddingHall();
    			}else if(adds.getCategory().equals("7")){
    				uploadBeautySaloon();
    			}else if(adds.getCategory().equals("8")){
    				uploadHoneyMoonDestination();
    			}else if(adds.getCategory().equals("9")){
    				uploadDJ();
    			}else if(adds.getCategory().equals("10")){
    				uploadPhotoVideo();
    			}else if(adds.getCategory().equals("11")){
    				uploadCarListing("1");
    			}else if(adds.getCategory().equals("12")){
    				uploadUsedItem();
    			}else if(adds.getCategory().equals("13")){
    				uploadHouseListing("1", "0");
    			}else if(adds.getCategory().equals("14")){
    				uploadHouseListing("1", "1");
    			}else if(adds.getCategory().equals("15")){
    				uploadCarListing("0");
    			}else if(adds.getCategory().equals("16")){
    				uploadHouseListing("0", "0");
    			}else if(adds.getCategory().equals("17")){
    				uploadGuestHouse();
    			}else if(adds.getCategory().equals("18")){
    				uploadHouseListing("0", "1");
    			}else if(adds.getCategory().equals("19")){
    				uploadGarage();
    			}else if(adds.getCategory().equals("20")){
    				uploadPharmacy();
    			}else if(adds.getCategory().equals("21")){
    				uploadClinic();
    			}else if(adds.getCategory().equals("22")){
    				uploadConstruction();
    			}else if(adds.getCategory().equals("23")){
    				uploadRestaurant();
    			}else if(adds.getCategory().equals("24")){
    				uploadTravelAgent();
    			}else if(adds.getCategory().equals("25")){
    				uploadEvent();
    			}else if(adds.getCategory().equals("26")){
    				uploadNightClub();
    			}else if(adds.getCategory().equals("27")){
    				uploadResort();
    			}else if(adds.getCategory().equals("28")){
    				uploadShopCloth();
    			}else if(adds.getCategory().equals("29")){
    				uploadShopElectronics();
    			}else if(adds.getCategory().equals("30")){
    				uploadShopFurniture();
    			}else if(adds.getCategory().equals("31")){
    				uploadShopComputers();
    			}
    			adds.delete();
    		}
    	}	   
    }
    
	private void initialize(LayoutInflater inflater, ViewGroup container){
		category = getArguments().getString("Add_Category");
		categoryId = getArguments().getInt("Category_Id");
		if(categoryId == 0 || categoryId == 1 || categoryId == 3 || categoryId == 4 || 
				categoryId == 8 || categoryId == 9 || categoryId == 10 || categoryId == 24 ||
				categoryId == 22 || categoryId == 26 || categoryId == 27){
			rootView = inflater.inflate(R.layout.fragment_add_list_one, container, false);
		}else if(categoryId== 2 || categoryId == 6 || categoryId==7 || categoryId == 12 || categoryId == 17 || 
				categoryId ==20 || categoryId == 21 || categoryId == 23 || categoryId == 25){
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
			else if(categoryId == 23)
				fetchRestaurantType();
			else if(categoryId == 25)
				fetchEventType();
		}else if(categoryId == 5 ||  categoryId ==19 || categoryId == 22 || categoryId ==29 || 
				categoryId== 30 || categoryId ==31){
			rootView = inflater.inflate(R.layout.fragment_add_list_three, container, false);
			sp_type_one = (Spinner) rootView.findViewById(R.id.type_one);
			sp_type_two = (Spinner) rootView.findViewById(R.id.type_two);
			if(categoryId == 5){
				fetchWeddingClothType();
				fetchWeddingService();
			}else if(categoryId == 19){
				fetchPharmacyType();
				fetchGarageServiceType();
			}else if(categoryId == 22){
				fetchMachine();
				fetchMaterial();
			}else if(categoryId == 29){
				fetchElectronicCategory();
				fetchService();
			}else if(categoryId == 30){
				fetchFurnitureType();
				fetchFurniturePlace();
			}else if(categoryId == 31){
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
	        	updateAddList();
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
	        	updateAddList();
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
		restaurantTypeHashMap.put("Select Restaurant Type", 0L);
		for (RestaurantType type : typeList) {
			listOfType.add(type.getRestaurantTypeName());
			restaurantTypeHashMap.put(type.getRestaurantTypeName(), type.getRestaurantTypeId());
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

		furniturePlaceHashMap.put("Ethiopian", "ethiopian");
		furniturePlaceHashMap.put("Imported", "imported");
		
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
    	newAddList.setCategory(String.valueOf(categoryId));
    	newAddList.setItemType1(itemType1);
    	newAddList.setItemType2(itemType2);
    	newAddList.setItemType3(itemType3);
    	newAddList.setItemType4(itemType4);
    	newAddList.setImageUrl(txtImageName.getText().toString());
    	newAddList.setLocationId(locationHashMap.get(sp_location.getSelectedItem().toString()));
    	newAddList.setUserId(userName);
    	
    	newAddList.save();
    	Toast.makeText(getActivity(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();
    	clearFields();
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
    	String[] imgs = itemImage.split("/");
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
                	Bitmap bMap= BitmapFactory.decodeFile(imgFile);
                	Bitmap out = Bitmap.createScaledBitmap(bMap, 250, 250, false);
                	File file = new File(imgFile);
                    
                    OutputStream fOut=null;
                    try {
                        fOut = new BufferedOutputStream(new FileOutputStream(file));
                        out.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
                        fOut.flush();
                        fOut.close();
                        bMap.recycle();
                        out.recycle();

                    } catch (Exception e) {}
                	uploadImage(file);                 	
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
	                  Toast.makeText(getActivity(), "Data Uploaded Successfully", Toast.LENGTH_SHORT).show();
	                  clearFields();
	                  safeUIBlockingUtility.safelyUnBlockUI();
	              }
	
	              @Override
	              public void failure(RetrofitError retrofitError) {
	                  Toast.makeText(getActivity(), "Failed to upload data", Toast.LENGTH_SHORT).show();
	                  clearFields();
	                  safeUIBlockingUtility.safelyUnBlockUI();
	              }
	          }
	  );
	}
    
    public void uploadDecorator(){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO decorators(DecoratorName, LocationId, Price, Discription, isFeatured, MemberId, Latitude, Longitude, User_Name, Image, Op1)" + 
    					  " VALUES('" + itemTitle + "', " + itemLocation + ", 0, '" + itemDiscription + 
    					  "', 0, 0, '0.0', '0.0', '" + userName + "', '" + imgUrl + "', '')";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
    
    public void uploadCatererPasteries(){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO caterersnpastries(CnPIdName, ServiceType, LocationId, Price, Discription, isFeatured, MemberId, Latitude, Longitude, User_Name, caterersnpastriesPic, Image)" + 
    					  " VALUES('" + itemTitle + "', '', " + itemLocation + ", 0, '" + itemDiscription + 
    					  "', 0, 0, '0.0', '0.0', '" + userName + "', '', '" + imgUrl + "')";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
    
    public void uploadWeddingCar(){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO weddingcar(weddingCarName, CarTypeId, LocationId, Price, Discription, isFeatured, MemberId, Latitude, Longitude, User_Name, Image)" + 
    					  " VALUES('" + itemTitle + "', '" + itemType1 + "', " + itemLocation + ", 0, '" + itemDiscription + 
    					  "', 0, 0, '0.0', '0.0', '" + userName + "', '" + imgUrl + "')";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
    
    public void uploadBand(){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO band(BandName, LocationId, Price, Discription, isFeatured, MemberId, Latitude, Longitude, User_Name, Image)" + 
    					  " VALUES('" + itemTitle + "', " + itemLocation + ", 0, '" + itemDiscription + 
    					  "', 0, 0, '0.0', '0.0', '" + userName + "', '" + imgUrl + "')";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
    
    public void uploadWeddingCRP(){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO weddingcardringprotocol(WeddingCRPName, LocationId, Price, Discription, isFeatured, MemberId, Latitude, Longitude, User_Name, Image)" + 
    					  " VALUES('" + itemTitle + "', " + itemLocation + ", 0, '" + itemDiscription + 
    					  "', 0, 0, '0.0', '0.0', '" + userName + "', '" + imgUrl + "')";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
    
    public void uploadWeddingGownToxido(){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO weddingcloth(WeddingClothName, LocationId, Price, Discription, isFeatured, MemberId, Latitude, Longitude, User_Name, Image, Cloth_Type, Service_Type)" + 
    					  " VALUES('" + itemTitle + "', " + itemLocation + ", 0, '" + itemDiscription + 
    					  "', 0, 0, '0.0', '0.0', '" + userName + "', '" + imgUrl + "', '" + itemType1 + "', '" + itemType2 + "')";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
    
    public void uploadBeautySaloon(){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO beautysaloons(beautysaloonsName, LocationId, Price, Discription, isFeatured, MemberId, Latitude, Longitude, User_Name, Image, beautysaloonsType)" + 
    					  " VALUES('" + itemTitle + "', " + itemLocation + ", 0, '" + itemDiscription + 
    					  "', 0, 0, '0.0', '0.0', '" + userName + "', '" + imgUrl + "', '" + itemType1 + "')";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
    
    public void uploadDJ(){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO dj(DjName, LocationId, Price, Discription, isFeatured, MemberId, Latitude, Longitude, User_Name, Image)" + 
    					  " VALUES('" + txtTitle.getText().toString() + "', " + itemLocation + ", 0, '" + itemDiscription + 
    					  "', 0, 0, '0.0', '0.0', '" + userName + "', '" + imgUrl + "')";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
    
    public void uploadHoneyMoonDestination(){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO hdnta(HDnTAName, LocationId, Price, Discription, isFeatured, MemberId, Latitude, Longitude, User_Name, Image)" + 
    					  " VALUES('" + itemTitle + "', " + itemLocation + ", 0, '" + itemDiscription + 
    					  "', 0, 0, '0.0', '0.0', '" + userName + "', '" + imgUrl + "')";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
    
    public void uploadPhotoVideo(){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO photovideo(PhotoVideoName, LocationId, Price, Discription, isFeatured, MemberId, Latitude, Longitude, User_Name, Image, PhotoVideoPics, WorkType)" + 
    					  " VALUES('" + itemTitle + "', " + itemLocation + ", 0, '" + itemDiscription + 
    					  "', 0, 0, '0.0', '0.0', '" + userName + "', '" + imgUrl + "', '', '')";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
    
    public void uploadCarListing(String isSale){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO carlisting(CarName, LocationId, Price, Discription, isFeatured, MemberId, Latitude, Longitude, User_Name, Image, CarTypeId, Year, isCarSale, CarPictures)" + 
    					  " VALUES('" + itemTitle + "', " + itemLocation + ", 0, '" + itemDiscription + 
    					  "', 0, 0, '0.0', '0.0', '" + userName + "', '" + imgUrl + "', " + itemType1 + ", " +itemType2 + "," + isSale + ", '')";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
    
    public void uploadHouseListing(String isSale, String isBusiness){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO houselisting(House_Name, LocationId, HousePrice, HouseDiscription, isFeatured, MemberId, Latitude, Longitude, User_Name, Image, HouseTypeId, NoRooms, LotSize, HouseImages, isSale, IsBusiness)" + 
    					  " VALUES('" + itemTitle + "', " + itemLocation + ", 0, '" + itemDiscription + 
    					  "', 0, 0, '0.0', '0.0', '" + userName + "', '" + imgUrl + "', " + itemType1 + ", " +itemType2 + ", '" + itemType3 + "', '', " +isSale + ", " + isBusiness + ")";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
    
    public void uploadGuestHouse(){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO guesthouse(GuestHouseName, LocationId, Price, GuestHouseDiscripton, isFeatured, Latitude, Longitude, User_Name, Image, NoRooms)" + 
    					  " VALUES('" + itemTitle + "', " + itemLocation + ", 0, '" + itemDiscription + 
    					  "', 0, '0.0', '0.0', '" + userName + "', '" + imgUrl + "', " + itemType1 + ")";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
    
    public void uploadUsedItem(){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO useditems(UsedItemName, LocationId, Price, Discription, isFeatured, MemberId, Latitude, Longitude, User_Name, Image, UsedItemTypeId, itemPics)" + 
    					  " VALUES('" + itemTitle + "', " + itemLocation + ", 0, '" + itemDiscription + 
    					  "', 0, 0, '0.0', '0.0', '" + userName + "', '" + imgUrl + "', " + itemType1 + ", '')";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
    
    public void uploadGarage(){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO garage(Item_Name, LocationId, Price, Discription, Latitude, Longitude, User_Name, Image, Garage_Type, Job_Type)" + 
    					  " VALUES('" + itemTitle + "', " + itemLocation + ", 0, '" + itemDiscription + 
    					  "', '0.0', '0.0', '" + userName + "', '" + imgUrl + "', '" + itemType1 + "', '" + itemType2 + "')";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
    
    public void uploadClinic(){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO clinic(Item_Name, LocationId, Price, Discription, Latitude, Longitude, User_Name, Image, Clinic_Type, Job_Type)" + 
    					  " VALUES('" + itemTitle + "', " + itemLocation + ", 0, '" + itemDiscription + 
    					  "', '0.0', '0.0', '" + userName + "', '" + imgUrl + "', '" + itemType1 + "', 0)";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
    
    public void uploadPharmacy(){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO pharma(Item_Name, LocationId, Price, Discription, Latitude, Longitude, User_Name, Image, Pharma_Type)" + 
    					  " VALUES('" + itemTitle + "', " + itemLocation + ", 0, '" + itemDiscription + 
    					  "', '0.0', '0.0', '" + userName + "', '" + imgUrl + "', '" + itemType1 + "')";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
    
    public void uploadConstruction(){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO constructionssns(ConstructionTitle, LocationId, Price, Discription, isFeatured, Latitude, Longitude, User_Name, Image, Profession, ConstructionMachineId, ConstructionMaterialId)" + 
    					  " VALUES('" + itemTitle + "', " + itemLocation + ", '0', '" + itemDiscription + 
    					  "', 0, '0.0', '0.0', '" + userName + "', '" + imgUrl + "', '" + itemType1 + "', " + itemType2 + ", '" + itemType3 + ")";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
    
    public void uploadHandyMan(){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO handy(Item_Name, LocationId, Discription, User_Name, Image, Category)" + 
    					  " VALUES('" + itemTitle + "', " + itemLocation + ", '" + itemDiscription + 
    					  "', '0.0', '0.0', '" + userName + "', '" + imgUrl + "', '" + itemType1 + "')";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }

    public void uploadRestaurant(){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO restaurant(Item_Name, LocationId, Discription, MemberId, Latitude, Longitude, User_Name, Image, RestaurantTypeId)" + 
    					  " VALUES('" + itemTitle + "', " + itemLocation + ", '" + itemDiscription + 
    					  "', 0, '0.0', '0.0', '" + userName + "', '" + imgUrl + "', " + itemType1 + ")";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
    
    public void uploadTravelAgent(){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO travel_agent(Item_Name, LocationId, Price, Discription, Latitude, Longitude, User_Name, Image)" + 
    					  " VALUES('" + itemTitle + "', " + itemLocation + ", '0', '" + itemDiscription + 
    					  "', '0.0', '0.0', '" + userName + "', '" + imgUrl + "')";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
    
    public void uploadEvent(){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO event(Item_Name, LocationId, Price, Discription, Latitude, Longitude, User_Name, Image, Event_Type)" + 
    					  " VALUES('" + itemTitle + "', " + itemLocation + ", '0', '" + itemDiscription + 
    					  "', '0.0', '0.0', '" + userName + "', '" + imgUrl + "', '" + itemType1 + "')";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
    
    public void uploadNightClub(){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO night_club(Item_Name, LocationId, Price, Discription, Latitude, Longitude, User_Name, Image)" + 
    					  " VALUES('" + itemTitle + "', " + itemLocation + ", 0, '" + itemDiscription + 
    					  "', '0.0', '0.0', '" + userName + "', '" + imgUrl + "')";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
    
    public void uploadResort(){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO resort(Item_Name, LocationId, Price, Discription, Latitude, Longitude, User_Name, Image)" + 
    					  " VALUES('" + itemTitle + "', " + itemLocation + ", 0, '" + itemDiscription + 
    					  "', '0.0', '0.0', '" + userName + "', '" + imgUrl + "')";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
    
    public void uploadShopCloth(){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO shop_cloth(Item_Name, LocationId, Price, Discription, isFeatured, Latitude, Longitude, User_Name, Image, Catagory, Type, Color, Size)" + 
    					  " VALUES('" + itemTitle + "', " + itemLocation + ", '0', '" + itemDiscription + 
    					  "', 0, '0.0', '0.0', '" + userName + "', '" + imgUrl + "', '" + itemType1 + "', '" + itemType2 + "', '" + itemType3 + "', '" + itemType4 + "')";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
    
    public void uploadShopElectronics(){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO shop_electronics(Item_Name, LocationId, Price, Discription, Latitude, Longitude, User_Name, Image, Brand_Name, Service_Type, Catagory)" + 
    					  " VALUES('" + itemTitle + "', " + itemLocation + ", 0, '" + itemDiscription + 
    					  "', '0.0', '0.0', '" + userName + "', '" + imgUrl + "', '" + itemType1 + "', '" + itemType2 + "', '" + itemType3 + "')";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
    
    public void uploadShopFurniture(){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO shop_furniture(Item_Name, LocationId, Price, Discription, Latitude, Longitude, User_Name, Image, Country, Item_Type)" + 
    					  " VALUES('" + itemTitle + "', " + itemLocation + ", 0, '" + itemDiscription + 
    					  "', '0.0', '0.0', '" + userName + "', '" + imgUrl + "', '" + itemType1 + "', '" + itemType2 + "')";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
    
    public void uploadShopComputers(){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO shop_computer(Item_Name, LocationId, Price, Discription, Latitude, Longitude, User_Name, Image, Brand_Name, Service_Type, Item_Type)" + 
    					  " VALUES('" + itemTitle + "', " + itemLocation + ", 0, '" + itemDiscription + 
    					  "', '0.0', '0.0', '" + userName + "', '" + imgUrl + "', '" + itemType1 + "', '" + itemType2 + "', '" + itemType3 + "')";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
    
    public void uploadWeddingHall(){
    	String insertStms = "";
    	String imgUrl = "img/" + userName + "/" + getImageName();
    	if (Network.isOnline(getActivity())) {
    		insertStms = "INSERT INTO weddinghalls(WeddingHallName, LocationId, Price, Discription, isFeatured, MemberId, Latitude, Longitude, User_Name, Image, ServiceType, dateAvailable, weddingHallsPics, Hall_Type, Break_Fast, Lunch, Dinner)" + 
    					  " VALUES('" + itemTitle + "', " + itemLocation + ", 0, '" + itemDiscription + 
    					  "', 0, 0, '0.0', '0.0', '" + userName + "', '" + imgUrl + "', '', '', '', '" + itemType1 + "', '', '', '')";
    		callService(insertStms); 		
    	}else{
    		saveAddList();
    	}
    }
    
    public Runnable ok(){
        return new Runnable() {
            public void run() {
                return;
            }
        };
    }
    
    private void updateAddList(){
    	itemTitle = txtTitle.getText().toString();
    	itemDiscription = txtDiscription.getText().toString();
    	itemImage = txtImageName.getText().toString();
    	itemLocation = String.valueOf(locationHashMap.get(sp_location.getSelectedItem().toString()));
    	
    	if(categoryId == 0){    		
    		if(txtTitle.getText().toString().equals("") || txtDiscription.getText().toString().equals("")
    				|| txtImageName.getText().toString().equals("")){
    		    boolean dlg = appdialog.Confirm(getActivity(), "Mandatory Validation", getString(R.string.msg_mandatory_validation),
    		            null, "Ok", null, ok());    			
    		}else{
    			uploadCatererPasteries();
    		}
    	}else if(categoryId == 1){
    		if(txtTitle.getText().toString().equals("") || txtDiscription.getText().toString().equals("")
    				|| txtImageName.getText().toString().equals("")){
    		    boolean dlg = appdialog.Confirm(getActivity(), "Mandatory Validation", getString(R.string.msg_mandatory_validation),
    		            null, "Ok", null, ok());     			
    		}else{
    			uploadDecorator();
    		}    		
    	}else if(categoryId == 2){
			itemType1 = String.valueOf(carTypeHashMap.get(sp_type_one.getSelectedItem().toString()));
    		if(txtTitle.getText().toString().equals("") || txtDiscription.getText().toString().equals("")
    				|| txtImageName.getText().toString().equals("") || itemType1.toString().equals("Select Car Type")){
    		    boolean dlg = appdialog.Confirm(getActivity(), "Mandatory Validation", getString(R.string.msg_mandatory_validation),
    		            null, "Ok", null, ok());   			
    		}else{
    			uploadWeddingCar();
    		}      		
    	}else if(categoryId == 3){
    		if(txtTitle.getText().toString().equals("") || txtDiscription.getText().toString().equals("")
    				|| txtImageName.getText().toString().equals("")){
    		    boolean dlg = appdialog.Confirm(getActivity(), "Mandatory Validation", getString(R.string.msg_mandatory_validation),
    		            null, "Ok", null, ok());      			
    		}else{
    			uploadBand();
    		}    		
    	}else if(categoryId == 4){
    		if(txtTitle.getText().toString().equals("") || txtDiscription.getText().toString().equals("")
    				|| txtImageName.getText().toString().equals("")){
    		    boolean dlg = appdialog.Confirm(getActivity(), "Mandatory Validation", getString(R.string.msg_mandatory_validation),
    		            null, "Ok", null, ok());      			
    		}else{
    			uploadWeddingCRP();
    		}
    	}else if(categoryId == 5){
			itemType1 = String.valueOf(weddingClothTypeHashMap.get(sp_type_one.getSelectedItem().toString()));
			itemType2 = String.valueOf(weddingServiceHashMap.get(sp_type_two.getSelectedItem().toString()));
    		if(txtTitle.getText().toString().equals("") || txtDiscription.getText().toString().equals("")
    				|| txtImageName.getText().toString().equals("") || itemType1.equals("Select Cloth Type")
    				|| itemType2.toString().equals("Select Service")){
    		    boolean dlg = appdialog.Confirm(getActivity(), "Mandatory Validation", getString(R.string.msg_mandatory_validation),
    		            null, "Ok", null, ok());     			
    		}else{
    			uploadWeddingGownToxido();
    		}
    	}else if(categoryId == 6){
			itemType1 = String.valueOf(hallTypeHashMap.get(sp_type_one.getSelectedItem().toString()));
       		if(txtTitle.getText().toString().equals("") || txtDiscription.getText().toString().equals("")
    				|| txtImageName.getText().toString().equals("") || itemType1.equals("Select Hall Type")){
    		    boolean dlg = appdialog.Confirm(getActivity(), "Mandatory Validation", getString(R.string.msg_mandatory_validation),
    		            null, "Ok", null, ok());     			
    		}else{
    			uploadWeddingHall();
    		}    		
    	}else if (categoryId == 7){
			itemType1 = String.valueOf(saloonTypeHashMap.get(sp_type_one.getSelectedItem().toString()));
       		if(txtTitle.getText().toString().equals("") || txtDiscription.getText().toString().equals("")
    				|| txtImageName.getText().toString().equals("") || itemType1.equals("Select Type")){
    		    boolean dlg = appdialog.Confirm(getActivity(), "Mandatory Validation", getString(R.string.msg_mandatory_validation),
    		            null, "Ok", null, ok());    			
    		}else{
    			uploadBeautySaloon();
    		}     		
    	}else if(categoryId == 8){
       		if(txtTitle.getText().toString().equals("") || txtDiscription.getText().toString().equals("")
    				|| txtImageName.getText().toString().equals("")){
    		    boolean dlg = appdialog.Confirm(getActivity(), "Mandatory Validation", getString(R.string.msg_mandatory_validation),
    		            null, "Ok", null, ok());     			
    		}else{
    			uploadHoneyMoonDestination();
    		}     		
    	}else if(categoryId == 9){
       		if(txtTitle.getText().toString().equals("") || txtDiscription.getText().toString().equals("")
    				|| txtImageName.getText().toString().equals("")){
    		    boolean dlg = appdialog.Confirm(getActivity(), "Mandatory Validation", getString(R.string.msg_mandatory_validation),
    		            null, "Ok", null, ok());    			
    		}else{
    			uploadDJ();
    		}    		
    	}else if(categoryId == 10){
       		if(txtTitle.getText().toString().equals("") || txtDiscription.getText().toString().equals("")
    				|| txtImageName.getText().toString().equals("")){
    		    boolean dlg = appdialog.Confirm(getActivity(), "Mandatory Validation", getString(R.string.msg_mandatory_validation),
    		            null, "Ok", null, ok());     			
    		}else{
    			uploadPhotoVideo();
    		}     		
    	}else if(categoryId == 11){
    		itemType1 = String.valueOf(carTypeHashMap.get(sp_type_one.getSelectedItem().toString()));
    		itemType2 = item_text_one.getText().toString();
       		if(txtTitle.getText().toString().equals("") || txtDiscription.getText().toString().equals("")
    				|| txtImageName.getText().toString().equals("") || itemType1.equals("") || itemType2.equals("")){
    		    boolean dlg = appdialog.Confirm(getActivity(), "Mandatory Validation", getString(R.string.msg_mandatory_validation),
    		            null, "Ok", null, ok());    			
    		}else{    			
    			uploadCarListing("1");
    		}     		
    	}else if(categoryId == 12){
    		itemType1 = String.valueOf(usedItemTypeHashMap.get(sp_type_one.getSelectedItem().toString()));
       		if(txtTitle.getText().toString().equals("") || txtDiscription.getText().toString().equals("")
    				|| txtImageName.getText().toString().equals("") || itemType1.equals("")){
    		    boolean dlg = appdialog.Confirm(getActivity(), "Mandatory Validation", getString(R.string.msg_mandatory_validation),
    		            null, "Ok", null, ok());    			
    		}else{    			
    			uploadUsedItem();
    		}      		
    	}else if(categoryId == 13){
    		itemType1 = String.valueOf(houseTypeHashMap.get(sp_type_one.getSelectedItem().toString()));
    		itemType2 = String.valueOf(noRoomsTypeHashMap.get(sp_type_two.getSelectedItem().toString()));
    		itemType3 = item_text_one.getText().toString();
       		if(txtTitle.getText().toString().equals("") || txtDiscription.getText().toString().equals("")
    				|| txtImageName.getText().toString().equals("") || itemType1.equals("")
    				|| itemType2.equals("") || itemType3.equals("")){
    		    boolean dlg = appdialog.Confirm(getActivity(), "Mandatory Validation", getString(R.string.msg_mandatory_validation),
    		            null, "Ok", null, ok());     			
    		}else{    			
    			uploadHouseListing("1", "0");
    		}      		
    	}else if(categoryId == 14){
    		itemType1 = String.valueOf(houseTypeHashMap.get(sp_type_one.getSelectedItem().toString()));
    		itemType2 = String.valueOf(noRoomsTypeHashMap.get(sp_type_two.getSelectedItem().toString()));
    		itemType3 = item_text_one.getText().toString();
       		if(txtTitle.getText().toString().equals("") || txtDiscription.getText().toString().equals("")
    				|| txtImageName.getText().toString().equals("") || itemType1.equals("")
    				|| itemType2.equals("") || itemType3.equals("")){
    		    boolean dlg = appdialog.Confirm(getActivity(), "Mandatory Validation", getString(R.string.msg_mandatory_validation),
    		            null, "Ok", null, ok());     			
    		}else{    			
    			uploadHouseListing("1", "1");
    		}     		
    	}else if(categoryId == 15){
    		itemType1 = String.valueOf(carTypeHashMap.get(sp_type_one.getSelectedItem().toString()));
    		itemType2 = item_text_one.getText().toString();
       		if(txtTitle.getText().toString().equals("") || txtDiscription.getText().toString().equals("")
    				|| txtImageName.getText().toString().equals("") || itemType1.equals("") || itemType2.equals("")){
    		    boolean dlg = appdialog.Confirm(getActivity(), "Mandatory Validation", getString(R.string.msg_mandatory_validation),
    		            null, "Ok", null, ok());      			
    		}else{    			
    			uploadCarListing("0");
    		}     		
    	}else if(categoryId == 16){
    		itemType1 = String.valueOf(houseTypeHashMap.get(sp_type_one.getSelectedItem().toString()));
    		itemType2 = String.valueOf(noRoomsTypeHashMap.get(sp_type_two.getSelectedItem().toString()));
    		itemType3 = item_text_one.getText().toString();
       		if(txtTitle.getText().toString().equals("") || txtDiscription.getText().toString().equals("")
    				|| txtImageName.getText().toString().equals("") || itemType1.equals("")
    				|| itemType2.equals("") || itemType3.equals("")){
    		    boolean dlg = appdialog.Confirm(getActivity(), "Mandatory Validation", getString(R.string.msg_mandatory_validation),
    		            null, "Ok", null, ok());    			
    		}else{    			
    			uploadHouseListing("0", "0");
    		}     		
    	}else if(categoryId == 17){
    		itemType1 = String.valueOf(houseTypeHashMap.get(sp_type_one.getSelectedItem().toString()));
       		if(txtTitle.getText().toString().equals("") || txtDiscription.getText().toString().equals("")
    				|| txtImageName.getText().toString().equals("") || itemType1.equals("")){
    		    boolean dlg = appdialog.Confirm(getActivity(), "Mandatory Validation", getString(R.string.msg_mandatory_validation),
    		            null, "Ok", null, ok());    			
    		}else{    			
    			uploadGuestHouse();
    		}       		
    	}else if(categoryId == 18){
    		itemType1 = String.valueOf(houseTypeHashMap.get(sp_type_one.getSelectedItem().toString()));
    		itemType2 = String.valueOf(noRoomsTypeHashMap.get(sp_type_two.getSelectedItem().toString()));
    		itemType3 = item_text_one.getText().toString();
       		if(txtTitle.getText().toString().equals("") || txtDiscription.getText().toString().equals("")
    				|| txtImageName.getText().toString().equals("") || itemType1.equals("")
    				|| itemType2.equals("") || itemType3.equals("")){
    		    boolean dlg = appdialog.Confirm(getActivity(), "Mandatory Validation", getString(R.string.msg_mandatory_validation),
    		            null, "Ok", null, ok());  			
    		}else{    			
    			uploadHouseListing("0", "1");
    		}     		
    	}else if(categoryId == 19){
    		itemType1 = String.valueOf(pharmacyTypeHashMap.get(sp_type_one.getSelectedItem().toString()));
    		itemType2 = String.valueOf(garageServiceTypeHashMap.get(sp_type_two.getSelectedItem().toString()));
       		if(txtTitle.getText().toString().equals("") || txtDiscription.getText().toString().equals("")
    				|| txtImageName.getText().toString().equals("") || itemType1.equals("") || itemType2.equals("")){
    		    boolean dlg = appdialog.Confirm(getActivity(), "Mandatory Validation", getString(R.string.msg_mandatory_validation),
    		            null, "Ok", null, ok());   			
    		}else{    			
    			uploadGarage();
    		}     		
    	}else if(categoryId == 20){
    		itemType1 = String.valueOf(pharmacyTypeHashMap.get(sp_type_one.getSelectedItem().toString()));
       		if(txtTitle.getText().toString().equals("") || txtDiscription.getText().toString().equals("")
    				|| txtImageName.getText().toString().equals("") || itemType1.equals("")){
    		    boolean dlg = appdialog.Confirm(getActivity(), "Mandatory Validation", getString(R.string.msg_mandatory_validation),
    		            null, "Ok", null, ok());  			
    		}else{    			
    			uploadPharmacy();
    		}     		
    	}else if(categoryId == 21){
    		itemType1 = String.valueOf(pharmacyTypeHashMap.get(sp_type_one.getSelectedItem().toString()));
       		if(txtTitle.getText().toString().equals("") || txtDiscription.getText().toString().equals("")
    				|| txtImageName.getText().toString().equals("") || itemType1.equals("")){
    		    boolean dlg = appdialog.Confirm(getActivity(), "Mandatory Validation", getString(R.string.msg_mandatory_validation),
    		            null, "Ok", null, ok());      			
    		}else{    			
    			uploadClinic();
    		}       		
    	}else if(categoryId == 22){
    		itemType1 = String.valueOf(conMachineHashMap.get(sp_type_one.getSelectedItem().toString()));
    		itemType2 = String.valueOf(conMaterialHashMap.get(sp_type_two.getSelectedItem().toString()));
       		if(txtTitle.getText().toString().equals("") || txtDiscription.getText().toString().equals("")
    				|| txtImageName.getText().toString().equals("") || itemType1.equals("") || itemType2.equals("")){
    		    boolean dlg = appdialog.Confirm(getActivity(), "Mandatory Validation", getString(R.string.msg_mandatory_validation),
    		            null, "Ok", null, ok());     			
    		}else{    			
    			uploadConstruction();
    		}      		
    	}else if(categoryId == 23){
    		itemType1 = String.valueOf(restaurantTypeHashMap.get(sp_type_one.getSelectedItem().toString()));
       		if(txtTitle.getText().toString().equals("") || txtDiscription.getText().toString().equals("")
    				|| txtImageName.getText().toString().equals("") || itemType1.equals("")){
    		    boolean dlg = appdialog.Confirm(getActivity(), "Mandatory Validation", getString(R.string.msg_mandatory_validation),
    		            null, "Ok", null, ok());     			
    		}else{    			
    			uploadRestaurant();
    		}      		
    	}else if(categoryId == 24){
       		if(txtTitle.getText().toString().equals("") || txtDiscription.getText().toString().equals("")
    				|| txtImageName.getText().toString().equals("")){
    		    boolean dlg = appdialog.Confirm(getActivity(), "Mandatory Validation", getString(R.string.msg_mandatory_validation),
    		            null, "Ok", null, ok());   			
    		}else{    			
    			uploadTravelAgent();
    		}     		
    	}else if(categoryId == 25){
    		itemType1 = String.valueOf(eventTypeHashMap.get(sp_type_one.getSelectedItem().toString()));
       		if(txtTitle.getText().toString().equals("") || txtDiscription.getText().toString().equals("")
    				|| txtImageName.getText().toString().equals("") || itemType1.equals("")){
    		    boolean dlg = appdialog.Confirm(getActivity(), "Mandatory Validation", getString(R.string.msg_mandatory_validation),
    		            null, "Ok", null, ok());     			
    		}else{    			
    			uploadEvent();
    		}     		
    	}else if(categoryId == 26){
       		if(txtTitle.getText().toString().equals("") || txtDiscription.getText().toString().equals("")
    				|| txtImageName.getText().toString().equals("")){
    		    boolean dlg = appdialog.Confirm(getActivity(), "Mandatory Validation", getString(R.string.msg_mandatory_validation),
    		            null, "Ok", null, ok());     			
    		}else{    			
    			uploadNightClub();
    		}     		
    	}else if (categoryId == 27){
       		if(txtTitle.getText().toString().equals("") || txtDiscription.getText().toString().equals("")
    				|| txtImageName.getText().toString().equals("")){
    		    boolean dlg = appdialog.Confirm(getActivity(), "Mandatory Validation", getString(R.string.msg_mandatory_validation),
    		            null, "Ok", null, ok());    			
    		}else{    			
    			uploadResort();
    		}    		
    	}else if(categoryId == 28){
    		itemType1 = String.valueOf(shopCatHashMap.get(sp_type_one.getSelectedItem().toString()));
    		itemType2 = String.valueOf(serviceHashMap.get(sp_type_two.getSelectedItem().toString()));
    		itemType3= item_text_one.getText().toString();
    		itemType4= item_text_two.getText().toString();
       		if(txtTitle.getText().toString().equals("") || txtDiscription.getText().toString().equals("")
    				|| txtImageName.getText().toString().equals("") || itemType1.equals("") 
    				|| itemType2.equals("") || itemType3.equals("") || itemType4.equals("")){
    		    boolean dlg = appdialog.Confirm(getActivity(), "Mandatory Validation", getString(R.string.msg_mandatory_validation),
    		            null, "Ok", null, ok());    			
    		}else{    			
    			uploadShopCloth();
    		}    		
    	}else if(categoryId == 29){
    		itemType1 = String.valueOf(electronicCategoryHashMap.get(sp_type_one.getSelectedItem().toString()));
    		itemType2 = String.valueOf(serviceHashMap.get(sp_type_two.getSelectedItem().toString()));
       		if(txtTitle.getText().toString().equals("") || txtDiscription.getText().toString().equals("")
    				|| txtImageName.getText().toString().equals("") || itemType1.equals("") || itemType2.equals("")){
    		    boolean dlg = appdialog.Confirm(getActivity(), "Mandatory Validation", getString(R.string.msg_mandatory_validation),
    		            null, "Ok", null, ok());     			
    		}else{    			
    			uploadShopElectronics();
    		}     		
    	}else if(categoryId == 30){
    		itemType1 = String.valueOf(furnitureTypeHashMap.get(sp_type_one.getSelectedItem().toString()));
    		itemType2 = String.valueOf(furniturePlaceHashMap.get(sp_type_two.getSelectedItem().toString()));
       		if(txtTitle.getText().toString().equals("") || txtDiscription.getText().toString().equals("")
    				|| txtImageName.getText().toString().equals("") || itemType1.equals("") || itemType2.equals("")){
    		    boolean dlg = appdialog.Confirm(getActivity(), "Mandatory Validation", getString(R.string.msg_mandatory_validation),
    		            null, "Ok", null, ok());     			
    		}else{    			
    			uploadShopFurniture();
    		}    		
    	}else{
    		itemType1 = String.valueOf(accessoryTypeHashMap.get(sp_type_one.getSelectedItem().toString()));
    		itemType2 = String.valueOf(serviceHashMap.get(sp_type_two.getSelectedItem().toString()));
       		if(txtTitle.getText().toString().equals("") || txtDiscription.getText().toString().equals("")
    				|| txtImageName.getText().toString().equals("") || itemType1.equals("") || itemType2.equals("")){
    		    boolean dlg = appdialog.Confirm(getActivity(), "Mandatory Validation", getString(R.string.msg_mandatory_validation),
    		            null, "Ok", null, ok());     			
    		}else{    			
    			uploadShopComputers();
    		}      		
    	}
    }
}
