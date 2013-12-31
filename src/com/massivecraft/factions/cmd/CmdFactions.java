package com.massivecraft.factions.cmd;

import java.util.List;

import com.github.mcfeudalages.kingdoms.cmd.CmdKingdomsRegionAddChunk;
import com.github.mcfeudalages.kingdoms.cmd.CmdKingdomsRegionClaim;
import com.github.mcfeudalages.kingdoms.cmd.CmdKingdomsRegionCreate;
import com.github.mcfeudalages.kingdoms.cmd.CmdKingdomsRegionDestroy;
import com.github.mcfeudalages.kingdoms.cmd.CmdKingdomsRegionList;
import com.github.mcfeudalages.kingdoms.cmd.CmdKingdomsRegionRemoveChunk;
import com.github.mcfeudalages.kingdoms.cmd.CmdKingdomsRegionUnclaim;
import com.massivecraft.factions.Factions;
import com.massivecraft.factions.Perm;
import com.massivecraft.factions.entity.MConf;
import com.massivecraft.mcore.cmd.HelpCommand;
import com.massivecraft.mcore.cmd.VersionCommand;

public class CmdFactions extends FCommand
{
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	//Factions
	public CmdFactionsList cmdFactionsList = new CmdFactionsList();
	public CmdFactionsFaction cmdFactionsFaction = new CmdFactionsFaction();
	public CmdFactionsPlayer cmdFactionsPlayer = new CmdFactionsPlayer();
	public CmdFactionsJoin cmdFactionsJoin = new CmdFactionsJoin();
	public CmdFactionsLeave cmdFactionsLeave = new CmdFactionsLeave();
	public CmdFactionsHome cmdFactionsHome = new CmdFactionsHome();
	public CmdFactionsMap cmdFactionsMap = new CmdFactionsMap();
	public CmdFactionsCreate cmdFactionsCreate = new CmdFactionsCreate();
	public CmdFactionsName cmdFactionsName = new CmdFactionsName();
	public CmdFactionsDescription cmdFactionsDescription = new CmdFactionsDescription();
	public CmdFactionsSethome cmdFactionsSethome = new CmdFactionsSethome();
	public CmdFactionsOpen cmdFactionsOpen = new CmdFactionsOpen();
	public CmdFactionsInvite cmdFactionsInvite = new CmdFactionsInvite();
	public CmdFactionsKick cmdFactionsKick = new CmdFactionsKick();
	public CmdFactionsTitle cmdFactionsTitle = new CmdFactionsTitle();
	public CmdFactionsPromote cmdFactionsPromote = new CmdFactionsPromote();
	public CmdFactionsDemote cmdFactionsDemote = new CmdFactionsDemote();
	public CmdFactionsOfficer cmdFactionsOfficer = new CmdFactionsOfficer();
	public CmdFactionsLeader cmdFactionsLeader = new CmdFactionsLeader();
	public CmdFactionsMoney cmdFactionsMoney = new CmdFactionsMoney();
	public CmdFactionsSeeChunk cmdFactionsSeeChunk = new CmdFactionsSeeChunk();
	public CmdFactionsClaim cmdFactionsClaim = new CmdFactionsClaim();
	public CmdFactionsAutoClaim cmdFactionsAutoClaim = new CmdFactionsAutoClaim();
	public CmdFactionsUnclaim cmdFactionsUnclaim = new CmdFactionsUnclaim();
	public CmdFactionsUnclaimall cmdFactionsUnclaimall = new CmdFactionsUnclaimall();
	public CmdFactionsAccess cmdFactionsAccess = new CmdFactionsAccess();
	public CmdFactionsRelationAlly cmdFactionsRelationAlly = new CmdFactionsRelationAlly();
	public CmdFactionsRelationTruce cmdFactionsRelationTruce = new CmdFactionsRelationTruce();
	public CmdFactionsRelationNeutral cmdFactionsRelationNeutral = new CmdFactionsRelationNeutral();
	public CmdFactionsRelationEnemy cmdFactionsRelationEnemy = new CmdFactionsRelationEnemy();
	public CmdFactionsPerm cmdFactionsPerm = new CmdFactionsPerm();
	public CmdFactionsFlag cmdFactionsFlag = new CmdFactionsFlag();
	public CmdFactionsDisband cmdFactionsDisband = new CmdFactionsDisband();
	public CmdFactionsAdmin cmdFactionsAdmin = new CmdFactionsAdmin();
	public CmdFactionsPowerBoost cmdFactionsPowerBoost = new CmdFactionsPowerBoost();
	public VersionCommand cmdFactionsVersion = new VersionCommand(Factions.get(), Perm.VERSION.node, "v", "version");
	
	//FAKingdoms
	public CmdKingdomsRegionCreate cmdKingdomsRegionCreate = new CmdKingdomsRegionCreate();
	public CmdKingdomsRegionDestroy cmdKingdomsRegionDestroy = new CmdKingdomsRegionDestroy();
	public CmdKingdomsRegionList cmdKingdomsRegionList = new CmdKingdomsRegionList();
	public CmdKingdomsRegionAddChunk cmdKingdomsRegionAddChunk = new CmdKingdomsRegionAddChunk();
	public CmdKingdomsRegionRemoveChunk cmdKingdomsRegionRemoveChunk = new CmdKingdomsRegionRemoveChunk();
	public CmdKingdomsRegionClaim cmdKingdomsRegionClaim = new CmdKingdomsRegionClaim();
	public CmdKingdomsRegionUnclaim cmdKingdomsRegionUnclaim = new CmdKingdomsRegionUnclaim();
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdFactions()
	{
		// Add SubCommands
		//Factions
		this.addSubCommand(HelpCommand.get());
		this.addSubCommand(this.cmdFactionsList);
		this.addSubCommand(this.cmdFactionsFaction);
		this.addSubCommand(this.cmdFactionsPlayer);
		this.addSubCommand(this.cmdFactionsJoin);
		this.addSubCommand(this.cmdFactionsLeave);
		this.addSubCommand(this.cmdFactionsHome);
		this.addSubCommand(this.cmdFactionsMap);
		this.addSubCommand(this.cmdFactionsCreate);
		this.addSubCommand(this.cmdFactionsName);
		this.addSubCommand(this.cmdFactionsDescription);
		this.addSubCommand(this.cmdFactionsSethome);
		this.addSubCommand(this.cmdFactionsOpen);
		this.addSubCommand(this.cmdFactionsInvite);
		this.addSubCommand(this.cmdFactionsKick);
		this.addSubCommand(this.cmdFactionsTitle);
		this.addSubCommand(this.cmdFactionsPromote);
		this.addSubCommand(this.cmdFactionsDemote);
		this.addSubCommand(this.cmdFactionsOfficer);
		this.addSubCommand(this.cmdFactionsLeader);
		this.addSubCommand(this.cmdFactionsMoney);
		this.addSubCommand(this.cmdFactionsSeeChunk);
		this.addSubCommand(this.cmdFactionsClaim);
		this.addSubCommand(this.cmdFactionsAutoClaim);
		this.addSubCommand(this.cmdFactionsUnclaim);
		this.addSubCommand(this.cmdFactionsUnclaimall);
		this.addSubCommand(this.cmdFactionsAccess);
		this.addSubCommand(this.cmdFactionsRelationAlly);
		this.addSubCommand(this.cmdFactionsRelationTruce);
		this.addSubCommand(this.cmdFactionsRelationNeutral);
		this.addSubCommand(this.cmdFactionsRelationEnemy);
		this.addSubCommand(this.cmdFactionsPerm);
		this.addSubCommand(this.cmdFactionsFlag);
		this.addSubCommand(this.cmdFactionsDisband);
		this.addSubCommand(this.cmdFactionsAdmin);
		this.addSubCommand(this.cmdFactionsPowerBoost);
		this.addSubCommand(this.cmdFactionsVersion);
		
		//Kingdoms
		this.addSubCommand(this.cmdKingdomsRegionCreate);
		this.addSubCommand(this.cmdKingdomsRegionDestroy);
		this.addSubCommand(this.cmdKingdomsRegionList);
		this.addSubCommand(this.cmdKingdomsRegionAddChunk);
		this.addSubCommand(this.cmdKingdomsRegionRemoveChunk);
		this.addSubCommand(this.cmdKingdomsRegionClaim);
		this.addSubCommand(this.cmdKingdomsRegionUnclaim);
		
		// Misc
		this.setDesc("The faction base command");
		this.setHelp("This command contains all faction stuff.");
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public List<String> getAliases()
	{
		return MConf.get().aliasesF;
	}

}
