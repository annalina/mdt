# Macchina di Turing

Questa è un'applicazione JAVA per realizzare una Macchina di Turing. Per lanciare l'applicazione basta digitare da terminale (nella stessa cartella in cui risiede il programma mdt.jar) il seguente comando:

`java -jar mdt.jar -u unitacontrollo.txt -n input.txt -a alfabeto.txt -q q0`

**unitcontrol.txt**
Il contenuto del file `unitacontrollo.txt` rappresenta le istruzioni della macchina di Turing. Questo deve necessariamente essere un file contenente un'istruzione per ogni riga nel seguente formato:

`` `<simbolo_letto,stato_corrente,simbolo_da_scrivere,spostamento_testina,nuovo_stato>` ``

Dove:

* `simbolo_letto` può essere qualsiasi carattere alfa-numerico (per convenzione il carattere `^` rappresenta il BLANK della macchina)
* `stato_corrente` è rappresentato da una stringa nel formato `qnumero` + lo stato di `HALT`
* `simbolo_da_scrivere` può essere un carattere alfa-numerico (per convenzione il carattere `^` rappresenta il `BLANK` della macchina)
* `spostamento_testina` può essere una delle seguenti lettere `F` (Fermo), `S` (Sinistra), `D` (Destra)
* `nuovo_stato` come `stato_corrente`

**input.txt**
Il file `input.txt` conterrà l'input del nastro.
Il carattere `pipe` (`|`) è utilizzato come delimitatore delle celle del nastro in input.
Si presuppone che il file inizi con il primo simbolo, a partire da sinistra della sequenza di input, che deve essere un carattere di BLANK. L'input termina con un altro carattere di BLANK. Ad esempio:

      ^|0|1|0|0|1|^

**alfabeto.txt**   
Il file `alfabeto.txt` deve contenere tutti i simboli dell'alfabeto (compreso `^`) separati da uno spazio e posti su di un'unica linea del file di testo.

**q0**
Lo stato iniziale della macchina di Turing.