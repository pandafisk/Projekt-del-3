//Udviklet og afleveret af:
//	Mikkel La Cour - midor17
//	Mathias Bischoff - mbisc17
//	Troels Have - trhav17

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Encode {


    public static void main(String[] args) throws IOException {
        int[] arr = new int[256];
        String[] strArr;
        int next;
        String bits = "";
        
//        1. gennemlæsning - Bygger Huffman-træet og encoder
        FileInputStream text = HuffWork.callIn(args[0]);
        FileOutputStream out = HuffWork.callOut(args[1]);

        BitInputStream bis = new BitInputStream(text);
        BitOutputStream bos = new BitOutputStream(out);

//        Læser inputfil og sepererer ved 8 bits (1 byte).
//        lægger 1 til frekvensen for den omhandlende byte
        while ((next = bis.readBit()) != -1) {
            bits += "" + next;
            if (bits.length() % 8 == 0) {
                arr[Integer.parseInt(bits, 2)] ++;
                bits = "";
            }
        }

//        skriver frekvenserne til output
        int count = 0;
        for (int i : arr) {
            bos.writeInt(i);
            count++;
        }

//        Encoder filen ved hjælp af vores metoder
        Element e = HuffWork.HuffConstructor(arr);
        strArr = HuffWork.encode((HuffWork.HuffNode) e.data);

        bis.close();

//        2. gennemlæsning - udskriver kodningen til output filen
        text = HuffWork.callIn(args[0]);
        bis = new BitInputStream(text);
        
//        læser input filen igen og udskriver karaktererne fra den 
//        encoded tabel.
        while ((next = bis.readBit()) != -1) {
            bits += "" + next;
            if (bits.length() % 8 == 0) {
                for (char c : strArr[Integer.parseInt(bits, 2)].toCharArray()) {
                    bos.writeBit(Integer.parseInt(Character.toString(c)));
                }
                bits = "";
            }
        }

        bis.close();
        out.close();


    }

}

