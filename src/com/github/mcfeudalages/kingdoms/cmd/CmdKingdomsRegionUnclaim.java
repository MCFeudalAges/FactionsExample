package com.github.mcfeudalages.kingdoms.cmd;

import java.util.Set;

import com.github.mcfeudalages.kingdoms.entity.Region;
import com.github.mcfeudalages.kingdoms.entity.RegionMapColls;
import com.github.mcfeudalages.kingdoms.task.RegionApplyFactionToChunkTask;
import com.massivecraft.factions.Factions;
import com.massivecraft.factions.Perm;
import com.massivecraft.factions.cmd.FCommand;
import com.massivecraft.factions.cmd.req.ReqFactionsEnabled;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.FactionColls;
import com.massivecraft.factions.entity.UConf;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;
import com.massivecraft.mcore.cmd.req.ReqIsPlayer;
import com.massivecraft.mcore.ps.PS;

public class CmdKingdomsRegionUnclaim extends FCommand {
	 
	public CmdKingdomsRegionUnclaim() {
		this.addAliases("runclaim", "ruc");
		
		this.addRequirements(ReqFactionsEnabled.get());
		this.addRequirements(ReqIsPlayer.get());
		this.addRequirements(ReqHasPerm.get(Perm.RUNCLAIM.node));
	}
	
	@Override
	public void perform() {
		PS chunk = PS.valueOf(me).getChunk(true);
		Region currentRegion = RegionMapColls.get().getRegionAt(chunk);
		
		if(currentRegion == null) {
			msg("Region was not found as this chunk");
			return;
		}
		
		Set<PS> chunkSet = RegionMapColls.get().getChunks(currentRegion);
		Faction newFaction = FactionColls.get().get(me).getNone();
		Faction oldFaction = usender.getFaction();
		
		if(oldFaction == newFaction) {
			msg("You need to be in a faction to unclaim a region");
			return;
		}
		
		if(chunkSet.size() == 0) {
			msg("The region contains no chunks to unclaim!?");
			return;
		}
		
		if(!usender.isUsingAdminMode()) {
			//TODO Logic for non admins to unclaim chunks
		}
		
		currentRegion.setOwnerFaction(UConf.get(me).factionIdNone);
		
		Factions.get().log("Going to add a region task for unclaiming " + currentRegion.getName() + " from " + oldFaction.getName());
		new RegionApplyFactionToChunkTask(chunkSet, newFaction, currentRegion, usender);
	}
}