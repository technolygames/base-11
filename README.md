# base11
(EN)<br>
IMPORTANT: There's a version in JDK 17. If you want to watch it, follow this [link](https://github.com/technolygames/base-17)<br>
A program made on Java 11 to manage a grocery. Features:
- Can store data from a graphical table to database table
- Employee, partners and providers data storage
- Employee, partners and providers image storage if it is need to make physical IDs
- Employee, partners, providers, sold and stored items data display on a table or detailed window
- Bill print
- Can be customable with:
* Look and feel
* Customable window icon
* Customable frame image
* Switchable name
- in case of update, download files from internet
- Import/export database
- Exception handling
- Execution threads

To start programming, everything is on "src" folder. In root path there is database, libraries, configuration files and visual media.<br>
Sometimes will be outdated because I don't give too much support to this version

Installation:

- To use without any troubles or errors, it'll need to use a database manager like MySQL or MariaDB (xampp). It's needed to the correct execution of the program. Database can be found on the following path: "data/database/MySQL", called as tienda.sql.
- After of this, you'll need to replace "config.properties" by "preconfig.properties", renaming it as the previous deleted file. I recommend you to copy "preconfig.properties" instead of using the same file.
- In case if you have a MySQL database server previously configurated and completed database import step, you'll need configurate on "Configurar BD" ("DB Config") panel inside in the window called "Herramientas de administrador" ("Administrator Tools") the server connection.

(ES)<br>
IMPORTANTE: hay una versión en JDK 17. Si quieres verla, clic en el siguiente [link](https://github.com/technolygames/base-17)<br>
Programa hecho en Java 11 para administrar una tienda de abarrotes. Sus características:
- Poder guardar, desde una tabla, datos a una base de datos
- Almacena datos de empleados, socios y proveedores
- Almacenamiento de las imágenes de los socios, proveedores y/o empleados en caso de crear una credencial física
- Visualización de los datos de los empleados, socios, proveedores, descuentos, productos vendidos y almacenados tablas o ventanas para ver los datos a más detalle
- Impresión de ticket
- Puede ser personalizado con:
* Look and feel
* Icono de ventana personalizable
* Imagen de ventana personalizable 
* Cambiar el nombre del programa
- En caso de actualización, descarga los archivos de internet
- Importar/Exportar la base de datos
- Gestión de errores
- Hilos de ejecución

Para poder programar, todo el código está en la carpeta "src". En la carpeta raíz del proyecto, están la base de datos, librerias (pom.xml), archivos de configuración y medios visuales.<br>
A veces dejaré desactualizado esta versión porque no le doy mucho soporte

Instalación:

- Para poder usar sin problemas o errores, se debe utilizar el gestor de base de datos MySQL o MariaDB (xampp). Es necesario para el correcto funcionamiento del programa. La base de datos se encuentra en "data/database/MySQL", llamado como tienda.sql.
- Después, debes cambiar el archivo "config.properties" por "preconfig.properties", renombrándolo como el archivo eliminado.
- En caso de que tengas un servidor de base de datos de MySQL ya configurado y hayas completado el paso de la importación de la base de datos, debes configurar, en el panel de "Configurar BD" en Herramientas de administrador, la conexión al servidor.
