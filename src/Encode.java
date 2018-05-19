/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package src;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Troels
 */
public class Encode {

    public static void main(String[] args) throws IOException {
        int[] arr = new int[256];
        String[] strArr = new String[256];
        int next;
        String bits = "";

        FileInputStream text = HuffWork.callIn("/home/bisch/Programming/Projekt-del-3/Testfiles/text.txt");
        FileOutputStream out = HuffWork.callOut("/home/bisch/Programming/Projekt-del-3/Testfiles/text2.txt");

        BitInputStream bis = new BitInputStream(text);
        BitOutputStream bos = new BitOutputStream(out);


        while ((next = bis.readBit()) != -1){
            bits += "" + next;
            if (bits.length() % 8 == 0){
                arr[Integer.parseInt(bits,2)]++;
                bits = "";
            }
        }

        int count = 0;
        for (int i :  arr) {
            System.out.println("byte: " + count + " occurrences:" + i);
                bos.writeInt(i);
                count++;
        }

        HuffWork.HuffConstructor(arr, strArr);

        /*
        for ( String s : strArr){
            System.out.println(s);
        }
*/

        text = HuffWork.callIn("/home/bisch/Programming/Projekt-del-3/Testfiles/text.txt");
        String[] bob = HuffWork.readingByte(text,arr,strArr);

        /*for (String s : bob){
            System.out.println(s);
        }
*/

        for (String string : strArr){
            bits += "" + string;
            if (bits.length() % 8 == 0) {
                for (char c : bits.toCharArray()) {
                    bos.writeBit(Integer.parseInt(Character.toString(c)));
                }
            }
            }


        bis.close();
        out.close();


    }

}

