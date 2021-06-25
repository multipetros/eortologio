import java.util.Calendar ;

/**
 * Τάξη που κάνει υπολογισμούς της ημ/νίας του πότε
 * 'πέφτει' το Πάσχα για ένα συγκεκριμένο έτος.
 * (προσαρμογή ήδη υπάρχουσας τάξης, στην J2ME)
 * @author Πέτρος Κυλαδίτης
 * @version 2.6 Mobile
 * @serial 20050217
 */
public class OrthodoxEaster {
	private int year ;
	private int month ;
	private int day ;
	
        /**
         * Ο αλγόριθμος υπολογισμού της ημ/νίας που πέφτει το Πάσχα
         * @since 1.0
         */
	private void algorithm(){
		
		int var = (((19*(year%19))+16)%30)+(((2*(year%4))+4*(year%7)+6*((19*(year%19)+16)%30))%7)+3 ;
	
		if ( var<=30 ) {
			day = var ;
			month = 4 ;
		}  
		else {
			day = var - 30 ;
			month = 5 ;
		}
	}
	
	/**
	 *Προεπιλεγμλενη μέθοδος κατασκευής του αντικειμένου, για το τρέχον έτος.<br>
	 *<em>Ο υπολογισμός εκτελείται αυτόματα με την κλήση αυτής της μεθόδου, δεν χρειάζονται περαιτέρω ενέργειες</em>
         *@since 1.2
	 */	
	public OrthodoxEaster() {
                Calendar today = Calendar.getInstance();    //To java.util.Calendar αντικατέστησε το java.util.Date από την έκδοση 2.0.0
                this.year = today.get(Calendar.YEAR)  ;
		algorithm();
	}

	/**
	 *Μέθοδος κατασκευής του αντικειμένου με καθορισμό του έτους, για το οποίο θα γίνει υπολογισμός.<br>
	 *<em>Ο υπολογισμός εκτελείται αυτόματα με την κλήση αυτής της μεθόδου, δεν χρειάζονται περαιτέρω ενέργειες</em>
	 *@param year Το έτος για το οποίο θα γίνει ο υπολογισμός
         *@since 1.0.0
	 */
	public OrthodoxEaster(int year) {
		this.year = year ;
		algorithm();
	}
	
	/**
	 *Μέθοδος καθορισμού του έτους, για το οποίο θα γίνει ο υπολογισμός του πότε 'πέφτει' το Πάσχα.<br>
	 *<em>Ο υπολογισμός εκτελείται αυτόματα με την κλήση αυτής της μεθόδου, δεν χρειάζονται περαιτέρω ενέργειες</em>
	 *@param year Το έτος για το οποίο θα γίνει ο υπολογισμός
         *@since 1.1
	 */
	public void setYear(int year){
		this.year = year ;
		algorithm();
	}
	
	/**
	 *@return Το έτος που έχει δοθεί, για να υπολογιστεί το Πάσχα.
         *@since 1.1
	 */
	public int getYear(){ 
		return year ; 
	}
	
	/**
	 *@return Ο μήνας που 'πέφτει' το Πάσχα, για το έτος που έχει δοθεί.
         *@since 1.0.0
	 */
	public int getMonth(){ 
		return month ; 
	}
	
	/**
	 *@return Η μέρα που 'πέφτει' το Πάσχα, για το έτος που έχει δοθεί.
         *@since 1.0
	 */
	public int getDay(){ 
		return day ; 
	}
	
        /**
         *@return Αντικείμενο της τάξης Calendar με πληροφορίες για το Πάσχα.
         *@since 2.5
         */
	public Calendar getCalendar(){
            Calendar calendar = Calendar.getInstance() ;
            calendar.set(Calendar.YEAR, this.year) ;
            calendar.set(Calendar.MONTH, this.month - 1) ;  //Η αρίθμιση των μηνών ξεκινά από το 0 στην τάξη java.util.Calendar
            calendar.set(Calendar.DAY_OF_MONTH, this.day) ;
            return calendar ;
        }
	
}
