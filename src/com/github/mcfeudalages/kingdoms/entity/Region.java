package com.github.mcfeudalages.kingdoms.entity;

import com.massivecraft.factions.entity.BoardColls;
import com.massivecraft.factions.entity.UPlayerColls;
import com.massivecraft.factions.util.MiscUtil;
import com.massivecraft.mcore.store.Entity;
import com.massivecraft.mcore.util.MUtil;

public class Region extends Entity<Region> {
	public static Region get(Object oid) {
		return RegionColls.get().get2(oid);
	}
	
	///////////////////////////////////////////////////////////////////////
	
	@Override
	public Region load(Region that) {
		this.setName(that.name);
		this.setOwnerFaction(that.ownerFaction);
		this.setClaimable(that.claimable);
		this.setCreatedAtMillis(that.createdAtMillis);
		
		
		return this;
	}
	
	@Override
	public void preDetach(String id) {
		String universe = this.getUniverse();
		
		// Clean the board
		BoardColls.get().getForUniverse(universe).clean();
		
		// Clean the uplayers
		UPlayerColls.get().getForUniverse(universe).clean();
	}
	
	////////////////////////////////////////////////////////////
	/*
	 * Raw values
	 */
	private String name = null;
	
	private String ownerFaction = null;
	
	private long createdAtMillis = System.currentTimeMillis();
	
	private Boolean claimable = null;
	
	////////////////////////////////////////////////////////////
	
	public boolean isNone() {
		//TODO Use real UUID
		return this.getId().equals("ffffffff-ffff-ffff-ffff-ffffffffffff");
	}
	
	public boolean isNormal()
	{
		return ! this.isNone();
	}
	
	////////////////////////////////////////////////////////////
	
	public String getOwnerFaction() {
		return this.ownerFaction;
	}
	
	public void setOwnerFaction(String owner) {
		String target = owner;
		if(MUtil.equals(this.ownerFaction, target)) return;
		this.ownerFaction = target;
		this.changed();
	}
	
	////////////////////////////////////////////////////////////
	
	public boolean isClaimable() {
		return this.claimable;
	}
	
	public void setClaimable(boolean state) {
		if(this.claimable == state) return;
		this.claimable = state;
		this.changed();
	}
	////////////////////////////////////////////////////////////
	
	public String getName() {
		String ret = this.name;
		//ret = ret.toUpperCase();
		return ret;
	}
	
	public void setName(String name) {
		String target = name;
		if(MUtil.equals(this.name, target)) return;
		this.name = target;
		this.changed();
	}
	
	///////////////////////////////////////////////////////////////////////
	
	public String getComparisonName()
	{
		return MiscUtil.getComparisonString(this.getName());
	}
	
	public String getName(String prefix)
	{
		return prefix + this.getName();
	}
	
	/*
	 * public String getName(RelationParticipator observer) {
	 * 	if (observer == null) return getName();
	 *	return this.getName(this.getColorTo(observer).toString());
	*}
	*/
	
	////////////////////////////////////////////////////////////////////////////////
	public long getCreatedAtMillis()
	{
		return this.createdAtMillis;
	}
	
	public void setCreatedAtMillis(long createdAtMillis)
	{
		// Clean input
		long target = createdAtMillis;
		
		// Detect Nochange
		if (MUtil.equals(this.createdAtMillis, createdAtMillis)) return;

		// Apply
		this.createdAtMillis = target;
		
		// Mark as changed
		this.changed();
	}

	
}
