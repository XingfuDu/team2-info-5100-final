package readleadformdata;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Readleadformdata {
	
	 public static void main(String[] args) { 
		 try { BufferedReader reader = new BufferedReader(new FileReader("xxx.csv"));//换成对应的文件名
               String line = null; 
               while((line=reader.readLine())!=null){ 
                 String item[] = line.split(",");            
                 String last = item[item.length-1];//所要的数据
             } 
         } catch (Exception e) { 
             e.printStackTrace(); 
         } 
     } 

	 public static ArrayList<String> customerdata(String pathName){
	        ArrayList<String> allLeads = new ArrayList<>();
	            String[] arr = leadformdata.split("\n"); //将获取的字符按行分割
	             //获取每一行的数据并封装成Lead对象
	            for (int i = 0; i < arr.length; i++) {
	                String[] line = arr[i].split(",");
	                Lead myLead = new Lead(Integer.parseInt(line[0]),line[1],line[2],line[3],line[4],line[5],line[6],line[7]);
	                allLeads.add(myLead);
	            }
	        }	 
	 
	  return allLeads;	
}
