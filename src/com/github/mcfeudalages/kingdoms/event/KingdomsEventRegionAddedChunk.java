package com.github.mcfeudalages.kingdoms.event;

import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;

import com.github.mcfeudalages.kingdoms.entity.Region;
import com.massivecraft.factions.event.FactionsEventAbstractSender;
import com.massivecraft.mcore.ps.PS;

public class KingdomsEventRegionAddedChunk extends FactionsEventAbstractSender {
	private static final HandlerList handlers = new HandlerList();
	@Override
	public HandlerList getHandlers() { return handlers; }
	public HandlerList getHandlerList() { return handlers; }

	private final Region region;
	public Region getOldRegion() { return region; }

	private final String regionID;
	public String getOldRegionId() { return regionID; }

	private final PS chunk;
	public PS getChunk() { return chunk; }

	public KingdomsEventRegionAddedChunk(CommandSender sender, Region region, PS chunk) {
		super(sender);
		this.region = region;
		this.regionID = region.getId();
		this.chunk = chunk;
	}
}
