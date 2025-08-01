/*
 * Copyright (c) 2021, Zoinkwiz
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
package net.runelite.client.plugins.microbot.questhelper.helpers.achievementdiaries.karamja;

import net.runelite.client.plugins.microbot.questhelper.collections.ItemCollections;
import net.runelite.client.plugins.microbot.questhelper.panel.PanelDetails;
import net.runelite.client.plugins.microbot.questhelper.questhelpers.ComplexStateQuestHelper;
import net.runelite.client.plugins.microbot.questhelper.requirements.Requirement;
import net.runelite.client.plugins.microbot.questhelper.requirements.conditional.Conditions;
import net.runelite.client.plugins.microbot.questhelper.requirements.item.ItemRequirement;
import net.runelite.client.plugins.microbot.questhelper.requirements.player.SkillRequirement;
import net.runelite.client.plugins.microbot.questhelper.requirements.util.LogicType;
import net.runelite.client.plugins.microbot.questhelper.requirements.var.VarplayerRequirement;
import net.runelite.client.plugins.microbot.questhelper.requirements.zone.Zone;
import net.runelite.client.plugins.microbot.questhelper.requirements.zone.ZoneRequirement;
import net.runelite.client.plugins.microbot.questhelper.rewards.ItemReward;
import net.runelite.client.plugins.microbot.questhelper.rewards.UnlockReward;
import net.runelite.client.plugins.microbot.questhelper.steps.*;
import net.runelite.api.Skill;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.gameval.ItemID;
import net.runelite.api.gameval.NpcID;
import net.runelite.api.gameval.ObjectID;
import net.runelite.api.gameval.VarPlayerID;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class KaramjaElite extends ComplexStateQuestHelper
{
	// Items required
	ItemRequirement natureTiaraOrAbyss, pureEssence, fireCapeOrInfernal, palmTreeSapling, antidotePlusPlus,
		zulrahScales, calquatSapling;

	ItemRequirement rake, spade;

	Requirement notCraftedRunes, notEquippedCape, notCheckedPalm, notCheckedCalquat, notMadePotion;

	Requirement farming72, herblore87, runecraft91;

	Requirement inNatureAltar, inHorse;

	Zone natureAltar, horse;

	QuestStep enterNatureAltar, craftRunes, equipCape, checkPalm, checkCalquat, makePotion, claimReward, moveToHorseShoe;

	ConditionalStep craftedRunesTask, equippedCapeTask, checkedPalmTask, checkedCalquatTask, madePotionTask;

	@Override
	public QuestStep loadStep()
	{
		initializeRequirements();
		setupSteps();

		ConditionalStep doElite = new ConditionalStep(this, claimReward);

		checkedCalquatTask = new ConditionalStep(this, checkCalquat);
		doElite.addStep(notCheckedCalquat, checkedCalquatTask);

		checkedPalmTask = new ConditionalStep(this, checkPalm);
		doElite.addStep(notCheckedPalm, checkedPalmTask);

		equippedCapeTask = new ConditionalStep(this, equipCape);
		doElite.addStep(notEquippedCape, equippedCapeTask);

		madePotionTask = new ConditionalStep(this, moveToHorseShoe);
		madePotionTask.addStep(inHorse, makePotion);
		doElite.addStep(notMadePotion, madePotionTask);

		craftedRunesTask = new ConditionalStep(this, enterNatureAltar);
		craftedRunesTask.addStep(inNatureAltar, craftRunes);
		doElite.addStep(notCraftedRunes, craftedRunesTask);

		return doElite;
	}

	@Override
	protected void setupRequirements()
	{
		notCraftedRunes = new VarplayerRequirement(VarPlayerID.ATJUN_TASKS_4, false, 1);
		notEquippedCape = new VarplayerRequirement(VarPlayerID.ATJUN_TASKS_4, false, 2);
		notCheckedPalm = new VarplayerRequirement(VarPlayerID.ATJUN_TASKS_4, false, 3);
		notMadePotion = new VarplayerRequirement(VarPlayerID.ATJUN_TASKS_4, false, 4);
		notCheckedCalquat = new VarplayerRequirement(VarPlayerID.ATJUN_TASKS_4, false, 5);

		natureTiaraOrAbyss = new ItemRequirement("Access to the Nature Altar", ItemCollections.NATURE_ALTAR_WEARABLE).showConditioned(notCraftedRunes).isNotConsumed();
		natureTiaraOrAbyss.setTooltip("Nature Tiara, Catalytic Tiara, RC-skill cape or via Abyss");
		pureEssence = new ItemRequirement("Pure essence", ItemID.BLANKRUNE_HIGH).showConditioned(notCraftedRunes);
		fireCapeOrInfernal = new ItemRequirement("Fire cape or infernal cape", ItemID.TZHAAR_CAPE_FIRE)
			.showConditioned(notEquippedCape).isNotConsumed();
		fireCapeOrInfernal.addAlternates(ItemID.INFERNAL_CAPE);
		palmTreeSapling = new ItemRequirement("Palm tree sapling", ItemID.PLANTPOT_PALM_SAPLING).showConditioned(notCheckedPalm);
		antidotePlusPlus = new ItemRequirement("Antidote++", ItemID.ANTIDOTE__4).showConditioned(notMadePotion);
		antidotePlusPlus.addAlternates(ItemID.ANTIDOTE__3, ItemID.ANTIDOTE__2, ItemID.ANTIDOTE__1);
		zulrahScales = new ItemRequirement("Zulrah scales", ItemID.SNAKEBOSS_SCALE).showConditioned(notMadePotion);
		calquatSapling = new ItemRequirement("Calquat sapling", ItemID.PLANTPOT_CALQUAT_SAPLING).showConditioned(notCheckedCalquat);
		rake = new ItemRequirement("Rake", ItemID.RAKE).showConditioned(new Conditions(LogicType.OR, notCheckedCalquat,
			notCheckedPalm)).isNotConsumed();
		spade = new ItemRequirement("Spade", ItemID.SPADE).showConditioned(new Conditions(LogicType.OR, notCheckedCalquat,
			notCheckedPalm)).isNotConsumed();


		farming72 = new SkillRequirement(Skill.FARMING, 72, true);
		herblore87 = new SkillRequirement(Skill.HERBLORE, 87, true);
		runecraft91 = new SkillRequirement(Skill.RUNECRAFT, 91, true);

		natureAltar = new Zone(new WorldPoint(2374, 4809, 0), new WorldPoint(2421, 4859, 0));
		horse = new Zone(new WorldPoint(2731, 3227, 0), new WorldPoint(2736, 3222, 0));

		inNatureAltar = new ZoneRequirement(natureAltar);
		inHorse = new ZoneRequirement(horse);
	}

	public void setupSteps()
	{
		enterNatureAltar = new ObjectStep(this, ObjectID.NATURETEMPLE_RUINED, new WorldPoint(2869, 3019, 0),
			"Enter the nature altar, either from the ruin or through the Abyss.", natureTiaraOrAbyss,
			pureEssence.quantity(28));
		craftRunes = new ObjectStep(this, ObjectID.NATURE_ALTAR, new WorldPoint(2400, 4841, 0),
			"Craft a full inventory of nature runes.", pureEssence.quantity(28));
		equipCape = new DetailedQuestStep(this, "Equip a fire or infernal cape.", fireCapeOrInfernal.equipped());
		checkPalm = new ObjectStep(this, ObjectID.FARMING_FRUIT_TREE_PATCH_3, new WorldPoint(2765, 3213, 0),
			"Grow and check the health of a palm tree in the Brimhaven patch. " +
				"If you're waiting for it to grow and want to complete further tasks, use the tick box on panel.",
			palmTreeSapling, rake, spade);
		checkCalquat = new ObjectStep(this, ObjectID.FARMING_CALQUAT_TREE_PATCH, new WorldPoint(2796, 3101, 0),
			"Grow and check the health of a Calquat in Tai Bwo Wannai. " +
				"If you're waiting for it to grow and want to complete further tasks, use the tick box on panel.",
			calquatSapling, rake, spade);
		moveToHorseShoe = new DetailedQuestStep(this, new WorldPoint(2734, 3224, 0),
			"Go to the horse shoe mine north west of Brimhaven.");
		makePotion = new DetailedQuestStep(this, new WorldPoint(2734, 3224, 0),
			"Make an antivenom potion whilst standing in the horse shoe mine.",
			antidotePlusPlus.highlighted(), zulrahScales.quantity(20).highlighted());

		claimReward = new NpcStep(this, NpcID.AGILITYARENA_TICKETTRADER_2OPS, new WorldPoint(2810, 3192, 0),
			"Talk to Pirate Jackie the Fruit in Brimhaven to claim your reward!");
		claimReward.addDialogStep("I have a question about my Achievement Diary.");
	}

	@Override
	public List<ItemRequirement> getItemRequirements()
	{
		return Arrays.asList(natureTiaraOrAbyss, pureEssence.quantity(28), fireCapeOrInfernal, palmTreeSapling,
			antidotePlusPlus, zulrahScales.quantity(20), calquatSapling, rake, spade);
	}

	@Override
	public List<Requirement> getGeneralRequirements()
	{
		List<Requirement> reqs = new ArrayList<>();

		reqs.add(new SkillRequirement(Skill.FARMING, 72, true));
		reqs.add(new SkillRequirement(Skill.HERBLORE, 87, true));
		reqs.add(new SkillRequirement(Skill.RUNECRAFT, 91, true));

		return reqs;
	}

	public List<ItemReward> getItemRewards()
	{
		return Arrays.asList(
			new ItemReward("Karamja Gloves (4)", ItemID.ATJUN_GLOVES_ELITE, 1),
			new ItemReward("50,000 Exp. Lamp (Any skill above level 70)", ItemID.THOSF_REWARD_LAMP, 1));
	}

	@Override
	public List<UnlockReward> getUnlockRewards()
	{
		return Arrays.asList(
			new UnlockReward("10% chance of receiving 2 Agility arena tickets in the Brimhaven Agility Dungeon"),
			new UnlockReward("Free usage of Shilo Village's furnace"),
			new UnlockReward("Free cart rides on Hajedy's cart system"),
			new UnlockReward("Free access to the Hardwood Grove"),
			new UnlockReward("Red and Metal in Brimhaven Dungeon will drop noted dragonhide and bars"),
			new UnlockReward("One free resurrection per day in the Fight Caves (Not the Inferno)"),
			new UnlockReward("Double Tokkul from TzHaar Fight Caves, Inferno and Ket-Rak's Challenges"));
	}

	@Override
	public List<PanelDetails> getPanels()
	{
		List<PanelDetails> allSteps = new ArrayList<>();

		PanelDetails palmSteps = new PanelDetails("Check Palm Tree Health", Collections.singletonList(checkPalm),
			new SkillRequirement(Skill.FARMING, 68, true), palmTreeSapling, rake, spade);
		palmSteps.setDisplayCondition(notCheckedPalm);
		palmSteps.setLockingStep(checkedPalmTask);
		allSteps.add(palmSteps);

		PanelDetails calquatSteps = new PanelDetails("Check Calquat Tree Health",
			Collections.singletonList(checkCalquat), farming72, calquatSapling, rake, spade);
		calquatSteps.setDisplayCondition(notCheckedCalquat);
		calquatSteps.setLockingStep(checkedCalquatTask);
		allSteps.add(calquatSteps);

		PanelDetails equipCapeSteps = new PanelDetails("Equip Fire / Infernal Cape",
			Collections.singletonList(equipCape), fireCapeOrInfernal);
		equipCapeSteps.setDisplayCondition(notEquippedCape);
		equipCapeSteps.setLockingStep(equippedCapeTask);
		allSteps.add(equipCapeSteps);

		PanelDetails potionSteps = new PanelDetails("Create Antivenom Potion", Arrays.asList(moveToHorseShoe,
			makePotion), herblore87, antidotePlusPlus, zulrahScales.quantity(20));
		potionSteps.setDisplayCondition(notMadePotion);
		potionSteps.setLockingStep(madePotionTask);
		allSteps.add(potionSteps);

		PanelDetails craftRunesSteps = new PanelDetails("Craft 56 Nature Runes", Arrays.asList(enterNatureAltar,
			craftRunes), runecraft91, pureEssence.quantity(28), natureTiaraOrAbyss);
		craftRunesSteps.setDisplayCondition(notCraftedRunes);
		craftRunesSteps.setLockingStep(craftedRunesTask);
		allSteps.add(craftRunesSteps);

		PanelDetails finishOffSteps = new PanelDetails("Finishing off", Collections.singletonList(claimReward));
		allSteps.add(finishOffSteps);

		return allSteps;
	}
}
