package src;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Decode {

    public static void main(String[] args) throws IOException {

        FileInputStream in = HuffWork.callIn("/home/bisch/Programming/AlgortimeProjektIII/Projekt-del-3/Testfiles/text3.txt");
        FileOutputStream out = HuffWork.callOut("/home/bisch/Programming/AlgortimeProjektIII/Projekt-del-3/Testfiles/text4.txt");

        BitInputStream bis = new BitInputStream(in);
        BitOutputStream bos = new BitOutputStream(out);

        int[] freq = new int[256];

        for(int i = 0; i < freq.length; i++){
            freq[i] = bis.readInt();
        }







    }

}
