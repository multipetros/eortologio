import javax.microedition.lcdui.* ;
import java.util.Calendar ;

/**
 * ���� ��������� ��� ��/���� ��� �����
 * @author ������ ���������
 * @version 1.1
 */
public class EasterForm extends Form {
    
    private OrthodoxEaster pasxa ;
    private StringBuffer output ;
    
    /**
     * ���������� ��� ����� �� ��� ��/��� ��� �����, ��� �� ����� ����.
     * @param year �� ���� ��� �� ����� �� ����������� �� �����
     * @throws NumberFormatException ��� ���� ����� �������� � �������� ����
     */
    public EasterForm(int year) throws NumberFormatException {                
        super("����������") ;

        pasxa = new OrthodoxEaster() ;
        int day ;
        String month ;
        
        if (year <= 0) throw new NumberFormatException("��� ���� ������ �� ����� � �����������.\n\n��� ������� �������� ������������ ������ ������� ����������� �����.") ;
        
        pasxa.setYear(year) ;
        if (pasxa.getMonth() == 4) month = new String("��������") ;
        else month = new String("�����") ;
        day = pasxa.getDay() ;
        output = new StringBuffer("�� ����� �� ����, ").append(year).append(" ������ ���� ").append(day).append(" ").append(month) ;

         this.append(new String(output)) ;
    }
    
    /**
     * ���������� ��� ����� �� ��� ��/��� ��� �����, ��� �� ������ ����.
     */
    public EasterForm(){
        this(Calendar.getInstance().get(Calendar.YEAR)) ;
    }
    
}
