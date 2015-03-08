/**
 * Copyright (c) 2015, Implementazione Macchina di Turing (MdT) AUTHORS
 *
 * Annalina Caputo Department of Computer Science - University of Bari Aldo Moro
 * - Italy
 *
 * annalina.caputo@uniba.it
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * Neither the name of the University of Bari nor the names of its contributors
 * may be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * GNU GENERAL PUBLIC LICENSE - Version 3, 29 June 2007
 *
 * =============================================================================
 *
 * This work fulfils the research objectives of the project ``VINCENTE - A
 * Virtual collective INtelligenCe ENvironment to develop sustainable Technology
 * Entrepreneurship ecosystems'' (PON 02 00563 3470993) funded by the Italian
 * Ministry of University and Research (MIUR).
 */
package it.uniba.dib.mdt;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 *
 * @author annalina
 */
public class MdT {

    static Options options;

    static CommandLineParser cmdParser = new BasicParser();

    static {
        options = new Options();
        options.addOption("u", true, "percorso file unita di controllo")
                .addOption("n", true, "percorso file nastro")
                .addOption("a", true, "percorso file alfabeto")
                .addOption("q", true, "stato iniziale");
    }

    Nastro nastro;
    Testina testina;
    UnitaControllo uc;
    Alfabeto alfabeto;
    String stato;

    public MdT(File fUc, File fNastro, File fAlfabeto, String stato) throws Exception {

        this.alfabeto = new Alfabeto(fAlfabeto);
        this.nastro = new Nastro(fNastro);
        this.testina = new Testina(nastro.getDimNastro() - 1);
        uc = new UnitaControllo(fUc, alfabeto);

        if (!uc.stati().contains(stato)) {
            throw new Exception("Stato di partenza non contenuto nella Matrice Funzionale");
        }
        this.stato = stato;
        System.out.println(alfabeto.toString() + " " + uc.stampaStati());
        System.out.println("Input: ");
        testina.stampaTestina(nastro);
        nastro.stampaNastro();
        System.out.println();
    }

    private void eseguiMdT() {
        while (!stato.equals("HALT")) {
            String simboloLetto = nastro.read(testina.getPointer());
            String cella = uc.getSimboloSpostamentoStato(simboloLetto, stato);
            String[] s = cella.split(" ");
            String simboloScrivere = s[0];
            String spostamento = s[1];
            String statoSuccessivo = s[2];
            System.out.println("Eseguo <" + simboloLetto + "," + stato + "," + simboloScrivere + "," + spostamento + "," + statoSuccessivo + ">:");

            testina.write(nastro, simboloScrivere);

            if (spostamento.equals("S")) {
                testina.sinistra();
            } else if (spostamento.equals("D")) {
                testina.destra();
            }
            stato = statoSuccessivo;
            testina.stampaTestina(nastro);
            nastro.stampaNastro();
        }
        System.out.println("La macchina di Turing si è fermata generando il seguente output: ");
        testina.stampaTestina(nastro);
        nastro.stampaNastro();
    }

    /**
     * Esegue una data Macchina di Turing data in input
     *
     * uso: [-u <arg>] [-n <arg>] [-a <arg>] [-q <arg>] esempio: -u
     * unitacontrollo.txt -n nastro.txt -a alfabeto.txt -q q0 -u <arg> file
     * contenente il comportamento dell'unità di controllo nel formato
     * "<simbolo,stato,simbolo,movimento,stato>": un'istruzione per ogni linea
     * -n <arg> file contenente il nastro ovvero una sequenza di caratteri
     * scritta senza ritorno a capo nel formato
     * "^|carattere|carattere|...|carattere|^", il simbolo "|" delimita i
     * caratteri, la stringa deve necessariamente iniziare e terminare con il
     * simbolo "^" -a <arg> file contenente l'alfabeto, ovvero sequenza di
     * caratteri separati da spazio e scritti su di un'unica linea -q <arg>
     * stato iniziale nel formato "qnumero" ad esempio "q0"
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        CommandLine cmd = cmdParser.parse(options, args);
        File unitControl = new File(args[0]);
        File input = new File(args[1]);
        File alfabeto = new File(args[2]);

        MdT macchina;
        try {
            macchina = new MdT(new File(cmd.getOptionValue("u")), new File(cmd.getOptionValue("n")), new File(cmd.getOptionValue("a")), cmd.getOptionValue("q"));
            macchina.eseguiMdT();
        } catch (Exception ex) {
            System.out.println("La MdT ha generato il seguente errore: " + ex);
        }

    }

}
