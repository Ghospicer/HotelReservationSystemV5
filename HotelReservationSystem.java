import java.util.*;

public class HotelReservationSystem {

    public static void main(String[] args) {
    	List<Services> service = new ArrayList<Services>();
        String resCity;
        int id = 0;
        int choice;
        int serviceChoice;
        Scanner sc = new Scanner(System.in);

        while(true) {       		
        	
        	for(MenuOptions rm: EnumSet.range(MenuOptions.FIRST, MenuOptions.EXIT)) {
        		System.out.printf("%-100s\n", rm.menuOptions());
        		System.out.printf("\n");
        	}        	

            choice = sc.nextInt();
            System.out.printf("\n");

            if (choice==1) {
            	System.out.println("ROOM INFOS: \n");
            	for(MenuOptions rm: EnumSet.range(MenuOptions.SINGLE, MenuOptions.SUITE)) {
            		System.out.printf("%-100s\n", rm.menuOptions());
            	} 
            	System.out.printf("\n");
            	System.out.printf("%s\n", "Hotel Name: ");
            	String hotelName = sc.next() + " " + sc.next();
                System.out.printf("%s\n", "Room Type: ");
                String roomType = sc.next();
                System.out.printf("%s\n", "Reservation Month: ");
                String reservationMonth = sc.next();
                System.out.printf("%s\n", "Reservation Start: ");
                int reservationStart = sc.nextInt();
                System.out.printf("%s\n", "Reservation End: ");
                int reservationEnd = sc.nextInt();
                id++;
                Room rm = new Room(roomType);
                Reservation reserv = new Reservation(reservationEnd, reservationStart, reservationMonth, hotelName, rm, id);
                service.add(reserv);
                System.out.printf("Reservation ID: %s created!\n", id);
                System.out.printf("\n");
                
            }
            else if (choice==2) {
            	Iterator<Services> svitr = service.iterator();
            	while(svitr.hasNext()) {
            		Services svr = svitr.next();
                    if (svr instanceof Reservation) {
                        Reservation reservation = (Reservation) svr;
                        reservation.displayInfo();
                    }
            	}
            }
            
            else if (choice==3) {

            	System.out.println("Type a city name for a reservation search: \n");
            	resCity=sc.next();
            	Iterator<Services> cLitr = service.iterator();
            	while (cLitr.hasNext()) {
            		Reservation reservation = (Reservation)cLitr.next();
            		if(reservation.getHotelName().contains(resCity)) { 
            			System.out.printf("%s \n", reservation.getHotelName());
            			
            		}
            	}
            	System.out.printf("\n");
            }
            else if (choice==4) {
                
            	//Add extra services to a reservation
            	System.out.println("Please select one of the extra services from below: \n");
            	System.out.println("1. Laundry Service");
            	System.out.println("2. Spa Service");
            	serviceChoice=sc.nextInt();
            	
            	if (serviceChoice==1) {
            		System.out.println("Type the reservation ID to credit this service: \n");
            		int CustomerID=sc.nextInt();
            		System.out.println("How many pieces of clothing?");
            		int clothingPieces=sc.nextInt();
            		Laundry lndry = new Laundry(CustomerID, clothingPieces);
            		service.add(lndry);
            		
            	}
            	else if (serviceChoice==2) {
            		System.out.println("Type the reservation ID to credit this service: \n");
            		int CustomerID=sc.nextInt();
            		System.out.println("How many days?");
            		int days=sc.nextInt();
            		Spa spa = new Spa(CustomerID, days);
            		service.add(spa);
            	}
            	
            }
            else if (choice==5) {
            	
            	//Calculate total cost for each service
            	Iterator<Services> ch5 = service.iterator();
            	while(ch5.hasNext()) {
            		Services svc = ch5.next();
            		if(svc instanceof Reservation) {
            			Reservation resev =(Reservation)svc;
            			int reservCost=resev.checker();
            			System.out.printf("The cost for the Room booking service of reservation ID %d: %d\n", resev.getCustomerID(), reservCost); 
            		}
            		else if (svc instanceof Spa) {
            			Spa spa =(Spa)svc;
            			double spaCost=spa.calculateService();
            			System.out.printf("The cost for the Room booking service of reservation ID %d: %f\n", spa.getCustomerID(), spaCost);
            		}
            		else  {
            			Laundry lndry = (Laundry)svc;
            			double laundryCost=lndry.calculateService();
            			System.out.printf("The cost for the Room booking service of reservation ID %d: %f\n", lndry.getCustomerID(), laundryCost);
            		}
            	}
            	System.out.println("\n");
            }
            else if (choice==6) {
            	
            	//Display the total cost of every customer
            	double total = 0;
            	for(int a=1;a<=id;a++) {
            		Iterator<Services> ch6 = service.iterator();
            		while(ch6.hasNext()) {
            			Services c6srv = ch6.next();
            				if(c6srv.getCustomerID()==a) {
            					if(c6srv instanceof Reservation) {
            						Reservation resev = (Reservation)c6srv;
            						total =  resev.checker();
            					}
            					else if(c6srv instanceof Spa) {
            						Spa spa = (Spa)c6srv;
                					total+=spa.calculateService();
            					}
            					else if(c6srv instanceof Laundry) {
            						Laundry lndry = (Laundry)c6srv;
                					total += lndry.calculateService();
            					}
            					System.out.printf("The total cost of all services of the reservation with ID: %d is %f\n", a, total);
            			}
            		}
            	}
            }
            else if (choice==7) {
            	System.out.println("Exiting, Goodbye!");
                break;
            }
            else {
                System.out.println("Wrong choice!");
            }
        
        
        }

        sc.close();
    }

}
