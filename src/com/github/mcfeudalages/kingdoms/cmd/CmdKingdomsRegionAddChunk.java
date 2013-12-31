package com.github.mcfeudalages.kingdoms.cmd;

import com.github.mcfeudalages.kingdoms.RegionAccess;
import com.github.mcfeudalages.kingdoms.cmd.arg.ARRegion;
import com.github.mcfeudalages.kingdoms.entity.Region;
import com.github.mcfeudalages.kingdoms.entity.RegionMapColls;
import com.github.mcfeudalages.kingdoms.event.KingdomsEventRegionAddedChunk;
import com.massivecraft.factions.Factions;
import com.massivecraft.factions.Perm;
import com.massivecraft.factions.cmd.FCommand;
import com.massivecraft.factions.cmd.req.ReqFactionsEnabled;
import com.massivecraft.factions.entity.UConf;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;
import com.massivecraft.mcore.cmd.req.ReqIsPlayer;
import com.massivecraft.mcore.ps.PS;

public class CmdKingdomsRegionAddChunk extends FCommand{
	public CmdKingdomsRegionAddChunk() {
		this.addAliases("raddchunk","rac");
		
		this.addRequiredArg("region");
		
		this.addRequirements(ReqFactionsEnabled.get());
		this.addRequirements(ReqIsPlayer.get());
		this.addRequirements(ReqHasPerm.get(Perm.RADDCHUNK.node));
	}
	
	@Override
	public void perform() {
		final Region forRegion = this.arg(0, ARRegion.get(usender));
		if(forRegion == null) return;
		
		this.addChunk(forRegion, PS.valueOf(me));
	}
	
	private void addChunk(Region newRegion, PS ps) {
		PS chunk = ps.getChunk(true);
		Region oldRegion = RegionMapColls.get().getRegionAt(chunk);
		
		if(newRegion == oldRegion) {
			msg("Chunk is already a part of the region %s.", newRegion.getName());
			return;
		}
		
		RegionAccess ra = RegionMapColls.get().getRegionAccessAt(chunk);
		if(ra != null) {
			if(!ra.getAssociatedRegionID().equals(UConf.get(me).regionIDNone)) {
				msg("Chunk is alreadly apart of another region");
				return;
			}
		}
		
		if(!chunk.getWorld().equals(newRegion.getWorld())) {
			msg("This chunk is not as the same world as the region");
			return;
		}
		
		//TODO Add check for non admin players
		
		KingdomsEventRegionAddedChunk event = new KingdomsEventRegionAddedChunk(sender, newRegion, chunk);
		event.run();
		if(event.isCancelled()) return;
		
		RegionMapColls.get().setRegionAt(chunk, newRegion);
		
		String chunkCoords = "[" + chunk.getChunkX() + "," + chunk.getChunkZ() + "," + chunk.getWorld() + "]";
		String msg = "Added Chunk: " + chunkCoords + " to region: " + newRegion.getName();
		usender.sendMessage(msg);
		Factions.get().log(msg);
	}
}
