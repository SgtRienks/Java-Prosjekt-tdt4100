# 🧮 GainsTeller

**GainsTeller** er en Java-applikasjon for å beregne hvor mange kalorier og proteiner du har konsumert i løpet av en dag.  
Appen lar brukeren legge til egne matvarer, lagre dem for gjenbruk, og enkelt regne ut næringsinnholdet i måltider.

---

## 🚀 Funksjonalitet

- Legg til matvarer med navn og vekt (gram)
- Lagre matvarer i en lokal fil slik at de ikke må legges inn på nytt
- Beregn total **kalorimengde** og **proteiner** for dagens måltider
- Fjern alle valgte matvarer med “Tøm”-knappen
- Beregning utføres med “Regn ut”-knappen
- Input krever desimalform (f.eks. `125.0`)

---

## 🧩 Struktur og arkitektur

Appen er bygget etter **Model-View-Controller (MVC)**-prinsippet:

- **Model:** `Matvare` og `MatvareListe` inneholder kjernelogikken og dataene
- **View:** GUI-komponentene for brukerinteraksjon
- **Controller:** Håndterer input, validering og kaller riktige metoder fra modellen

### Brukte objektorienterte prinsipper

- **Delegering:** `MatvareListe` varsler sine lyttere (listeners) når data endres  
- **Observer/Observable:** `MatvareListe` bruker `MatvareListListener`-interfacet for å varsle om endringer  
- **Filhåndtering:** `MatvareSkriver` skriver og leser matvaredata fra `.txt`-fil

---

## ⚙️ Tekniske detaljer

- Appen bruker **vanlige løkker** og **Scanner** for filhåndtering  
- Enkel bruk av **Stream API** for å iterere gjennom to lister samtidig under utregning  
- Utregning av kalorier/protein baseres på to parallelle lister (navn og vekt)

> En mulig forbedring ville vært å bruke et `HashMap<Matvare, Double>` for å knytte hver matvare til sin vekt mer robust.

---

## 🧠 Testing

- Testet gjennom praktisk bruk av applikasjonen  
- Fokus på håndtering av tomme input-felt, som kan forskyve beregningen  
- Testet lagring og henting av matvarer fra fil  
- Flere metoder er testet individuelt for korrekt funksjon  

---

## 🔍 Forbedringspotensial

- Økt bruk av **Stream API** for enklere og mer effektiv koding  
- Bedre håndtering av feilinput fra brukere  
- Mer robust kobling mellom matvare og vekt (f.eks. via `HashMap`)

---

## 🏗️ Klassediagram

<img width="604" height="922" alt="image" src="https://github.com/user-attachments/assets/7a720523-187c-40ed-8dde-118487c5c744" />

