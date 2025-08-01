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
package net.runelite.client.plugins.microbot.questhelper.helpers.quests.theeyesofglouphrie;

import net.runelite.client.plugins.microbot.questhelper.collections.ItemCollections;
import net.runelite.client.plugins.microbot.questhelper.panel.PanelDetails;
import net.runelite.client.plugins.microbot.questhelper.questhelpers.BasicQuestHelper;
import net.runelite.client.plugins.microbot.questhelper.questinfo.QuestHelperQuest;
import net.runelite.client.plugins.microbot.questhelper.requirements.Requirement;
import net.runelite.client.plugins.microbot.questhelper.requirements.conditional.Conditions;
import net.runelite.client.plugins.microbot.questhelper.requirements.item.ItemRequirement;
import net.runelite.client.plugins.microbot.questhelper.requirements.player.SkillRequirement;
import net.runelite.client.plugins.microbot.questhelper.requirements.quest.QuestRequirement;
import net.runelite.client.plugins.microbot.questhelper.requirements.var.VarbitRequirement;
import net.runelite.client.plugins.microbot.questhelper.requirements.zone.Zone;
import net.runelite.client.plugins.microbot.questhelper.requirements.zone.ZoneRequirement;
import net.runelite.client.plugins.microbot.questhelper.rewards.ExperienceReward;
import net.runelite.client.plugins.microbot.questhelper.rewards.QuestPointReward;
import net.runelite.client.plugins.microbot.questhelper.rewards.UnlockReward;
import net.runelite.client.plugins.microbot.questhelper.steps.*;
import net.runelite.client.plugins.microbot.questhelper.util.QHObjectID;
import net.runelite.api.QuestState;
import net.runelite.api.Skill;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.gameval.ItemID;
import net.runelite.api.gameval.NpcID;
import net.runelite.api.gameval.ObjectID;

import java.util.*;

public class TheEyesOfGlouphrie extends BasicQuestHelper
{
	//Items Required
	ItemRequirement bucketOfSap, mudRune, mapleLog, oakLog, hammer, saw, pestleAndMortar, groundMud, magicGlue, mudRuneHighlight, pestleHighlight, bucketOfSapHiglight;

	Requirement inCave, inspectedMachine, inspectedBowl, inHazelmereHut, killedCreature1, killedCreature2, killedCreature3, killedCreature4, killedCreature5,
		killedCreature6, inFloor1, inFloor2, inFloor3;

	QuestStep enterCave, talkToBrimstail, inspectBowl, inspectMachine, talkToBrimstailAgain, goUpToHazelmere, talkToHazelmere, enterCaveAgain, talkToBrimstailAfterHazelmere, grindMudRunes,
		useMudOnSap, repairMachine, talkToBrimstailAfterRepairing, talkToBrimstailForMoreDisks, unlockMachine, operateMachine, killCreature1, talkToBrimstailAfterIllusion, killCreature2, killCreature3,
		killCreature4, killCreature5, killCreature6, climbUpToF1Tree, climbUpToF2Tree, climbUpToF3Tree, talkToNarnode;

	//Zones
	Zone cave, hazelmereHut, floor1, floor2, floor3;

	@Override
	public Map<Integer, QuestStep> loadSteps()
	{
		initializeRequirements();
		setupConditions();
		setupSteps();

		Map<Integer, QuestStep> steps = new HashMap<>();

		ConditionalStep startQuest = new ConditionalStep(this, enterCave);
		startQuest.addStep(inCave, talkToBrimstail);

		steps.put(0, startQuest);

		ConditionalStep inspectDevices = new ConditionalStep(this, enterCave);
		inspectDevices.addStep(new Conditions(inCave, inspectedBowl), inspectMachine);
		inspectDevices.addStep(inCave, inspectBowl);

		steps.put(1, inspectDevices);

		ConditionalStep talkAfterInspect = new ConditionalStep(this, enterCave);
		talkAfterInspect.addStep(inCave, talkToBrimstailAgain);

		steps.put(2, talkAfterInspect);

		ConditionalStep goTalkToHazelmere = new ConditionalStep(this, goUpToHazelmere);
		goTalkToHazelmere.addStep(inHazelmereHut, talkToHazelmere);

		steps.put(5, goTalkToHazelmere);
		steps.put(9, goTalkToHazelmere);
		steps.put(10, goTalkToHazelmere);
		steps.put(11, goTalkToHazelmere);
		steps.put(12, goTalkToHazelmere);
		ConditionalStep talkToBrimAfterHazel = new ConditionalStep(this, enterCaveAgain);
		talkToBrimAfterHazel.addStep(inCave, talkToBrimstailAfterHazelmere);

		steps.put(15, talkToBrimAfterHazel);

		ConditionalStep fixMachine = new ConditionalStep(this, grindMudRunes);
		fixMachine.addStep(new Conditions(magicGlue, inCave), repairMachine);
		fixMachine.addStep(magicGlue, enterCaveAgain);
		fixMachine.addStep(groundMud, useMudOnSap);

		steps.put(20, fixMachine);
		steps.put(21, fixMachine);
		steps.put(22, fixMachine);
		steps.put(23, fixMachine);

		ConditionalStep brimstailAfterFixing = new ConditionalStep(this, enterCave);
		brimstailAfterFixing.addStep(inCave, talkToBrimstailAfterRepairing);

		steps.put(25, brimstailAfterFixing);

		ConditionalStep getMoreTokens = new ConditionalStep(this, enterCave);
		getMoreTokens.addStep(inCave, unlockMachine);

		steps.put(27, unlockMachine);
		steps.put(30, getMoreTokens);

		ConditionalStep goOperateMachine = new ConditionalStep(this, enterCave);
		goOperateMachine.addStep(inCave, operateMachine);

		steps.put(35, goOperateMachine);

		ConditionalStep prepareToKill = new ConditionalStep(this, enterCave);
		prepareToKill.addStep(inCave, talkToBrimstailAfterIllusion);

		steps.put(36, prepareToKill);

		ConditionalStep killThemAll = new ConditionalStep(this, enterCave);
		killThemAll.addStep(new Conditions(killedCreature1, killedCreature5, killedCreature6, killedCreature4, killedCreature3), killCreature2);
		killThemAll.addStep(new Conditions(killedCreature1, killedCreature5, killedCreature6, killedCreature4, inFloor3), killCreature3);
		killThemAll.addStep(new Conditions(killedCreature1, killedCreature5, killedCreature6, killedCreature4, inFloor2), climbUpToF3Tree);
		killThemAll.addStep(new Conditions(killedCreature1, killedCreature5, killedCreature6, killedCreature4, inFloor1), climbUpToF2Tree);
		killThemAll.addStep(new Conditions(killedCreature1, killedCreature5, killedCreature6, killedCreature4), climbUpToF1Tree);
		killThemAll.addStep(new Conditions(killedCreature1, killedCreature5, killedCreature6), killCreature4);
		killThemAll.addStep(new Conditions(killedCreature1, killedCreature5), killCreature6);
		killThemAll.addStep(killedCreature1, killCreature5);
		killThemAll.addStep(inCave, killCreature1);

		steps.put(40, killThemAll);
		steps.put(45, killThemAll);

		steps.put(50, talkToNarnode);

		return steps;
	}

	@Override
	protected void setupRequirements()
	{
		bucketOfSap = new ItemRequirement("Bucket of sap", ItemID.ICS_LITTLE_SAP_BUCKET);
		bucketOfSap.setTooltip("You can get this by using a knife on an evergreen tree with a bucket in your " +
			"inventory");
		bucketOfSapHiglight = new ItemRequirement("Bucket of sap", ItemID.ICS_LITTLE_SAP_BUCKET);
		bucketOfSapHiglight.setTooltip("You can get this by using a knife on an evergreen tree with a bucket in your " +
			"inventory");
		bucketOfSapHiglight.setHighlightInInventory(true);

		groundMud = new ItemRequirement("Ground mud runes", ItemID.EYEGLO_GROUND_MUD_RUNES);
		groundMud.setHighlightInInventory(true);
		mudRune = new ItemRequirement("Mud rune", ItemID.MUDRUNE);
		mudRuneHighlight = new ItemRequirement("Mud rune", ItemID.MUDRUNE);
		mudRuneHighlight.setHighlightInInventory(true);
		mapleLog = new ItemRequirement("Maple logs", ItemID.MAPLE_LOGS);
		oakLog = new ItemRequirement("Oak logs", ItemID.OAK_LOGS);
		hammer = new ItemRequirement("Hammer", ItemCollections.HAMMER).isNotConsumed();
		saw = new ItemRequirement("Saw", ItemCollections.SAW).isNotConsumed();
		pestleAndMortar = new ItemRequirement("Pestle and mortar", ItemID.PESTLE_AND_MORTAR).isNotConsumed();
		pestleHighlight = pestleAndMortar.highlighted();
		magicGlue = new ItemRequirement("Magic glue", ItemID.EYEGLO_MAGIC_GLUE);
		magicGlue.setHighlightInInventory(true);
	}

	@Override
	protected void setupZones()
	{
		cave = new Zone(new WorldPoint(2379, 9806, 0), new WorldPoint(2415, 9832, 0));
		hazelmereHut = new Zone(new WorldPoint(2673, 3085, 1), new WorldPoint(2681, 3089, 1));
		floor1 = new Zone(new WorldPoint(2437, 3474, 1), new WorldPoint(2493, 3511, 1));
		floor2 = new Zone(new WorldPoint(2437, 3474, 2), new WorldPoint(2493, 3511, 2));
		floor3 = new Zone(new WorldPoint(2437, 3474, 3), new WorldPoint(2493, 3511, 3));
	}

	public void setupConditions()
	{
		inCave = new ZoneRequirement(cave);
		inspectedBowl = new VarbitRequirement(2515, 1);
		inspectedMachine = new VarbitRequirement(2516, 1);
		inHazelmereHut = new ZoneRequirement(hazelmereHut);

		killedCreature1 = new VarbitRequirement(2504, 2);
		killedCreature2 = new VarbitRequirement(2505, 2);
		killedCreature3 = new VarbitRequirement(2506, 2);
		killedCreature4 = new VarbitRequirement(2507, 2);
		killedCreature5 = new VarbitRequirement(2508, 2);
		killedCreature6 = new VarbitRequirement(2509, 2);

		inFloor1 = new ZoneRequirement(floor1);
		inFloor2 = new ZoneRequirement(floor2);
		inFloor3 = new ZoneRequirement(floor3);
	}

	public void setupSteps()
	{
		int[] brimstailNPCs = new int[]{NpcID.GNOME_BRIMSTAIL_CUTSCENE, NpcID.GNOME_BRIMSTAIL_2OP};
		enterCave = new ObjectStep(this, ObjectID.EYEGLO_BRIMSTAILS_CAVE_ENTRANCE, new WorldPoint(2404, 3419, 0), "Go talk to Brimstail in his cave in west Tree Gnome Stronghold.");
		talkToBrimstail = new NpcStep(this, brimstailNPCs, new WorldPoint(2410, 9818, 0), "Talk to Brimstail.");
		talkToBrimstail.addDialogStep("What's that cute creature wandering around?");
		talkToBrimstail.addDialogStep("Yes, that sounds fascinating...");
		talkToBrimstail.addDialogStep("Oh, yes I love a bit of History.");
		talkToBrimstail.addDialogStep("Yes.");
		enterCave.addSubSteps(talkToBrimstail);
		inspectBowl = new ObjectStep(this, ObjectID.EYEGLO_SINGING_BOWL, new WorldPoint(2388, 9813, 0), "Inspect the singing bowl in the west room.");
		inspectMachine = new ObjectStep(this, ObjectID.EYEGLO_GNOME_MACHINE_02_MULTILOC, new WorldPoint(2390, 9826, 0),
			"Attempt to unlock oaknock's machine in the north of the cave.");
		talkToBrimstailAgain = new NpcStep(this, brimstailNPCs, new WorldPoint(2410, 9818, 0), "Talk to Brimstail again.");
		talkToBrimstailAgain.addDialogStep("I've had a look in the other room now.");
		talkToBrimstailAgain.addDialogStep("Of course, I'd love to!");

		goUpToHazelmere = new ObjectStep(this, ObjectID.LADDER, new WorldPoint(2677, 3087, 0),
			"Go talk to Hazelmere in his hut east of Yanille (Fairy Ring CLS).");
		talkToHazelmere = new NpcStep(this, NpcID.GRANDTREE_HAZELMERE, new WorldPoint(2677, 3087, 1),
			"Go talk to Hazelmere in his hut east of Yanille (Fairy Ring CLS).");
		talkToHazelmere.addSubSteps(goUpToHazelmere);

		enterCaveAgain = new ObjectStep(this, ObjectID.EYEGLO_BRIMSTAILS_CAVE_ENTRANCE, new WorldPoint(2404, 3419, 0), "Go back to Brimstail's cave in west Tree Gnome Stronghold.", pestleAndMortar, mudRune, bucketOfSap, oakLog, mapleLog, saw, hammer);
		talkToBrimstailAfterHazelmere = new NpcStep(this, brimstailNPCs, new WorldPoint(2410, 9818, 0), "Talk to Brimstail.");
		talkToBrimstailAfterHazelmere.addDialogStep("I've visited Hazelmere, he told me all sorts of interesting things.");
		talkToBrimstailAfterHazelmere.addSubSteps(enterCaveAgain);

		grindMudRunes = new DetailedQuestStep(this, "Use the pestle and mortar on some mud runes.", pestleHighlight,
			mudRuneHighlight);
		useMudOnSap = new DetailedQuestStep(this, "Use the ground mud runes on the bucket of sap.", groundMud,
			bucketOfSapHiglight);

		repairMachine = new ObjectStep(this, ObjectID.EYEGLO_GNOME_MACHINE_02_MULTILOC, new WorldPoint(2390, 9826, 0),
			"Use the magic glue on oaknock's machine in the north of the cave.", magicGlue, oakLog, mapleLog, saw, hammer);
		repairMachine.addIcon(ItemID.EYEGLO_MAGIC_GLUE);

		talkToBrimstailAfterRepairing = new NpcStep(this, brimstailNPCs, new WorldPoint(2410, 9818, 0), "Talk to Brimstail.");
		talkToBrimstailAfterRepairing.addDialogStep("I think I've fixed the machine now!");
		talkToBrimstailForMoreDisks = new NpcStep(this, brimstailNPCs, new WorldPoint(2410, 9818, 0), "Talk to Brimstail for more disks.");
		talkToBrimstailForMoreDisks.addDialogStep("I can't work out what to do with these discs!");

		PuzzleStep unlockMachineTrueStep = new PuzzleStep(this);
		unlockMachineTrueStep.addSubSteps(unlockMachineTrueStep.getSteps());
		unlockMachine = new PuzzleWrapperStep(this, unlockMachineTrueStep, "Unlock the machine.");

		operateMachine = new ObjectStep(this, ObjectID.EYEGLO_GNOME_MACHINE_02_MULTILOC, new WorldPoint(2390, 9826, 0), "Operate the machine.");
		unlockMachineTrueStep.addSubSteps(operateMachine);

		killCreature1 = new NpcStep(this, NpcID.EYEGLO_FLUFFIE_EVIL_1, new WorldPoint(2408, 9819, 0), "Kill the evil creature next to Brimstail.");
		killCreature2 = new NpcStep(this, NpcID.EYEGLO_FLUFFIE_EVIL_2, new WorldPoint(2465, 3494, 0), "Kill the evil creature next to Narnode.");
		killCreature3 = new NpcStep(this, NpcID.EYEGLO_FLUFFIE_EVIL_3, new WorldPoint(2466, 3496, 3), "Kill the evil creature at the top of the Grand Tree.");
		killCreature4 = new NpcStep(this, NpcID.EYEGLO_FLUFFIE_EVIL_4, new WorldPoint(2422, 3526, 0), "Kill the evil creature in the north west of the Stronghold.");
		killCreature5 = new NpcStep(this, NpcID.EYEGLO_FLUFFIE_EVIL_5, new WorldPoint(2461, 3388, 0), "Kill the evil creature next to the Stronghold's entrance.");
		killCreature6 = new NpcStep(this, NpcID.EYEGLO_FLUFFIE_EVIL_6, new WorldPoint(2462, 3443, 0), "Kill the evil creature next to the Stronghold's Spirit Tree.");
		//killCreature7 = new NpcStep(this, NpcID.EYEGLO_FLUFFIE_EVIL_1, new WorldPoint(3408, 9819, 0), "Kill the evil creature next to Brimstail.");

		talkToBrimstailAfterIllusion = new NpcStep(this, brimstailNPCs, new WorldPoint(2410, 9818, 0), "Talk to Brimstail again.");
		talkToBrimstailAfterIllusion.addDialogStep("Phew! I've got that machine working now. What do I need to do now?");

		climbUpToF1Tree = new ObjectStep(this, QHObjectID.GRAND_TREE_F0_LADDER, new WorldPoint(2466, 3495, 0), "Kill the evil creature at the top of the Grand Tree.");
		climbUpToF2Tree = new ObjectStep(this, QHObjectID.GRAND_TREE_F1_LADDER, new WorldPoint(2466, 3495, 1), "Kill the evil creature at the top of the Grand Tree.");
		climbUpToF3Tree = new ObjectStep(this, QHObjectID.GRAND_TREE_F2_LADDER, new WorldPoint(2466, 3495, 2), "Kill the evil creature at the top of the Grand Tree.");
		killCreature3.addSubSteps(climbUpToF1Tree, climbUpToF2Tree, climbUpToF3Tree);

		talkToNarnode = new NpcStep(this, NpcID.GRANDTREE_NARNODE_1OP, new WorldPoint(2465, 3496, 0), "Talk to King Narnode to finish the quest.");
	}

	@Override
	public List<ItemRequirement> getItemRequirements()
	{
		return Arrays.asList(bucketOfSap, mudRune, mapleLog, oakLog, hammer, saw, pestleAndMortar);
	}

	@Override
	public List<Requirement> getGeneralRequirements()
	{
		ArrayList<Requirement> req = new ArrayList<>();
		req.add(new QuestRequirement(QuestHelperQuest.THE_GRAND_TREE, QuestState.FINISHED));
		req.add(new SkillRequirement(Skill.CONSTRUCTION, 5));
		req.add(new SkillRequirement(Skill.MAGIC, 46));
		return req;
	}

	@Override
	public List<String> getCombatRequirements()
	{
		return Collections.singletonList("Evil creature (x6)");
	}

	@Override
	public QuestPointReward getQuestPointReward()
	{
		return new QuestPointReward(2);
	}

	@Override
	public List<ExperienceReward> getExperienceRewards()
	{
		return Arrays.asList(
				new ExperienceReward(Skill.MAGIC, 12000),
				new ExperienceReward(Skill.WOODCUTTING, 2500),
				new ExperienceReward(Skill.RUNECRAFT, 6000),
				new ExperienceReward(Skill.CONSTRUCTION, 250));
	}

	@Override
	public List<UnlockReward> getUnlockRewards()
	{
		return Collections.singletonList(new UnlockReward("A Crystal Saw Seed"));
	}

	@Override
	public List<PanelDetails> getPanels()
	{
		List<PanelDetails> allSteps = new ArrayList<>();

		allSteps.add(new PanelDetails("Learning", Arrays.asList(enterCave, inspectBowl, inspectMachine,
			talkToBrimstailAgain, talkToHazelmere, talkToBrimstailAfterHazelmere, grindMudRunes, useMudOnSap, repairMachine,
			talkToBrimstailAfterRepairing, talkToBrimstailForMoreDisks, unlockMachine),
			bucketOfSap, mudRune, mapleLog, oakLog, hammer, saw, pestleAndMortar));

		allSteps.add(new PanelDetails("Kill the spies",
			Arrays.asList(talkToBrimstailAfterIllusion, killCreature1, killCreature5, killCreature6, killCreature4,
				killCreature3, killCreature2, talkToNarnode)));

		return allSteps;
	}
}
