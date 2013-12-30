package com.github.mcfeudalages.kingdoms.cmd;

import java.util.Set;

import com.github.mcfeudalages.kingdoms.RegionAccess;
import com.github.mcfeudalages.kingdoms.entity.Region;
import com.github.mcfeudalages.kingdoms.entity.RegionMapColls;
import com.github.mcfeudalages.kingdoms.task.RegionChunkTask;
import com.massivecraft.factions.Factions;
import com.massivecraft.factions.Perm;
import com.massivecraft.factions.cmd.FCommand;
import com.massivecraft.factions.cmd.req.ReqFactionsEnabled;
import com.massivecraft.factions.entity.BoardColls;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.UConf;
import com.massivecraft.factions.event.FactionsEventChunkChange;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;
import com.massivecraft.mcore.cmd.req.ReqIsPlayer;
import com.massivecraft.mcore.ps.PS;

public class CmdKingdomsRegionClaim extends FCommand {
	public CmdKingdomsRegionClaim() {
		this.addAliases("rclaim", "rc");
		
		this.addRequirements(ReqFactionsEnabled.get());
		this.addRequirements(ReqIsPlayer.get());
		this.addRequirements(ReqHasPerm.get(Perm.RCLAIM.node));
	}
	
	@Override
	public void perform() {
		PS currentChunk = PS.valueOf(me).getChunk(true);
		
		RegionAccess ra = RegionMapColls.get().getRegionAccessAt(currentChunk);
		if(ra.getAssociatedRegionID().equals(UConf.get(this).regionIDNone)) {
			msg("You can't claim a chunk without a region");
			return;
		}
		
		Region region = RegionMapColls.get().getRegionAt(currentChunk);
		if(region == null) {
			msg("Region not found at this chunk");
			return;
		}
		
		Set<PS> chunkSet = RegionMapColls.get().getChunks(region);
		if(chunkSet.isEmpty()) {
			msg("This region lacks chunks!?");
		}
		
		if(usender.getFaction().getId().equals(UConf.get(this).factionIdNone)){
			msg("You need to be apart of a faction to claim a region!");
		}
		if(!usender.isUsingAdminMode()) {
			//TODO add checking for non admins for claim
		}
		
		//TODO Create Event
		//KingdomsEventRegionClaim event = new KingdomsEventRegionClaim();
		region.setOwnerFaction(usender.getFactionId());
		
		new RegionChunkTask(chunkSet, usenderFaction, region) {
			
			@Override
			public boolean work(PS chunk, Faction faction, Region region) {
				FactionsEventChunkChange event = new FactionsEventChunkChange(usender.getSender(), chunk, faction);
				event.run();
				if(event.isCancelled()) return false;
				BoardColls.get().setFactionAt(chunk, faction);
				String chunkCoords = "[" + chunk.getChunkX() + "," + chunk.getChunkZ() + "," + chunk.getWorld() + "]";
				String msg = "Chunk: " + chunkCoords + " of Region: " + region.getName() + " has been given to faction: " + faction.getName(); 
				Factions.get().log(msg);
				return true;
			}
			
		};
		
	}
}
