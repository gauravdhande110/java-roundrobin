import java.util.Scanner;
import java.util.LinkedList; 
import java.util.Queue; 
import java.util.*;
class first{
     int n,total=0,pos,temp=0,quntum;
     float  waitavg, turnavg;
     int process[],bursttime [],waittime [],turntime [],completion[],bursttime1 [],artime[];
     Queue<Integer> q = new LinkedList<>();
     Queue<Integer> pstack = new LinkedList<>();    
     public static void main(String args[]) throws NoSuchElementException 
  {
    first a = new first();
      a.input();
      a.round();
      a.print();
}
void input(){
     Scanner s = new Scanner(System.in);
     System.out.println("enter number of process: ");
      n = s.nextInt();
      process= new int[n];
      bursttime = new int[n];
      waittime= new int[n];
      turntime = new int[n];
      completion = new int[n];
      bursttime1 = new int[n];
      artime = new int[n];
      System.out.println("enter Burst time for process");
     for(int i = 0;i < n; i++ )
         {
            System.out.print("process " +(i+1) + ":");
	    System.out.print("bursttime : ");
            bursttime[i] = s.nextInt();
	    bursttime1[i]=bursttime[i];
	    System.out.print("arival time : ");
	    artime[i] = s.nextInt();
	    process[i]=i+1;
	 }
      System.out.println("enter quntum size");
      quntum = s.nextInt();    
}
void update(int x, int b)
{
  for( int i=0;i<n;i++)
		{
		if (artime[i] <= x && bursttime[i]!=0 && b != i)
 		 {
		    if(!q.contains(i+1))	
                    q.add(i+1); 
                }
}
}
void  round()
{
        int sumburst =0 ;
        int red = 0  ; 
        update(0,-1);
     while (!q.isEmpty())
        {
         int a = q.remove();
         bursttime[a-1] = bursttime[a-1] - quntum;
         if(bursttime[a-1]<= 0)
          {
            red = bursttime[a-1];  
           bursttime[a-1] = 0;
 	 }
         else
        {
          red = 0; 
          
        }
        for (int j=0;j<n;j++)
			{
		         if ((a-1)!= j  && bursttime[j]!=0)			
			  waittime[j]=waittime[j] + (quntum + red); 
			}
         pstack.add(a);
	 sumburst = sumburst + quntum + red ;
	 update(sumburst,a-1);
	if(bursttime[a-1]> 0) q.add(a);
          
        }
}
void print()
{
while(!pstack.isEmpty())
{
System.out.println("period QUNTUM :"+pstack.remove());
}
 System.out.println("\n\n\n PROCESS\t artime \t fintime\t turntime\twaittime\n ");
	   System.out.println("---------------------------------------------------------------------");
          for (int j=0;j<n;j++)
			{
			completion[j] = waittime[j] + bursttime1[j];
			turntime[j] = completion[j] - artime[j];
			waittime[j] = waittime[j] - artime[j];
     			System.out.println("\n"+process[j]+"\t\t"+artime[j]+"\t\t"+completion[j]+"\t\t"+turntime[j]+"\t\t"+waittime[j]);
			}
            System.out.println("---------------------------------------------------------------------");
            waitavg = 0 ;
             for (int j=0;j<n;j++) 
             {
              waitavg = waitavg + waittime[j];
             }
            waitavg= waitavg/n;
		System.out.println("waittime average :"+waitavg);
            turnavg = 0 ;
             for (int j=0;j<n;j++) 
             {
              turnavg  = turnavg  + turntime[j];
             }
            turnavg = turnavg /n;     
		System.out.println("turnavg average :"+turnavg);
		System.out.println("---------------------------------------------------------------------");
}
}

