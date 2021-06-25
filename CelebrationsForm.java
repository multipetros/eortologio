import javax.microedition.lcdui.* ;
import java.util.Calendar ;
import java.util.Date ;

/**
 * ���� ��������� ��������� ��� ������������ ������
 * @author ������ ���������
 * @version 2.0 (�������� ������� ���������� ��������)
 */
public class CelebrationsForm extends Form{
    
    private OnomastikesEortes eortes ;   // �������� ��� ����������� ������
    private Calendar todayCalendar ;       // �� ������ ������������ ����

    private static String NOBODY = "# ������� #" ;             //������������ �������������� ��� ����� ������������� ��� ������
    private static String SELEBRATE = "����������" ;
    private static String NORESULT = "��� ������� ����������   :-(\n\n��������� ���� �� ����������� �������." ;
    private static int DAY_MILLISECONDS = 86400000 ;    //�� �������� ��� ����. ���� �����: 24x60x60x1000    
    private static String[] WEEKDAYS = {"�������", "�������", "�����", "�������", "������", "���������", "�������"} ;    
    
    /**
     * ������������� ������� ����������<br>
     * ���������� ��� ����� �� ������ ��� ��������� ��/����, ���� ���� ���� ��� ��� ����� ����.
     * @version 2.0 (�������� ���������� ��������� ��� �������������� ��� ����� ��� ����
     *                          ��� ��������� �� ��� ���� �������� ��� ����� OnomastikesEortes)
     * @since 1.0
     */
    public CelebrationsForm(){
        //������������ ����������
        super(SELEBRATE) ;
        
         //������������ ��� ���������� ���������� ��� ������������
         eortes = new OnomastikesEortes() ;
         todayCalendar = Calendar.getInstance() ;

         //��������� ��� ��������. �� ������� ����� ���/��� �� ���������������� �� �� �������
         StringBuffer celebrateMsg = new StringBuffer() ;
         String celebration ;
         String[] descript = {"������:\n", "\n\n�����:\n", "\n\n��������:\n", "\n\n�� ����� �����:\n", "\n\n�� ��������� �����:\n", "\n\n�� ����� �����:\n", "\n\n�� ��� �����:\n", "\n\n�� ��� ��������:\n"} ;
         for(int i=0; i<8; i++){
            celebration = eortes.getEorti(todayCalendar.getTime().getTime() + (DAY_MILLISECONDS * i)) ;
            if(celebration.length()==0)    celebration = NOBODY ;
            celebrateMsg.append(descript[i]).append(celebration) ;
         }

         //���������� ��� ��������, �������� ��� ������������� ������
         this.append(new String(celebrateMsg)) ;
    } 
    
    /**
     * ������� ���������� �� ������� ��� ��/���� ��������� ��� ������
     * @param calendar � ��/��� ��� ��� ����� �� ���������� � �����
     * @since 1.1
     * @version 2.0 (��������� �� ��� ���� �������� ��� OnomastikesEortes)
     */
    public CelebrationsForm(Calendar calendar){
        super(SELEBRATE) ;

        //������������ ��� ���������� ��� ��� ������������        
        eortes = new OnomastikesEortes(calendar.get(Calendar.YEAR)) ;  // ������������ ��� ����. ��� �� ���� ��� ������
        String celebration = eortes.getEorti(calendar) ;        
        if (celebration.length() == 0) celebration = this.NOBODY ;
        StringBuffer celebrateMsg = new StringBuffer(WEEKDAYS[calendar.get(Calendar.DAY_OF_WEEK) - 1]).append(" ").append(calendar.get(Calendar.DAY_OF_MONTH)).append("/").append(calendar.get(Calendar.MONTH) + 1).append("/").append(calendar.get(Calendar.YEAR)).append(":\n").append(celebration) ;
        
        //���������� ��� ��������, �������� ��� ������������� ������
        this.append(new String(celebrateMsg)) ;
    }

    /**
     * ������� ���������� �� ��������� ��������
     * @param name � ������������ ��� ��� ����� �� ����� ���������
     * @since 2.0
     */
    public CelebrationsForm(String name){
       
        //������������ ���������� ��� ������������
        super(name + " ...") ;
        eortes = new OnomastikesEortes() ;
        String celebrateMsg = eortes.getEorti(name) ;
        if(celebrateMsg.length() == 0) celebrateMsg = this.NORESULT ;
        
        //���������� ��� ��������, �������� ��� ������������� ������
        this.append(celebrateMsg) ;
    }
}
