import javax.microedition.lcdui.* ;
import java.util.Calendar ;

/**
 * Τάξη εμφάνισης της ημ/νίας του Πάσχα
 * @author Πέτρος Κυλαδίτης
 * @version 1.1
 */
public class EasterForm extends Form {
    
    private OrthodoxEaster pasxa ;
    private StringBuffer output ;
    
    /**
     * Δημιουργεί μια φόρμα με την ημ/νία του Πάσχα, για το δωθέν έτος.
     * @param year Το έτος για το οποίο θα υπολογιστεί το Πάσχα
     * @throws NumberFormatException Εάν έχει δωθεί αρνητική ή μηδενική τιμή
     */
    public EasterForm(int year) throws NumberFormatException {                
        super("Αποτέλεσμα") ;

        pasxa = new OrthodoxEaster() ;
        int day ;
        String month ;
        
        if (year <= 0) throw new NumberFormatException("Δεν ήταν δυνατό να γίνει ο υπολογισμός.\n\nΣτο πλαίσιο κειμένου επιτρέπονται μονάχα θετικές αριθμητικές τιμές.") ;
        
        pasxa.setYear(year) ;
        if (pasxa.getMonth() == 4) month = new String("Απριλίου") ;
        else month = new String("Μαϊου") ;
        day = pasxa.getDay() ;
        output = new StringBuffer("Το Πάσχα το έτος, ").append(year).append(" πέφτει στις ").append(day).append(" ").append(month) ;

         this.append(new String(output)) ;
    }
    
    /**
     * Δημιουργεί μια φόρμα με την ημ/νία του Πάσχα, για το τρέχον έτος.
     */
    public EasterForm(){
        this(Calendar.getInstance().get(Calendar.YEAR)) ;
    }
    
}
