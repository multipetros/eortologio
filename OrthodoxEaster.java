import java.util.Calendar ;

/**
 * ���� ��� ����� ������������ ��� ��/���� ��� ����
 * '������' �� ����� ��� ��� ������������ ����.
 * (���������� ��� ���������� �����, ���� J2ME)
 * @author ������ ���������
 * @version 2.6 Mobile
 * @serial 20050217
 */
public class OrthodoxEaster {
	private int year ;
	private int month ;
	private int day ;
	
        /**
         * � ���������� ����������� ��� ��/���� ��� ������ �� �����
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
	 *�������������� ������� ���������� ��� ������������, ��� �� ������ ����.<br>
	 *<em>� ����������� ���������� �������� �� ��� ����� ����� ��� �������, ��� ����������� ��������� ���������</em>
         *@since 1.2
	 */	
	public OrthodoxEaster() {
                Calendar today = Calendar.getInstance();    //To java.util.Calendar ������������� �� java.util.Date ��� ��� ������ 2.0.0
                this.year = today.get(Calendar.YEAR)  ;
		algorithm();
	}

	/**
	 *������� ���������� ��� ������������ �� ��������� ��� �����, ��� �� ����� �� ����� �����������.<br>
	 *<em>� ����������� ���������� �������� �� ��� ����� ����� ��� �������, ��� ����������� ��������� ���������</em>
	 *@param year �� ���� ��� �� ����� �� ����� � �����������
         *@since 1.0.0
	 */
	public OrthodoxEaster(int year) {
		this.year = year ;
		algorithm();
	}
	
	/**
	 *������� ���������� ��� �����, ��� �� ����� �� ����� � ����������� ��� ���� '������' �� �����.<br>
	 *<em>� ����������� ���������� �������� �� ��� ����� ����� ��� �������, ��� ����������� ��������� ���������</em>
	 *@param year �� ���� ��� �� ����� �� ����� � �����������
         *@since 1.1
	 */
	public void setYear(int year){
		this.year = year ;
		algorithm();
	}
	
	/**
	 *@return �� ���� ��� ���� �����, ��� �� ����������� �� �����.
         *@since 1.1
	 */
	public int getYear(){ 
		return year ; 
	}
	
	/**
	 *@return � ����� ��� '������' �� �����, ��� �� ���� ��� ���� �����.
         *@since 1.0.0
	 */
	public int getMonth(){ 
		return month ; 
	}
	
	/**
	 *@return � ���� ��� '������' �� �����, ��� �� ���� ��� ���� �����.
         *@since 1.0
	 */
	public int getDay(){ 
		return day ; 
	}
	
        /**
         *@return ����������� ��� ����� Calendar �� ����������� ��� �� �����.
         *@since 2.5
         */
	public Calendar getCalendar(){
            Calendar calendar = Calendar.getInstance() ;
            calendar.set(Calendar.YEAR, this.year) ;
            calendar.set(Calendar.MONTH, this.month - 1) ;  //� �������� ��� ����� ������ ��� �� 0 ���� ���� java.util.Calendar
            calendar.set(Calendar.DAY_OF_MONTH, this.day) ;
            return calendar ;
        }
	
}
