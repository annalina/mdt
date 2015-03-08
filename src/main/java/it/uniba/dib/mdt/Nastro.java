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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author annalina
 */
public class Nastro {

    List<String> nastro = new LinkedList<>();

    public Nastro(File iFile) throws FileNotFoundException, IOException, Exception {
        BufferedReader br = new BufferedReader(new FileReader(iFile));
        String line = br.readLine();
        if (line.isEmpty() || !line.endsWith("^") || !line.startsWith("^")) {
            throw new Exception("Formato input invalido");
        }

        String[] symbols = line.trim().split("\\|");
        for (int i = 0; i < symbols.length; i++) {
            if ((symbols[i].length() > 1) || (symbols[i].length() == 0)) {
                throw new Exception("Formato simbolo invalido " + symbols[i]);
            }
            nastro.add(symbols[i]);
        }
    }

    public void stampaAlfabeto(Set<String> alphabet) {
        String out = "X={";
        for (String symbol : alphabet) {
            out += symbol + ",";
        }
        out = out.substring(0, out.length() - 1);
        System.out.println(out + "}");

    }

    public void stampaNastro() {
        String out = new String();
        for (int i = 0; i < nastro.size(); i++) {
            out += "\u005F ";
        }
        out = out.trim();
        System.out.println(out);
        out = new String();
        for (String s : nastro) {
            out += s + "|";
        }
        out = out.substring(0, out.length() - 1);
        System.out.println(out);
        out = new String();
        for (int i = 0; i < nastro.size(); i++) {
            out += "\u203E ";
        }
        out = out.trim();
        System.out.println(out);
    }

    public int write(int pointer, String symbol) {
        if ((pointer == 0) && (!symbol.equals("^"))) {
            nastro.set(pointer, symbol);
            nastro.add(pointer, "^");
            pointer++;
        } else {
            if ((pointer == nastro.size()) && (!symbol.equals("^"))) {
                nastro.set(pointer, symbol);
                nastro.add("^");
            } else {
                nastro.set(pointer, symbol);
            }

        }
        return pointer;
    }

    public int getDimNastro() {
        return this.nastro.size();
    }

    public String read(int pointer) {
        return this.nastro.get(pointer);
    }
}
