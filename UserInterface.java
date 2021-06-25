import javax.microedition.midlet.* ;
import javax.microedition.lcdui.* ;
import java.util.Calendar ;
import java.util.Date ;

/**
 * Τάξη υλοποίησης της διασύνδεσης με το χρήστη.
 * @author  Πέτρος Κυλαδίτης
 * @version 3.0
 */
public class UserInterface extends MIDlet implements CommandListener {

    private Display display ;                      // Αναπαριστά το περιβάλλον
    
    private Command exitCommand ;                                   // Εντολή τερματισμού της εφαρμογής
    private Command otherDateCommand ;                         // Εντολή  εμφάνισης της φόρμας εισαγωγής ορισμού της ημ/νίας του εορτολογίου από το χρήστη
    private Command showOtherCelebrationsCommand ;    // Εντολή εμφάνισης των εορτών της ημ/νίας που όρισε ο χρήστης
    private Command showTodayCelebrationsCommand ;   // Εντολή εμφάνισης των σημερινών εορτών
    private Command showEasterTextBoxCommand ;          // Εντολή εμφάνισης του πλαισίου εισαγωγής του έτους για τον υπολογισμό του Πάσχα
    private Command showEasterFormCommand ;               // Εντολή εμφάνισης της ημ/νίας που πέφτει το Πάσχα για το ορισμένο από το χρήστη έτος
    private Command backToEasterTextBoxCommand ;       // Εντολή επανεμφάνισης του πλαισίου εισαγωγής του έτους για τον υπολογισμό του Πάσχα
    private Command showInfoCommand ;                           // Εντολή εμφάνισης πληροφοριών για την εφαμρμογή
    private Command showArgiesFormCommand ;               // Εντολή εμφάνισης των αργιών του ορισμένου από το χρήστη έτους
    private Command showArgiesTextBoxCommand ;          // Εντολή εμφάνισης του πλαισίου εισαγωγής του έτους για τον υπολογισμό των αργιών
    private Command backToArgiesTextBoxCommand ;       // Εντολή επανεμφάνισης του πλαισίου εισαγωγής του έτους για τον υπολογισμό των αργιών
    private Command showSearchTextBoxCommand ;          // Εντολή εμφάνισης του πλαισίου εισαγωγής του ερωτήματος της αναζήτησης
    private Command showSearchOutputFormCommand ;    // Εντολή εμφανισης του αποτελέσματος της αναζήτησης
    private Command backToSearchTextBoxCommand ;       // Εντολή επανεμφάνισης του πλαισίου εισαγωγής του ερωτήματος της αναζήτησης
    private Command showNextCelebrationCommand ;        // Εντολή εμφάνισης της επόμενης εορτής
    private Command showPreviousCelebrationCommand ;  // Εντολή εμφάνισης της προηγουμενης εορτής
    
    private Form todayCelebrationsForm ;    // Φόρμα εμφάνισης των σημερινών εορτών
    private Form otherCelebrationsForm ;    // Φόρμα εμφάνισης των εορτών της ημ/νίας που διάλεξε ο χρήστης
    private Form dateInputForm ;                  // Φόρμα επιλογής άλλης ημ/νίας
    private Form argiesForm ;                       // Φόρμα εμφάνισης των αργιών
    private Form easterForm ;                       // Φόρμα εμφάνισης της ημ/νίας του Πάσχα
    private Form searchOutputForm ;            // Φόρμα αναζήτησης του ονόματος
    
    private DateField inputDateField ;           // Πεδίο εισαγωγής της ημ/νία στη φόρμα

    private TextBox easterTextBox ;             // Πλαίσιο κειμένου εισαγωγής του έτους υπλογισμού του Πάσχα
    private TextBox argiesTextBox ;             // Πλαίσιο κειμένου εισαγωγής του έτους υπλογισμού των αργιών
    private TextBox searchTextBox ;             // Πλαίσιο κειμένου εισαγωγής του ερωτήματος της αναζήτησης
    
    private Date buoyDate = new Date(System.currentTimeMillis()) ;
    private static int DAY_MILLISECONDS =  86400000 ; //Τα χιλιοστά του δευτ. μιας μέρας: 24x60x60x1000
    
    //Μέθοδος κατασκευής
    public UserInterface() {

        //Κατασκευή του γραφικού περιβάλλοντος
        display = Display.getDisplay(this) ;
        
        //Κατασκευή των κουμπιών εντολών
        exitCommand = new Command("Έξοδος", Command.EXIT, 1) ;
        otherDateCommand = new Command("ʼλλοτε", Command.SCREEN, 1);
        showOtherCelebrationsCommand = new Command("Προβολή",  Command.SCREEN, 1) ;
        showTodayCelebrationsCommand = new Command("Σήμερα", Command.SCREEN, 1);
        showEasterTextBoxCommand = new Command("Υπολ. Πάσχα", Command.SCREEN, 1) ;
        showEasterFormCommand = new Command("ΟΚ", Command.SCREEN, 1) ;
        backToEasterTextBoxCommand = new Command("Ξανά", Command.SCREEN, 1) ;
        showInfoCommand = new Command("Πληροφορίες", Command.SCREEN, 1) ;
        showArgiesFormCommand = new Command("OK", Command.SCREEN, 1) ;
        showArgiesTextBoxCommand = new Command("Αργίες", Command.SCREEN, 1) ;
        backToArgiesTextBoxCommand = new Command("Ξανά", Command.SCREEN, 1) ;
        showSearchTextBoxCommand = new Command("Αναζήτηση", Command.SCREEN, 1) ;
        showSearchOutputFormCommand = new Command("ΟΚ", Command.SCREEN, 1) ;
        backToSearchTextBoxCommand = new Command("Ξανά", Command.SCREEN, 1) ;
        showNextCelebrationCommand = new Command("+1 μέρα", Command.SCREEN, 1) ;
        showPreviousCelebrationCommand = new Command("-1 μέρα", Command.SCREEN, 1) ;
    
        //Κατασκευή πλαισίων κειμένου
        easterTextBox = new TextBox("Δώστε το έτος", Integer.toString(Calendar.getInstance().get(Calendar.YEAR)), 4, TextField.NUMERIC) ;
        argiesTextBox = new TextBox("Δώστε το έτος", Integer.toString(Calendar.getInstance().get(Calendar.YEAR)), 4, TextField.NUMERIC) ;
        searchTextBox = new TextBox("Αναζήτηση ονόματος", null, 30, TextField.ANY) ;
    }
    
    // Σημείο εκκίνισης της εφαρμογής.
    public void startApp() {
        showTodayCelebrations() ;
    }
    
    /** Διακοπή ενεργειών και αποδέσμευση πόρων.
         Εδώ δεν έχει καμία λειτουργία, αλλά πρέπει να επικαληφθεί
         διότι είναι μέθοδος της αφηριμένης τάξης MIDlet.    */    
    public void pauseApp() {  }
    
    /** Καθαρισμός και αποδέσμευση πόρων που δεν μπορεί να καταστρέψει ο GC
          Εδώ δεν έχει καμία λειτουργία, αλλά πρέπει να επικαληφθεί
          διότι είναι μέθοδος της αφηριμένης τάξης MIDlet.    */
    public void destroyApp(boolean unconditional) {  }
    
    //Διαχειριστής εντολών
    public void commandAction(Command c, Displayable s) {
        if (c == exitCommand) {
            this.destroyApp(false) ;                         //Εντολή τερματισμού της εφαρμογής
            this.notifyDestroyed() ;                         //Ειδοποίηση της VM ότι τερματίστηκε
        }
        else if(c == otherDateCommand){
            inputOtherDateCelebrations() ;
        }
        else if(c == showOtherCelebrationsCommand){
            showOtherDateCelebrations(otherDateCommand, showNextCelebrationCommand, showPreviousCelebrationCommand) ;
        }
        else if(c == showTodayCelebrationsCommand){
            showTodayCelebrations() ;
        }
        else if(c == showEasterTextBoxCommand || c == backToEasterTextBoxCommand){
            showEasterTextBox() ;
        }
        else if(c == showEasterFormCommand){
            showEasterForm() ;
        }
        else if(c == showInfoCommand){
            showInfo() ;
        }
        else if(c == showArgiesTextBoxCommand || c == backToArgiesTextBoxCommand){
            showArgiesTextBox() ;
        }
        else if(c == showArgiesFormCommand){
            showArgiesForm() ;
        }
        else if(c == showSearchOutputFormCommand){
            showSearchOutputForm() ;
        }
        else if(c == showSearchTextBoxCommand || c == backToSearchTextBoxCommand){
            showSearchTextBox() ;
        }
        else if(c == showNextCelebrationCommand){
            inputDateField.setDate(new Date(buoyDate.getTime() + DAY_MILLISECONDS)) ;
            showOtherDateCelebrations(showNextCelebrationCommand, showPreviousCelebrationCommand, otherDateCommand) ;
        }
        else if(c == showPreviousCelebrationCommand){
            inputDateField.setDate(new Date(buoyDate.getTime() - DAY_MILLISECONDS)) ;
            showOtherDateCelebrations(showPreviousCelebrationCommand, showNextCelebrationCommand, otherDateCommand) ;
        }        
    }
    
    /**
     * Εμφανίζει τις γιορτές για σήμερα, αύριο και μεθαύριο
     * @since 1.1
     */
    private void showTodayCelebrations(){
        buoyDate.setTime(System.currentTimeMillis()) ;
        todayCelebrationsForm = new CelebrationsForm() ;        
        todayCelebrationsForm.setCommandListener(this) ;
        todayCelebrationsForm.addCommand(otherDateCommand) ;
        todayCelebrationsForm.addCommand(showSearchTextBoxCommand) ;
        todayCelebrationsForm.addCommand(showEasterTextBoxCommand) ;
        todayCelebrationsForm.addCommand(showArgiesTextBoxCommand) ;
        todayCelebrationsForm.addCommand(showInfoCommand) ;
        todayCelebrationsForm.addCommand(exitCommand) ;        
        display.setCurrent(todayCelebrationsForm) ;         
    }
    
    /**
     * Φόρμα εισαγωγής της ημ/νίας εμφάνισης των γιορτών απ' το χρήστη
     * @since 2.0
     */
    private void inputOtherDateCelebrations(){
        inputDateField = new DateField("Δώστε την ημερομηνία για την οποία θέλετε να εμφανιστεί η γιορτή", DateField.DATE) ;
        inputDateField.setDate(buoyDate) ; 
        dateInputForm = new Form("ʼλλοτε") ;
        dateInputForm.append(inputDateField) ;
        dateInputForm.setCommandListener(this) ;
        dateInputForm.addCommand(showOtherCelebrationsCommand) ;
        display.setCurrent(dateInputForm) ;
    }
    
    /**
     * Εμφανίζει τις γιορτές για την ημ/νία που έδωσε ο χρήστης
     * @param first Η πρώτη σε σειρά εντολή
     * @param second Η δεύτερη σε σειρά εντολή
     * @param thrid Η τρίτη σε σειρά εντολή
     * @since 3.0
     */
    private void showOtherDateCelebrations(Command first, Command second, Command third){        
        Calendar givenCalendar = Calendar.getInstance() ;
        givenCalendar.setTime( inputDateField.getDate() ) ;
        buoyDate.setTime(inputDateField.getDate().getTime()) ;
        otherCelebrationsForm = new CelebrationsForm(givenCalendar) ;
        otherCelebrationsForm.setCommandListener(this) ;
        otherCelebrationsForm.addCommand(first) ;
        otherCelebrationsForm.addCommand(second) ;
        otherCelebrationsForm.addCommand(third) ;
        otherCelebrationsForm.addCommand(showTodayCelebrationsCommand) ;
        otherCelebrationsForm.addCommand(showSearchTextBoxCommand) ;
        otherCelebrationsForm.addCommand(showEasterTextBoxCommand) ;
        otherCelebrationsForm.addCommand(showArgiesTextBoxCommand) ;
        otherCelebrationsForm.addCommand(showInfoCommand) ;
        otherCelebrationsForm.addCommand(exitCommand) ;
        display.setCurrent(otherCelebrationsForm) ;
    }
    
    private void showSearchTextBox(){
        searchTextBox.setCommandListener(this) ;
        searchTextBox.addCommand(showSearchOutputFormCommand) ;
        searchTextBox.addCommand(showTodayCelebrationsCommand) ;
        searchTextBox.addCommand(otherDateCommand) ;
        searchTextBox.addCommand(showEasterTextBoxCommand) ;        
        searchTextBox.addCommand(showArgiesTextBoxCommand) ;
        searchTextBox.addCommand(showInfoCommand) ;
        searchTextBox.addCommand(exitCommand) ;
        display.setCurrent(searchTextBox) ;
    }
    
    private void showSearchOutputForm(){        
        searchOutputForm = new CelebrationsForm(searchTextBox.getString()) ;
        searchOutputForm.setCommandListener(this) ;
        searchOutputForm.addCommand(backToSearchTextBoxCommand) ;
        searchOutputForm.addCommand(showTodayCelebrationsCommand) ;
        searchOutputForm.addCommand(otherDateCommand) ;
        searchOutputForm.addCommand(showEasterTextBoxCommand) ;        
        searchOutputForm.addCommand(showArgiesTextBoxCommand) ;
        searchOutputForm.addCommand(showInfoCommand) ;
        searchOutputForm.addCommand(exitCommand) ;        
        display.setCurrent(searchOutputForm) ;
        
    }
    
    /**
     * Πλαίσιο κειμένου εισαγωγής του έτους για τον υπολογισμό της ημ/νίας του Πάσχα
     * @since 1.5
     */
    private void showEasterTextBox(){
        easterTextBox.setCommandListener(this) ;
        easterTextBox.addCommand(showEasterFormCommand) ;
        easterTextBox.addCommand(showTodayCelebrationsCommand) ;
        easterTextBox.addCommand(otherDateCommand) ;
        easterTextBox.addCommand(showSearchTextBoxCommand) ;
        easterTextBox.addCommand(showArgiesTextBoxCommand) ;
        easterTextBox.addCommand(showInfoCommand) ;
        easterTextBox.addCommand(exitCommand) ;
        display.setCurrent(easterTextBox) ;
    }
    
    /**
     * Εμφανίζει το πότε πέφτει το Πάσχα για το έτος που όρισε ο χρήστης
     * @since 1.5
     */
    private void showEasterForm(){
        try {
            easterForm = new EasterForm(Integer.parseInt(easterTextBox.getString())) ;
            easterForm.setCommandListener(this) ;
            easterForm.addCommand(backToEasterTextBoxCommand) ;
            easterForm.addCommand(showTodayCelebrationsCommand) ;
            easterForm.addCommand(otherDateCommand) ;
            easterForm.addCommand(showSearchTextBoxCommand) ;
            easterForm.addCommand(showArgiesTextBoxCommand) ;
            easterForm.addCommand(showInfoCommand) ;
            easterForm.addCommand(exitCommand) ;
            display.setCurrent(easterForm) ;
        }
        catch(NumberFormatException exception){
            Alert alert = new Alert("Προσοχή!") ;
            alert.setString(exception.getMessage()) ;
            alert.setType(AlertType.ERROR) ;
            alert.setTimeout(Alert.FOREVER) ;
            alert.setCommandListener(this) ;
            alert.addCommand(backToEasterTextBoxCommand) ;
            alert.addCommand(showTodayCelebrationsCommand) ;
            alert.addCommand(otherDateCommand) ;
            alert.addCommand(showSearchTextBoxCommand) ;
            alert.addCommand(showArgiesTextBoxCommand) ;
            alert.addCommand(showInfoCommand) ;
            alert.addCommand(exitCommand) ;
            display.setCurrent(alert) ;
        }
    }    
    

    /**
     * Πλαίσιο κειμένου για την εισαγωγή του έτους υπολογισμού των αργιών
     * @since 2.5
     */
    private void showArgiesTextBox(){
        argiesTextBox.setCommandListener(this) ;
        argiesTextBox.addCommand(showArgiesFormCommand) ;        
        argiesTextBox.addCommand(showEasterTextBoxCommand) ;
        argiesTextBox.addCommand(showTodayCelebrationsCommand) ;
        argiesTextBox.addCommand(otherDateCommand) ;
        argiesTextBox.addCommand(showSearchTextBoxCommand) ;
        argiesTextBox.addCommand(showArgiesFormCommand) ;
        argiesTextBox.addCommand(showInfoCommand) ;
        argiesTextBox.addCommand(exitCommand) ;
        display.setCurrent(argiesTextBox) ;
    }
    
    /**
     * Εμφανίζει τις αργίες για το έτος που όρισε ο χρήστης
     * @since 2.5
     */
    private void showArgiesForm(){
        try{
            argiesForm = new ArgiesForm(Integer.parseInt(argiesTextBox.getString())) ;
            argiesForm.addCommand(backToArgiesTextBoxCommand) ;
            argiesForm.addCommand(showTodayCelebrationsCommand) ;
            argiesForm.addCommand(otherDateCommand) ;
            argiesForm.addCommand(showSearchTextBoxCommand) ;
            argiesForm.addCommand(showEasterTextBoxCommand) ;
            argiesForm.addCommand(showInfoCommand) ;
            argiesForm.addCommand(exitCommand) ;
            argiesForm.setCommandListener(this) ;
            display.setCurrent(argiesForm) ;
        }
        catch(NumberFormatException exception){
            Alert alert = new Alert("Προσοχή!") ;
            alert.setString(exception.getMessage()) ;
            alert.setType(AlertType.ERROR) ;
            alert.setTimeout(Alert.FOREVER) ;
            alert.setCommandListener(this) ;
            alert.addCommand(backToArgiesTextBoxCommand) ;
            alert.addCommand(showTodayCelebrationsCommand) ;
            alert.addCommand(otherDateCommand) ;
            alert.addCommand(showSearchTextBoxCommand) ;
            alert.addCommand(showEasterTextBoxCommand) ;
            alert.addCommand(showInfoCommand) ;
            alert.addCommand(exitCommand) ;
            display.setCurrent(alert) ;            
        }
    }
    
    /**
     * Εμφανίζει πληροφορίες για την εφαρμογή και τα πνευματικά δικαιώματα
     * ##############################################################################
     * # Ο κωδικός 2122 αντιστοιχεί στον Unicode κωδικό του εμπορικού σήματος                               #
     * # Ο κωδικός 00A9 αντιστοιχεί στον Unicode κωδικό του συμβόλου πνευματικών δικαιωμάτων  #
     * ##############################################################################
     * @since 2.0
     */
    private void showInfo(){
        Form infoForm = new Form("Πληροφορίες") ;
        infoForm.append("Εορτολόγιο\nΈκδοση: 3.1\n\nΠνευματικά δικαιώματα, \u00A9 2005 - Πέτρος Κυλαδίτης") ;
        infoForm.append("\n\n\nΜια εφαρμογή για την πλατφόρμα Java\u2122 2 Micro Edition\nΑπαιτήσεις συσκευής: MIDP-1.0 / CLDC-1.0") ;
        infoForm.append("\n\nΤο όνομα Java είναι εμπορικό σήμα της Sun Microsystems, Inc.") ;
        infoForm.setCommandListener(this) ;
        infoForm.addCommand(showTodayCelebrationsCommand) ;
        infoForm.addCommand(otherDateCommand) ;
        infoForm.addCommand(showSearchTextBoxCommand) ;
        infoForm.addCommand(showEasterTextBoxCommand) ;
        infoForm.addCommand(showArgiesTextBoxCommand) ;
        infoForm.addCommand(exitCommand) ;
        display.setCurrent(infoForm) ;
    }
}
