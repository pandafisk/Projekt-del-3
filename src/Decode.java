//Udviklet og afleveret af:
//	Mikkel La Cour - midor17
//	Mathias Bischoff - mbisc17
//	Troels Have - trhav17

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public class Decode {
    public static void main(String[] args) throws IOException {


        FileInputStream encodedFile = HuffWork.callIn(args[0]);
        FileOutputStream out = HuffWork.callOut(args[1]);

        BitInputStream bis = new BitInputStream(encodedFile);
        BitOutputStream bos = new BitOutputStream(out);

//    læser alle frekvenser fra input filen og gemmer dem i et array
        int[] arr = new int[256];
        for (int i = 0; i < arr.length; i++) arr[i] = bis.readInt();

//    Bygger et Huffman træ ud fra de givne frekvenser
        Element e = HuffWork.HuffConstructor(arr);

//    Finder Huffmann koden for alt i input
        Map<String, Integer> map = HuffWork.decode((HuffWork.HuffNode)e.data);

        String bits = "";
        int bit;
        int counter = e.key;

//    læser den resterende fil, bit for bit
        while ((bit = bis.readBit()) != -1) {
            bits += "" + bit;

//    slår op i vores map, og afgør om de omhandlende bits koder til noget i
//    Huffman-træet. Hvis dette re tilfældet, indeholder decodeInt de tal,
//    der skal skrives til output
            Integer decodeInt = map.get(bits);
            if (decodeInt != null) {
//    finder bit strengen for det omhandlende tal
                bits = Integer.toBinaryString(decodeInt);

//    Hvis bit strengen ikke fylder de fulde 8 bits, tilføjes resten som 0
                for (int i = bits.length() - 8; i < 0; i++) {
                    bos.writeBit(0);
                }
//    Tallet skrives som bits til output
                for (char c : bits.toCharArray()) {
                    bos.writeBit(Integer.valueOf(Character.toString(c)));
                }
//    bitstrengen bliver nulstillet, så resten kan decodes
                bits = "";
                counter--;
            }
        }

        bis.close();
        bos.close();




    }
}
