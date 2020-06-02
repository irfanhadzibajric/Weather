# Weather
Naziv Fakulteta: Fakultet za saobraćaj i komunikacije  
Naziv projekta: Aplikacija za vremensku prognozu  
Imena učesnika projekta:  
•	Adis Šehović  
•	Irfan Hadžibajrić  
•	Aldin Emšo  
•	Toni Marjanović  
Datum izrade: 12.05.2020.g.  
Naziv predmeta: Razvoj mobilnih aplikacija  
Ime profesora: doc.dr. Damir Omerašević  
### BAZA PODATAKA  
Prije pokretanja same aplikacije potrebno je kreirati shemu baze podataka pod nazivom users na serveru. Nakon toga potrebno je importovati users.sql u tu shemu.  
 
### KOMUNIKACIJA IZMEĐU BAZE I APLIKACIJE  
Komunikacija baze i aplikacije omogućena je pomoću php-a. U našem slučaju koriste se tri php fajla. Sva tri php fajla potrebno je spremiti u HTDOCS folder, ukoliko koristite XAMPP, a u slučaju WAMP-a php-ove je potrebno spremiti u folder WWW.  

Prvi php fajl (conn.php) služi za konektovanje na samu bazu. U njemu je potrebno izvršiti promjene dvije varijable:  
$mysql_username = "root";  
$mysql_password = "";  
Ove dvije varijable predstavljaju korisnika kojem su dodijeljene privilegije na phpmyadmin-u.  
U php fajlovima register.php i login.php nije potrebno ništa mijenjati.  
 
### PROMJENE KOJE JE POTREBNO IZVRŠITI U ANDROID STUDIU  
-Klasa Backgroundworker:  
 
Potrebno je promijeniti IP adresu u adresu Vašeg računara.  

WeatherActivity:  
Napomena: Redirekcija na web stranicu neće biti u funkciji, jer  neće biti dostavljena uz aplikaciju iz razloga što nije završena.  
 
