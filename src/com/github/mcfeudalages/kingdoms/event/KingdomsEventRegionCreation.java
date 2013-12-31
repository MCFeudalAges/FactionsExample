package com.github.mcfeudalages.kingdoms.event;

import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;

import com.massivecraft.factions.event.FactionsEventAbstractSender;

public class KingdomsEventRegionCreation extends FactionsEventAbstractSender {
	private static final HandlerList handlers = new HandlerList();
	@Override
	public HandlerList getHandlers() { return handlers; }
	public static HandlerList getHandlerList() { return handlers; }
	
	private final String universe;
	public final String getUniverse() { return this.universe; }
	
	private final String regionId;
	public final String getRegionID() { return this.regionId; }
	
	private final String regionName;
	public final String getRegionName() { return regionName; }
	
	public KingdomsEventRegionCreation(CommandSender sender, String universe, String regionId, String newName) {
		super(sender);
		this.universe = universe;
		this.regionId = regionId;
		this.regionName = newName;
	}
}
