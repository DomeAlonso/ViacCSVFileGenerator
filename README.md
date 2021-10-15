# English
## Introduction
This tool is made to import transactions from the VIAC 3a pillar into Portfolio Performance in a more accurate way than by using the standard file import.
This is due to the fact that VIAC uses rounded values in their reports. When importing the PDFs from VIAC, this issue leads to a deviation from the actual portfolio.
This tool should help reduce the error in calculating the quantities based on stock prices and the amount invested instead of using the rounded quantities.  
  
**Currently, this tool only supports VIAC documents in German. That's why most of the guide is written in German only at the moment.
If somebody receives documents from VIAC in another language than German, feel free to create an issue and I'll try to implement it for you.**


# Deutsch
## Einleitung

Dieses Helferlein soll ein genauerer Import des 3a Kontos von VIAC in Portfolio Performance ermöglichen.
VIAC generiert für jede Transaktion einen PDF-Report. Leider werden Stückzahlen in den PDF-Dateien gerundet. 
Beim Importieren in Portfolio Performance entsteht dadurch eine Abweichung zwischen dem tatsächlichen Depot und den importieren Werten in Portfolio Performance.
Dieses Helferlein soll Abhilfe schaffen, indem es Stückzahlen selber anhand von Aktienkurse und investierten Beträge ermittelt.
Die errechneten Transaktionen werden dann als CSV Datei zum Importieren zur Verfügung gestellt.

## Einschränkungen
Bei den im PDF aufgeführten investierten Beträge und möglicherweise auch bei den Aktienkursen handelt es sich ebenfalls um gerundete Werte. 
Da diese Zahlen zur Berechnung der Stückzahl verwendet werden, hat die Stückzahl nach wie vor ein Rundungsfehler, jedoch ist die berechnete Zahl genauer wie die im PDF aufgeführte Stückzahl.

## Anleitung
### Vorbedingungen
Das Helferlein ist darauf ausgerichtet, ein Depot in Portfolio Performance pro VIAC Portfolio abzubilden, welche sich ein gemeinsames Referenzkonto teilen.  
![Portfolio Performance Depot Example](/docs/PP_example.jpg)

### Schritt für Schritt Anleitung
- Aktuellste Version hier herunterladen.
- Die JAR-Datei zusammen mit den VIAC PDF-Dateien in einen leeren Ordner kopieren.
- JAR-Datei ausführen. Anschliessend werden mehrere CSV-Dateien generiert. _(Es werden immer CSV-Dateien für 5 Depots generiert. Besitzt man in VIAC weniger Portfolios, bleiben die anderen CSV-Dateien leer und können ignoriert werden.)_  
Anschliessend erscheint eine Meldung ob die Generierung fehlerfrei geklappt hat.  
**Sollte der Import einer Datei nicht geklappt haben, kann ein Issue erstellt werden.**
- In Portfolio Performance über `File -> Import -> CSV` files eines der generierten Dateien auswählen.
- Für Referenzkonto, als Datentyp `Kontoumsätze` auswählen. Für alle anderen Dateien `Kontoumsätze`. Trennzeichen `Komma (,)`, Enkodierung `windows-1252`. Danach kontrollieren, dass alle Spalten richtig gemappt wurden. _Alternativ können auch über das Zahnrad die folgenden Konfigurationen importiert werden._
![Portfolio Performance Import Settings](/docs/ImportSettings.jpg)
- Im nächsten Schritt nicht vergessen das Depot und das Referenzkonto anzupassen. Importiert man Kontoumsätze kann eines der VIAC Depots ausgewählt werden.

## Unterstützte Dokumente
Zurzeit sind folgenden Dokumente in Deutsch der 3a Säule unterstützt und getestet:
- Kauf
- Verkauf
- Einlage
- Verwaltungsgebühren
- Zins
- Dividende  
  
**Sollte du Dokumente von VIAC erhalten haben, die noch nicht unterstützt werden, lass es mich mit einem neuen Issue wissen. Ich werde es anschliessend versuchen zu implementieren.  
Falls Bedarf für andere Sprachen oder anderen Produkten von VIAC besteht, erfasse ebenfalls ein Issue.**


## Bekannte Fehler
Werden alle Anteile eines Wertpapieres verkauft, bleibt aufgrund der im Kapitel Einschränkungen erwähnten Rundungsfehler, ein kleiner Restbestand im Bereich 10^-6 übrig. 
Dieser kann auch negativ sein. In diesem Fall muss die Stückzahl des letzten Verkaufs in Portfolio Performance manuell angepasst werden, damit der Bestand nach dem Verkauf 0 ist.
