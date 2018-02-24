
package pagereplacementalgorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author meghagaonkar
 */
public class PageReplacementAlgorithm {
public void simulate_FIFO(Map<Integer, List<Integer>> pageTable,Queue<Process> q,int[] frame, int[] counter,int processCount)
    {
        int clockTick=0;
        int pageReferenced;
        int current_process;
        int count=0;
        Random random = new Random();
        while(clockTick<100)
        {   
            clockTick++;
            System.out.print("Clocktick "+clockTick+": ");
            current_process=q.peek().processId;

            if(q.peek().processLifeTime>1)
            {
                System.out.println("Process "+q.peek().processId+" is picked to run;");
                q.peek().processLifeTime=q.peek().processLifeTime-1;
                pageReferenced=random.nextInt(q.peek().processSize) + 1;
                System.out.println("\t\tPage "+pageReferenced+" is referenced;");
                
                if(frame[pageTable.get(current_process).get(0)]==0)
                {
                    count=count+1;
                    
                    frame[pageTable.get(current_process).get(0)]=pageReferenced;
                    counter[pageTable.get(current_process).get(0)]=count;
                    System.out.print("\t\tpage fault has occurred;");
                    System.out.println("Page "+pageReferenced+" has been placed in index"+pageTable.get(current_process).get(0)+" frame;");
                }else if (frame[pageTable.get(current_process).get(1)]==0)
                {
                    if(frame[pageTable.get(current_process).get(0)]==pageReferenced)
                    {
                        System.out.print("\t\tno page Fault;"); 
                    }
                    else{
                    count=count+1;
                    System.out.print("\t\tpage fault has occurred;");
                    frame[pageTable.get(current_process).get(1)]=pageReferenced;
                    counter[pageTable.get(current_process).get(1)]=count;
                    System.out.println("Page "+pageReferenced+" has been placed in index"+pageTable.get(current_process).get(1)+" frame;");
                    }
                    
                }else if (frame[pageTable.get(current_process).get(2)]==0)
                {
                    if(frame[pageTable.get(current_process).get(0)]==pageReferenced
                    ||frame[pageTable.get(current_process).get(1)]==pageReferenced)
                    {
                        System.out.print("\t\tno page Fault;"); 
                    }else{
                    System.out.print("\t\tpage fault has occurred;");
                    count=count+1;
                    frame[pageTable.get(current_process).get(2)]=pageReferenced;
                    counter[pageTable.get(current_process).get(2)]=count;
                    System.out.println("Page "+pageReferenced+" has been placed in index"+pageTable.get(current_process).get(2)+" frame;");
                    }
                }
                else
                {
                    if(frame[pageTable.get(current_process).get(0)]==pageReferenced 
                    ||frame[pageTable.get(current_process).get(1)]==pageReferenced||
                    frame[pageTable.get(current_process).get(2)]==pageReferenced)
                    {
                       System.out.print("\t\tno page Fault;"); 
                    }
                    else
                    {
                        count=count+1;
                        System.out.print("\t\tpage fault has occurred;"); 
                        
                        if(counter[pageTable.get(current_process).get(0)]<counter[pageTable.get(current_process).get(1)])
                        {
                         
                           if(counter[pageTable.get(current_process).get(0)]<counter[pageTable.get(current_process).get(2)]) 
                           {
                               //Page 4 replaces Page 3 in a frame;
                               System.out.println("\tPage "+pageReferenced+" replaces  Page "+frame[pageTable.get(current_process).get(0)]+" in a frame;");
                               frame[pageTable.get(current_process).get(0)]=pageReferenced;
                               counter[pageTable.get(current_process).get(0)]=count;
                           }
                           else
                           {
                               System.out.println("\tPage "+pageReferenced+" replaces  Page "+frame[pageTable.get(current_process).get(2)]+" in a frame;");
                               frame[pageTable.get(current_process).get(2)]=pageReferenced;
                               counter[pageTable.get(current_process).get(2)]=count;
                           }
                        
                        }else
                        {
                            if(counter[pageTable.get(current_process).get(1)]<counter[pageTable.get(current_process).get(2)]) 
                           {
                               System.out.println("\tPage "+pageReferenced+" replaces  Page "+frame[pageTable.get(current_process).get(1)]+" in a frame;");
                               frame[pageTable.get(current_process).get(1)]=pageReferenced;
                               counter[pageTable.get(current_process).get(1)]=count;
                           }
                           else
                           {
                               System.out.println("\tPage "+pageReferenced+" replaces  Page "+frame[pageTable.get(current_process).get(2)]+" in a frame;");
                               frame[pageTable.get(current_process).get(2)]=pageReferenced;
                               counter[pageTable.get(current_process).get(2)]=count;
                           }
                            
                        } 
                    }
                }
                System.out.println("\t\tRemaining lifetime: "+q.peek().processLifeTime);
                //Remove Process and add at end here
                Process temp=new Process();
                temp=q.poll();
                q.add(temp);
                
                
            }
            else
            {
                processCount=processCount+1;
                System.out.println("\tProcess "+q.peek().processId+" is picked to run;");
                //Page 3 is referenced; no page fault;
                pageReferenced=random.nextInt(q.peek().processSize) + 1;
                System.out.println("\t\tPage "+pageReferenced+" is referenced;");
                 if(frame[pageTable.get(current_process).get(0)]==pageReferenced 
                    ||frame[pageTable.get(current_process).get(1)]==pageReferenced||
                    frame[pageTable.get(current_process).get(2)]==pageReferenced)
                    {
                       System.out.print("\t\tno page Fault;"); 
                    }
                 else
                 {
                        count=count+1;
                        System.out.print("\t\tpage fault has occurred;"); 
                        if(frame[pageTable.get(current_process).get(0)]>0&&frame[pageTable.get(current_process).get(1)]>0&&
                                frame[pageTable.get(current_process).get(2)]>0)
                        {
                        if(counter[pageTable.get(current_process).get(0)]<counter[pageTable.get(current_process).get(1)])
                        {
                         
                           if(counter[pageTable.get(current_process).get(0)]<counter[pageTable.get(current_process).get(2)]) 
                           {
                               //Page 4 replaces Page 3 in a frame;
                               System.out.println("\tPage "+pageReferenced+" replaces  Page "+frame[pageTable.get(current_process).get(0)]+" in a frame;");
                               frame[pageTable.get(current_process).get(0)]=pageReferenced;
                               counter[pageTable.get(current_process).get(0)]=count;
                           }
                           else
                           {
                               System.out.println("\tPage "+pageReferenced+" replaces  Page "+frame[pageTable.get(current_process).get(2)]+" in a frame;");
                               frame[pageTable.get(current_process).get(2)]=pageReferenced;
                               counter[pageTable.get(current_process).get(2)]=count;
                           }
                        
                        }else
                        {
                            if(counter[pageTable.get(current_process).get(1)]<counter[pageTable.get(current_process).get(2)]) 
                           {
                               System.out.println("\tPage "+pageReferenced+" replaces  Page "+frame[pageTable.get(current_process).get(1)]+" in a frame;");
                               frame[pageTable.get(current_process).get(1)]=pageReferenced;
                               counter[pageTable.get(current_process).get(1)]=count;
                           }
                           else
                           {
                               System.out.println("\tPage "+pageReferenced+" replaces  Page "+frame[pageTable.get(current_process).get(2)]+" in a frame;");
                               frame[pageTable.get(current_process).get(2)]=pageReferenced;
                               counter[pageTable.get(current_process).get(2)]=count;
                           }
                          
                        }
                        }
                        else
                        {
                                if(frame[pageTable.get(current_process).get(1)]==0){
                                    
                                   // System.out.print("\t\tpage fault has occurred;");
                                    frame[pageTable.get(current_process).get(1)]=pageReferenced;
                                    counter[pageTable.get(current_process).get(1)]=count;
                                    System.out.println("Page "+pageReferenced+" has been placed in index"+pageTable.get(current_process).get(1)+" frame;");
                                }
                                else{
                                   // System.out.print("\t\tpage fault has occurred;");
                                    
                                    frame[pageTable.get(current_process).get(2)]=pageReferenced;
                                    counter[pageTable.get(current_process).get(2)]=count;
                                    System.out.println("Page "+pageReferenced+" has been placed in index"+pageTable.get(current_process).get(2)+" frame;");
                                    
                                }
                        }
                     
                 }
                System.out.println("\t\tRemaining lifetime: 0. Process "+q.peek().processId+" is deleted from the queue");
                frame[pageTable.get(q.peek().processId).get(0)]=0;
                frame[pageTable.get(q.peek().processId).get(1)]=0;
                frame[pageTable.get(q.peek().processId).get(2)]=0;
                counter[pageTable.get(q.peek().processId).get(0)]=0;
                counter[pageTable.get(q.peek().processId).get(1)]=0;
                counter[pageTable.get(q.peek().processId).get(2)]=0;
                pageTable.put(processCount, pageTable.get(q.peek().processId));
                q.remove();
                Process temp=new Process(processCount, random.nextInt(10) + 1,random.nextInt(9)+1);
                q.add(temp);
                System.out.println("\t\tProcess"+processCount+" is generated: size : "+temp.processSize +"pages, lifetime :"+temp.processLifeTime+"." );
                
            }
          
        }
        
    }
public void simulate_LRU(Map<Integer, List<Integer>> pageTable,Queue<Process> q,int[] frame, int[] counter,int processCount)
    {
        int clockTick=0;
        int pageReferenced;
        int current_process;
        int count=0;
        Random random = new Random();
        while(clockTick<100)
        {   current_process=q.peek().processId;
            clockTick++;
            System.out.print("Clocktick "+clockTick+": ");
            if(q.peek().processLifeTime>1)
            {
                System.out.println("Process "+q.peek().processId+" is picked to run;");
                q.peek().processLifeTime=q.peek().processLifeTime-1;
                pageReferenced=random.nextInt(q.peek().processSize) + 1;
                System.out.println("\t\tPage "+pageReferenced+" is referenced;");
                
                
                if(frame[pageTable.get(current_process).get(0)]==0)
                {
                    count=count+1;
                    
                    frame[pageTable.get(current_process).get(0)]=pageReferenced;
                    counter[pageTable.get(current_process).get(0)]=count;
                    System.out.print("\t\tpage fault has occurred;");
                    System.out.println("Page "+pageReferenced+" has been placed in index"+pageTable.get(current_process).get(0)+" frame;");
                }else if (frame[pageTable.get(current_process).get(1)]==0)
                {
                    if(frame[pageTable.get(current_process).get(0)]==pageReferenced)
                    {
                        count=count+1;
                        counter[pageTable.get(current_process).get(1)]=count;
                        System.out.print("\t\tno page Fault;"); 
                    }
                    else{
                    count=count+1;
                    System.out.print("\t\tpage fault has occurred;");
                    frame[pageTable.get(current_process).get(1)]=pageReferenced;
                    counter[pageTable.get(current_process).get(1)]=count;
                    System.out.println("Page "+pageReferenced+" has been placed in index"+pageTable.get(current_process).get(1)+" frame;");
                    }
                    
                }else if (frame[pageTable.get(current_process).get(2)]==0)
                {
                    if(frame[pageTable.get(current_process).get(0)]==pageReferenced
                    ||frame[pageTable.get(current_process).get(1)]==pageReferenced)
                    {
                        count=count+1;
                        System.out.print("\t\tno page Fault;"); 
                        counter[pageTable.get(current_process).get(2)]=count;
                    }else{
                    System.out.print("\t\tpage fault has occurred;");
                    count=count+1;
                    frame[pageTable.get(current_process).get(2)]=pageReferenced;
                    counter[pageTable.get(current_process).get(2)]=count;
                    System.out.println("Page "+pageReferenced+" has been placed in index"+pageTable.get(current_process).get(2)+" frame;");
                    }
                }
                else
                {
                    if(frame[pageTable.get(current_process).get(0)]==pageReferenced 
                    ||frame[pageTable.get(current_process).get(1)]==pageReferenced||
                    frame[pageTable.get(current_process).get(2)]==pageReferenced)
                    {
                        if(frame[pageTable.get(current_process).get(0)]==pageReferenced )
                       {
                        count=count+1;
                        counter[pageTable.get(current_process).get(0)]=count;
                           
                       }
                       else if(frame[pageTable.get(current_process).get(1)]==pageReferenced){
                        count=count+1;
                        counter[pageTable.get(current_process).get(1)]=count;
                       }
                       else{
                        count=count+1;
                        counter[pageTable.get(current_process).get(2)]=count;
                       }
                       System.out.print("\t\tno page Fault;"); 
                    }
                    else
                    {
                        count=count+1;
                        System.out.print("\t\tpage fault has occurred;"); 
                        
                        if(counter[pageTable.get(current_process).get(0)]<counter[pageTable.get(current_process).get(1)])
                        {
                         
                           if(counter[pageTable.get(current_process).get(0)]<counter[pageTable.get(current_process).get(2)]) 
                           {
                               //Page 4 replaces Page 3 in a frame;
                               System.out.println("\tPage "+pageReferenced+" replaces  Page "+frame[pageTable.get(current_process).get(0)]+" in a frame;");
                               frame[pageTable.get(current_process).get(0)]=pageReferenced;
                               counter[pageTable.get(current_process).get(0)]=count;
                           }
                           else
                           {
                               System.out.println("\tPage "+pageReferenced+" replaces  Page "+frame[pageTable.get(current_process).get(2)]+" in a frame;");
                               frame[pageTable.get(current_process).get(2)]=pageReferenced;
                               counter[pageTable.get(current_process).get(2)]=count;
                           }
                        
                        }else
                        {
                            if(counter[pageTable.get(current_process).get(1)]<counter[pageTable.get(current_process).get(2)]) 
                           {
                               System.out.println("\tPage "+pageReferenced+" replaces  Page "+frame[pageTable.get(current_process).get(1)]+" in a frame;");
                               frame[pageTable.get(current_process).get(1)]=pageReferenced;
                               counter[pageTable.get(current_process).get(1)]=count;
                           }
                           else
                           {
                               System.out.println("\tPage "+pageReferenced+" replaces  Page "+frame[pageTable.get(current_process).get(2)]+" in a frame;");
                               frame[pageTable.get(current_process).get(2)]=pageReferenced;
                               counter[pageTable.get(current_process).get(2)]=count;
                           }
                        
                            
                        }
                     
                        
                    }
                    
                }
                System.out.println("\t\tRemaining lifetime: "+q.peek().processLifeTime);
                //Remove Process and add at end here
                Process temp=new Process();
                temp=q.poll();
                q.add(temp);
                
                
            }
            else
            {
                processCount=processCount+1;
                System.out.println("Process "+q.peek().processId+" is picked to run;");
                //Page 3 is referenced; no page fault;
                pageReferenced=random.nextInt(q.peek().processSize) + 1;
                System.out.println("\t\tPage "+pageReferenced+" is referenced;");
                    if(frame[pageTable.get(current_process).get(0)]==pageReferenced 
                    ||frame[pageTable.get(current_process).get(1)]==pageReferenced||
                    frame[pageTable.get(current_process).get(2)]==pageReferenced)
                    {
                        if(frame[pageTable.get(current_process).get(0)]==pageReferenced )
                       {
                        count=count+1;
                        counter[pageTable.get(current_process).get(0)]=count;
                           
                       }
                       else if(frame[pageTable.get(current_process).get(1)]==pageReferenced){
                        count=count+1;
                        counter[pageTable.get(current_process).get(1)]=count;
                       }
                       else{
                        count=count+1;
                        counter[pageTable.get(current_process).get(2)]=count;
                       }
                       System.out.print("\t\tno page Fault;"); 
                    }
                    else
                    {
                        count=count+1;
                        System.out.print("\t\tpage fault has occurred;");
                        
                        if(frame[pageTable.get(current_process).get(0)]>0&&frame[pageTable.get(current_process).get(1)]>0&&
                                frame[pageTable.get(current_process).get(2)]>0)
                        {
                        if(counter[pageTable.get(current_process).get(0)]<counter[pageTable.get(current_process).get(1)])
                        {
                         
                           if(counter[pageTable.get(current_process).get(0)]<counter[pageTable.get(current_process).get(2)]) 
                           {
                               //Page 4 replaces Page 3 in a frame;
                               System.out.println("\tPage "+pageReferenced+" replaces  Page "+frame[pageTable.get(current_process).get(0)]+" in a frame;");
                               frame[pageTable.get(current_process).get(0)]=pageReferenced;
                               counter[pageTable.get(current_process).get(0)]=count;
                           }
                           else
                           {
                               System.out.println("\tPage "+pageReferenced+" replaces  Page "+frame[pageTable.get(current_process).get(2)]+" in a frame;");
                               frame[pageTable.get(current_process).get(2)]=pageReferenced;
                               counter[pageTable.get(current_process).get(2)]=count;
                           }
                        
                        }else
                        {
                            if(counter[pageTable.get(current_process).get(1)]<counter[pageTable.get(current_process).get(2)]) 
                           {
                               System.out.println("\tPage "+pageReferenced+" replaces  Page "+frame[pageTable.get(current_process).get(1)]+" in a frame;");
                               frame[pageTable.get(current_process).get(1)]=pageReferenced;
                               counter[pageTable.get(current_process).get(1)]=count;
                           }
                           else
                           {
                               System.out.println("\tPage "+pageReferenced+" replaces  Page "+frame[pageTable.get(current_process).get(2)]+" in a frame;");
                               frame[pageTable.get(current_process).get(2)]=pageReferenced;
                               counter[pageTable.get(current_process).get(2)]=count;
                           }
                        
                            
                        }}
                        else
                        {
                                if(frame[pageTable.get(current_process).get(1)]==0){
                                    count=count+1;
                                    //System.out.print("\t\tpage fault has occurred;");
                                    frame[pageTable.get(current_process).get(1)]=pageReferenced;
                                    counter[pageTable.get(current_process).get(1)]=count;
                                    System.out.println("Page "+pageReferenced+" has been placed in index"+pageTable.get(current_process).get(1)+" frame;");
                                }
                                else{
                                   // System.out.print("\t\tpage fault has occurred;");
                                    count=count+1;
                                    frame[pageTable.get(current_process).get(2)]=pageReferenced;
                                    counter[pageTable.get(current_process).get(2)]=count;
                                    System.out.println("Page "+pageReferenced+" has been placed in index"+pageTable.get(current_process).get(2)+" frame;");
                                    
                                }
                                    
                            
                        }
                     
                        
                    }
                System.out.println("\t\tRemaining lifetime: 0. Process "+q.peek().processId+" is deleted from the queue");
                frame[pageTable.get(q.peek().processId).get(0)]=0;
                frame[pageTable.get(q.peek().processId).get(1)]=0;
                frame[pageTable.get(q.peek().processId).get(2)]=0;
                counter[pageTable.get(q.peek().processId).get(0)]=0;
                counter[pageTable.get(q.peek().processId).get(1)]=0;
                counter[pageTable.get(q.peek().processId).get(2)]=0;
                pageTable.put(processCount, pageTable.get(q.peek().processId));
                q.remove();
                Process temp=new Process(processCount, random.nextInt(10) + 1,random.nextInt(9)+1);
                q.add(temp);
                System.out.println("\t\tProcess"+processCount+" is generated: size : "+temp.processSize +"pages, lifetime :"+temp.processLifeTime+"." );
                
            }
        
        }
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int processCount=10;
        Scanner scan = new Scanner(System.in);
        
        Process[] p=new Process[processCount];  
        Queue<Process> processQ=new LinkedList<Process>();  
        Random random = new Random();
        //int[] pageTable = new int[30];
        int[] frame =new int[30];
        int[] counter = new int[30];
        int lval=0;
        int nval=3;
        int input=0;
        //Creates 10 processes with id, lifetime and size
        for(int i=0;i<10;i++){
            p[i]=new Process(i, random.nextInt(10) + 1,random.nextInt(9)+1);
            processQ.add(p[i]);
            //page table 
            for(int j=lval;j<nval;j++)
            {
                //pageTable[j]=p[i].processId;
            }
            lval+=3;
            nval+=3;
            
        }
        //print processes
        for(Process p1:processQ)
        {
            System.out.println("ID: "+p1.processId+" Lifetime: "+p1.processLifeTime+" Size: "+p1.processSize);
        }
        
        //initializing frame and counter array to 0 indicating it is empty and is free to contain page
        
        for(int i=0; i<frame.length;i++)
        {
            frame[i]=0;
            counter[i]=0;
        }
        Map<Integer, List<Integer>> pageTable = new HashMap<Integer, List<Integer>>();
        // create list one and store values
        List<Integer> valSet1 = new ArrayList<Integer>();
        valSet1.add(0);
        valSet1.add(1);
        valSet1.add(2);
        // create list two and store values
        List<Integer> valSet2 = new ArrayList<Integer>();
        valSet2.add(3);
        valSet2.add(4);
        valSet2.add(5);
        // create list three and store values
        List<Integer> valSet3 = new ArrayList<Integer>();
        valSet3.add(6);
        valSet3.add(7);
        valSet3.add(8);
        // 4
        List<Integer> valSet4 = new ArrayList<Integer>();
        valSet4.add(9);
        valSet4.add(10);
        valSet4.add(11);
        // 5
        List<Integer> valSet5 = new ArrayList<Integer>();
        
        valSet5.add(12);
        valSet5.add(13);
        valSet5.add(14);
        // 6
        List<Integer> valSet6 = new ArrayList<Integer>();
       
        valSet6.add(15);
        valSet6.add(16);
         valSet6.add(17);
        // 7
        List<Integer> valSet7 = new ArrayList<Integer>();
        valSet7.add(18);
        valSet7.add(19);
        valSet7.add(20);
        // 8
        List<Integer> valSet8 = new ArrayList<Integer>();
        valSet8.add(21);
        valSet8.add(22);
        valSet8.add(23);
        // 9
        List<Integer> valSet9 = new ArrayList<Integer>();
        valSet9.add(24);
        valSet9.add(25);
        valSet9.add(26);
        // 10
        List<Integer> valSet10 = new ArrayList<Integer>();
        valSet10.add(27);
        valSet10.add(28);
        valSet10.add(29);
       
        // put values into map
        pageTable.put(0, valSet1);
        pageTable.put(1, valSet2);
        pageTable.put(2, valSet3);
        pageTable.put(3, valSet4);
        pageTable.put(4, valSet5);
        pageTable.put(5, valSet6);
        pageTable.put(6, valSet7);
        pageTable.put(7, valSet8);
        pageTable.put(8, valSet9);
        pageTable.put(9, valSet10);
        PageReplacementAlgorithm p1=new PageReplacementAlgorithm();
       
        System.out.print("Choose the page replacement algorithm.\n Enter 1: FIFO, 2: LRU 3: Exit\n ");
        try{
             input = scan.nextInt();
        }catch(NumberFormatException nfe){
            System.err.println("Invalid Format!");
        }
        switch (input) {
            case 1:
                p1.simulate_FIFO(pageTable,processQ,frame,counter,processCount-1);
                break;
            case 2:
                p1.simulate_LRU(pageTable,processQ,frame,counter,processCount-1);
                break;
                
            case 3:
                System.exit(0);
            default:
                System.out.print("Invalid Choice!");
                break;
        
       }
    }
    
}
