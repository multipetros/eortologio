import java.util.Calendar ;
import java.util.Date ;

/**
 * ���� ��������� ����������� ������
 * @author ������ ���������
 * @version 3.1
 * @serial 20050510
 */
public class OnomastikesEortes{

    // ������������ ������� 12x31 ��� ��� ���������� ��� ������������� ��� �������� ������
    private String[][] eortes = { {"�������\n��������", "", "", "���������", "", "��������\n��������\n�������\n�����", "������\n�������\n���������", "", "", "", "��������", "�������", "����������", "", "", "", "�������\n�������", "�������\n��������", "���������", "�������\n�������", "����\n�������\n��������", "��������", "�����������", "����", "���������", "����������", "", "", "", "", "�������"},
                              {"���������\n��������", "��������", "��������\n��������", "", "�����", "", "���������", "", "", "����������", "������", "��������", "", "���������\n����������", "", "", "��������", "", "�������", "", "", "", "����������", "", "", "", "", "", "", "", ""},
                              {"�������", "�������", "", "", "", "", "", "�����", "", "", "", "", "", "", "", "", "������\n������\n������", "", "", "", "", "", "", "", "��������\n���������", "", "", "", "", "", ""},
                              {"", "", "", "", "", "", "", "", "", "����������\n���������\n������������\n�������\n��������", "", "��������", "", "", "��������", "", "��������\n��������", "", "", "", "", "��������", "", "��������", "������", "", "", "", "�����", "�������\n��������", ""},
                              {"", "������", "", "�������", "������\n������\n����", "", "", "��������\n��������", "�����������", "�����", "��������", "��������", "�����\n��������", "��������\n�������", "���������", "", "�������\n���������\n����������\n������", "������", "", "�����", "�����\n�����\n�����������\n������������", "", "", "", "", "", "", "", "", "", ""},
                              {"", "���������", "", "�����", "�������\n��������\n�������", "", "", "��������", "", "", "������������", "", "", "���������", "�����������", "", "", "", "", "", "", "��������", "", "��������", "������\n��������", "", "", "", "������\n������", "���������", ""},
                              {"���������\n������\n������\n��������", "", "", "", "", "", "�������", "��������\n��������", "", "", "����\n�������", "��������", "", "���������\n������", "��������\n�������", "", "������\n�������", "�������\n����������", "", "�����", "����", "��������\n���������", "", "", "�������", "���������\n����������", "��������", "", "����������", "", ""},
                              {"", "", "", "", "", "�������\n�������", "������", "�������������", "", "", "", "", "", "", "�����\n������\n���������\n����������\n��������", "��������\n��������\n��������\n����������\n��������", "", "����������", "", "", "", "", "", "", "�������\n�������\n�����", "��������\n���������\n�������", "���������", "", "", "����������", ""},
                              {"�����\n�������\n��������\n�����\n�����\n���������\n��������\n���������\n������", "������", "�����\n���������", "�������\n��������\n�������", "��������", "", "", "", "�������", "���������", "�������", "�������", "����������\n���������\n���������", "�������", "�������", "", "������\n�����\n�����", "�������", "", "������\n��������", "", "�����", "��������", "�����", "���������", "", "", "", "��������\n��������", "", ""},
                              {"�������", "", "��������\n��������", "", "��������", "", "������\n�������", "�������", "", "��������\n���������", "", "������", "", "", "", "", "", "������", "", "�������\n��������\n���������", "��������\n������������", "", "", "", "", "�������\n��������", "��������", "�������", "", "�������", ""},
                              {"���������\n������", "�����������", "", "", "", "���������", "", "������\n�������\n�������", "���������\n��������\n���������", "�������", "��������\n���������\n�����", "", "", "��������", "", "��������", "", "������\n�������", "", "", "", "�������", "", "", "��������\n����������", "�������\n������", "", "", "", "�������", ""},
                              {"", "������", "�������", "�������", "��������\n������", "�����\n��������", "���������", "", "����", "", "", "������", "�������\n������", "", "���������\n����������\n����", "", "������", "�����������\n��������", "������\n����", "��������", "�����������", "", "", "�������", "����������\n�������\n�������\n�����", "", "��������\n�������", "�����", "", "", ""} } ;
    
    /* �� ��� �������� ������� ������������ ��� ������������� ��� ������� ������ ��� ���
     ������ ��������� �� ����� �� �� �����. �� ����� ��� ������� ������ �� ����� �� ���� */
    private String[] kinitesEortesOnomata = {"�������\n��������", "�������", "�����", "���������\n���������\n�������\n��������", "��������\n�������","���\n����", "�����"} ;
    private int [] kinitesEortesHmeres = {-43, -8, -7, 0 , 1, 5, 7} ;
   
    private Calendar pasxa ;
    
    private static int DAY_MILLISECONDS =  86400000 ; //�� �������� ��� ����. ���� �����: 24x60x60x1000
    private static String NEW_LINE = new String("\n") ;
    private static String DATE_SEPARATOR  = new String(" \\ ") ;
    private static String NAME_SEPARATOR = new String ("  :  ");
    
    /**
     * ������� ���������� ��� ����������� �� ������� ��� �����
     * @param year �� ���� ��� �� ����� �� ������������ �� ������� ������
     */
    public OnomastikesEortes(int year){
        pasxa = new OrthodoxEaster(year).getCalendar() ;
        initializingKinitesEortes() ;
    }
    
    /**
     * ������������� ������� ���������� ��� ����������� ��� �� ������ ����
     */
    public OnomastikesEortes(){
        pasxa = new OrthodoxEaster().getCalendar() ;
        initializingKinitesEortes() ;
    }
    
    /**
     *  �������� �� ���������� ����������� ��� ������� ������ ��� ��� ��������� ���� ������ 
     * @version 2.1 (���������� ���� ���������� ��� ������)
     */
    private void initializingKinitesEortes(){
        long pasxaDays = pasxa.getTime().getTime() / DAY_MILLISECONDS ;
        long easterDaysDiv = pasxa.getTime().getTime() % DAY_MILLISECONDS ; //�� ���.���� ��� ���������� ��� �� ���������� ���� ������ ����������
        for(int i=0; i<kinitesEortesOnomata.length; i++){
            pasxa.setTime(new Date((pasxaDays + kinitesEortesHmeres[i]) * DAY_MILLISECONDS + easterDaysDiv)) ;
            if(eortes[pasxa.get(Calendar.MONTH)][pasxa.get(Calendar.DAY_OF_MONTH) - 1].length()==0)
                eortes[pasxa.get(Calendar.MONTH)][pasxa.get(Calendar.DAY_OF_MONTH) - 1] += kinitesEortesOnomata[i] ;
            else
                eortes[pasxa.get(Calendar.MONTH)][pasxa.get(Calendar.DAY_OF_MONTH) - 1] += NEW_LINE + kinitesEortesOnomata[i] ;
            pasxa.setTime(new Date((pasxaDays - kinitesEortesHmeres[i]) * DAY_MILLISECONDS - easterDaysDiv)) ; //��������� ���� ��/��� ��� �����
        }
    }
    
    /**
     * @param day � ����� ��� ����
     * @param month � ����� ��� ������
     * @return ������������ ��� �������� �� ������� ��� �����������
     * @deprecated ��� ��� ������ 3.0, ��� ������ ��������� ��� ����� � ������� ���� ������ �� ����� private
     * @see #getEorti(Calendar) ����� ������������ java.util.Calendar
     * @see #getEorti(long) ����� 1000���� ��� ����. ��� ��� epoch
     */
    public String getEorti(int day, int month){
        if(month<1 || month>12 || day<1 || day>31)
            return null ;
        return eortes[month-1][day-1] ;         //-1 ����� �� ������� �������� ��� �������� ��� �� 0
    }
    
    /**
     * @param calendar ����������� java.util.Calendar ��� ���������� ��� ��/���
     * @return ������������ ��� �������� �� ������� ��� �����������
     * @sine 3.0
     */
    public String getEorti(Calendar calendar){
        return getEorti(calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH) + 1) ;  //����� � �������� ��� ����� ���� ���� Calendar �������� ��� �� 0
    }

    /**
     * @param dateMilliseconds ��������� ��� ��/���� �� 1000��� ��� ����. ��� ��� epoch
     * @return ������������ ��� �������� �� ������� ��� �����������
     * @sine 3.0
     */
    public String getEorti(long dateMilliseconds){
        Calendar calendar = Calendar.getInstance() ;
        calendar.setTime(new Date(dateMilliseconds)) ;
        return getEorti(calendar) ;
    }
    
    
    /**
     * @return �� ���� ��� �� ����� ���� ����� � ����������� ��� ������� ������
     */
    public int getYear(){
        return pasxa.get(Calendar.YEAR) ;
    }
    
    
    /**
     * @param name �� ������� ��� ����������.
     * @return ������������ �� �� ������� ��� ��� ������, ��� �� ����� ����� ���������.
     * @since 3.0
     */
    public String getEorti(String name){
        
   	// ��������� ���� ���� ������ �� ��� �������� ��� '���������' ����������
   	String[][] eortesTemp = new String[12][31] ;
   	for(int i=0; i<12; i++){
   		for(int j=0; j<31; j++){
   			eortesTemp[i][j] = stringNormalize(eortes[i][j]) ;
   		}
   	}
   	
        StringBuffer buffer = new StringBuffer() ;
        name = stringNormalize(name) ;

   	// ��������� �������� �� ��� ��� ������
   	for (int i=0; i<12; i++){
            for (int j=0; j<31; j++){
                int lastPosition = -1 ;
   		int nPosition = eortesTemp[i][j].indexOf('\n', lastPosition + 1) ;
                //��������� ����� ��� ���� ������ ��� ������
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
        
        //��������� ��� �������������
        return new String(buffer) ;
    }
    
    
    /**
     * ��������� ��� ������������� �� ��������. K�� ��� �� GSM �������� �� Unicode
     * (�� MIDP ��� ����������� �� ������ toUperCase ��� no Latine ����������)
     * @param unNormalizedString � ������������ ���� ���������.
     * @return � ����������� �� Unicode �������� ��������.
     * @since 3.0
     */
    private String stringNormalize(String unNormalizedString){
    	//return unNormalizedString.toUpperCase().replace('�', '�').replace('�', '�').replace('�', '�').replace('�', '�').replace('�', '�').replace('�', '�').replace('�', '�').replace('�', '�').replace('�', '�').replace('�', '�').replace('�', '�').replace('�', '�').replace('�', '�').replace('�', '�').replace('A', '�').replace('B', '�').replace('E', '�').replace('H', '�').replace('I', '�').replace('K', '�').replace('M', '�').replace('N', '�').replace('O', '�').replace('P', '�').replace('T', '�').replace('X', '�').replace('Y', '�').replace('\u0010', '�').replace('\u0012', '�').replace('\u0013', '�').replace('\u0014', '�').replace('\u0015', '�').replace('\u0016', '�').replace('\u0017', '�').replace('\u0018', '�').replace('\u0019', '�').replace('\u0020', '�').replace('�', '�').replace('�', '�').replace('�', '�').replace('�', '�').replace('�', '�').replace('�', '�').replace('�', '�').replace('�', '�').replace('�', '�').replace('�', '�').replace('�', '�').replace('�', '�').replace('�', '�').replace('�', '�').replace('�', '�').replace('�', '�').replace('�', '�').replace('�', '�').replace('�', '�').replace('�', '�').replace('�', '�').replace('�', '�').replace('�', '�').replace('�', '�').replace('�', '�') ;
                
        char[] strArray = unNormalizedString.toUpperCase().toCharArray() ;
        
        for(int i=0; i<strArray.length; i++){
            if(strArray[i] == '�' || strArray[i] == '�' || strArray[i] == '�' || strArray[i] == '\u0041')  strArray[i] = '�' ;   
            else if(strArray[i] == '�' || strArray[i] == '\u0042')  strArray[i] = '�' ;   
            else if(strArray[i] == '�' || strArray[i] == '\u0013')  strArray[i] = '�' ;   
            else if(strArray[i] == '�' || strArray[i] == '\u0010')  strArray[i] = '�' ;   
            else if(strArray[i] == '�' || strArray[i] == '�' || strArray[i] == '�' || strArray[i] == '\u0045')  strArray[i] = '�' ;   
            else if(strArray[i] == '�' || strArray[i] == '\u005A')  strArray[i] = '�' ;   
            else if(strArray[i] == '�' || strArray[i] == '�' || strArray[i] == '�' || strArray[i] == '\u0048')  strArray[i] = '�' ;   
            else if(strArray[i] == '�' || strArray[i] == '\u0019')  strArray[i] = '�' ;   
            else if(strArray[i] == '�' || strArray[i] == '�' || strArray[i] == '�' || strArray[i] == '\u0049')  strArray[i] = '�' ;   
            else if(strArray[i] == '�' || strArray[i] == '\u004B')  strArray[i] = '�' ;   
            else if(strArray[i] == '�' || strArray[i] == '\u0014')  strArray[i] = '�' ;   
            else if(strArray[i] == '�' || strArray[i] == '\u004D')  strArray[i] = '�' ;   
            else if(strArray[i] == '�' || strArray[i] == '\u004E')  strArray[i] = '�' ;   
            else if(strArray[i] == '�' || strArray[i] == '\u0020')  strArray[i] = '�' ;   
            else if(strArray[i] == '�' || strArray[i] == '�' || strArray[i] == '�' || strArray[i] == '\u004F')  strArray[i] = '�' ;   
            else if(strArray[i] == '�' || strArray[i] == '\u0016')  strArray[i] = '�' ;   
            else if(strArray[i] == '�' || strArray[i] == '\u0050')  strArray[i] = '�' ;   
            else if(strArray[i] == '�' || strArray[i] == '�' || strArray[i] == '\u0018')  strArray[i] = '�' ;   
            else if(strArray[i] == '�' || strArray[i] == '\u0054')  strArray[i] = '�' ;   
            else if(strArray[i] == '�' || strArray[i] == '�' || strArray[i] == '�' || strArray[i] == '\u0059')  strArray[i] = '�' ;   
            else if(strArray[i] == '�' || strArray[i] == '\u0012')  strArray[i] = '�' ;   
            else if(strArray[i] == '�' || strArray[i] == '\u0058')  strArray[i] = '�' ;   
            else if(strArray[i] == '�' || strArray[i] == '\u0017')  strArray[i] = '�' ;   
            else if(strArray[i] == '�' || strArray[i] == '�' || strArray[i] == '�' || strArray[i] == '\u0015')  strArray[i] = '�' ;  
        }
        
        return new String(strArray, 0, strArray.length) ;
    }     
}
