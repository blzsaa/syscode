# Feladat
A feladat két CRUD microservice készítése Spring framework, és legalább Java 11 használatával

## profile service:
- Legyen egy Student enƟtás, aminek három mezője van: id, név, email cím
- Az id legyen UUID formátumú, a másik két mező sztring
- Legyen négy REST végpont:
  - listázás
  - új felvitel
  - módosítás
  - törlés
- Az email címre legyen formai validáció
- Az adatbázis legyen relációs, viszont önállóan indítható legyen a program (ne kelljen külsö
adatbázist konfigolni)
  - pl.: H2, MariaDB
- Használni kell ORM-et (pl.: Hibernate)
- Az adatbázis létrehozása történjen Liquibase-zel
- Készüljön legalább egy integrációs (Spring kontextet elindító, controllertől a DB-ig lefutó) és
megfelelő mennyiségű unit teszt.
- Teszt tool, amit használni kell: JUnit5 
## address service:
- Legyen benne egy végpont, ami visszaad egy Address objektumot, aminek két mezője van: id,
address
- Az id legyen UUID formátumú, a másik mező sztring
- ide nem kell adatbázis
## egyéb
- Külön indítható legyen a két service
- A végpontok legyenek pl.: Postmanből meghívhatóak, tehát UI-t nem szükséges csinálni
- Legyen benne logolás
- A forráskódot Github-ra kell feltölteni
- A forráskódot Maven-nel lehessen buildelni
  AjánloƩ technológiák
  ezeket nem szükséges használni a megoldásban, lehet más módon is:
- OpenAPI 3.0 (a service-ek közöƫ contractokhoz)
- Lombok
- REST végponton érkező objektum és a DB enƟtás közöƫ mappelés megoldása (Pl.: ModelMapper, MapStruct, ...)
- address serviceben legyen basic autenƟkáció
- Webclient