backend, bazę danych (oraz narzędzie pomocnicze phpmyadmin) uruchamiamy poprzez docker compose
najpierw wykonujemy:
docker compose build
później:
docker compose up -d

na narzędzie phpmyadmin wchodzimy poprzez:
localhost:8183/index.php
dane do logowania do bazy:
host: 172.17.0.1:3306
user: filmuser
haslo: Password123

baza danych stoi na portach:
3306:3306

backend stoi na portach:
8090

aby uruchomic narzedzie testujace typu swagger dla backendu:
localhost:8090/swagger-ui/index.html
