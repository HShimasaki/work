import com.ripplex.UnixTime;

public class Main {

	public static void main(String[] args) {
		System.out.println("☆単体テスト☆\n");

		System.out.println("① コンストラクタUnixTime()の確認");

		UnixTime uTime = new UnixTime();
	
		System.out.println("② getYear()の確認");
		System.out.println("③ getMonth()の確認");
		System.out.println("④ getDay()の確認");
		System.out.println("西暦年月日 : " + uTime.getYear() + "年 " + uTime.getMonth() + "月 " + uTime.getDay() + "日\n");
		
		uTime.setUnixTime(915148800);
		System.out.println("⑤ setUnixTime(915148800)の確認");
		System.out.println("西暦年月日 : " + uTime.getYear() + "年 " + uTime.getMonth() + "月 " + uTime.getDay() + "日\n");

		System.out.println("⑥ getUnixTime()の確認");
		System.out.println("Unix時間 :" + uTime.getUnixTime() + "\n");	

	
		System.out.println("⑦ コンストラクタUnixTime(uTime)の確認");
		System.out.println("⑧ intの取りうる範囲(最大)の確認");
		UnixTime uTimeMax = new UnixTime(2147483647);
		System.out.println("西暦年月日 : " + uTimeMax.getYear() + "年 " + uTimeMax.getMonth() + "月 " + uTimeMax.getDay() + "日\n");
	}
}
