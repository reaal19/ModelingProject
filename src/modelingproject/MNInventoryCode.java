package modelingproject;
public class MNInventoryCode {
   int cycleLength, noOfCycles, startInventory, simulationLength, condition, Quantity;
   double shortageCount=0;
   Day[] days;
   String[][] data;

    public MNInventoryCode(int cycleLength, int noOfCycles, int startInventory, int condition, int Quantity) {
        this.cycleLength = cycleLength;
        this.noOfCycles = noOfCycles;
        this.startInventory = startInventory;
        this.condition = condition;
        this.Quantity = Quantity;
        this.simulationLength=noOfCycles*cycleLength;
    }

       class Day{
        int demandRD, demand, inventoryBeg, inventoryEnd, backOrder, orderQuantity, leadTimeRD, leadTime;

        public void setDemandRD() {
            demandRD= (int) ((Math.random()*100)+1);
        }

        public void setDemand() {
            if(demandRD<=33)
            {
                demand = 0;
            }
            else if(demandRD<=58)
            {
                demand = 1;
            }
            else if(demandRD<=78)
            {
                demand = 2;
            }
            else if (demandRD<=90)
            {
                demand = 3;
            }
            else 
            {
                demand =4;
            }
        }
        public void setleadTimeRD()
        {
            leadTimeRD= (int) ((Math.random()*100)+1);
        }
        public void setLeadTime()
        {
            if(leadTimeRD <=30) 
                leadTime=0;
            else if(leadTimeRD<=80)
                leadTime=1;
            else 
                leadTime=2;
        }
       
        }
 
     public  void checkLeadTime(Day d)
     {
      if(d.leadTime<0)
             System.out.print("-");
      else
             System.out.print(d.leadTime);
     }

     public  void simulate()
    {
        days=new Day[simulationLength];
        days[0]=new Day();
        days[0].inventoryBeg= startInventory;
        days[0].setDemandRD();
        days[0].setDemand();
        days[0].inventoryEnd=days[0].inventoryBeg-days[0].demand;
        days[0].backOrder=0;
        days[0].orderQuantity=0;
        days[0].leadTime=-1;
        days[0].leadTimeRD=0;
        int order=0;
        
        for(int i=1; i<simulationLength; i++)
        {
            days[i]=new Day();
            days[i].setDemandRD();
            days[i].setDemand();
           
            
            if(days[i-1].leadTime==0)
            {
                days[i].inventoryBeg=days[i-1].inventoryEnd+Quantity;
            }
            else
            {
                days[i].inventoryBeg=days[i-1].inventoryEnd;
            }
                // may result in an error ?? 
                if((days[i].demand+days[i-1].backOrder)>days[i].inventoryBeg)
                {
                    days[i].inventoryEnd=0;
                    days[i].backOrder=days[i].demand+days[i-1].backOrder-days[i].inventoryBeg;
                    shortageCount++;
                }
                else
                {
                    days[i].inventoryEnd=days[i].inventoryBeg-days[i].demand-days[i-1].backOrder;
                    days[i].backOrder=0;
                }
                 if( ((i+1)%cycleLength==0&&days[i].inventoryEnd<=condition)) // new Cycle
                 {
                days[i].setleadTimeRD();
                days[i].setLeadTime();
                
                days[i].orderQuantity=Quantity;
                
                }
                else
                 {
                    days[i].leadTime=(days[i-1].leadTime)-1;
                     days[i].leadTimeRD=0;
                   days[i].orderQuantity=0;
            }
            
            }
         
        
    }
    
    public  String[][] getTable() {

        
        data = new String[simulationLength][10];
        int cycle = 0;
        for (int i = 0; i < this.simulationLength; i++) {
            if ((i) % cycleLength == 0) {
                cycle++;
            }
            data[i][0]=cycle+"";
            data[i][1]=(i+1)+"";
            data[i][2]=days[i].inventoryBeg+"";
            data[i][3]=days[i].demandRD+"";
            data[i][4]=days[i].demand+"";
            data[i][5]=days[i].inventoryEnd+"";
            data[i][6]=days[i].backOrder+"";
            data[i][7]=days[i].orderQuantity+"";
            data[i][8]=days[i].leadTimeRD+"";
            if(days[i].leadTime<0)
                data[i][9]="-";
            else
                data[i][9]=days[i].leadTime+"";
           
        }
        return data;
    }
    
}
