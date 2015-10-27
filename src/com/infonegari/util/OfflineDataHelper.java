package com.infonegari.util;

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
import com.infonegari.objects.db.HallType;
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
import com.infonegari.objects.db.Restaurant;
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
import com.infonegari.objects.db.UserSite;
import com.infonegari.objects.db.WeddingCar;
import com.infonegari.objects.db.WeddingCardRingProtocol;
import com.infonegari.objects.db.WeddingCloth;
import com.infonegari.objects.db.WeddingHall;
import com.infonegari.objects.db.WeddingItemRent;

import android.util.Log;

public class OfflineDataHelper {
    private OfflineDataSaveListener offlineDataSaveListener;
    private String tag = getClass().getSimpleName();

    public void setOfflineDataSaveListener(OfflineDataSaveListener offlineDataSaveListener) {
        this.offlineDataSaveListener = offlineDataSaveListener;
    }

    public void saveAuctionGategoryData(AuctionCategory ac) {
    	AuctionCategory newAc = new AuctionCategory();
    	newAc.setAc_id(ac.getId());
    	newAc.setCatagory_Name(ac.getCatagory_Name());
    	
    	newAc.save();
    }

    public void saveCarTypeData(CarType carType) {
    	CarType newCarType = new CarType();
    	newCarType.setCarTypeId(carType.getCarTypeId());
    	newCarType.setCarTypeName(carType.getCarTypeName());
	  	
    	newCarType.save();
    }
    
    public void saveCinemaPlaceData(CinemaPlace cinemaPlace) {
    	CinemaPlace newCinemaPlace = new CinemaPlace();
    	newCinemaPlace.setCp_id(cinemaPlace.getId());
    	newCinemaPlace.setCinema_Name(cinemaPlace.getCinema_Name());
	  	
    	newCinemaPlace.save();
    }
    
    public void saveCityData(City city) {
    	City newCity = new City();
    	newCity.setCityId(city.getCityId());
    	newCity.setCityName(city.getCityName());
	  	
    	newCity.save();
    }
    
    public void saveClinicData(Clinic clinic) {
    	Clinic newClinic = new Clinic();
    	newClinic.setClinicId(clinic.getId());
    	newClinic.setItem_Name(clinic.getItem_Name());
    	newClinic.setPrice(clinic.getPrice());
    	newClinic.setLocationId(clinic.getLocationId());
    	newClinic.setDiscription(clinic.getDiscription());
    	newClinic.setClinic_Type(clinic.getClinic_Type());
    	newClinic.setJob_Type(clinic.getJob_Type());
    	newClinic.setUser_Name(clinic.getUser_Name());
	  	
    	newClinic.save();
    }
    
    public void saveHouseTypeData(HouseType houseType) {
    	HouseType newHouseType = new HouseType();
    	newHouseType.setHouseTypeId(houseType.getHouseTypeId());
    	newHouseType.setHouseTypeName(houseType.getHouseTypeName());
	  	
    	newHouseType.save();
    }
    
    public void saveJobCategoryData(JobCategory jc) {
    	JobCategory newJc = new JobCategory();
    	newJc.setJcId(jc.getId());
    	newJc.setCategory_Name(jc.getCategory_Name());
	  	
    	newJc.save();
    }
    
    public void saveJobsData(Jobs jobs) {
    	Jobs newJobs = new Jobs();
    	newJobs.setJobId(jobs.getId());
    	newJobs.setJob_Title(jobs.getJob_Title());
    	newJobs.setCategory(jobs.getCategory());
    	newJobs.setLocationId(jobs.getLocationId());
    	newJobs.setEducation_Level(jobs.getEducation_Level());
    	newJobs.setQualification(jobs.getQualification());
    	newJobs.setResponsibility(jobs.getResponsibility());
    	newJobs.setExperiance(jobs.getExperiance());
    	newJobs.setSalary(jobs.getSalary());
    	newJobs.setAddress(jobs.getAddress());
    	newJobs.setWork_Place(jobs.getWork_Place());
    	newJobs.setDead_Line(jobs.getDead_Line());
    	newJobs.setJob_Duration(jobs.getJob_Duration());
    	newJobs.setUser_Name(jobs.getUser_Name());
	  	
    	newJobs.save();
    }
    
    public void saveLocationData(Location location) {
    	Location newLocation = new Location();
    	newLocation.setLocationId(location.getLocationId());
    	newLocation.setLocationName(location.getLocationName());
    	newLocation.setCityId(location.getCityId());
	  	
    	newLocation.save();
    }
    
    public void saveMainCategoryData(MainCategory mainCategory) {
    	MainCategory newMainCategory = new MainCategory();
    	newMainCategory.setCategoryId(mainCategory.getCategoryId());
    	newMainCategory.setCategoryName(mainCategory.getCategoryName());
	  	
    	newMainCategory.save();
    }
    
    public void saveMediaData(Media media) {
    	Media newMedia = new Media();
    	newMedia.setMediaId(media.getMediaId());
    	newMedia.setMediaName(media.getMediaName());
	  	
    	newMedia.save();
    }
    
    public void saveMembersData(Members member) {
    	Members newMember = new Members();
    	newMember.setMemberId(member.getMemberId());
    	newMember.setBusinessName(member.getBusinessName());
    	newMember.setFirstName(member.getFirstName());
    	newMember.setLastName(member.getLastName());
    	newMember.setPhoneNo(member.getPhoneNo());
    	newMember.setDateJoined(member.getDateJoined());
	  	
    	newMember.save();
    }
    
    public void saveMovieTitleData(MovieTitle mt) {
    	MovieTitle newMt = new MovieTitle();
    	newMt.setMtId(mt.getId());
    	newMt.setMovie_Title(mt.getMovie_Title());
	  	
    	newMt.save();
    }
    
    public void saveMovieTypeData(MovieType mt) {
    	MovieType newMt = new MovieType();
    	newMt.setMtId(mt.getId());
    	newMt.setMovie_Type(mt.getMovie_Type());
	  	
    	newMt.save();
    }
    
    public void saveNewsLetterData(NewsLetter newsLetter) {
    	NewsLetter newNewsLetter = new NewsLetter();
    	newNewsLetter.setNewsLetterId(newsLetter.getNewsLetterId());
    	newNewsLetter.setGuestEmail(newsLetter.getGuestEmail());
    	newNewsLetter.setRequestedDate(newsLetter.getRequestedDate());
	  	
    	newNewsLetter.save();
    }
    
    public void saveNightClubData(NightClub nc) {
    	NightClub newNc = new NightClub();
    	newNc.setNcId(nc.getId());
    	newNc.setItem_Name(nc.getItem_Name());
    	newNc.setPrice(nc.getPrice());
    	newNc.setDiscription(nc.getDiscription());
    	newNc.setLocationId(nc.getLocationId());
    	newNc.setUser_Name(nc.getUser_Name());
	  	
    	newNc.save();
    }
    
    public void savePharmacyData(Pharmacy pharmacy) {
    	Pharmacy newPharmacy = new Pharmacy();
    	newPharmacy.setPharmacyId(pharmacy.getId());
    	newPharmacy.setItem_Name(pharmacy.getItem_Name());
    	newPharmacy.setDiscription(pharmacy.getDiscription());
    	newPharmacy.setLocationId(pharmacy.getLocationId());
    	newPharmacy.setPharma_Type(pharmacy.getPharma_Type());
    	newPharmacy.setPrice(pharmacy.getPrice());
    	newPharmacy.setUser_Name(pharmacy.getUser_Name());
	  	
    	newPharmacy.save();
    }
    
    public void saveRestaurantTypeData(RestaurantType restaurantType) {
    	RestaurantType newRestaurantType = new RestaurantType();
    	newRestaurantType.setRestaurantTypeId(restaurantType.getRestaurantTypeId());
    	newRestaurantType.setRestaurantTypeName(restaurantType.getRestaurantTypeName());
	  	
    	newRestaurantType.save();
    }
    
    public void saveSubCategoryData(SubCategory subCategory) {
    	SubCategory newSubCategory = new SubCategory();
    	newSubCategory.setCategoryId(subCategory.getCategoryId());
    	newSubCategory.setSubCategoryName(subCategory.getSubCategoryName());
	  	
    	newSubCategory.save();
    }
    
    public void saveTenderCategoryData(TenderCategory tenderCategory) {
    	TenderCategory newTenderCategory = new TenderCategory();
    	newTenderCategory.setTc_id(tenderCategory.getId());
    	newTenderCategory.setCatagory_Name(tenderCategory.getCatagory_Name());
	  	
    	newTenderCategory.save();
    }
    
    public void saveUsedItemTypeData(UsedItemType usedItemType) {
    	UsedItemType newUsedItemType = new UsedItemType();
    	newUsedItemType.setUsedItemTypeId(usedItemType.getUsedItemTypeId());
    	newUsedItemType.setUsedItemTypeName(usedItemType.getUsedItemTypeName());
	  	
    	newUsedItemType.save();
    }
    
    public void saveAuctionData(Auction auction) {
    	Auction newAuction = new Auction();
    	newAuction.setAuction_id(auction.getId());
    	newAuction.setCompany_Name(auction.getCompany_Name());
    	newAuction.setPhone_Number(auction.getPhone_Number());
    	newAuction.setPost_Date(auction.getPost_Date());
    	newAuction.setSubmission_Deadline(auction.getSubmission_Deadline());
    	newAuction.setAuction_Catagory(auction.getAuction_Catagory());
    	newAuction.setDescription(auction.getDescription());
    	newAuction.setMinimum_Price(auction.getMinimum_Price());
    	newAuction.setSource(auction.getSource());
    	newAuction.setOpening_Date(auction.getOpening_Date());
    	newAuction.setUser_Name(auction.getUser_Name());

    		  	
    	newAuction.save();
    }
    
    public void saveBandData(Band band){
    	Band newBand = new Band();
    	newBand.setBandId(band.getBandId());
    	newBand.setBandName(band.getBandName());
    	newBand.setLocationId(band.getLocationId());
    	newBand.setMemberId(band.getMemberId());
    	newBand.setDiscription(band.getDiscription());
    	newBand.setPrice(band.getPrice());
    	newBand.setUser_Name(band.getUser_Name());
    	
    	newBand.save();
    }
    
    public void saveBankData(Bank bank){
    	Bank newBank = new Bank();
    	newBank.setBankId(bank.getId());
    	newBank.setItem_Name(bank.getItem_Name());
    	newBank.setPrice(bank.getPrice());
    	newBank.setLocationId(bank.getLocationId());
    	newBank.setDiscription(bank.getDiscription());
    	newBank.setBranch_Name(bank.getBranch_Name());
    	newBank.setUser_Name(bank.getUser_Name());
    	
    	newBank.save();
    }
    
    public void saveBeautySaloonData(BeautySaloon beautySaloon){
    	BeautySaloon newBeautySaloon = new BeautySaloon();
    	newBeautySaloon.setBeautysaloonsId(beautySaloon.getBeautysaloonsId());
    	newBeautySaloon.setBeautysaloonsName(beautySaloon.getBeautysaloonsName());
    	newBeautySaloon.setLocationId(beautySaloon.getLocationId());
    	newBeautySaloon.setBeautysaloonsType(beautySaloon.getBeautysaloonsType());
    	newBeautySaloon.setPrice(beautySaloon.getPrice());
    	newBeautySaloon.setMemberId(beautySaloon.getMemberId());
    	newBeautySaloon.setDiscription(beautySaloon.getDiscription());
    	newBeautySaloon.setUser_Name(beautySaloon.getUser_Name());
    	
    	newBeautySaloon.save();
    }
    
    public void saveBusinessData(BusinessListing businessListing){
    	BusinessListing newBusinessListing = new BusinessListing();
    	newBusinessListing.setListingId(businessListing.getListingId());
    	newBusinessListing.setListingName(businessListing.getListingName());
    	newBusinessListing.setListingDiscription(businessListing.getListingDiscription());
    	newBusinessListing.setListingLocation(businessListing.getListingLocation());
    	newBusinessListing.setFullAddress(businessListing.getFullAddress());
    	newBusinessListing.setListingCity(businessListing.getListingCity());
    	newBusinessListing.setListingPhone(businessListing.getListingPhone());
    	newBusinessListing.setListingEmail(businessListing.getListingEmail());
    	newBusinessListing.setListingMainCategory(businessListing.getListingMainCategory());
    	newBusinessListing.setListingSubCategory(businessListing.getListingSubCategory());
    	newBusinessListing.setMemberId(businessListing.getMemberId());
    	
    	newBusinessListing.save();
    }
    
    public void saveCarListingData(CarListing carListing){
    	CarListing newCarListing = new CarListing();
    	newCarListing.setCarListingId(carListing.getCarListingId());
    	newCarListing.setCarName(carListing.getCarName());
    	newCarListing.setCarTypeId(carListing.getCarTypeId());
    	newCarListing.setCarPrice(carListing.getCarPrice());
    	newCarListing.setDiscription(carListing.getDiscription());
    	newCarListing.setLocationId(carListing.getLocationId());
    	newCarListing.setYear(carListing.getYear());
    	newCarListing.setMemberId(carListing.getMemberId());
    	newCarListing.setCarSale(carListing.isCarSale());
    	newCarListing.setUser_Name(carListing.getUser_Name());
    	
    	newCarListing.save();
    }
    
    public void saveCatererPasteriesData(CaterersPasteries caterersPasteries){
    	CaterersPasteries newCp = new CaterersPasteries();
    	newCp.setCnPId(caterersPasteries.getCnPId());
    	newCp.setCnPIdName(caterersPasteries.getCnPIdName());
    	newCp.setServiceType(caterersPasteries.getServiceType());
    	newCp.setLocationId(caterersPasteries.getLocationId());
    	newCp.setPrice(caterersPasteries.getPrice());
    	newCp.setMemberId(caterersPasteries.getMemberId());
    	newCp.setDiscription(caterersPasteries.getDiscription());
    	newCp.setUser_Name(caterersPasteries.getUser_Name());
    	
    	newCp.save();
    }
    
    public void saveCinemaData(Cinema cinema){
    	Cinema newCinema = new Cinema();
    	newCinema.setCinemaId(cinema.getCinemaId());
    	newCinema.setCinemaTitle(cinema.getCinemaTitle());
    	newCinema.setCalendar(cinema.getCalendar());
    	newCinema.setDiscription(cinema.getDiscription());
    	newCinema.setMovie_Type(cinema.getMovie_Type());
    	newCinema.setLocationId(cinema.getLocationId());
    	newCinema.setUser_Name(cinema.getUser_Name());
    	
    	newCinema.save();
    }
    
    public void saveConstructionData(Construction construction){
    	Construction newConstruction = new Construction();
    	newConstruction.setConstructionSnSId(construction.getConstructionSnSId());
    	newConstruction.setConstructionTitle(construction.getConstructionTitle());
    	newConstruction.setLocationId(construction.getLocationId());
    	newConstruction.setProfession(construction.getProfession());
    	newConstruction.setConstructionMachineId(construction.getConstructionMachineId());
    	newConstruction.setConstructionMaterialId(construction.getConstructionMaterialId());
    	newConstruction.setPrice(construction.getPrice());
    	newConstruction.setDiscription(construction.getDiscription());
    	newConstruction.setUser_Name(construction.getUser_Name());
    	
    	newConstruction.save();
    }
    
    public void saveConMachineData(ConstructionMachine cm) {
    	ConstructionMachine newCm = new ConstructionMachine();
    	newCm.setCmId(cm.getId());
    	newCm.setMachine(cm.getMachine());
	  	
    	newCm.save();
    }
    
    public void saveConMaterialData(ConstructionMaterial cm) {
    	ConstructionMaterial newCm = new ConstructionMaterial();
    	newCm.setCm_id(cm.getId());
    	newCm.setMaterials(cm.getMaterials());
	  	
    	newCm.save();
    }
    
    public void saveDecoratorData(Decorators decorator){
    	Decorators newDecorator = new Decorators();
    	newDecorator.setDecoratorId(decorator.getDecoratorId());
    	newDecorator.setDecoratorName(decorator.getDecoratorName());
    	newDecorator.setLocationId(decorator.getLocationId());
    	newDecorator.setPrice(decorator.getPrice());
    	newDecorator.setDiscription(decorator.getDiscription());
    	newDecorator.setMemberId(decorator.getMemberId());
    	newDecorator.setUser_Name(decorator.getUser_Name());
    	
    	newDecorator.save();
    }
    
    public void saveDJData(DJ dj){
    	DJ newDj = new DJ();
    	newDj.setDjId(dj.getDjId());
    	newDj.setDjName(dj.getDjName());
    	newDj.setLocationId(dj.getLocationId());
    	newDj.setPrice(dj.getPrice());
    	newDj.setMemberId(dj.getMemberId());
    	newDj.setDiscription(dj.getDiscription());
    	newDj.setUser_Name(dj.getUser_Name());
    	
    	newDj.save();
    }
    
    public void saveEducationCatData(EducationCategory ec){
    	EducationCategory newEc = new EducationCategory();
    	newEc.setEcId(ec.getId());
    	newEc.setEducation_Level(ec.getEducation_Level());
    	
    	newEc.save();
    }
    
    public void saveElectronicsBrandData(ElectronicsBrand eb){
    	ElectronicsBrand newEb = new ElectronicsBrand();
    	newEb.setEbId(eb.getId());
    	newEb.setBrand_Name(eb.getBrand_Name());
    	
    	newEb.save();
    }
    
    public void saveEventData(Event event){
    	Event newEvent = new Event();
    	newEvent.setEventId(event.getId());
    	newEvent.setItem_Name(event.getItem_Name());
    	newEvent.setDiscription(event.getDiscription());
    	newEvent.setPrice(event.getPrice());
    	newEvent.setLocationId(event.getLocationId());
    	newEvent.setEvent_Type(event.getEvent_Type());
    	newEvent.setUser_Name(event.getUser_Name());
    	
    	newEvent.save();
    }
    
    public void saveFemaleClothData(FemaleCloth fc){
    	FemaleCloth newFc = new FemaleCloth();
    	newFc.setFcId(fc.getId());
    	newFc.setItem_Name(fc.getItem_Name());
    	newFc.setDiscription(fc.getDiscription());
    	newFc.setLocationId(fc.getLocationId());
    	newFc.setPrice(fc.getPrice());
    	newFc.setColor(fc.getColor());
    	newFc.setSize(fc.getSize());
    	newFc.setUser_Name(fc.getUser_Name());
    	
    	newFc.save();
    }
    
    public void saveGuarageData(Guarage guarage){
    	Guarage newGuarage = new Guarage();
    	newGuarage.setGuarageId(guarage.getId());
    	newGuarage.setItem_Name(guarage.getItem_Name());
    	newGuarage.setDiscription(guarage.getDiscription());
    	newGuarage.setGarage_Type(guarage.getGarage_Type());
    	newGuarage.setJob_Type(guarage.getJob_Type());
    	newGuarage.setLocationId(guarage.getLocationId());
    	newGuarage.setPrice(guarage.getPrice());
    	newGuarage.setUser_Name(guarage.getUser_Name());
    	
    	newGuarage.save();
    }
    
    public void saveGuestHouseData(GuestHouse guestHouse){
    	GuestHouse newGuestHouse = new GuestHouse();
    	newGuestHouse.setGuestHouseId(guestHouse.getGuestHouseId());
    	newGuestHouse.setGuestHouseName(guestHouse.getGuestHouseName());
    	newGuestHouse.setNoRooms(guestHouse.getNoRooms());
    	newGuestHouse.setLocationId(guestHouse.getLocationId());
    	newGuestHouse.setPrice(guestHouse.getPrice());
    	newGuestHouse.setGuestHouseDiscripton(guestHouse.getGuestHouseDiscripton());
    	newGuestHouse.setUser_Name(guestHouse.getUser_Name());
    	
    	newGuestHouse.save();
    }
 
    public void saveHallTypeData(HallType ht){
    	HallType newHt = new HallType();
    	newHt.setHtId(ht.getId());
    	newHt.setHall_Type(ht.getHall_Type());
    	
    	newHt.save();
    }
    
    public void saveHandyManData(HandyMan handyMan){
    	HandyMan newHandyMan = new HandyMan();
    	newHandyMan.setHandyManId(handyMan.getHandyManId());
    	newHandyMan.setHandyManName(handyMan.getHandyManName());
    	newHandyMan.setHandyManProfession(handyMan.getHandyManProfession());
    	newHandyMan.setLocationId(handyMan.getLocationId());
    	newHandyMan.setMemberId(handyMan.getMemberId());
    	
    	newHandyMan.save();
    }
    
    public void saveHdntaData(Hdnta hdnta){
    	Hdnta newHdnta = new Hdnta();
    	newHdnta.setHdntaId(hdnta.getHdntaId());
    	newHdnta.setHdtaName(hdnta.getHdtaName());
    	newHdnta.setLocationId(hdnta.getLocationId());
    	newHdnta.setMemberId(hdnta.getMemberId());
    	newHdnta.setPrice(hdnta.getPrice());
    	newHdnta.setDiscription(hdnta.getDiscription());
    	newHdnta.setUser_Name(hdnta.getUser_Name());
    	
    	newHdnta.save();
    }
    
    public void saveHouseListingData(HouseListing houseListing){
    	HouseListing newHL = new HouseListing();
    	newHL.setHouseListingId(houseListing.getHouseListingId());
    	newHL.setHouse_Name(houseListing.getHouse_Name());
    	newHL.setHouseDiscription(houseListing.getHouseDiscription());
    	newHL.setHousePrice(houseListing.getHousePrice());
    	newHL.setHouseTypeId(houseListing.getHouseTypeId());
    	newHL.setLocationId(houseListing.getLocationId());
    	newHL.setNoRooms(houseListing.getNoRooms());
    	newHL.setLotSize(houseListing.getLotSize());
    	newHL.setMemberId(houseListing.getMemberId());
    	newHL.setSale(houseListing.isSale());
    	newHL.setIsBusiness(houseListing.isIsBusiness());
    	newHL.setUser_Name(houseListing.getUser_Name());
    	
    	newHL.save();
    }
    
    public void savePhotoVideoData(PhotoVideo photoVideo){
    	PhotoVideo newPV = new PhotoVideo();
    	newPV.setPhotoVideoId(photoVideo.getPhotoVideoId());
    	newPV.setPhotoVideoName(photoVideo.getPhotoVideoName());
    	newPV.setLocationId(photoVideo.getLocationId());
    	newPV.setPrice(photoVideo.getPrice());
    	newPV.setMemberId(photoVideo.getMemberId());
    	newPV.setWorkType(photoVideo.getWorkType());
    	newPV.setDiscription(photoVideo.getDiscription());
    	newPV.setUser_Name(photoVideo.getUser_Name());
    	
    	newPV.save();
    	
    }
    
    public void saveRecaptchaData(Recaptcha recaptcha){
    	Recaptcha newRecaptcha = new Recaptcha();
    	newRecaptcha.setRecaptchaId(recaptcha.getId());
    	newRecaptcha.setTest(recaptcha.getTest());
    	newRecaptcha.setCode(recaptcha.getCode());
    	
    	newRecaptcha.save();
    }

    public void saveResortData(Resort resort){
    	Resort newResort = new Resort();
    	newResort.setResortId(resort.getId());
    	newResort.setItem_Name(resort.getItem_Name());
    	newResort.setDiscription(resort.getDiscription());
    	newResort.setLocationId(resort.getLocationId());
    	newResort.setPrice(resort.getPrice());
    	newResort.setUser_Name(resort.getUser_Name());
    	
    	newResort.save();
    }
    
    public void saveRestaurantData(Restaurant restaurant){
    	Restaurant newRestaurant = new Restaurant();
    	newRestaurant.setRestaurant_id(restaurant.getId());
    	newRestaurant.setItem_Name(restaurant.getItem_Name());
    	newRestaurant.setLocationId(restaurant.getLocationId());
    	newRestaurant.setRestaurantTypeId(restaurant.getRestaurantTypeId());
    	newRestaurant.setDiscription(restaurant.getDiscription());
    	newRestaurant.setMemberId(restaurant.getMemberId());
    	newRestaurant.setUser_Name(restaurant.getUser_Name());
    	
    	newRestaurant.save();
    }
    
    public void saveSalesAuctionData(SalesAuction salesAuction){
    	SalesAuction newSA = new SalesAuction();
    	newSA.setSalesAuctionId(salesAuction.getSalesAuctionId());
    	newSA.setSalesAuctionName(salesAuction.getSalesAuctionName());
    	newSA.setStartDate(salesAuction.getStartDate());
    	newSA.setCategory(salesAuction.getCategory());
    	newSA.setSalesItemType(salesAuction.getSalesItemType());
    	newSA.setMemberId(salesAuction.getMemberId());
    	newSA.setSalesAuctionSource(salesAuction.getSalesAuctionSource());
    	
    	newSA.save();
    }
    
    public void saveShopData(Shop shop){
    	Shop newShop = new Shop();
    	newShop.setShopId(shop.getId());
    	newShop.setItem_Name(shop.getItem_Name());
    	newShop.setCatagory_Id(shop.getCatagory_Id());
    	newShop.setDiscription(shop.getDiscription());
    	newShop.setPrice(shop.getPrice());
    	newShop.setColor(shop.getColor());
    	newShop.setLocationId(shop.getLocationId());
    	newShop.setSize(shop.getSize());
    	newShop.setUser_Name(shop.getUser_Name());
    	
    	newShop.save();
    }
    
    public void saveShopCategoryData(ShopCategory sc){
    	ShopCategory newSc = new ShopCategory();
    	newSc.setScId(sc.getId());
    	newSc.setCatagory_Name(sc.getCatagory_Name());
    	
    	newSc.save();
    }
    
    public void saveShopClothData(ShopCloth sc){
    	ShopCloth newSc = new ShopCloth();
    	newSc.setScId(sc.getId());
    	newSc.setItem_Name(sc.getItem_Name());
    	newSc.setCatagory(sc.getCatagory());
    	newSc.setDiscription(sc.getDiscription());
    	newSc.setColor(sc.getCatagory());
    	newSc.setLocationId(sc.getLocationId());
    	newSc.setPrice(sc.getPrice());
    	newSc.setSize(sc.getSize());
    	newSc.setType(sc.getType());
    	newSc.setUser_Name(sc.getUser_Name());
    	
    	newSc.save();
    }
    
    public void saveShopComputerData(ShopComputer sc){
    	ShopComputer newSc = new ShopComputer();
    	newSc.setScId(sc.getId());
    	newSc.setBrand_Name(sc.getBrand_Name());
    	newSc.setDiscription(sc.getDiscription());
    	newSc.setItem_Name(sc.getItem_Name());
    	newSc.setItem_Type(sc.getItem_Type());
    	newSc.setLocationId(sc.getLocationId());
    	newSc.setPrice(sc.getPrice());
    	newSc.setService_Type(sc.getService_Type());
    	newSc.setUser_Name(sc.getUser_Name());
    	
    	newSc.save();
    }
    
    public void saveShopElectronicData(ShopElectronic se){
    	ShopElectronic newSe = new ShopElectronic();
    	newSe.setSeId(se.getId());
    	newSe.setItem_Name(se.getItem_Name());
    	newSe.setBrand_Name(se.getBrand_Name());
    	newSe.setCatagory(se.getCatagory());
    	newSe.setDiscription(se.getDiscription());
    	newSe.setLocationId(se.getLocationId());
    	newSe.setPrice(se.getPrice());
    	newSe.setService_Type(se.getService_Type());
    	newSe.setUser_Name(se.getUser_Name());
    	
    	newSe.save();
    }
    
    public void saveShopFurnitureData(ShopFurniture sf){
    	ShopFurniture newSf = new ShopFurniture();
    	newSf.setSfId(sf.getId());
    	newSf.setItem_Name(sf.getItem_Name());
    	newSf.setDiscription(sf.getDiscription());
    	newSf.setCountry(sf.getCountry());
    	newSf.setItem_Type(sf.getItem_Type());
    	newSf.setLocationId(sf.getLocationId());
    	newSf.setPrice(sf.getPrice());
    	newSf.setUser_Name(sf.getUser_Name());
    	
    	newSf.save();
    }
    
    public void saveTaxiData(Taxi taxi){
    	Taxi newTaxi = new Taxi();
    	newTaxi.setTaxiId(taxi.getId());
    	newTaxi.setItem_Name(taxi.getItem_Name());
    	newTaxi.setDiscription(taxi.getDiscription());
    	newTaxi.setLocationId(taxi.getLocationId());
    	newTaxi.setPrice(taxi.getPrice());
    	newTaxi.setUser_Name(taxi.getUser_Name());
    	
    	newTaxi.save();
    }
    
    public void saveTenderData(Tender tender){
    	Tender newTender = new Tender();
    	newTender.setTender_id(tender.getId());
    	newTender.setCompany_Name(tender.getCompany_Name());
    	newTender.setPhone_Number(tender.getPhone_Number());
    	newTender.setPost_Date(tender.getPost_Date());
    	newTender.setSubmission_Deadline(tender.getSubmission_Deadline());
    	newTender.setOpening_Date(tender.getOpening_Date());
    	newTender.setTender_Catagory(tender.getTender_Catagory());
    	newTender.setDiscription(tender.getDiscription());
    	newTender.setSource(tender.getSource());
    	newTender.setUser_Name(tender.getUser_Name());
    	
    	newTender.save();
    }
    
    public void saveTravelAgentData(TravelAgent ta){
    	TravelAgent newTa = new TravelAgent();
    	newTa.setTaId(ta.getId());
    	newTa.setItem_Name(ta.getItem_Name());
    	newTa.setDiscription(ta.getDiscription());
    	newTa.setLocationId(ta.getLocationId());
    	newTa.setPrice(ta.getPrice());
    	newTa.setUser_Name(ta.getUser_Name());
    	
    	newTa.save();
    }
    
    public void saveUsedItemData(UsedItem usedItem){
    	UsedItem newUI = new UsedItem();
    	newUI.setUsedItemId(usedItem.getUsedItemId());
    	newUI.setUsedItemName(usedItem.getUsedItemName());
    	newUI.setUsedItemTypeId(usedItem.getUsedItemTypeId());
    	newUI.setLocationId(usedItem.getLocationId());
    	newUI.setPrice(usedItem.getPrice());
    	newUI.setDiscription(usedItem.getDiscription());
    	newUI.setMemberId(usedItem.getMemberId());
    	newUI.setUser_Name(usedItem.getUser_Name());
    	
    	newUI.save();
    	
    }
    
    public void saveUserSiteData(UserSite userSite){
    	UserSite newUS = new UserSite();
    	newUS.setUsId(userSite.getId());
    	newUS.setFirst_Name(userSite.getFirst_Name());
    	newUS.setLast_Name(userSite.getLast_Name());
    	newUS.setE_mail(userSite.getE_mail());
    	newUS.setPhone_Number(userSite.getPhone_Number());
    	newUS.setUser_Name(userSite.getUser_Name());
    	newUS.setCompany_Name(userSite.getCompany_Name());
    	
    	newUS.save();
    	
    }
    
    public void saveWeddingCarData(WeddingCar weddingCar){
    	WeddingCar newWC = new WeddingCar();
    	newWC.setWeddingCarId(weddingCar.getWeddingCarId());
    	newWC.setWeddingCarName(weddingCar.getWeddingCarName());
    	newWC.setCarTypeId(weddingCar.getCarTypeId());
    	newWC.setLocationId(weddingCar.getLocationId());
    	newWC.setPrice(weddingCar.getPrice());
    	newWC.setMemberId(weddingCar.getMemberId());
    	newWC.setDiscription(weddingCar.getDiscription());
    	newWC.setUser_Name(weddingCar.getUser_Name());
    	
    	newWC.save();
    }
    
    
    public void saveWeddingClothData(WeddingCloth weddingCloth){
    	WeddingCloth newWC = new WeddingCloth();
    	newWC.setWeddingClothId(weddingCloth.getWeddingClothId());
    	newWC.setWeddingClothName(weddingCloth.getWeddingClothName());
    	newWC.setLocationId(weddingCloth.getLocationId());
    	newWC.setPrice(weddingCloth.getPrice());
    	newWC.setMemberId(weddingCloth.getMemberId());
    	newWC.setDiscription(weddingCloth.getDiscription());
    	newWC.setCloth_Type(weddingCloth.getCloth_Type());
    	newWC.setService_Type(weddingCloth.getService_Type());
    	newWC.setUser_Name(weddingCloth.getUser_Name());
    	
    	newWC.save();
    }
    
    public void saveWeddingHall(WeddingHall weddingHall){
    	WeddingHall newWH = new WeddingHall();
    	newWH.setWeddingHallId(weddingHall.getWeddingHallId());
    	newWH.setWeddingHallName(weddingHall.getWeddingHallName());
    	newWH.setServiceType(weddingHall.getServiceType());
    	newWH.setDateAvailable(weddingHall.getDateAvailable());
    	newWH.setLocationId(weddingHall.getLocationId());
    	newWH.setPrice(weddingHall.getPrice());
    	newWH.setMemberId(weddingHall.getMemberId());
    	newWH.setDiscription(weddingHall.getDiscription());
    	newWH.setHall_Type(weddingHall.getHall_Type());
    	newWH.setBreak_Fast(weddingHall.getBreak_Fast());
    	newWH.setLunch(weddingHall.getLunch());
    	newWH.setDinner(weddingHall.getDinner());
    	newWH.setUser_Name(weddingHall.getUser_Name());
    	
    	newWH.save();
    }
    
    public void saveWeddingItemRentData(WeddingItemRent weddingItemRent){
    	WeddingItemRent newWIR = new WeddingItemRent();
    	newWIR.setWeddingItemId(weddingItemRent.getWeddingItemId());
    	newWIR.setWeddingItemName(weddingItemRent.getWeddingItemName());
    	newWIR.setWeddingItemDiscription(weddingItemRent.getWeddingItemDiscription());
    	newWIR.setWeddingItemPrice(weddingItemRent.getWeddingItemPrice());
    	newWIR.setLocationId(weddingItemRent.getLocationId());
    	newWIR.setMemberId(weddingItemRent.getMemberId());
    	
    	newWIR.save();
    }
    
    public void saveWCRPData(WeddingCardRingProtocol wcrp){
    	WeddingCardRingProtocol newWCRP = new WeddingCardRingProtocol();
    	newWCRP.setWcrpId(wcrp.getWcrpId());
    	newWCRP.setWeddingCRPName(wcrp.getWeddingCRPName());
    	newWCRP.setLocationId(wcrp.getLocationId());
    	newWCRP.setPrice(wcrp.getPrice());
    	newWCRP.setDiscription(wcrp.getDiscription());
    	newWCRP.setMemberId(wcrp.getMemberId());
    	newWCRP.setUser_Name(wcrp.getUser_Name());
    	
    	newWCRP.save();
    	
    	Log.i(tag, "--------All Wedding Card, Ring and Protocol data saved to DB---------");
    	
	    if (offlineDataSaveListener != null) {
	        offlineDataSaveListener.dataSaved();
	    }
    	
    }
    public interface OfflineDataSaveListener {
        public void dataSaved();
    }
}
