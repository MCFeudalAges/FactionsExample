package com.github.mcfeudalages.kingdoms.cmd;



import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.HumanEntity;

import com.github.mcfeudalages.kingdoms.entity.Region;
import com.github.mcfeudalages.kingdoms.entity.RegionColls;
import com.massivecraft.factions.Perm;
import com.massivecraft.factions.cmd.FCommand;
import com.massivecraft.factions.cmd.req.ReqFactionsEnabled;
import com.massivecraft.mcore.cmd.arg.ARInteger;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;
import com.massivecraft.mcore.util.Txt;

public class CmdKingdomsRegionList extends FCommand{
	public CmdKingdomsRegionList() {
		this.addAliases("rl", "rlist");
		
		this.addOptionalArg("page", "1");
		
		this.addRequirements(ReqFactionsEnabled.get());
		this.addRequirements(ReqHasPerm.get(Perm.RLIST.node));
	}
	
	@Override
	public void perform() {
		Integer pageHumanBased = this.arg(0, ARInteger.get(), 1);
		
		List<String> lines = new ArrayList<String>();
		
		//TODO may have to add ListComparator for regions, if this causes a issue
		ArrayList<Region> regionList = new ArrayList<Region>(RegionColls.get().get(sender).getAll());
		
		final int pageHeight = 9;
		
		int pageCount = (regionList.size() / pageHeight) + 1;
		if(pageHumanBased > pageCount) {
			pageHumanBased = pageCount;
		} else if(pageHumanBased < 1) {
			pageHumanBased = 1;
		}
		int start = (pageHumanBased - 1) * pageHeight;
		int end = start + pageHeight;
		if(end > regionList.size()) {
			end = regionList.size();
		}
		
		lines.add(Txt.titleize("Region List "+ pageHumanBased + "/" + pageCount));
		
		for(Region region :regionList.subList(start, end)) {
			if(region.isNone()) {
				lines.add(Txt.parse("<i>Regionless<i>"));
				continue;
			}
			lines.add(Txt.parse("%s<i>", region.getName()));
		}
		sendMessage(lines);
	}
}
