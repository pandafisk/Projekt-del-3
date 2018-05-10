/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reading;

class HuffNode {

    Object data;
    int key;

    HuffNode left;
    HuffNode right;
}

public class Huffman2 {

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

    public static void HuffConstructor(int[] intArr, String[] strArr) {

        int n = intArr.length;
        PQ pq = new PQHeap(256);

        for (int i = 0; i < n; i++) {

            HuffNode hn = new HuffNode();

            hn.key = i;
            hn.data = intArr[i];

            hn.left = null;
            hn.right = null;

            pq.insert(new Element(hn.key, hn));
        }

        HuffNode root = null;

        for (int i = 1; i < intArr.length; i++) {

            Element x = pq.extractMin();
            Element y = pq.extractMin();
            HuffNode node = new HuffNode();

            node.key = x.key + y.key;
            node.left = ((HuffNode) x.data);
            node.right = ((HuffNode) y.data);

            root = node;

            pq.insert(new Element(node.key, node));
        }
        getCode(root, strArr, "");
    }
}
