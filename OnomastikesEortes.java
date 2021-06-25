import java.util.Calendar ;
import java.util.Date ;

/**
 * Τάξη εμφάνισης ονομαστικών εορτών
 * @author Πέτρος Κυλαδίτης
 * @version 3.1
 * @serial 20050510
 */
public class OnomastikesEortes{

    // Δισδιάστατος πίνακας 12x31 για την αποθήκευση των συμβολοσειρών των σταθερών εορτών
    private String[][] eortes = { {"Βασίλης\nΒασιλική", "", "", "Ονούφριος", "", "Θεοφάνης\nΙορδάνης\nΟυρανία\nΦώτης", "Ιωάννα\nΙωάννης\nΠρόδρομος", "", "", "", "Θεοδόσης", "Τατιάνα", "Ευστράτιος", "", "", "", "Αντώνης\nΑντωνία", "Θανάσης\nΑθανασία", "Ευγενικός", "Ευθύμης\nΕυθυμία", "Αγνή\nΜάξιμος\nΝεόφυτος", "Τιμόθεος", "Αγαθάγγελος", "Ξένη", "Γρηγόριος", "Ξενοφώντας", "", "", "", "", "Ευδοξία"},
                              {"Περπέτουα\nΤρύφωνας", "Υπαπαντή", "Σταμάτης\nΣταματία", "", "Αγαθή", "", "Παρθένιος", "", "", "Χαράλαμπος", "Βλάσης", "Μελέτιος", "", "Βαλεντίνη\nΒαλεντίνος", "", "", "Μαριάννα", "", "Φιλοθέη", "", "", "", "Πολύκαρπος", "", "", "", "", "", "", "", ""},
                              {"Ευδοκία", "Ευθαλία", "", "", "", "", "", "Ερμής", "", "", "", "", "", "", "", "", "Αλέκος\nΑλέξης\nΑλεξία", "", "", "", "", "", "", "", "Βαγγέλης\nΕυαγγελία", "", "", "", "", "", ""},
                              {"", "", "", "", "", "", "", "", "", "Δημοσθένης\nΙσοκράτης\nΕπαμεινώνδας\nΗρακλής\nΠερικλής", "", "Αχιλλέας", "", "", "Λεωνίδας", "", "Μακάριος\nΑγαπητός", "", "", "", "", "Ναθαναήλ", "", "Ελισάβετ", "Μάρκος", "", "", "", "Ιάσων", "Ιάκωβος\nΙακωβίνα", ""},
                              {"", "Ραφαήλ", "", "Πελαγία", "Ειρήνη\nΓαλήνη\nΠηγή", "", "", "Αρσένιος\nΘεολόγος", "Χριστόφορος", "Σίμος", "Αρμόδιος", "Αχιλλέας", "Αλίκη\nΓλυκερία", "Ισίδωρος\nΙσιδώρα", "Ιερώνυμος", "", "Φωτεινή\nΑνδρονίκη\nΑνδρόνικος\nΙουνία", "Ιουλία", "", "Λυδία", "Έλενα\nΕλένη\nΚωνσταντίνα\nΚωνσταντίνος", "", "", "", "", "", "", "", "", "", ""},
                              {"", "Νικηφόρος", "", "Μάρθα", "Δωροθέα\nΔωρόθεος\nΑπόλλων", "", "", "Καλλιόπη", "", "", "Βαρθολομαίος", "", "", "Ελισσαίος", "Αυγουστίνος", "", "", "", "", "", "", "Ευσέβιος", "", "Χριστίνα", "Έρωτας\nΦεβρωνία", "", "", "", "Πέτρος\nΠαύλος", "Αποστόλης", ""},
                              {"Ανάργυρος\nΑργυρώ\nΚοσμάς\nΔαμιανός", "", "", "", "", "", "Κυριακή", "Θεόφιλος\nΠροκόπης", "", "", "Όλγα\nΕυφημία", "Βερονίκη", "", "Νικόδημος\nΑκύλας", "Ιουλίττα\nΚήρυκας", "", "Μαρίνα\nΜαρίνος", "Αιμιλία\nΑιμιλιανός", "", "Ηλίας", "Βάσω", "Μαρκέλλα\nΜαγδαληνή", "", "", "Ολυμπία", "Παρασκευή\nΠαρασκευάς", "Παντελής", "", "Καλλίνικος", "", ""},
                              {"", "", "", "", "", "Σωτήρης\nΣωτηρία", "Αστέρω", "Τριαντάφυλλος", "", "", "", "", "", "", "Μαρία\nΜάριος\nΠαναγιώτα\nΠαναγιώτης\nΔέσποινα", "Σταμάτης\nΣταματία\nΔιομήδης\nΑλκιβιάδης\nΣαράντης", "", "Λαυρέντιος", "", "", "", "", "", "", "Ευτύχης\nΕυτυχία\nΤίτος", "Ανδριανή\nΑνδριανός\nΝαταλία", "Φανουριος", "", "", "Αλέξανδρος", ""},
                              {"Αθηνα\nΑσπασία\nΑντιγόνη\nΚλειώ\nΘεανώ\nΚλεοπάτρα\nΠηνελόπη\nΜαργαρίτα\nΣυμεών", "Μάμμας", "Φοίβη\nΠολύδωρας", "Ερμιόνη\nΧαριτίνη\nΆνθιμος", "Ζαχαρίας", "", "", "", "Ιωακείμ", "Πουλχερία", "Ευανθία", "Ωκεανός", "Αριστείδης\nΚορνήλιος\nΑυτόνομος", "Σταύρος", "Νικήτας", "", "Ελπίδα\nΣοφία\nΑγάπη", "Αριαδνη", "", "Στάθης\nΘεοπίστη", "", "Φωκάς", "Πολυξένη", "Θέκλα", "Ευφροσύνη", "", "", "", "Ξανθίππη\nΚυριάκος", "", ""},
                              {"Θηρεσία", "", "Διονύσης\nΔιονυσία", "", "Χαριτίνη", "", "Βάκχος\nΣέργιος", "Πελαγία", "", "Ευλαμπία\nΕυλάμπιος", "", "Συμεών", "", "", "", "", "", "Λουκάς", "", "Άρτεμις\nΑρτέμιος\nΓεράσιμος", "Σωκράτης\nΧριστόδουλος", "", "", "", "", "Δήμητρα\nΔημήτρης", "Νέστορας", "Σίμωνας", "", "Ζηνοβία", ""},
                              {"Αναργυρος\nΑργυρώ", "Ελπιδοφόρος", "", "", "", "Λεονάρδος", "", "Αγγέλα\nΆγγελος\nΜιχάλης", "Νεκτάριος\nΝεκταρία\nΠορφύριος", "Ορέστης", "Βίκτορας\nΒικέντιος\nΜηνάς", "", "", "Φίλιππος", "", "Ματθαίος", "", "Πλάτων\nΡωμανός", "", "", "", "Φιλήμων", "", "", "Κατερίνα\nΜερκούριος", "Στέλιος\nΣτέλλα", "", "", "", "Ανδρέας", ""},
                              {"", "Μυρώπη", "Αγάπιος", "Βαρβάρα", "Διογένης\nΣάββας", "Νίκος\nΝικολέτα", "Αμβρόσιος", "", "Άννα", "", "", "Σπύρος", "Στράτος\nΛουκία", "", "Ελευθερία\nΕλευθέριος\nΑνθή", "", "Δανιήλ", "Σεβαστιανός\nΜόδεστος", "Αγλαΐα\nΆρης", "Ιγνάτιος", "Θεμιστοκλής", "", "", "Ευγενία", "Εμμανουέλα\nΜανώλης\nΧρήστος\nΧρύσα", "", "Στέφανος\nΣτέφανη", "Δόμνα", "", "", ""} } ;
    
    /* Οι δύο επόμενοι πίνακες αναπαριστούν τις συμβολοσειρές των κινητών εορτών και τις
     ημέρες διαφέρουν σε σχέση με το Πάσχα. Το μήκος των πινάκων πρέπει να είναι το ίδιο */
    private String[] kinitesEortesOnomata = {"Θεοδώρα\nΘεόδωρος", "Λάζαρος", "Βάϊος", "Αναστάσης\nΑναστασία\nΛάμπρος\nΛαμπρινή", "Γεώργιος\nΓεωργία","Ζωή\nΠηγή", "Θωμάς"} ;
    private int [] kinitesEortesHmeres = {-43, -8, -7, 0 , 1, 5, 7} ;
   
    private Calendar pasxa ;
    
    private static int DAY_MILLISECONDS =  86400000 ; //Τα χιλιοστά του δευτ. μιας μέρας: 24x60x60x1000
    private static String NEW_LINE = new String("\n") ;
    private static String DATE_SEPARATOR  = new String(" \\ ") ;
    private static String NAME_SEPARATOR = new String ("  :  ");
    
    /**
     * Μέθοδος κατακσευής του εορτολογίου με επιλογή του έτους
     * @param year Το έτος για το οποίο θα υπολογιστούν οι κινητές εορτές
     */
    public OnomastikesEortes(int year){
        pasxa = new OrthodoxEaster(year).getCalendar() ;
        initializingKinitesEortes() ;
    }
    
    /**
     * Προεπιλεγμένη μέθοδος κατασκευής του εορτολογίου για το τρέχον έτος
     */
    public OnomastikesEortes(){
        pasxa = new OrthodoxEaster().getCalendar() ;
        initializingKinitesEortes() ;
    }
    
    /**
     *  Ξεκινάει τη διαδικασία υπολογισμού των κινητών εορτών και τις τοποθετεί στον πίνακα 
     * @version 2.1 (διορθώσεις στον υπολογισμό των εορτών)
     */
    private void initializingKinitesEortes(){
        long pasxaDays = pasxa.getTime().getTime() / DAY_MILLISECONDS ;
        long easterDaysDiv = pasxa.getTime().getTime() % DAY_MILLISECONDS ; //Τα χιλ.δευτ που περισεύουν και θα προστεθούν στον τελικό υπολογισμό
        for(int i=0; i<kinitesEortesOnomata.length; i++){
            pasxa.setTime(new Date((pasxaDays + kinitesEortesHmeres[i]) * DAY_MILLISECONDS + easterDaysDiv)) ;
            if(eortes[pasxa.get(Calendar.MONTH)][pasxa.get(Calendar.DAY_OF_MONTH) - 1].length()==0)
                eortes[pasxa.get(Calendar.MONTH)][pasxa.get(Calendar.DAY_OF_MONTH) - 1] += kinitesEortesOnomata[i] ;
            else
                eortes[pasxa.get(Calendar.MONTH)][pasxa.get(Calendar.DAY_OF_MONTH) - 1] += NEW_LINE + kinitesEortesOnomata[i] ;
            pasxa.setTime(new Date((pasxaDays - kinitesEortesHmeres[i]) * DAY_MILLISECONDS - easterDaysDiv)) ; //Επιστροφή στην ημ/νία του Πάσχα
        }
    }
    
    /**
     * @param day Η ημέρα του μήνα
     * @param month Ο μήνας του χρόνου
     * @return Συμβολοσειρά που περιέχει τα ονόματα των εορταζόντων
     * @deprecated Από την έκδοση 3.0, για λόγους ασφαλείας και τάξης η μέθοδος αυτή τείνει να γίνει private
     * @see #getEorti(Calendar) Χρήση αντικειμένου java.util.Calendar
     * @see #getEorti(long) Χρήση 1000στών του δευτ. από την epoch
     */
    public String getEorti(int day, int month){
        if(month<1 || month>12 || day<1 || day>31)
            return null ;
        return eortes[month-1][day-1] ;         //-1 διότι οι πίνακες ξεκινάνε την αρίθμιση από το 0
    }
    
    /**
     * @param calendar Αντικείμενο java.util.Calendar που περιγράφει την ημ/νία
     * @return Συμβολοσειρά που περιέχει τα ονόματα των εορταζόντων
     * @sine 3.0
     */
    public String getEorti(Calendar calendar){
        return getEorti(calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH) + 1) ;  //Διότι η αρίθμιση των μηνών στην τάξη Calendar ξεκινάει από το 0
    }

    /**
     * @param dateMilliseconds Περιγραφή της ημ/νίας σε 1000στά που δευτ. από την epoch
     * @return Συμβολοσειρά που περιέχει τα ονόματα των εορταζόντων
     * @sine 3.0
     */
    public String getEorti(long dateMilliseconds){
        Calendar calendar = Calendar.getInstance() ;
        calendar.setTime(new Date(dateMilliseconds)) ;
        return getEorti(calendar) ;
    }
    
    
    /**
     * @return Το έτος για το οποίο έχει γίνει ο υπολογισμός των κινητών εορτών
     */
    public int getYear(){
        return pasxa.get(Calendar.YEAR) ;
    }
    
    
    /**
     * @param name Το ερώτημα της αναζήτησης.
     * @return Συμβολοσειρά με τα ονόματα και τις εορτές, για τα οποία έγινε αναζήτηση.
     * @since 3.0
     */
    public String getEorti(String name){
        
   	// Δμιουργία ενός νέου πίνακα με όλα καφαλαία για 'ελαστικές' συγκρίσεις
   	String[][] eortesTemp = new String[12][31] ;
   	for(int i=0; i<12; i++){
   		for(int j=0; j<31; j++){
   			eortesTemp[i][j] = stringNormalize(eortes[i][j]) ;
   		}
   	}
   	
        StringBuffer buffer = new StringBuffer() ;
        name = stringNormalize(name) ;

   	// Αναζήτηση ονόματος σε όλο τον πίνακα
   	for (int i=0; i<12; i++){
            for (int j=0; j<31; j++){
                int lastPosition = -1 ;
   		int nPosition = eortesTemp[i][j].indexOf('\n', lastPosition + 1) ;
                //Αναζήτηση εντός του κάθε κελιού του πίνακα
   		while(nPosition != -1){
                    if(eortesTemp[i][j].startsWith(name, lastPosition + 1))
                    buffer.append(eortes[i][j].substring(lastPosition + 1, nPosition)).append(NAME_SEPARATOR).append(j + 1).append(DATE_SEPARATOR).append(i + 1).append(NEW_LINE) ;
                    lastPosition = nPosition ;
                    nPosition = eortesTemp[i][j].indexOf('\n', lastPosition + 1) ;
   		}
                if(eortesTemp[i][j].startsWith(name, lastPosition + 1))
                    buffer.append(eortes[i][j].substring(lastPosition + 1)).append(NAME_SEPARATOR).append(j + 1).append(DATE_SEPARATOR).append(i + 1).append(NEW_LINE) ;
            }
   	} 
        
        //Επιστροφή της συμβολοσειράς
        return new String(buffer) ;
    }
    
    
    /**
     * Μετατροπή της συμβολοσειράς σε κεφαλαία. Kαι από το GSM αλφάβητο σε Unicode
     * (το MIDP δεν υποστιρίζει τη μέθοδο toUperCase για no Latine χαρακτήρες)
     * @param unNormalizedString Η συμβολοσειρά προς μετατροπή.
     * @return Η συβολοσειρά σε Unicode κεφαλαία ελληνικά.
     * @since 3.0
     */
    private String stringNormalize(String unNormalizedString){
    	//return unNormalizedString.toUpperCase().replace('Ά', 'Α').replace('Έ', 'Ε').replace('Ή', 'Η').replace('Ί', 'Ι').replace('Ό', 'Ο').replace('Ύ', 'Υ').replace('Ώ', 'Ω').replace('ά', 'Α').replace('έ', 'Ε').replace('ή', 'Η').replace('ί', 'Ι').replace('ό', 'Ο').replace('ύ', 'Υ').replace('ώ', 'Ω').replace('A', 'Α').replace('B', 'Β').replace('E', 'Ε').replace('H', 'Η').replace('I', 'Ι').replace('K', 'Κ').replace('M', 'Μ').replace('N', 'Ν').replace('O', 'Ο').replace('P', 'Ρ').replace('T', 'Τ').replace('X', 'Χ').replace('Y', 'Υ').replace('\u0010', 'Δ').replace('\u0012', 'Φ').replace('\u0013', 'Γ').replace('\u0014', 'Λ').replace('\u0015', 'Ω').replace('\u0016', 'Π').replace('\u0017', 'Ψ').replace('\u0018', 'Σ').replace('\u0019', 'Θ').replace('\u0020', 'Ξ').replace('α', 'Α').replace('β', 'Β').replace('γ', 'Γ').replace('δ', 'Δ').replace('ε', 'Ε').replace('ζ', 'Ζ').replace('η', 'Η').replace('θ', 'Θ').replace('ι', 'Ι').replace('κ', 'Κ').replace('λ', 'Λ').replace('μ', 'Μ').replace('ν', 'Ν').replace('ξ', 'Ξ').replace('ο', 'Ο').replace('π', 'Π').replace('ρ', 'Ρ').replace('σ', 'Σ').replace('ς', 'Σ').replace('τ', 'Τ').replace('υ', 'Υ').replace('φ', 'Φ').replace('χ', 'Χ').replace('ψ', 'Ψ').replace('ω', 'Ω') ;
                
        char[] strArray = unNormalizedString.toUpperCase().toCharArray() ;
        
        for(int i=0; i<strArray.length; i++){
            if(strArray[i] == 'α' || strArray[i] == 'ά' || strArray[i] == 'Ά' || strArray[i] == '\u0041')  strArray[i] = 'Α' ;   
            else if(strArray[i] == 'β' || strArray[i] == '\u0042')  strArray[i] = 'Β' ;   
            else if(strArray[i] == 'γ' || strArray[i] == '\u0013')  strArray[i] = 'Γ' ;   
            else if(strArray[i] == 'δ' || strArray[i] == '\u0010')  strArray[i] = 'Δ' ;   
            else if(strArray[i] == 'ε' || strArray[i] == 'έ' || strArray[i] == 'Έ' || strArray[i] == '\u0045')  strArray[i] = 'Ε' ;   
            else if(strArray[i] == 'ζ' || strArray[i] == '\u005A')  strArray[i] = 'Ζ' ;   
            else if(strArray[i] == 'η' || strArray[i] == 'ή' || strArray[i] == 'Ή' || strArray[i] == '\u0048')  strArray[i] = 'Η' ;   
            else if(strArray[i] == 'θ' || strArray[i] == '\u0019')  strArray[i] = 'Θ' ;   
            else if(strArray[i] == 'ι' || strArray[i] == 'ί' || strArray[i] == 'Ί' || strArray[i] == '\u0049')  strArray[i] = 'Ι' ;   
            else if(strArray[i] == 'κ' || strArray[i] == '\u004B')  strArray[i] = 'Κ' ;   
            else if(strArray[i] == 'λ' || strArray[i] == '\u0014')  strArray[i] = 'Λ' ;   
            else if(strArray[i] == 'μ' || strArray[i] == '\u004D')  strArray[i] = 'Μ' ;   
            else if(strArray[i] == 'ν' || strArray[i] == '\u004E')  strArray[i] = 'Ν' ;   
            else if(strArray[i] == 'ξ' || strArray[i] == '\u0020')  strArray[i] = 'Ξ' ;   
            else if(strArray[i] == 'ο' || strArray[i] == 'ό' || strArray[i] == 'Ό' || strArray[i] == '\u004F')  strArray[i] = 'Ο' ;   
            else if(strArray[i] == 'π' || strArray[i] == '\u0016')  strArray[i] = 'Π' ;   
            else if(strArray[i] == 'ρ' || strArray[i] == '\u0050')  strArray[i] = 'Ρ' ;   
            else if(strArray[i] == 'ς' || strArray[i] == 'σ' || strArray[i] == '\u0018')  strArray[i] = 'Σ' ;   
            else if(strArray[i] == 'τ' || strArray[i] == '\u0054')  strArray[i] = 'Τ' ;   
            else if(strArray[i] == 'υ' || strArray[i] == 'ύ' || strArray[i] == 'Ύ' || strArray[i] == '\u0059')  strArray[i] = 'Υ' ;   
            else if(strArray[i] == 'φ' || strArray[i] == '\u0012')  strArray[i] = 'Φ' ;   
            else if(strArray[i] == 'χ' || strArray[i] == '\u0058')  strArray[i] = 'Χ' ;   
            else if(strArray[i] == 'ψ' || strArray[i] == '\u0017')  strArray[i] = 'Ψ' ;   
            else if(strArray[i] == 'ω' || strArray[i] == 'ώ' || strArray[i] == 'Ώ' || strArray[i] == '\u0015')  strArray[i] = 'Ω' ;  
        }
        
        return new String(strArray, 0, strArray.length) ;
    }     
}
