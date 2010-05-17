/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package elex;

/**
 *
 * @author iamedu
 */
public class Yytoken {
	public static final int SIMB    = 1;
	public static final int OR      = 2;
	public static final int MAS     = 3;
	public static final int ESTRE   = 4;
	public static final int INTER   = 5;
	public static final int RES     = 6;
	public static final int PAR_IZQ = 7;
	public static final int PAR_DER = 8;
	private String m_text;
	private int m_token;
	public Yytoken(int token, String text) {
		m_token = token;
     		m_text = new String(text);
	}
	public String getText() {
		return m_text;
	}
	public int getToken() {
		return m_token;
	}
	public String toString() {
		return "Yylex: " + m_token + " Yytex: " + m_text;
	}
}
