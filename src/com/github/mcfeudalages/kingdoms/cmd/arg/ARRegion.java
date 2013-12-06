package com.github.mcfeudalages.kingdoms.cmd.arg;

import org.bukkit.command.CommandSender;

import com.github.mcfeudalages.kingdoms.entity.Region;
import com.github.mcfeudalages.kingdoms.entity.RegionColl;
import com.github.mcfeudalages.kingdoms.entity.RegionColls;
import com.massivecraft.mcore.cmd.arg.ArgReaderAbstract;
import com.massivecraft.mcore.cmd.arg.ArgResult;
import com.massivecraft.mcore.util.Txt;

public class ARRegion extends ArgReaderAbstract<Region>{
	
	public static ARRegion get(Object universe) { return new ARRegion(RegionColls.get().get(universe)); }
	public ARRegion(RegionColl coll) {
		this.coll = coll;
	}
	
	private final RegionColl coll;
	public RegionColl getColl() { return this.coll; }

	@Override
	public ArgResult<Region> read(String arg, CommandSender sender) {
		ArgResult<Region> result = new ArgResult<Region>();
		
		result.setResult(this.getColl().getByName(arg));
		if(result.hasResult()) return result;
		
		result.setResult(this.getColl().getBestNameMatch(arg));
		if(result.hasResult()) return result;
		
		result.setErrors(Txt.parse("<b>No Region matching \"<p>%s<b>\".", arg));
		return result;
	}

}
