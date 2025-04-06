package modelingproject;

class Day {

    public int randomDigitOfType;
    public String typeOfday;
    public int randomDigitDemand;
    public int demand;
    public float revenue;
    public float lostProfit;
    public float salvageFromScrap;
    public float dailyProfit;
}

public class ClassicalInventoryCode {

    int num, num2 = 0;//for random digit
    float total = 0;//to store in the total profit
    int numberOfDays, Quantity, costOfBuying, costOfSelling, costOfScrap, CostOfLostInProfit;
    String type;//day type
    Day[] days;
    String data[][];

    public ClassicalInventoryCode(int length, int Quantity, int costOfBuying, int costOfSelling, int costOfScrap, int CostOfLostInProfit) {
        numberOfDays = length;
        data = new String[numberOfDays][9];
        this.Quantity = Quantity;
        this.costOfBuying = costOfBuying;
        this.costOfSelling = costOfSelling;
        this.costOfScrap = costOfScrap;
        this.CostOfLostInProfit = CostOfLostInProfit;

    }

    public void typeAndDemand() {
        for (int i = 0; i < numberOfDays; i++) {
            if (num >= 1 && num <= 35) {
                type = "Good";
                if (num2 >= 1 && num2 <= 3) {
                    num2 = 40;
                } else if (num2 >= 4 && num2 <= 8) {
                    num2 = 50;
                } else if (num2 >= 9 && num2 <= 23) {
                    num2 = 60;
                } else if (num2 >= 24 && num2 <= 43) {
                    num2 = 70;
                } else if (num2 >= 44 && num2 <= 78) {
                    num2 = 80;
                } else if (num2 >= 79 && num2 <= 93) {
                    num2 = 90;
                } else if (num2 >= 94 && num2 <= 100) {
                    num2 = 100;
                }
            } else if (num >= 36 && num <= 80) {
                type = "Fair";
                if (num2 >= 1 && num2 <= 10) {
                    num2 = 40;
                } else if (num2 >= 11 && num2 <= 28) {
                    num2 = 50;
                } else if (num2 >= 29 && num2 <= 68) {
                    num2 = 60;
                } else if (num2 >= 69 && num2 <= 88) {
                    num2 = 70;
                } else if (num2 >= 89 && num2 <= 96) {
                    num2 = 80;
                } else if (num2 >= 97 && num2 <= 100) {
                    num2 = 90;
                }
            } else if (num >= 81 && num <= 100) {
                type = "Poor";
                if (num2 >= 1 && num2 <= 44) {
                    num2 = 40;
                } else if (num2 >= 45 && num2 <= 66) {
                    num2 = 50;
                } else if (num2 >= 67 && num2 <= 82) {
                    num2 = 60;
                } else if (num2 >= 83 && num2 <= 94) {
                    num2 = 70;
                } else if (num2 >= 95 && num2 <= 100) {
                    num2 = 80;
                }
            }

        }
    }

    public void Simulate() {
        days = new Day[numberOfDays];
        for (int i = 0; i < numberOfDays; i++) {
            days[i] = new Day();
            days[i].randomDigitOfType = (int) (Math.random() * 100 + 1);
            num = days[i].randomDigitOfType;
            days[i].randomDigitDemand = (int) (Math.random() * 100 + 1);
            num2 = days[i].randomDigitDemand;
            typeAndDemand();
            days[i].typeOfday = type;
            days[i].demand = num2;

            if (days[i].demand > Quantity) {
                days[i].revenue = (Quantity * costOfSelling) / 100;
                days[i].lostProfit = ((days[i].demand - Quantity) * CostOfLostInProfit) / 100;
            } else {
                days[i].revenue = (days[i].demand * costOfSelling) / 100;
                days[i].lostProfit = 0;
            }

            if (Quantity <= days[i].demand) {
                days[i].salvageFromScrap = 0;
            } else if (Quantity > days[i].demand) {
                days[i].salvageFromScrap = ((Quantity - days[i].demand) * costOfScrap) / 100;
            }
            days[i].dailyProfit = days[i].revenue - ((Quantity * costOfBuying) / 100) - days[i].lostProfit + days[i].salvageFromScrap;
        }

    }

    public String[][] getTable() {
        data = new String[numberOfDays][9];
        for (int i = 0; i < numberOfDays; i++) {
            data[i][0] = i + 1 + "";
            data[i][1] = days[i].randomDigitOfType + "";
            data[i][2] = days[i].typeOfday + "";
            data[i][3] = days[i].randomDigitDemand + "";
            data[i][4] = days[i].demand + "";
            data[i][5] = days[i].revenue + "";
            data[i][6] = days[i].lostProfit + "";
            data[i][7] = days[i].salvageFromScrap + "";
            data[i][8] = days[i].dailyProfit + "";
        }

        return data;
    }

    public float Compute_Statestics() {

        for (int i = 0; i < numberOfDays; i++) {
            total = total + days[i].dailyProfit;
        }
        return total;
    }

}
