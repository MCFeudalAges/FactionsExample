package com.github.mcfeudalages.kingdoms.event;

import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;

import com.github.mcfeudalages.kingdoms.entity.Region;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.event.FactionsEventAbstractSender;

public class KingdomsEventRegionUnclaim extends FactionsEventAbstractSender {
	private static final HandlerList handlers = new HandlerList();
	@Override
	public HandlerList getHandlers() { return handlers; }
	public HandlerList getHandlerList() { return handlers; }
	
	private final Faction oldFaction;
	public Faction getOldFaction() { return oldFaction; }
	
	private final String oldFactionID;
	public String getOldFactionID() { return oldFactionID; }
	
	private final Faction newFaction;
	public Faction getNewFaction() { return newFaction; }
	
	private final String newFactionID;
	public String getNewFactionID() { return newFactionID; }
	
	private final Region region;
	public Region getRegion() { return region; }
	
	public KingdomsEventRegionUnclaim(CommandSender sender, Region region, Faction oldFaction, Faction newFaction) {
		super(sender);
		this.region = region;
		this.oldFaction = oldFaction;
		this.oldFactionID = oldFaction.getId();
		this.newFaction = newFaction;
		this.newFactionID = newFaction.getId();
	}	

}
