# Documentación de la enumeración Rs2Food

## [Volver](development.md)

## Descripción general
La enumeración `Rs2Food` define constantes para varios alimentos usados en el juego. Cada alimento está asociado con un identificador (ID), la cantidad que cura (heal) y un nombre descriptivo.

## Constantes de la enumeración

### Alimentos
Cada constante en la enumeración `Rs2Food` representa un tipo específico de alimento, incluyendo sus propiedades de curación y su ID de objeto. A continuación, algunos ejemplos de la enumeración:

- **Dark_Crab**: Representa un Dark Crab que cura 27 puntos.
- **ROCKTAIL**: Representa un Rocktail que cura 23 puntos.
- **MANTA**: Representa un Manta Ray que cura 22 puntos.
- **SHARK**: Representa un Shark que cura 20 puntos.
- **LOBSTER**: Representa un Lobster que cura 12 puntos.
- **TROUT**: Representa un Trout que cura 7 puntos.
- **SALMON**: Representa un Salmon que cura 9 puntos.
- **SWORDFISH**: Representa un Swordfish que cura 14 puntos.
- **TUNA**: Representa un Tuna que cura 10 puntos.
- **MONKFISH**: Representa un Monkfish que cura 16 puntos.
- **SEA_TURTLE**: Representa un Sea Turtle que cura 21 puntos.
- **CAKE**: Representa un Cake que cura 4 puntos.
- **CHOCOLATE_CAKE**: Representa un Chocolate Cake que cura 5 puntos.
- **PLAIN_PIZZA**: Representa un Plain Pizza que cura 7 puntos.
- **MEAT_PIZZA**: Representa un Meat Pizza que cura 8 puntos.
- **ANCHOVY_PIZZA**: Representa un Anchovy Pizza que cura 9 puntos.
- **PINEAPPLE_PIZZA**: Representa un Pineapple Pizza que cura 11 puntos.
- **BREAD**: Representa un Bread que cura 5 puntos.
- **APPLE_PIE**: Representa un Apple Pie que cura 7 puntos.
- **MEAT_PIE**: Representa un Meat Pie que cura 6 puntos.

... y muchos más.

## Métodos

### `getId`
- **Descripción**: Devuelve el ID del alimento, que se utiliza dentro del juego para identificar diferentes tipos de comida.

### `getHeal`
- **Descripción**: Devuelve la cantidad de puntos de vida que el alimento puede restaurar al ser consumido.

### `getName`
- **Descripción**: Proporciona el nombre del alimento, que puede usarse en interfaces del juego y scripts.

### `toString`
- **Descripción**: Devuelve una representación en cadena del alimento, combinando su nombre y valor de curación, útil para mostrar información o depuración.

## Uso
Esta enumeración es particularmente útil para scripts que manejan la salud del jugador, donde los alimentos son necesarios para restaurar puntos de vida. Simplifica el proceso de seleccionar alimentos adecuados según su capacidad de curación y proporciona una manera fácil de referenciarlos por nombre o ID.