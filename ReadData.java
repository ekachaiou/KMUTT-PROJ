/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml_classification_weka;

import weka.core.Instances;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import weka.core.AttributeStats;
import weka.core.Instance;

/**
 *
 * @author ekachai
 */
public class ReadData {

    public static void main(String args[]) throws FileNotFoundException, IOException {
        String Path="D:\\works\\KMUTT\\video\\bigdata\\Year2009-2011-cleansing_online_retail_II_cleansing-RemoveWithValues-S0.0-C4-L1-8-M-Remove-R1,3,5,6,7,8.arff";
        BufferedReader reader = new BufferedReader(new FileReader(Path));
        Instances data = new Instances(reader);

        List<String> classes = new ArrayList<String>();
        
        AttInst f = new AttInst();
        String[] ItemName=f.getArrayItemName(Path);
        int ItemSize=0;
        ItemSize=ItemName.length;
        System.out.println("size="+ItemSize);
        //get number of instances
        int numInst = data.numInstances();
        System.out.println("num=" + numInst);
        //numInst=1000;
        //get number of attributes (notice class is not counted)
        data.setClassIndex(data.numAttributes() - 1);
        int[] intArray= new int[ItemSize];
        
        String chkInvoice="-";
        String Invoice="";
        String ObjectName="";
            try(FileOutputStream fileOutputStream = new FileOutputStream("d:\\test.csv")) {    
                fileOutputStream.write((displayHeaderList(ItemName)+"\n").getBytes());
                for (int j = 0; j < numInst; j++) {            
                Instance instance = data.instance(j);
                Invoice=String.valueOf(instance.value(0)).replace(".0","");
                if (Invoice.equals(chkInvoice)){

                }else{
                    if (j>0)
                    //System.out.println(displayArrayList(intArray));
                    fileOutputStream.write((displayArrayList(intArray)+"\n").getBytes());
                    setZeroArray(intArray);
                    chkInvoice=Invoice;
                }
                double cV = instance.classValue();
                ObjectName=instance.classAttribute().value((int) cV);
                //intArray[Arrays.asList(ItemName).indexOf(ObjectName.trim())]=1;
                intArray[findindexofArray(ItemName,ObjectName.trim())]=1;
                
                //System.out.println(instance.classAttribute().value((int) cV));
                System.out.println(Invoice + " , "+ ObjectName);
            }
        } catch (FileNotFoundException e) {
            // exception handling
        } catch (IOException e) {
            // exception handling
        }
        
        
        
        System.out.println("index="+Arrays.asList(ItemName).indexOf("DOG BOWL   CHASING BALL DESIGN"));
        System.out.println("ARRAY=="+ItemName[Arrays.asList(ItemName).indexOf("DOG BOWL   CHASING BALL DESIGN")]);
        reader.close();
        System.out.println("xxxxxxxxxxxxxxxxxx");
    }
   public static int findindexofArray(String str[],String word){
        int ic=0;
        for (int i = 0; i < str.length; i++) {
            if (str[i].equals(word.trim())){
                ic=i;
                break;
            }
       } 
       return ic;
   }
   public static int[] setZeroArray(int[] ar){
       for (int i = 0; i < ar.length; i++) {
           ar[i]=0;
       } 
        return ar;
   }
   public static String displayArrayList(int[] ar){
       String str="";
       for (int i = 0; i < ar.length; i++) {
           if (i==0){
               str=String.valueOf(ar[i]);
           }else{
               str=str + ","+String.valueOf(ar[i]);
           }
       } 
        return str;
   }
   public static String displayHeaderList(String[] ar){
       String str="";
       for (int i = 0; i < ar.length; i++) {
           if (i==0){
               str="\""+String.valueOf(ar[i])+"\"";
           }else{
               str=str + ",\""+String.valueOf(ar[i])+"\"";
           }
       } 
        return str;
   }
    public static void mainxx(String args[]) {
        String[] name1 = {"Amy", "Jose", "Jeremy", "Alice", "Patrick"};

        String[] name2 = {"Alan", "Amy", "Jeremy", "Helen", "Alexi"};

        String[] name3 = {"Adel", "Aaron", "Amy", "James", "Alice"};

        Set<String> letter = new HashSet<String>();

        for (int i = 0; i < name1.length; i++) {
            letter.add(name1[i]);
        }

        for (int j = 0; j < name2.length; j++) {
            letter.add(name2[j]);
        }

        for (int k = 0; k < name3.length; k++) {
            letter.add(name3[k]);
        }

        System.out.println(letter.size() + " letters must be sent to: " + letter);

    }
}
