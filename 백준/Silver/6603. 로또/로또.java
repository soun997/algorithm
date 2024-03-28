import java.util.*;

public class Main {
	
	static int K,S,cnt=0;
	static int[] num;
	static boolean[] check;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			K=sc.nextInt();	//개수
			
			if(K==0)
				break;
			num= new int[K];	//입력 담는 배열 
			check= new boolean[K];
			
			for(int i=0;i<K;i++)
				num[i]=sc.nextInt();
			
			f(0,0);
			System.out.println();
		}
		
	}// main()
	
	static void f(int dp,int start) {
		if(dp==6) {
			for(int i=0;i<K;i++){
				if(check[i]){
                    System.out.print(num[i]+" ");
                }
			}
			System.out.println();
		}
		
		for(int i=start;i<K;i++) {
			check[i]=true;
			f(dp+1,i+1);
			check[i]=false;
		}
		
	}//f()
	
}