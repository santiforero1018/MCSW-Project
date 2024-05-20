# Proyecto MCSW_M Airetupal Bank
Hecho por: Juan Sebastian Cepeda Saray, Santiago Forero Yate, Ricardo Olarte
## Ejecución
usar el comando:

```bash 
mvn spring-boot:run
```

## Base de datos
MySQL, montada en un contenedor Docker

```bash
docker run --name Airetupal -e MYSQL_ROOT_PASSWORD=1234 -e MYSQL_DATABASE=AiretupalDb -p 3306:3306 -d mysql:latest
```


### Cambios y actualizaciones realizadas en la rama devs
