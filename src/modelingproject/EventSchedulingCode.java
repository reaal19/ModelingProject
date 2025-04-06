package modelingproject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class EventSchedulingCode{
 
    int EventSimulationLength;
    ArrayList<Event> Events = new ArrayList<Event>();
    String FEL="";
    String CheckoutList="";
    String[][] ScheduleData;

    public EventSchedulingCode(int Simulation_Length) { 
        this.Simulation_Length=Simulation_Length;
        EventSimulationLength=Simulation_Length*2;
    }
    
    public String[][] getData()
    {
        
        Simulate(); 
        for(int i=0; i<Simulation_Length; i++)
        {
           String arrive ="A";
           int ArriveT= customers[i].Arrival_Time;
           String depart ="D";
           int departT= customers[i].Service_End;
           Events.add(new Event(arrive, ArriveT));
           Events.add(new Event(depart, departT));
        }
      Collections.sort(Events,new EventComparator());
       int LQ=0;
       ScheduleData=new String[EventSimulationLength][6];
       ScheduleData[0][0]=Events.get(0).type; //TYPE
       ScheduleData[0][1]=Events.get(0).Time+""; //CLK
       ScheduleData[0][2]=LQ+""; //LQ
       ScheduleData[0][3]="1"; //LS
     CheckoutList= CheckoutList.concat("(C"+(1)+","+Events.get(0).Time+")");// (C1,2)
       ScheduleData[0][4]=CheckoutList;
       FEL="("+Events.get(1).type+","+Events.get(1).Time+") ("+Events.get(2).type+","+Events.get(2).Time+")";
       ScheduleData[0][5]=FEL;
                       int arrivalCount=2; //ARRIVAL COUNTER TO BUILD CHECKOUTLIST STRING

        for (int i = 1; i <ScheduleData.length; i++) {
          
                ScheduleData[i][0]=Events.get(i).type;
                ScheduleData[i][1]=Events.get(i).Time+"";
                if(ScheduleData[i][0].equals("A")) //In Case Of Arrival
                {
                   if(ScheduleData[i-1][3].equals("1")) // LS OF Previous Record = 1 
                   {
                       LQ++;
                        ScheduleData[i][2]=LQ+""; // Increment LQ  a
                         ScheduleData[i][3]="1"; //KEEP LS =1
                   }
                   else // LS OF PREVIOUS = 0
                   {
                       ScheduleData[i][2]=LQ+""; // Keep LQ as it's --> 0
                         ScheduleData[i][3]="1"; // Turn LS TO 1 
                       
                   }
                   CheckoutList= CheckoutList.concat("(C"+arrivalCount+","+Events.get(i).Time+")"); // Add Customer to CheckoutList --> At the end 
                   ScheduleData[i][4]=CheckoutList;
                   if(i<EventSimulationLength-2) // LAST TWO EVENTS WON'T HAVE FUTURE EVENTS 
                   {
                       FEL="("+Events.get(i+1).type+","+Events.get(i+1).Time+") ("+Events.get(i+2).type+","+Events.get(i+2).Time+")"; // add next two events to FEL
                   
                    
                   }
                   else
                   {
                       FEL=FEL.substring(FEL.indexOf(")")+1); // Remove next future event 
                   }
                   ScheduleData[i][5]=FEL; // update FEL
                   arrivalCount++; // Increment Arrival Count 
                }
                else //In case of departure
                {
                    
                    if(LQ>0) // If Queue isn't empty
                    {
                        LQ--; // Decrement Quueue
                        ScheduleData[i][2]=LQ+""; 
                        ScheduleData[i][3]="1"; // LS = 1
                    }
                    else{
                        LQ=0; // Keep LQ to zero, not sure if assigning is necessary
                        ScheduleData[i][2]=LQ+""; 
                        ScheduleData[i][3]="0"; // Set LS to Zero --> Customer Departed and the queue was empty --> server idle
                    }
                    CheckoutList=CheckoutList.substring(CheckoutList.indexOf(")")+1); //Remove Customer from checkoutList
                    ScheduleData[i][4]=CheckoutList;
                   
                    if(i<EventSimulationLength-2) // LAST TWO EVENTS WON'T HAVE FUTURE EVENTS 
                   {
                       FEL="("+ Events.get(i+1).type + "," +Events.get(i+1).Time+ "),("+Events.get(i+2).type+ "," +Events.get(i+2).Time+"),"; // Add Next Two Customers TO FEL
        
                    
                   }
                    else
                   {
                       FEL=FEL.substring(FEL.indexOf(")")+1); // At the last two records start removing from FEL
                   }
                    ScheduleData[i][5]=FEL;
                }
             
        }
       return ScheduleData;
    }
           
     public class EventComparator implements Comparator<Event>
       {
            //SORTS ASC according to TIME of EVENT
            @Override
            public int compare(Event o1, Event o2) {
               return o1.Time-o2.Time;
               
            }
           
       }
    class Event {

     
        String type; //Arrival or Departure
        int Time; 

        public Event(String type, int Time) {
            this.type = type;
            this.Time = Time;
        }

      
           @Override
        public String toString() {
            return "Event{" + "type=" + type + ", Time=" + Time + '}';
        }
    }
   
    public  static int computeServiceTime()
    {
        int RDA =(int) ((Math.random()*100)+1); // Generates a random integer from [1-100] inclusive
        if(RDA>=1 && RDA<=10) // Checks the value of the random generated number and returns its respective service time
            return 1;
        else if (RDA>=11 && RDA<=30) {
            return 2;
        } else if (RDA>=31 && RDA<=60) {
            return 3;
        } else if (RDA>=61 && RDA <=85) {
            return 4;
        } else if (RDA>=86 && RDA<=95) {
            return 5;
        }
        else
            return 6;
    }
    
    public static int computeIAT() {
        int RDA = (int) ((Math.random() * 1000) + 1); // Generates a random integer from [1-1000] inclusive
        if (RDA >= 1 && RDA <= 125) // Checks the value of the random generated number and returns its respective service time
            return 1;
        else if (RDA >= 126 && RDA <= 250) {
            return 2;
        } else if (RDA >= 251 && RDA <= 375) {
            return 3;
        } else if (RDA >= 376 && RDA <= 500) {
            return 4;
        } else if (RDA >= 501 && RDA <= 625) {
            return 5;
        } else if (RDA >= 626 && RDA <= 750) {
            return 6;
        } else if (RDA >= 751 && RDA <= 875) {
            return 7;
        } else
            return 8;
    }
    
    public static int Simulation_Length;
    public static Customer[] customers;

    public static void ComputeArrivalTimes()
    {
        customers[0].Arrival_Time= customers[0].IAT;
        for(int i=1; i<Simulation_Length; i++)
        {
            customers[i].Arrival_Time= customers[i-1].Arrival_Time + customers[i].IAT;
        }
    }
 public static void Simulate()
    {

        customers= new Customer[Simulation_Length];

        for(int i=0; i<Simulation_Length; i++)
        {
            customers[i]=new Customer();
            customers[i].IAT= computeIAT();
            customers[i].Service_Time= computeServiceTime();
        }

        ComputeArrivalTimes();


        for(int i=0; i<Simulation_Length; i++)
        {
            if(i==0)
            {
                customers[i].Service_Begin= customers[i].Arrival_Time;
            }
            else
            {
                customers[i].Service_Begin=Math.max(customers[i].Arrival_Time,customers[i-1].Service_End);
            }

            customers[i].Service_End= customers[i].Service_Begin + customers[i].Service_Time;
            customers[i].Waiting_Time= customers[i].Service_Begin- customers[i].Arrival_Time;
            customers[i].Time_Spent = customers[i].Waiting_Time+ customers[i].Service_Time;
            if(i==0)
            {
                customers[i].Server_Idle =customers[i].Arrival_Time;
            }
            else
            {
                customers[i].Server_Idle = customers[i].Service_Begin- customers[i-1].Service_End;
            }

        }
        
    }
    
    
}
