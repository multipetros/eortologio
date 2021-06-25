import javax.microedition.lcdui.* ;
import java.util.Calendar ;
import java.util.Date ;

/**
 * Τάξη εμφάνισης αργιών
 * @author Πέτρος Κυλαδίτης
 * @verion 1.4
 */
public class ArgiesForm extends Form{
    
    private static String[] WEEKDAYS = {"Κυριακή", "Δευτέρα", "Τρίτη", "Τετάρτη", "Πέμπτη", "Παρασκευή", "Σάββατο"} ;
    private static String DATE_SEPARATOR = "/" ;              //Διαχωριστικό της ημ/νίας
    private static String WEEKDAY_SEPARATOR = ", " ;       //Διαχωριστικό της μέρας της βδομάδας
    private static int DAY_MILLISECONDS = 86400000 ;  //Τα χιλιοστά του δευτ. μιας μέρας: 24x60x60x1000
            
    private Calendar calendar = Calendar.getInstance() ;
    private Calendar katharaDeutera = Calendar.getInstance() ;
    private Calendar agiouPneumatos = Calendar.getInstance() ;
    private OrthodoxEaster easter ;
    
    /**
     * Προεπιλεγμένη μέθοδος κατασκευής. Ορίζει αυτόματα σαν έτος το τρέχον.
     */
    public ArgiesForm(){
        this(Calendar.getInstance().get(Calendar.YEAR)) ;
    }
    
    /**
     * Μέθοδος κατασκευής με οριζόμενο το έτος.
     * @param year Το έτος για το οποίο θα εμφανιστούν οι αργίες
     * @throws NumberFormatException Αν δοθεί μηδενική ή αρνητική τιμή
     */
    public ArgiesForm(int year) throws NumberFormatException{
        super("Αργίες " + Integer.toString(year)) ;
        
        //Εάν δωθεί ανρητικό τιμή ή 0 εμφανίζεται η εξαίρεση
        if (year <= 0) throw new NumberFormatException("Δεν ήταν δυνατό να γίνει ο υπολογισμός.\n\nΣτο πλαίσιο κειμένου επιτρέπονται μονάχα θετικές αριθμητικές τιμές.") ;
        
        //Αρχικοποίηση της ημ/νίας του Πάσχα και των άλλων κινητών εορτών βάση αυτού
        easter = new OrthodoxEaster(year) ;
        long easterDays = easter.getCalendar().getTime().getTime() / DAY_MILLISECONDS ;
        long easterDaysDiv = easter.getCalendar().getTime().getTime() % DAY_MILLISECONDS ;  //Τα χιλ.δευτ που περισεύουν και θα προστεθούν στον τελικό υπολογισμό

        katharaDeutera .setTime(new Date(((easterDays  - 48) * DAY_MILLISECONDS + easterDaysDiv))) ;
        agiouPneumatos.setTime(new Date(((easterDays  + 50) * DAY_MILLISECONDS + easterDaysDiv))) ;

        //Δημιουργία της συμβολοσειράς της φόρμας
        StringBuffer outputBuffer = new StringBuffer() ;
//        if(year % 4 == 0) outputBuffer.append("(Δίσεκτο έτος.\nΟ Φεβρουάριος έχει 29 ημέρες.)\n\n\n") ;
        outputBuffer.append("Πρωτοχρονιά:\n").append(getDate(1, 1, year)) ;
        outputBuffer.append("\n\nΘεοφάνεια:\n").append(getDate(6, 1, year)) ;
        outputBuffer.append("\n\nΚαθαρά Δευτέρα:\n").append(getDate(katharaDeutera)) ;
        outputBuffer.append("\n\n25η Μαρτίου:\n").append(getDate(25, 3, year)) ;
        outputBuffer.append("\n\nΠάσχα:\n").append(getDate(easter.getCalendar())) ;
        outputBuffer.append("\n\nΠρωτομαγιά:\n").append(getDate(1, 5, year)) ;
        outputBuffer.append("\n\nΑγίου Πνεύματος:\n").append(getDate(agiouPneumatos)) ;        
        outputBuffer.append("\n\nΔεκαπενταύγουστος:\n").append(getDate(15, 8, year)) ;
        outputBuffer.append("\n\n28η Οκτωβρίου:\n").append(getDate(28, 10, year)) ;
        outputBuffer.append("\n\n17η Νοεμβρίου:\n").append(getDate(17, 11, year)) ;
        outputBuffer.append("\n\nΧριστούγεννα:\n").append(getDate(25, 12, year)) ;        
        
        //Προσθήκη των αργιών στη φόρμα
        this.append(new String(outputBuffer)) ;
    }
    
    /**
     * Εμφανίζει την περιγραφική ημ/νία για τα ορίσματα
     * @param day Η μέρα του μήνα
     * @param month Ο μήνας
     * @param year Το έτος
     * @return Μια συμβολοσειρά που περιγράφει αναλυτικά την δοσμένη ημ/νία, μαζί με τη μέρα της βδομάδας
     */
    private String getDate(int day, int month, int year){        
        calendar.set(Calendar.YEAR, year) ;
        calendar.set(Calendar.MONTH, month - 1) ;   //Η αρίθμιση των μηνών στην τάξη ξεκινά απ' το μηδέν
        calendar.set(Calendar.DAY_OF_MONTH, day) ;
        StringBuffer returnBuffer = new StringBuffer(WEEKDAYS[calendar.get(Calendar.DAY_OF_WEEK) - 1]).append(WEEKDAY_SEPARATOR).append(day).append(DATE_SEPARATOR).append(month).append(DATE_SEPARATOR).append(year) ;
        return new String(returnBuffer)  ;
    }

     /**
     * Εμφανίζει την περιγραφική ημ/νία για τα ορίσματα
     * @param calendar Αντικείμενο της τάξης Calendar που περιγράφει την ημ/νία
     * @return Μια συμβολοσειρά που περιγράφει αναλυτικά την δοσμένη ημ/νία, μαζί με τη μέρα της βδομάδας
     * @since 1.1
     */
    private String getDate(Calendar calendar){
        return this.getDate(calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR)) ;
    }
}
