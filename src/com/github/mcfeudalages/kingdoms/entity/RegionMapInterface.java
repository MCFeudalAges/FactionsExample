package com.github.mcfeudalages.kingdoms.entity;

import java.util.ArrayList;
import java.util.Set;

import com.github.mcfeudalages.kingdoms.RegionAccess;
import com.massivecraft.factions.RelationParticipator;
import com.massivecraft.mcore.ps.PS;

public interface RegionMapInterface {
	public RegionAccess getRegionAccessAt(PS ps);
	public Region getRegionAt(PS ps);
	
	public void setRegionAccessAt(PS ps, RegionAccess regionAccess);
	public void setRegionAt(PS ps, Region region);
	
	public void removeAt(PS ps);
	public void removeAll(Region region);
	public void clean();
	
	public Set<PS> getChunks(Region region);
	
	public int getCount(Region region);
	
	public ArrayList<String> getMap(RelationParticipator observer, PS centerPs, double inDegrees);

}
