package com.github.mcfeudalages.kingdoms.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.github.mcfeudalages.kingdoms.RegionAccess;
import com.massivecraft.factions.Factions;
import com.massivecraft.factions.RelationParticipator;
import com.massivecraft.factions.entity.Board;
import com.massivecraft.mcore.ps.PS;
import com.massivecraft.mcore.store.Coll;
import com.massivecraft.mcore.store.MStore;
import com.massivecraft.mcore.util.MUtil;

public class RegionMapColl extends Coll<RegionMap> implements RegionMapInterface {
	
	public RegionMapColl(String name) {
		super(name, RegionMap.class, MStore.getDb(), Factions.get(), false, true, true);
	}
	
	@Override
	public String fixId(Object oid) {
		if (oid == null) return null;
		if (oid instanceof String) return (String)oid;
		if (oid instanceof RegionMap) return this.getId(oid);
		
		return MUtil.extract(String.class, "worldName", oid);
	}

	@Override
	public RegionAccess getRegionAccessAt(PS ps) {
		if (ps == null) return null;
		RegionMap regionMap = this.get(ps.getWorld());
		if (regionMap == null) return null;
		return regionMap.getRegionAccessAt(ps);
	}

	@Override
	public Region getRegionAt(PS ps) {
		if(ps == null) return null;
		RegionMap regionMap = this.get(ps.getWorld());
		if(regionMap == null) return null;
		return regionMap.getRegionAt(ps);
	}

	@Override
	public void setRegionAccessAt(PS ps, RegionAccess regionAccess) {
		if(ps == null) return;
		RegionMap regionMap = this.get(ps.getWorld());
		if(regionMap == null) return;
		regionMap.setRegionAccessAt(ps, regionAccess);;
	}

	@Override
	public void setRegionAt(PS ps, Region region) {
		if(ps == null) return;
		RegionMap regionMap = this.get(ps.getWorld());
		if(regionMap == null) return;
		regionMap.setRegionAt(ps, region);
	}

	@Override
	public void removeAt(PS ps) {
		if(ps == null) return;
		RegionMap regionMap = this.get(ps.getWorld());
		if(regionMap == null) return;
		regionMap.removeAt(ps);
	}

	@Override
	public void removeAll(Region region) {
		for(RegionMap regionMap : this.getAll()) {
			regionMap.removeAll(region);
		}
	}
	
	@Override
	public void clean() {
		for (RegionMap regionMap : this.getAll())
		{
			regionMap.clean();
		}
	}

	@Override
	public Set<PS> getChunks(Region region) {
		Set<PS> ret = new HashSet<PS>();
		for(RegionMap regionMap : this.getAll()) {
			ret.addAll(regionMap.getChunks(region));
		}
		return ret;
	}

	@Override
	public int getCount(Region region) {
		int ret = 0;
		for(RegionMap regionMap : this.getAll()) {
			ret += regionMap.getCount(region);
		}
		return ret;
	}

	@Override
	public ArrayList<String> getMap(RelationParticipator observer, PS centerPs,
			double inDegrees) {
		if(centerPs == null) return null;
		RegionMap regionMap = this.get(centerPs.getWorld());
		if(regionMap == null) return null;
		return regionMap.getMap(observer, centerPs, inDegrees);
	}
	
}
