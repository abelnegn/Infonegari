package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class BusinessListing extends SugarRecord<BusinessListing>{
	private long ListingId;
	private String ListingName;
	private String ListingDiscription;
	private long ListingLocation;
	private String FullAddress;
	private long ListingCity;
	private String ListingPhone;
	private String ListingEmail;
	private long ListingMainCategory;
	private long ListingSubCategory;
	private long MemberId;

    public boolean isNew() {
        long count = Select.from(BusinessListing.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }


	public long getListingId() {
		return ListingId;
	}


	public void setListingId(long listingId) {
		ListingId = listingId;
	}


	public String getListingName() {
		return ListingName;
	}

	public void setListingName(String listingName) {
		ListingName = listingName;
	}

	public String getListingDiscription() {
		return ListingDiscription;
	}

	public void setListingDiscription(String listingDiscription) {
		ListingDiscription = listingDiscription;
	}

	public long getListingLocation() {
		return ListingLocation;
	}

	public void setListingLocation(long listingLocation) {
		ListingLocation = listingLocation;
	}

	public String getFullAddress() {
		return FullAddress;
	}

	public void setFullAddress(String fullAddress) {
		FullAddress = fullAddress;
	}

	public long getListingCity() {
		return ListingCity;
	}

	public void setListingCity(long listingCity) {
		ListingCity = listingCity;
	}

	public String getListingPhone() {
		return ListingPhone;
	}

	public void setListingPhone(String listingPhone) {
		ListingPhone = listingPhone;
	}

	public String getListingEmail() {
		return ListingEmail;
	}

	public void setListingEmail(String listingEmail) {
		ListingEmail = listingEmail;
	}

	public long getListingMainCategory() {
		return ListingMainCategory;
	}

	public void setListingMainCategory(long listingMainCategory) {
		ListingMainCategory = listingMainCategory;
	}

	public long getListingSubCategory() {
		return ListingSubCategory;
	}

	public void setListingSubCategory(long listingSubCategory) {
		ListingSubCategory = listingSubCategory;
	}

	public long getMemberId() {
		return MemberId;
	}

	public void setMemberId(long memberId) {
		MemberId = memberId;
	}

	@Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
