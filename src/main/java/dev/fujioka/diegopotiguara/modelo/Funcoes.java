package dev.fujioka.diegopotiguara.modelo;

public class Funcoes {
	//Metodo para remover os acentos das palavras
    public String removeAcentos(String string) {
        string = string.replaceAll("[ÂÀÁÄÃ]", "A");
        string = string.replaceAll("[âãàáä]", "a");
        string = string.replaceAll("[ÊÈÉË]", "E");
        string = string.replaceAll("[êèéë]", "e");
        string = string.replaceAll("ÎÍÌÏ", "I");
        string = string.replaceAll("îíìï", "i");
        string = string.replaceAll("[ÔÕÒÓÖ]", "O");
        string = string.replaceAll("[ôõòóö]", "o");
        string = string.replaceAll("[ÛÙÚÜ]", "U");
        string = string.replaceAll("[ûúùü]", "u");
        string = string.replaceAll("Ç", "C");
        string = string.replaceAll("ç", "c");
        string = string.replaceAll("[ýÿ]", "y");
        string = string.replaceAll("Ý", "Y");
        string = string.replaceAll("ñ", "n");
        string = string.replaceAll("Ñ", "N");
        return string;
    }
}
