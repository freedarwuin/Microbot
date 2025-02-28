# Documentación de la clase Rs2Npc
## [Volver](development.md)
## Descripción general
La clase `Rs2Npc` proporciona un conjunto completo de métodos para interactuar con los NPC dentro del juego. Estos métodos incluyen la recuperación de los NPC por varios atributos, la validación de la interactividad de los NPC, la realización de acciones como atacar o robar, y el manejo de la visibilidad y la selección de objetivos de los NPC a través de la cámara y los sistemas de caminata del juego.

## Métodos

### `getNpcByIndex`
- **Descripción**: recupera un NPC en función de su índice único.
- **Parámetros**:
- `index`: `int` - El índice del NPC.
- **Devuelve**: `NPC` - El NPC correspondiente al índice dado o `null` si no existe ningún NPC con ese índice.

### `validateInteractable`
- **Descripción**: Valida si se puede interactuar con un NPC asegurándose de que no sea nulo, luego camina hacia su ubicación y gira la cámara hacia él.
- **Parámetros**:
- `npc`: `NPC` - El NPC a validar.
- **Devuelve**: `NPC` - El mismo NPC si es válido para la interacción, `null` en caso contrario.

### `getNpcsForPlayer`
- **Descripción**: Recupera una lista de NPC que están interactuando actualmente con el jugador.
- **Devuelve**: `List<NPC>` - Una lista de NPC ordenados por su distancia del jugador.

### `getHealth`
- **Descripción**: Calcula la salud de un NPC en función de su índice de salud y escala.
- **Parámetros**:
- `npc`: `Actor` - El NPC cuya salud se determinará.
- **Devuelve**: `int` - La salud estimada del PNJ.

### `getNpcs`
- **Descripción**: Obtiene un flujo de todos los PNJ que se encuentran actualmente en el entorno del juego, filtrados por estado no nulo o no muerto, y ordenados por proximidad al jugador.
- **Devuelve**: `Stream<NPC>` - Un flujo de PNJ.

### `interact`
- **Descripción**: Realiza una acción específica en un PNJ, como "atacar" o "hablar con".
- **Parámetros**:
- `npc`: `NPC` - El PNJ con el que interactuar.
- `action`: `String` - La acción a realizar.
- **Devuelve**: `boolean` - `true` si la acción se inició correctamente, `false` en caso contrario.

### `hasLineOfSight`
- **Descripción**: Comprueba si el jugador tiene una línea de visión clara hacia el NPC.
- **Parámetros**:
- `npc`: `NPC` - El NPC para comprobar la visibilidad.
- **Devuelve**: `boolean` - `true` si hay una línea de visión, `false` en caso contrario.

### `attack`
- **Descripción**: Inicia un ataque a un NPC determinado.
- **Parámetros**:
- `npc`: `NPC` - El NPC al que atacar.
- **Devuelve**: `boolean` - `true` si el ataque se inició correctamente, `false` en caso contrario.

## Observaciones
- **Seguridad de subprocesos**: La mayoría de las operaciones que implican la obtención de datos del juego se ejecutan en el subproceso del cliente para garantizar un acceso seguro.
- **Interacción con NPC**: esta clase es fundamental para las tareas que requieren interacción directa con NPC, ya que proporciona métodos de alto nivel para acciones complejas y métodos de bajo nivel para un control preciso.

## Conclusión
La clase `Rs2Npc` es un componente esencial del cliente de Microbot, que facilita capacidades avanzadas de interacción con NPC para tareas de automatización. Sus métodos están diseñados para manejar varios aspectos de la interacción con NPC, desde interacciones básicas hasta comportamientos complejos como el combate y la búsqueda de caminos.