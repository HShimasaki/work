package com.ripplex;

/**
* Unix時間から西暦の年月日を取得するクラス(※2038年問題)
* 
* @author H.Shimasaki
*
*/
public class UnixTime {
	
	//Unix時間
	private int UnixTime = 0; 	
	//クラス生成時UnixTimeより算出する(※setNowDate内で設定)
	private int NowYear = 0; 
	private int NowMonth = 0; 
	private int NowDay = 0; 
	private int NowHour = 0; 
	private int NowMin = 0; 
	private int NowSec = 0; 
	
	//**-計算時に使用-**
	
	//1日の秒数
	private final int SEC_PER_DAY = 86400;
	//1時間の秒数
	private final int SEC_PER_HOUR = 3600;
	//1分の秒数
	private final int SEC_PER_MIN = 60;
	//1年の秒数
	private final int SEC_PER_YEAR_NRM = SEC_PER_DAY * 365;
	//1年の秒数(うるう年) 
	private final int SEC_PER_YEAR_LEP = SEC_PER_DAY * 366;
		
	//Unix時間の開始年
	private final int UnixStaYear = 1970;
	
	//各月の最大秒数
	private final int[] SEC_PER_MONTHS_NRM = {SEC_PER_DAY * 31,	//1月
												SEC_PER_DAY * 28,	//2月
												SEC_PER_DAY * 31,	//3月
												SEC_PER_DAY * 30,	//4月
												SEC_PER_DAY * 31,	//5月
												SEC_PER_DAY * 30,	//6月
												SEC_PER_DAY * 31,	//7月
												SEC_PER_DAY * 31,	//8月
												SEC_PER_DAY * 30,	//9月
												SEC_PER_DAY * 31,	//10月
												SEC_PER_DAY * 30,	//11月
												SEC_PER_DAY * 31}; 	//12月
	private final int[] SEC_PER_MONTHS_LEP = {SEC_PER_DAY * 31,	//1月
												SEC_PER_DAY * 29,	//2月
												SEC_PER_DAY * 31,	//3月
												SEC_PER_DAY * 30,	//4月
												SEC_PER_DAY * 31,	//5月
												SEC_PER_DAY * 30,	//6月
												SEC_PER_DAY * 31,	//7月
												SEC_PER_DAY * 31,	//8月
												SEC_PER_DAY * 30,	//9月
												SEC_PER_DAY * 31,	//10月
												SEC_PER_DAY * 30,	//11月
												SEC_PER_DAY * 31}; 	//12月


	/**
	  * -UnixTimeより年月日時分を算出する
	  * 
	  * @param utime 計算対象のUnix時間
	  */
	private void setNowDate(int utime) {
		//結果用
		int wYear = 0;
		int wMonth = 0;
		int wDay = 0;
		int wHour = 0;
		int wMin = 0;
		int wSec = 0;
	
		int wUnixTime = UnixTime;	
		
		//年取得
		int FromStaYear = 0;
		int SEC_PER_YEAR = 0;
		int[] SEC_PER_MONTHS;
		while(true) {
			
			//対象年の秒数取得する(うるう年判定)
			if(isLeapYear(UnixStaYear + FromStaYear)) {
				SEC_PER_YEAR = SEC_PER_YEAR_LEP;
				SEC_PER_MONTHS = SEC_PER_MONTHS_LEP;
			}else {				
				SEC_PER_YEAR = SEC_PER_YEAR_NRM;			
				SEC_PER_MONTHS = SEC_PER_MONTHS_NRM;
			}
			
			//引けない場合は対象年
			if((wUnixTime - SEC_PER_YEAR) < 0) {
				wYear = UnixStaYear + FromStaYear; 
				break;								
			}else {
				FromStaYear++;				
				wUnixTime = wUnixTime - SEC_PER_YEAR;
			}			
		}
		
		//月取得
		for(int i=0; i<11; i++){
			if ((wUnixTime - SEC_PER_MONTHS[i]) < 0) {
				wMonth = i + 1;
				break;
			}else {
				wUnixTime = wUnixTime -  SEC_PER_MONTHS[i];				
			}			
		}

		//日取得
		if(wUnixTime != 0) {
			wDay = (wUnixTime / SEC_PER_DAY) + 1;  			
			wUnixTime = wUnixTime - (wDay * SEC_PER_DAY); 				

			//時取得
			if(wUnixTime != 0) {
				wHour = wUnixTime / SEC_PER_HOUR;				
				wUnixTime = wUnixTime - (wHour * SEC_PER_HOUR); 				
		
				//分取得
				if(wUnixTime != 0) {
					wMin = wUnixTime / SEC_PER_MIN;								
					wUnixTime = wUnixTime - (wMin * SEC_PER_MIN);
					
					//秒取得
					wSec = wUnixTime;					
				}else {
					wMin = 0;
					wSec = 0;			
				}						
			}else {
				wHour = 0;								
				wMin = 0;
				wSec = 0;			
			}			
		}else {
			wDay = 1;
			wHour = 0;
			wMin = 0;
			wSec = 0;			
		}
					
		//結果
		NowYear = wYear;
		NowMonth = wMonth;	
		NowDay = wDay;
		NowHour = wHour;
		NowMin = wMin;
		NowSec = wSec;		
	}
	
	/**
	 * -対象の西暦年がうるう年の場合trueを返す
	 * 
	 * @param Year 判定する西暦年
	 * @return true:うるう年/false:通常年
	 */
	private boolean isLeapYear(int Year) {		
		boolean Result = false;
		
		//西暦年が4で割り切れる年は閏年
		//ただし、西暦年が100で割り切れる年は平年
		//ただし、西暦年が400で割り切れる年は必ず閏年
		if(Year % 4 == 0 && Year % 100 != 0 || Year % 400 == 0) {
			Result = true;
		}else {
			Result = false;			
		}
			
		return Result;			
	}
	
	/**
	 * -UNIX時間「0」を保持するインスタンスを生成するコンストラクタ
	 */
	public UnixTime() {
		setUnixTime(0);
	}
	
	/**
	 * -UNIX時刻を符号あり32ビット整数の引数として受け取るコンストラクタ
	 * 
	 * @param utime 入力UNIX時刻
	 */
	public UnixTime(int utime) {
		setUnixTime(utime);
	}
	
	/**
	 * -時刻をUNIX時刻(符号あり32ビット整数)でセットします。
	 * 
	 * @param utime 入力UNIX時刻
	 */
	public void setUnixTime(int utime) {
		UnixTime = utime;
		setNowDate(utime);	
	}
	
	/**
	 * -保持しているUNIX時間を得るメソッド
	 * 
	 * @return 保持しているUNIX時間
	 */
	public int getUnixTime() {
		return UnixTime;
	}
	
	/**
	 * -保持しているUNIX時刻から、西暦の「年」を得るメソッド
	 * -西暦2000年であれば2000を返します
	 * 
	 * @return 西暦年
	 */
	public int getYear() {
		return NowYear;
	}
	
	/**
	 * -保持しているUNIX時刻から、西暦の「月」を得るメソッド
	 * -一月であれば1、二月であれば2 を返します
	 * 
	 * @return 西暦月
	 */
	public int getMonth() {
		return NowMonth;
	}
	
	/**
	 * -保持しているUNIX時刻から、西暦の「日」を得るメソッド
	 * -一日であれば1、二日であれば2 を返します
	 * 
	 * @return 西暦日
	 */
	public int getDay() {
		return NowDay;
	}
		
	/**
	 * -保持しているUNIX時刻から、「時」を得るメソッド
	 * 
	 * @return 時
	 */
	public int getHour() {
		return NowHour;
	}
	
	/**
	 * -保持しているUNIX時刻から、「分」を得るメソッド
	 * 
	 * @return 分
	 */
	public int getMin() {
		return NowMin;
	}
	
	/**
	 * -保持しているUNIX時刻から、「秒」を得るメソッド
	 * 
	 * @return 秒
	 */
	public int getSec() {
		return NowSec;
	}
}
