Anotaciones para mi bonito:

Nota: Para este esquema de implementación se tiene en cuenta que la matriz de símbolos se genera automáticamente, es decir, el sistema no usa listas para simular el movimiento de los rodillos de la tragaperra.

ELEMENTOS:

Enums:
Symbol -> guarda únicamente los diferentes símbolos que puede tener la matriz de la tragaperra, no incluye métodos

Clases:
PayTable -> relaciona un símbolo con sus pesos (bonificaciones). Guarda un objeto Map<Symbol, int[]>. Clase consultada únicamente para saber el retorno del jugador en caso de victoria. Se puede valorar el hacerla estatica o inyectarla a la clase Game.

Board -> núcleo computacional del sistema. Clase encargada de la generación y validación de wins. Cuando Game lo solicita, genera la matriz y, posteriormente, cuando Game lo solitice verifica si hay alguna linia ganadora. ejemplo de retorno a game de la función de validación: [[3, A], [4, CHERRY]], donde A y CHERRY son de tipo Symbol. Los elementos de la matriz generada son de tipo Symbol.

Player -> guarda los datos del usuario, como el saldo disponible junto con otra información que se desee guardar.

Game -> controlador y núcleo funcional del sistema. Relaciona los datos de PayTable, Player y Board e interactua con el usario. gestiona el flujo del juego.