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
import net.runelite.client.plugins.microbot.questhelper.collections.ItemCollections;
import net.runelite.client.plugins.microbot.questhelper.panel.PanelDetails;
import net.runelite.client.plugins.microbot.questhelper.questhelpers.BasicQuestHelper;
import net.runelite.client.plugins.microbot.questhelper.questinfo.QuestHelperQuest;
import net.runelite.client.plugins.microbot.questhelper.questinfo.QuestVarbits;
import net.runelite.client.plugins.microbot.questhelper.requirements.Requirement;
import net.runelite.client.plugins.microbot.questhelper.requirements.conditional.Conditions;
import net.runelite.client.plugins.microbot.questhelper.requirements.item.ItemRequirement;
import net.runelite.client.plugins.microbot.questhelper.requirements.player.PrayerRequirement;
import net.runelite.client.plugins.microbot.questhelper.requirements.player.SkillRequirement;
import net.runelite.client.plugins.microbot.questhelper.requirements.quest.QuestRequirement;
import net.runelite.client.plugins.microbot.questhelper.requirements.util.LogicType;
import net.runelite.client.plugins.microbot.questhelper.requirements.util.Operation;
import net.runelite.client.plugins.microbot.questhelper.requirements.var.VarbitRequirement;
import net.runelite.client.plugins.microbot.questhelper.requirements.zone.Zone;
import net.runelite.client.plugins.microbot.questhelper.requirements.zone.ZoneRequirement;
import net.runelite.client.plugins.microbot.questhelper.rewards.ExperienceReward;
import net.runelite.client.plugins.microbot.questhelper.rewards.QuestPointReward;
import net.runelite.client.plugins.microbot.questhelper.rewards.UnlockReward;
import net.runelite.client.plugins.microbot.questhelper.steps.*;
import net.runelite.api.Client;
import net.runelite.api.Prayer;
import net.runelite.api.QuestState;
import net.runelite.api.Skill;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.gameval.ItemID;
import net.runelite.api.gameval.NpcID;
import net.runelite.api.gameval.ObjectID;
import net.runelite.api.gameval.VarbitID;

import java.util.*;

public class RFDAwowogei extends BasicQuestHelper
{
	//Items Required
	ItemRequirement cookedSnake, cookedSnakeHighlighted, mAmulet, gorillaGreegree, ninjaGreegree, zombieGreegree, bananaHighlighted, monkeyNutsHighlighted, ropeHighlighted,
		knife, pestleAndMortar, tchikiNuts, tchikiNutsHighlighted, redBanana, redBananaHighlighted, snakeCorpse, snakeCorpseHighlighted,
		rawStuffedSnake, rawStuffedSnakeHighlighted, slicedRedBanana, greegreeEquipped, paste, combatGear;

	Requirement protectMelee;

	Requirement inDiningRoom, askedAboutBanana, askedAboutNut, hasSnakeCorpse, hasRedBanana, hasTchikiNut, onCrashIsland, inSnakeHole,
		inNutHole, inTempleDungeon, inCookRoom;

	QuestStep enterDiningRoom, inspectAwowogei, talkToAwowogei, talkToWiseMonkeys, useBananaOnWiseMonkeys, useNutsOnWiseMonkeys, goToCrashIsland,
		enterCrashHole, killSnake, leaveSnakeHole, returnToApeAtoll, useRopeOnTree, enterNutHole, takeNuts, grindNuts, sliceBanana, stuffSnake,
		enterZombieDungeon, enterCookingHole, cookSnake, enterDiningRoomAgain, useSnakeOnAwowogei;

	//Zones
	Zone diningRoom, crashIsland, snakeHole, nutHole, templeDungeon, cookRoom;

	@Override
	public Map<Integer, QuestStep> loadSteps()
	{
		initializeRequirements();
		setupConditions();
		setupSteps();

		Map<Integer, QuestStep> steps = new HashMap<>();

		ConditionalStep goInspectSkrach = new ConditionalStep(this, enterDiningRoom);
		goInspectSkrach.addStep(inDiningRoom, inspectAwowogei);
		steps.put(0, goInspectSkrach);

		steps.put(5, talkToAwowogei);

		steps.put(10, talkToWiseMonkeys);

		ConditionalStep prepareMeal = new ConditionalStep(this, useBananaOnWiseMonkeys);
		prepareMeal.addStep(new Conditions(new Conditions(inDiningRoom, cookedSnake)), useSnakeOnAwowogei);
		prepareMeal.addStep(new Conditions(cookedSnake), enterDiningRoomAgain);
		prepareMeal.addStep(new Conditions(rawStuffedSnake, inCookRoom), cookSnake);
		prepareMeal.addStep(new Conditions(rawStuffedSnake, inTempleDungeon), enterCookingHole);
		prepareMeal.addStep(new Conditions(rawStuffedSnake), enterZombieDungeon);
		prepareMeal.addStep(new Conditions(hasSnakeCorpse, slicedRedBanana, paste), stuffSnake);
		prepareMeal.addStep(new Conditions(hasSnakeCorpse, hasRedBanana, paste), sliceBanana);
		prepareMeal.addStep(new Conditions(hasSnakeCorpse, hasRedBanana, hasTchikiNut), grindNuts);
		prepareMeal.addStep(new Conditions(askedAboutNut, hasSnakeCorpse, hasRedBanana, inNutHole), takeNuts);
		prepareMeal.addStep(new Conditions(askedAboutNut, hasSnakeCorpse, hasRedBanana), enterNutHole);
		prepareMeal.addStep(new Conditions(askedAboutBanana, askedAboutNut, onCrashIsland, hasSnakeCorpse), returnToApeAtoll);
		prepareMeal.addStep(new Conditions(askedAboutBanana, askedAboutNut, inSnakeHole, hasSnakeCorpse), leaveSnakeHole);
		prepareMeal.addStep(new Conditions(askedAboutBanana, askedAboutNut, hasSnakeCorpse), useRopeOnTree);
		prepareMeal.addStep(new Conditions(askedAboutBanana, askedAboutNut, inSnakeHole), killSnake);
		prepareMeal.addStep(new Conditions(askedAboutBanana, askedAboutNut, onCrashIsland), enterCrashHole);
		prepareMeal.addStep(new Conditions(askedAboutBanana, askedAboutNut), goToCrashIsland);
		prepareMeal.addStep(askedAboutBanana, useNutsOnWiseMonkeys);
		steps.put(15, prepareMeal);
		steps.put(20, prepareMeal);
		steps.put(30, prepareMeal);
		steps.put(40, prepareMeal);
		return steps;
	}

	@Override
	protected void setupRequirements()
	{
		cookedSnake = new ItemRequirement("Stuffed snake", ItemID.HUNDRED_ILM_COOKED_STUFFED_SNAKE);
		cookedSnakeHighlighted = new ItemRequirement("Stuffed snake", ItemID.HUNDRED_ILM_COOKED_STUFFED_SNAKE);
		cookedSnakeHighlighted.setHighlightInInventory(true);

		mAmulet = new ItemRequirement("M'speak amulet", ItemID.MM_AMULET_OF_MONKEY_SPEAK, 1, true);
		gorillaGreegree = new ItemRequirement("Gorilla greegree", ItemID.MM_MONKEY_GREEGREE_FOR_NORMAL_GORILLA, 1, true).isNotConsumed();
		gorillaGreegree.addAlternates(ItemID.MM_MONKEY_GREEGREE_FOR_ANCIENT_MONKEY_SKULL, ItemID.MM_MONKEY_GREEGREE_FOR_BEARDED_GORILLA);
		gorillaGreegree.setTooltip("Kill a gorilla in the monkey temple for their bones, and make a greegree from them");
		ninjaGreegree = new ItemRequirement("Ninja greegree", ItemID.MM_MONKEY_GREEGREE_FOR_SMALL_NINJA_MONKEY, 1, true).isNotConsumed();
		ninjaGreegree.setTooltip("Kill a monkey archer in the monkey market for its bones and make a greegree from them");
		ninjaGreegree.addAlternates(ItemID.MM_MONKEY_GREEGREE_FOR_MEDIUM_NINJA_MONKEY, ItemID.MM2_KRUK_GREEGREE);
		zombieGreegree = new ItemRequirement("Zombie greegree", ItemID.MM_MONKEY_GREEGREE_FOR_SMALL_ZOMBIE_MONKEY, 1, true).isNotConsumed();
		zombieGreegree.setTooltip("Kill a zombie monkey under the monkey temple and make a greegree from them");
		zombieGreegree.addAlternates(ItemID.MM_MONKEY_GREEGREE_FOR_LARGE_ZOMBIE_MONKEY);
		greegreeEquipped = new ItemRequirement("Any greegree", ItemCollections.GREEGREES).equipped().isNotConsumed();
		bananaHighlighted = new ItemRequirement("Banana", ItemID.BANANA);
		bananaHighlighted.setHighlightInInventory(true);
		monkeyNutsHighlighted = new ItemRequirement("Monkey nuts", ItemID.MM_MONKEY_NUTS);
		monkeyNutsHighlighted.setHighlightInInventory(true);
		monkeyNutsHighlighted.setTooltip("You can buy some from Solihib in the monkey market");
		ropeHighlighted = new ItemRequirement("Rope", ItemID.ROPE).isNotConsumed();
		ropeHighlighted.setHighlightInInventory(true);
		knife = new ItemRequirement("Knife", ItemID.KNIFE).isNotConsumed();
		knife.setHighlightInInventory(true);
		pestleAndMortar = new ItemRequirement("Pestle and mortar", ItemID.PESTLE_AND_MORTAR).isNotConsumed();
		pestleAndMortar.setHighlightInInventory(true);

		tchikiNuts = new ItemRequirement("Tchiki monkey nuts", ItemID.HUNDRED_ILM_TCHIKI_MONKEY_NUTS);
		tchikiNutsHighlighted = new ItemRequirement("Tchiki monkey nuts", ItemID.HUNDRED_ILM_TCHIKI_MONKEY_NUTS);
		tchikiNutsHighlighted.setHighlightInInventory(true);
		redBanana = new ItemRequirement("Red banana", ItemID.HUNDRED_ILM_RED_BANANA);
		redBananaHighlighted = new ItemRequirement("Red banana", ItemID.HUNDRED_ILM_RED_BANANA);
		redBananaHighlighted.setHighlightInInventory(true);
		snakeCorpse = new ItemRequirement("Snake corpse", ItemID.HUNDRED_ILM_SNAKE_CORPSE);
		snakeCorpseHighlighted = new ItemRequirement("Snake corpse", ItemID.HUNDRED_ILM_SNAKE_CORPSE);
		snakeCorpseHighlighted.setHighlightInInventory(true);

		rawStuffedSnake = new ItemRequirement("Raw stuffed snake", ItemID.HUNDRED_ILM_CORRECTLY_STUFFED_SNAKE);
		rawStuffedSnakeHighlighted = new ItemRequirement("Raw stuffed snake", ItemID.HUNDRED_ILM_CORRECTLY_STUFFED_SNAKE);
		rawStuffedSnakeHighlighted.setHighlightInInventory(true);

		slicedRedBanana = new ItemRequirement("Sliced red banana", ItemID.HUNDRED_ILM_SLICED_RED_BANANA);
		paste = new ItemRequirement("Tchiki nut paste", ItemID.HUNDRED_ILM_TCHIKI_MONKEY_NUT_PASTE);
		paste.setHighlightInInventory(true);

		combatGear = new ItemRequirement("Combat gear", -1, -1).isNotConsumed();
		combatGear.setDisplayItemId(BankSlotIcons.getCombatGear());

		protectMelee = new PrayerRequirement("Protect from Melee", Prayer.PROTECT_FROM_MELEE);
	}

	@Override
	protected void setupZones()
	{
		diningRoom = new Zone(new WorldPoint(1856, 5313, 0), new WorldPoint(1870, 5333, 0));
		crashIsland = new Zone(new WorldPoint(2883, 2693, 0), new WorldPoint(2941, 2747, 0));
		snakeHole = new Zone(new WorldPoint(3019, 5484, 0), new WorldPoint(3028, 5492, 0));
		nutHole = new Zone(new WorldPoint(3014, 5451, 0), new WorldPoint(3030, 5462, 0));
		templeDungeon = new Zone(new WorldPoint(2777, 9185, 0), new WorldPoint(2818, 9219, 0));
		cookRoom = new Zone(new WorldPoint(3049, 5477, 0), new WorldPoint(3069, 5492, 0));
	}

	public void setupConditions()
	{
		inDiningRoom = new ZoneRequirement(diningRoom);
		onCrashIsland = new ZoneRequirement(crashIsland);
		inSnakeHole = new ZoneRequirement(snakeHole);
		inNutHole = new ZoneRequirement(nutHole);
		inTempleDungeon = new ZoneRequirement(templeDungeon);
		inCookRoom = new ZoneRequirement(cookRoom);


		askedAboutBanana = new VarbitRequirement(VarbitID.HUNDRED_ILM_BANANA, 10, Operation.GREATER_EQUAL);
		askedAboutNut = new VarbitRequirement(VarbitID.HUNDRED_ILM_NUTS, 10, Operation.GREATER_EQUAL);


		hasSnakeCorpse = new Conditions(LogicType.OR, snakeCorpse, rawStuffedSnake);
		hasRedBanana = new Conditions(LogicType.OR, redBanana, slicedRedBanana);
		hasTchikiNut = new Conditions(LogicType.OR, tchikiNuts, paste);
	}

	public void setupSteps()
	{
		enterDiningRoom = new ObjectStep(this, ObjectID.HUNDRED_LUMBRIDGE_DOUBLEDOORL, new WorldPoint(3213, 3221, 0), "Go inspect Awowogei in Lumbridge Castle.");
		inspectAwowogei = new ObjectStep(this, ObjectID.HUNDRED_MONKEY_BASE, new WorldPoint(1865, 5319, 0), "Inspect Awowogei.");
		inspectAwowogei.addSubSteps(enterDiningRoom);

		talkToAwowogei = new ObjectStep(this, ObjectID.MM_THRONE, new WorldPoint(2803, 2765, 0), "Talk to Awowogei on Ape Atoll.", greegreeEquipped, mAmulet);
		talkToWiseMonkeys = new NpcStep(this, NpcID.HUNDRED_ILM_IWAZARU, new WorldPoint(2789, 2795, 0), "Talk to the three monkeys sat in the temple.", greegreeEquipped, mAmulet);
		talkToWiseMonkeys.addDialogStep("Do you know anything about the King's favourite dish?");
		useBananaOnWiseMonkeys = new NpcStep(this, NpcID.HUNDRED_ILM_IWAZARU, new WorldPoint(2789, 2795, 0), "Use a banana on the one of the three monkeys.", bananaHighlighted, greegreeEquipped, mAmulet);
		useBananaOnWiseMonkeys.addIcon(ItemID.BANANA);
		useNutsOnWiseMonkeys = new NpcStep(this, NpcID.HUNDRED_ILM_IWAZARU, new WorldPoint(2789, 2795, 0), "Use some monkey nuts on the one of the three monkeys.", monkeyNutsHighlighted, greegreeEquipped, mAmulet);
		useNutsOnWiseMonkeys.addIcon(ItemID.MM_MONKEY_NUTS);

		goToCrashIsland = new NpcStep(this, NpcID.MM_LUMDO_2OPS, new WorldPoint(2802, 2706, 0), "Travel to Crash Island with Lumdo.", combatGear);
		enterCrashHole = new ObjectStep(this, ObjectID._100_ILM_SNAKEPIT_ENTRANCE, new WorldPoint(2922, 2722, 0), "Enter the hole on Crash Island. Protect melee when entering as you'll be attacked straight away by snakes.", combatGear, protectMelee);
		enterCrashHole.addDialogStep("Yes, I'm as hard as nails.");
		killSnake = new NpcStep(this, NpcID.HUNDRED_ILM_SNAKE, new WorldPoint(3019, 5485, 0), "Kill a Big Snake for its corpse. Kill a few in case you burn it.", snakeCorpse);
		leaveSnakeHole = new ObjectStep(this, ObjectID._100_ILM_LIGHT_STREAMING_WITH_ROPE, new WorldPoint(3024, 5489, 0), "Leave the hole.");
		returnToApeAtoll = new NpcStep(this, NpcID.MM_LUMDO_2OPS, new WorldPoint(2892, 2723, 0), "Travel back to Ape Atoll with Lumdo.");

		useRopeOnTree = new ObjectStep(this, ObjectID._100_ILM_BANANA_TREE_MULTILOC, new WorldPoint(2697, 2786, 0), "Use a rope on the red banana tree on the north west of Ape Atoll as a gorilla.", gorillaGreegree, ropeHighlighted);
		useRopeOnTree.addIcon(ItemID.ROPE);

		enterNutHole = new ObjectStep(this, ObjectID._100_ILM_VINE_HOLE, new WorldPoint(2758, 2729, 0), "Go around the monkey agility course until you reach a hole, and go down it.", ninjaGreegree);
		takeNuts = new ObjectStep(this, ObjectID._100_ILM_MONKEY_NUT_BUSH_01, new WorldPoint(3021, 5458, 0), "Take a nut from the bush. Take a few in case you burn the final meal.");

		grindNuts = new DetailedQuestStep(this, "Use a pestle and mortar on the tchiki nuts.", pestleAndMortar, tchikiNutsHighlighted);
		sliceBanana = new DetailedQuestStep(this, "Use a knife/slash weapon on the red banana.", knife, redBananaHighlighted);

		stuffSnake = new DetailedQuestStep(this, "Use the paste on the snake to stuff it.", paste, slicedRedBanana, snakeCorpseHighlighted);

		enterZombieDungeon = new ObjectStep(this, ObjectID.MM_TEMPLE_TRAPDOOR_OPEN, new WorldPoint(2807, 2785, 0), "Enter the trapdoor in the monkey temple.", zombieGreegree, rawStuffedSnake);
		((ObjectStep) (enterZombieDungeon)).addAlternateObjects(ObjectID.MM_TEMPLE_TRAPDOOR);
		enterCookingHole = new ObjectStep(this, ObjectID._100_ILM_ZOMBIE_CAVE_CRACK, new WorldPoint(2805, 9199, 0), "Enter the hole just under where you entered.", zombieGreegree, rawStuffedSnake);
		cookSnake = new ObjectStep(this, ObjectID._100_ILM_LONG_HOT_ROCK_MULTI, new WorldPoint(3056, 5485, 0), "Go across the hot rocks with the zombie greegree equipped. Cook the stuffed snake on the rock at the end of the room.", zombieGreegree, rawStuffedSnakeHighlighted);
		cookSnake.addIcon(ItemID.HUNDRED_ILM_CORRECTLY_STUFFED_SNAKE);

		enterDiningRoomAgain = new ObjectStep(this, ObjectID.HUNDRED_LUMBRIDGE_DOOR, new WorldPoint(3207, 3217, 0), "Go give the snake to Awowogei to finish the quest.", cookedSnake);
		useSnakeOnAwowogei = new ObjectStep(this, ObjectID.HUNDRED_MONKEY_BASE, new WorldPoint(1865, 5319, 0), "Give the snake to Awowogei to finish the quest.", cookedSnakeHighlighted);
		useSnakeOnAwowogei.addIcon(ItemID.HUNDRED_ILM_COOKED_STUFFED_SNAKE);
		useSnakeOnAwowogei.addSubSteps(enterDiningRoomAgain);
	}

	@Override
	public List<ItemRequirement> getItemRequirements()
	{
		return Arrays.asList(mAmulet, bananaHighlighted, monkeyNutsHighlighted, ropeHighlighted, knife, pestleAndMortar, gorillaGreegree, ninjaGreegree, zombieGreegree);
	}

	@Override
	public List<String> getCombatRequirements()
	{
		return Arrays.asList("Big Snake (level 84)", "If you need the greegrees still, a zombie, ninja, and guard monkey");
	}

	@Override
	public List<String> getNotes()
	{
		return Collections.singletonList("If you don't have the ninja/gorilla/zombie greegrees ready, it's recommended you get them all in a single run to Zooknock to save time.");
	}

	@Override
	public List<Requirement> getGeneralRequirements()
	{
		ArrayList<Requirement> req = new ArrayList<>();
		req.add(new VarbitRequirement(QuestVarbits.QUEST_RECIPE_FOR_DISASTER.getId(), Operation.GREATER_EQUAL, 3,
			"Started Recipe for Disaster"));
		req.add(new QuestRequirement(QuestHelperQuest.MONKEY_MADNESS_I, QuestState.FINISHED));
		req.add(new SkillRequirement(Skill.AGILITY, 48));
		req.add(new SkillRequirement(Skill.COOKING, 70, true));
		return req;
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
				new ExperienceReward(Skill.COOKING, 10000),
				new ExperienceReward(Skill.AGILITY, 10000));
	}

	@Override
	public List<UnlockReward> getUnlockRewards()
	{
		return Arrays.asList(
				new UnlockReward("Ability to teleport to Ape Atoll"),
				new UnlockReward("Increased access to the Culinaromancer's Chest"));
	}

	@Override
	public List<PanelDetails> getPanels()
	{
		List<PanelDetails> allSteps = new ArrayList<>();
		allSteps.add(new PanelDetails("Starting off", Collections.singletonList(inspectAwowogei)));
		allSteps.add(new PanelDetails("Saving Awowogei", Arrays.asList(talkToAwowogei, talkToWiseMonkeys, useBananaOnWiseMonkeys, useNutsOnWiseMonkeys, goToCrashIsland, enterCrashHole, killSnake, leaveSnakeHole,
			returnToApeAtoll, useRopeOnTree, enterNutHole, takeNuts, grindNuts, sliceBanana, stuffSnake, enterZombieDungeon, enterCookingHole, cookSnake, useSnakeOnAwowogei),
			mAmulet, bananaHighlighted, monkeyNutsHighlighted, ropeHighlighted, knife, pestleAndMortar, zombieGreegree, ninjaGreegree, gorillaGreegree));
		return allSteps;
	}

	@Override
	public QuestState getState(Client client)
	{
		int questState = client.getVarbitValue(QuestVarbits.QUEST_RECIPE_FOR_DISASTER_MONKEY_AMBASSADOR.getId());
		if (questState == 0)
		{
			return QuestState.NOT_STARTED;
		}

		if (questState < 50)
		{
			return QuestState.IN_PROGRESS;
		}

		return QuestState.FINISHED;
	}

	@Override
	public boolean isCompleted()
	{
		return (client.getVarbitValue(QuestVarbits.QUEST_RECIPE_FOR_DISASTER_MONKEY_AMBASSADOR.getId()) >= 50 || client.getVarbitValue(QuestVarbits.QUEST_RECIPE_FOR_DISASTER.getId()) < 3);
	}
}
