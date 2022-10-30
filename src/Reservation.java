/////////////////////////////////////-------Using separate classes for each customer-----/////////////////////////////////

//import java.util.Scanner;

//class Ticket {
//    Scanner input = new Scanner(System.in);
//    int tickets;
//
//    public Ticket(int tickets) {
//        this.tickets = tickets;
//    }
//
//    public synchronized void reserve(String name) {
//        System.out.println("Total Tickets Remaining : " + this.tickets + "\n");
//
//        if (this.tickets > 0) {
//            System.out.print("Dear " + name + ", Please enter the amount of Tickets willing to purchase : ");
//            int count = input.nextInt();
//
//            if (count == 0) {
//                System.out.println("No Tickets was reserved by "+name+"\n\n");
//                return;
//            }
//
//            if (count <= this.tickets) {
//                System.out.println(count + " Ticket/s was Successfully Reserved by " + name + "\n\n");
//                this.tickets -= count;
//
//                if (this.tickets == 0) {
//                    System.out.println("All Tickets Sold Out....!!!");
//                }
//            } else {
//                System.out.print("You have only " + this.tickets + " Ticket/s available...\n\nWould you like to purchase the remaining (y/n) : ");
//                String option = input.next();
//                switch (option) {
//                    case "y":
//                        System.out.println(this.tickets + " Ticket/s was Successfully Reserved by " + name + "\n\n");
//                        this.tickets=0;
//                        System.out.println("All Tickets Sold Out....!!!");
//                        break;
//
//                    case "n":
//                        System.out.println("\nTotal Tickets Remaining : " + this.tickets + "\n");
//                        break;
//
//                    default:
//
//                }
//            }
//        }
//    }
//}
//
//class Customer1 implements Runnable {
//    Ticket t;
//
//    public Customer1(Ticket t) {
//        this.t = t;
//    }
//
//    @Override
//    public void run() {
//        t.reserve("Customer 1");
//    }
//}
//
//class Customer2 implements Runnable {
//    Ticket t;
//
//    public Customer2(Ticket t) {
//        this.t = t;
//    }
//
//    @Override
//    public void run() {
//        t.reserve("Customer 2");
//    }
//}
//
//public class Reservation {
//    public static void main(String[] args) {
//        System.out.println("\n################################################################################");
//        System.out.println("###################### Movie Ticket Reservation System #########################");
//        System.out.println("################################################################################\n");
//
//        Ticket ticket = new Ticket(3);
//        Customer1 c1 = new Customer1(ticket);
//        Customer2 c2 = new Customer2(ticket);
//        new Thread(c1).start();
//        new Thread(c2).start();
//    }
//}

/////////////////////////////////////------Method 2-------/////////////////////////////////

import java.util.Scanner;

class Ticket implements Runnable{
    Scanner input = new Scanner(System.in);
    int tickets;

    public Ticket(int tickets) {
        this.tickets = tickets;
    }

    @Override
    public synchronized void run() {
        Thread t = Thread.currentThread();
        String name = t.getName();

        System.out.println("Total Tickets Remaining : " + this.tickets + "\n");

        if (this.tickets > 0) {
            System.out.print("Dear " + name + ", Please enter the amount of Tickets willing to purchase : ");
            int count = input.nextInt();

            if (count == 0) {
                System.out.println("No Tickets was reserved by "+name+"\n\n");
                return;
            }

            if (count <= this.tickets) {
                System.out.println(count + " Ticket/s was Successfully Reserved by " + name + "\n\n");
                this.tickets -= count;

                if (this.tickets == 0) {
                    System.out.println("All Tickets Sold Out....!!!");
                }
            } else {
                System.out.print("You have only " + this.tickets + " Ticket/s available...\n\nWould you like to purchase the remaining (y/n) : ");
                String option = input.next();
                switch (option) {
                    case "y":
                        System.out.println(this.tickets + " Ticket/s was Successfully Reserved by " + name + "\n\n");
                        this.tickets=0;
                        System.out.println("All Tickets Sold Out....!!!");
                        break;

                    case "n":
                        System.out.println("\nTotal Tickets Remaining : " + this.tickets + "\n");
                        break;

                    default:

                }
            }
        }
    }
}

public class Reservation {
    public static void main(String[] args) {
        System.out.println("\n################################################################################");
        System.out.println("###################### Movie Ticket Reservation System #########################");
        System.out.println("################################################################################\n");

        Ticket ticket = new Ticket(3);
        Thread t1 = new Thread(ticket);
        Thread t2 = new Thread(ticket);
        t1.setName("Customer 1");
        t2.setName("Customer 2");
        t1.start();
        t2.start();

    }
}

