Was könnte verbessert werden?
=============================
Die anonyme 'student-solution' ist eine korrekte, schöne Lösung.
Dennoch kann man ein paar Sachen in meinen Augen schöner machen:

* die booleans für die Beleuchtung / die Klingel heißen momentan `light` und `ring`
  Besser wäre: `hasLight` und `hasRing`
* auch die Kommentare auf Englisch. Das ist aber NICHT für die 
  Aufgaben / Abschlussaufgaben gefordert! Es ist nur mein 
  persönlicher Geschmack
* Korrekte [UTF-8 Kodierung](http://de.wikipedia.org/wiki/UTF-8) (siehe Umlaute)
* vgl. Quelltext-Beispiel
* Javadoc besser schreiben (vgl. [javadoc - The Java API Documentation Generator](http://docs.oracle.com/javase/6/docs/technotes/tools/windows/javadoc.html)
  um mal den generierten Code zu sehen.)


Quelltext-Verbesserung
----------------------
```java
public boolean isStreetLegal() {
    boolean b = false;
    if (this.light && this.ring) {
        b = true;
    }
    return b;
}
```

kann man als

```java
public boolean isStreetLegal() {
    return this.light && this.ring;
}
```
