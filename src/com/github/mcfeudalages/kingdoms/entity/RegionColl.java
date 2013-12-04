package com.github.mcfeudalages.kingdoms.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;

import com.massivecraft.factions.Factions;
import com.massivecraft.factions.entity.BoardColls;
import com.massivecraft.factions.entity.UPlayerColls;
import com.massivecraft.factions.util.MiscUtil;
import com.massivecraft.mcore.store.Coll;
import com.massivecraft.mcore.store.MStore;
import com.massivecraft.mcore.util.Txt;

public class RegionColl extends Coll<Region> {
	public RegionColl(String name) {
		super(name, Region.class, MStore.getDb(), Factions.get());
	}
	
	@Override
	public void init() {
		super.init();
	}
	
	@Override
	public Region get(Object oid) {
		Region ret = super.get(oid);
		
		if(ret == null && Factions.get().isDatabaseInitialized()) {
			String msg = Txt.parse("<b>Non existing regionID <h>%s requested. <i>Cleaning all boards and uplayers", this.fixId(oid));
			Factions.get().log(msg);
			
			BoardColls.get().clean();
			UPlayerColls.get().clean();
		}
		return ret;
	}
	
	public ArrayList<String> validateName(String str) {
		ArrayList<String> errors = new ArrayList<String>();
		
		//TODO Make 3 a const
		if(MiscUtil.getComparisonString(str).length() < 3) {
			errors.add(Txt.parse("<i>The region name can't be shorter that <h>%s<i> chars", 3));
		}
		//TODO Make 16 a const
		if(str.length() > 16) {
			errors.add(Txt.parse("<i>The region name can't be longer that <h>%s<i> chars", 16));
		}
		
		for(char c : str.toCharArray()) {
			if(!MiscUtil.substanceChars.contains(String.valueOf(c))) {
				errors.add(Txt.parse("<i>Region name must be alphanumeric. \"<h>%s<i>\" is not allowed.", c));
			}
		}
		return errors;
	}
	
	public Region getByName(String str) {
		String compStr = MiscUtil.getComparisonString(str);
		for (Region region : this.getAll())
		{
			if (region.getComparisonName().equals(compStr))
			{
				return region;
			}
		}
		return null;
	}
	
	public Region getBestNameMatch(String searchFor)
	{
		Map<String, Region> name2region = new HashMap<String, Region>();
		
		// TODO: Slow index building
		for (Region region : this.getAll())
		{
			name2region.put(ChatColor.stripColor(region.getName()), region);
		}
		
		String tag = Txt.getBestCIStart(name2region.keySet(), searchFor);
		if (tag == null) return null;
		return name2region.get(tag);
	}
	
	public boolean isNameTaken(String str)
	{
		return this.getByName(str) != null;
	}

}