package com.infonegari.service;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;

import com.infonegari.objects.db.Ads;
import com.infonegari.objects.db.AllCategory;
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
import com.infonegari.service.data.APIEndPoint;

public class API {
	public static final String TAG = API.class.getName();
    public static final String ACCEPT_JSON = "Accept: application/json";
    public static final String CONTENT_TYPE_JSON = "Content-Type: application/json";
    public static final String CONTENT_TYPE_MULTIPART_FORM_DATA = "Content-Type: multipart/form-data";
    public static final String INSTANCE_URL = "InstanceUrl";
    
    //This instance has more Data for Testing
    public static String mInstanceUrl = "http://www.infonegari.com/info_service";
    public static AdsService adsService;
    public static AllCategoryService allCategoryService;
    public static AuctionCategoryService auctionCategoryService;
    public static CarTypeService carTypeService;
    public static CinemaPlaceService cinemaPlaceService;
    public static CityService cityService;
    public static ClinicService clinicService;
    public static HouseTypeService houseTypeService;
    public static JobCategoryService jobCategoryService;
    public static JobService jobService;
    public static LocationService locationService;
    public static MainCategoryService mainCategoryService;
    public static MediaService mediaService;
    public static MembersService membersService;
    public static MovieTitleService movieTitleService;
    public static MovieTypeService movieTypeService;
    public static NewsLetterService newsLetterService;
    public static NightClubService nightClubService;
    public static PharmacyService pharmacyService;
    public static RestaurantTypeService restaurantTypeService;
    public static SubCategoryService subCategoryService;
    public static TenderCategoryService tenderCategoryService;
    public static UsedItemTypeService usedItemTypeService;
    public static AuctionService auctionService;
    public static BandService bandService;
    public static BankService bankService;
    public static BeautySaloonService beautySaloonService;
    public static BusinessListingService businessListingService;
    public static CarListingService carListingService;
    public static CaterersPasteriesService caterersPasteriesService;
    public static CinemaService cinemaService;
    public static ConstructionService constructionService;
    public static ConstructionMachineService constructionMachineService;
    public static ConstructionMaterialService constructionMaterialService;
    public static DecoratorsService decoratorsService;
    public static DJService dJService;
    public static EducationCategoryService educationCategoryService;
    public static ElectronicsBrandService electronicsBrandService;
    public static EventService eventService;
    public static FemaleClothService femaleClothService;
    public static GuarageService guarageService;
    public static GuestHouseService guestHouseService;
    public static HallTypeService hallTypeService;
    public static HandyManService handyManService;
    public static HdntaService hdntaService;
    public static HouseListingService houseListingService;
    public static PhotoVideoService photoVideoService;
    public static RecaptchaService recaptchaService;
    public static ResortService resortService;
    public static RestaurantService restaurantService;
    public static SalesAuctionService salesAuctionService;
    public static ShopService shopService;
    public static ShopCategoryService shopCategoryService;
    public static ShopClothService shopClothService;
    public static ShopComputerService shopComputerService;
    public static ShopElectronicService shopElectronicService;
    public static ShopFurnitureService shopFurnitureService;
    public static TaxiService taxiService;
    public static TravelAgentService travelAgentService;
    public static TenderService tenderService;
    public static UsedItemService usedItemService;
    public static UserSiteService userSiteService;
    public static UpdateUserSiteService updateUserSiteService;
    public static WeddingCarService weddingCarService;
    public static WeddingClothService weddingClothService;
    public static WeddingHallService weddingHallService;
    public static WeddingItemRentService weddingItemRentService;
    public static WeddingCardRingProtocolService weddingCardRingProtocolService;

    static{
    	init();
    }
    
    static RestAdapter sRestAdapter;
    
    private static synchronized void init() {
    	//Lookup services initialization
    	sRestAdapter = createRestAdapter(getInstanceUrl());
    	adsService = sRestAdapter.create(AdsService.class);
    	allCategoryService = sRestAdapter.create(AllCategoryService.class);
    	auctionCategoryService = sRestAdapter.create(AuctionCategoryService.class);
    	carTypeService = sRestAdapter.create(CarTypeService.class);
    	cinemaPlaceService = sRestAdapter.create(CinemaPlaceService.class);
    	cityService = sRestAdapter.create(CityService.class);
    	clinicService = sRestAdapter.create(ClinicService.class);
    	houseTypeService = sRestAdapter.create(HouseTypeService.class);
    	jobCategoryService = sRestAdapter.create(JobCategoryService.class);
    	jobService = sRestAdapter.create(JobService.class);
    	locationService = sRestAdapter.create(LocationService.class);
    	mainCategoryService = sRestAdapter.create(MainCategoryService.class);
    	mediaService = sRestAdapter.create(MediaService.class);
    	membersService = sRestAdapter.create(MembersService.class);
    	movieTitleService = sRestAdapter.create(MovieTitleService.class);
    	movieTypeService = sRestAdapter.create(MovieTypeService.class);
    	newsLetterService = sRestAdapter.create(NewsLetterService.class);
    	nightClubService = sRestAdapter.create(NightClubService.class);
    	pharmacyService = sRestAdapter.create(PharmacyService.class);
    	restaurantTypeService = sRestAdapter.create(RestaurantTypeService.class);
    	subCategoryService = sRestAdapter.create(SubCategoryService.class);
    	tenderCategoryService = sRestAdapter.create(TenderCategoryService.class);
    	usedItemTypeService = sRestAdapter.create(UsedItemTypeService.class);
    	
    	//Transaction service initialization 
    	auctionService = sRestAdapter.create(AuctionService.class);
    	bandService = sRestAdapter.create(BandService.class);
    	bankService = sRestAdapter.create(BankService.class);
    	beautySaloonService = sRestAdapter.create(BeautySaloonService.class);
    	businessListingService = sRestAdapter.create(BusinessListingService.class);
    	carListingService = sRestAdapter.create(CarListingService.class);
    	caterersPasteriesService = sRestAdapter.create(CaterersPasteriesService.class);
    	cinemaService = sRestAdapter.create(CinemaService.class);
    	constructionService = sRestAdapter.create(ConstructionService.class);
    	constructionMachineService = sRestAdapter.create(ConstructionMachineService.class);
    	constructionMaterialService = sRestAdapter.create(ConstructionMaterialService.class);
    	decoratorsService = sRestAdapter.create(DecoratorsService.class);
    	dJService = sRestAdapter.create(DJService.class);
    	educationCategoryService = sRestAdapter.create(EducationCategoryService.class);
    	electronicsBrandService = sRestAdapter.create(ElectronicsBrandService.class);
    	eventService = sRestAdapter.create(EventService.class);
    	femaleClothService = sRestAdapter.create(FemaleClothService.class);
    	guarageService = sRestAdapter.create(GuarageService.class);
    	guestHouseService = sRestAdapter.create(GuestHouseService.class);
    	hallTypeService = sRestAdapter.create(HallTypeService.class);
    	handyManService = sRestAdapter.create(HandyManService.class);
    	hdntaService = sRestAdapter.create(HdntaService.class);
    	houseListingService = sRestAdapter.create(HouseListingService.class);
    	photoVideoService = sRestAdapter.create(PhotoVideoService.class);
    	recaptchaService = sRestAdapter.create(RecaptchaService.class);
    	resortService = sRestAdapter.create(ResortService.class);
    	restaurantService = sRestAdapter.create(RestaurantService.class);
    	salesAuctionService = sRestAdapter.create(SalesAuctionService.class);
    	shopService = sRestAdapter.create(ShopService.class);
    	shopCategoryService = sRestAdapter.create(ShopCategoryService.class);
    	shopClothService = sRestAdapter.create(ShopClothService.class);
    	shopComputerService = sRestAdapter.create(ShopComputerService.class);
    	shopElectronicService = sRestAdapter.create(ShopElectronicService.class);
    	shopFurnitureService = sRestAdapter.create(ShopFurnitureService.class);
    	taxiService = sRestAdapter.create(TaxiService.class);
    	travelAgentService = sRestAdapter.create(TravelAgentService.class);
    	tenderService = sRestAdapter.create(TenderService.class);
    	usedItemService = sRestAdapter.create(UsedItemService.class);
    	userSiteService = sRestAdapter.create(UserSiteService.class);
    	updateUserSiteService = sRestAdapter.create(UpdateUserSiteService.class);
    	weddingCarService = sRestAdapter.create(WeddingCarService.class);
    	weddingClothService = sRestAdapter.create(WeddingClothService.class);
    	weddingHallService = sRestAdapter.create(WeddingHallService.class);
    	weddingItemRentService = sRestAdapter.create(WeddingItemRentService.class);
    	weddingCardRingProtocolService = sRestAdapter.create(WeddingCardRingProtocolService.class);
    }
    
    private static RestAdapter createRestAdapter(final String url) {
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(url).build();
      
        restAdapter.setLogLevel(RestAdapter.LogLevel.FULL);
        return restAdapter;
    }
    
    public static <T> Callback<T> getCallback(T t) {
        Callback<T> cb = new Callback<T>() {
            @Override
            public void success(T o, Response response) {
                System.out.println("Object " + o);
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                System.out.println("Error: " + retrofitError);
            }
        };

        return cb;
    }

    public static <T> Callback<List<T>> getCallbackList(List<T> t) {
        Callback<List<T>> cb = new Callback<List<T>>() {
            @Override
            public void success(List<T> o, Response response) {
                System.out.println("Object " + o);
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                System.out.println("Error: " + retrofitError);
            }
        };

        return cb;
    }

    public static synchronized String getInstanceUrl() {
        return mInstanceUrl;
    }

    public static synchronized void setInstanceUrl(String url) {
        mInstanceUrl = url;
        init();
    }

	public interface AdsService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.ADS)
        public void getAds(Callback<List<Ads>> callback);
    }
	
	public interface AllCategoryService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.ALLCATEGORY)
        public void getAllCategories(@Query("ac_id") long acId, 
        		Callback<List<AllCategory>> callback);
    }

	public interface AuctionCategoryService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.AUCTION_CATEGORY)
        public void getAuctionCategories(@Query("ac_id") long acId, 
        		Callback<List<AuctionCategory>> callback);
    }
	
	public interface CarTypeService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.CAR_TYPES)
        public void getCarTypes(@Query("car_type_id") long acId, 
        		Callback<List<CarType>> callback);
    }
	
	public interface CinemaPlaceService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.CINEMA_PLACE)
        public void getCinemaPlace(@Query("cp_id") long cpId, 
        		Callback<List<CinemaPlace>> callback);
    }
	
	public interface CityService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.CITY)
        public void getCities(@Query("city_id") long cityId, 
        		Callback<List<City>> callback);
    }
	
	public interface ClinicService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.CLINIC)
        public void getClinics(@Query("clinic_id") long clinicId, 
        		Callback<List<Clinic>> callback);
    }
	
	public interface HouseTypeService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.HOUSETYPE)
        public void getHouseTypes(@Query("ht_id") long htId, 
        		Callback<List<HouseType>> callback);
    }
	
	public interface JobCategoryService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.JOB_CATEGORY)
        public void getJobCategories(@Query("jc_id") long jcId, 
        		Callback<List<JobCategory>> callback);
    }
	
	public interface JobService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.JOB)
        public void getJobs(@Query("job_id") long jobId, 
        		Callback<List<Jobs>> callback);
    }
	
	public interface LocationService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.LOCATION )
        public void getLocations(@Query("loc_id") long locId,
        		Callback<List<Location>> callback);
    }
	
	public interface MainCategoryService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.MAIN_CATEGORY)
        public void getMainCategories(@Query("mc_id") long mcId, 
        		Callback<List<MainCategory>> callback);
    }
	
	public interface MediaService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.MEDIA)
        public void getMedias(@Query("media_id") long mediaId, 
        		Callback<List<Media>> callback);
    }
	
	public interface MembersService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.MEMBERS)
        public void getMembers(@Query("member_id") long memberId, 
        		Callback<List<Members>> callback);
    }
	
	public interface MovieTitleService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.MOVIE_TITLE)
        public void getMovieTitles(@Query("mt_id") long mtId, 
        		Callback<List<MovieTitle>> callback);
    }
	
	public interface MovieTypeService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.MOVIE_TYPE)
        public void getMovieTypes(@Query("mt_id") long mtId, 
        		Callback<List<MovieType>> callback);
    }
	
	public interface NewsLetterService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.NEWS_LETTER)
        public void getNewsLetter(@Query("nl_id") long nlId, 
        		Callback<List<NewsLetter>> callback);
    }
	
	public interface NightClubService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.NIGHT_CLUB)
        public void getNightClubs(@Query("nc_id") long ncId, 
        		Callback<List<NightClub>> callback);
    }
	
	public interface PharmacyService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.PHARMACY)
        public void getPharmacy(@Query("pharmacy_id") long pharmacyId, 
        		Callback<List<Pharmacy>> callback);
    }
	
	public interface RestaurantTypeService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.RESTAURANT_TYPE)
        public void getRestaurantType(@Query("rt_id") long rtId, 
        		Callback<List<RestaurantType>> callback);
    }
	
	public interface SubCategoryService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.SUB_CATEGORY)
        public void getSubCategories(@Query("sc_id") long scId, 
        		Callback<List<SubCategory>> callback);
    }
	
	public interface TenderCategoryService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.TENDER_CATEGORY)
        public void getTenderCategories(@Query("tc_id") long tcId, 
        		Callback<List<TenderCategory>> callback);
    }
	
	public interface UsedItemTypeService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.USED_ITEM_TYPE)
        public void getUsedItemTypes(@Query("uit_id") long uitId, 
        		Callback<List<UsedItemType>> callback);
    }
	
	public interface AuctionService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.AUCTION)
        public void getAuctions(@Query("auction_id") long auctionId, 
        		Callback<List<Auction>> callback);
    }
	
	public interface BandService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.BAND)
        public void getBands(@Query("band_id") long bandId, 
        		Callback<List<Band>> callback);
    }
	
	public interface BankService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.BANK)
        public void getBanKs(@Query("bank_id") long bankId, 
        		Callback<List<Bank>> callback);
    }
	
	public interface BeautySaloonService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.BEAUTY_SALOONS)
        public void getBeautySaloons(@Query("bs_id") long bsId, 
        		Callback<List<BeautySaloon>> callback);
    }
	
	public interface BusinessListingService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.BUSINESS_LISTING)
        public void getBusinessListings(@Query("bl_id") long blId, 
        		Callback<List<BusinessListing>> callback);
    }
	
	public interface CarListingService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.CAR_LISTING)
        public void getCarListings(@Query("cl_id") long clId, 
        		Callback<List<CarListing>> callback);
    }
	
	public interface CaterersPasteriesService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.CATERERS_PASTERIES)
        public void getCaterersPasteries(@Query("cp_id") long cpId, 
        		Callback<List<CaterersPasteries>> callback);
    }
	
	public interface CinemaService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.CINEMA)
        public void getCinemas(@Query("cinema_id") long cinemaId, Callback<List<Cinema>> callback);
    }
	
	public interface ConstructionService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.CONSTRUCTION_SSNS)
        public void getConstructions(@Query("con_id") long conId, 
        		Callback<List<Construction>> callback);
    }

	public interface ConstructionMachineService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.CONSTRUCTION_MACHINE)
        public void getConstructionMachines(@Query("cm_id") long cmId, 
        		Callback<List<ConstructionMachine>> callback);
    }
	
	public interface ConstructionMaterialService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.CONSTRUCTION_MATERIAL)
        public void getConstructionMaterials(@Query("cm_id") long cmId, 
        		Callback<List<ConstructionMaterial>> callback);
    }
	
	public interface DecoratorsService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.DECORATORS)
        public void getDecorators(@Query("decore_id") long decoreId, 
        		Callback<List<Decorators>> callback);
    }
	
	public interface DJService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.DJ)
        public void getDJ(@Query("dj_id") long djId, 
        		Callback<List<DJ>> callback);
    }
	
	public interface EducationCategoryService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.EDUCATION_CATEGORY)
        public void getEducationCategories(@Query("ec_id") long ecId, 
        		Callback<List<EducationCategory>> callback);
    }
	
	public interface ElectronicsBrandService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.ELECTRONICS_BRAND)
        public void getElectronicBrands(@Query("eb_id") long ebId, 
        		Callback<List<ElectronicsBrand>> callback);
    }
	
	public interface EventService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.EVENT)
        public void getEvents(@Query("event_id") long eventId, 
        		Callback<List<Event>> callback);
    }
	
	public interface FemaleClothService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.FEMALE_CLOTH)
        public void getFemaleCloths(@Query("cloth_id") long clothId, 
        		Callback<List<FemaleCloth>> callback);
    }
	
	public interface GuarageService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.GUARAGE)
        public void getGuarages(@Query("garage_id") long garageId, 
        		Callback<List<Guarage>> callback);
    }
	
	public interface GuestHouseService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.GUEST_HOUSE)
        public void getGuestHouse(@Query("gh_id") long ghId, 
        		Callback<List<GuestHouse>> callback);
    }
	
	public interface HallTypeService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.HALL_TYPE)
        public void getHallTypes(@Query("ht_id") long htId, 
        		Callback<List<HallType>> callback);
    }
	
	public interface HandyManService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.HANDY_MAN)
        public void getHandyMan(@Query("hm_id") long hmId, 
        		Callback<List<HandyMan>> callback);
    }	
	
	public interface HdntaService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.HDNTA)
        public void getHDNTA(@Query("hdnta_id") long hdntaId, 
        		Callback<List<Hdnta>> callback);
    }
	
	public interface HouseListingService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.HOUSE_LISTING)
        public void getHouseListing(@Query("hl_id") long hlId, 
        		Callback<List<HouseListing>> callback);
    }
	
	public interface PhotoVideoService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.PHOTO_VIDEO)
        public void getPhotoVideos(@Query("pv_id") long pvId, 
        		Callback<List<PhotoVideo>> callback);
    }
	
	public interface RecaptchaService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.RECAPTCHA)
        public void getRecaptchas(Callback<List<Recaptcha>> callback);
    }
	
	public interface ResortService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.RESORT)
        public void getResorts(@Query("resort_id") long resortId, 
        		Callback<List<Resort>> callback);
    }
	
	public interface RestaurantService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.RESTAURANT)
        public void getRestaurants(@Query("restaurant_id") long restaurantId, 
        		Callback<List<Restaurant>> callback);
    }
	
	public interface SalesAuctionService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.SALES_AUCTION)
        public void getSalesAuction(@Query("sa_id") long saId, 
        		Callback<List<SalesAuction>> callback);
    }
	
	public interface ShopService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.SHOP)
        public void getShops(@Query("shop_id") long shopId, 
        		Callback<List<Shop>> callback);
    }
	
	public interface ShopCategoryService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.SHOP_CATEGORY)
        public void getShopCategories(@Query("sc_id") long scId, 
        		Callback<List<ShopCategory>> callback);
    }
	
	public interface ShopClothService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.SHOP_CLOTH)
        public void getShopCloth(@Query("sc_id") long scId, 
        		Callback<List<ShopCloth>> callback);
    }
	
	public interface ShopComputerService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.SHOP_COMPUTER)
        public void getShopComputers(@Query("sc_id") long scId, 
        		Callback<List<ShopComputer>> callback);
    }
	
	public interface ShopElectronicService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.SHOP_ELECTRONIC)
        public void getShopElectronics(@Query("se_id") long seId, 
        		Callback<List<ShopElectronic>> callback);
    }
	
	public interface ShopFurnitureService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.SHOP_FURNITURE)
        public void getShopFurnitures(@Query("sf_id") long sfId, 
        		Callback<List<ShopFurniture>> callback);
    }
	
	public interface TaxiService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.TAXI)
        public void getTaxies(@Query("taxi_id") long taxiId, 
        		Callback<List<Taxi>> callback);
    }
	
	public interface TravelAgentService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.TRAVEL_AGENT)
        public void getTravelAgents(@Query("ta_id") long taId, 
        		Callback<List<TravelAgent>> callback);
    }
	
	public interface TenderService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.TENDER)
        public void getTenders(@Query("tender_id") long tenderId, 
        		Callback<List<Tender>> callback);
    }
	
	public interface UsedItemService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.USED_ITEMS)
        public void getUsedItem(@Query("ui_id") long uiId, 
        		Callback<List<UsedItem>> callback);
    }

	public interface UserSiteService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.USER_SITE)
        public void getUserSite(@Query("us_id") long usId, 
        		Callback<List<UserSite>> callback);
    }
	
	public interface UpdateUserSiteService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.UPDATE_USER_SITE)
        public void updateUserSite(@Query("user_id") long usId, 
        		@Query("notification") String notification, Callback<String> callback);
    }
	
	public interface WeddingCarService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.WEDDING_CAR)
        public void getWeddingCars(@Query("wc_id") long wcId, 
        		Callback<List<WeddingCar>> callback);
    }
	
	public interface WeddingClothService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.WEDDING_CLOTH)
        public void getWeddingCloths(@Query("wc_id") long wcId, 
        		Callback<List<WeddingCloth>> callback);
    }
	
	public interface WeddingHallService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.WEDDING_HALL)
        public void getWeddingHalls(@Query("wh_id") long whId, 
        		Callback<List<WeddingHall>> callback);
    }
	
	public interface WeddingItemRentService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.WEDDING_ITEMS_RENT)
        public void getWeddingItemsRent(@Query("wir_id") long wirId, 
        		Callback<List<WeddingItemRent>> callback);
    }
	
	public interface WeddingCardRingProtocolService{
        @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
        @GET(APIEndPoint.WEDDINGCARD_RINGPROTOCOLS)
        public void getWeddingCardRingProtocols(@Query("wcrp_id") long wcrpId, 
        		Callback<List<WeddingCardRingProtocol>> callback);
    }
}
