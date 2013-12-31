package com.github.mcfeudalages.kingdoms.event;

import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;

import com.github.mcfeudalages.kingdoms.entity.Region;
import com.massivecraft.factions.event.FactionsEventAbstractSender;

public class KingdomsEventRegionDestroy extends FactionsEventAbstractSender {
   
	private static final HandlerList handlers = new HandlerList();
    @Override 
    public HandlerList getHandlers() { return handlers; }
    public static HandlerList getHandlerList() { return handlers; }
    
    private final String regionID;
    public String getRegionID() { return regionID; }
    
    private final Region region;
    public Region getRegion() { return region; }
    
    public KingdomsEventRegionDestroy(CommandSender sender, Region region) {
    	super(sender);
    	this.region = region;
    	this.regionID = region.getId();
    }
}
