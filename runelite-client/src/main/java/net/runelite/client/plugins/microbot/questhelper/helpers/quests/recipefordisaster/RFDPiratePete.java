/*
 * Copyright (c) 2020, Zoinkwiz
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.runelite.client.plugins.microbot.questhelper.helpers.quests.recipefordisaster;

import net.runelite.client.plugins.microbot.questhelper.bank.banktab.BankSlotIcons;
import net.runelite.client.plugins.microbot.questhelper.panel.PanelDetails;
import net.runelite.client.plugins.microbot.questhelper.questhelpers.BasicQuestHelper;
import net.runelite.client.plugins.microbot.questhelper.questinfo.QuestHelperQuest;
import net.runelite.client.plugins.microbot.questhelper.questinfo.QuestVarbits;
import net.runelite.client.plugins.microbot.questhelper.requirements.ComplexRequirement;
import net.runelite.client.plugins.microbot.questhelper.requirements.Requirement;
import net.runelite.client.plugins.microbot.questhelper.requirements.conditional.Conditions;
import net.runelite.client.plugins.microbot.questhelper.requirements.item.ItemRequirement;
import net.runelite.client.plugins.microbot.questhelper.requirements.player.SkillRequirement;
import net.runelite.client.plugins.microbot.questhelper.requirements.player.WeightRequirement;
import net.runelite.client.plugins.microbot.questhelper.requirements.quest.QuestRequirement;
import net.runelite.client.plugins.microbot.questhelper.requirements.util.LogicType;
import net.runelite.client.plugins.microbot.questhelper.requirements.util.Operation;
import net.runelite.client.plugins.microbot.questhelper.requirements.var.VarbitRequirement;
import net.runelite.client.plugins.microbot.questhelper.requirements.zone.Zone;
import net.runelite.client.plugins.microbot.questhelper.requirements.zone.ZoneRequirement;
import net.runelite.client.plugins.microbot.questhelper.rewards.ExperienceReward;
import net.runelite.client.plugins.microbot.questhelper.rewards.ItemReward;
import net.runelite.client.plugins.microbot.questhelper.rewards.QuestPointReward;
import net.runelite.client.plugins.microbot.questhelper.rewards.UnlockReward;
import net.runelite.client.plugins.microbot.questhelper.steps.*;
import net.runelite.api.Client;
import net.runelite.api.QuestState;
import net.runelite.api.Skill;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.gameval.ItemID;
import net.runelite.api.gameval.NpcID;
import net.runelite.api.gameval.ObjectID;

import java.util.*;

public class RFDPiratePete extends BasicQuestHelper
{
	ItemRequirement combatGear, pestleHighlighted, rawCodHighlighted, knifeHighlighted, breadHighlighted, divingAparatus, divingHelmet, fishBowl, bronzeWire3, needle, fishCake,
		fishCakeHighlighted, rocks5, mudskipperHide5, crabMeat, kelp, groundKelpHighlighted, groundCrabMeatHighlighted, kelpHighlighted, crabMeatHighlighted,
		rawFishCake, groundCod, breadcrumbs;

	WeightRequirement canSwim;

	Requirement inDiningRoom, askedCookOptions, inUnderWater, hasEnoughRocks, hasCrabMeat, hasKelp, walkingUnderwater;

	QuestStep enterDiningRoom, inspectPete, enterKitchen, usePestleOnCod, useKnifeOnBread, talkToMurphy, talkToMurphyAgain, goDiving,
		pickKelp, talkToNung, pickUpRocks, enterCave, killMudksippers5, returnToNung, talkToNungAgain, giveNungWire, killCrab, grindKelp, grindCrab, climbAnchor,
		talkToCookAgain, useCrabOnKelp, cookCake, enterDiningRoomAgain, useCakeOnPete, goDivingAgain, pickUpRocksAgain;

	AskAboutFishCake talkToCook;

	//Zones
	Zone diningRoom, underwater;

	@Override
	public Map<Integer, QuestStep> loadSteps()
	{
		initializeRequirements();
		setupConditions();
		setupSteps();
		Map<Integer, QuestStep> steps = new HashMap<>();

		ConditionalStep goInspectPete = new ConditionalStep(this, enterDiningRoom);
		goInspectPete.addStep(inDiningRoom, inspectPete);
		steps.put(0, goInspectPete);

		ConditionalStep goTalkToCook = new ConditionalStep(this, talkToCook);
		goTalkToCook.addStep(askedCookOptions, talkToMurphy);
		steps.put(10, goTalkToCook);
		steps.put(20, goTalkToCook);

		steps.put(30, talkToMurphyAgain);

		ConditionalStep goTalkToNung = new ConditionalStep(this, goDiving);
		goTalkToNung.addStep(new Conditions(hasKelp, inUnderWater), talkToNung);
		goTalkToNung.addStep(inUnderWater, pickKelp);
		steps.put(40, goTalkToNung);

		ConditionalStep goGetHides = new ConditionalStep(this, goDiving);
		goGetHides.addStep(new Conditions(hasKelp, inUnderWater, mudskipperHide5), returnToNung);
		goGetHides.addStep(new Conditions(hasKelp, inUnderWater, walkingUnderwater), killMudksippers5);
		goGetHides.addStep(new Conditions(hasKelp, inUnderWater, hasEnoughRocks), enterCave);
		goGetHides.addStep(new Conditions(hasKelp, inUnderWater), pickUpRocks);
		goGetHides.addStep(inUnderWater, pickKelp);
		steps.put(50, goGetHides);

		ConditionalStep goTalkToNungAgain = new ConditionalStep(this, goDiving);
		goTalkToNungAgain.addStep(new Conditions(hasKelp, inUnderWater), talkToNungAgain);
		goTalkToNungAgain.addStep(inUnderWater, pickKelp);
		steps.put(60, goTalkToNungAgain);

		ConditionalStep goBringNungWire = new ConditionalStep(this, goDiving);
		goBringNungWire.addStep(new Conditions(hasKelp, inUnderWater), giveNungWire);
		goBringNungWire.addStep(inUnderWater, pickKelp);
		steps.put(70, goBringNungWire);

		ConditionalStep goLearnHowToMakeFishCake = new ConditionalStep(this, goDivingAgain);
		goLearnHowToMakeFishCake.addStep(new Conditions(fishCake, inDiningRoom), useCakeOnPete);
		goLearnHowToMakeFishCake.addStep(new Conditions(fishCake.alsoCheckBank(questBank)), enterDiningRoomAgain);
		goLearnHowToMakeFishCake.addStep(new Conditions(rawFishCake.alsoCheckBank(questBank)), cookCake);
		goLearnHowToMakeFishCake.addStep(new Conditions(hasKelp, hasCrabMeat, inUnderWater), climbAnchor);
		goLearnHowToMakeFishCake.addStep(new Conditions(hasKelp, inUnderWater, hasEnoughRocks), killCrab);
		goLearnHowToMakeFishCake.addStep(new Conditions(hasKelp, inUnderWater), pickUpRocksAgain);
		goLearnHowToMakeFishCake.addStep(new Conditions(groundKelpHighlighted, groundCrabMeatHighlighted, groundCod, breadcrumbs), talkToCookAgain);
		goLearnHowToMakeFishCake.addStep(new Conditions(groundKelpHighlighted, groundCrabMeatHighlighted, groundCod), useKnifeOnBread);
		goLearnHowToMakeFishCake.addStep(new Conditions(groundKelpHighlighted, groundCrabMeatHighlighted), usePestleOnCod);
		goLearnHowToMakeFishCake.addStep(new Conditions(hasKelp, groundCrabMeatHighlighted), grindKelp);
		goLearnHowToMakeFishCake.addStep(new Conditions(hasKelp, hasCrabMeat), grindCrab);
		goLearnHowToMakeFishCake.addStep(inUnderWater, pickKelp);
		steps.put(80, goLearnHowToMakeFishCake);
		steps.put(90, goLearnHowToMakeFishCake);

		ConditionalStep savePete = new ConditionalStep(this, useCrabOnKelp);
		savePete.addStep(new Conditions(fishCake, inDiningRoom), useCakeOnPete);
		savePete.addStep(new Conditions(fishCake.alsoCheckBank(questBank)), enterDiningRoomAgain);
		savePete.addStep(new Conditions(rawFishCake), cookCake);
		steps.put(100, savePete);

		return steps;
	}

	@Override
	protected void setupRequirements()
	{
		canSwim = new WeightRequirement("Weight less than 27kg", 26, Operation.LESS_EQUAL);

		pestleHighlighted = new ItemRequirement("Pestle and mortar", ItemID.PESTLE_AND_MORTAR).isNotConsumed();
		pestleHighlighted.setHighlightInInventory(true);
		rawCodHighlighted = new ItemRequirement("Raw cod (more if you burn cake)", ItemID.RAW_COD);
		rawCodHighlighted.setHighlightInInventory(true);
		knifeHighlighted = new ItemRequirement("Knife", ItemID.KNIFE).isNotConsumed();
		knifeHighlighted.setHighlightInInventory(true);
		breadHighlighted = new ItemRequirement("Bread (more if you burn cake)", ItemID.BREAD);
		breadHighlighted.setTooltip("You can make this by using a knife on bread");
		breadHighlighted.setHighlightInInventory(true);
		divingAparatus = new ItemRequirement("Diving apparatus", ItemID.HUNDRED_PIRATE_DIVING_BACKPACK, 1, true).isNotConsumed();
		divingHelmet = new ItemRequirement("Fishbowl helmet", ItemID.HUNDRED_PIRATE_DIVING_HELMET, 1, true).isNotConsumed();
		fishBowl = new ItemRequirement("Empty fishbowl", ItemID.FISHBOWL_EMPTY);
		bronzeWire3 = new ItemRequirement("Bronze wire", ItemID.BRONZECRAFTWIRE, 3);
		needle = new ItemRequirement("Needle", ItemID.NEEDLE).isNotConsumed();
		fishCake = new ItemRequirement("Cooked fishcake", ItemID.HUNDRED_PIRATE_FISHCAKE);
		fishCakeHighlighted = new ItemRequirement("Cooked fishcake", ItemID.HUNDRED_PIRATE_FISHCAKE);
		fishCakeHighlighted.setHighlightInInventory(true);
		rocks5 = new ItemRequirement("Rock", ItemID.HUNDRED_PIRATE_ROCK, 5);
		mudskipperHide5 = new ItemRequirement("Mudskipper hide", ItemID.HUNDRED_PIRATE_MUDSKIPPER_HIDE, 5);
		kelp = new ItemRequirement("Kelp", ItemID.HUNDRED_PIRATE_KELP);
		crabMeat = new ItemRequirement("Crab meat", ItemID.HUNDRED_PIRATE_GIANT_CRAB_MEAT);
		crabMeat.addAlternates(ItemID.CERT_HUNDRED_PIRATE_GIANT_CRAB_MEAT);
		kelpHighlighted = new ItemRequirement("Kelp", ItemID.HUNDRED_PIRATE_KELP);
		kelpHighlighted.setHighlightInInventory(true);
		crabMeatHighlighted = new ItemRequirement("Crab meat", ItemID.HUNDRED_PIRATE_GIANT_CRAB_MEAT);
		crabMeatHighlighted.addAlternates(ItemID.CERT_HUNDRED_PIRATE_GIANT_CRAB_MEAT);
		crabMeatHighlighted.setHighlightInInventory(true);
		groundCrabMeatHighlighted = new ItemRequirement("Ground crab meat", ItemID.HUNDRED_PIRATE_GROUND_GIANT_CRAB_MEAT);
		groundCrabMeatHighlighted.setTooltip("You will need to kill another underwater crab and grind its meat");
		groundCrabMeatHighlighted.setHighlightInInventory(true);
		groundKelpHighlighted = new ItemRequirement("Ground kelp", ItemID.HUNDRED_PIRATE_GROUND_KELP);
		groundKelpHighlighted.setTooltip("You will need to go underwater with Murphy and pick more kelp to grind");
		groundKelpHighlighted.setHighlightInInventory(true);
		rawFishCake = new ItemRequirement("Raw fishcake", ItemID.HUNDRED_PIRATE_RAW_FISHCAKE);
		groundCod = new ItemRequirement("Ground cod", ItemID.HUNDRED_PIRATE_GROUND_COD);
		groundCod.setTooltip("You can make this by use a pestle and mortar on a raw cod");
		breadcrumbs = new ItemRequirement("Breadcrumbs", ItemID.HUNDRED_PIRATE_BREADCRUMBS);

		combatGear = new ItemRequirement("Combat gear", -1, -1);
		combatGear.setDisplayItemId(BankSlotIcons.getCombatGear());
	}

	@Override
	protected void setupZones()
	{
		diningRoom = new Zone(new WorldPoint(1856, 5313, 0), new WorldPoint(1870, 5333, 0));
		underwater = new Zone(new WorldPoint(2944, 9472, 1), new WorldPoint(3007, 9534, 1));
	}

	public void setupConditions()
	{
		inDiningRoom = new ZoneRequirement(diningRoom);
		walkingUnderwater = new VarbitRequirement(1871, 1);

		askedCookOptions = new Conditions(
			new VarbitRequirement(1873, 1),
			new VarbitRequirement(1876, 1),
			new VarbitRequirement(1877, 1));

		inUnderWater = new ZoneRequirement(underwater);

		hasEnoughRocks = new VarbitRequirement(1869, 5);

		hasCrabMeat = new Conditions(LogicType.OR, crabMeat, groundCrabMeatHighlighted);
		hasKelp = new Conditions(LogicType.OR, kelp, groundKelpHighlighted);

		// 1852 = number of people saved
		// Talked to cook through base dialog: 1854 0->1

		// 1860 = 1->0 is Pirate Pete gone from dining room
		// 1872 = 0->1 when first going underwater
		// 1875 0->5, Given hides
	}

	public void setupSteps()
	{
		enterDiningRoom = new ObjectStep(this, ObjectID.HUNDRED_LUMBRIDGE_DOUBLEDOORL, new WorldPoint(3213, 3221, 0), "Go inspect Pirate Pete.");
		inspectPete = new ObjectStep(this, ObjectID.HUNDRED_PIRATE_BASE, new WorldPoint(1862, 5323, 0), "Inspect Pirate Pete.");
		inspectPete.addSubSteps(enterDiningRoom);

		enterKitchen = new ObjectStep(this, ObjectID.HUNDRED_PORTAL_DOOR1, new WorldPoint(1861, 5316, 0), "Talk to the Lumbridge Cook.");
		talkToCook = new AskAboutFishCake(this);
		talkToCook.addSubSteps(enterKitchen);
		usePestleOnCod = new DetailedQuestStep(this, "Use a pestle and mortar on a raw cod.", pestleHighlighted, rawCodHighlighted);
		useKnifeOnBread = new DetailedQuestStep(this, "Use a knife on some bread and make BREADCRUMBS.", knifeHighlighted, breadHighlighted);
		talkToMurphy = new NpcStep(this, NpcID.MURPHY, new WorldPoint(2664, 3160, 0), "Talk to Murphy in Port Khazard.", fishBowl);
		talkToMurphy.addDialogStep("Talk about Recipe for Disaster.");
		talkToMurphyAgain = new NpcStep(this, NpcID.MURPHY, new WorldPoint(2664, 3160, 0), "Talk to Murphy again.", fishBowl);
		talkToMurphyAgain.addDialogStep("Talk about Recipe for Disaster.");
		goDiving = new NpcStep(this, NpcID.MURPHY, new WorldPoint(2664, 3160, 0), "Talk to Murphy again to go diving.", divingAparatus, divingHelmet, bronzeWire3, needle, canSwim);
		goDiving.addDialogSteps("Talk about Recipe for Disaster.", "Yes, Let's go diving.");
		pickKelp = new ObjectStep(this, ObjectID.KELP_PICKINGPOINT, new WorldPoint(2966, 9490, 1), "Pick some kelp. Get 3 or so in case you burn a fishcake or two.");
		talkToNung = new NpcStep(this, NpcID._100_PIRATE_MOGRE_NUNG, new WorldPoint(2971, 9513, 1), "Talk to Nung in the north of the area.");
		pickUpRocks = new DetailedQuestStep(this, new WorldPoint(2950, 9511, 1), "Pick up 5 rocks in the west of the area.", rocks5);
		enterCave = new ObjectStep(this, ObjectID.SEABED2_CAVEWALL_MUDSKIPPER_CAVEENTRANCER, new WorldPoint(2950, 9516, 1), "Enter the underwater cave entrance.");
		killMudksippers5 = new NpcStep(this, NpcID.HUNDRED_PIRATE_GIANT_MUDSKIPPER, new WorldPoint(2951, 9526, 1), "Kill mudskippers for 5 hides.", true, mudskipperHide5);
		((NpcStep) (killMudksippers5)).addAlternateNpcs(NpcID.HUNDRED_PIRATE_GIANT_MUDSKIPPER_2);
		returnToNung = new NpcStep(this, NpcID._100_PIRATE_MOGRE_NUNG, new WorldPoint(2971, 9513, 1), "Bring the hides to Nung.", mudskipperHide5);
		talkToNungAgain = new NpcStep(this, NpcID._100_PIRATE_MOGRE_NUNG, new WorldPoint(2971, 9513, 1), "Talk to Nung again.");
		returnToNung.addSubSteps(talkToNungAgain);

		giveNungWire = new NpcStep(this, NpcID._100_PIRATE_MOGRE_NUNG, new WorldPoint(2971, 9513, 1), "Bring bronze wire and a needle to Nung.", bronzeWire3, needle);
		goDivingAgain = new NpcStep(this, NpcID.MURPHY, new WorldPoint(2664, 3160, 0), "Talk to Murphy again to go diving.", divingAparatus, divingHelmet, canSwim);
		goDiving.addDialogSteps("Talk about Recipe for Disaster.", "Yes, Let's go diving.");
		pickUpRocksAgain = new DetailedQuestStep(this, new WorldPoint(2950, 9511, 1), "Pick up 5 rocks so you can kill the crabs.", rocks5);
		killCrab = new NpcStep(this, NpcID.HUNDRED_PIRATE_GIANT_CRAB_2, new WorldPoint(2977, 9518, 1), "Kill the crabs for some crab meat. Get 2-3 to be safe.", true, crabMeat);
		killCrab.addSubSteps(pickUpRocksAgain);
		grindKelp = new DetailedQuestStep(this, "Use a pestle and mortar on the kelp.", pestleHighlighted, kelpHighlighted);
		grindCrab = new DetailedQuestStep(this, "Use a pestle and mortar on the crab.", pestleHighlighted, crabMeatHighlighted);
		climbAnchor = new ObjectStep(this, ObjectID.ANCHOR_MIDDLE, new WorldPoint(2963, 9477, 1), "Climb the anchor to the south to return to the surface.");
		talkToCookAgain = new NpcStep(this, NpcID.POH_SERVANT_COOK_WOMAN, new WorldPoint(3209, 3215, 0),
			"Talk to the Lumbridge Cook about Pirate Pete again.", groundCod, groundCrabMeatHighlighted, groundKelpHighlighted, breadcrumbs);
		talkToCookAgain.addDialogStep("Protecting the Pirate");
		useCrabOnKelp = new DetailedQuestStep(this, "Use the ingredients together to make the cake.", groundCrabMeatHighlighted, groundKelpHighlighted, groundCod, breadcrumbs);
		cookCake = new DetailedQuestStep(this, "Cook the fishcake. It's possible to burn it.", rawFishCake);
		enterDiningRoomAgain = new ObjectStep(this, ObjectID.HUNDRED_LUMBRIDGE_DOOR, new WorldPoint(3207, 3217, 0), "Go give the fishcake to Pirate Pete to finish the quest.", fishCake);
		useCakeOnPete = new ObjectStep(this, ObjectID.HUNDRED_PIRATE_BASE, new WorldPoint(1862, 5323, 0), "Give the fishcake to Pirate Pete to finish the quest. BE CAREFUL NOT TO EAT IT.", fishCakeHighlighted);
		useCakeOnPete.addIcon(ItemID.HUNDRED_PIRATE_FISHCAKE);
		useCakeOnPete.addSubSteps(enterDiningRoomAgain);
	}

	@Override
	public List<ItemRequirement> getItemRequirements()
	{
		return Arrays.asList(fishBowl, needle, bronzeWire3, pestleHighlighted, rawCodHighlighted, breadHighlighted, knifeHighlighted);
	}

	@Override
	public List<ItemRequirement> getItemRecommended()
	{
		ArrayList<ItemRequirement> req = new ArrayList<>();
		req.add(combatGear);
		req.add(new ItemRequirement("Teleport to Khazard", ItemID.LUNAR_TABLET_KHAZARD_TELEPORT));
		req.add(new ItemRequirement("Teleport to Lumbridge", ItemID.POH_TABLET_LUMBRIDGETELEPORT));

		return req;
	}

	@Override
	public List<String> getCombatRequirements()
	{
		return Arrays.asList("5 Mudskippers (level 30/31)", "Crab (level 21/23)");
	}

	@Override
	public List<Requirement> getGeneralRequirements()
	{
		ArrayList<Requirement> generalReqs = new ArrayList<>();
		generalReqs.add(new SkillRequirement(Skill.COOKING, 31));
		if (questHelperPlugin.getPlayerStateManager().getAccountType().isAnyIronman())
		{
			generalReqs.add(new ComplexRequirement(LogicType.OR, "42 Crafting or started Rum Deal for a fishbowl",
				new SkillRequirement(Skill.CRAFTING, 42, true),
				new QuestRequirement(QuestHelperQuest.RUM_DEAL, QuestState.IN_PROGRESS)));
		}
		return generalReqs;
	}

	@Override
	public QuestPointReward getQuestPointReward()
	{
		return new QuestPointReward(1);
	}

	@Override
	public List<ExperienceReward> getExperienceRewards()
	{
		return Arrays.asList(
				new ExperienceReward(Skill.COOKING, 1000),
				new ExperienceReward(Skill.CRAFTING, 1000),
				new ExperienceReward(Skill.FISHING, 1000),
				new ExperienceReward(Skill.SMITHING, 1000));
	}

	@Override
	public List<ItemReward> getItemRewards()
	{
		return Collections.singletonList(new ItemReward("Diving Apparatus", ItemID.HUNDRED_PIRATE_DIVING_BACKPACK, 1));
	}

	@Override
	public List<UnlockReward> getUnlockRewards()
	{
		return Arrays.asList(
				new UnlockReward("Access to the Mogre Camp"),
				new UnlockReward("Increased access to the Culinaromancer's Chest"));
	}

	@Override
	public List<PanelDetails> getPanels()
	{
		List<PanelDetails> allSteps = new ArrayList<>();
		allSteps.add(new PanelDetails("Starting off", Arrays.asList(inspectPete, talkToCook, talkToMurphy, talkToMurphyAgain), fishBowl));
		allSteps.add(new PanelDetails("Get Crab and Kelp", Arrays.asList(goDiving, pickKelp, talkToNung, pickUpRocks, enterCave, killMudksippers5, returnToNung, giveNungWire, killCrab, climbAnchor), divingHelmet, divingAparatus, bronzeWire3, needle));
		allSteps.add(new PanelDetails("Saving Pete", Arrays.asList(grindCrab, grindKelp, usePestleOnCod, useKnifeOnBread, talkToCookAgain, useCrabOnKelp, cookCake, useCakeOnPete), pestleHighlighted, knifeHighlighted, rawCodHighlighted, breadHighlighted, crabMeat, kelp));
		return allSteps;
	}

	@Override
	public QuestState getState(Client client)
	{
		int questState = client.getVarbitValue(QuestVarbits.QUEST_RECIPE_FOR_DISASTER_PIRATE_PETE.getId());
		if (questState == 0)
		{
			return QuestState.NOT_STARTED;
		}

		if (questState < 110)
		{
			return QuestState.IN_PROGRESS;
		}

		return QuestState.FINISHED;
	}

	@Override
	public boolean isCompleted()
	{
		return (client.getVarbitValue(1895) >= 110 || client.getVarbitValue(QuestVarbits.QUEST_RECIPE_FOR_DISASTER.getId()) < 3);
	}
}
