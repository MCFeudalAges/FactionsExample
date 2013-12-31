package com.github.mcfeudalages.kingdoms.cmd;

import com.github.mcfeudalages.kingdoms.RegionAccess;
import com.github.mcfeudalages.kingdoms.entity.Region;
import com.github.mcfeudalages.kingdoms.entity.RegionColl;
import com.github.mcfeudalages.kingdoms.entity.RegionColls;
import com.github.mcfeudalages.kingdoms.entity.RegionMapColl;
import com.github.mcfeudalages.kingdoms.entity.RegionMapColls;
import com.massivecraft.factions.Factions;
import com.massivecraft.factions.Perm;
import com.massivecraft.factions.cmd.FCommand;
import com.massivecraft.factions.cmd.req.ReqFactionsEnabled;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;
import com.massivecraft.mcore.cmd.req.ReqIsPlayer;
import com.massivecraft.mcore.ps.PS;

public class CmdKingdomsRegionRemoveChunk extends FCommand {
	public CmdKingdomsRegionRemoveChunk() {
		this.addAliases("rremovechunk", "rrc");
		
		this.addRequiredArg("region");
		
		this.addRequirements(ReqFactionsEnabled.get());
		this.addRequirements(ReqIsPlayer.get());
		this.addRequirements(ReqHasPerm.get(Perm.RREMOVECHUNK.node));
	}
	
	@Override
	public void perform() {
		PS chunk = PS.valueOf(me).getChunk(true);
		Region region = RegionColls.get().get(me).getNone();
		
		this.removeChunk(region, chunk);
	}
	
	private void removeChunk(Region newRegion, PS ps) {
		PS chunk = ps.getChunk(true);
		Region oldRegion = RegionMapColls.get().getRegionAt(chunk);
		
		if(newRegion == oldRegion) {
			msg("Chunk is already a part of the region %s.", newRegion.getName());
			return;
		}
		
		//RegionAccess ra = RegionMapColls.get().getRegionAccessAt(chunk);
		//if(ra != null) {
		//	if(!ra.getAssociatedRegionID().equals(newRegion.getId())) {
		//		msg("Chunk is alreadly apart of another region");
		//		return;
		//	}
		//}

		//TODO Add check for non admin players
		
		//TODO Add Region Add Chunk Event
		//KingdomsEventRegionAddedChunk event =
		
		RegionMapColls.get().setRegionAt(chunk, newRegion);
		//TODO remove chunk from the board if it was claimed
		
		String chunkCoords = "[" + chunk.getChunkX() + "," + chunk.getChunkZ() + "," + chunk.getWorld() + "]";
		String msg = "Added Chunk: " + chunkCoords + " to region: " + newRegion.getName();
		usender.sendMessage(msg);
		Factions.get().log(msg);
	}
}
