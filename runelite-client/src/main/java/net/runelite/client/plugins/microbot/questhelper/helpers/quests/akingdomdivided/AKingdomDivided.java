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
package net.runelite.client.plugins.microbot.questhelper.helpers.quests.akingdomdivided;

import net.runelite.client.plugins.microbot.questhelper.bank.banktab.BankSlotIcons;
import net.runelite.client.plugins.microbot.questhelper.collections.ItemCollections;
import net.runelite.client.plugins.microbot.questhelper.panel.PanelDetails;
import net.runelite.client.plugins.microbot.questhelper.questhelpers.BasicQuestHelper;
import net.runelite.client.plugins.microbot.questhelper.questinfo.QuestHelperQuest;
import net.runelite.client.plugins.microbot.questhelper.requirements.Requirement;
import net.runelite.client.plugins.microbot.questhelper.requirements.conditional.Conditions;
import net.runelite.client.plugins.microbot.questhelper.requirements.conditional.NpcCondition;
import net.runelite.client.plugins.microbot.questhelper.requirements.conditional.ObjectCondition;
import net.runelite.client.plugins.microbot.questhelper.requirements.item.ItemRequirement;
import net.runelite.client.plugins.microbot.questhelper.requirements.item.ItemRequirements;
import net.runelite.client.plugins.microbot.questhelper.requirements.player.FreeInventorySlotRequirement;
import net.runelite.client.plugins.microbot.questhelper.requirements.player.SkillRequirement;
import net.runelite.client.plugins.microbot.questhelper.requirements.player.SpellbookRequirement;
import net.runelite.client.plugins.microbot.questhelper.requirements.quest.QuestRequirement;
import net.runelite.client.plugins.microbot.questhelper.requirements.util.LogicType;
import net.runelite.client.plugins.microbot.questhelper.requirements.util.Spellbook;
import net.runelite.client.plugins.microbot.questhelper.requirements.var.VarbitRequirement;
import net.runelite.client.plugins.microbot.questhelper.requirements.zone.Zone;
import net.runelite.client.plugins.microbot.questhelper.requirements.zone.ZoneRequirement;
import net.runelite.client.plugins.microbot.questhelper.rewards.ItemReward;
import net.runelite.client.plugins.microbot.questhelper.rewards.QuestPointReward;
import net.runelite.client.plugins.microbot.questhelper.rewards.UnlockReward;
import net.runelite.client.plugins.microbot.questhelper.steps.*;
import net.runelite.api.QuestState;
import net.runelite.api.Skill;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.gameval.ItemID;
import net.runelite.api.gameval.NpcID;
import net.runelite.api.gameval.ObjectID;

import java.util.*;

public class AKingdomDivided extends BasicQuestHelper
{
	QuestStep talkToMartinHolt, talkToCommanderFullore, talkToCommanderFulloreOutsideHouse, talkToTomasLawry,
		goToLovakengjPub, talkToCabinBoyHerbert, talkToCabinBoyHerbertSidebar, fightJudgeofYama,
		talkToCommanderFulloreAfterYama, talkToCommanderFullore2, talkToMartinHolt2, teleportArcheio,
		pickpocketIstoria, openRosesDiaryCase, talkToMartinHolt3, talkToMartinHoltNoDiary, readRosesNote,
		talkToMartinHolt4;

	QuestStep enterHomeForClueSearch, inspectReceipt, getReceipt, goDownCouncillorsHomeF3toF2,
		goDownCouncillorsHomeF2toF1, goUpCouncillorsHomeF1toF2, goUpCouncillorsHomeF2toF3, judgeOfYamaDetailedStep,
		enterJudgeOfYamaFightPortal, talkToMartinHoltForthosRuins, chopVines, squeezeThroughVines, checkPanel,
		solvePanelPuzzle, readRosesNote2, talkToMartinHoltForthosRuins2, talkToMartinHoltSettlementRuins, killAssassin,
		talkToMartinHoltSettlementRuins2, castFireSpellOnIce, searchIce, openSettlementRuinsPanel, readRosesNote3,
		talkToMartinHoltSettlementRuins3, talkToMartinHoltLeglessFaun, climbUpPillarLeglessFaun, checkLeglessFaunPanel,
		climbDownLeglessFaun, solveStatuesPuzzle, readRosesNote4, inspectCratesInShack, watchCutsceneAfterTalkingToFulloreInBasement, enterLizardTemple,
		talkToKahtbalam, exitLizardTemple, goToEggArea, collectEgg, returntoKahtBalam, talkToKahtbalamAgain,
		openDoorNearKaht, openXamphurGate, openDoorNearKahtNoKey, fightXamphurSidebar, fightXamphur,
		searchTableAfterXamphur, returnToFulloreAgainSidebar, inspectCratesInShack2, talkToFulloreForBurial,
		talkToLordArceuusSidebar, talkToLordArceuus, climbTowerOfMagicStairs, talkToLordHosidius, talkToLadyLova,
		talkToLadyPiscSidebar, talkToLadyPisc, climbDownSewerLadyPisc, talkToLordShayzienSidebar, talkToLordShayzien,
		climbUpLadderLordShayzien, talkToFulloreXericsLookout, talkToAllMembersInXericsLookoutSidebar,
		talkToPiscLookout, talkToArceuusLookout, talkToLovaLookout, talkToShayzienLookout, talkToHosidiusLookout,
		talkToFulloreAboutLovaXericsLookout, talkToKaalMejSan,
		talkToMartinHoltHelpingLadyLova, climbDownStairsShayzienPrison, talkToKaalMejSanGoDownElevator,
		mixDefencePotionWithSulphur, talkToKaalMejSanWithSulphurPotion, useShieldingPotionOnDinhsDoor,
		goDownLadderInKourendWoodland, killBarbarianInKourendWoodland, talkToPhileasRimor,
		goDownLadderInKourendAfterBarbFight, speakWithJoraAndFightAssassin, goUpStairsShayzienPrison,
		fightAssassinHelpingLova, talkToJoraAgain, talkToMori, climbUpStairsArceuusChurchF0toF1,
		climbUpStairsArceuusChurchF1toF2, enterChasmOfFire, inspectWineBarrel, picklockChestInWineBarrel,
		talkToAllMembersInXericsLookoutSidebarTaskFinish, talkToPiscLookoutTaskFinish, talkToArceuusLookoutTaskFinish,
		talkToLovaLookoutTaskFinish, talkToShayzienLookoutTaskFinish, talkToHosidiusLookoutTaskFinish, talkToFulloreAfterHelpingAll,
		watchCutsceneAfterHelpingAll, talkToFulloreAfterHelpingAllAgain, climbDownLadderAndTalkToHosidius,
		talkToHosidiusXericsLookoutFinal, talkToFulloreFinalCutscene, lastCutscene,
		goDownCouncillorsHomeF3toF2WithReceipt, watchCutsceneInShack, enterLizardTempleFirstTime, enterLizardTempleWithEgg,
		enterLizardTempleToFightXamphur, watchCutsceneAfterXamphur, talkToFulloreToFinishQuest;

	ConditionalStep xamphurCutscene, xamphurTableSearch;

	Requirement hasTalkedToTomasLowry, hasBluishKey, inArceuusLibraryHistoricalArchive, inCouncillorsHouseF1,
		inCouncillorsHouseF2, inCouncillorsHouseF3, hasReceipt, hasInspectedReceipt, judgeOfYamaNearby, inPanelZone,
		assassinNearby, hasColdKey, inLeglessFaunF1, inPrisonRoom, inLizardTemple, hasKahtEgg, inEggArea, xamphurNearby,
		xamphurGateNearby, inXamphurRoom, inTowerOfMagic, inWarrens, inShayzienRoom, inLookoutBasement, inLookoutF0, inLookoutF1,
		inLookoutF2, inLookoutF3, helpingLova0, helpingPisc0, helpingHosidius0, helpingShayzien0, helpingArceuus0,
		helpingLova2, helpingPisc2, helpingHosidius2, helpingShayzien2, helpingArceuus2, helpingLova4, helpingPisc4,
		helpingHosidius4, helpingShayzien4, helpingArceuus4, helpingLova6, helpingPisc6, helpingHosidius6, helpingShayzien6,
		helpingArceuus6, helpingLova8, helpingPisc8, helpingHosidius8, helpingArceuus8, helpingLova10, helpingPisc10,
		helpingArceuus10, helpingLova12, helpingLova14, inShayzienPrison, inMountKaruulm, hasSulphurPotion,
		barbarianWarlordNearby, phileasRimorNearby, lovaAssassinNearby, inArceuusChurchF1, inArceuusChurchF2,
		inWineBarrel;

	ItemRequirement combatGearForJudgeOfYama, food, bluishKey, rosesDiary, rosesNote, receipt, antipoison,
		kharedstsMemoirs, anyAxe, rosesNote2, combatGear, fireSpellGear, coldKey, rosesNote3, gamesNecklace, rosesNote4,
		fairyRingStaffOrSkillsNecklace, combatGearForXamphur, kahtEgg, dampKey, defencePotion, volcanicSulphur, moltenGlass,
		darkEssenceBlock, brokenRedirector, sulphurPotion, shieldingPotion, lovaDeclaration, fairyRingStaff, darkNullifier,
		shayzienJournal, radasBlessingOrSkillsNecklace, xericsTalisman;

	Requirement freeInventorySlots;

	Zone arceuusLibraryHistoricalArchive, councillorsHouseF1, councillorsHouseF2, councillorsHouseF3, panelArea1, panelArea2,
		prisonRoom, leglessFaunF1, lizardTemple, eggArea, xamphurRoom, towerOfMagic, warrens, shayzienRoom, lookoutBasement,
		lookoutF0, lookoutF1, lookoutF2, lookoutF3, shayzienPrison, mountKaruulm, arceuusChurchF1, arceuusChurchF2, wineBarrel;

	@Override
	public Map<Integer, QuestStep> loadSteps()
	{
		initializeRequirements();
		setupConditions();
		setupSteps();

		Map<Integer, QuestStep> steps = new HashMap<>();

		steps.put(0, talkToMartinHolt);
		steps.put(2, talkToMartinHolt);
		steps.put(4, talkToCommanderFullore);
		steps.put(6, talkToCommanderFullore);
		steps.put(8, talkToCommanderFulloreOutsideHouse);

		ConditionalStep homeSearch = new ConditionalStep(this, enterHomeForClueSearch);

		homeSearch.addStep(hasTalkedToTomasLowry, goToLovakengjPub);
		homeSearch.addStep(new Conditions(inCouncillorsHouseF3, hasInspectedReceipt), goDownCouncillorsHomeF3toF2WithReceipt);
		homeSearch.addStep(new Conditions(inCouncillorsHouseF2, hasInspectedReceipt), goDownCouncillorsHomeF2toF1);
		homeSearch.addStep(hasInspectedReceipt, talkToTomasLawry);

		homeSearch.addStep(new Conditions(inCouncillorsHouseF3, new Conditions(LogicType.NAND, hasReceipt)), goDownCouncillorsHomeF3toF2);
		homeSearch.addStep(new Conditions(inCouncillorsHouseF1, new Conditions(LogicType.NAND, hasReceipt)), goUpCouncillorsHomeF1toF2);
		homeSearch.addStep(new Conditions(hasReceipt, new Conditions(LogicType.NAND, hasInspectedReceipt)), inspectReceipt);
		homeSearch.addStep(new Conditions(inCouncillorsHouseF2, new Conditions(LogicType.NAND, hasReceipt)), getReceipt);

		steps.put(10, homeSearch);
		steps.put(12, homeSearch);
		steps.put(14, talkToCabinBoyHerbert);
		steps.put(16, talkToCabinBoyHerbert);

		ConditionalStep doTheFightWithYama = new ConditionalStep(this, fightJudgeofYama);
		doTheFightWithYama.addStep(new Conditions(LogicType.NAND, judgeOfYamaNearby), enterJudgeOfYamaFightPortal);
		steps.put(18, doTheFightWithYama);
		steps.put(20, talkToCommanderFulloreAfterYama);
		steps.put(22, talkToCommanderFulloreAfterYama);
		steps.put(24, talkToCommanderFullore2);
		steps.put(26, talkToMartinHolt2);

		steps.put(28, talkToMartinHolt2);

		ConditionalStep gettingRosesDiary = new ConditionalStep(this, teleportArcheio);
		gettingRosesDiary.addStep(new Conditions(inArceuusLibraryHistoricalArchive, hasBluishKey), openRosesDiaryCase);
		gettingRosesDiary.addStep(inArceuusLibraryHistoricalArchive, pickpocketIstoria);
		steps.put(30, gettingRosesDiary);
		steps.put(32, gettingRosesDiary);
		steps.put(34, talkToMartinHolt3);
		steps.put(36, talkToMartinHoltNoDiary);
		steps.put(38, readRosesNote);
		steps.put(40, talkToMartinHolt4);
		steps.put(42, talkToMartinHoltForthosRuins);
		steps.put(44, chopVines);
		steps.put(46, solvePanelPuzzle);
		steps.put(48, solvePanelPuzzle);
		steps.put(50, readRosesNote2);

		ConditionalStep talkToMartinHoltAgain = new ConditionalStep(this, talkToMartinHoltForthosRuins2);
		talkToMartinHoltAgain.addStep(new Conditions(inPanelZone), squeezeThroughVines);

		steps.put(52, talkToMartinHoltAgain);

		ConditionalStep talkToMartinHoltSettlementRuinFight = new ConditionalStep(this, talkToMartinHoltSettlementRuins);
		talkToMartinHoltSettlementRuinFight.addStep(assassinNearby, killAssassin);

		steps.put(54, talkToMartinHoltSettlementRuinFight);
		steps.put(56, talkToMartinHoltSettlementRuinFight);
		steps.put(58, talkToMartinHoltSettlementRuins2);
		steps.put(60, castFireSpellOnIce);

		ConditionalStep openSettlementRuinsPanelConditional = new ConditionalStep(this, searchIce);
		openSettlementRuinsPanelConditional.addStep(hasColdKey, openSettlementRuinsPanel);

		steps.put(62, openSettlementRuinsPanelConditional);
		steps.put(64, readRosesNote3);
		steps.put(66, talkToMartinHoltSettlementRuins3);
		steps.put(68, talkToMartinHoltLeglessFaun);
		steps.put(70, solveStatuesPuzzle);
		steps.put(72, solveStatuesPuzzle);

		ConditionalStep openLeglessFaunPanelConditional = new ConditionalStep(this, climbUpPillarLeglessFaun);
		openLeglessFaunPanelConditional.addStep(new Conditions(inLeglessFaunF1), checkLeglessFaunPanel);

		steps.put(74, openLeglessFaunPanelConditional);
		steps.put(76, readRosesNote4);
		steps.put(78, inspectCratesInShack);

		ConditionalStep goWatchDiscoveryCutscene = new ConditionalStep(this, inspectCratesInShack);
		goWatchDiscoveryCutscene.addStep(inPrisonRoom, watchCutsceneInShack);
		steps.put(80, goWatchDiscoveryCutscene);
		steps.put(82, goWatchDiscoveryCutscene);

		ConditionalStep talkToKaht = new ConditionalStep(this, enterLizardTempleFirstTime);
		talkToKaht.addStep(new Conditions(inLizardTemple), talkToKahtbalam);

		steps.put(84, talkToKaht);
		steps.put(86, talkToKaht);

		ConditionalStep collectTheEgg = new ConditionalStep(this, goToEggArea);
		collectTheEgg.addStep(new Conditions(inEggArea), collectEgg);
		collectTheEgg.addStep(new Conditions(inLizardTemple), exitLizardTemple);

		steps.put(88, collectTheEgg);

		ConditionalStep returnToKaht = new ConditionalStep(this, returntoKahtBalam, kahtEgg);
		returnToKaht.addStep(new Conditions(LogicType.NAND, inLizardTemple), enterLizardTempleWithEgg);
		returnToKaht.addStep(new Conditions(LogicType.NAND, hasKahtEgg), collectTheEgg);
		returnToKaht.addStep(new Conditions(inLizardTemple), talkToKahtbalamAgain);

		steps.put(90, returnToKaht);
		steps.put(92, openDoorNearKaht);
		steps.put(94, openDoorNearKahtNoKey);

		ConditionalStep goToXamphurRoom = new ConditionalStep(this, enterLizardTempleToFightXamphur);
		goToXamphurRoom.addStep(inLizardTemple, openDoorNearKahtNoKey);

		ConditionalStep xamphurFightConditional = new ConditionalStep(this, goToXamphurRoom);
		xamphurFightConditional.addStep(xamphurNearby, fightXamphur);
		xamphurFightConditional.addStep(xamphurGateNearby, openXamphurGate);

		steps.put(96, xamphurFightConditional);
		steps.put(98, xamphurFightConditional);
		steps.put(100, xamphurFightConditional);

		xamphurCutscene = new ConditionalStep(this, goToXamphurRoom, "Watch the Xamphur cutscene.");
		xamphurCutscene.addStep(inXamphurRoom, watchCutsceneAfterXamphur);
		steps.put(102, xamphurCutscene);

		xamphurTableSearch = new ConditionalStep(this, goToXamphurRoom, "Search the table in the north of the" +
			" room you fought Xamphur for some notes.");
		xamphurTableSearch.addStep(inXamphurRoom, searchTableAfterXamphur);
		steps.put(104, xamphurTableSearch);

		ConditionalStep goWatchSecondFulloreCutsceneCutscene = new ConditionalStep(this, inspectCratesInShack2);
		goWatchSecondFulloreCutsceneCutscene.addStep(inPrisonRoom, watchCutsceneAfterTalkingToFulloreInBasement);
		steps.put(106, goWatchSecondFulloreCutsceneCutscene);

		ConditionalStep goWatchBurial = new ConditionalStep(this, talkToFulloreForBurial);
		goWatchBurial.addStep(inPrisonRoom, watchCutsceneAfterTalkingToFulloreInBasement);
		steps.put(108, goWatchBurial);

		ConditionalStep talkingToArceuus = new ConditionalStep(this, climbTowerOfMagicStairs);
		talkingToArceuus.addStep(inTowerOfMagic, talkToLordArceuus);

		steps.put(110, talkingToArceuus);
		steps.put(112, talkToLordHosidius);
		steps.put(114, talkToLadyLova);

		ConditionalStep talkingToLadyPisc = new ConditionalStep(this, climbDownSewerLadyPisc);
		talkingToLadyPisc.addStep(inWarrens, talkToLadyPisc);

		steps.put(116, talkingToLadyPisc);

		ConditionalStep talkingToLordShayzien = new ConditionalStep(this, climbUpLadderLordShayzien);
		talkingToLordShayzien.addStep(inShayzienRoom, talkToLordShayzien);

		steps.put(118, talkingToLordShayzien);
		steps.put(120, talkToFulloreXericsLookout);
		steps.put(122, talkToFulloreXericsLookout);

		XericsLookoutStepper arceuusLookoutStepper = new XericsLookoutStepper(this, talkToArceuusLookout, 0, talkToAllMembersInXericsLookoutSidebar);
		ConditionalStep talkToAllLeadersLookout = new ConditionalStep(this, arceuusLookoutStepper);
		talkToAllLeadersLookout.addStep(helpingArceuus0, arceuusLookoutStepper);
		talkToAllLeadersLookout.addStep(helpingHosidius0, new XericsLookoutStepper(this, talkToHosidiusLookout, -1, talkToAllMembersInXericsLookoutSidebar));
		talkToAllLeadersLookout.addStep(helpingShayzien0, new XericsLookoutStepper(this, talkToShayzienLookout, 1, talkToAllMembersInXericsLookoutSidebar));
		talkToAllLeadersLookout.addStep(helpingLova0, new XericsLookoutStepper(this, talkToLovaLookout, 2, talkToAllMembersInXericsLookoutSidebar));
		talkToAllLeadersLookout.addStep(helpingPisc0, new XericsLookoutStepper(this, talkToPiscLookout, 3, talkToAllMembersInXericsLookoutSidebar));


		ConditionalStep megaStep = new ConditionalStep(this, talkToAllLeadersLookout);

		megaStep.addStep(new Conditions(LogicType.OR, helpingHosidius0, helpingArceuus0, helpingShayzien0, helpingLova0, helpingPisc0), talkToAllLeadersLookout);
		megaStep.addStep(helpingLova2, new XericsLookoutStepper(this, talkToFulloreAboutLovaXericsLookout, 0));

		ConditionalStep talkToKaalMejSanConditional = new ConditionalStep(this, talkToKaalMejSanGoDownElevator);
		talkToKaalMejSanConditional.addStep(inMountKaruulm, talkToKaalMejSan);

		megaStep.addStep(new Conditions(LogicType.OR, helpingArceuus2, helpingArceuus4), talkToKaalMejSanConditional);
		megaStep.addStep(new Conditions(helpingArceuus6, hasSulphurPotion), talkToKaalMejSanWithSulphurPotion);
		megaStep.addStep(helpingArceuus6, mixDefencePotionWithSulphur);
		megaStep.addStep(helpingArceuus8, useShieldingPotionOnDinhsDoor);

		ConditionalStep fightBarbarianWarlord = new ConditionalStep(this, goDownLadderInKourendWoodland);
		fightBarbarianWarlord.addStep(barbarianWarlordNearby, killBarbarianInKourendWoodland);

		megaStep.addStep(new Conditions(LogicType.OR, helpingHosidius2, helpingHosidius4), fightBarbarianWarlord);

		ConditionalStep talkToPhileasConditional = new ConditionalStep(this, goDownLadderInKourendAfterBarbFight);
		talkToPhileasConditional.addStep(phileasRimorNearby, talkToPhileasRimor);

		megaStep.addStep(helpingHosidius6, talkToPhileasConditional);

		ConditionalStep talkToMartinHoltInPrison = new ConditionalStep(this, climbDownStairsShayzienPrison);
		talkToMartinHoltInPrison.addStep(inShayzienPrison, talkToMartinHoltHelpingLadyLova);

		megaStep.addStep(helpingLova4, talkToMartinHoltInPrison);

		ConditionalStep talkToJoraAndFight = new ConditionalStep(this, speakWithJoraAndFightAssassin);
		talkToJoraAndFight.addStep(inShayzienPrison, goUpStairsShayzienPrison);
		talkToJoraAndFight.addStep(lovaAssassinNearby, fightAssassinHelpingLova);

		megaStep.addStep(new Conditions(LogicType.OR, helpingLova6, helpingLova8, helpingLova10), talkToJoraAndFight);
		megaStep.addStep(helpingLova12, talkToJoraAgain);

		ConditionalStep talkToMoriConditional = new ConditionalStep(this, climbUpStairsArceuusChurchF0toF1);
		talkToMoriConditional.addStep(inArceuusChurchF1, climbUpStairsArceuusChurchF1toF2);
		talkToMoriConditional.addStep(inArceuusChurchF2, talkToMori);

		megaStep.addStep(new Conditions(LogicType.OR, helpingPisc2, helpingPisc4, helpingPisc6), talkToMoriConditional);
		megaStep.addStep(helpingPisc8, enterChasmOfFire);

		ConditionalStep enterWineBarrelAndPicklock = new ConditionalStep(this, inspectWineBarrel);
		enterWineBarrelAndPicklock.addStep(inWineBarrel, picklockChestInWineBarrel);

		megaStep.addStep(new Conditions(LogicType.OR, helpingShayzien2, helpingShayzien4), enterWineBarrelAndPicklock);

		XericsLookoutStepper arceuusLookoutStepperFinish = new XericsLookoutStepper(this, talkToArceuusLookoutTaskFinish, 0, talkToAllMembersInXericsLookoutSidebarTaskFinish);
		ConditionalStep talkToAllLeadersLookoutFinish = new ConditionalStep(this, arceuusLookoutStepperFinish);
		talkToAllLeadersLookoutFinish.addStep(helpingArceuus10, arceuusLookoutStepperFinish);
		talkToAllLeadersLookoutFinish.addStep(helpingHosidius8, new XericsLookoutStepper(this, talkToHosidiusLookoutTaskFinish, -1, talkToAllMembersInXericsLookoutSidebarTaskFinish));
		talkToAllLeadersLookoutFinish.addStep(helpingShayzien6, new XericsLookoutStepper(this, talkToShayzienLookoutTaskFinish, 1, talkToAllMembersInXericsLookoutSidebarTaskFinish));
		talkToAllLeadersLookoutFinish.addStep(helpingLova14, new XericsLookoutStepper(this, talkToLovaLookoutTaskFinish, 2, talkToAllMembersInXericsLookoutSidebarTaskFinish));
		talkToAllLeadersLookoutFinish.addStep(helpingPisc10, new XericsLookoutStepper(this, talkToPiscLookoutTaskFinish, 3, talkToAllMembersInXericsLookoutSidebarTaskFinish));

		megaStep.addStep(new Conditions(LogicType.OR, helpingLova14, helpingPisc10, helpingShayzien6, helpingHosidius8, helpingArceuus10), talkToAllLeadersLookoutFinish);

		// 124 is a giant mega step
		steps.put(124, megaStep);

		// then jagex decides to split speaking to each person as one step
		steps.put(126, megaStep);
		steps.put(128, megaStep);
		steps.put(130, megaStep);
		steps.put(132, megaStep);

		steps.put(134, new XericsLookoutStepper(this, talkToFulloreAfterHelpingAll, 0));
		steps.put(136, watchCutsceneAfterHelpingAll);
		steps.put(138, talkToFulloreAfterHelpingAllAgain);

		ConditionalStep speakWithHosidius = new ConditionalStep(this, climbDownLadderAndTalkToHosidius);
		speakWithHosidius.addStep(inLookoutBasement, talkToHosidiusXericsLookoutFinal);

		steps.put(140, new XericsLookoutStepper(this, talkToHosidiusXericsLookoutFinal, -1));

		XericsLookoutStepper talkToFulloreToFinishStepper = new XericsLookoutStepper(this, talkToFulloreFinalCutscene, 0);
		steps.put(142, talkToFulloreToFinishStepper);
		steps.put(144, talkToFulloreToFinishStepper);
		steps.put(146, lastCutscene);
		steps.put(148, talkToFulloreToFinishQuest);
		steps.put(150, talkToFulloreToFinishQuest);

		return steps;
	}

	@Override
	protected void setupZones()
	{
		arceuusLibraryHistoricalArchive = new Zone(new WorldPoint(1535, 10236, 0), new WorldPoint(1571, 10213, 0));
		councillorsHouseF1 = new Zone(new WorldPoint(1670, 3684, 0), new WorldPoint(1683, 3677, 0));
		councillorsHouseF2 = new Zone(new WorldPoint(1670, 3684, 1), new WorldPoint(1683, 3677, 1));
		councillorsHouseF3 = new Zone(new WorldPoint(1670, 3684, 2), new WorldPoint(1683, 3677, 2));
		panelArea1 = new Zone(new WorldPoint(1670, 3577, 0), new WorldPoint(1671, 3576, 0));
		panelArea2 = new Zone(new WorldPoint(1669, 3581, 0), new WorldPoint(1672, 3578, 0));
		leglessFaunF1 = new Zone(new WorldPoint(1766, 3686, 1), new WorldPoint(1773, 3679, 1));
		lizardTemple = new Zone(new WorldPoint(1281, 10109, 0), new WorldPoint(1341, 10051, 0));
		eggArea = new Zone(new WorldPoint(1232, 3624, 0), new WorldPoint(1244, 3612, 0));
		xamphurRoom = new Zone(new WorldPoint(3026, 5895, 0), new WorldPoint(3047, 5943, 0));
		towerOfMagic = new Zone(new WorldPoint(1563, 3802, 1), new WorldPoint(1595, 3836, 1));
		warrens = new Zone(new WorldPoint(1728, 10115, 0), new WorldPoint(1814, 10177, 0));
		shayzienRoom = new Zone(new WorldPoint(1480, 3640, 1), new WorldPoint(1489, 3631, 1));
		lookoutF3 = new Zone(new WorldPoint(1589, 3533, 3), new WorldPoint(1595, 3527, 3));
		lookoutF2 = new Zone(new WorldPoint(1589, 3533, 2), new WorldPoint(1595, 3527, 2));
		lookoutF1 = new Zone(new WorldPoint(1589, 3533, 1), new WorldPoint(1595, 3527, 1));
		lookoutF0 = new Zone(new WorldPoint(1576, 3536, 0), new WorldPoint(1599, 3524, 0));
		lookoutBasement = new Zone(new WorldPoint(1558, 9960, 0), new WorldPoint(1572, 9945, 0));
		shayzienPrison = new Zone(new WorldPoint(1422, 9968, 0), new WorldPoint(1448, 9935, 0));
		mountKaruulm = new Zone(new WorldPoint(1284, 10224, 0), new WorldPoint(1331, 10182, 0));
		arceuusChurchF1 = new Zone(new WorldPoint(1677, 3806, 1), new WorldPoint(1714, 3781, 1));
		arceuusChurchF2 = new Zone(new WorldPoint(1681, 3805, 2), new WorldPoint(1714, 3781, 2));
		wineBarrel = new Zone(new WorldPoint(1890, 9961, 0), new WorldPoint(1907, 9950, 0));
		prisonRoom = new Zone(new WorldPoint(1181, 10262, 0), new WorldPoint(1200, 10270, 0));
	}

	@Override
	protected void setupRequirements()
	{
		combatGearForJudgeOfYama = new ItemRequirement("Melee combat gear to fight Judge of Yama", -1, -1).isNotConsumed();
		combatGearForJudgeOfYama.setDisplayItemId(BankSlotIcons.getMeleeCombatGear());
		combatGearForJudgeOfYama.setTooltip("Judge of Yama is immune to range and magic attacks.");
		bluishKey = new ItemRequirement("Bluish Key", ItemID.AKD_ROSE_KEY);
		bluishKey.setTooltip("You can get another one from pickpocketing Istoria in the Arceuus Library Historical" +
			" Archives");
		rosesDiary = new ItemRequirement("Rose's diary", ItemID.AKD_ROSE_DIARY);
		rosesDiary.setTooltip("You can get another from the south east display case in the Arceuus Library Historical" +
			" Archives");
		rosesNote = new ItemRequirement("Rose's note", ItemID.AKD_ROSE_RIDDLE_1);
		rosesNote.setTooltip("You can get another from Martin Holt, east of Kourend Castle");
		receipt = new ItemRequirement("Receipt", ItemID.AKD_HUGHES_RECEIPT);

		freeInventorySlots = new FreeInventorySlotRequirement(1);

		kharedstsMemoirs = new ItemRequirement("Kharedst's Memoirs for teleports", ItemID.VEOS_KHAREDSTS_MEMOIRS).isNotConsumed();
		anyAxe = new ItemRequirement("Any axe", ItemCollections.AXES).isNotConsumed();
		rosesNote2 = new ItemRequirement("Rose's note", ItemID.AKD_ROSE_RIDDLE_2);
		rosesNote2.setTooltip("You can get another from the panel in the Forthos Ruin");
		combatGear = new ItemRequirement("Combat gear", -1, -1).isNotConsumed();
		fireSpellGear = new ItemRequirement("Runes/equipment to cast FIRE bolt or better", -1);
		fireSpellGear.setDisplayItemId(ItemID.FIRERUNE);
		coldKey = new ItemRequirement("Cold key", ItemID.AKD_SETTLEMENT_RUINS_KEY);
		rosesNote3 = new ItemRequirement("Rose's note", ItemID.AKD_ROSE_RIDDLE_3);
		rosesNote3.setTooltip("You can get another from the panel in the Settlement Ruins south west of the Wintertodt camp.");
		food = new ItemRequirement("Decent food", -1, -1);
		food.setDisplayItemId(BankSlotIcons.getFood());
		gamesNecklace = new ItemRequirement("Games necklace for Wintertodt camp teleport", ItemCollections.GAMES_NECKLACES);
		rosesNote4 = new ItemRequirement("Rose's note", ItemID.AKD_ROSE_RIDDLE_4);
		fairyRingStaff = new ItemRequirement("Staff for Fairy rings", ItemCollections.FAIRY_STAFF);
		fairyRingStaffOrSkillsNecklace = new ItemRequirement("Staff for Fairy rings or a Skills Necklace",
			ItemCollections.FAIRY_STAFF).isNotConsumed();
		fairyRingStaffOrSkillsNecklace.addAlternates(ItemCollections.SKILLS_NECKLACES);
		combatGearForXamphur = new ItemRequirement("Melee or range gear to fight Xamphur.", -1, -1).isNotConsumed();
		combatGearForXamphur.setDisplayItemId(BankSlotIcons.getRangedCombatGear());
		combatGearForXamphur.setTooltip("Xamphur is immune to magic attacks.");
		antipoison = new ItemRequirement("Antipoison", ItemCollections.ANTIPOISONS, 1);

		kahtEgg = new ItemRequirement("Lizardman Egg", ItemID.AKD_LIZARDMAN_EGG);
		kahtEgg.setTooltip("Received during quest.");
		dampKey = new ItemRequirement("Damp Key", ItemID.AKD_LIZARDMAN_KEY);
		dampKey.setTooltip("You can get another from Kaht B'alam");
		defencePotion = new ItemRequirement("Defence Potion (3) or (4)", ItemID._4DOSE1DEFENSE);
		defencePotion.addAlternates(ItemID._3DOSE1DEFENSE);
		volcanicSulphur = new ItemRequirement("Volcanic sulphur", ItemID.LOVAKENGJ_SULPHUR);
		volcanicSulphur.setTooltip("Untradeable. Must be mined in Lovakengj.");
		moltenGlass = new ItemRequirement("Molten glass", ItemID.MOLTEN_GLASS);
		darkEssenceBlock = new ItemRequirement("Dark essence block", ItemID.ARCEUUS_ESSENCE_BLOCK_DARK);
		darkEssenceBlock.setTooltip("Untradeable. Mine a Dense essence block in Arceuus then use the Dark Altar.");
		brokenRedirector = new ItemRequirement("Broken redirector", ItemID.AKD_BROKEN_REDIRECTOR);
		brokenRedirector.setTooltip("You can get a new one by talking to Lord Arceuus at Xeric's Lookout");
		sulphurPotion = new ItemRequirement("Sulphur potion", ItemID.AKD_SULPHUR_POTION);
		shieldingPotion = new ItemRequirement("Shielding potion", ItemID.AKD_SHIELDING_POTION);
		lovaDeclaration = new ItemRequirement("Declaration", ItemID.AKD_COUNCIL_DECLARATION);
		lovaDeclaration.setTooltip("You can get another one from Jorra at the Graveyard of Heroes in Shayzien.");
		darkNullifier = new ItemRequirement("Dark nullifier", ItemID.AKD_DARK_NULLIFIER);
		darkNullifier.setTooltip("You can obtain another from Mori in the Arceuus Church.");
		shayzienJournal = new ItemRequirement("Shayzien Journal", ItemID.AKD_SHAYZIEN_JOURNAL);
		shayzienJournal.setTooltip("You can get another one from the chest inside of the barrel in the Hosidious vinery.");
		radasBlessingOrSkillsNecklace = new ItemRequirement("Rada's blessing or Skills necklace", ItemCollections.SKILLS_NECKLACES);
		radasBlessingOrSkillsNecklace.addAlternates(ItemID.RADA_BLESSING_DUMMY, ItemID.ZEAH_BLESSING_EASY, ItemID.ZEAH_BLESSING_MEDIUM,
			ItemID.ZEAH_BLESSING_HARD, ItemID.ZEAH_BLESSING_ELITE);
		xericsTalisman = new ItemRequirement("Xeric's talisman", ItemID.XERIC_TALISMAN);
	}

	public void setupConditions()
	{
		hasTalkedToTomasLowry = new VarbitRequirement(12302, 1);
		inArceuusLibraryHistoricalArchive = new ZoneRequirement(arceuusLibraryHistoricalArchive);
		judgeOfYamaNearby = new NpcCondition(NpcID.AKD_JUDGE_OF_YAMA_COMBAT);
		hasBluishKey = new ItemRequirements(bluishKey);
		inCouncillorsHouseF1 = new ZoneRequirement(councillorsHouseF1);
		inCouncillorsHouseF2 = new ZoneRequirement(councillorsHouseF2);
		inCouncillorsHouseF3 = new ZoneRequirement(councillorsHouseF3);
		hasReceipt = new ItemRequirements(receipt);
		hasInspectedReceipt = new VarbitRequirement(12298, 1);
		inPanelZone = new ZoneRequirement(panelArea1, panelArea2);
		assassinNearby = new NpcCondition(NpcID.AKD_SETTLEMENT_RUINS_ASSASSIN);
		hasColdKey = new ItemRequirements(coldKey);
		inLeglessFaunF1 = new ZoneRequirement(leglessFaunF1);
		inPrisonRoom = new ZoneRequirement(prisonRoom);
		inLizardTemple = new ZoneRequirement(lizardTemple);
		hasKahtEgg = new ItemRequirements(kahtEgg);
		inEggArea = new ZoneRequirement(eggArea);
		xamphurGateNearby = new ObjectCondition(ObjectID.AKD_XAMPHUR_LAB_GATE_OUTER);
		xamphurNearby = new NpcCondition(NpcID.AKD_XAMPHUR_COMBAT);
		inXamphurRoom = new ZoneRequirement(xamphurRoom);
		inTowerOfMagic = new ZoneRequirement(towerOfMagic);
		inWarrens = new ZoneRequirement(warrens);
		inShayzienRoom = new ZoneRequirement(shayzienRoom);
		inLookoutBasement = new ZoneRequirement(lookoutBasement);
		inLookoutF0 = new ZoneRequirement(lookoutF0);
		inLookoutF1 = new ZoneRequirement(lookoutF1);
		inLookoutF2 = new ZoneRequirement(lookoutF2);
		inLookoutF3 = new ZoneRequirement(lookoutF3);
		inShayzienPrison = new ZoneRequirement(shayzienPrison);
		helpingPisc0 = new VarbitRequirement(12318, 0);
		helpingArceuus0 = new VarbitRequirement(12319, 0);
		helpingLova0 = new VarbitRequirement(12320, 0);
		helpingHosidius0 = new VarbitRequirement(12321, 0);
		helpingShayzien0 = new VarbitRequirement(12322, 0);
		helpingLova2 = new VarbitRequirement(12320, 2);
		helpingLova4 = new VarbitRequirement(12320, 4);
		helpingLova6 = new VarbitRequirement(12320, 6);
		helpingLova8 = new VarbitRequirement(12320, 8);
		helpingLova10 = new VarbitRequirement(12320, 10);
		helpingLova12 = new VarbitRequirement(12320, 12);
		helpingLova14 = new VarbitRequirement(12320, 14);
		helpingArceuus2 = new VarbitRequirement(12319, 2);
		helpingArceuus4 = new VarbitRequirement(12319, 4);
		helpingArceuus6 = new VarbitRequirement(12319, 6);
		helpingArceuus8 = new VarbitRequirement(12319, 8);
		helpingArceuus10 = new VarbitRequirement(12319, 10);
		helpingHosidius2 = new VarbitRequirement(12321, 2);
		helpingHosidius4 = new VarbitRequirement(12321, 4);
		helpingHosidius6 = new VarbitRequirement(12321, 6);
		helpingHosidius8 = new VarbitRequirement(12321, 8);
		helpingPisc2 = new VarbitRequirement(12318, 2);
		helpingPisc4 = new VarbitRequirement(12318, 4);
		helpingPisc6 = new VarbitRequirement(12318, 6);
		helpingPisc8 = new VarbitRequirement(12318, 8);
		helpingPisc10 = new VarbitRequirement(12318, 10);
		helpingShayzien2 = new VarbitRequirement(12322, 2);
		helpingShayzien4 = new VarbitRequirement(12322, 4);
		helpingShayzien6 = new VarbitRequirement(12322, 6);
		inMountKaruulm = new ZoneRequirement(mountKaruulm);
		hasSulphurPotion = new ItemRequirements(sulphurPotion);
		barbarianWarlordNearby = new NpcCondition(NpcID.AKD_BARBARIAN_WARLORD);
		phileasRimorNearby = new NpcCondition(NpcID.PHILEAS_RIMOR_VISIBLE);
		lovaAssassinNearby = new NpcCondition(NpcID.AKD_SHAYZIEN_ASSASSIN);
		inArceuusChurchF1 = new ZoneRequirement(arceuusChurchF1);
		inArceuusChurchF2 = new ZoneRequirement(arceuusChurchF2);
		inWineBarrel = new ZoneRequirement(wineBarrel);
	}

	public void setupSteps()
	{
		talkToMartinHolt = new NpcStep(this, NpcID.AKD_MARTIN_HOLT_VIS_PROTEST, new WorldPoint(1664, 3670, 0), "Talk to Martin Holt east of Kourend Castle.");
		talkToMartinHolt.addDialogStep("Yes.");
		talkToCommanderFullore = new NpcStep(this, NpcID.AKD_LYDIA_FULLORE_VIS, new WorldPoint(1614, 3668, 0), "Talk to Commander Fullore in Kourend Castle.");
		talkToCommanderFullore.addDialogStep("Let's get going.");

		talkToCommanderFulloreOutsideHouse = new NpcStep(this, NpcID.AKD_LYDIA_FULLORE_VIS, new WorldPoint(1676, 3674, 0),
			"Talk to Commander Fullore east of the Kourend Castle.");
		talkToCommanderFullore.addSubSteps(talkToCommanderFulloreOutsideHouse);

		goUpCouncillorsHomeF1toF2 = new ObjectStep(this, ObjectID.FAI_VARROCK_STAIRS, new WorldPoint(1671, 3681, 0), "Climb up the stairs of the Councillor's home.");
		goUpCouncillorsHomeF2toF3 = new ObjectStep(this, ObjectID.AKD_HUGHES_SPIRALSTAIRS, new WorldPoint(1676, 3679, 1), "Climb up the stairs of the Councillor's home.");
		goDownCouncillorsHomeF2toF1 = new ObjectStep(this, ObjectID.FAI_VARROCK_STAIRS_TOP, new WorldPoint(1671, 3681, 1), "Climb down the stairs of the Councillor's home.");
		goDownCouncillorsHomeF3toF2 = new ObjectStep(this, ObjectID.VARROCK_SPIRALSTAIRSTOP, new WorldPoint(1676, 3679, 2), "Climb down the stairs of the Councillor's home.");
		goDownCouncillorsHomeF3toF2WithReceipt = new ObjectStep(this, ObjectID.VARROCK_SPIRALSTAIRSTOP, new WorldPoint(1676, 3679, 2), "Climb down the stairs of the Councillor's home.");

		getReceipt = new ObjectStep(this, ObjectID.AKD_HUGHES_DRAWERS_1_OP, new WorldPoint(1679, 3680, 1),
			"Search the drawers in the east room for the receipt.");
		inspectReceipt = new DetailedQuestStep(this, "Inspect the receipt.", receipt.highlighted());

		talkToTomasLawry = new NpcStep(this, NpcID.PISCQUEST_OFFICIAL_VISIBLE, new WorldPoint(1677, 3682, 0), "Speak with Tomas Lawry on the ground floor of the Councillor's home.");
		talkToTomasLawry.addDialogStep("I've found something that might be useful.");
		talkToTomasLawry.addSubSteps(goDownCouncillorsHomeF2toF1, goDownCouncillorsHomeF3toF2WithReceipt);
		goToLovakengjPub = new NpcStep(this, NpcID.LOVAKENGJ_BARTENDER, new WorldPoint(1569, 3758, 0), "Talk to Fuggy in the pub in southeast Lovakengj.");
		goToLovakengjPub.addDialogSteps("'Jewellery of Jubilation' - Lovakengj", "Had any councillors stay here recently?", "So about the Councillor.");
		((NpcStep) goToLovakengjPub).addTeleport(kharedstsMemoirs.named("Kharedst's memoirs [4]"));

		enterHomeForClueSearch = new DetailedQuestStep(this, new WorldPoint(1676, 3680, 0), "Search the Councillor's home east of Kourend castle for clues.");
		enterHomeForClueSearch.addSubSteps(getReceipt, inspectReceipt,
			goDownCouncillorsHomeF3toF2, goUpCouncillorsHomeF1toF2, goUpCouncillorsHomeF2toF3);

		talkToCabinBoyHerbert = new NpcStep(this, NpcID.CABIN_BOY_HERBERT_PISC_VIS, new WorldPoint(1826, 3691, 0),
			"Prepare to fight the Judge of Yama. This boss uses magic + range prayer so melee is required. Run in the " +
				"gaps of the fire waves to approach the boss.\n\nTalk to Cabin Boy Herbert next to Veos's ship in Port" +
				" Piscarilius to initiate the fight.",
			combatGearForJudgeOfYama, food);
		((NpcStep) talkToCabinBoyHerbert).addTeleport(kharedstsMemoirs.named("Kharedst's memoirs [2]"));
		talkToCabinBoyHerbert.addDialogSteps("'The Fisher's Flute' - Piscarilius", "I'm looking for a councillor.");

		talkToCabinBoyHerbertSidebar = new DetailedQuestStep(this, "Talk to Cabin Boy Herbert next to Veos's ship in " +
			"Port Piscarilius, ready to fight the Judge of Yama.");
		talkToCabinBoyHerbertSidebar.addSubSteps(talkToCabinBoyHerbert);

		fightJudgeofYama = new NpcStep(this, NpcID.AKD_JUDGE_OF_YAMA_COMBAT,
			"Fight the Judge of Yama. This boss uses magic + range prayer so melee is required. Run in the gaps of " +
				"the fire waves to approach the boss.", combatGearForJudgeOfYama, food);

		enterJudgeOfYamaFightPortal = new ObjectStep(this, ObjectID.AKD_JUDGE_PORTAL_ENTER_VIS, new WorldPoint(1823, 3686, 0),
			"Prepare to fight the Judge of Yama. This boss uses magic + range prayer so melee is required. Run in the " +
				"gaps of the fire waves to approach the boss.\n\nEnter the Portal near Veos's ship to fight the Judge of Yama.",
			combatGearForJudgeOfYama, food);

		judgeOfYamaDetailedStep = new DetailedQuestStep(this, "Fight the Judge of Yama. This boss uses magic + range " +
			"prayer so melee is required. Run in the gaps of the fire waves to approach the boss.", combatGearForJudgeOfYama, food);

		judgeOfYamaDetailedStep.addSubSteps(talkToCabinBoyHerbert, fightJudgeofYama, enterJudgeOfYamaFightPortal);

		talkToCommanderFulloreAfterYama = new NpcStep(this, NpcID.AKD_LYDIA_FULLORE_VIS, new WorldPoint(1614, 3668, 0), "Talk to Commander Fullore in Kourend Castle.");

		talkToCommanderFullore2 = new NpcStep(this, NpcID.AKD_LYDIA_FULLORE_VIS, new WorldPoint(1604, 3654, 0), "Talk to Commander Fullore just south of the Kourend Castle.");
		talkToMartinHolt2 = new NpcStep(this, NpcID.AKD_MARTIN_HOLT_VIS_PROTEST, new WorldPoint(1664, 3670, 0), "Talk to Martin Holt again east of the Kourend Castle.");

		teleportArcheio = new NpcStep(this, NpcID.RAIDQUEST_LIBRARY_ARCHIVE_GUARDIAN, new WorldPoint(1625, 3808, 0), "Have Archeio in the Arceuus library teleport you to the Historical Archives.");
		teleportArcheio.addDialogStep("Yes please!");

		pickpocketIstoria = new NpcStep(this, NpcID.RAIDQUEST_LIBRARY_HISTORIAN_3OP, new WorldPoint(1551, 10222, 0), "Pickpocket Istoria in the Arceuus Historical Archives to get the bluish key.");
		pickpocketIstoria.addSubSteps(teleportArcheio);
		pickpocketIstoria.addIcon(ItemID.HIGHWAYMANMASK);
		openRosesDiaryCase = new ObjectStep(this, ObjectID.AKD_ROSE_BOOK_DISPLAY_OP, new WorldPoint(1559, 10219, 0),
			"Search the display case in the south east corner of the Arceuus Historical Archives to get Rose's diary.", bluishKey);

		talkToMartinHolt3 = new NpcStep(this, NpcID.AKD_MARTIN_HOLT_VIS_PROTEST, new WorldPoint(1664, 3670, 0), "Talk to Martin Holt again east of the Kourend Castle.", rosesDiary);
		talkToMartinHoltNoDiary = new NpcStep(this, NpcID.AKD_MARTIN_HOLT_VIS_PROTEST, new WorldPoint(1664, 3670, 0), "Talk to Martin Holt again east of the Kourend Castle.");
		talkToMartinHolt3.addSubSteps(talkToMartinHoltNoDiary);

		readRosesNote = new DetailedQuestStep(this, "Read Rose's note that fell out of the diary.", rosesNote.highlighted());
		talkToMartinHolt4 = new NpcStep(this, NpcID.AKD_MARTIN_HOLT_VIS_PROTEST, new WorldPoint(1664, 3670, 0), "Talk to Martin Holt again east of the Kourend Castle.", rosesNote);

		talkToMartinHoltForthosRuins = new NpcStep(this, NpcID.AKD_MARTIN_HOLT_VIS, new WorldPoint(1673, 3580, 0), "Talk to " +
			"Martin Holt on the north side of the Forthos Ruins.");
		chopVines = new ObjectStep(this, ObjectID.AKD_FORTHOS_VINES_OP, new WorldPoint(1671, 3577, 0), "Chop the vines south of Martin Holt.", anyAxe);
		squeezeThroughVines = new ObjectStep(this, ObjectID.AKD_FORTHOS_VINES_CUT, new WorldPoint(1671, 3577, 0), "Squeeze through the vines.");
		checkPanel = new ObjectStep(this, ObjectID.AKD_FORTHOS_PANEL_OP, new WorldPoint(1672, 3579, 0), "Check the panel on the wall.");
		solvePanelPuzzle = new PuzzleWrapperStep(this, new StonePuzzleStep(this), "Solve the wall panel puzzle.");
		readRosesNote2 = new DetailedQuestStep(this, "Read Rose's note from the panel on the wall.", rosesNote2.highlighted());
		talkToMartinHoltForthosRuins2 = new NpcStep(this, NpcID.AKD_MARTIN_HOLT_VIS, new WorldPoint(1673, 3580, 0), "Talk to Martin Holt again on the north side of the Forthos Ruins.");
		talkToMartinHoltForthosRuins2.addSubSteps(squeezeThroughVines);
		talkToMartinHoltSettlementRuins = new NpcStep(this, NpcID.AKD_MARTIN_HOLT_VIS, new WorldPoint(1545, 3895, 0),
			"Talk to Martin Holt again in the Settlement Ruins south west of the Wintertodt camp. " +
				"Be prepared to fight a level 132 assassin who uses a dragon dagger and dragon darts.", combatGear, food);
		((NpcStep) talkToMartinHoltSettlementRuins).addTeleport(gamesNecklace.named("Games necklace (Wintertodt Camp [5])"));
		talkToMartinHoltSettlementRuins.addDialogStep("Wintertodt Camp.");
		killAssassin = new NpcStep(this, NpcID.AKD_SETTLEMENT_RUINS_ASSASSIN, "Kill the Assassin.", combatGear, food);
		talkToMartinHoltSettlementRuins.addSubSteps(killAssassin);
		talkToMartinHoltSettlementRuins2 = new NpcStep(this, NpcID.AKD_MARTIN_HOLT_VIS, new WorldPoint(1545, 3895, 0), "Talk to Martin Holt again in the Settlement Ruins south west of the Wintertodt camp.");
		castFireSpellOnIce = new PuzzleWrapperStep(this,
			new NpcStep(this, NpcID.AKD_ICE_BLOCK_BIG_OP, new WorldPoint(1541, 3886, 0),
				"Cast FIRE bolt or stronger on the ice chunks south west of Martin Holt in the Settlement Ruins.", fireSpellGear), "Work out what to do to unlock the next wall panel.");
		castFireSpellOnIce.addIcon(ItemID.FIRERUNE);
		searchIce = new PuzzleWrapperStep(this,
			new NpcStep(this, NpcID.AKD_ICE_BLOCK_SMALL_OP, new WorldPoint(1541, 3886, 0), "Search the melted ice chunks to get the Cold key."),
			"Work out what to do to unlock the next wall panel."
		);
		openSettlementRuinsPanel = new ObjectStep(this, ObjectID.AKD_SETTLEMENT_RUINS_PANEL_OP, new WorldPoint(1543, 3892, 0), "Open the panel on the wall in the Settlement Ruins.");
		readRosesNote3 = new DetailedQuestStep(this, "Read Rose's note from the panel on the wall.", rosesNote3.highlighted());
		talkToMartinHoltSettlementRuins3 = new NpcStep(this, NpcID.AKD_MARTIN_HOLT_VIS, new WorldPoint(1545, 3895, 0), "Talk to Martin Holt in the Settlement Ruins again.");
		talkToMartinHoltLeglessFaun = new NpcStep(this, NpcID.AKD_MARTIN_HOLT_VIS, new WorldPoint(1775, 3681, 0), "Talk to Martin Holt at the Legless Faun pub in the south west corner of Port Piscarilius.");
		((NpcStep) talkToMartinHoltLeglessFaun).addTeleport(kharedstsMemoirs.named("Kharedst's memoirs [2]"));
		talkToMartinHoltLeglessFaun.addDialogStep("'The Fisher's Flute' - Piscarilius");
		climbUpPillarLeglessFaun = new ObjectStep(this, ObjectID.AKD_PISCARILIUS_PILLAR_BROKEN_OP, new WorldPoint(1772, 3680, 0), "Climb up the pillar west of Martin Holt again.");
		checkLeglessFaunPanel = new ObjectStep(this, ObjectID.AKD_PISCARILIUS_PANEL_OPEN_OP, new WorldPoint(1768, 3686, 1), "Check the panel on the wall again.");
		checkLeglessFaunPanel.addSubSteps(climbUpPillarLeglessFaun);
		climbDownLeglessFaun = new ObjectStep(this, ObjectID.AKD_PISCARILIUS_BATTLEMENT_OP, new WorldPoint(1772, 3679, 1), "Climb down the wall.");
		solveStatuesPuzzle = new PuzzleWrapperStep(this, new StatuePuzzle(this));
		readRosesNote4 = new DetailedQuestStep(this, "Read Rose's note from the panel on the wall.", rosesNote4.highlighted());

		inspectCratesInShack = new ObjectStep(this, ObjectID.AKD_ROSE_BASEMENT_ENTRY_OP, new WorldPoint(1281, 3763, 0),
			"Inspect the crates in the north west corner of the shack located north east of the farming guild.");
		((ObjectStep) inspectCratesInShack).addTeleport(fairyRingStaffOrSkillsNecklace.named("Fairy ring to CIR or Skills Necklace (Farming Guild [6])"));
		inspectCratesInShack.addDialogSteps("Climb through it.");
		watchCutsceneInShack = new DetailedQuestStep(this, "Watch the cutscene.");
		inspectCratesInShack.addSubSteps(watchCutsceneInShack);
		enterLizardTemple = new ObjectStep(this, ObjectID.MOLCH_TEMPLE_LIZARD_DWELLING, new WorldPoint(1292, 3657, 0), "Enter the Lizard Dwelling south east of the Farming guild.");
		enterLizardTempleFirstTime = new ObjectStep(this, ObjectID.MOLCH_TEMPLE_LIZARD_DWELLING, new WorldPoint(1292, 3657, 0), "Enter the Lizard Dwelling south east of the Farming guild.");
		enterLizardTempleWithEgg = new ObjectStep(this, ObjectID.MOLCH_TEMPLE_LIZARD_DWELLING, new WorldPoint(1292, 3657, 0), "Enter the Lizard Dwelling south east of the Farming guild.");
		enterLizardTempleToFightXamphur = new ObjectStep(this, ObjectID.MOLCH_TEMPLE_LIZARD_DWELLING, new WorldPoint(1292, 3657, 0), "Enter the Lizard Dwelling south east of the Farming guild.");
		talkToKahtbalam = new NpcStep(this, NpcID.MOLCH_TEMPLE_SHAMAN_VIS, new WorldPoint(1330, 10084, 0), "Talk to Kaht B'alam located on the east side of the middle tunnel.");
		talkToKahtbalam.addDialogSteps("Do you think you could help me find a mage?");
		exitLizardTemple = new ObjectStep(this, ObjectID.MOLCH_TEMPLE_EXIT, new WorldPoint(1292, 10077, 0), "Exit the Lizard Temple by jumping down the hole to the west.");

		goToEggArea = new DetailedQuestStep(this, new WorldPoint(1238, 3618, 0), "Search the Lizardman eggs found in the Kebos Lowlands until you find a Lizardman egg. You will be attacked by a level 75 lizardman but you do not need to kill it.", freeInventorySlots);
		collectEgg = new ObjectStep(this, ObjectID.AKD_LIZARD_EGGS_CENTER_OP, "Search the Lizardman eggs found in the Kebos Lowlands until you find a Lizardman egg. You will be attacked by a level 75 lizardman but you do not need to kill it.", freeInventorySlots);
		((ObjectStep) collectEgg).setHideWorldArrow(true);
		((ObjectStep) collectEgg).addAlternateObjects(ObjectID.AKD_LIZARD_EGGS_CORNER_OP);

		collectEgg.addSubSteps(exitLizardTemple);
		collectEgg.addSubSteps(goToEggArea);

		returntoKahtBalam = new DetailedQuestStep(this, "Return to Kaht B'alam with the Lizardman egg.");
		talkToKahtbalamAgain = new NpcStep(this, NpcID.MOLCH_TEMPLE_SHAMAN_VIS, new WorldPoint(1330, 10084, 0), "Talk to Kaht B'alam located on the east side of the middle tunnel.");
		talkToKahtbalamAgain.addDialogSteps("So, about the key to that door...", "So about the door over there...");

		returntoKahtBalam.addSubSteps(collectEgg, talkToKahtbalamAgain, enterLizardTempleWithEgg);
		openDoorNearKaht = new ObjectStep(this, ObjectID.AKD_TEMPLE_DOOR_CLOSED_OP, new WorldPoint(1323, 10080, 0), "Open and enter the door near Kaht B'alam with the damp key.", dampKey);
		openDoorNearKaht.addDialogSteps("Yes.", "So about the door over there...");

		openDoorNearKahtNoKey = new ObjectStep(this, ObjectID.AKD_TEMPLE_DOOR_OPEN, new WorldPoint(1323, 10080, 0), "Enter the door near Kaht B'alam.");
		openDoorNearKahtNoKey.addDialogSteps("Yes.");
		openXamphurGate = new ObjectStep(this, ObjectID.AKD_XAMPHUR_LAB_GATE_OUTER, "Open the gate once you are ready to fight.", combatGearForXamphur);

		fightXamphurSidebar = new DetailedQuestStep(this, "Fight Xamphur.", combatGearForXamphur, food);
		fightXamphurSidebar.addText("Xamphur uses several special mechanics. It is recommended to read or watch a guide on the fight.");
		fightXamphur = new NpcStep(this, NpcID.AKD_XAMPHUR_COMBAT, "Fight Xamphur.", combatGearForXamphur, food);
		fightXamphurSidebar.addSubSteps(openXamphurGate, openDoorNearKahtNoKey, fightXamphur, enterLizardTempleToFightXamphur);

		watchCutsceneAfterXamphur = new DetailedQuestStep(this, "");
		searchTableAfterXamphur = new ObjectStep(this, ObjectID.AKD_XAMPHUR_LAB_TABLE_OP, "");
		returnToFulloreAgainSidebar = new DetailedQuestStep(this, "Return to Commandore Fullore in the shack basement north east of the Farming guild.");
		inspectCratesInShack2 = new ObjectStep(this, ObjectID.AKD_ROSE_BASEMENT_ENTRY_OP, new WorldPoint(1281, 3763, 0),
			"Inspect the crates in the north west corner of the shack located north east of the farming guild.");
		((ObjectStep) inspectCratesInShack2).addTeleport(fairyRingStaffOrSkillsNecklace.named("Fairy ring to CIR or Skills Necklace (Farming Guild [6])"));
		returnToFulloreAgainSidebar.addSubSteps(inspectCratesInShack2);

		talkToFulloreForBurial = new NpcStep(this, NpcID.AKD_LYDIA_FULLORE_VIS, new WorldPoint(1273, 3760, 0),
			"Talk to Commander Fullore east of the Farming Guild to watch the burial cutscene.");
		watchCutsceneAfterTalkingToFulloreInBasement = new DetailedQuestStep(this, "Watch the cutscene.");
		watchCutsceneAfterTalkingToFulloreInBasement.addSubSteps(talkToFulloreForBurial);

		talkToLordArceuusSidebar = new NpcStep(this, NpcID.TROBIN_ARCEUUS_VISIBLE, "Speak to Lord Arceuus in the Tower of Magic, north west of Arceuus.");
		talkToLordArceuus = new NpcStep(this, NpcID.TROBIN_ARCEUUS_VISIBLE, "Speak to Lord Arceuus in the Tower of Magic, north west of Arceuus.");
		climbTowerOfMagicStairs = new ObjectStep(this, ObjectID.ARCQUEST_STAIRS_LOWER_LEFT, new WorldPoint(1585, 3821, 0), "Climb up the stairs in the Tower of Magic in Arceuus.");
		climbTowerOfMagicStairs.addDialogSteps("'Jewellery of Jubilation' - Lovakengj");
		((ObjectStep) climbTowerOfMagicStairs).addTeleport(kharedstsMemoirs.named("Kharedst's memoirs [4]"));
		talkToLordArceuusSidebar.addSubSteps(talkToLordArceuus, climbTowerOfMagicStairs);

		talkToLordHosidius = new NpcStep(this, NpcID.HOSIDIUSQUEST_LORD_VIS, new WorldPoint(1782, 3572, 0), "Speak to Lord Hosidius in his home on the south east side of Hosidius.");
		talkToLordHosidius.addDialogStep("'Lunch by the Lancalliums' - Hosidius");
		((NpcStep) talkToLordHosidius).addTeleport(kharedstsMemoirs.named("Kharedst's memoirs [1]"));
		((NpcStep) talkToLordHosidius).addTeleport(xericsTalisman.named("Xeric's talisman [2]"));

		talkToLadyLova = new NpcStep(this, NpcID.VULCANA_LOVAKENGJ_VIS, new WorldPoint(1484, 3748, 0), "Speak to Lady Lovakengj in the Lovakengj Assembly.");
		talkToLadyLova.addDialogSteps("'Jewellery of Jubilation' - Lovakengj");
		((NpcStep) talkToLadyLova).addTeleport(kharedstsMemoirs.named("Kharedst's memoirs [4]"));
		((NpcStep) talkToLadyLova).addTeleport(xericsTalisman.named("Xeric's talisman [3]"));

		talkToLadyPiscSidebar = new NpcStep(this, NpcID.PISCQUEST_QUEEN_POSTQUEST, "Speak to Lady Piscarilius in the sewers under Port Piscarilius.");
		talkToLadyPisc = new NpcStep(this, NpcID.PISCQUEST_QUEEN_POSTQUEST, new WorldPoint(1764, 10158, 0), "Speak to Lady Piscarilius in the sewers under Port Piscarilius.");
		climbDownSewerLadyPisc = new ObjectStep(this, ObjectID.PISCQUEST_MANHOLE_CLOSED, new WorldPoint(1813, 3745, 0), "Climb down the man hole in Port Piscarilius.");
		((ObjectStep) climbDownSewerLadyPisc).addAlternateObjects(ObjectID.PISCQUEST_MANHOLE_OPEN);
		climbDownSewerLadyPisc.addDialogStep("'The Fisher's Flute' - Piscarilius");
		((ObjectStep) climbDownSewerLadyPisc).addTeleport(kharedstsMemoirs.named("Kharedst's memoirs [2]"));
		talkToLadyPiscSidebar.addSubSteps(climbDownSewerLadyPisc, talkToLadyPisc);

		talkToLordShayzienSidebar = new NpcStep(this, NpcID.SHIRO_SHAYZIEN_VIS, "Speak to Lord Shayzien upstairs of the War Tent in the Shayzien Encampment.");
		talkToLordShayzien = new NpcStep(this, NpcID.SHIRO_SHAYZIEN_VIS, new WorldPoint(1484, 3634, 1), "Speak to Lord Shayzien upstairs of the War Tent in the Shayzien Encampment.");
		climbUpLadderLordShayzien = new ObjectStep(this, ObjectID.SHAYZIEN_LADDER, new WorldPoint(1481, 3633, 0), "Climb up the ladder in the War Tent in the Shayzien Encampment.");
		climbUpLadderLordShayzien.addDialogStep("'History and Hearsay' - Shayzien");
		((ObjectStep) climbUpLadderLordShayzien).addTeleport(kharedstsMemoirs.named("Kharedst's memoirs [3]"));
		talkToLordShayzienSidebar.addSubSteps(talkToLordShayzien, climbUpLadderLordShayzien);

		talkToFulloreXericsLookout = new NpcStep(this, NpcID.AKD_LYDIA_FULLORE_VIS, new WorldPoint(1591, 3528, 0), "Speak to Commander Fullore at Xeric's Lookout located south east of Shayzien.");
		talkToFulloreXericsLookout.addDialogStep("'History and Hearsay' - Shayzien");
		((NpcStep) talkToFulloreXericsLookout).addTeleport(kharedstsMemoirs.named("Kharedst's memoirs [3] and run east"));
		((NpcStep) talkToFulloreXericsLookout).addTeleport(xericsTalisman.named("Xeric's talisman [1]"));

		talkToAllMembersInXericsLookoutSidebar = new DetailedQuestStep(this, "Speak with all the leaders again on each floor of Xeric's Lookout. Lord " +
				"Hosidius can be found in the basement and Lord Arceuus just outside the door of the Lookout.");
		talkToArceuusLookout = new NpcStep(this, NpcID.AKD_TROBIN_ARCEUUS_VIS, new WorldPoint(1579, 3528, 0), "Talk to Lord Arceuus in Xeric's Lookout.");
		talkToHosidiusLookout = new NpcStep(this, NpcID.AKD_KANDUR_HOSIDIUS_VIS, new WorldPoint(1568, 9955, 0), "Talk to Lord Hosidius in Xeric's Lookout.");
		talkToShayzienLookout = new NpcStep(this, NpcID.AKD_SHIRO_SHAYZIEN_VIS, new WorldPoint(1591, 3530, 1), "Talk to Lord Shayzien in Xeric's Lookout.");
		talkToLovaLookout = new NpcStep(this, NpcID.AKD_VULCANA_LOVAKENGJ_VIS, new WorldPoint(1592, 3531, 2), "Talk to Lady Lovakengj in Xeric's Lookout.");
		talkToPiscLookout = new NpcStep(this, NpcID.AKD_SHAUNA_PISCARILIUS_VIS, new WorldPoint(1592, 3531, 3), "Talk to Lady Piscarilius in Xeric's Lookout.");

		talkToAllMembersInXericsLookoutSidebar.addSubSteps(talkToArceuusLookout, talkToHosidiusLookout, talkToShayzienLookout, talkToLovaLookout, talkToPiscLookout);

		talkToFulloreAboutLovaXericsLookout = new NpcStep(this, NpcID.AKD_LYDIA_FULLORE_VIS, new WorldPoint(1591, 3528, 0), "Speak to Commander Fullore in Xeric's Lookout about Lady Lovakengj.");
		talkToFulloreAboutLovaXericsLookout.addDialogSteps("I need some help with Lady Lovakengj.");

		talkToKaalMejSanGoDownElevator = new ObjectStep(this, ObjectID.BRIMSTONE_ELEVATOR_CENTRAL_TILE, new WorldPoint(1311, 3807, 0), "Go to Mount Karuulm, go down the elevator, and speak with Kaal-Mej-San.", brokenRedirector);
		// TODO: Add Rada's blessing 3/4 as alternatives? Not sure if they are reasonable to get before the player does this quest
		((ObjectStep) talkToKaalMejSanGoDownElevator).addTeleport(fairyRingStaff.named("Fairy ring to CIR"));
		talkToKaalMejSan = new NpcStep(this, NpcID.TASAKAAL_MEJ, new WorldPoint(1306, 10205, 0), "Go to Mount Karuulm, go down the elevator, and speak with Kaal-Mej-San.", brokenRedirector);
		talkToKaalMejSan.addSubSteps(talkToKaalMejSanGoDownElevator);

		mixDefencePotionWithSulphur = new DetailedQuestStep(this, "Mix the Defence potion with the Volcanic sulphur.", defencePotion.highlighted(), volcanicSulphur.highlighted());
		talkToKaalMejSanWithSulphurPotion = new NpcStep(this, NpcID.TASAKAAL_MEJ, new WorldPoint(1306, 10205, 0), "Talk to Kaal-Mej-San again.", sulphurPotion);
		useShieldingPotionOnDinhsDoor = new ObjectStep(this, ObjectID.WINT_DOOR, new WorldPoint(1630, 3965, 0), "Use the Shielding potion on the Doors of Dinh in the Wintertodt camp.", shieldingPotion.highlighted());
		useShieldingPotionOnDinhsDoor.addIcon(ItemID.AKD_SHIELDING_POTION);
		((ObjectStep) useShieldingPotionOnDinhsDoor).addTeleport(gamesNecklace.named("Games necklace (Wintertodt Camp [5])"));
		useShieldingPotionOnDinhsDoor.addDialogStep("Wintertodt Camp.");
		goDownLadderInKourendWoodland = new ObjectStep(this, ObjectID.AKD_BARBARIAN_LADDER_TOP, new WorldPoint(1582, 3428, 0), "Fight and kill the Barbarian Warlord (level 91) in the Kourend Woodlands.", combatGear, food);
		((ObjectStep) goDownLadderInKourendWoodland).addTeleport(radasBlessingOrSkillsNecklace.named("Rada's blessing to Kourend Woodland or Skills necklace [5]"));
		killBarbarianInKourendWoodland = new NpcStep(this, NpcID.AKD_BARBARIAN_WARLORD, "Fight and kill the Barbarian Warlord (level 91) in the Kourend Woodlands.", combatGear, food);
		goDownLadderInKourendWoodland.addSubSteps(killBarbarianInKourendWoodland);
		goDownLadderInKourendAfterBarbFight = new ObjectStep(this, ObjectID.AKD_BARBARIAN_LADDER_TOP, new WorldPoint(1582, 3428, 0), "Talk to Phileas Rimor in the Barbarian Warlords prison.");
		talkToPhileasRimor = new NpcStep(this, NpcID.PHILEAS_RIMOR_VISIBLE, "Talk to Phileas Rimor in the Barbarian Warlords prison.");
		talkToPhileasRimor.addSubSteps(goDownLadderInKourendAfterBarbFight);


		climbDownStairsShayzienPrison = new ObjectStep(this, ObjectID.AKD_PRISON_ENTRY, new WorldPoint(1464, 3568, 0), "Talk to Martin Holt in the Shayzien Prison west of the Graveyard of Heroes.");
		climbDownStairsShayzienPrison.addDialogStep("'History and Hearsay' - Shayzien");
		((ObjectStep) climbDownStairsShayzienPrison).addTeleport(kharedstsMemoirs.named("Kharedst's memoirs [3]"));
		talkToMartinHoltHelpingLadyLova = new NpcStep(this, NpcID.AKD_MARTIN_HOLT_JAILED_VIS, new WorldPoint(1437, 9946, 0), "Talk to Martin Holt in the Shayzien Prison west of the Graveyard of Heroes.");
		talkToMartinHoltHelpingLadyLova.addSubSteps(climbDownStairsShayzienPrison);
		speakWithJoraAndFightAssassin = new NpcStep(this, NpcID.LOVAKENGJ_ASSISTANT_VIS, new WorldPoint(1496, 3557, 0), "Speak with Jorra south of the Graveyard of Heroes in Shayzien then fight the Assassin (level 132).", combatGear, food);
		speakWithJoraAndFightAssassin.addDialogSteps("Evil only triumphs when the good do nothing.");
		goUpStairsShayzienPrison = new ObjectStep(this, ObjectID.AKD_PRISON_EXIT, new WorldPoint(1445, 9960, 0), "Speak with Jorra south of the Graveyard of Heroes in Shayzien then fight the Assassin (level 132).", combatGear, food);
		fightAssassinHelpingLova = new NpcStep(this, NpcID.AKD_SHAYZIEN_ASSASSIN, "Speak with Jorra south of the Graveyard of Heroes in Shayzien then fight the Assassin (level 132).", combatGear, food);
		speakWithJoraAndFightAssassin.addSubSteps(goUpStairsShayzienPrison, fightAssassinHelpingLova);
		talkToJoraAgain = new NpcStep(this, NpcID.LOVAKENGJ_ASSISTANT_VIS, new WorldPoint(1496, 3557, 0), "Speak with Jorra again.");

		talkToMori = new NpcStep(this, NpcID.ARCQUEST_MORI_POSTQUEST_VISIBLE, new WorldPoint(1703, 3797, 2), "Talk to Mori on the top floor of the Arceuus church.", darkEssenceBlock, moltenGlass);
		climbUpStairsArceuusChurchF0toF1 = new ObjectStep(this, ObjectID.ARCHEUUS_STAIRS_LOWER_LEFT, new WorldPoint(1680, 3784, 0), "Talk to Mori on the top floor of the Arceuus church.", darkEssenceBlock, moltenGlass);
		climbUpStairsArceuusChurchF0toF1.addDialogStep("'A Dark Disposition' - Arceuus");
		((ObjectStep) climbUpStairsArceuusChurchF0toF1).addTeleport(kharedstsMemoirs.named("Kharedst's memoirs [5]"));
		climbUpStairsArceuusChurchF1toF2 = new ObjectStep(this, ObjectID.ARCHEUUS_STAIRS_LOWER_RIGHT, new WorldPoint(1708, 3793, 1), "Talk to Mori on the top floor of the Arceuus church.", darkEssenceBlock, moltenGlass);

		talkToMori.addSubSteps(climbUpStairsArceuusChurchF0toF1, climbUpStairsArceuusChurchF1toF2);

		enterChasmOfFire = new ObjectStep(this, ObjectID.COF_OVER_FALLOFF2_ROPE, new WorldPoint(1436, 3671, 0), "Enter the Chasm of Fire.", darkNullifier);
		((ObjectStep) enterChasmOfFire).addTeleport(fairyRingStaff.named("Fairy ring to DJR"));

		inspectWineBarrel = new ObjectStep(this, ObjectID.AKD_HOSIDIUS_OFFICE_ENTRY_OP, new WorldPoint(1809, 3544, 0), "Inspect the eastern wine barrel in the Hosidius vinery then enter it. Once inside, picklock the chest to receive a Shayzien journal.");
		inspectWineBarrel.addDialogSteps("Climb through it.");
		inspectWineBarrel.addDialogStep("'Lunch by the Lancalliums' - Hosidius");
		((ObjectStep) inspectWineBarrel).addTeleport(kharedstsMemoirs.named("Kharedst's memoirs [1] and run east"));
		((ObjectStep) inspectWineBarrel).addTeleport(xericsTalisman.named("Xeric's talisman [2]"));
		picklockChestInWineBarrel = new ObjectStep(this, ObjectID.AKD_HOSIDIUS_OFFICE_CHEST_CLOSED, new WorldPoint(1898, 9957, 0), "Inspect the eastern wine barrel in the Hosidius vinery then enter it. Once inside, picklock the chest to receive a Shayzien journal.");

		inspectWineBarrel.addSubSteps(picklockChestInWineBarrel);

		talkToAllMembersInXericsLookoutSidebarTaskFinish = new DetailedQuestStep(this, "Speak with all the leaders again on each floor of Xeric's Lookout.");
		talkToArceuusLookoutTaskFinish = new NpcStep(this, NpcID.AKD_TROBIN_ARCEUUS_VIS, new WorldPoint(1579, 3528, 0), "Talk to Lord Arceuus in Xeric's Lookout.");
		talkToArceuusLookoutTaskFinish.addDialogStep("'History and Hearsay' - Shayzien");
		((NpcStep) talkToArceuusLookoutTaskFinish).addTeleport(kharedstsMemoirs.named("Kharedst's memoirs [3] and run east"));
		((NpcStep) talkToArceuusLookoutTaskFinish).addTeleport(xericsTalisman.named("Xeric's talisman [1]"));
		talkToHosidiusLookoutTaskFinish = new NpcStep(this, NpcID.AKD_KANDUR_HOSIDIUS_VIS, new WorldPoint(1568, 9955, 0), "Talk to Lord Hosidius in Xeric's Lookout.");
		talkToShayzienLookoutTaskFinish = new NpcStep(this, NpcID.AKD_SHIRO_SHAYZIEN_VIS, new WorldPoint(1591, 3530, 1), "Talk to Lord Shayzien in Xeric's Lookout.", shayzienJournal);
		talkToLovaLookoutTaskFinish = new NpcStep(this, NpcID.AKD_VULCANA_LOVAKENGJ_VIS, new WorldPoint(1592, 3531, 2), "Talk to Lady Lovakengj in Xeric's Lookout.", lovaDeclaration);
		talkToPiscLookoutTaskFinish = new NpcStep(this, NpcID.AKD_SHAUNA_PISCARILIUS_VIS, new WorldPoint(1592, 3531, 3), "Talk to Lady Piscarilius in Xeric's Lookout.");

		talkToAllMembersInXericsLookoutSidebarTaskFinish.addSubSteps(talkToArceuusLookoutTaskFinish, talkToHosidiusLookoutTaskFinish,
			talkToShayzienLookoutTaskFinish, talkToLovaLookoutTaskFinish, talkToPiscLookoutTaskFinish);
		talkToFulloreAfterHelpingAll = new NpcStep(this, NpcID.AKD_LYDIA_FULLORE_VIS,
			new WorldPoint(1591, 3528, 0), "Speak to Commander Fullore at Xeric's Lookout.");
		watchCutsceneAfterHelpingAll = new DetailedQuestStep(this, "Watch cutscene.");
		talkToFulloreAfterHelpingAllAgain = new NpcStep(this, NpcID.AKD_LYDIA_FULLORE_VIS,
			new WorldPoint(1591, 3528, 0), "Speak to Commander Fullore again at Xeric's Lookout.");

		talkToHosidiusXericsLookoutFinal = new NpcStep(this, NpcID.AKD_KANDUR_HOSIDIUS_VIS,
			new WorldPoint(1568, 9955, 0), "Speak with Lord Hosidius in Xeric's Lookout basement.");

		talkToFulloreFinalCutscene = new NpcStep(this, NpcID.AKD_LYDIA_FULLORE_VIS,
			new WorldPoint(1591, 3528, 0), "Speak to Commander Fullore to begin the final cutscene.");
		talkToFulloreFinalCutscene.addDialogSteps("Let's do this.");

		lastCutscene = new DetailedQuestStep(this, "Watch the final cutscene.");

		talkToFulloreToFinishQuest = new NpcStep(this, NpcID.AKD_LYDIA_FULLORE_VIS, new WorldPoint(1612, 3669, 0), "Speak with Commander Fullore in Kourend Castle to finish the quest.");
	}

	@Override
	public List<ItemRequirement> getItemRequirements()
	{
		return Arrays.asList(anyAxe, volcanicSulphur, defencePotion, moltenGlass, darkEssenceBlock, fireSpellGear,
			combatGearForJudgeOfYama, combatGearForXamphur, combatGear, food);
	}

	@Override
	public List<ItemRequirement> getItemRecommended()
	{
		return Arrays.asList(kharedstsMemoirs, fairyRingStaffOrSkillsNecklace, gamesNecklace, antipoison,
			radasBlessingOrSkillsNecklace, xericsTalisman);
	}

	@Override
	public List<String> getCombatRequirements()
	{
		ArrayList<String> reqs = new ArrayList<>();
		reqs.add("Lizardman Brute (level 75)");
		reqs.add("Barbarian Warlord (level 91)");
		reqs.add("2 Assassins (level 132)");
		reqs.add("Judge of Yama (level 168)");
		reqs.add("Xamphur (level 239)");
		return reqs;
	}

	@Override
	public QuestPointReward getQuestPointReward()
	{
		return new QuestPointReward(2);
	}

	@Override
	public List<ItemReward> getItemRewards()
	{
		return Arrays.asList(
			new ItemReward("The book of the dead", ItemID.BOOK_OF_THE_DEAD, 1),
			new ItemReward("Antique Lamp (10,000 Exp. Any Skill level 40 or above.", ItemID.THOSF_REWARD_LAMP, 1));
	}

	@Override
	public List<UnlockReward> getUnlockRewards()
	{
		return Arrays.asList(
			new UnlockReward("New respawn point in Kourend Castle."),
			new UnlockReward("Expanded access to the Arceuus spellbook."));
	}

	@Override
	public List<Requirement> getGeneralRequirements()
	{
		List<Requirement> reqs = new ArrayList<>();
		reqs.add(new SkillRequirement(Skill.AGILITY, 54));
		reqs.add(new SkillRequirement(Skill.THIEVING, 52));
		reqs.add(new SkillRequirement(Skill.WOODCUTTING, 52));
		reqs.add(new SkillRequirement(Skill.HERBLORE, 50));
		reqs.add(new SkillRequirement(Skill.MINING, 42));
		reqs.add(new SkillRequirement(Skill.CRAFTING, 38));
		reqs.add(new SkillRequirement(Skill.MAGIC, 35));

		reqs.add(new QuestRequirement(QuestHelperQuest.X_MARKS_THE_SPOT, QuestState.FINISHED));
		reqs.add(new QuestRequirement(QuestHelperQuest.THE_QUEEN_OF_THIEVES, QuestState.FINISHED));
		reqs.add(new QuestRequirement(QuestHelperQuest.THE_DEPTHS_OF_DESPAIR, QuestState.FINISHED));
		reqs.add(new QuestRequirement(QuestHelperQuest.THE_ASCENT_OF_ARCEUUS, QuestState.FINISHED));
		reqs.add(new QuestRequirement(QuestHelperQuest.THE_FORSAKEN_TOWER, QuestState.FINISHED));
		reqs.add(new QuestRequirement(QuestHelperQuest.TALE_OF_THE_RIGHTEOUS, QuestState.FINISHED));
		reqs.add(new SpellbookRequirement(Spellbook.NORMAL));

		return reqs;
	}

	@Override
	public ArrayList<PanelDetails> getPanels()
	{
		ArrayList<PanelDetails> allSteps = new ArrayList<>();
		allSteps.add(new PanelDetails("The Disgraced Councillor's Escape", Arrays.asList(talkToMartinHolt,
			talkToCommanderFullore, enterHomeForClueSearch, talkToTomasLawry, goToLovakengjPub, talkToCabinBoyHerbertSidebar,
			judgeOfYamaDetailedStep, talkToCommanderFulloreAfterYama, talkToCommanderFullore2, talkToMartinHolt2,
			pickpocketIstoria, openRosesDiaryCase, talkToMartinHolt3, readRosesNote, talkToMartinHolt4),
			combatGearForJudgeOfYama, food, kharedstsMemoirs, freeInventorySlots)
		);

		allSteps.add(new PanelDetails("Kourend's Last Princess", Arrays.asList(talkToMartinHoltForthosRuins,
			chopVines, solvePanelPuzzle, readRosesNote2, talkToMartinHoltForthosRuins2, talkToMartinHoltSettlementRuins,
			killAssassin, talkToMartinHoltSettlementRuins2, castFireSpellOnIce, searchIce, openSettlementRuinsPanel,
			readRosesNote3, talkToMartinHoltSettlementRuins3, talkToMartinHoltLeglessFaun, solveStatuesPuzzle,
			checkLeglessFaunPanel, readRosesNote4, inspectCratesInShack, watchCutsceneInShack), anyAxe, fireSpellGear, kharedstsMemoirs,
			gamesNecklace, fairyRingStaffOrSkillsNecklace, combatGear, food)
		);

		allSteps.add(new PanelDetails("The Mysterious Mage", Arrays.asList(enterLizardTempleFirstTime, talkToKahtbalam,
			collectEgg, returntoKahtBalam, openDoorNearKaht, fightXamphurSidebar, xamphurCutscene, xamphurTableSearch,
			returnToFulloreAgainSidebar, watchCutsceneAfterTalkingToFulloreInBasement,
			talkToLordArceuusSidebar, talkToLordHosidius, talkToLadyLova, talkToLadyPiscSidebar, talkToLordShayzienSidebar,
			talkToFulloreXericsLookout, talkToAllMembersInXericsLookoutSidebar), Arrays.asList(fairyRingStaffOrSkillsNecklace, kharedstsMemoirs,
			combatGearForXamphur, food), Arrays.asList(xericsTalisman, antipoison))
		);

		allSteps.add(new PanelDetails("The Council's End", Arrays.asList(talkToFulloreAboutLovaXericsLookout,
			talkToKaalMejSan, mixDefencePotionWithSulphur, talkToKaalMejSanWithSulphurPotion,
			useShieldingPotionOnDinhsDoor, goDownLadderInKourendWoodland, talkToPhileasRimor, talkToMartinHoltHelpingLadyLova,
			speakWithJoraAndFightAssassin, talkToJoraAgain, talkToMori, enterChasmOfFire, inspectWineBarrel,
			talkToAllMembersInXericsLookoutSidebarTaskFinish, talkToFulloreAfterHelpingAll, watchCutsceneAfterHelpingAll,
			talkToFulloreAfterHelpingAllAgain, talkToHosidiusXericsLookoutFinal, talkToFulloreFinalCutscene, lastCutscene, talkToFulloreToFinishQuest),
			Arrays.asList(kharedstsMemoirs, defencePotion, darkEssenceBlock, volcanicSulphur, brokenRedirector, moltenGlass, gamesNecklace,
				combatGear, food, fairyRingStaff),
			Arrays.asList(xericsTalisman, radasBlessingOrSkillsNecklace))
		);

		return allSteps;
	}
}
