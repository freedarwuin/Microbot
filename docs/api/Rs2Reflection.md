# Rs2Reflection Class Documentation

## [Back](development.md)

## Overview
La clase `Rs2Reflection` proporciona métodos utilitarios para manipular elementos del juego mediante Java reflection. Esto permite realizar operaciones normalmente inaccesibles a través de la API estándar, como obtener o modificar atributos de entidades del juego e invocar acciones ocultas dentro del cliente del juego.

## Methods

### `getAnimation(NPC npc)`
- **Parámetros**: `NPC npc` - El NPC cuya animación se desea obtener.
- **Retorna**: `int` - Identificador de la animación del NPC, o `-1` si no se encuentra.
- **Descripción**: Recupera el identificador de animación de un NPC determinado identificando dinámicamente el campo correspondiente mediante reflection.

### `getGroundItemActions(ItemComposition item)`
- **Parámetros**: `ItemComposition item` - La composición del ítem a analizar.
- **Retorna**: `String[]` - Array con las posibles acciones para el ítem en el suelo.
- **Descripción**: Extrae las acciones de ítem en el suelo usando reflection para acceder al campo relevante del objeto.

### `setItemId(MenuEntry menuEntry, int itemId)`
- **Parámetros**:
    - `MenuEntry menuEntry` - La entrada del menú a modificar.
    - `int itemId` - ID del ítem a establecer.
- **Descripción**: Establece el ID del ítem en una entrada de menú, permitiendo modificar dinámicamente el contexto de la interacción.

### `getObjectByName(String[] names, boolean exact)`
- **Parámetros**:
    - `String[] names` - Array de nombres de objetos a buscar.
    - `boolean exact` - Indica si la coincidencia debe ser exacta.
- **Retorna**: `ArrayList<Integer>` - Lista de IDs de objetos que coinciden con los nombres.
- **Descripción**: Busca IDs de objetos en las definiciones del juego según sus nombres, con opción de coincidencia exacta o parcial.

### `invokeMenu(int param0, int param1, int opcode, int identifier, int itemId, String option, String target, int x, int y)`
- **Parámetros**: Parámetros que definen el contexto y detalles de la acción del menú a invocar.
- **Descripción**: Invoca directamente una acción de menú del juego mediante reflection, usado típicamente para simular clics y otras interacciones programáticamente.

## Usage Example

Este ejemplo demuestra cómo usar `getAnimation` para obtener la animación de un NPC y mostrarla:

```java
NPC someNpc = ... // obtener instancia de NPC desde el cliente del juego
int animationId = Rs2Reflection.getAnimation(someNpc);
System.out.println("Current NPC Animation ID: " + animationId);
