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
package net.runelite.client.plugins.microbot.questhelper.helpers.quests.witchspotion;

import net.runelite.client.plugins.microbot.questhelper.panel.PanelDetails;
import net.runelite.client.plugins.microbot.questhelper.questhelpers.BasicQuestHelper;
import net.runelite.client.plugins.microbot.questhelper.requirements.item.ItemRequirement;
import net.runelite.client.plugins.microbot.questhelper.rewards.ExperienceReward;
import net.runelite.client.plugins.microbot.questhelper.rewards.QuestPointReward;
import net.runelite.client.plugins.microbot.questhelper.steps.ConditionalStep;
import net.runelite.client.plugins.microbot.questhelper.steps.NpcStep;
import net.runelite.client.plugins.microbot.questhelper.steps.ObjectStep;
import net.runelite.client.plugins.microbot.questhelper.steps.QuestStep;
import net.runelite.api.Skill;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.gameval.ItemID;
import net.runelite.api.gameval.NpcID;
import net.runelite.api.gameval.ObjectID;

import java.util.*;

public class WitchsPotion extends BasicQuestHelper
{
	//Items Required
	ItemRequirement ratTail, onion, burntMeat, eyeOfNewt;

	QuestStep talkToWitch, killRat, returnToWitch, drinkPotion;

	@Override
	public Map<Integer, QuestStep> loadSteps()
	{
		initializeRequirements();
		setupSteps();
		Map<Integer, QuestStep> steps = new HashMap<>();

		steps.put(0, talkToWitch);

		ConditionalStep getIngredients = new ConditionalStep(this, killRat);
		getIngredients.addStep(ratTail.alsoCheckBank(questBank), returnToWitch);

		steps.put(1, getIngredients);

		steps.put(2, drinkPotion);

		return steps;
	}

	@Override
	protected void setupRequirements()
	{
		ratTail = new ItemRequirement("Rat's tail", ItemID.RATS_TAIL);
		onion = new ItemRequirement("Onion", ItemID.ONION);
		onion.setTooltip("You can pick one from the field north of Rimmington");
		burntMeat = new ItemRequirement("Burnt meat", ItemID.BURNT_MEAT);
		burntMeat.setTooltip("You can use cooked meat on a fire/range to burn it");
		eyeOfNewt = new ItemRequirement("Eye of newt", ItemID.EYE_OF_NEWT);
		eyeOfNewt.setTooltip("You can buy one from Betty in Port Sarim for 3gp");
	}

	public void setupSteps()
	{
		talkToWitch = new NpcStep(this, NpcID.HETTY, new WorldPoint(2968, 3205, 0),
			"Talk to Hetty in Rimmington.", onion, eyeOfNewt, burntMeat);
		talkToWitch.addDialogStep("I am in search of a quest.");
		talkToWitch.addDialogStep("Yes, help me become one with my darker side.");

		killRat = new NpcStep(this, NpcID.RAT_INDOORS, new WorldPoint(2956, 3203, 0), "Kill a rat in the house to the west for a rat tail.", ratTail);
		returnToWitch = new NpcStep(this, NpcID.HETTY, new WorldPoint(2968, 3205, 0),
			"Bring the ingredients to Hetty.", onion, eyeOfNewt, burntMeat, ratTail);

		drinkPotion = new ObjectStep(this, ObjectID.HETTYCAULDRON, new WorldPoint(2967, 3205, 0), "Drink from the cauldron to finish off the quest.");

	}

	@Override
	public List<ItemRequirement> getItemRequirements()
	{
		ArrayList<ItemRequirement> reqs = new ArrayList<>();
		reqs.add(onion);
		reqs.add(burntMeat);
		reqs.add(eyeOfNewt);
		return reqs;
	}

	@Override
	public List<String> getCombatRequirements()
	{
		return Arrays.asList("Rat (level 1)");
	}

	@Override
	public QuestPointReward getQuestPointReward()
	{
		return new QuestPointReward(1);
	}

	@Override
	public List<ExperienceReward> getExperienceRewards()
	{
		return Collections.singletonList(new ExperienceReward(Skill.MAGIC, 325));
	}

	@Override
	public List<PanelDetails> getPanels()
	{
		List<PanelDetails> allSteps = new ArrayList<>();

		allSteps.add(new PanelDetails("Starting off", Collections.singletonList(talkToWitch)));
		allSteps.add(new PanelDetails("Getting a rat's tail", Collections.singletonList(killRat)));
		allSteps.add(new PanelDetails("Make the potion", Collections.singletonList(returnToWitch), ratTail, onion, burntMeat, eyeOfNewt));
		return allSteps;
	}
}
