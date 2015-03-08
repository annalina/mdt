/**
 * Copyright (c) 2015, Implementazione Macchina di Turing (MdT) AUTHORS
 *
 * Annalina Caputo Department of Computer Science - University
 * of Bari Aldo Moro - Italy
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author annalina
 */
public class UnitaControllo {

    Map<String, Map<String, String>> cella;
    Set<String> stati;

    public UnitaControllo(File iFile, Alfabeto ab) throws FileNotFoundException, IOException, Exception {
        stati = new HashSet<>();
        cella = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(iFile));
        String line;
        while ((line = br.readLine()) != null) {
            if (!verificaIstruzione(line)) {
                throw new Exception("Istruzione non valida");
            }
            line = line.substring(1,line.length()-1);
            String[] parts = line.split(",");
            if (verificaIstruzione(parts, ab)) {
                if (cella.containsKey(parts[0])) {
                    cella.get(parts[0]).put(parts[1], parts[2] + " " + parts[3] + " " + parts[4]);
                } else {
                    cella.put(parts[0], new HashMap<String, String>());
                    cella.get(parts[0]).put(parts[1], parts[2] + " " + parts[3] + " " + parts[4]);
                }
                if (!stati.contains(parts[1])) {
                    stati.add(parts[1]);
                }
                if (!stati.contains(parts[4])) {
                    stati.add(parts[4]);
                }
            } else {
                throw new Exception("Istruzione non valida");
            }

        }

    }

    public String getSimboloSpostamentoStato(String simboloLetto, String statoCorrente) {
        return cella.get(simboloLetto).get(statoCorrente);
    }

    private boolean verificaIstruzione(String[] parts, Alfabeto ab) throws Exception {
        if ((parts.length > 5) || (parts.length < 5)) {
            throw new Exception("Istruzione con un numero errato di parametri");
        }
        verificaSimbolo(parts[0], ab);
        verificaStato(parts[1]);
        verificaSimbolo(parts[2], ab);
        verificaSpostamento(parts[3]);
        verificaStato(parts[4]);
        return true;
    }

    private boolean verificaIstruzione(String line) {
        if (line.startsWith("<") && (line.endsWith(">"))) {
            return true;
        }
        return false;
    }

    private boolean verificaStato(String stato) {
        if (!((stato.startsWith("q")) && (stato.substring(1, stato.length()).matches("-?\\d+(\\.\\d+)?")))) {
            return false;
        }
        return true;
    }

    private boolean verificaSimbolo(String simbolo, Alfabeto ab) throws Exception {
        if ((simbolo.length() == 1) || (ab.contains(simbolo))) {
            return true;
        }
        return false;
    }

    private boolean verificaSpostamento(String spostamento) {
        if (!((spostamento.equals("F") || (spostamento.equals("D") || (spostamento.equals("S")))))) {
            return false;
        }
        return true;
    }

    public Set<String> stati() {
        return stati;
    }

    public String stampaStati() {
        String out = "Q={";
        for (String s : stati) {
            out += s + ",";
        }
        out = out.substring(0, out.length() - 1);
        out += "}";
        return out;

    }
}
