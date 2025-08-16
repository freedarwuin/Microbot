# Rs2MiniMap Class Documentation

## [Back](development.md)

## Overview
La clase `Rs2MiniMap` proporciona métodos para traducir coordenadas dentro del juego a puntos en el mini-mapa en el cliente Microbot. Esta funcionalidad es esencial para tareas de navegación y pathfinding que requieren interacción con el mini-mapa.

## Methods

### `localToMinimap(LocalPoint localPoint)`
- **Descripción**: Convierte un `LocalPoint` a su punto correspondiente en el mini-mapa.
- **Parámetros**:
    - `localPoint`: `LocalPoint` - El punto local dentro de la región actual.
- **Retorna**: `Point` - Las coordenadas en el mini-mapa del punto local especificado. Retorna `null` si la entrada es `null` o si el punto no puede ser convertido.