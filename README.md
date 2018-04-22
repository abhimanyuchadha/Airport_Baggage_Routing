# Airport_Baggage_Routing

The application aims to find the shortest path and distance for routing baggage at airport.

To run the application, use com.barclays.application.Main class.

The application assumes that input on console will be in the section order; Conveyor System, Departures and Bags, see below for an example:

## NOTE: After entering the input, press enter twice for the output, else the system will keep waiting for the input.

\# Section: Conveyor System
Concourse_A_Ticketing A5 5
A5 BaggageClaim 5  
A5 A10 4  
A5 A1 6  
A1 A2 1  
A2 A3 1  
A3 A4 1  
A10 A9 1  
A9 A8 1  
A8 A7 1  
A7 A6 1  
\# Section: Departures  
UA10 A1 MIA 08:00  
UA11 A1 LAX 09:00  
UA12 A1 JFK 09:45  
UA13 A2 JFK 08:30  
UA14 A2 JFK 09:45  
UA15 A2 JFK 10:00  
UA16 A3 JFK 09:00  
UA17 A4 MHT 09:15  
UA18 A5 LAX 10:15  
\# Section: Bags  
0001 Concourse_A_Ticketing UA12  
0002 A5 UA17  
0003 A2 UA10  
0004 A8 UA18  
0005 A7 ARRIVAL  


The ouput of the application will be the optimized route for the each bag
Following will be the output for the above Input:  

0001 Concourse_A_Ticketing A5 A1 : 11  
0002 A5 A1 A2 A3 A4 : 9  
0003 A2 A1 : 1  
0004 A8 A9 A10 A5 : 6  
0005 A7 A8 A9 A10 A5 BaggageClaim : 12  
