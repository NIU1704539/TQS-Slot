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


### Flujo de una ronda
1. El jugador elige la apuesta (o mantiene la anterior) o se retira.
2. Se valida la apuesta (> 0 y dentro de los límites).
3. Se comprueba que saldo ≥ apuesta; si no, termina la partida.
4. Se descuenta la apuesta.
5. `Board` hace el spin.
6. Se evalúan las líneas (`checkWins`).
7. Se calcula el premio con `PayTable` y se suma al saldo.
8. Se muestra el resultado y se vuelve al paso 1.

### Casos de prueba
| Área | Casos |
|---|---|
| Constructores | Atributos inicializados; el `Board` recibido es el mismo objeto |
| Validación de apuesta | Negativa, 0, mínimo, máximo y valores justo fuera de los límites |
| Saldo insuficiente | No se juega la ronda, el saldo no cambia y no se hace spin |
| Saldo exacto | Saldo == apuesta permite jugar |
| Ronda sin premio | Saldo final = saldo − apuesta |
| Cálculo del premio | Sin líneas → 0; líneas de 3, 4 y 5 símbolos; varias líneas se suman |
| Ronda con premio | Saldo final = saldo − apuesta + premio |
| Fin de partida | Por retirada del jugador o por saldo insuficiente |