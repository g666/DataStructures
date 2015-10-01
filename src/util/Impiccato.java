package util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

import util.Util;

/**
 * 
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *Struttura naive statica con due array (n*lp)
 *Struttura ordinata (log*costruzione)
 *Struttura hash con ricerca O(1)
 *
 */
public class Impiccato {

	private char[] word;
	private char[] hidden_word;
	private InputStream in;
	private OutputStream out;
	private char hiddenChar = '_';

	public Impiccato(int wordLength, InputStream in, OutputStream out) {
		this.in = in;
		this.out = out;
		word = Util.generateRandomString(wordLength).toCharArray();
		init();
	}

	public Impiccato(String word) {
		this.word = word.toCharArray();
	}

	private void init(){
		hidden_word = new char[word.length];
		for(int i=0; i<hidden_word.length; i++)
			hidden_word[i] = hiddenChar;
	}

	public Impiccato(String word, InputStream in, OutputStream out) {
		this.in = in;
		this.out = out;
		this.word = word.toCharArray();
		init();
	}

	public void play(int k) throws IOException{
		for(int i=0; i<k; i++)
			readChar();
		out.write("Insert hidden word\n".getBytes());
		byte[] b = new byte[word.length];
		in.read(b);
		if(new String(b).equals(new String(word)))
			out.write("You win\n".getBytes());
		else
			out.write("You lose\n".getBytes());
		out.flush();
		out.close();
		in.close();
	}

	public void automaticPlay(int times, int p){
		Random r = new Random();
		for(int t=0; t<times; t++){
			System.out.println("Game: "+(t+1));
			init();
			char[] cs = new char[p];
			for(int i=0; i<p; i++){
				char c = (char)(r.nextInt(100)<50?
						r.nextInt(90-65)+65:r.nextInt(122-97)+97);
				cs[i] = c;
				for(int j=0; j<word.length; j++){
					if(word[j]==c)
						hidden_word[j]=c;
				}
			}
			String word_s = new String(word);
			String hiddenWord_s = new String(hidden_word);
			System.out.println("\tword: "+word_s);
			System.out.println("\thidden word: "+hiddenWord_s);
			System.out.println("\tInserted char: "+new String(cs));
			System.out.println("\t"+(word_s.equals(hiddenWord_s)?"win":"lose"));
		}
	}

	private void readChar() throws IOException{
		out.write("Insert char: ".getBytes());
		byte[] b = new byte[512];
		in.read(b);
		char c = (char) b[0];
		int k=0;
		for(int i=0; i<word.length; i++){
			if(word[i]==c)
			{
				k++;
				hidden_word[i]=c;
			}
		}
		out.write(("\nThere are "+k+" "+c+"\n").getBytes());
		for(int i=0; i<hidden_word.length; i++)
		{
			out.write((hidden_word[i]+" ").getBytes());
		}
		out.write("\n\n".getBytes());
		out.flush();
	}

	public static void main(String[] args) throws Exception{
		Impiccato i = new Impiccato(5, System.in, System.out);
//		long start = System.currentTimeMillis();
//		i.automaticPlay(1, 7);
//		long end = System.currentTimeMillis();
//		System.out.println((end-start)/1000);
		i.play(3);
	}
}
