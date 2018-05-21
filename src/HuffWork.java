//Udviklet og afleveret af:
//	Mikkel La Cour - midor17
//	Mathias Bischoff - mbisc17
//	Troels Have - trhav17

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 *
 * @author Troels
 */

public class HuffWork {

    static class HuffNode {

        int key;
        HuffNode left;
        HuffNode right;

        HuffNode(int key) {
            this.key = key;
            right = null;
            left = null;
        }
    }


    private static void getCode(HuffNode root, String[] strArr, String s) {
        if (root.left == null && root.right == null) {
            strArr[root.key] = s;
            return;
        }
            getCode(root.left, strArr, s + "0");
            getCode(root.right, strArr, s + "1");
    }

    public static String[] encode(HuffNode root) {
        String[] strArr = new String[256];
        if (root.right != null || root.left != null) {
            getCode(root, strArr, "");
        }else {
            getCode(root, strArr, "0");
        }
        return strArr;
    }

    public static Map<String, Integer> decode(HuffNode root) {
        Map<String, Integer> map = new HashMap<>();
        if (root.right != null || root.left != null) {
            decoding(root, map, "");
        } else {
            decoding(root, map, "0");
        }
        return map;
    }

    //    genererer rekursivt en bitstreng baseret på Huffman-træet
    private static void decoding(HuffNode node, Map<String, Integer> map, String s) {
        if (node.left == null && node.right == null) {
            map.put(s, node.key);
        } else {
            decoding(node.left, map, s + "0");
            decoding(node.right, map, s + "1");
        }
    }


    public static Element HuffConstructor(int[] arr) {
        int counter = 0;

        for(int i : arr){
            if (i != 0){
                counter++;
            }
        }

//    Hvis Elements er tilstede, fyldes heapet med "counter" antal Elements
//    med frekevnserne som key og et blad-node med værdien som key, som data
        if (counter > 0) {
            PQ pq = new PQHeap(counter);
            for (int i = 0; i < arr.length; i++){
                if (arr[i] != 0){
                    pq.insert(new Element(arr[i], new HuffNode(i)));
                }
            }
            for (int i = 0; i < counter - 1; i++) {
                Element l = pq.extractMin();
                Element r = pq.extractMin();
                int key = l.key + r.key;

//    konstruerer en ny node, bestående af de to mindste i vores heap
//    nodens Key sættes til at være den samlede værdi for de to mindste.
//    Derudover sættes nodens højre/ventra barn til at være data fra to midste
                HuffNode node = new HuffNode(key);
                node.left = (HuffNode) l.data;
                node.right = (HuffNode) r.data;

//    tilføjer den nye node til prioritetskøen
                pq.insert(new Element(key, node));
            }
            return pq.extractMin();
        }
        return null;
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
