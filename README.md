# GainsTeller

Appen går ut på å regne ut hva man har spist i løpet av en dag, i kalorier og proteiner.  
Man legger til matvarer selv, som lagres i en fil slik at man slipper å legge dem inn på nytt.  
Brukeren skriver inn matvare og vekt i to felt, og kan velge mellom tre knapper:

- **Legg til:** legger matvare og vekt til i listene  
- **Tøm:** fjerner alle elementer i lista  
- **Regn ut:** regner ut totalen av kalorier og proteiner

Appen bruker klasser som samarbeider med hverandre. `Matvare` definerer matvarer, `MatvareListe` holder styr på listene og er hoveddelen av appen.  
`MatvareSkriver` skriver og leser fra fil, og observerer endringer i lista.  
Det er brukt observer/observer-teknikk og enkel delegering mellom klassene.

# Klassediagram

<img width="604" height="922" alt="image" src="https://github.com/user-attachments/assets/7a720523-187c-40ed-8dde-118487c5c744" />

