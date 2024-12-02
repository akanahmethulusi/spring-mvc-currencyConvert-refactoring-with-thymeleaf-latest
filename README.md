Projektziel:
eine modulare und erweiterbare UmrechnungsAnwendung zu erstellen.


Schlüsselaspekte des Projekts
1-RestTemplate:
  Für JSON-API wurde RestTemplate.exchange() genutzt werden.
2-Entity und Wrapper Klasse
  Die Wrapper-Klasse ruft die Daten aus der Entity Klasse auf und verarbeitet sie weiter.
  Entity Klasse: Für die API-Antwort enspricht dem DatenModell
  Wrapper Klasse: Enthält eine Map für dynamische Zuordnung von Währungscodes zu Wechselkursen
3-Endpunkte
  /convert: durchführt die Währungsumrechnung
  /currencies: listet die alle Währungscodes auf
  /update-date: durchführt die aktuelle Zeit
  
  
