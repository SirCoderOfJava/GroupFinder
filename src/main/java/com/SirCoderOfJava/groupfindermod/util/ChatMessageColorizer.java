package com.SirCoderOfJava.groupfindermod.util;

//Black magic code from the hypixel forums
public class ChatMessageColorizer {
    public static final char COLOUR_CHAR = '\u00A7';

    /**
     * Method to replace color codes with the internal minecraft format
     * @param string input string with "&" color codes
     * @return output string with internal minecraft color codes
     */
    public static String replaceCodes(String string){
        //Devil code from somewhere on the hypixel forums
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
