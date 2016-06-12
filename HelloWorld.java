public class HelloWorld{
    
    public static void main(String []args){

        System.out.println("Hello World");
        //restock();
        //await_commands();
        ATM atm = new ATM();
        atm.await_commands();
    }
     

}