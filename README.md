# ModelingProject
# 🛒 ModelingProject – Supermarket Simulation Suite

This project is a Java-based simulation suite for modeling and analyzing different real-world supermarket operations. Built with a GUI in Java Swing and powered by random event generation, the system provides four key simulations used in operations research and systems modeling.

## 📦 Modules

### 1. 🎛️ Multi-Server Queuing System
Simulates a supermarket with **three parallel cashiers**, each having different service time distributions. Tracks:
- Customer arrival and service times
- Waiting times
- Utilization of each server (cashier)

### 2. 📉 Classical Inventory Simulation
Simulates daily inventory management over several days, accounting for:
- Demand based on day type (Good, Fair, Poor)
- Costs, revenues, salvage, and lost profit
- Total profit calculation

### 3. 🧾 (M, N) Inventory Model
Models inventory management with restocking cycles and stochastic demand:
- Calculates shortages and lead times
- Shows order quantity logic and shortage proportions

### 4. 📅 Event Scheduling System
Event-driven simulation using a **Future Event List (FEL)**:
- Tracks arrivals and departures
- Manages queue logic
- Simulates server utilization and dynamic state changes

## 🖥️ Technologies Used
- Java SE
- Swing (GUI)
- Ant (Build tool)
- NetBeans project structure

---

## 🚀 How to Run

### ✅ Using NetBeans
1. Clone or download the repository.
2. Open the project in **NetBeans**.
3. Press **Run** or right-click → `Run`.
4. Choose your desired simulation from the GUI start menu.

### ✅ Using Command Line
1. Make sure you have Java and Ant installed.
2. Navigate to the project root directory.
3. Run:
   ```bash
   ant clean
   ant jar
   java -jar dist/ModelingProject.jar
