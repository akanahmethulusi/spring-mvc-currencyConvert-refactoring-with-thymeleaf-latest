# Spring MVC Currency Converter (Refactoring mit Thymeleaf)

## 🎯 Projektziel
Ziel dieses Projekts ist die Entwicklung einer **modularen, wartbaren und erweiterbaren Währungsumrechnungsanwendung** auf Basis von **Spring MVC**.  
Die Anwendung nutzt eine externe JSON-API zur Bereitstellung aktueller Wechselkurse und 
stellt diese über REST-Endpunkte sowie eine Thymeleaf-Oberfläche bereit.

---

## 🔑 Schlüsselaspekte des Projekts

### 1️⃣ Verwendung von `RestTemplate`
Für den Zugriff auf eine externe **JSON-API** wird `RestTemplate.exchange()` verwendet.  
Dies ermöglicht:
- flexible HTTP-Methoden
- saubere Verarbeitung von API-Antworten
- bessere Erweiterbarkeit bei API-Änderungen

---

### 2️⃣ Entity- und Wrapper-Klassen

**Entity-Klasse**
- Repräsentiert das **Datenmodell der API-Antwort**
- Entspricht direkt der JSON-Struktur der externen API

**Wrapper-Klasse**
- Kapselt die Entity-Klasse
- Verarbeitet und transformiert die Rohdaten
- Enthält eine `Map<String, BigDecimal>` zur **dynamischen Zuordnung von Währungscodes zu Wechselkursen**

➡️ Diese Trennung verbessert:
- Lesbarkeit
- Wartbarkeit
- Wiederverwendbarkeit des Codes

---

### 3️⃣ REST-Endpunkte

| Endpoint        | Beschreibung |
|-----------------|--------------|
| `/convert`      | Führt eine Währungsumrechnung basierend auf Betrag und Zielwährung durch |
| `/currencies`   | Gibt eine Liste aller verfügbaren Währungscodes zurück |
| `/update-date`  | Liefert das Datum bzw. die Zeit der letzten Kursaktualisierung |

---

## 🧩 Umgang mit dynamischen API-Strukturen

```java
@JsonIgnoreProperties(ignoreUnknown = true)

 Diese Annotation wird verwendet, um unbekannte Felder in der JSON-Antwort zu ignorieren.

Warum ist das wichtig?

Externe APIs können sich jederzeit ändern

Zusätzliche Felder würden sonst zu einer UnrecognizedPropertyException führen

Erhöht die Robustheit der Anwendung bei API-Updates


🚀 Technologien

-Java

-Spring MVC

-RestTemplate

-Thymeleaf

-JSON (externe API)



📌 Fazit

Dieses Projekt demonstriert den sauberen Einsatz von Spring MVC, REST-Kommunikation mit externen APIs
sowie eine klare Trennung von Datenmodell und Verarbeitungslogik.
Der Fokus liegt auf Refactoring, Best Practices und Erweiterbarkeit.

