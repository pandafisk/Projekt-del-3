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
import java.util.Arrays;
import java.util.stream.IntStream;

/**
 *
 * @author Troels
 */
public class HuffWork {


    static class HuffNode {

        int key;
        int data;
        HuffNode left;
        HuffNode right;
        HuffNode parent;

        HuffNode(int key) {
            this.key = key;
            right = null;
            left = null;
            parent = null;
        }
    }

        public static String[] readingByte(FileInputStream file, int[] arr, String[] strArr) throws IOException {
        String[] intarr = new String[256];
            System.out.println("");
            System.out.println("Available Bytes");
            for (int i = 0; i < arr.length; i++) {

            if (arr[i] > 0) {
                System.out.println("byte " + i + " Code:" + strArr[i]);
                intarr[i] = strArr[i];
            }
        }

        return intarr;
    }

    public static void getCode(HuffNode root, String[] strArr, String s) {

        if (root.left == null && root.right == null) {
//            System.out.println(root.key + ":" + s);
            strArr[root.key] = s;
            return;
        }

        getCode(root.left, strArr, s + "0");
        getCode(root.right, strArr, s + "1");


    }

    public static void HuffConstructor(int[] intArr, String[] strArr) {

        int counter = (int) IntStream.of(intArr)
                .filter(n -> n != 0)
                .count();

        if (counter > 0) {
            PQHeap pq = new PQHeap(counter);


            for (int i = 0; i < intArr.length; i++) {

                    HuffNode hn = new HuffNode(i);

                    hn.data = intArr[i];

                    hn.left = null;
                    hn.right = null;

                    pq.insert(new Element(hn.key, hn));
            }


                HuffNode root = null;

                for (int i = 1; i < intArr.length; i++) {

                    Element x = pq.extractMin();
                    Element y = pq.extractMin();
                    HuffNode node = new HuffNode(x.key + y.key);
                    node.left = ((HuffNode) x.data);
                    node.right = ((HuffNode) y.data);
                    root = node;

                    pq.insert(new Element(node.key, node));
                }
                getCode(root, strArr, "");
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
