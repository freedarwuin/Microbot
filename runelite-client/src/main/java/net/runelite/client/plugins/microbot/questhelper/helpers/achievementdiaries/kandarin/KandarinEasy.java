/*
 * Copyright (c) 2021, Zoinkwiz <https://github.com/Zoinkwiz>
 * Copyright (c) 2021, Obasill <https://github.com/Obasill>
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
package net.runelite.client.plugins.microbot.questhelper.helpers.achievementdiaries.kandarin;

import net.runelite.client.plugins.microbot.questhelper.bank.banktab.BankSlotIcons;
import net.runelite.client.plugins.microbot.questhelper.collections.ItemCollections;
import net.runelite.client.plugins.microbot.questhelper.collections.KeyringCollection;
import net.runelite.client.plugins.microbot.questhelper.panel.PanelDetails;
import net.runelite.client.plugins.microbot.questhelper.questhelpers.ComplexStateQuestHelper;
import net.runelite.client.plugins.microbot.questhelper.questinfo.QuestHelperQuest;
import net.runelite.client.plugins.microbot.questhelper.requirements.Requirement;
import net.runelite.client.plugins.microbot.questhelper.requirements.conditional.Conditions;
import net.runelite.client.plugins.microbot.questhelper.requirements.item.ItemRequirement;
import net.runelite.client.plugins.microbot.questhelper.requirements.item.ItemRequirements;
import net.runelite.client.plugins.microbot.questhelper.requirements.item.KeyringRequirement;
import net.runelite.client.plugins.microbot.questhelper.requirements.player.SkillRequirement;
import net.runelite.client.plugins.microbot.questhelper.requirements.quest.QuestRequirement;
import net.runelite.client.plugins.microbot.questhelper.requirements.runelite.RuneliteRequirement;
import net.runelite.client.plugins.microbot.questhelper.requirements.util.LogicType;
import net.runelite.client.plugins.microbot.questhelper.requirements.var.VarplayerRequirement;
import net.runelite.client.plugins.microbot.questhelper.requirements.zone.Zone;
import net.runelite.client.plugins.microbot.questhelper.rewards.ItemReward;
import net.runelite.client.plugins.microbot.questhelper.rewards.UnlockReward;
import net.runelite.client.plugins.microbot.questhelper.statemanagement.AchievementDiaryStepManager;
import net.runelite.client.plugins.microbot.questhelper.steps.*;
import net.runelite.api.QuestState;
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

public class KandarinEasy extends ComplexStateQuestHelper
{
	// Items required
	ItemRequirement combatGear, bigFishingNet, coins, juteSeed, seedDibber, rake, batteredKey,
		emptyFishbowl, fishBowl, seaweed, fishBowlSeaweed, tinyNet, genericFishbowl;

	// Items recommended
	ItemRequirement food;

	Requirement notCatchMackerel, notBuyCandle, notCollectFlax, notPlayOrgan, notPlantJute, notCupTea,
		notKillEle, notPetFish, notBuyStew, notTalkSherlock, notLogShortcut;

	Requirement eleWorkI;

	// quest steps
	QuestStep buyCandle, collectFlax, playOrgan, plantJute, cupTea, petFish, fillFishbowl,
		buyStew, talkSherlock, logShortcut, claimReward, moveToWorkshop, petFishMix, petFishFish;

	QuestStep killFire, killEarth, killWater, killAir;

	NpcStep catchMackerel, killEle;

	Zone workshop;

	Requirement inWorkshop;

	ConditionalStep catchMackerelTask, buyCandleTask, collectFlaxTask, playOrganTask, plantJuteTask, cupTeaTask,
		killEleTask, petFishTask, buyStewTask, talkSherlockTask, logShortcutTask;

	RuneliteRequirement killedFire, killedEarth, killedWater, killedAir;

	@Override
	public QuestStep loadStep()
	{
		initializeRequirements();
		setupSteps();

		ConditionalStep doEasy = new ConditionalStep(this, claimReward);

		catchMackerelTask = new ConditionalStep(this, catchMackerel);
		doEasy.addStep(notCatchMackerel, catchMackerelTask);

		petFishTask = new ConditionalStep(this, fillFishbowl);
		petFishTask.addStep(new Conditions(fishBowlSeaweed, tinyNet), petFishFish);
		petFishTask.addStep(fishBowlSeaweed, petFish);
		petFishTask.addStep(fishBowl, petFishMix);

		doEasy.addStep(new Conditions(notPetFish), petFishTask);

		buyCandleTask = new ConditionalStep(this, buyCandle);
		doEasy.addStep(notBuyCandle, buyCandleTask);

		talkSherlockTask = new ConditionalStep(this, talkSherlock);
		doEasy.addStep(notTalkSherlock, talkSherlockTask);

		collectFlaxTask = new ConditionalStep(this, collectFlax);
		doEasy.addStep(notCollectFlax, collectFlaxTask);

		playOrganTask = new ConditionalStep(this, playOrgan);
		doEasy.addStep(notPlayOrgan, playOrganTask);

		buyStewTask = new ConditionalStep(this, buyStew);
		doEasy.addStep(notBuyStew, buyStewTask);

		killEleTask = new ConditionalStep(this, moveToWorkshop);
		killEleTask.addStep(new Conditions(inWorkshop, killedFire, killedEarth, killedWater), killAir);
		killEleTask.addStep(new Conditions(inWorkshop, killedFire, killedEarth), killWater);
		killEleTask.addStep(new Conditions(inWorkshop, killedFire), killEarth);
		killEleTask.addStep(new Conditions(inWorkshop), killFire);
		killEleTask.addStep(inWorkshop, killEle);
		doEasy.addStep(notKillEle, killEleTask);

		plantJuteTask = new ConditionalStep(this, plantJute);
		doEasy.addStep(notPlantJute, plantJuteTask);

		cupTeaTask = new ConditionalStep(this, cupTea);
		doEasy.addStep(notCupTea, cupTeaTask);

		logShortcutTask = new ConditionalStep(this, logShortcut);
		doEasy.addStep(notLogShortcut, logShortcutTask);

		return doEasy;
	}

	@Override
	protected void setupRequirements()
	{
		notCatchMackerel = new VarplayerRequirement(VarPlayerID.KANDARIN_ACHIEVEMENT_DIARY, false, 1);
		notBuyCandle = new VarplayerRequirement(VarPlayerID.KANDARIN_ACHIEVEMENT_DIARY, false, 2);
		notCollectFlax = new VarplayerRequirement(VarPlayerID.KANDARIN_ACHIEVEMENT_DIARY, false, 3);
		notPlayOrgan = new VarplayerRequirement(VarPlayerID.KANDARIN_ACHIEVEMENT_DIARY, false, 4);
		notPlantJute = new VarplayerRequirement(VarPlayerID.KANDARIN_ACHIEVEMENT_DIARY, false, 5);
		notCupTea = new VarplayerRequirement(VarPlayerID.KANDARIN_ACHIEVEMENT_DIARY, false, 6);
		notKillEle = new VarplayerRequirement(VarPlayerID.KANDARIN_ACHIEVEMENT_DIARY, false, 7);
		notPetFish = new VarplayerRequirement(VarPlayerID.KANDARIN_ACHIEVEMENT_DIARY, false, 8);
		notBuyStew = new VarplayerRequirement(VarPlayerID.KANDARIN_ACHIEVEMENT_DIARY, false, 9);
		notTalkSherlock = new VarplayerRequirement(VarPlayerID.KANDARIN_ACHIEVEMENT_DIARY, false, 10);
		notLogShortcut = new VarplayerRequirement(VarPlayerID.KANDARIN_ACHIEVEMENT_DIARY, false, 11);

		killedFire = AchievementDiaryStepManager.getKilledFire();
		killedEarth = AchievementDiaryStepManager.getKilledEarth();
		killedWater = AchievementDiaryStepManager.getKilledWater();
		killedAir = AchievementDiaryStepManager.getKilledAir();

		coins = new ItemRequirement("Coins", ItemCollections.COINS).showConditioned(new Conditions(LogicType.OR, notPetFish, notBuyCandle, notBuyStew));
		bigFishingNet = new ItemRequirement("Big fishing net", ItemID.BIG_NET).showConditioned(notCatchMackerel).isNotConsumed();

		emptyFishbowl = new ItemRequirement("Empty fishbowl", ItemID.FISHBOWL_EMPTY).showConditioned(notPetFish);
		fishBowl = new ItemRequirement("Filled fishbowl", ItemID.FISHBOWL_WATER).showConditioned(notPetFish);
		fishBowlSeaweed = new ItemRequirement("Fishbowl with seaweed", ItemID.FISHBOWL_WATER_WEED).showConditioned(notPetFish);
		tinyNet = new ItemRequirement("Tiny net", ItemID.TINY_NET).showConditioned(notPetFish);
		genericFishbowl = new ItemRequirements(LogicType.OR, "Fishbowl", emptyFishbowl, fishBowl, fishBowlSeaweed).showConditioned(notPetFish);

        seaweed = new ItemRequirement("Seaweed", ItemID.SEAWEED).showConditioned(notPetFish);
        juteSeed = new ItemRequirement("Jute seeds", ItemID.JUTE_SEED).showConditioned(notPlantJute);
        rake = new ItemRequirement("Rake", ItemID.RAKE).showConditioned(notPlantJute).isNotConsumed();
        seedDibber = new ItemRequirement("Seed dibber", ItemID.DIBBER).showConditioned(notPlantJute).isNotConsumed();
        batteredKey = new KeyringRequirement("Battered Key", configManager, KeyringCollection.BATTERED_KEY).showConditioned(notKillEle).isNotConsumed();
        batteredKey.setTooltip("You can get another by searching the bookcase in the house south of the Elemental " +
			"Workshop, then reading the book you get from it");

        combatGear = new ItemRequirement("Combat gear to defeat all types of elementals (level 35)", -1, -1)
			.showConditioned(notKillEle).isNotConsumed();
        combatGear.setDisplayItemId(BankSlotIcons.getCombatGear());

		food = new ItemRequirement("Food", ItemCollections.GOOD_EATING_FOOD, -1);

		inWorkshop = AchievementDiaryStepManager.getInWorkshop();
		setupGenericRequirements();
	}

	public void setupGenericRequirements()
	{
		eleWorkI = new Conditions(LogicType.OR,
			new VarplayerRequirement(VarPlayerID.ELEMENTAL_WORKSHOP_BITS, true, 15),
			new QuestRequirement(QuestHelperQuest.ELEMENTAL_WORKSHOP_I, QuestState.FINISHED)
		);
		((Conditions) eleWorkI).setText("Partial completion of Elemental Workshop I");
	}

	@Override
	protected void setupZones()
	{
		workshop = new Zone(new WorldPoint(2682, 9862, 0), new WorldPoint(2747, 9927, 0));
	}

	public void setupSteps()
	{
		catchMackerel = new NpcStep(this, NpcID._0_44_53_MEMBERFISH, new WorldPoint(2841, 3432, 0),
			"Fish on Catherby beach at the Big Net fishing spots for a mackerel.", true, bigFishingNet);
		buyCandle = new NpcStep(this, NpcID.CANDLE_MAKER, new WorldPoint(2799, 3439, 0),
			"Buy a candle from the candle maker in Catherby.", coins.quantity(3));

		fillFishbowl = new ObjectStep(this, ObjectID.SINK2, new WorldPoint(2830, 3441, 0),
			"Fill an empty fish bowl at a sink.", emptyFishbowl.highlighted(), seaweed, coins.quantity(10));
		fillFishbowl.addIcon(ItemID.FISHBOWL_EMPTY);
		petFish = new NpcStep(this, NpcID.HARRY, new WorldPoint(2833, 3443, 0),
			"Speak with Harry in the Catherby Fishing Shop to get a tiny net.", fishBowlSeaweed, coins.quantity(10));
		petFish.addDialogSteps("Can I get a fish for this bowl?", "I'll take it!");
		petFishMix = new ItemStep(this, "Put seaweed into the fishbowl.", fishBowl.highlighted(), seaweed.highlighted());
		petFishFish = new ObjectStep(this, ObjectID.AQUARIUM, new WorldPoint(2831, 3445, 0),
			"Fish in the aquarium near Harry.", tinyNet);
		collectFlax = new ObjectStep(this, ObjectID.FLAX, new WorldPoint(2742, 3446, 0),
			"Pick 5 flax at the flax field west of Catherby.");
		talkSherlock = new NpcStep(this, NpcID.SHERLOCK, new WorldPoint(2735, 3413, 0),
			"Speak with Sherlock west of Catherby.");
		moveToWorkshop = new ObjectStep(this, ObjectID.ELEMENTAL_WORKSHOP_SPIRALSTAIRSTOP, new WorldPoint(2711, 3498, 0),
			"Enter the Elemental Workshop in Seers' Village.", batteredKey, combatGear, food);
		killEle = new NpcStep(this, NpcID.ELEMENTAL_FIRE, new WorldPoint(2719, 9889, 0),
			"Kill one of each of the 4 elementals.", combatGear, food);
		killEle.addAlternateNpcs(NpcID.ELEMENTAL_WATER, NpcID.ELEMENTAL_AIR, NpcID.ELEMENTAL_EARTH);
		killFire = new NpcStep(this, NpcID.ELEMENTAL_FIRE, new WorldPoint(2719, 9877, 0),
			"Kill one of each of the 4 elementals.", true, combatGear, food);
		killEarth = new NpcStep(this, NpcID.ELEMENTAL_EARTH, new WorldPoint(2700, 9903, 0),
			"Kill one of each of the 4 elementals.", true, combatGear, food);
		killWater = new NpcStep(this, NpcID.ELEMENTAL_WATER, new WorldPoint(2719, 9903, 0),
			"Kill one of each of the 4 elementals.", true, combatGear, food);
		killAir = new NpcStep(this, NpcID.ELEMENTAL_AIR, new WorldPoint(2735, 9891, 0),
			"Kill one of each of the 4 elementals.", true, combatGear, food);
		killEle.addSubSteps(killFire, killEarth, killWater, killAir);

		buyStew = new NpcStep(this, NpcID.FORESTERS_BARTENDER, new WorldPoint(2691, 3494, 0),
			"Talk with the bartender in Seers' Village and buy a stew.", coins.quantity(20));
		buyStew.addDialogSteps("What do you have?", "Could I have some stew please?");
		playOrgan = new ObjectStep(this, ObjectID.KR_CHURCHORGAN, new WorldPoint(2692, 3463, 0),
			"Play the organ in Seers' Village Church.");
		plantJute = new ObjectStep(this, ObjectID.FARMING_HOPS_PATCH_4, new WorldPoint(2669, 3523, 0),
			"Plant 3 jute seeds in the hops patch north west of Seers' Village.", juteSeed.quantity(3),
			seedDibber, rake);
		plantJute.addIcon(ItemID.JUTE_SEED);
		cupTea = new NpcStep(this, NpcID.BROTHER_GALAHAD, new WorldPoint(2612, 3474, 0),
			"Talk with Galahad west of McGrubor's Wood until he gives you some tea.");
		cupTea.addDialogStep("Do you get lonely here on your own?");
		logShortcut = new ObjectStep(this, ObjectID.MINE_LOG_BALANCE1, new WorldPoint(2602, 3477, 0),
			"Cross the log shortcut near to Galahad.");

		claimReward = new NpcStep(this, NpcID.SEERS_DIARY_WEDGE, new WorldPoint(2760, 3476, 0),
			"Talk to the 'Wedge' in front of Camelot Castle to claim your reward!");
		claimReward.addDialogStep("I have a question about my Achievement Diary.");
	}

	@Override
	public List<ItemRequirement> getItemRequirements()
	{
		return Arrays.asList(coins.quantity(33), bigFishingNet, juteSeed.quantity(3),
			seedDibber, rake, batteredKey, genericFishbowl, seaweed, combatGear);
	}

	@Override
	public List<ItemRequirement> getItemRecommended()
	{
		return Collections.singletonList(food);
	}

	@Override
	public List<String> getCombatRequirements()
	{
		return Collections.singletonList("4 level 35 elementals");
	}

	@Override
	public List<Requirement> getGeneralRequirements()
	{
		setupGenericRequirements();
		ArrayList<Requirement> req = new ArrayList<>();
		req.add(new SkillRequirement(Skill.AGILITY, 20, true));
		req.add(new SkillRequirement(Skill.FARMING, 13, true));
		req.add(new SkillRequirement(Skill.FISHING, 16, true));

		req.add(eleWorkI);

		return req;
	}

	@Override
	public List<ItemReward> getItemRewards()
	{
		return Arrays.asList(
			new ItemReward("Kandarin headgear (1)", ItemID.SEERS_HEADBAND_EASY, 1),
			new ItemReward("2,500 Exp. Lamp (Any skill over 30)", ItemID.THOSF_REWARD_LAMP, 1));
	}

	@Override
	public List<UnlockReward> getUnlockRewards()
	{
		return Arrays.asList(
			new UnlockReward("Coal trucks can hold up to 140 coal."),
			new UnlockReward("The Flax keeper will exchange 30 noted flax for 30 noted bow strings daily"),
			new UnlockReward("5% more marks of grace on Seers' Village Rooftop Course"));
	}

	@Override
	public List<PanelDetails> getPanels()
	{
		List<PanelDetails> allSteps = new ArrayList<>();

		PanelDetails catchMackSteps = new PanelDetails("Catch a Mackerel", Collections.singletonList(catchMackerel),
			new SkillRequirement(Skill.FISHING, 16, true), bigFishingNet);
		catchMackSteps.setDisplayCondition(notCatchMackerel);
		catchMackSteps.setLockingStep(catchMackerelTask);
		allSteps.add(catchMackSteps);

		PanelDetails getPetFishSteps = new PanelDetails("Get a Pet Fish", Arrays.asList(fillFishbowl, petFishMix,
			petFish, petFishFish), coins.quantity(10), genericFishbowl, seaweed);
		getPetFishSteps.setDisplayCondition(notPetFish);
		getPetFishSteps.setLockingStep(petFishTask);
		allSteps.add(getPetFishSteps);

		PanelDetails buyCandleSteps = new PanelDetails("Buy a Candle", Collections.singletonList(buyCandle),
			coins.quantity(3));
		buyCandleSteps.setDisplayCondition(notBuyCandle);
		buyCandleSteps.setLockingStep(buyCandleTask);
		allSteps.add(buyCandleSteps);

		PanelDetails talkSherlockSteps = new PanelDetails("Talk to Sherlock", Collections.singletonList(talkSherlock));
		talkSherlockSteps.setDisplayCondition(notTalkSherlock);
		talkSherlockSteps.setLockingStep(talkSherlockTask);
		allSteps.add(talkSherlockSteps);

		PanelDetails collectFlaxSteps = new PanelDetails("Collect 5 Flax", Collections.singletonList(collectFlax));
		collectFlaxSteps.setDisplayCondition(notCollectFlax);
		collectFlaxSteps.setLockingStep(collectFlaxTask);
		allSteps.add(collectFlaxSteps);

		PanelDetails playOrganSteps = new PanelDetails("Play the Church Organ", Collections.singletonList(playOrgan));
		playOrganSteps.setDisplayCondition(notPlayOrgan);
		playOrganSteps.setLockingStep(playOrganTask);
		allSteps.add(playOrganSteps);

		PanelDetails buyStewSteps = new PanelDetails("Buy Stew", Collections.singletonList(buyStew),
			coins.quantity(20));
		buyStewSteps.setDisplayCondition(notBuyStew);
		buyStewSteps.setLockingStep(buyStewTask);
		allSteps.add(buyStewSteps);

		PanelDetails killElesSteps = new PanelDetails("Defeat Elementals", Arrays.asList(moveToWorkshop, killEle),
			eleWorkI, batteredKey, combatGear, food);
		killElesSteps.setDisplayCondition(notKillEle);
		killElesSteps.setLockingStep(killEleTask);
		allSteps.add(killElesSteps);

		PanelDetails plantJuteSteps = new PanelDetails("Plant Jute", Collections.singletonList(plantJute),
			new SkillRequirement(Skill.FARMING, 13, true), juteSeed.quantity(3), seedDibber, rake);
		plantJuteSteps.setDisplayCondition(notPlantJute);
		plantJuteSteps.setLockingStep(plantJuteTask);
		allSteps.add(plantJuteSteps);

		PanelDetails getTeaSteps = new PanelDetails("Cup of Tea with Galahad", Collections.singletonList(cupTea));
		getTeaSteps.setDisplayCondition(notCupTea);
		getTeaSteps.setLockingStep(cupTeaTask);
		allSteps.add(getTeaSteps);

		PanelDetails takeShortcutSteps = new PanelDetails("Log Shortcut", Collections.singletonList(logShortcut),
			new SkillRequirement(Skill.AGILITY, 20, true));
		takeShortcutSteps.setDisplayCondition(notLogShortcut);
		takeShortcutSteps.setLockingStep(logShortcutTask);
		allSteps.add(takeShortcutSteps);

		PanelDetails finishOffSteps = new PanelDetails("Finishing off", Collections.singletonList(claimReward));
		allSteps.add(finishOffSteps);

		return allSteps;
	}
}
