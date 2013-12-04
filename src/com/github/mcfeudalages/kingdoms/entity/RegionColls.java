package com.github.mcfeudalages.kingdoms.entity;

import com.github.mcfeudalages.kingdoms.FAConst;
import com.massivecraft.factions.Factions;
import com.massivecraft.factions.entity.XColls;
import com.massivecraft.mcore.Aspect;

public class RegionColls extends XColls<RegionColl, Region> {
	public static RegionColls i = new RegionColls();
	public static RegionColls get() { return i; }
	
	@Override
	public RegionColl createColl(String collName) {
		return new RegionColl(collName);
	}
	
	@Override
	public Aspect getAspect() {
		return Factions.get().getAspect();
	}
	
	@Override 
	public String getBasename() {
		return FAConst.COLLECTION_REGION;
	}
	
	@Override
	public void init() {
		super.init();
	}
	
	
}
