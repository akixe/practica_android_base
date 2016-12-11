# KOMANDA
Aplicación para práctica de fundamentos de Android del master KeepCoding Bootcamp edición III.

## Notas de la Entrega

He cumplido con los objetivos fijados aunque no se ha terminado el trabajo opcional de hacer la interfaz compatible con tabletas. Si se desea ver el trabajo (sin terminar) en este sentido se puede consultar la rama **"tablet"**.

No he querido incluir esta rama debido a que la calidad del código todavía estaba sin pulir demasiado.

## Flujo de funcionamiento de la aplicación

Primero se muestra una lista de mesas del restaurante indicando si estan libres u ocupadas. (según si tienen asignado algún plato al pedido o no)

![](https://dl.dropboxusercontent.com/s/j9jpyjhxg9nohvp/2016-12-11%20at%2019.55.png)

Al pulsar una mesa cargamos ListaPlatosMesaActivity con los platos que contiene el pedido de esta mesa.
![](https://dl.dropboxusercontent.com/s/w7h5gkfp71ttnkv/2016-12-11%20at%2019.56.png)



Al pulsar el floating button con el más de esta mesa se carga el menu del restaurante para seleccionar un plato.
![](https://dl.dropboxusercontent.com/s/73awfgp6jifj1tv/2016-12-11%20at%2019.57.png)

Al seleccionar un plato pasamos a la pantalla de detalle de plato donde podemos ralizar anotaciones e indicar la cantidad. Si pulsamos "guardar" se actualiza la lista de platos de la mesa. Si pulsamos en "quitar" se quita el plato del pedido.

Si pulsamos en un plato de la lista de platos de una mesa podemos editar sus datos. Así mismo si seleccionamos un plato ya existente al añadir un plato del menú se carga el plato existente con sus notas y sus cantidades.
![](https://dl.dropboxusercontent.com/s/73awfgp6jifj1tv/2016-12-11%20at%2019.57.png)



En la lista de platos de una mesa si pulsamos en el icono del recibo nos da la opcion de cerrar la cuenta. Si la cerramos se vacia el pedido de la mesa y pone su estado a "libre"
![](https://dl.dropboxusercontent.com/s/b18v53m9twoasp2/2016-12-11%20at%2020.00.png)


## Notas
Algunas notas:
- Los datos de Mesas y Menu se guardan en dos Singletons para persistir los datos durante la ejecución de la aplicación. Estos datos se descargar al principio desde una API en internet.
- Al poner a 0 la cantidad de un plato se borra el plato de la lista de platos de la mesa


## Mejoras Pendientes
- Los botones de añadir platos y cerrar la cuenta de una mesa se han implementado en el activity ListaPlatosMesaActivity. Esto me ha supuesto un problema no previsto a la hora de adaptar la interfaz a tablet, ya que el fragment del recyclerView de la lista de platos de la mesa no tiene referencia a esos botones. Esto me suponía tener que duplicar la misma funcionalidad. Queda pendiente mejorar la arquitectura de esta pantalla para no tener que repetir código.
- La pantalla de mesas inicial es más pobre de lo pensado. En vez de un texto para "ocupado" "libre" sería mejor incluir un icono que lo indique. Así mismo en vez de esperar a cargar un plato en la mesa
- Las cantidades del plato se anotan en un editText numérico normal. Se pueden añadir botones de "más" y "menos" para facilitar la actualización del campo.
- La gestion de los alergenos es un poco simple, muy mejorable.
- ...



