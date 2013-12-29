package com.github.mcfeudalages.kingdoms;

import com.github.mcfeudalages.kingdoms.entity.Region;
import com.github.mcfeudalages.kingdoms.entity.RegionColls;
import com.github.mcfeudalages.kingdoms.entity.RegionMapColls;

public class RegionAccess {
	private final String associatedRegionID;
	public String getAssociatedRegionID() { return associatedRegionID; }
	
	public RegionAccess withAssociatedRegion(String regionID) { return valueOf(regionID); }
	/*
	 * Private COnstructer
	 */
	private RegionAccess(String regionID) {
		if(regionID == null) throw new IllegalArgumentException("ownerFaction is null");
		this.associatedRegionID = regionID;
	}
	
	public static RegionAccess valueOf(String regionID) {
		return new RegionAccess(regionID);
	}
	
	public Region getAssociatedRegion(Object universe) {
		return RegionColls.get().get(universe).get(this.getAssociatedRegionID());
	}
}
