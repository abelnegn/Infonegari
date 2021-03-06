package com.infonegari.fragment;
import java.io.File;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import com.infonegari.activity.R;
import com.infonegari.objects.db.Ads;
import com.infonegari.objects.db.Auction;
import com.infonegari.objects.db.AuctionCategory;
import com.infonegari.objects.db.Band;
import com.infonegari.objects.db.Bank;
import com.infonegari.objects.db.BeautySaloon;
import com.infonegari.objects.db.CarListing;
import com.infonegari.objects.db.CarType;
import com.infonegari.objects.db.CaterersPasteries;
import com.infonegari.objects.db.Cinema;
import com.infonegari.objects.db.CinemaPlace;
import com.infonegari.objects.db.City;
import com.infonegari.objects.db.Clinic;
import com.infonegari.objects.db.ClinicType;
import com.infonegari.objects.db.Construction;
import com.infonegari.objects.db.ConstructionMachine;
import com.infonegari.objects.db.ConstructionMaterial;
import com.infonegari.objects.db.DJ;
import com.infonegari.objects.db.Decorators;
import com.infonegari.objects.db.EducationCategory;
import com.infonegari.objects.db.Event;
import com.infonegari.objects.db.Guarage;
import com.infonegari.objects.db.GuestHouse;
import com.infonegari.objects.db.HallType;
import com.infonegari.objects.db.HandyCategory;
import com.infonegari.objects.db.HandyMan;
import com.infonegari.objects.db.Hdnta;
import com.infonegari.objects.db.HouseListing;
import com.infonegari.objects.db.HouseType;
import com.infonegari.objects.db.JobCategory;
import com.infonegari.objects.db.Jobs;
import com.infonegari.objects.db.Location;
import com.infonegari.objects.db.MovieType;
import com.infonegari.objects.db.NightClub;
import com.infonegari.objects.db.Pharmacy;
import com.infonegari.objects.db.PhotoVideo;
import com.infonegari.objects.db.Resort;
import com.infonegari.objects.db.Restaurant;
import com.infonegari.objects.db.RestaurantType;
import com.infonegari.objects.db.ShopCloth;
import com.infonegari.objects.db.ShopComputer;
import com.infonegari.objects.db.ShopElectronic;
import com.infonegari.objects.db.ShopFurniture;
import com.infonegari.objects.db.Taxi;
import com.infonegari.objects.db.Tender;
import com.infonegari.objects.db.TenderCategory;
import com.infonegari.objects.db.TravelAgent;
import com.infonegari.objects.db.UsedItem;
import com.infonegari.objects.db.UsedItemType;
import com.infonegari.objects.db.UserSite;
import com.infonegari.objects.db.WeddingCar;
import com.infonegari.objects.db.WeddingCardRingProtocol;
import com.infonegari.objects.db.WeddingCloth;
import com.infonegari.objects.db.WeddingHall;
import com.infonegari.service.API;
import com.infonegari.util.DownloadAdsImage;
import com.infonegari.util.Network;
import com.infonegari.util.OfflineDataHelper;
import com.infonegari.util.SafeUIBlockingUtility;
import com.orm.query.Condition;
import com.orm.query.Select;

import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class DownloadDataFragment extends Fragment implements OfflineDataHelper.OfflineDataSaveListener{
	View rootView;
	FragmentActivity activity;
	SafeUIBlockingUtility safeUIBlockingUtility;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }
    
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, null);

        activity = (FragmentActivity) getActivity();       
		safeUIBlockingUtility = new SafeUIBlockingUtility(getActivity(), 
				getString(R.string.msg_updating), getString(R.string.msg_please_wait) + "...");
   		safeUIBlockingUtility.safelyBlockUI();
        if (Network.isOnline(this.getActivity())) {            
            saveAdsData();
            saveAuctionData();
            saveAuctionCategoryData();
            saveBandData();
            saveBankData();
            saveBeautySaloonData();
            saveCarListingData();
            saveCarTypeData();
            saveCaterersPasteriesData();
            saveCinemaData();
            saveCinemaPlaceData();
            saveCityData();
            saveClinicTypeData();
            saveClinicData();
            saveConstructionData();
            saveConstructionMachineData();
            saveConstructionMaterialData();
            saveDecoratorsData();
            saveDJData();
            saveEducationCategoryData();
            saveEventData();
            saveGuarageData();
            saveGuestHouseData();
            saveHallTypeData();
            saveHandyManData();
            saveHandyCategoryData();
            saveHdntaData();
            saveHouseListingData();
            saveHouseTypeData();
            saveJobCategoryData();
            saveJobData();
            saveLocationData();
            saveMovieTypeData();
            saveNightClubData();
            savePharmacyData();
            savePhotoVideoData();
            saveResortData();
            saveRestaurantTypeData();
            saveRestaurantData();
            saveShopClothData();
            saveShopComputerData();
            saveShopElectronicData();
            saveShopFurnitureData();
            saveTaxiData();
            saveTenderData();
            saveTenderCategoryData();
            saveTravelAgentData();
            saveUsedItemData();
            saveUserSiteData();
            updateUserSiteData();
            saveUsedItemTypeData();
            saveWeddingCarData();
            saveWeddingCRPData();
            saveWeddingClothData();
            saveWeddingHallData();
        }else{
        	safeUIBlockingUtility.safelyUnBlockUI();
            Toast.makeText(getActivity(), "There is no internet connection", Toast.LENGTH_SHORT).show();
    		FragmentManager fragmentManager = getFragmentManager();
    		HomeFragment fragment = new HomeFragment();
    		fragmentManager.beginTransaction()
    				.replace(R.id.frame_container, fragment).commit();	
        }
        return rootView;
    }
	
    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {   	
        super.onPause();
    }
    
    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void saveAdsData(){
    	if (Network.isOnline(this.getActivity())) {        	
            API.adsService.getAds(new Callback<List<Ads>>() {
                @Override
                public void success(List<Ads> ads, Response response) {
                	List<Ads> adsList= Select.from(Ads.class).list();
                	for (Ads adImage : adsList) {    
                		deleteAdsImage(adImage.getImage_mob());
                		adImage.delete();
                	}               	
                	
                	for (Ads ad : ads) {      
                		if(!ad.getImage_mob().equals("uploads/ads/add.jpg")){
                            OfflineDataHelper helper = new OfflineDataHelper();
                            helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                            helper.saveAdsData(ad);	 
                            DownloadAdsImage downloadAds = new DownloadAdsImage(getActivity(), ad.getImage_mob());               			
                		}
                	}
                }

                @Override
                public void failure(RetrofitError error) {
                	
                }

            }); 		
    	}
    }
    
    private void deleteAdsImage(String imageName){
   		String[] imgName = imageName.split("/");
		String imagePath = getActivity().getFilesDir() + "/" + imgName[2];
		File imageFile = new File(imagePath);
  		if (imageFile.exists()) {
  			imageFile.delete();
  		}	
    }
    
    private void saveAuctionData(){
        if (Network.isOnline(this.getActivity())) {	
        	long auctionId = 0;
        	List<Auction> auctionList= Auction.findWithQuery(Auction.class, 
        			"SELECT * FROM  Auction WHERE auctionId = " +
        			"(SELECT max(auctionId) FROM  Auction)");
        	if(auctionList.size() > 0)
        		auctionId = auctionList.get(0).getAuction_id();
        	
            API.auctionService.getAuctions(auctionId,
                new Callback<List<Auction>>() {
                    @Override
                    public void success(List<Auction> acutions, Response response) {
                        for (Auction auction : acutions) {
                            if (auction != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveAuctionData(auction);
                            }
                        }
                        
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    	
                    }

                });
        }
    }
    
    private void saveAuctionCategoryData(){
        if (Network.isOnline(this.getActivity())) {   		
        	long acId = 0;
        	List<AuctionCategory> acList= AuctionCategory.findWithQuery(AuctionCategory.class, 
        			"SELECT * FROM  Auction_Category WHERE acId = " +
        			"(SELECT max(acId) FROM  Auction_Category)");
        	if(acList.size() > 0)
        		acId = acList.get(0).getAc_id();
        	
            API.auctionCategoryService.getAuctionCategories(acId,
                new Callback<List<AuctionCategory>>() {
                    @Override
                    public void success(List<AuctionCategory> acutionCategories, Response response) {
                        for (AuctionCategory auctionCategory : acutionCategories) {
                            if (auctionCategory != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveAuctionGategoryData(auctionCategory);
                            }
                        }
                        
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    	
                    }

                });
        }
    }

    private void saveBandData(){
        if (Network.isOnline(this.getActivity())) {  		
        	long bandId = 0;
        	List<Band> bandList= Band.findWithQuery(Band.class, 
        			"SELECT * FROM  Band WHERE Band_Id = " +
        			"(SELECT max(Band_Id) FROM  Band)");
        	if(bandList.size() > 0)
        		bandId = bandList.get(0).getBandId();
        	
            API.bandService.getBands(bandId,
                new Callback<List<Band>>() {
                    @Override
                    public void success(List<Band> bands, Response response) {
                        for (Band band : bands) {
                            if (band != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveBandData(band);
                            }
                        }
                        
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    	
                    }

                });
        }
    }

    private void saveBankData(){
        if (Network.isOnline(this.getActivity())) {  		
        	long bankId = 0;
        	List<Bank> bankList= Bank.findWithQuery(Bank.class, 
        			"SELECT * FROM  Bank WHERE bank_Id = " +
        			"(SELECT max(bank_Id) FROM  Bank)");
        	if(bankList.size() > 0)
        		bankId = bankList.get(0).getBankId();
        	
            API.bankService.getBanKs(bankId,
                new Callback<List<Bank>>() {
                    @Override
                    public void success(List<Bank> banks, Response response) {
                        for (Bank bank : banks) {
                            if (bank != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveBankData(bank);
                            }
                        }
                        
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    	
                    }

                });
        }
    }

    private void saveBeautySaloonData(){
        if (Network.isOnline(this.getActivity())) {
    		
        	long bsId = 0;
        	List<BeautySaloon> bsList= BeautySaloon.findWithQuery(BeautySaloon.class, 
        			"SELECT * FROM  Beauty_Saloon WHERE beautysaloons_Id = " +
        			"(SELECT max(beautysaloons_Id) FROM  Beauty_Saloon)");
        	if(bsList.size() > 0)
        		bsId = bsList.get(0).getBeautysaloonsId();
        	
            API.beautySaloonService.getBeautySaloons(bsId,
                new Callback<List<BeautySaloon>>() {
                    @Override
                    public void success(List<BeautySaloon> beautySaloons, Response response) {
                        for (BeautySaloon beautySaloon : beautySaloons) {
                            if (beautySaloon != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveBeautySaloonData(beautySaloon);
                            }
                        }
                        
                    }

                    @Override
                    public void failure(RetrofitError error) { 
                    	
                    }

                });
        }
    }

    private void saveCarListingData(){
        if (Network.isOnline(this.getActivity())) {
    		
        	long clId = 0;
        	List<CarListing> clList= CarListing.findWithQuery(CarListing.class, 
        			"SELECT * FROM  Car_Listing WHERE Car_Listing_Id = " +
        			"(SELECT max(Car_Listing_Id) FROM  Car_Listing)");
        	if(clList.size() > 0)
        		clId = clList.get(0).getCarListingId();
        	
            API.carListingService.getCarListings(clId,
                new Callback<List<CarListing>>() {
                    @Override
                    public void success(List<CarListing> carListings, Response response) {
                        for (CarListing carListing : carListings) {
                            if (carListing != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveCarListingData(carListing);
                            }
                        }
                        
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    	
                    }

                });
        }
    }
    
    private void saveCarTypeData(){
        if (Network.isOnline(this.getActivity())) {
    		
        	long ctId = 0;
        	List<CarType> ctList= CarType.findWithQuery(CarType.class, 
        			"SELECT * FROM  Car_Type WHERE Car_Type_Id = " +
        			"(SELECT max(Car_Type_Id) FROM  Car_Type)");
        	if(ctList.size() > 0)
        		ctId = ctList.get(0).getCarTypeId();
        	
            API.carTypeService.getCarTypes(ctId,
                new Callback<List<CarType>>() {
                    @Override
                    public void success(List<CarType> carTypes, Response response) {
                        for (CarType carType : carTypes) {
                            if (carType != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveCarTypeData(carType);
                            }
                        }
                        
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    	
                    }

                });
        }
    }
    
    private void saveCaterersPasteriesData(){
        if (Network.isOnline(this.getActivity())) {
    		
        	long cpId = 0;
        	List<CaterersPasteries> cpList= CaterersPasteries.findWithQuery(CaterersPasteries.class, 
        			"SELECT * FROM  Caterers_Pasteries WHERE Cn_P_Id = " +
        			"(SELECT max(Cn_P_Id) FROM  Caterers_Pasteries)");
        	if(cpList.size() > 0)
        		cpId = cpList.get(0).getCnPId();
        	
            API.caterersPasteriesService.getCaterersPasteries(cpId,
                new Callback<List<CaterersPasteries>>() {
                    @Override
                    public void success(List<CaterersPasteries> caterersPasteries, Response response) {
                        for (CaterersPasteries caterersPastery : caterersPasteries) {
                            if (caterersPastery != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveCatererPasteriesData(caterersPastery);
                            }
                        }
                        
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    	
                    }

                });
        }
    }
    
    private void saveCinemaData(){
        if (Network.isOnline(this.getActivity())) {
    		
        	long cinemaId = 0;
        	List<Cinema> cinemaList= Cinema.findWithQuery(Cinema.class, 
        			"SELECT * FROM  Cinema WHERE Cinema_Id = " +
        			"(SELECT max(Cinema_Id) FROM  Cinema)");
        	if(cinemaList.size() > 0)
        		cinemaId = cinemaList.get(0).getCinemaId();
        	
            API.cinemaService.getCinemas(cinemaId, new Callback<List<Cinema>>() {
                    @Override
                    public void success(List<Cinema> cinemas, Response response) {
                        for (Cinema cinema : cinemas) {
                            if (cinema != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveCinemaData(cinema);
                            }
                        }
                        
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    	
                    }

                });
        }
    }
    
    private void saveCinemaPlaceData(){
        if (Network.isOnline(this.getActivity())) {
    		
        	long cpId = 0;
        	List<CinemaPlace> cpList= CinemaPlace.findWithQuery(CinemaPlace.class, 
        			"SELECT * FROM  Cinema_Place WHERE cpId = " +
        			"(SELECT max(cpId) FROM  Cinema_Place)");
        	if(cpList.size() > 0)
        		cpId = cpList.get(0).getCp_id();
        	
            API.cinemaPlaceService.getCinemaPlace(cpId,
                new Callback<List<CinemaPlace>>() {
                    @Override
                    public void success(List<CinemaPlace> cinemaPlaces, Response response) {
                        for (CinemaPlace cinemaPlace : cinemaPlaces) {
                            if (cinemaPlace != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveCinemaPlaceData(cinemaPlace);
                            }
                        }
                        
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    	
                    }

                });
        }
    }
    
    private void saveCityData(){
        if (Network.isOnline(this.getActivity())) {
    		
        	long cityId = 0;
        	List<City> cityList= City.findWithQuery(City.class, 
        			"SELECT * FROM  City WHERE City_Id = " +
        			"(SELECT max(City_Id) FROM  City)");
        	if(cityList.size() > 0)
        		cityId = cityList.get(0).getCityId();
        	
            API.cityService.getCities(cityId,
                new Callback<List<City>>() {
                    @Override
                    public void success(List<City> cities, Response response) {
                        for (City city : cities) {
                            if (city != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveCityData(city);
                            }
                        }
                        
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    	
                    }

                });
        }
    }
    
    private void saveClinicTypeData(){
        if (Network.isOnline(this.getActivity())) {
    		
        	long typeId = 0;
        	List<ClinicType> typeList= ClinicType.findWithQuery(ClinicType.class, 
        			"SELECT * FROM  Clinic_Type WHERE Type_Id = " +
        			"(SELECT max(Type_Id) FROM  Clinic_Type)");
        	if(typeList.size() > 0)
        		typeId = typeList.get(0).getTypeId();
        	
            API.clinicTypeService.getClinicTypes(typeId,
                new Callback<List<ClinicType>>() {
                    @Override
                    public void success(List<ClinicType> types, Response response) {
                        for (ClinicType type : types) {
                            if (type != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveClinicTypeData(type);
                            }
                        }
                        
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    	
                    }

                });
        }
    }
    
    private void saveClinicData(){
        if (Network.isOnline(this.getActivity())) {
    		
        	long clinicId = 0;
        	List<Clinic> clinicList= Clinic.findWithQuery(Clinic.class, 
        			"SELECT * FROM  Clinic WHERE clinic_Id = " +
        			"(SELECT max(clinic_Id) FROM  Clinic)");
        	if(clinicList.size() > 0)
        		clinicId = clinicList.get(0).getClinicId();
        	
            API.clinicService.getClinics(clinicId,
                new Callback<List<Clinic>>() {
                    @Override
                    public void success(List<Clinic> clinics, Response response) {
                        for (Clinic clinic : clinics) {
                            if (clinic != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveClinicData(clinic);
                            }
                        }
                        
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    	
                    }

                });
        }
    }
    
    private void saveConstructionData(){
        if (Network.isOnline(this.getActivity())) {
    		
        	long conId = 0;
        	List<Construction> conList= Construction.findWithQuery(Construction.class, 
        			"SELECT * FROM  Construction WHERE Construction_Sn_S_Id = " +
        			"(SELECT max(Construction_Sn_S_Id) FROM  Construction)");
        	if(conList.size() > 0)
        		conId = conList.get(0).getConstructionSnSId();
        	
            API.constructionService.getConstructions(conId,
                new Callback<List<Construction>>() {
                    @Override
                    public void success(List<Construction> constructions, Response response) {
                        for (Construction construction : constructions) {
                            if (construction != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveConstructionData(construction);
                            }
                        }
                        
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    	
                    }

                });
        }
    }
    
    private void saveConstructionMachineData(){
        if (Network.isOnline(this.getActivity())) {
    		
        	long cmId = 0;
        	List<ConstructionMachine> cmList= ConstructionMachine.findWithQuery(ConstructionMachine.class, 
        			"SELECT * FROM  Construction_Machine WHERE cm_Id = " +
        			"(SELECT max(cm_Id) FROM  Construction_Machine)");
        	if(cmList.size() > 0)
        		cmId = cmList.get(0).getCmId();
        	
            API.constructionMachineService.getConstructionMachines(cmId,
                new Callback<List<ConstructionMachine>>() {
                    @Override
                    public void success(List<ConstructionMachine> constructionMachines, Response response) {
                        for (ConstructionMachine constructionMachine : constructionMachines) {
                            if (constructionMachine != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveConMachineData(constructionMachine);
                            }
                        }
                        
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    	
                    }

                });
        }
    }
    
    private void saveConstructionMaterialData(){
        if (Network.isOnline(this.getActivity())) {
    		
        	long cmId = 0;
        	List<ConstructionMaterial> cmList= ConstructionMaterial.findWithQuery(ConstructionMaterial.class, 
        			"SELECT * FROM  Construction_Material WHERE cmId = " +
        			"(SELECT max(cmId) FROM  Construction_Material)");
        	if(cmList.size() > 0)
        		cmId = cmList.get(0).getCm_id();
        	
            API.constructionMaterialService.getConstructionMaterials(cmId,
                new Callback<List<ConstructionMaterial>>() {
                    @Override
                    public void success(List<ConstructionMaterial> constructionMaterials, Response response) {
                        for (ConstructionMaterial constructionMaterial : constructionMaterials) {
                            if (constructionMaterial != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveConMaterialData(constructionMaterial);
                            }
                        }
                        
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    	
                    }

                });
        }
    }
    
    private void saveDecoratorsData(){
        if (Network.isOnline(this.getActivity())) {
    		
        	long decoreId = 0;
        	List<Decorators> decoreList= Decorators.findWithQuery(Decorators.class, 
        			"SELECT * FROM  Decorators WHERE Decorator_Id = " +
        			"(SELECT max(Decorator_Id) FROM  Decorators)");
        	if(decoreList.size() > 0)
        		decoreId = decoreList.get(0).getDecoratorId();
        	
            API.decoratorsService.getDecorators(decoreId,
                new Callback<List<Decorators>>() {
                    @Override
                    public void success(List<Decorators> decorators, Response response) {
                        for (Decorators decorator : decorators) {
                            if (decorator != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveDecoratorData(decorator);
                            }
                        }
                        
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    	
                    }

                });
        }
    }
    
    private void saveDJData(){
        if (Network.isOnline(this.getActivity())) {
    		
        	long djId = 0;
        	List<DJ> djList= DJ.findWithQuery(DJ.class, 
        			"SELECT * FROM  DJ WHERE Dj_Id = " +
        			"(SELECT max(Dj_Id) FROM  DJ)");
        	if(djList.size() > 0)
        		djId = djList.get(0).getDjId();
        	
            API.dJService.getDJ(djId,
                new Callback<List<DJ>>() {
                    @Override
                    public void success(List<DJ> djs, Response response) {
                        for (DJ dj : djs) {
                            if (dj != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveDJData(dj);
                            }
                        }
                        
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    	
                    }

                });
        }
    }
    
    private void saveEducationCategoryData(){
        if (Network.isOnline(this.getActivity())) {
    		
        	long ecId = 0;
        	List<EducationCategory> ecList= EducationCategory.findWithQuery(EducationCategory.class, 
        			"SELECT * FROM  Education_Category WHERE ec_Id = " +
        			"(SELECT max(ec_Id) FROM  Education_Category)");
        	if(ecList.size() > 0)
        		ecId = ecList.get(0).getEcId();
        	
            API.educationCategoryService.getEducationCategories(ecId,
                new Callback<List<EducationCategory>>() {
                    @Override
                    public void success(List<EducationCategory> educationCategories, Response response) {
                        for (EducationCategory educationCategory : educationCategories) {
                            if (educationCategory != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveEducationCatData(educationCategory);
                            }
                        }
                        
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    	
                    }

                });
        }
    }
    
    private void saveEventData(){
        if (Network.isOnline(this.getActivity())) {
    		
        	long eventId = 0;
        	List<Event> eventList= Event.findWithQuery(Event.class, 
        			"SELECT * FROM  Event WHERE event_Id = " +
        			"(SELECT max(event_Id) FROM  Event)");
        	if(eventList.size() > 0)
        		eventId = eventList.get(0).getEventId();
        	
            API.eventService.getEvents(eventId,
                new Callback<List<Event>>() {
                    @Override
                    public void success(List<Event> events, Response response) {
                        for (Event event : events) {
                            if (event != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveEventData(event);
                            }
                        }
                        
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    	
                    }

                });
        }
    }
    
    private void saveGuarageData(){
        if (Network.isOnline(this.getActivity())) {
    		
        	long garageId = 0;
        	List<Guarage> garageList= Guarage.findWithQuery(Guarage.class, 
        			"SELECT * FROM  Guarage WHERE guarage_Id = " +
        			"(SELECT max(guarage_Id) FROM  Guarage)");
        	if(garageList.size() > 0)
        		garageId = garageList.get(0).getGuarageId();
        	
            API.guarageService.getGuarages(garageId,
                new Callback<List<Guarage>>() {
                    @Override
                    public void success(List<Guarage> guarages, Response response) {
                        for (Guarage guarage : guarages) {
                            if (guarage != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveGuarageData(guarage);
                            }
                        }
                        
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    	
                    }

                });
        }
    }
    
    private void saveGuestHouseData(){
        if (Network.isOnline(this.getActivity())) {
    		
        	long ghId = 0;
        	List<GuestHouse> ghList= GuestHouse.findWithQuery(GuestHouse.class, 
        			"SELECT * FROM  Guest_House WHERE Guest_House_Id = " +
        			"(SELECT max(Guest_House_Id) FROM  Guest_House)");
        	if(ghList.size() > 0)
        		ghId = ghList.get(0).getGuestHouseId();
        	
            API.guestHouseService.getGuestHouse(ghId,
                new Callback<List<GuestHouse>>() {
                    @Override
                    public void success(List<GuestHouse> guestHouses, Response response) {
                        for (GuestHouse guestHouse : guestHouses) {
                            if (guestHouse != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveGuestHouseData(guestHouse);
                            }
                        }
                        
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    	
                    }

                });
        }
    }
    
    private void saveHallTypeData(){
        if (Network.isOnline(this.getActivity())) {
    		
        	long htId = 0;
        	List<HallType> htList= HallType.findWithQuery(HallType.class, 
        			"SELECT * FROM  Hall_Type WHERE ht_Id = " +
        			"(SELECT max(ht_Id) FROM  Hall_Type)");
        	if(htList.size() > 0)
        		htId = htList.get(0).getHtId();
        	
            API.hallTypeService.getHallTypes(htId,
                new Callback<List<HallType>>() {
                    @Override
                    public void success(List<HallType> hallTypes, Response response) {
                        for (HallType hallType : hallTypes) {
                            if (hallType != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveHallTypeData(hallType);
                            }
                        }
                        
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    	
                    }

                });
        }
    }

    private void saveHandyManData(){
        if (Network.isOnline(this.getActivity())) {
    		
        	long hmId = 0;
        	List<HandyMan> hmList= HandyMan.findWithQuery(HandyMan.class, 
        			"SELECT * FROM  Handy_Man WHERE handy_Man_Id = " +
        			"(SELECT max(handy_Man_Id) FROM  Handy_Man)");
        	if(hmList.size() > 0)
        		hmId = hmList.get(0).getHandyManId();
        	
            API.handyManService.getHandyMan(hmId,
                new Callback<List<HandyMan>>() {
                    @Override
                    public void success(List<HandyMan> handyList, Response response) {
                        for (HandyMan handy : handyList) {
                            if (handy != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveHandyManData(handy);
                            }
                        }
                        
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    	
                    }

                });
        }
    }

    private void saveHandyCategoryData(){
        if (Network.isOnline(this.getActivity())) {
    		
        	long hcId = 0;
        	List<HandyCategory> hcList= HandyCategory.findWithQuery(HandyCategory.class, 
        			"SELECT * FROM  Handy_Category WHERE hc_Id = " +
        			"(SELECT max(hc_Id) FROM  Handy_Category)");
        	if(hcList.size() > 0)
        		hcId = hcList.get(0).getHcId();
        	
            API.handyCategoryService.getHandyCategory(hcId,
                new Callback<List<HandyCategory>>() {
                    @Override
                    public void success(List<HandyCategory> handyList, Response response) {
                        for (HandyCategory handy : handyList) {
                            if (handy != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveHandyCategory(handy);
                            }
                        }
                        
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    	
                    }

                });
        }
    }
    
    private void saveHdntaData(){
        if (Network.isOnline(this.getActivity())) {
    		
        	long hdntaId = 0;
        	List<Hdnta> hdntaList= Hdnta.findWithQuery(Hdnta.class, 
        			"SELECT * FROM  Hdnta WHERE hdnta_Id = " +
        			"(SELECT max(hdnta_Id) FROM  Hdnta)");
        	if(hdntaList.size() > 0)
        		hdntaId = hdntaList.get(0).getHdntaId();
        	
            API.hdntaService.getHDNTA(hdntaId,
                new Callback<List<Hdnta>>() {
                    @Override
                    public void success(List<Hdnta> hdntas, Response response) {
                        for (Hdnta hdnta : hdntas) {
                            if (hdnta != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveHdntaData(hdnta);
                            }
                        }
                        
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    	
                    }

                });
        }
    }
    
    private void saveHouseListingData(){
        if (Network.isOnline(this.getActivity())) {
    		
        	long hlId = 0;
        	List<HouseListing> hlList= HouseListing.findWithQuery(HouseListing.class, 
        			"SELECT * FROM  House_Listing WHERE House_Listing_Id = " +
        			"(SELECT max(House_Listing_Id) FROM  House_Listing)");
        	if(hlList.size() > 0)
        		hlId = hlList.get(0).getHouseListingId();
        	
            API.houseListingService.getHouseListing(hlId,
                new Callback<List<HouseListing>>() {
                    @Override
                    public void success(List<HouseListing> houseListings, Response response) {
                        for (HouseListing houseListing : houseListings) {
                            if (houseListing != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveHouseListingData(houseListing);
                            }
                        }
                        
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    	
                    }

                });
        }
    }
    
    private void saveHouseTypeData(){
        if (Network.isOnline(this.getActivity())) {
    		
        	long htId = 0;
        	List<HouseType> htList= HouseType.findWithQuery(HouseType.class, 
        			"SELECT * FROM  House_Type WHERE House_Type_Id = " +
        			"(SELECT max(House_Type_Id) FROM  House_Type)");
        	if(htList.size() > 0)
        		htId = htList.get(0).getHouseTypeId();
        	
            API.houseTypeService.getHouseTypes(htId,
                new Callback<List<HouseType>>() {
                    @Override
                    public void success(List<HouseType> houseTypes, Response response) {
                        for (HouseType houseType : houseTypes) {
                            if (houseType != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveHouseTypeData(houseType);
                            }
                        }
                        
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    	
                    }

                });
        }
    }
    
    private void saveJobCategoryData(){
        if (Network.isOnline(this.getActivity())) {
    		
        	long jcId = 0;
        	List<JobCategory> jcList= JobCategory.findWithQuery(JobCategory.class, 
        			"SELECT * FROM  Job_Category WHERE jc_Id = " +
        			"(SELECT max(jc_Id) FROM  Job_Category)");
        	if(jcList.size() > 0)
        		jcId = jcList.get(0).getJcId();
        	
            API.jobCategoryService.getJobCategories(jcId,
                new Callback<List<JobCategory>>() {
                    @Override
                    public void success(List<JobCategory> jobCategories, Response response) {
                        for (JobCategory jobCategory : jobCategories) {
                            if (jobCategory != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveJobCategoryData(jobCategory);
                            }
                        }
                        
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    	
                    }

                });
        }
    }
    
    private void saveJobData(){
        if (Network.isOnline(this.getActivity())) {
    		
        	long jobId = 0;
        	List<Jobs> jobList= Jobs.findWithQuery(Jobs.class, 
        			"SELECT * FROM  Jobs WHERE job_Id = " +
        			"(SELECT max(job_Id) FROM  Jobs)");
        	if(jobList.size() > 0)
        		jobId = jobList.get(0).getJobId();
        	
            API.jobService.getJobs(jobId,
                new Callback<List<Jobs>>() {
                    @Override
                    public void success(List<Jobs> jobs, Response response) {
                        for (Jobs job : jobs) {
                            if (job != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveJobsData(job);
                            }
                        }
                        
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    	
                    }

                });
        }
    }
    
    private void saveLocationData(){
        if (Network.isOnline(this.getActivity())) {
    		
        	long locId = 0;
        	List<Location> locList= Location.findWithQuery(Location.class, 
        			"SELECT * FROM  Location WHERE Location_Id = " +
        			"(SELECT max(Location_Id) FROM  Location)");
        	if(locList.size() > 0)
        		locId = locList.get(0).getLocationId();
        	
            API.locationService.getLocations(locId,
                new Callback<List<Location>>() {
                    @Override
                    public void success(List<Location> locations, Response response) {
                        for (Location location : locations) {
                            if (location != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveLocationData(location);
                            }
                        }
                        
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    	
                    }

                });
        }
    }
    
    private void saveMovieTypeData(){
        if (Network.isOnline(this.getActivity())) {
    		
        	long mtId = 0;
        	List<MovieType> mtList= MovieType.findWithQuery(MovieType.class, 
        			"SELECT * FROM  Movie_Type WHERE mt_Id = " +
        			"(SELECT max(mt_Id) FROM  Movie_Type)");
        	if(mtList.size() > 0)
        		mtId = mtList.get(0).getMtId();
        	
            API.movieTypeService.getMovieTypes(mtId,
                new Callback<List<MovieType>>() {
                    @Override
                    public void success(List<MovieType> movieTypes, Response response) {
                        for (MovieType movieType : movieTypes) {
                            if (movieType != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveMovieTypeData(movieType);
                            }
                        }
                        
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    	
                    }

                });
        }
    }
    
    private void saveNightClubData(){
        if (Network.isOnline(this.getActivity())) {
    		
        	long ncId = 0;
        	List<NightClub> ncList= NightClub.findWithQuery(NightClub.class, 
        			"SELECT * FROM  Night_Club WHERE nc_Id = " +
        			"(SELECT max(nc_Id) FROM  Night_Club)");
        	if(ncList.size() > 0)
        		ncId = ncList.get(0).getNcId();
        	
            API.nightClubService.getNightClubs(ncId,
                new Callback<List<NightClub>>() {
                    @Override
                    public void success(List<NightClub> nightClubs, Response response) {
                        for (NightClub nightClub : nightClubs) {
                            if (nightClub != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveNightClubData(nightClub);
                            }
                        }
                        
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    	
                    }

                });
        }
    }
    
    private void savePharmacyData(){
        if (Network.isOnline(this.getActivity())) {
    		
        	long pharmacyId = 0;
        	List<Pharmacy> pharmacyList= Pharmacy.findWithQuery(Pharmacy.class, 
        			"SELECT * FROM  Pharmacy WHERE pharmacy_Id = " +
        			"(SELECT max(pharmacy_Id) FROM  Pharmacy)");
        	if(pharmacyList.size() > 0)
        		pharmacyId = pharmacyList.get(0).getPharmacyId();
        	
            API.pharmacyService.getPharmacy(pharmacyId,
                new Callback<List<Pharmacy>>() {
                    @Override
                    public void success(List<Pharmacy> pharmacies, Response response) {
                        for (Pharmacy pharmacy : pharmacies) {
                            if (pharmacy != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.savePharmacyData(pharmacy);
                            }
                        }
                        
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    	
                    }

                });
        }
    }
    
    private void savePhotoVideoData(){
        if (Network.isOnline(this.getActivity())) {
    		
        	long pvId = 0;
        	List<PhotoVideo> pvList= PhotoVideo.findWithQuery(PhotoVideo.class, 
        			"SELECT * FROM  Photo_Video WHERE Photo_Video_Id = " +
        			"(SELECT max(Photo_Video_Id) FROM  Photo_Video)");
        	if(pvList.size() > 0)
        		pvId = pvList.get(0).getPhotoVideoId();
        	
            API.photoVideoService.getPhotoVideos(pvId,
                new Callback<List<PhotoVideo>>() {
                    @Override
                    public void success(List<PhotoVideo> photoVideos, Response response) {
                        for (PhotoVideo photoVideo : photoVideos) {
                            if (photoVideo != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.savePhotoVideoData(photoVideo);
                            }
                        }
                        
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    	
                    }

                });
        }
    }
    
    private void saveResortData(){
        if (Network.isOnline(this.getActivity())) {
    		
        	long resortId = 0;
        	List<Resort> resortList= Resort.findWithQuery(Resort.class, 
        			"SELECT * FROM  Resort WHERE resort_Id = " +
        			"(SELECT max(resort_Id) FROM  Resort)");
        	if(resortList.size() > 0)
        		resortId = resortList.get(0).getResortId();
        	
            API.resortService.getResorts(resortId,
                new Callback<List<Resort>>() {
                    @Override
                    public void success(List<Resort> resorts, Response response) {
                        for (Resort resort : resorts) {
                            if (resort != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveResortData(resort);
                            }
                        }
                        
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    	
                    }

                });
        }
    }
    
    private void saveRestaurantTypeData(){
        if (Network.isOnline(this.getActivity())) {
    		
        	long rtId = 0;
        	List<RestaurantType> rtList= RestaurantType.findWithQuery(RestaurantType.class, 
        			"SELECT * FROM  Restaurant_Type WHERE Restaurant_Type_Id = " +
        			"(SELECT max(Restaurant_Type_Id) FROM  Restaurant_Type)");
        	if(rtList.size() > 0)
        		rtId = rtList.get(0).getRestaurantTypeId();
        	
            API.restaurantTypeService.getRestaurantType(rtId,
                new Callback<List<RestaurantType>>() {
                    @Override
                    public void success(List<RestaurantType> restaurantTypes, Response response) {
                        for (RestaurantType restaurantType : restaurantTypes) {
                            if (restaurantType != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveRestaurantTypeData(restaurantType);
                            }
                        }
                        
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    	
                    }

                });
        }
    }
    
    private void saveRestaurantData(){
        if (Network.isOnline(this.getActivity())) {
    		
        	long restaurantId = 0;
        	List<Restaurant> restaurantList= Restaurant.findWithQuery(Restaurant.class, 
        			"SELECT * FROM  Restaurant WHERE restaurantId = " +
        			"(SELECT max(restaurantId) FROM  Restaurant)");
        	if(restaurantList.size() > 0)
        		restaurantId = restaurantList.get(0).getRestaurant_id();
        	
            API.restaurantService.getRestaurants(restaurantId,
                new Callback<List<Restaurant>>() {
                    @Override
                    public void success(List<Restaurant> restaurants, Response response) {
                        for (Restaurant restaurant : restaurants) {
                            if (restaurant != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveRestaurantData(restaurant);
                            }
                        }
                        
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    	
                    }

                });
        }
    }
    
    private void saveShopClothData(){
        if (Network.isOnline(this.getActivity())) {
    		
        	long scId = 0;
        	List<ShopCloth> scList= ShopCloth.findWithQuery(ShopCloth.class, 
        			"SELECT * FROM  Shop_Cloth WHERE sc_Id = " +
        			"(SELECT max(sc_Id) FROM  Shop_Cloth)");
        	if(scList.size() > 0)
        		scId = scList.get(0).getScId();
        	
            API.shopClothService.getShopCloth(scId,
                new Callback<List<ShopCloth>>() {
                    @Override
                    public void success(List<ShopCloth> shopCloths, Response response) {
                        for (ShopCloth shopCloth : shopCloths) {
                            if (shopCloth != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveShopClothData(shopCloth);
                            }
                        }
                        
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    	
                    }

                });
        }
    }
    
    private void saveShopComputerData(){
        if (Network.isOnline(this.getActivity())) {
    		
        	long scId = 0;
        	List<ShopComputer> scList= ShopComputer.findWithQuery(ShopComputer.class, 
        			"SELECT * FROM  Shop_Computer WHERE sc_Id = " +
        			"(SELECT max(sc_Id) FROM  Shop_Computer)");
        	if(scList.size() > 0)
        		scId = scList.get(0).getScId();
        	
            API.shopComputerService.getShopComputers(scId,
                new Callback<List<ShopComputer>>() {
                    @Override
                    public void success(List<ShopComputer> shopComputers, Response response) {
                        for (ShopComputer shopComputer : shopComputers) {
                            if (shopComputer != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveShopComputerData(shopComputer);
                            }
                        }
                        
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    	
                    }

                });
        }
    }
    
    private void saveShopElectronicData(){
        if (Network.isOnline(this.getActivity())) {
    		
        	long seId = 0;
        	List<ShopElectronic> seList= ShopElectronic.findWithQuery(ShopElectronic.class, 
        			"SELECT * FROM  Shop_Electronic WHERE se_Id = " +
        			"(SELECT max(se_Id) FROM  Shop_Electronic)");
        	if(seList.size() > 0)
        		seId = seList.get(0).getSeId();
        	
            API.shopElectronicService.getShopElectronics(seId,
                new Callback<List<ShopElectronic>>() {
                    @Override
                    public void success(List<ShopElectronic> shopElectronics, Response response) {
                        for (ShopElectronic shopElectronic : shopElectronics) {
                            if (shopElectronic != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveShopElectronicData(shopElectronic);
                            }
                        }
                        
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    	
                    }

                });
        }
    }
    
    private void saveShopFurnitureData(){
        if (Network.isOnline(this.getActivity())) {
    		
        	long sfId = 0;
        	List<ShopFurniture> sfList= ShopFurniture.findWithQuery(ShopFurniture.class, 
        			"SELECT * FROM  Shop_Furniture WHERE sf_Id = " +
        			"(SELECT max(sf_Id) FROM  Shop_Furniture)");
        	if(sfList.size() > 0)
        		sfId = sfList.get(0).getSfId();
        	
            API.shopFurnitureService.getShopFurnitures(sfId,
                new Callback<List<ShopFurniture>>() {
                    @Override
                    public void success(List<ShopFurniture> shopFurnitures, Response response) {
                        for (ShopFurniture shopFurniture : shopFurnitures) {
                            if (shopFurniture != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveShopFurnitureData(shopFurniture);
                            }
                        }
                        
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    	
                    }

                });
        }
    }
    
    private void saveTaxiData(){
        if (Network.isOnline(this.getActivity())) {
    		
        	long taxiId = 0;
        	List<Taxi> taxiList= Taxi.findWithQuery(Taxi.class, 
        			"SELECT * FROM  Taxi WHERE taxi_Id = " +
        			"(SELECT max(taxi_Id) FROM  Taxi)");
        	if(taxiList.size() > 0)
        		taxiId = taxiList.get(0).getTaxiId();
        	
            API.taxiService.getTaxies(taxiId,
                new Callback<List<Taxi>>() {
                    @Override
                    public void success(List<Taxi> taxies, Response response) {
                        for (Taxi taxi : taxies) {
                            if (taxi != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveTaxiData(taxi);
                            }
                        }
                        
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    	
                    }

                });
        }
    }
    
    private void saveTenderData(){
        if (Network.isOnline(this.getActivity())) {
    		
        	long tenderId = 0;
        	List<Tender> tenderList= Tender.findWithQuery(Tender.class, 
        			"SELECT * FROM  Tender WHERE tenderId = " +
        			"(SELECT max(tenderId) FROM  Tender)");
        	if(tenderList.size() > 0)
        		tenderId = tenderList.get(0).getTender_id();
        	
            API.tenderService.getTenders(tenderId,
                new Callback<List<Tender>>() {
                    @Override
                    public void success(List<Tender> tenders, Response response) {
                        for (Tender tender : tenders) {
                            if (tender != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveTenderData(tender);
                            }
                        }
                        
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    	
                    }

                });
        }
    }
    
    private void saveTenderCategoryData(){
        if (Network.isOnline(this.getActivity())) {
    		
        	long tCId = 0;
        	List<TenderCategory> tCList= TenderCategory.findWithQuery(TenderCategory.class, 
        			"SELECT * FROM  Tender_Category WHERE tcId = " +
        			"(SELECT max(tcId) FROM  Tender_Category)");
        	if(tCList.size() > 0)
        		tCId = tCList.get(0).getTc_id();
        	
            API.tenderCategoryService.getTenderCategories(tCId,
                new Callback<List<TenderCategory>>() {
                    @Override
                    public void success(List<TenderCategory> tenderCategories, Response response) {
                        for (TenderCategory tenderCategory : tenderCategories) {
                            if (tenderCategory != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveTenderCategoryData(tenderCategory);
                            }
                        }
                        
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    	
                    }

                });
        }
    }
    
    private void saveTravelAgentData(){
        if (Network.isOnline(this.getActivity())) {
    		
        	long taId = 0;
        	List<TravelAgent> taList= TravelAgent.findWithQuery(TravelAgent.class, 
        			"SELECT * FROM  Travel_Agent WHERE ta_Id = " +
        			"(SELECT max(ta_Id) FROM  Travel_Agent)");
        	if(taList.size() > 0)
        		taId = taList.get(0).getTaId();
        	
            API.travelAgentService.getTravelAgents(taId,
                new Callback<List<TravelAgent>>() {
                    @Override
                    public void success(List<TravelAgent> travelAgents, Response response) {
                        for (TravelAgent travelAgent : travelAgents) {
                            if (travelAgent != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveTravelAgentData(travelAgent);
                            }
                        }
                        
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    	
                    }

                });
        }
    }
    
    private void saveUsedItemData(){
        if (Network.isOnline(this.getActivity())) {
    		
        	long uiId = 0;
        	List<UsedItem> uiList= UsedItem.findWithQuery(UsedItem.class, 
        			"SELECT * FROM  Used_Item WHERE Used_Item_Id = " +
        			"(SELECT max(Used_Item_Id) FROM  Used_Item)");
        	if(uiList.size() > 0)
        		uiId = uiList.get(0).getUsedItemId();
        	
            API.usedItemService.getUsedItem(uiId,
                new Callback<List<UsedItem>>() {
                    @Override
                    public void success(List<UsedItem> usedItems, Response response) {
                        for (UsedItem usedItem : usedItems) {
                            if (usedItem != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveUsedItemData(usedItem);
                            }
                        }
                        
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    	
                    }

                });
        }
    }
    
    private void saveUserSiteData(){
        if (Network.isOnline(this.getActivity())) {
    		
        	long usId = 0;
        	List<UserSite> usList= UserSite.findWithQuery(UserSite.class, 
        			"SELECT * FROM  User_site WHERE us_Id = " +
        			"(SELECT max(us_Id) FROM  User_site)");
        	if(usList.size() > 0)
        		usId = usList.get(0).getUsId();
        	
            API.userSiteService.getUserSite(usId,
                new Callback<List<UserSite>>() {
                    @Override
                    public void success(List<UserSite> userSites, Response response) {
                        for (UserSite userSite : userSites) {
                            if (userSite != null) {
                            	userSite.setIsActive("0");
                            	userSite.setIsSync("1");
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveUserSiteData(userSite);
                            }
                        }
                        
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    	
                    }

                });
        }
    }

    private void updateUserSiteData(){
        if (Network.isOnline(this.getActivity())) {
    		
       	 	final List<UserSite> userSite= Select.from(UserSite.class).
     			where(Condition.prop("is_Active").eq("1")).and(Condition.
     					prop("is_Sync").eq("0")).list();
       	 	if(userSite.size() > 0){
                API.updateUserSiteService.updateUserSite(userSite.get(0).getUsId(), "'" + userSite.get(0).getNotification() + "'",
                    userSite.get(0).getNotify_Job(), userSite.get(0).getNotify_Loc(), new Callback<String>() {
                        @Override
                        public void success(String Updated, Response response) {
                        	userSite.get(0).setIsSync("1");
                        	userSite.get(0).save();
                        	
                        	Toast.makeText(getActivity(), "Notification sent successfuly", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void failure(RetrofitError error) {
                        	
                        }

                    });
            }   		       	 		
       	 }
	}
	
    private void saveUsedItemTypeData(){
        if (Network.isOnline(this.getActivity())) {
    		
        	long uitId = 0;
        	List<UsedItemType> uiList= UsedItemType.findWithQuery(UsedItemType.class, 
        			"SELECT * FROM  Used_Item_Type WHERE Used_Item_Type_Id = " +
        			"(SELECT max(Used_Item_Type_Id) FROM  Used_Item_Type)");
        	if(uiList.size() > 0)
        		uitId = uiList.get(0).getUsedItemTypeId();
        	
            API.usedItemTypeService.getUsedItemTypes(uitId,
                new Callback<List<UsedItemType>>() {
                    @Override
                    public void success(List<UsedItemType> usedItemTypes, Response response) {
                        for (UsedItemType usedItemType : usedItemTypes) {
                            if (usedItemType != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveUsedItemTypeData(usedItemType);
                            }
                        }
                        
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    	
                    }

                });
        }
    }
    
    private void saveWeddingCarData(){
        if (Network.isOnline(this.getActivity())) {
    		
        	long wcId = 0;
        	List<WeddingCar> wcList= WeddingCar.findWithQuery(WeddingCar.class, 
        			"SELECT * FROM  Wedding_Car WHERE wedding_Car_Id = " +
        			"(SELECT max(wedding_Car_Id) FROM  Wedding_Car)");
        	if(wcList.size() > 0)
        		wcId = wcList.get(0).getWeddingCarId();
        	
            API.weddingCarService.getWeddingCars(wcId,
                new Callback<List<WeddingCar>>() {
                    @Override
                    public void success(List<WeddingCar> weddingCars, Response response) {
                        for (WeddingCar weddingCar : weddingCars) {
                            if (weddingCar != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveWeddingCarData(weddingCar);
                            }
                        }
                        
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    	
                    }

                });
        }
    }
    
    private void saveWeddingCRPData(){
        if (Network.isOnline(this.getActivity())) {
    		
        	long wcId = 0;
        	List<WeddingCardRingProtocol> wcList= WeddingCardRingProtocol.findWithQuery(WeddingCardRingProtocol.class, 
        			"SELECT * FROM  Wedding_Card_Ring_Protocol WHERE wcrp_Id = " +
        			"(SELECT max(wcrp_Id) FROM  Wedding_Card_Ring_Protocol)");
        	if(wcList.size() > 0)
        		wcId = wcList.get(0).getWcrpId();
        	
            API.weddingCardRingProtocolService.getWeddingCardRingProtocols(wcId,
                new Callback<List<WeddingCardRingProtocol>>() {
                    @Override
                    public void success(List<WeddingCardRingProtocol> weddingCardRingProtocols, Response response) {
                        for (WeddingCardRingProtocol weddingCardRingProtocol : weddingCardRingProtocols) {
                            if (weddingCardRingProtocol != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveWCRPData(weddingCardRingProtocol);
                            }
                        }
                        
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    	
                    }

                });
        }
    }
    
    private void saveWeddingClothData(){
        if (Network.isOnline(this.getActivity())) {
    		
        	long wcId = 0;
        	List<WeddingCloth> wcList= WeddingCloth.findWithQuery(WeddingCloth.class, 
        			"SELECT * FROM  Wedding_Cloth WHERE Wedding_Cloth_Id = " +
        			"(SELECT max(Wedding_Cloth_Id) FROM  Wedding_Cloth)");
        	if(wcList.size() > 0)
        		wcId = wcList.get(0).getWeddingClothId();
        	
            API.weddingClothService.getWeddingCloths(wcId,
                new Callback<List<WeddingCloth>>() {
                    @Override
                    public void success(List<WeddingCloth> weddingCloths, Response response) {
                        for (WeddingCloth weddingCloth : weddingCloths) {
                            if (weddingCloth != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveWeddingClothData(weddingCloth);
                            }
                        }
                        
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    	
                    }

                });
        }
    }
    
    private void saveWeddingHallData(){
        if (Network.isOnline(this.getActivity())) {
    		
        	long whId = 0;
        	List<WeddingHall> whList= WeddingHall.findWithQuery(WeddingHall.class, 
        			"SELECT * FROM  Wedding_Hall WHERE Wedding_Hall_Id = " +
        			"(SELECT max(Wedding_Hall_Id) FROM  Wedding_Hall)");
        	if(whList.size() > 0)
        		whId = whList.get(0).getWeddingHallId();
        	
            API.weddingHallService.getWeddingHalls(whId,
                new Callback<List<WeddingHall>>() {
                    @Override
                    public void success(List<WeddingHall> weddingHalls, Response response) {
                        for (WeddingHall weddingHall : weddingHalls) {
                            if (weddingHall != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveWeddingHall(weddingHall);
                            }
                        }
                        mHome();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    	mHome();
                    }

                });
        }
    }
    
    private void mHome(){
    	safeUIBlockingUtility.safelyUnBlockUI();
		FragmentManager fragmentManager = getFragmentManager();
		HomeFragment fragment = new HomeFragment();
		fragmentManager.beginTransaction()
				.replace(R.id.frame_container, fragment).commit();    	
    }
    
	@Override
	public void dataSaved() {		
	}
}
