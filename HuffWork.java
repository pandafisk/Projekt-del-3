/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reading;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.stream.IntStream;

/**
 *
 * @author Troels
 */
public class HuffWork {

    public static class HuffNode {

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

    public static int[] readingByte(FileInputStream file, FileOutputStream out, int[] arr) throws IOException {
        int available = file.available();
        System.out.println("the real available: " + available);

//        FileOutputStream out = new FileOutputStream(
//                "C:\\Users\\Troels\\Dropbox\\SDU Datalogi\\2. semester\\DM 507 - Algoritmer og datastrukturer\\Projekt del 3\\text2.txt");

        for (int i = 0; i < available; i++) {
            int next = file.read();
            System.out.print(next + " ");
            out.write(next);
            arr[next] += 1;
        }

        return arr;
    }
    
        public static int[] readingByte2(FileInputStream file, FileOutputStream out) throws IOException {
        int available = file.available();
        int[] arr = new int[available];
        System.out.println("the real available: " + available);

//        FileOutputStream out = new FileOutputStream(
//                "C:\\Users\\Troels\\Dropbox\\SDU Datalogi\\2. semester\\DM 507 - Algoritmer og datastrukturer\\Projekt del 3\\text2.txt");

        for (int i = 0; i < available; i++) {
            int next = file.read();
//            System.out.print(next + " ");
            out.write(next);
            arr[i] = next;
        }

        return arr;
    }

    public static Element HuffBuild(int[] intArr) {
        int elementCount = (int) IntStream.of(intArr)
                .filter(n -> n != 0)
                .count();

        System.out.println(elementCount);

        if (elementCount > 0) {
            PQ pq = new PQHeap(elementCount);

            IntStream.range(0, intArr.length)
                    .filter(n -> intArr[n] != 0)
                    .forEach(n -> pq.insert(new Element(intArr[n], new HuffNode(n))));

            for (int i = 0; i < elementCount - 1; i++) {
                Element L = pq.extractMin();

                Element R = pq.extractMin();
                int key = L.key + R.key;

                HuffNode node = new HuffNode(key);
                pq.insert(new Element(key, node));
                System.out.println("aded " + key + " with node: " + node);
            }
//            System.out.println(pq.extractMin());
            return pq.extractMin();
        }
        return null;
    }

    public static String[] encoding(HuffNode root){

        int[] i = new int[256];
        String[] s = new String[256];
        String str = "";
        
        InOrderTreeWalk(s, root, str);
        for (int j = 0; j < i.length - 1; j++) {
//            System.out.println(i[j] + ":" + s[j]);
        }
        return s;
    }
    
    private static void InOrderTreeWalk(String[] strArr, HuffNode x, String str) {
        if (x == null){
            return;
        } if (x.left == null && x.right == null){
            System.out.println("working?");
            System.out.println(str);
            strArr[x.key] = str;
        } else {
            
            System.out.println("Going left");
            InOrderTreeWalk(strArr, x.left, str + "0");
            System.out.println("Going right");
            InOrderTreeWalk(strArr, x.right, str + "1");
        }
        
    }

}
