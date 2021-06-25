import javax.microedition.lcdui.* ;
import java.util.Calendar ;
import java.util.Date ;

/**
 * Τάξη εμφάνισης σημερινών και προσκείμενων εορτών
 * @author Πέτρος Κυλαδίτης
 * @version 2.0 (προσθήκη μεθόδου αναζήτησης ονόματος)
 */
public class CelebrationsForm extends Form{
    
    private OnomastikesEortes eortes ;   // Περιέχει τις ονομαστικές εορτές
    private Calendar todayCalendar ;       // Το τρέχον ημερολογιακό έτος

    private static String NOBODY = "# κανένας #" ;             //Συμβολοσειρά αντικατάστασης των κενών συμβολοσειρών του πίνακα
    private static String SELEBRATE = "Γιορτάζουν" ;
    private static String NORESULT = "Δεν βρέθηκε αποτέλεσμα   :-(\n\nΔοκιμάστε ξανά με διαφορετικό ερώτημα." ;
    private static int DAY_MILLISECONDS = 86400000 ;    //Τα χιλιοστά του δευτ. μιας μέρας: 24x60x60x1000    
    private static String[] WEEKDAYS = {"Κυριακή", "Δευτέρα", "Τρίτη", "Τετάρτη", "Πέμπτη", "Παρασκευή", "Σάββατο"} ;    
    
    /**
     * Προεπιλεγμένη μέθοδος κατασκευής<br>
     * Δημιουργεί μια φόρμα με εορτές της τρέχουσας ημ/νίας, μιας μέρα μετά και δύο μερών μετά.
     * @version 2.0 (Διόρθωση σημαντικού σφάλματος που δημιουργούνταν στο τέλος του μήνα
     *                          και συμόρφωση με τις νέες μεθόδους της τάξης OnomastikesEortes)
     * @since 1.0
     */
    public CelebrationsForm(){
        //Αρχικοποίηση υπερκλάσης
        super(SELEBRATE) ;
        
         //Αρχικοποίηση των βοηθητικών μεταβλιτών και αντικειμένων
         eortes = new OnomastikesEortes() ;
         todayCalendar = Calendar.getInstance() ;

         //Κατασκευή του κειμένου. Αν βρεθούν κενές συμ/ρές θα αντικαθιστούνται με τη σταθερά
         StringBuffer celebrateMsg = new StringBuffer() ;
         String celebration ;
         String[] descript = {"Σήμερα:\n", "\n\nΑύριο:\n", "\n\nΜεθαύριο:\n", "\n\nΣε τρεις μέρες:\n", "\n\nΣε τέσσερεις μέρες:\n", "\n\nΣε πέντε μέρες:\n", "\n\nΣε έξι μέρες:\n", "\n\nΣε μια εβδομάδα:\n"} ;
         for(int i=0; i<8; i++){
            celebration = eortes.getEorti(todayCalendar.getTime().getTime() + (DAY_MILLISECONDS * i)) ;
            if(celebration.length()==0)    celebration = NOBODY ;
            celebrateMsg.append(descript[i]).append(celebration) ;
         }

         //Τοποθέτηση του κειμένου, καλώντας την κληρονομημένη μέθοδο
         this.append(new String(celebrateMsg)) ;
    } 
    
    /**
     * Μέθοδος κατασκευής με επιλογή της ημ/νίας εμφάνισης της εορτής
     * @param calendar Η ημ/νία για την οποία θα εμφανιστεί ο εορτή
     * @since 1.1
     * @version 2.0 (συμόρφωση με τις νέες μεθόδους της OnomastikesEortes)
     */
    public CelebrationsForm(Calendar calendar){
        super(SELEBRATE) ;

        //Αρχικοποίηση των μεταβλητών και των αντικειμένων        
        eortes = new OnomastikesEortes(calendar.get(Calendar.YEAR)) ;  // Αρχικοποίηση του αντι. για το έτος που δώθηκε
        String celebration = eortes.getEorti(calendar) ;        
        if (celebration.length() == 0) celebration = this.NOBODY ;
        StringBuffer celebrateMsg = new StringBuffer(WEEKDAYS[calendar.get(Calendar.DAY_OF_WEEK) - 1]).append(" ").append(calendar.get(Calendar.DAY_OF_MONTH)).append("/").append(calendar.get(Calendar.MONTH) + 1).append("/").append(calendar.get(Calendar.YEAR)).append(":\n").append(celebration) ;
        
        //Τοποθέτηση του κειμένου, καλώντας την κληρονομημένη μέθοδο
        this.append(new String(celebrateMsg)) ;
    }

    /**
     * Μέθοδος κατασκευής με αναζήτηση ονόματος
     * @param name Η συμβολοσειρά για την οποία θα γίνει αναζήτηση
     * @since 2.0
     */
    public CelebrationsForm(String name){
       
        //Αρχικοποίηση μεταβλητών και αντικειμένων
        super(name + " ...") ;
        eortes = new OnomastikesEortes() ;
        String celebrateMsg = eortes.getEorti(name) ;
        if(celebrateMsg.length() == 0) celebrateMsg = this.NORESULT ;
        
        //Τοποθέτηση του κειμένου, καλώντας την κληρονομημένη μέθοδο
        this.append(celebrateMsg) ;
    }
}
