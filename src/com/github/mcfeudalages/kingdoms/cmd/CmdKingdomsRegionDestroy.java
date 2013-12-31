package com.github.mcfeudalages.kingdoms.cmd;

import com.github.mcfeudalages.kingdoms.cmd.arg.ARRegion;
import com.github.mcfeudalages.kingdoms.entity.Region;
import com.massivecraft.factions.Factions;
import com.massivecraft.factions.Perm;
import com.massivecraft.factions.cmd.FCommand;
import com.massivecraft.factions.cmd.req.ReqFactionsEnabled;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;
import com.massivecraft.mcore.util.Txt;

public class CmdKingdomsRegionDestroy extends FCommand {
	public CmdKingdomsRegionDestroy() {
		this.addAliases("rdestroy", "rd");
		
		this.addRequiredArg("region");
		
		this.addRequirements(ReqFactionsEnabled.get());
		this.addRequirements(ReqHasPerm.get(Perm.RDESTROY.node));
	}
	
	@Override
	public void perform() {
		Region region = this.arg(0, ARRegion.get(usender));
		if(region == null) return;
		
		//TODO Create REgion Distroy event
		//KingdomsEventRegionDestroy eventRegionDestroy = new KingdomsEventRegionDestroy()
		//event.run();
		//if(event.isCancelled()) return;
		
		//TODO Unclaim the region chunks
		
		Factions.get().log(Txt.parse("<i>The Region <h>%s <i>(<h>%s<i>) was destroyed by <h>%s<i>.", region.getName(), region.getId(), usender.getDisplayName()));
		usender.msg("<i>The Region <h>%s <i>(<h>%s<i>) has been destroyed!",region.getName(), region.getId());
		
		region.detach();
	}
}
