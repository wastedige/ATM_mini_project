import java.util.Scanner;
import java.lang.*;

public class ATM {

    public ATM () {
        System.out.println("Initiating ATM");
        restock();
    }

    private int [] bill_value_per_slot = new int [6];
    private int [] bill_available_per_slot = new int [6];

    public void restock() {
        bill_value_per_slot = new int [] {1, 5, 10, 20, 50, 100};
        bill_available_per_slot = new int [] {10, 10, 10, 10, 10, 10};
    }

    public boolean withdraw(String string_amount) {
        int amount;
        try {
          amount = Integer.parseInt ( string_amount.replace("$", "") );
        } catch (Exception e) {
            System.out.println("Invalid withdraw parameter!");
            return false;
        }
        int [] bills_to_take = new int [6];
        for ( int i = 5; i > -1; i-- ) {
            // System.out.println("for: " + amount + ", " + bill_value_per_slot[i]);
            if ( amount < bill_value_per_slot[i] )
                continue;
            else if ( bill_available_per_slot[i] < 1 )
                continue;
            else {
                bills_to_take[i] = Math.min (( amount / bill_value_per_slot[i] ), bill_available_per_slot[i] );
                System.out.println("$" + bill_value_per_slot[i] + ": " + bills_to_take[i]);
                amount = amount - (bills_to_take[i] *  bill_value_per_slot[i] );
            }
        }
        if ( amount == 0 ) {
            for ( int i = 5; i > -1; i-- ) {
                bill_available_per_slot[i] = bill_available_per_slot[i] - bills_to_take[i];
            }
            System.out.println("Withdraw successful!");
            return true;
        }
        else
            System.out.println("Withdraw failed!");
            return false;
    }

    public int[] getAtmStatus() {
      return bill_available_per_slot;
    }

    public void printAtmStatus(){
      String output = "";
       for ( int i = 5; i > -1; i-- ) {
           output += "$" + bill_value_per_slot[i] + ": " + bill_available_per_slot[i] + "\n";
       }
       System.out.println (output);
    }

    public void await_commands() {
		Scanner scan = new Scanner(System.in);
		while (true) {
        System.out.println("Commands are: [R: Restock, W {$amount}: Withdraw, I: ATM status, Q: Quit]\nEnter command: ");
    		String action = scan.next();

    		switch (action.toLowerCase()) {
    		    case "w":
    		        String amount = scan.next();
    		        withdraw( amount );
    		        break;
    		    case "r":
    		        restock();
    		        break;
    		    case "i":
    		        printAtmStatus();
    		        break;
    		    case "q":
    		        System.exit(0);
    		        break;
    		    default:
    		        System.out.println("Invalid command!");

    		}
		}
    }
}
