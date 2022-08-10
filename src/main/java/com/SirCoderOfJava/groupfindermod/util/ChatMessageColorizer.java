package com.SirCoderOfJava.groupfindermod.util;

public class ChatMessageColorizer {
    public static final char COLOUR_CHAR = '\u00A7';

    public static String replaceCodes(String string){

        char[] b = string.toCharArray();
        for (int i = 0; i < b.length - 1; i++){
            if(b[i] == '&' && "0123456789AaBbCcDdEeFfKkLlMmNnOoRrXx".indexOf(b[i + 1]) > -1){
                b[i] = COLOUR_CHAR;
                b[i + 1] = Character.toLowerCase(b[i + 1]);

            }
        }
        return  new String(b);
    }
}
