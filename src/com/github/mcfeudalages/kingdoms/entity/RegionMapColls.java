package com.github.mcfeudalages.kingdoms.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.github.mcfeudalages.kingdoms.FAConst;
import com.github.mcfeudalages.kingdoms.RegionAccess;
import com.massivecraft.factions.Factions;
import com.massivecraft.factions.RelationParticipator;
import com.massivecraft.factions.entity.Board;
import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.XColls;
import com.massivecraft.mcore.Aspect;
import com.massivecraft.mcore.ps.PS;
import com.massivecraft.mcore.util.MUtil;

public class RegionMapColls extends XColls<RegionMapColl, RegionMap> implements RegionMapInterface {
	private static RegionMapColls i = new RegionMapColls();
	public static RegionMapColls get() { return i; }
	
	@Override
	public RegionMapColl createColl(String collName) {
		return new RegionMapColl(collName)
;	}
	
	@Override
	public Aspect getAspect() {
		return Factions.get().getAspect();
	}
	
	@Override
	public String getBasename() {
		return FAConst.COLLECTION_REGION_MAP;
	}
	
	@Override
	public void init() {
		super.init();
	}
	@Override
	public RegionAccess getRegionAccessAt(PS ps) {
		RegionMapColl coll = this.getForWorld(ps.getWorld());
		if(coll == null) return null;
		return coll.getRegionAccessAt(ps);
	}
	@Override
	public Region getRegionAt(PS ps) {
		RegionMapColl coll = this.getForWorld(ps.getWorld());
		if(coll == null) return null;
		return coll.getRegionAt(ps);
	}
	@Override
	public void setRegionAccessAt(PS ps, RegionAccess regionAccess) {
		RegionMapColl coll = this.getForWorld(ps.getWorld());
		if(coll == null) return;
		coll.setRegionAccessAt(ps, regionAccess);
	}
	@Override
	public void setRegionAt(PS ps, Region region) {
		RegionMapColl coll = this.getForWorld(ps.getWorld());
		if(coll == null) return;
		coll.setRegionAt(ps, region);
	}
	@Override
	public void removeAt(PS ps) {
		RegionMapColl coll = this.getForWorld(ps.getWorld());
		if(coll == null) return;
		coll.removeAt(ps);
	}
	@Override
	public void removeAll(Region region) {
		for(RegionMapColl coll : this.getColls()) {
			coll.clean();
		}
		
	}
	@Override
	public Set<PS> getChunks(Region region) {
		Set<PS> ret = new HashSet<PS>();
		for(RegionMapColl coll: this.getColls()) {
			ret.addAll(coll.getChunks(region));
		}
		return ret;
	}
	@Override
	public int getCount(Region region) {
		int ret = 0;
		for(RegionMapColl coll : this.getColls()) {
			ret += coll.getCount(region);
		}
		return ret;
	}
	@Override
	public ArrayList<String> getMap(RelationParticipator observer, PS centerPs,
			double inDegrees) {
		RegionMapColl coll = this.getForWorld(centerPs.getWorld());
		if(coll == null) return null;
		return coll.getMap(observer, centerPs, inDegrees);
	}

	@Override
	public void clean() {
		for(RegionMapColl coll : this.getColls()) {
			coll.clean();
		}
	}
	
}
