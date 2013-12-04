package com.github.mcfeudalages.kingdoms.event;

import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;

import com.massivecraft.factions.event.FactionsEventAbstractSender;

public class KingdomsEventRegionCreation extends FactionsEventAbstractSender {
	public KingdomsEventRegionCreation(CommandSender sender, String string, String regionId, String newName) {
		super(sender);
		// TODO Auto-generated constructor stub
	}

	@Override
	public HandlerList getHandlers() {
		// TODO Auto-generated method stub
		return null;
	}

}
