/**
 * 
 */
package pattern_matching;


/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class PatternMatchingAlgorithm {

	public static int bruteForceMatch(String text, String pattern){
		int n = text.length();
		int m = pattern.length();
		int j=0;
		for(int i=0; i<n-m && j!=m; i++){
			j=0;
			while(j<m && text.charAt(i+j)==pattern.charAt(j))
				j++;
			if(j==m)
				return i;
		}
		return -1;
	}

	public static int boyerMooreMatch(String text, String pattern){
		int[] lastOcc = lastOccurrenceFunction(pattern);
		int m = pattern.length();
		int i = m-1;
		int j = m-1;
		int n = text.length();
		while(i<=n-1){
			if(text.charAt(i)==pattern.charAt(j)){
				if(j==0)
					return i;
				else{
					i--;
					j--;
				}
			}else{
				int l = -1;
				try {
					l = lastOcc[(int)text.charAt(i)];
				} catch (Exception e) {
					l = -1;
				}
				i = i+m-Math.min(j, 1+l);
				j = m-1;
			}
		}
		return -1;
	}

	public static int knuthMorrisPrattMatch(String text, String pattern){
		int[] f = failureFunction(pattern);
		int i = 0;
		int j = 0;
		int n = text.length();
		int m = pattern.length();
		while(i<n){
			if(text.charAt(i)==pattern.charAt(j)){
				if(j==m-1)
					return i-j;
				else{
					i++;
					j++;
				}
			}
			else{
				if(j>0)
					j=f[j-1];
				else
					i++;
			}
		}
		return -1;
	}

	private static int[] failureFunction(String p){
		int m = p.length();
		int[] f = new int[m];
		int i = 1;
		int j = 0;
		while(i<m){
			if(p.charAt(i)==p.charAt(j)){
				f[i]=j+1;
				i++;
				j++;
			}else if(j>0){
				j=f[j-1];
			}
			else{
				f[i]=0;
				i++;
			}
		}
		return f;
	}

	private static int[] lastOccurrenceFunction(String s){
		int[] lof = new int[255];
		char[] ch = s.toCharArray();
		for(int i=0; i<ch.length; i++){
			lof[(int)ch[i]] = i;
		}
		return lof;
	}
	
	public static void main(String[] args) {
		System.out.println(PatternMatchingAlgorithm.bruteForceMatch(
				"the pattern more intelligently", "patt"));
	}

}
