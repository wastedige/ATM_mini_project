import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class TestJunit {

   ATM atm = new ATM();
	 int [] desired_atm_status;

   @Test
   public void test1_initStock() {
			desired_atm_status = new int [] {10, 10, 10, 10, 10, 10};
			for (int i = 0; i < 6; i++) {
				assertEquals( atm.getAtmStatus()[i] , desired_atm_status[i] );
			}
   }

	 @Test
	 public void test2_Withdraw1() {
		 atm.withdraw("$1"); // with $ sign
		 desired_atm_status = new int [] {9, 10, 10, 10, 10, 10};
		 for (int i = 0; i < 6; i++) {
			 assertEquals( atm.getAtmStatus()[i] , desired_atm_status[i] );
		 }
	 }

 	 @Test
	 public void test3_Withdraw2() {
		 atm.withdraw("19"); // without $ sign
		 desired_atm_status = new int [] {6, 9, 9, 10, 10, 10};
		 for (int i = 0; i < 6; i++) {
			 assertEquals( atm.getAtmStatus()[i] , desired_atm_status[i] );
		 }
	 }

	 @Test
 public void test4_MultipleWithdraws() {
	 atm.withdraw("1000");
	 atm.withdraw("100");
	 atm.withdraw("20");
	 desired_atm_status = new int [] {10, 10, 10, 9, 8, 0};
	 for (int i = 0; i < 6; i++) {
		 assertEquals( atm.getAtmStatus()[i] , desired_atm_status[i] );
	 }
 }

	 @Test
	 public void test5_Restock() {
		 atm.withdraw("$19");
		 atm.restock();
		 desired_atm_status = new int [] {10, 10, 10, 10, 10, 10};
		 for (int i = 0; i < 6; i++) {
			 assertEquals( atm.getAtmStatus()[i] , desired_atm_status[i] );
		 }
	 }

	 @Test
	 public void test6_OverWithdraw() {
		 assertEquals( atm.withdraw("$2000"), false );
	 }

	 @Test
	 public void test7_InvalidWithdraw1() {
		 assertEquals( atm.withdraw("abcd"), false );
	 }

	 @Test
	 public void test8_InvalidWithdraw2() {
		 assertEquals( atm.withdraw("-100"), false );
	 }

}
