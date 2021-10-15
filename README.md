## Einleitung

Dieses Helferlein soll ein genauerer Import des 3a Kontos von VIAC in Portfolio Performance ermöglichen.
VIAC generiert für jede Transaktion einen PDF Report. Leider werden Stückzahlen in den PDF Dateien gerundet. 
Beim Importieren in Portfolio Performance entsteht dadurch eine Abweichung zwischen dem tatsächlichen Depot 
Somit entsteht eine Abweichung zwischen dem tatsächlichen Depotwerten und den importieren Werten in Portfolio Performance.
Dieses Helferlein soll abhile schaffen, indem es Stückzahlen selber anhand von Aktienkurse und investierten Beträge ermittelt.
Die errechneten Transaktionen werden dann als CSV Datei zum Importieren zur Verfügung gestellt.

## Einschränkungen
Bei den im PDF aufgeführten investierten Beträge und möglicherweise auch bei den Aktienkurse handelt es sich ebenfalls um gerundete Werte. 
Da diese Zahlen zur Berechnung der Stückzahl verwendet werden, hat die Stückzahl nachwievor ein Rundungsfehler, jedoch ist die berechnete Zahl genauer wie die im PDF aufgeführte Stückzahl.

## Anleitung
### Vorbedingungen
Das Helferlein ist darauf ausgerichtet ein Depot in Portfolio Performance pro VIAC Portfolio abzubilden, welche sich ein gemeinsames Referenzkonto teilen.

### Schritt für Schritt Anleitung
- Aktuellste Version hier herunterladen.
- Die JAR Datei zusammen mit den VIAC PDF Dateien in einen leeren Ordner kopieren.
- JAR Datei ausführen. Anschliessend werden mehrere CSV Dateien generiert. _(Es werden immer CSV Dateien für 5 Depots generiert. Besitzt man in VIAC weniger Portfolios, bleiben die anderen CSV Dateien leer und können ignoriert werden.)_
- In Portfolio Performance über `File -> Import -> CSV` files eines der geneierten Dateien auswählen.
- Für Referenzkonto als Datentyp `Kontoumsätze` auswählen. Für alle anderen Dateien `Kontoumsätze`. Trennzeichen `Komma (,)`, Enkodierung `windows-1252`. Danach kontrollieren, dass alle Spalten richtig gemappt wurden. _Alternativ können auch über das Zahnrad die folgenden Konfigurationen importiert werden._
- Im nächsten Schritt nicht vergessen das Depot und das Referenzkonto anzupassen. Importiert man Kontoumsätze kann eines der VIAC Depots ausgewählt werden.

## Bekannte Fehler
Werden alle Anteile eines Wertpapieres verkauft, bleibt aufgrund der im Kapitel Einschränkungen erwähnten Rundungsfehler, ein kleiner Restbestand im Bereich 10^-6 übrig. 
Dieser kann auch negativ sein. In diesem Fall muss die Stückzahl der letzte Verkaufstransaktionen in Portfolio Performance manuell angepasst werden, damit der Bestand nach dem Verkauf 0 ist.
