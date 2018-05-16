/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Troels
 */
//Udviklet og afleveret af:
//	Mikkel La Cour - midor17
//	Mathias Bischoff - mbisc17
//	Troels Have - trhav17

import java.util.Arrays;

public class PQHeap implements PQ{

    //Nødvendige elementer oprettes
    Element[] A;
    int maxElms;

    //Constructor-metode for PQHeap oprettes, med 0 elementer for 0 index.
    public PQHeap(int maxElms){
        A = new Element[0];
        this.maxElms = maxElms;
    }

    //minHeapify metode med 0-index.
    public void minHeap(int i){
        int l = 2*i+1;                                //Højre- og venstre barn angives for træets knude.
        int r = l+1;
        int MIN;
        if(l <= A.length-1 && A[l].key < A[i].key){   //Der kontrolleres om index er inden for A's grænser. Samt om venstre
            MIN= l;                                   //barns key, er mindre end i's key. I så fald sættes MIN til at være l,
        }else{                                        //ellers sættes MIN til at være i.
            MIN = i;
        }
        if(r <= A.length-1 && A[r].key < A[MIN].key){ //Samme rutine gentages for r, men kontrolleres i stedet for MIN.
            MIN = r;
        }
        if(MIN != i){                                 //Hvis MIN ikke er den omtalte knude på index i,
            Element temp = A[i];                      //bytter de to pågældende elementer plads i træet.
            A[i] = A[MIN];
            A[MIN] = temp;
            minHeap(MIN);                             //Metoden kaldes rekursivt på MIN.
        }
    }



    //Metode til extracting af mindste element.
    public Element extractMin() {
        Element min = A[0];                           //Første element i træet markeres som minimum.
        A[0] = A[A.length-1];                         //Det sidste element indsættes på første elements plads.
        A = Arrays.copyOf(A, A.length-1);        //Træets størrelse bliver forkortet med en.
        minHeap(0);                                    //minHeap køres rekursivt med startindex 0.
        return min;                                    //Mindste element returneres.
    }
    
    public static Object elementString(Element e){
        return e.data;
    }

    //Metode for indsættelse af nyt element.
    public void insert(Element e) {
        A = Arrays.copyOf(A, (A.length+1));           //Træets størrelse forlænges med 1, hvorefter det gældende
        A[A.length-1] = e;                            //element bliver sat ind på træets sidste plads.
        int i = A.length-1;

        while(i > 0 && A[(i-1)/2].key > A[i].key){    //Så længe elementer stadig findes i træet, og forældrens key
            Element temp = A[i];                      //er større end sidste elements key, så bytter de plads i træet.
            A[i] = A[(i-1)/2];
            A[(i-1)/2] = temp;
            i = (i-1)/2;                              //Det forhenværende sidste element sættes til at være forælder.
        }
    }
}