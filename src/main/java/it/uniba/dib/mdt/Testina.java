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

/**
 *
 * @author annalina
 */
public class Testina {
    
    int pointer;
    
    public Testina(int dimensione){
        this.pointer = dimensione;
    }
    
    
    public void sinistra(){
        pointer--;
    }
    
    public void destra(){
        pointer++;
    }
    
    public void fermo(){
        
    }
    
    public void write(Nastro nastro,String symbol){
        pointer = nastro.write(pointer, symbol);
    }
    
    public String read(Nastro nastro){
        return nastro.read(pointer);
    }
    
    public int getPointer(){
        return pointer;
    }
    
    public void stampaTestina(Nastro nastro){
        String out = new String();
        for (int i=0; i<nastro.getDimNastro(); i++){
            if (this.pointer == i)
                out += "\u2193 ";
            else out += "  ";
        }
        out = out.substring(0, out.lastIndexOf(" "));
        System.out.println(out);
    }
    
}
