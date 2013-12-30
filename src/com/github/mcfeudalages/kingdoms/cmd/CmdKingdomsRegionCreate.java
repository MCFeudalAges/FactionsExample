package com.github.mcfeudalages.kingdoms.cmd;

import java.util.ArrayList;

import com.github.mcfeudalages.kingdoms.entity.Region;
import com.github.mcfeudalages.kingdoms.entity.RegionColl;
import com.github.mcfeudalages.kingdoms.entity.RegionColls;
import com.github.mcfeudalages.kingdoms.event.KingdomsEventRegionCreation;
import com.massivecraft.factions.Factions;
import com.massivecraft.factions.Perm;
import com.massivecraft.factions.cmd.FCommand;
import com.massivecraft.factions.cmd.req.ReqFactionsEnabled;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;
import com.massivecraft.mcore.store.MStore;

public class CmdKingdomsRegionCreate extends FCommand{
	public CmdKingdomsRegionCreate() {
		this.addAliases("rcreate", "rcr");
		this.addRequiredArg("name");
		this.addRequiredArg("world");
		//this.addOptionalArg(arg, def);
		//TODO add requirements 
		this.addRequirements(ReqFactionsEnabled.get());
		this.addRequirements(ReqHasPerm.get(Perm.RCREATE.node));
	}
	
	@Override
	public void perform() {
		String newName = this.arg(0);
		String newWorld = this.arg(1);
		
		Factions.get().log("Getting Region Coll");
		RegionColl coll = RegionColls.get().get(usender);
		
		if (coll.isNameTaken(newName))
		{
			msg("<b>That name is already in use.");
			return;
		}
		
		ArrayList<String> nameValidationErrors = coll.validateName(newName);
		if (nameValidationErrors.size() > 0)
		{
			sendMessage(nameValidationErrors);
			return;
		}
		
		String regionId = MStore.createId();
		Factions.get().log("Creating Event for region creation");
		KingdomsEventRegionCreation createEvent = new KingdomsEventRegionCreation(sender, coll.getUniverse(), regionId, newName);
		//TODO causes NullPointer Exeption need to fix
		// createEvent.run();
		if (createEvent.isCancelled()) return;
		
		Factions.get().log("Creating region");
		Region region = coll.create(regionId);
		region.setName(newName);
		region.setWorld(newWorld);
		
		String msg = "Created Region: " + region.getName() + " for world " + region.getWorld();
		usender.sendMessage(msg);
		Factions.get().log(msg);
	}
}
