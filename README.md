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

## Pruebas de la aplicación

Se realizaron pruebas de funcionamiento con la aplicación de Postman, aqui ejemplos de autenticación correcta con JWT

![imagen](https://github.com/santiforero1018/MCSW-Project/assets/88952698/8b461dcf-3593-4b23-b5f7-2eeba8d0f0df)
![imagen](https://github.com/santiforero1018/MCSW-Project/assets/88952698/2596ef47-4c57-48e1-b8ca-fcc3cf448858)


Las demas funciones implementadas se plantearon de manera similar para probar el funcionamiento con postman.

