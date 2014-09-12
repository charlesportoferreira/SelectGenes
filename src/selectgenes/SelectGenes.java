/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selectgenes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author charleshenriqueportoferreira
 */
public class SelectGenes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<String> genesSelecionados = new ArrayList<>();
        String nomeArquivo = args[0];
        //String arquivoModificicado = "";
        List<String> genes = new ArrayList<>();
        try {

            genes = lerGenes(args[0]);
        } catch (IOException ex) {
            Logger.getLogger(SelectGenes.class.getName()).log(Level.SEVERE, null, ex);
        }
        StringBuilder arquivoTexto = new StringBuilder();
        try {
            for (String gene : genes) {
                genesSelecionados.add(lerArquivo(args[1], gene));
            }
            genesSelecionados.add(",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?");
            for (String linhaGeneSelecionado : genesSelecionados) {
                arquivoTexto.append(linhaGeneSelecionado);
                arquivoTexto.append("\n");
            }
        } catch (IOException ex) {
            Logger.getLogger(SelectGenes.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            printFile(args[2], arquivoTexto.toString());
        } catch (IOException ex) {
            Logger.getLogger(SelectGenes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String lerArquivo(String filePath, String gene) throws FileNotFoundException, IOException {
        StringBuilder linha = new StringBuilder();

        try (FileReader fr = new FileReader(filePath); BufferedReader br = new BufferedReader(fr)) {
            while (br.ready()) {
                String linhaLida = br.readLine();
                if (linhaLida.contains(gene)) {
                    return linhaLida;
                }

            }
            br.close();
            fr.close();
        }
        return linha.toString();
    }

    public static void printFile(String fileName, String texto) throws IOException {
        try (FileWriter fw = new FileWriter(fileName); BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(texto);
            bw.newLine();
            bw.close();
            fw.close();
        }
    }

    private static List<String> lerGenes(String filePath) throws FileNotFoundException, IOException {
        //StringBuilder linha = new StringBuilder();
        List<String> genesLidos = new ArrayList<>();
        try (FileReader fr = new FileReader(filePath); BufferedReader br = new BufferedReader(fr)) {
            while (br.ready()) {
                String linhaLida = br.readLine();

                if (linhaLida.contains("NUMERIC")) {
                    linhaLida = linhaLida.replace("@ATTRIBUTE ", "");
                    linhaLida = linhaLida.replace(" NUMERIC", "");
                    genesLidos.add(linhaLida);
                }

            }
            br.close();
            fr.close();
        }
        return genesLidos;
    }

}
