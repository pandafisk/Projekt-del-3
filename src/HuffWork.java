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

    public static int[] readingByte(FileInputStream file, int[] arr) throws IOException {
        int available = file.available();

        for (int i = 0; i < available; i++) {
            int next = file.read();
            arr[next] += 1;
        }

        return arr;
    }
    
        public static int[] readingByte2(FileInputStream file) throws IOException {
        int available = file.available();
        int[] arr = new int[available];

        for (int i = 0; i < available; i++) {
            int next = file.read();
            arr[i] = next;
        }

        return arr;
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

    public static void printCode (String[] strArr){
        int count = 0;
        for (int str = 0; str < strArr.length; str++){
            count++;
            System.out.println("byte " + str + ":" + strArr[str]);
        }
        System.out.println("count: " + count);
    }

    public static void HuffConstructor(int[] intArr, String[] strArr, PQHeap pq) {

        int n = intArr.length;

        for (int i = 0; i < n; i++) {

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

    public static FileInputStream callIn(String in) throws FileNotFoundException {
        FileInputStream text = new FileInputStream(in);
        return text;
    }

    public static FileOutputStream callOut(String in) throws FileNotFoundException {
        FileOutputStream out = new FileOutputStream(in);
        return out;
    }
}
