
/*
    public static Element HuffBuild(int[] intArr) {
        int elementCount = (int) IntStream.of(intArr)
                .filter(n -> n != 0)
                .count();

        System.out.println(elementCount);

        if (elementCount > 0) {
            PQ pq = new PQHeap(elementCount);

            IntStream.range(0, intArr.length)
                    .filter(n -> intArr[n] != 0)
                    .forEach(n -> pq.insert(new Element(intArr[n], new HuffWork.HuffNode(n))));

            for (int i = 0; i < elementCount - 1; i++) {
                Element L = pq.extractMin();

                Element R = pq.extractMin();
                int key = L.key + R.key;

                HuffWork.HuffNode node = new HuffWork.HuffNode(key);
                pq.insert(new Element(key, node));
                System.out.println("aded " + key + " with node: " + node);
            }
//            System.out.println(pq.extractMin());
            return pq.extractMin();
        }
        return null;
    }

    public static String[] encoding(HuffWork.HuffNode root){

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
*/