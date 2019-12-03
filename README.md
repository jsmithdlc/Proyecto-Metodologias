# Tarea Alpaca Emblem: Entrega 1

## Items
**IEquipableItem** es la interfaz que es implementada por las clases abstractas **AbstractItem**, la cual refiere a los items no mágicos, 
y **AbstractBook**, la cual refiere a los items mágicos. En la interfaz se declaran los métodos getters y setters para extraer o 
modificar los atributos de los items. Así también, se declaran los métodos para equipar cada una de las unidades (p.ej. equipArcher)
 así como los métodos para recibir el ataque de cierto item (p. ej. recieveBowAttack) y el método de ataque que inicia el ataque 
desde un item a otro junto al de contrataque. Luego, en la clase **AbstractItem** se realiza un override a los métodos que hacen que 
un item no mágico reciba un ataque mágico y  en la clase **AbstractBook** se realiza un override a los méotodos que hacen que un item 
mágico reciba un ataque mágico. Esto dado que un item mágico es siempre fuerte contra uno no mágico y viceversa. Por otro lado, 
en las clases creadas para cada item se especifican, mediante **double dispatch** las fortalezas y debilidades correspondientes al item 
y así como a quién se equipa. En total, existen 5 items no mágicos: **Axe**, **Bow**, **Spear**, **Staff** y **Sword** así como 3 items mágicos: **DarkBook**, 
**LightBook** y**SpiritBook**, los últimos a equiparse por la unidad **Sorcerer** y que implementa cada uno de ellos el tipo de magia definido en 
el nombre.

## Mapa
Es un paquete para crear el mapa de juego así como las locaciones de las unidades y algunos métodos para comparar locaciones,
obtener celdas del mapa y añadirlas. A este paquete no se le hicieron modificaciones para esta entrega.

## Units
La interfaz **IUnit** concierne a las unidades especifica los métodos a implementar en la clase **AbstractUnit** y las subclases de esta
para cada unidad. Se definen en esta los métodos getters y setters, un método para mover una unidad de una locación a otra, otro 
método para verificar si la unidad está viva (checkAlive), un método para verificar el rango de un arma con respecto a la posición 
de una unidad (inRange). Así tambien se incluyen métodos para modificar los hitPoints de una unidad en base al ataque recibido, 
clasificándolas en débil, normal y fuerte, un método para sanar una unidad ocupado por el clérigo, un método para iniciar el combate
entre una unidad y otra, y por último algunos métodos para agregar, eliminar y traspasar items del inventario de una unidad. En total
existen 7 unidades: **Alpaca**, **Archer**, **Cleric**, **Fighter**, **Hero**,  **Sorcerer** y **SwordMaster**. Se creó una clase para
cada una de estas unidades, con **double dispatch** en los métodos para equipar cada item y adicionalmente, en **Alpaca** se hace un override
 al método de ataque para que no ataque y en el **clérigo** igualmente para que solo ocupe el método para sanar.
 
# Tarea Alpaca Emblem: Entrega 2
## Corrección Entrega 1
Ahora los objetos que atacan implementan la interfaz **AttackingItem** mientras que los que no atacan implementan la interfaz **HarmlessItem**. Dentro de la primera de estas interfaces, se declara que los items que atacan deben tener el método "attackItem", el cual realiza un ataque sobre otro item. La segunda interfaz, en cambio, especifica que los items que no atacan hacen uso de un método "useItemOn" que hace efecto sobre otra unidad diréctamente, de acuerdo a las características del item que efectua dicha acción.

No se corrigió el error de diseño de repetición de código debido a la falta de tiempo. Aun así, la manera de corregir estos errores es creando clases abstractas, o incluyendo código en clases abstractas, para poder reutilizar el código en las subclases. Un ejemplo de esto es aplicable en las clases de los items, donde se subieron los métodos de equipamento de items que desde las subclases libros a **AbstractBook**.

## Tactician
Cada jugador está representado mediante una instancia de la clase Tactician.

Esta clase es la intermediaria entre el controlador y el modelo del juego. Posee métodos que hacen efectiva las funcionalidades del modelo del juego para el jugador correspondiente. Este jugador dispone de un set de unidades, que dispone en un mapa y puede manejar a través de métodos como "equipItem", "moveTo", "useItemOn", etc. Cada unidad del jugador puede realizar a lo más 1 acción por turno. Para ello, cada una de las unidades que son propiedad de tal jugador posee una referencia a este. El jugador mantiene una referencia a la unidad seleccionada. A la vez, se dispone de métodos que permiten conocer el estado de la unidad seleccionada y, por lo tanto, de todas las unidades que pertenecen al jugador.

## GameController
Un juego se materializa mediante la creación de una instancia de **GameController**.

Esta posee métodos como "equipItem", "moveTo", "useItemOn" y otros que explotan las funcionalidades añadidas en el modelo del juego. Mantiene también un mapa del juego, referencias a los jugadores que participan en este así como controla el orden de turnos y se reserva la capacidad de añadir unidades a este y eliminar jugadores. Se comunica con cada jugador que participa en la partida y echa a andar las rondas y turnos del juego. En fin, su función es asegurar el funcionamiento del juego de acuerdo a las reglas establecidas y ofrecer al usuario métodos cruciales para su jugabilidad, que le permitirán interactuar con los elementos del juego pertenecientes a este y otros usuarios.

## Factory Design Pattern
A modo de facilitar la creación de unidades e items en el controlador del juego, se implementó un **Abstract Factory Pattern** para la creación fácil de unidades e items. Esto se demuestra a través de las clases **AbstractItemFactory** y **AbstractUnitFactory**, las cuales implementan las interfaces **ItemFactory** y **UnitFactory** respectivamente. A su vez, para cada tipo de unidad e item se creó una subclase que permite crear unidades o items predefenidos mediante **Double Dispatch** de acuerdo a los métodos "createNormalItem", "createStrongItem", "createNormalUnit" y "createStrongUnit". Además se agregaron métodos para crear items y unidades personalizadas ("createItem" y "createUnit") respectivamente, los cuales hacen uso de métodos que fijan los parámetros de la unidad o item a crear, a diferencia de los primeros que hacen uso de atributos predefinidos.

Adicionalmente, para facilitar la creación de mapas, se creó la clase **FieldFactory**, la cual permite crear un mapa de juego simplemente ingresándole el tamaño y conectando las celdas de este de acuerdo a una semilla aleatoria predefinida.

## Observer Pattern
Con el objetivo de facilitar la comunicación eficiente entre las clases que componen un juego de Alpaca Emblem, se aplicó el **Observer Pattern**. Quizás la aplicación más importante de este patrón de diseño fue en la constante actualización del estado del mapa que se tenia que hacer para los jugadores de una partida. Esto por ejemplo cuando un jugador, desde la referencia al mapa que posee, ataca una unidad o mueve la suya o transfiere un item, se deben notificar estos cambios tanto al controlador como al resto de los jugadores. Esto luego, se hace fácil aplicando un Observer. Para ello se creó la clase **MapHandler**, la cual permite vincular la clase **Tactician** al controlador del juego de manera de notificar cambios hechos al mapa del juego por otros jugadores.

A su vez, se aplicó este patrón de diseño para el caso de la muerte de una unidad. En este caso se creó la clase **UnitHandler**, donde el "Listener" en este caso es la clase **Tactician** a la cual se le notifica desde la clase **AbstractUnit** si una unidad que posee ha perdido toda la vida. Esto con el fin de que sea eliminada del juego.

Con una finalidad similar al **UnitHandler**, se creó la clase **HeroHandler**. No obstante, esta clase maneja el caso en que un heroe de un jugador pierda su vida. Nuevamente es el **Tactician** el Listener pero cuando sucede tal evento, su tarea es hacer los procedimientos necesarios para retirarse del juego, eliminando todas sus unidades y su nombre de la lista en el controlador.

Por último, la clase **PlayerHandler** maneja el caso en que se produzca una condición, fuera de las ya vistas, en la que un jugador tenga que salir del juego. En este caso la comunicación se realiza entre la clase **Tactician** y **GameController**, siendo esta última el Listener. En el caso que se gatille el evento, se ordena la remoción del jugador con todas las implicancias ya vistas.





