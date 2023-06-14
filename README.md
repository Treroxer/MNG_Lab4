## Lab 1 - 2
W oparciu o przygotowany szablon projektu zaimplementuj aplikację serwerową, która umożliwia zarządzanie koszykiem produktów w sklepie internetowym.

### Wymagania dla aplikacji 
1. Aplikacja udostępnia API zgodne ze standardem REST
2. Dane o koszyku produktów zapisywane są w bazie danych 

#### Wymagania dla API
1. Możliwość utworzenia nowego klienta, wymagane dane
   1. adres e-mail
   2. nazwa użytkownika
2. Możliwość utworzenia koszyka dla użytkownika
3. Przeszukiwanie produktów wg. nazwy
4. Możliwość dodania produktu do koszyka 
   1. produkty rozróżniane są poprzez unikalny identyfikator 
   2. produkty mogą być dodane w różnej liczbie 
5. Możliwość usunięcia produktów z koszyka (wszytkie wystąpienia)
6. Możliwość zmiany liczby produktów w koszyku

#### Wymagania dla modelu danych 
1. Klient opisywany jest przez:
   1. id (long)
   2. nazwę (string)
   3. adres e-mail (string)
2. Jeden klient może posiadać [0 .. n] koszyków
3. Koszyk opisywany jest przez:
   1. id (long)
   2. nazwę (string)
4. Każdy koszyk może zawierać [0 .. n] produktów 
5. Produkt opisywany jest przez
   1. id (long) 
   2. nazwę (string)
   3. jednostę miary (string) 


### Dokumentacja API:
URL: http://localhost:9090/swagger-ui.html

### Konsola bazy H2:
URL: http://localhost:9090/h2-console <br>
Conn str: jdbc:h2:mem:testdb <br>
Użytkownik: sa <br>
Hasło: password