package com.infonegari.fragment;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import com.infonegari.activity.R;
import com.infonegari.objects.db.Auction;
import com.infonegari.objects.db.AuctionCategory;
import com.infonegari.objects.db.Band;
import com.infonegari.objects.db.Bank;
import com.infonegari.objects.db.BeautySaloon;
import com.infonegari.objects.db.BusinessListing;
import com.infonegari.objects.db.CarListing;
import com.infonegari.objects.db.CarType;
import com.infonegari.objects.db.CaterersPasteries;
import com.infonegari.objects.db.Cinema;
import com.infonegari.objects.db.CinemaPlace;
import com.infonegari.objects.db.City;
import com.infonegari.objects.db.Clinic;
import com.infonegari.objects.db.Construction;
import com.infonegari.objects.db.ConstructionMachine;
import com.infonegari.objects.db.ConstructionMaterial;
import com.infonegari.objects.db.DJ;
import com.infonegari.objects.db.Decorators;
import com.infonegari.objects.db.EducationCategory;
import com.infonegari.objects.db.ElectronicsBrand;
import com.infonegari.objects.db.Event;
import com.infonegari.objects.db.FemaleCloth;
import com.infonegari.objects.db.Guarage;
import com.infonegari.objects.db.GuestHouse;
import com.infonegari.objects.db.HandyMan;
import com.infonegari.objects.db.Hdnta;
import com.infonegari.objects.db.HouseListing;
import com.infonegari.objects.db.HouseType;
import com.infonegari.objects.db.JobCategory;
import com.infonegari.objects.db.Jobs;
import com.infonegari.objects.db.Location;
import com.infonegari.objects.db.MainCategory;
import com.infonegari.objects.db.Media;
import com.infonegari.objects.db.Members;
import com.infonegari.objects.db.MovieTitle;
import com.infonegari.objects.db.MovieType;
import com.infonegari.objects.db.NewsLetter;
import com.infonegari.objects.db.NightClub;
import com.infonegari.objects.db.Pharmacy;
import com.infonegari.objects.db.PhotoVideo;
import com.infonegari.objects.db.Recaptcha;
import com.infonegari.objects.db.Resort;
import com.infonegari.objects.db.RestaurantType;
import com.infonegari.objects.db.SalesAuction;
import com.infonegari.objects.db.Shop;
import com.infonegari.objects.db.ShopCategory;
import com.infonegari.objects.db.ShopCloth;
import com.infonegari.objects.db.ShopComputer;
import com.infonegari.objects.db.ShopElectronic;
import com.infonegari.objects.db.ShopFurniture;
import com.infonegari.objects.db.SubCategory;
import com.infonegari.objects.db.Taxi;
import com.infonegari.objects.db.Tender;
import com.infonegari.objects.db.TenderCategory;
import com.infonegari.objects.db.TravelAgent;
import com.infonegari.objects.db.UsedItem;
import com.infonegari.objects.db.UsedItemType;
import com.infonegari.objects.db.WeddingCar;
import com.infonegari.objects.db.WeddingCardRingProtocol;
import com.infonegari.objects.db.WeddingCloth;
import com.infonegari.objects.db.WeddingHall;
import com.infonegari.objects.db.WeddingItemRent;
import com.infonegari.service.API;
import com.infonegari.util.Network;
import com.infonegari.util.OfflineDataHelper;
import com.infonegari.util.SafeUIBlockingUtility;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_download_data, null);

        activity = (FragmentActivity) getActivity();
        safeUIBlockingUtility = new SafeUIBlockingUtility(activity);

        if (Network.isOnline(this.getActivity())) {
            safeUIBlockingUtility.safelyBlockUI();

            saveAuctionData();
            saveAuctionCategoryData();
            saveBandData();
            saveBankData();
            saveBeautySaloonData();
            saveBusinessListingData();
            saveCarListingData();
            saveCarTypeData();
            saveCaterersPasteriesData();
            saveCinemaData();
            saveCinemaPlaceData();
            saveCityData();
            saveClinicData();
            saveConstructionData();
            saveConstructionMachineData();
            saveConstructionMaterialData();
            saveDecoratorsData();
            saveDJData();
            saveEducationCategoryData();
            saveElectronicBrandData();
            saveEventData();
            saveFemaleClothData();
            saveGuarageData();
            saveGuestHouseData();
            saveHallTypeData();
            saveHandyManData();
            saveHdntaData();
            saveHouseListingData();
            saveHouseTypeData();
            saveJobCategoryData();
            saveJobData();
            saveLocationData();
            saveMainCategoryData();
            saveMemberData();
            saveMediaData();
            saveMovieTitleData();
            saveMovieTypeData();
            saveNewsLetterData();
            saveNightClubData();
            savePharmacyData();
            savePhotoVideoData();
            saveRecaptchaData();
            saveResortData();
            saveRestaurantTypeData();
            saveSalesAuctionData();
            saveShopData();
            saveShopCategoryData();
            saveShopClothData();
            saveShopComputerData();
            saveShopElectronicData();
            saveShopFurnitureData();
            saveSubCategoryData();
            saveTaxiData();
            saveTenderData();
            saveTenderCategoryData();
            saveTravelAgentData();
            saveUsedItemData();
            saveUsedItemTypeData();
            saveWeddingCarData();
            saveWeddingCRPData();
            saveWeddingClothData();
            saveWeddingHallData();
            saveWeddingItemRentData();
        }else{
            Toast.makeText(getActivity(), "There is no internet connection", Toast.LENGTH_SHORT).show();
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
        safeUIBlockingUtility.safelyUnBlockUI();
    }
    
    @Override
    public void onDetach() {

        super.onDetach();
        safeUIBlockingUtility.safelyUnBlockUI();
    }

    private void saveAuctionData(){
        if (Network.isOnline(this.getActivity())) {
            API.auctionService.getAuctions(
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
                        Toast.makeText(getActivity(), "Auction data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                           Toast.makeText(getActivity(), "Failed to download Auction Data", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                    	
                        } 
                    }

                });
        }
    }
    
    private void saveAuctionCategoryData(){
        if (Network.isOnline(this.getActivity())) {
            API.auctionCategoryService.getAuctionCategories(
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
                        Toast.makeText(getActivity(), "Auction Category data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {                       	
                            Toast.makeText(getActivity(), "Failed to download Auction Category Data", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                       	
                        } 
                    }

                });
        }
    }

    private void saveBandData(){
        if (Network.isOnline(this.getActivity())) {
            API.bandService.getBands(
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
                        Toast.makeText(getActivity(), "Band data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to download Band Data", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                        	
                        } 
                    }

                });
        }
    }

    private void saveBankData(){
        if (Network.isOnline(this.getActivity())) {
            API.bankService.getBanKs(
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
                        Toast.makeText(getActivity(), "Bank data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to download Bank Data", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                      	
                        }
                    }

                });
        }
    }

    private void saveBeautySaloonData(){
        if (Network.isOnline(this.getActivity())) {
            API.beautySaloonService.getBeautySaloons(
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
                        Toast.makeText(getActivity(), "Beauty Saloon data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to download Beauty Saloon Data", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                        	
                        } 
                    }

                });
        }
    }
    
    private void saveBusinessListingData(){
        if (Network.isOnline(this.getActivity())) {
            API.businessListingService.getBusinessListings(
                new Callback<List<BusinessListing>>() {
                    @Override
                    public void success(List<BusinessListing> businessListings, Response response) {
                        for (BusinessListing businessListing : businessListings) {
                            if (businessListing != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveBusinessData(businessListing);
                            }
                        }
                        Toast.makeText(getActivity(), "Business Listing data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to download Business Listing Data", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {
                        	
                        }
                    }

                });
        }
    }

    private void saveCarListingData(){
        if (Network.isOnline(this.getActivity())) {
            API.carListingService.getCarListings(
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
                        Toast.makeText(getActivity(), "Car Listing data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to download Car Listing Data", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                        	
                        } 
                    }

                });
        }
    }
    
    private void saveCarTypeData(){
        if (Network.isOnline(this.getActivity())) {
            API.carTypeService.getCarTypes(
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
                        Toast.makeText(getActivity(), "Car Type data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to download Car Type", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                       	
                        }
                    }

                });
        }
    }
    
    private void saveCaterersPasteriesData(){
        if (Network.isOnline(this.getActivity())) {
            API.caterersPasteriesService.getCaterersPasteries(
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
                        Toast.makeText(getActivity(), "Caterers and Pasteries data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to download Caterers and Pateries Data", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                      	
                        } 
                    }

                });
        }
    }
    
    private void saveCinemaData(){
        if (Network.isOnline(this.getActivity())) {
            API.cinemaService.getCinemas(
                new Callback<List<Cinema>>() {
                    @Override
                    public void success(List<Cinema> cinemas, Response response) {
                        for (Cinema cinema : cinemas) {
                            if (cinema != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveCinemaData(cinema);
                            }
                        }
                        Toast.makeText(getActivity(), "Cinema data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to download Cinema Data", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                       	
                        }
                    }

                });
        }
    }
    
    private void saveCinemaPlaceData(){
        if (Network.isOnline(this.getActivity())) {
            API.cinemaPlaceService.getCinemaPlace(
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
                        Toast.makeText(getActivity(), "Cinema Place data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to download Cinema Place", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                       	
                        } 
                    }

                });
        }
    }
    
    private void saveCityData(){
        if (Network.isOnline(this.getActivity())) {
            API.cityService.getCities(
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
                        Toast.makeText(getActivity(), "City data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to download City Data", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                       	
                        }
                    }

                });
        }
    }
    
    private void saveClinicData(){
        if (Network.isOnline(this.getActivity())) {
            API.clinicService.getClinics(
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
                        Toast.makeText(getActivity(), "Clinic data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to download Clinic Data", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                       	
                        } 
                    }

                });
        }
    }
    
    private void saveConstructionData(){
        if (Network.isOnline(this.getActivity())) {
            API.constructionService.getConstructions(
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
                        Toast.makeText(getActivity(), "Construction data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to download Construction Data", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                       	
                        }
                    }

                });
        }
    }
    
    private void saveConstructionMachineData(){
        if (Network.isOnline(this.getActivity())) {
            API.constructionMachineService.getConstructionMachines(
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
                        Toast.makeText(getActivity(), "Construction Machine data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to download Construction Machine Data", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                       	
                        }
                    }

                });
        }
    }
    
    private void saveConstructionMaterialData(){
        if (Network.isOnline(this.getActivity())) {
            API.constructionMaterialService.getConstructionMaterials(
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
                        Toast.makeText(getActivity(), "Construction Material data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to download Construction Material Data", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                       	
                        } 
                    }

                });
        }
    }
    
    private void saveDecoratorsData(){
        if (Network.isOnline(this.getActivity())) {
            API.decoratorsService.getDecorators(
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
                        Toast.makeText(getActivity(), "Decorator data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to download Decorator Data", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                        	
                        }
                    }

                });
        }
    }
    
    private void saveDJData(){
        if (Network.isOnline(this.getActivity())) {
            API.dJService.getDJ(
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
                        Toast.makeText(getActivity(), "DJ data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to download DJ Data", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                       	
                        } 
                    }

                });
        }
    }
    
    private void saveEducationCategoryData(){
        if (Network.isOnline(this.getActivity())) {
            API.educationCategoryService.getEducationCategories(
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
                        Toast.makeText(getActivity(), "Electronic Category data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to download Education Category Data", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                      	
                        } 
                    }

                });
        }
    }
    
    private void saveElectronicBrandData(){
        if (Network.isOnline(this.getActivity())) {
            API.electronicsBrandService.getElectronicBrands(
                new Callback<List<ElectronicsBrand>>() {
                    @Override
                    public void success(List<ElectronicsBrand> electronicsBrands, Response response) {
                        for (ElectronicsBrand electronicsBrand : electronicsBrands) {
                            if (electronicsBrand != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveElectronicsBrandData(electronicsBrand);
                            }
                        }
                        Toast.makeText(getActivity(), "Electronic Brand data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to download Electronic Brand Data", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                      	
                        } 
                    }

                });
        }
    }
    
    private void saveEventData(){
        if (Network.isOnline(this.getActivity())) {
            API.eventService.getEvents(
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
                        Toast.makeText(getActivity(), "Event data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to download Event Data", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                        	
                        }
                    }

                });
        }
    }
    
    private void saveFemaleClothData(){
        if (Network.isOnline(this.getActivity())) {
            API.femaleClothService.getFemaleCloths(
                new Callback<List<FemaleCloth>>() {
                    @Override
                    public void success(List<FemaleCloth> femaleCloths, Response response) {
                        for (FemaleCloth femaleCloth : femaleCloths) {
                            if (femaleCloth != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveFemaleClothData(femaleCloth);
                            }
                        }
                        Toast.makeText(getActivity(), "Female Cloth data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to download Female Cloth Data", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                       	
                        } 
                    }

                });
        }
    }
    
    private void saveGuarageData(){
        if (Network.isOnline(this.getActivity())) {
            API.guarageService.getGuarages(
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
                        Toast.makeText(getActivity(), "Guarage data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to download Guarage Data", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                       	
                        } 
                    }

                });
        }
    }
    
    private void saveGuestHouseData(){
        if (Network.isOnline(this.getActivity())) {
            API.guestHouseService.getGuestHouse(
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
                        Toast.makeText(getActivity(), "Guest House data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to download Guest House Data", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                       	
                        } 
                    }

                });
        }
    }
    
    private void saveHallTypeData(){
        if (Network.isOnline(this.getActivity())) {
            API.houseTypeService.getHouseTypes(
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
                        Toast.makeText(getActivity(), "Hall Type data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to download Hall Type Data", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                        	
                        }
                    }

                });
        }
    }
    
    private void saveHandyManData(){
        if (Network.isOnline(this.getActivity())) {
            API.handyManService.getHandyMan(
                new Callback<List<HandyMan>>() {
                    @Override
                    public void success(List<HandyMan> handyMans, Response response) {
                        for (HandyMan handyMan : handyMans) {
                            if (handyMan != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveHandyManData(handyMan);
                            }
                        }
                        Toast.makeText(getActivity(), "Handy Man data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to download Handy Man Data", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                       	
                        } 
                    }

                });
        }
    }
    
    private void saveHdntaData(){
        if (Network.isOnline(this.getActivity())) {
            API.hdntaService.getHDNTA(
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
                        Toast.makeText(getActivity(), "HDNTA data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to download Hdnta Data", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                       	
                        }
                    }

                });
        }
    }
    
    private void saveHouseListingData(){
        if (Network.isOnline(this.getActivity())) {
            API.houseListingService.getHouseListing(
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
                        Toast.makeText(getActivity(), "House Listing data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to download House Listing Data", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                       	
                        } 
                    }

                });
        }
    }
    
    private void saveHouseTypeData(){
        if (Network.isOnline(this.getActivity())) {
            API.houseTypeService.getHouseTypes(
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
                        Toast.makeText(getActivity(), "House Type data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to download House Type", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                       	
                        }
                    }

                });
        }
    }
    
    private void saveJobCategoryData(){
        if (Network.isOnline(this.getActivity())) {
            API.jobCategoryService.getJobCategories(
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
                        Toast.makeText(getActivity(), "Job Category data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to download Job Category Data", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                        	
                        }
                    }

                });
        }
    }
    
    private void saveJobData(){
        if (Network.isOnline(this.getActivity())) {
            API.jobService.getJobs(
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
                        Toast.makeText(getActivity(), "Job data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to download Job Data", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                      	
                        } 
                    }

                });
        }
    }
    
    private void saveLocationData(){
        if (Network.isOnline(this.getActivity())) {
            API.locationService.getLocations(
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
                        Toast.makeText(getActivity(), "Location data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to download Lcation", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                       	
                        }
                    }

                });
        }
    }
    
    private void saveMainCategoryData(){
        if (Network.isOnline(this.getActivity())) {
            API.mainCategoryService.getMainCategories(
                new Callback<List<MainCategory>>() {
                    @Override
                    public void success(List<MainCategory> mainCategories, Response response) {
                        for (MainCategory mainCategory : mainCategories) {
                            if (mainCategory != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveMainCategoryData(mainCategory);
                            }
                        }
                        Toast.makeText(getActivity(), "Main Category data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to Download Main Category", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                        	
                        } 
                    }

                });
        }
    }
 
    private void saveMediaData(){
        if (Network.isOnline(this.getActivity())) {
            API.mediaService.getMedias(
                new Callback<List<Media>>() {
                    @Override
                    public void success(List<Media> medias, Response response) {
                        for (Media media : medias) {
                            if (media != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveMediaData(media);
                            }
                        }
                        Toast.makeText(getActivity(), "Media data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to Download Media", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                        	
                        }
                    }

                });
        }
    }
    
    private void saveMovieTitleData(){
        if (Network.isOnline(this.getActivity())) {
            API.movieTitleService.getMovieTitles(
                new Callback<List<MovieTitle>>() {
                    @Override
                    public void success(List<MovieTitle> movieTitles, Response response) {
                        for (MovieTitle movieTitle : movieTitles) {
                            if (movieTitle != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveMovieTitleData(movieTitle);
                            }
                        }
                        Toast.makeText(getActivity(), "Movie Title data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to Download Movie Title Data", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                        	
                        } 
                    }

                });
        }
    }
    
    private void saveMovieTypeData(){
        if (Network.isOnline(this.getActivity())) {
            API.movieTypeService.getMovieTypes(
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
                        Toast.makeText(getActivity(), "Movie Type data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to Download Movie Type Data", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                        	
                        } 
                    }

                });
        }
    }
    
    private void saveNightClubData(){
        if (Network.isOnline(this.getActivity())) {
            API.nightClubService.getNightClubs(
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
                        Toast.makeText(getActivity(), "Night Club data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to Download Night Club Data", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                        	
                        } 
                    }

                });
        }
    }
    
    private void savePharmacyData(){
        if (Network.isOnline(this.getActivity())) {
            API.pharmacyService.getPharmacy(
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
                        Toast.makeText(getActivity(), "Pharmacy data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to Download Pharmacy Data", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                        	
                        } 
                    }

                });
        }
    }
    
    private void savePhotoVideoData(){
        if (Network.isOnline(this.getActivity())) {
            API.photoVideoService.getPhotoVideos(
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
                        Toast.makeText(getActivity(), "Photo and Video data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to Download Photo and Video Data", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                       	
                        } 
                    }

                });
        }
    }
    
    private void saveRecaptchaData(){
        if (Network.isOnline(this.getActivity())) {
            API.recaptchaService.getRecaptchas(
                new Callback<List<Recaptcha>>() {
                    @Override
                    public void success(List<Recaptcha> recaptchas, Response response) {
                        for (Recaptcha recaptcha : recaptchas) {
                            if (recaptcha != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveRecaptchaData(recaptcha);
                            }
                        }
                        Toast.makeText(getActivity(), "Recaptcha data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to Download Recaptcha Data", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                       	
                        }
                    }

                });
        }
    }
    
    private void saveResortData(){
        if (Network.isOnline(this.getActivity())) {
            API.resortService.getResorts(
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
                        Toast.makeText(getActivity(), "Resort data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to Download Resort Data", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                       	
                        } 
                    }

                });
        }
    }
    
    private void saveMemberData(){
        if (Network.isOnline(this.getActivity())) {
            API.membersService.getMembers(
                new Callback<List<Members>>() {
                    @Override
                    public void success(List<Members> members, Response response) {
                        for (Members member : members) {
                            if (member != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveMembersData(member);
                            }
                        }
                        Toast.makeText(getActivity(), "Member data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to Download Member Data", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                       	
                        }
                    }

                });
        }
    }
    
    private void saveNewsLetterData(){
        if (Network.isOnline(this.getActivity())) {
            API.newsLetterService.getNewsLetter(
                new Callback<List<NewsLetter>>() {
                    @Override
                    public void success(List<NewsLetter> newsLetters, Response response) {
                        for (NewsLetter newsLetter : newsLetters) {
                            if (newsLetter != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveNewsLetterData(newsLetter);
                            }
                        }
                        Toast.makeText(getActivity(), "News Letter data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to Download News Letter", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                       	
                        } 
                    }

                });
        }
    }
    
    private void saveRestaurantTypeData(){
        if (Network.isOnline(this.getActivity())) {
            API.restaurantTypeService.getRestaurantType(
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
                        Toast.makeText(getActivity(), "Restaurant Type data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to Download Restaurant Type", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                        	
                        } 
                    }

                });
        }
    }
    
    private void saveSalesAuctionData(){
        if (Network.isOnline(this.getActivity())) {
            API.salesAuctionService.getSalesAuction(
                new Callback<List<SalesAuction>>() {
                    @Override
                    public void success(List<SalesAuction> salesAuctions, Response response) {
                        for (SalesAuction salesAuction : salesAuctions) {
                            if (salesAuction != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveSalesAuctionData(salesAuction);
                            }
                        }
                        Toast.makeText(getActivity(), "Sales Auction data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to Download Sales Auction Data", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                       	
                        } 
                    }

                });
        }
    }
    
    private void saveShopData(){
        if (Network.isOnline(this.getActivity())) {
            API.shopService.getShops(
                new Callback<List<Shop>>() {
                    @Override
                    public void success(List<Shop> shops, Response response) {
                        for (Shop shop : shops) {
                            if (shop != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveShopData(shop);
                            }
                        }
                        Toast.makeText(getActivity(), "Shop data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to Download Shop Data", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                       	
                        }
                    }

                });
        }
    }
    
    private void saveShopCategoryData(){
        if (Network.isOnline(this.getActivity())) {
            API.shopCategoryService.getShopCategories(
                new Callback<List<ShopCategory>>() {
                    @Override
                    public void success(List<ShopCategory> shopCategories, Response response) {
                        for (ShopCategory shopCategory : shopCategories) {
                            if (shopCategory != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveShopCategoryData(shopCategory);
                            }
                        }
                        Toast.makeText(getActivity(), "Shop Category data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to Download Shop Category Data", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                       	
                        } 
                    }

                });
        }
    }
    
    private void saveShopClothData(){
        if (Network.isOnline(this.getActivity())) {
            API.shopClothService.getShopCloth(
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
                        Toast.makeText(getActivity(), "Shop Cloth data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to Download Shop Cloth Data", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                        	
                        } 
                    }

                });
        }
    }
    
    private void saveShopComputerData(){
        if (Network.isOnline(this.getActivity())) {
            API.shopComputerService.getShopComputers(
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
                        Toast.makeText(getActivity(), "Shop Computer data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to Download Shop Computer Data", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                        	
                        } 
                    }

                });
        }
    }
    
    private void saveShopElectronicData(){
        if (Network.isOnline(this.getActivity())) {
            API.shopElectronicService.getShopElectronics(
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
                        Toast.makeText(getActivity(), "Shop Electronic data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to Download Shop Electronic Data", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                       	
                        }
                    }

                });
        }
    }
    
    private void saveShopFurnitureData(){
        if (Network.isOnline(this.getActivity())) {
            API.shopFurnitureService.getShopFurnitures(
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
                        Toast.makeText(getActivity(), "Shop Furniture data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to Download Shop Furniture Data", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                       	
                        } 
                    }

                });
        }
    }
    
    private void saveSubCategoryData(){
        if (Network.isOnline(this.getActivity())) {
            API.subCategoryService.getSubCategories(
                new Callback<List<SubCategory>>() {
                    @Override
                    public void success(List<SubCategory> subCategories, Response response) {
                        for (SubCategory subCategory : subCategories) {
                            if (subCategory != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveSubCategoryData(subCategory);
                            }
                        }
                        Toast.makeText(getActivity(), "Sub Category data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to Download Sub Category", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                      	
                        }
                    }

                });
        }
    }
    
    private void saveTaxiData(){
        if (Network.isOnline(this.getActivity())) {
            API.taxiService.getTaxies(
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
                        Toast.makeText(getActivity(), "Taxi data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to Download Taxi Data", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                       	
                        }
                    }

                });
        }
    }
    
    private void saveTenderData(){
        if (Network.isOnline(this.getActivity())) {
            API.tenderService.getTenders(
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
                        Toast.makeText(getActivity(), "Tender data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to Download Tender Data", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                       	
                        } 
                    }

                });
        }
    }
    
    private void saveTenderCategoryData(){
        if (Network.isOnline(this.getActivity())) {
            API.tenderCategoryService.getTenderCategories(
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
                        Toast.makeText(getActivity(), "Tender Category data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to Download Tender Category", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                        	
                        } 
                    }

                });
        }
    }
    
    private void saveTravelAgentData(){
        if (Network.isOnline(this.getActivity())) {
            API.travelAgentService.getTravelAgents(
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
                        Toast.makeText(getActivity(), "Travel Agent data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to Download Travel Agent Data", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                       	
                        } 
                    }

                });
        }
    }
    
    private void saveUsedItemData(){
        if (Network.isOnline(this.getActivity())) {
            API.usedItemService.getUsedItem(
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
                        Toast.makeText(getActivity(), "Used Item data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to Download Used Item Data", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                        	
                        } 
                    }

                });
        }
    }
    
    private void saveUsedItemTypeData(){
        if (Network.isOnline(this.getActivity())) {
            API.usedItemTypeService.getUsedItemTypes(
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
                        Toast.makeText(getActivity(), "Used Item Type data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to Download Used Item Type", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                        	
                        } 
                    }

                });
        }
    }
    
    private void saveWeddingCarData(){
        if (Network.isOnline(this.getActivity())) {
            API.weddingCarService.getWeddingCars(
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
                        Toast.makeText(getActivity(), "Wedding Car data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to Download Wedding Car Data", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                        	
                        } 
                    }

                });
        }
    }
    
    private void saveWeddingCRPData(){
        if (Network.isOnline(this.getActivity())) {
            API.weddingCardRingProtocolService.getWeddingCardRingProtocols(
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
                        Toast.makeText(getActivity(), "Wedding Card, Ring and Protocol data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to Download Wedding CRP Data", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                        	
                        }
                    }

                });
        }
    }
    
    private void saveWeddingClothData(){
        if (Network.isOnline(this.getActivity())) {
            API.weddingClothService.getWeddingCloths(
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
                        Toast.makeText(getActivity(), "Wedding Closs data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to Download Wedding Cloth Data", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                        	
                        }
                    }

                });
        }
    }
    
    private void saveWeddingHallData(){
        if (Network.isOnline(this.getActivity())) {
            API.weddingHallService.getWeddingHalls(
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
                        Toast.makeText(getActivity(), "Wedding Hall data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to Download Wedding Hall Data", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                      	
                        } 
                    }

                });
        }
    }
    
    private void saveWeddingItemRentData(){
        if (Network.isOnline(this.getActivity())) {
            API.weddingItemRentService.getWeddingItemsRent(
                new Callback<List<WeddingItemRent>>() {
                    @Override
                    public void success(List<WeddingItemRent> weddingItemRents, Response response) {
                        for (WeddingItemRent weddingItemRent : weddingItemRents) {
                            if (weddingItemRent != null) {
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(DownloadDataFragment.this);
                                helper.saveWeddingItemRentData(weddingItemRent);
                            }
                        }
                        Toast.makeText(getActivity(), "Wedding Item Rent data Downloaded Successfuly", Toast.LENGTH_LONG).show();
                        safeUIBlockingUtility.safelyUnBlockUI();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
                            Toast.makeText(getActivity(), "Failed to Download Wedding Item Rent Data", Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {                        	
                        } 
                    }

                });
        }
    }
	@Override
	public void dataSaved() {		
		safeUIBlockingUtility.safelyUnBlockUI();
	}
}
