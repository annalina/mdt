# Macchina di Turing

Questa è un'applicazione JAVA per realizzare una Macchina di Turing. Per lanciare l'applicazione basta digitare da terminale (nella stessa cartella in cui risiede il programma mdt.jar) il seguente comando:

`java -jar mdt.jar unitacontrollo.txt input.txt alfabeto.txt`

**unitcontrol.txt**
Il contenuto del file `unitacontrollo.txt` rappresentano le istruzioni della macchina di Turing. Questo deve necessariamente essere un file contenente un'istruzione per ogni riga nel seguente formato:

`` `<simbolo_letto,stato_corrente,simbolo_da_scrivere,spostamento_testina,nuovo_stato>` ``

Dove:

* `simbolo_letto` può essere qualsiasi carattere alfa-numerico (per convenzione il carattere `^` rappresenta il BLANK della macchina)
* `stato_corrente` è rappresentato da una stringa nel formato `qnumero` + lo stato di `HALT`
* `simbolo_da_scrivere` può essere un carattere alfa-numerico (per convenzione il carattere `^` rappresenta il `BLANK` della macchina)
* `spostamento_testina` può essere una delle seguenti lettere `F` (Fermo), `S` (Sinistra), `D` (Destra)
* `nuovo_stato` come `stato_corrente`

**input.txt**
Il file `input.txt` conterrà l'input del nastro prima che avvenga la computazione, e se la computazione termina producendo un output, questo sarà salvato nello stesso file.
Il carattere `pipe` (`|`) è utilizzato come delimitatore delle celle di input.
Si presuppone che il file inizi con il primo simbolo, a partire da sinistra della sequenza di input, che deve essere un carattere di BLANK. L'input termina con un altro carattere di BLANK. Non devono essere presenti altri caratteri di `carriage return`. Ad esempio:

      ^0|1|0|0|1^

**alfabeto.txt**   
Il file `alfabeto.txt` deve contenere tutti i simboli dell'alfabeto (compreso `^`) separati da uno spazio e posti su di un'unica linea del file di testo.
