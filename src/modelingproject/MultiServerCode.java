package modelingproject;

import java.util.*;

class Customer {//Customer Who buys food 

    public int IAT;
    public int Arrival_Time;
    public int Service_Time;
    public int Service_Begin;
    public int Service_End;
    public int Waiting_Time;
    public int Time_Spent;
    public int Server_Idle;

}

public class MultiServerCode {

    double sumWait = 0, sumA = 0, sumB = 0, sumC = 0, averageWaiting, A_util, B_util, C_util;//for Computed Statistics
    int num1, num2;// the two random number of the interarrival time and the service time
    int Simulation_Length;//Simulation Length = Number of customer
    Customer[] customers;
    //Array list To store the value of each customer enter and in which server(cashier)
    ArrayList<Customer> a = new ArrayList<Customer>();
    ArrayList<Customer> b = new ArrayList<Customer>();
    ArrayList<Customer> c = new ArrayList<Customer>();
    String data[][];//to store all data to convert it into the array of data

    public MultiServerCode(int length) {
        Simulation_Length = length;
        data = new String[Simulation_Length][13];//number of colums we have are 13
    }

    public void ComputeArrivalTimes() {
        customers[0].Arrival_Time = customers[0].IAT;
        for (int i = 1; i < Simulation_Length; i++) {
            customers[i].Arrival_Time = customers[i - 1].Arrival_Time + customers[i].IAT;
        }
    }

    public String[][] getTable() {

        for (int i = 0; i < Simulation_Length; i++) {

                //First cashier
            if (a.contains(customers[i])) {
                data[i][0] = i + 1 + "";
                data[i][1] = customers[i].IAT + "";
                data[i][2] = customers[i].Arrival_Time + "";
                data[i][3] = customers[i].Service_Time + "";
                data[i][4] = customers[i].Service_Begin + "";
                data[i][5] = customers[i].Service_End + "";
                data[i][12] = customers[i].Waiting_Time + "";

            }   //Second cashier
            else if (b.contains(customers[i])) {
                data[i][0] = (i + 1) + "";
                data[i][1] = customers[i].IAT + "";
                data[i][2] = customers[i].Arrival_Time + "";
                data[i][6] = customers[i].Service_Time + "";
                data[i][7] = customers[i].Service_Begin + "";
                data[i][8] = customers[i].Service_End + "";
                data[i][12] = customers[i].Waiting_Time + "";
            } else {
                //Third cashier
                data[i][0] = (i + 1) + "";
                data[i][1] = customers[i].IAT + "";
                data[i][2] = customers[i].Arrival_Time + "";
                data[i][9] = customers[i].Service_Time + "";
                data[i][10] = customers[i].Service_Begin + "";
                data[i][11] = customers[i].Service_End + "";
                data[i][12] = customers[i].Waiting_Time + "";
            }
        }
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                if (data[i][j] == null) {
                    data[i][j] = "0";
                }
            }
        }
        return data;
    }

    // InterArrivalTime
    int interArrivalTime() {
        num1 = (int) (Math.random() * 100 + 1);
        if (num1 >= 1 && num1 <= 25) {
            num1 = 1;
        } else if (num1 >= 26 && num1 <= 65) {
            num1 = 2;
        } else if (num1 >= 66 && num1 <= 85) {
            num1 = 3;
        } else if (num1 >= 86 && num1 <= 100) {
            num1 = 4;
        }
        return num1;
    }

    //ServiceTime First cashier
    int serviceTimeA() {
        num2 = (int) (Math.random() * 100 + 1);
        if (num2 >= 1 && num2 <= 30) {
            num2 = 2;
        } else if (num2 >= 31 && num2 <= 58) {
            num2 = 3;
        } else if (num2 >= 59 && num2 <= 83) {
            num2 = 4;
        } else if (num2 >= 84 && num2 <= 100) {
            num2 = 5;
        }
        return num2;
    }

    //ServiceTime Second cashier
    int serviceTimeB() {
        num2 = (int) (Math.random() * 100 + 1);
        if (num2 >= 1 && num2 <= 35) {
            num2 = 3;
        } else if (num2 >= 36 && num2 <= 60) {
            num2 = 4;
        } else if (num2 >= 61 && num2 <= 80) {
            num2 = 5;
        } else if (num2 >= 81 && num2 <= 100) {
            num2 = 6;
        }
        return num2;
    }

    //ServiceTime third cashier
    int serviceTimeC() {
        num2 = (int) (Math.random() * 100 + 1);
        if (num2 >= 1 && num2 <= 30) {
            num2 = 2;
        } else if (num2 >= 31 && num2 <= 63) {
            num2 = 3;
        } else if (num2 >= 64 && num2 <= 100) {
            num2 = 4;
        }
        return num2;
    }

    public void Simulate() {

        customers = new Customer[Simulation_Length];
        //Calculate the arrival time and the IAT
        for (int i = 0; i < Simulation_Length; i++) {
            customers[i] = new Customer();
            customers[i].IAT = interArrivalTime();
        }
        ComputeArrivalTimes();

        for (int i = 0; i < Simulation_Length; i++) {
            if (i == 0) {
                // first customer will go to First cashier
                customers[i].Service_Time = serviceTimeA();
                customers[i].Service_Begin = customers[i].Arrival_Time;
                customers[i].Service_End = customers[i].Service_Begin + customers[i].Service_Time;
                customers[i].Waiting_Time = customers[i].Service_Begin - customers[i].Arrival_Time;
                a.add(customers[i]);

                // if customer to came when First cashier was idle
            } else if (customers[i].Arrival_Time >= a.get(a.size() - 1).Service_End) {
                customers[i].Service_Time = serviceTimeA();
                customers[i].Service_Begin = customers[i].Arrival_Time;
                customers[i].Service_End = customers[i].Service_Begin + customers[i].Service_Time;
                customers[i].Waiting_Time = customers[i].Service_Begin - customers[i].Arrival_Time;
                a.add(customers[i]);

                // if customer to came when First cashier was busy and Second cashier was idle
            } else if (b.isEmpty() || customers[i].Arrival_Time >= b.get(b.size() - 1).Service_End) {
                customers[i].Service_Time = serviceTimeB();
                customers[i].Service_Begin = customers[i].Arrival_Time;
                customers[i].Service_End = customers[i].Service_Begin + customers[i].Service_Time;
                customers[i].Waiting_Time = customers[i].Service_Begin - customers[i].Arrival_Time;
                b.add(customers[i]);

                // if customer to came when First cashier was busy and Second cashier was busy and third cashier was idle
            } else if (c.isEmpty() || customers[i].Arrival_Time >= c.get(c.size() - 1).Service_End) {
                customers[i].Service_Time = serviceTimeC();
                customers[i].Service_Begin = customers[i].Arrival_Time;
                customers[i].Service_End = customers[i].Service_Begin + customers[i].Service_Time;
                customers[i].Waiting_Time = customers[i].Service_Begin - customers[i].Arrival_Time;
                c.add(customers[i]);

                // if customer to came when First cashier was busy and Second cashier was busy  and third cashier was busy and First cashier will finish first
            } else if ((a.get(a.size() - 1).Service_End <= b.get(b.size() - 1).Service_End) && (a.get(a.size() - 1).Service_End <= c.get(c.size() - 1).Service_End)) {
                customers[i].Service_Time = serviceTimeA();
                customers[i].Service_Begin = a.get(a.size() - 1).Service_End;
                customers[i].Service_End = customers[i].Service_Begin + customers[i].Service_Time;
                customers[i].Waiting_Time = customers[i].Service_Begin - customers[i].Arrival_Time;
                a.add(customers[i]);

                // if customer to came when First cashier was busy and Second cashier was busy  and third cashier was busy and Second cashier will finish first
            } else if ((b.get(b.size() - 1).Service_End <= a.get(a.size() - 1).Service_End) && (b.get(c.size() - 1).Service_End <= c.get(c.size() - 1).Service_End)) {
                customers[i].Service_Time = serviceTimeB();
                customers[i].Service_Begin = b.get(b.size() - 1).Service_End;
                customers[i].Service_End = customers[i].Service_Begin + customers[i].Service_Time;
                customers[i].Waiting_Time = customers[i].Service_Begin - customers[i].Arrival_Time;
                b.add(customers[i]);

            } else { // if customer to came when First cashier was busy and Second cashier was busy  and third cashier was busy and third cashier will finish first
                customers[i].Service_Time = serviceTimeC();
                customers[i].Service_Begin = c.get(c.size() - 1).Service_End;
                customers[i].Service_End = customers[i].Service_Begin + customers[i].Service_Time;
                customers[i].Waiting_Time = customers[i].Service_Begin - customers[i].Arrival_Time;
                c.add(customers[i]);
            }
        }
    }

    public void Compute_Statestics() {

        for (int i = 0; i < Simulation_Length; i++) {
            sumWait = sumWait + customers[i].Waiting_Time;
        }

        averageWaiting = Math.round((sumWait / Simulation_Length) * 100.0) / 100.0;

        for (int i = 0; i < Simulation_Length; i++) {

            if (a.contains(customers[i])) {

                sumA = sumA + customers[i].Service_Time;

            } else if (b.contains(customers[i])) {

                sumB = sumB + customers[i].Service_Time;
            } else {
                sumC = Math.round((sumC + customers[i].Service_Time) * 100.0) / 100.0;
            }
        }

        A_util = (Math.round((sumA / a.get(a.size() - 1).Service_End) * 100.0) / 100.0) * 100;
        try {
            B_util = (Math.round((sumB / b.get(b.size() - 1).Service_End) * 100.0) / 100.0) * 100;
        } catch (Exception E) {
            B_util = 0;
        }

        try {
            C_util = (Math.round((sumC / c.get(c.size() - 1).Service_End) * 100.0) / 100.0) * 100;
        } catch (Exception E) {
            C_util = 0;
        }
    }
}
