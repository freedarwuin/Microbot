package net.runelite.client.plugins.microbot.agility;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigInformation;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;
import net.runelite.client.plugins.microbot.agility.enums.AgilityCourse;

@ConfigGroup("MicroAgility")
@ConfigInformation("Activa el plugin cerca del inicio del curso de agilidad seleccionado. <br />" +
        "<b>Requisitos de los cursos:</b>" +
        "<ul>" +
        "<li> Ape Atoll - Kruk o Ninja greegree equipado. Se recomiendan pociones de energía.</li>" +
        "<li> Shayzien Avanzado - Ballesta y Mith Grapple equipados.</li>" +
        "</ul>")
public interface MicroAgilityConfig extends Config
{

	String selectedCourse = "course";
	String hitpointsThreshold = "hitpointsThreshold";
	String shouldAlch = "shouldAlch";
	String itemsToAlch = "itemsToAlch";

	@ConfigSection(
		name = "General",
		description = "General",
		position = 0,
		closedByDefault = false
	)
	String generalSection = "general";

	@ConfigItem(
		keyName = selectedCourse,
		name = "Course",
		description = "Elige tu curso de agilidad",
		position = 1,
		section = generalSection
	)
	default AgilityCourse agilityCourse()
	{
		return AgilityCourse.CANIFIS_ROOFTOP_COURSE;
	}

	@ConfigItem(
		keyName = hitpointsThreshold,
		name = "Eat at",
        description = "Usa comida cuando los puntos de vida bajen de cierto porcentaje. Si no hay comida en el inventario, el script se detiene. Coloca 0 para desactivar.",
		position = 2,
		section = generalSection
	)
	default int hitpoints()
	{
		return 20;
	}

	@ConfigItem(
		keyName = shouldAlch,
		name = "Alch",
		description = "Usar Alquimia Baja/Alta mientras haces agilidad",
		position = 4,
		section = generalSection
	)
	default boolean alchemy()
	{
		return false;
	}

	@ConfigItem(
		keyName = itemsToAlch,
		name = "Items to Alch",
		description = "Introduce los ítems para alquear, separados por comas (ejemplo: Espada rúnica, Daga de dragón, Armadura de mithril)",
		position = 5,
		section = generalSection
	)
	default String itemsToAlch()
	{
		return "";
	}
}