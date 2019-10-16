# Tarea1: Entrega 1 Alpaca Emblem

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
 
