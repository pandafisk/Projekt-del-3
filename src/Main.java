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
public class Main {

    public static void main(String[] Args) throws FileNotFoundException, IOException {
        PQHeap pqh = new PQHeap(256);
        int[] arr = new int[256];
        int[] arr2 = new int[256];

        String[] strArr = new String[256];

        FileInputStream text = callIn("/home/bisch/Programming/AlgortimeProjektIII/Projekt-del-3/Testfiles/text.txt");
        FileOutputStream out = callOut("/home/bisch/Programming/AlgortimeProjektIII/Projekt-del-3/Testfiles/text2.txt");

        BitInputStream in = new BitInputStream(text);

        int[] intArr = HuffWork.readingByte(text, out, arr);
        for (int i = 0; i < intArr.length; i++) {
            if (intArr[i] > 0) {
                System.out.println(i + ":" + intArr[i]);
            }
        }

        System.out.println("Calling the Huff-constructor");
        HuffWork.HuffConstructor(intArr, strArr);
//        System.out.println("huff");
//        HuffWork.printCode(strArr);
//        System.out.println("huff, out");
        FileInputStream text2 = callIn("/home/bisch/Programming/AlgortimeProjektIII/Projekt-del-3/Testfiles/text.txt");
        FileOutputStream out2 = callOut("/home/bisch/Programming/AlgortimeProjektIII/Projekt-del-3/Testfiles/text3.txt");
        System.out.println("BOB!");
        int[] bob = HuffWork.readingByte2(text2, out2);
        BitOutputStream bos = new BitOutputStream(out2);
        for (int i : bob) {
            System.out.println(i + " : " + Long.parseLong(strArr[i]) + " - pik - " + strArr[i]);
            
            bos.writeInt(Integer.parseInt(strArr[i]));
        }

    }

    public static FileInputStream callIn(String in) throws FileNotFoundException {
        FileInputStream text = new FileInputStream(in);
        return text;
    }

    public static FileOutputStream callOut(String in) throws FileNotFoundException {
        FileOutputStream out = new FileOutputStream(in);
        return out;
    }
}







//
//        System.out.println("Inserting 8,3,1,6,10,14,13,4,7");
//        int[] insertValues = {8, 3, 1, 6, 10, 14, 13, 4, 7};
//        for (int i = 0; i < insertValues.length; i++) {
//            d.insert(insertValues[i]);
//        }
//        d.orderedTraversal();
//        HuffWork.printCode(strArr);
//            System.out.print(i);
//            bos.writeInt(i);
//        }
//        int bit;
//        int[] frequencies = new int[256];
//        String readBits = "";
//        String[] encodedTable = null;
//
//        // Read the input file as bits; seperate at 8 bits (1 byte).
//        // Add one to the frequency of the byte on occurrence.
//        bit = in.readBit();
//        while (bit != -1) {
//            System.out.println(bit);
//        }
//        for (int i : intArr) {
//            System.out.print(i);
//            bos.writeInt(i);
//        }
