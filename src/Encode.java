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
        PQHeap pqh = new PQHeap(256);
        int[] arr = new int[256];

        String[] strArr = new String[256];

        FileInputStream text = HuffWork.callIn("/home/bisch/Programming/Projekt-del-3/Testfiles/text.txt");
        FileOutputStream out;

        int[] intArr = HuffWork.readingByte(text, arr);

        text.close();


        HuffWork.HuffConstructor(intArr, strArr, pqh);

        text = HuffWork.callIn("/home/bisch/Programming/Projekt-del-3/Testfiles/text.txt");
        out = HuffWork.callOut("/home/bisch/Programming/Projekt-del-3/Testfiles/text2.txt");

        int[] bob = HuffWork.readingByte2(text);
        BitOutputStream bos = new BitOutputStream(out);
        for (int i = 0; i < intArr.length; i++) {
            System.out.println("byte: " +  i + " occurrences:" + intArr[i]);
            if(intArr[i] > 0) {
                bos.writeInt(i);
            }
        }
        System.out.println(" ------------------------------------------- ");
        for (int i : bob) {
            System.out.println(i + " : " + Integer.parseInt(strArr[i]));
            bos.writeInt(Integer.parseInt(strArr[i],2));
        }

        text.close();
        out.close();


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
