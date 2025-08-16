# Rs2Npc Class Documentation

## [Back](development.md)

## Overview
La clase `Rs2Npc` proporciona un conjunto completo de métodos para interactuar con NPCs dentro del juego. Estos métodos incluyen la obtención de NPCs por distintos atributos, validación de su capacidad de interacción, realizar acciones como atacar o robar, y gestionar la visibilidad y el apuntado de NPCs mediante la cámara y el sistema de caminata del juego.

## Methods

### `getNpcByIndex`
- **Descripción**: Obtiene un NPC basado en su índice único.
- **Parámetros**:
    - `index`: `int` - El índice del NPC.
- **Retorna**: `NPC` - El NPC correspondiente al índice dado, o `null` si no existe ningún NPC con ese índice.

### `validateInteractable`
- **Descripción**: Valida si un NPC puede ser interactuado asegurándose de que no sea `null`, caminando hacia su ubicación y girando la cámara hacia él.
- **Parámetros**:
    - `npc`: `NPC` - El NPC a validar.
- **Retorna**: `NPC` - El mismo NPC si es válido para interactuar, `null` de lo contrario.

### `getNpcsForPlayer`
- **Descripción**: Obtiene una lista de NPCs que actualmente interactúan con el jugador.
- **Retorna**: `List<NPC>` - Una lista de NPCs ordenada por distancia al jugador.

### `getHealth`
- **Descripción**: Calcula la salud de un NPC basado en su ratio y escala de salud.
- **Parámetros**:
    - `npc`: `Actor` - El NPC cuya salud se desea determinar.
- **Retorna**: `int` - La salud estimada del NPC.

### `getNpcs`
- **Descripción**: Obtiene un stream de todos los NPCs actualmente en el entorno del juego, filtrando los nulos, muertos y ordenando por proximidad al jugador.
- **Retorna**: `Stream<NPC>` - Un stream de NPCs.

### `interact`
- **Descripción**: Realiza una acción específica sobre un NPC, como "attack" o "talk-to".
- **Parámetros**:
    - `npc`: `NPC` - El NPC con el que interactuar.
    - `action`: `String` - La acción a realizar.
- **Retorna**: `boolean` - `true` si la acción se inició correctamente, `false` de lo contrario.

### `hasLineOfSight`
- **Descripción**: Verifica si el jugador tiene línea de visión clara hacia el NPC.
- **Parámetros**:
    - `npc`: `NPC` - El NPC a verificar.
- **Retorna**: `boolean` - `true` si hay línea de visión, `false` de lo contrario.

### `attack`
- **Descripción**: Inicia un ataque sobre un NPC dado.
- **Parámetros**:
    - `npc`: `NPC` - El NPC a atacar.
- **Retorna**: `boolean` - `true` si el ataque se inició correctamente, `false` de lo contrario.

## Remarks
- **Seguridad de Hilos**: La mayoría de las operaciones que implican acceso a datos del juego se ejecutan en el hilo del cliente para asegurar acceso seguro.
- **Interacción con NPCs**: Esta clase es central para tareas que requieren interacción directa con NPCs, ofreciendo métodos de alto nivel para acciones complejas y métodos de bajo nivel para control preciso.

## Conclusion
La clase `Rs2Npc` es un componente esencial del cliente Microbot, facilitando capacidades avanzadas de interacción con NPCs para tareas de automatización. Sus métodos están diseñados para manejar diversos aspectos del compromiso con NPCs, desde interacciones básicas hasta comportamientos complejos como combate y pathfinding.