
public class Palindromes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String sentence1 = "Madam I'm Adam!";
		System.out.println(sentence1);
		System.out.println("Palindromes: "+isPalindrome(sentence1));
		String sentence2 = "Sir, I'm Eve!";
		System.out.println(sentence2);
		System.out.println("Palindrome: " + isPalindrome(sentence2));
	}
	public static boolean isPalindrome(String text)
	{
		return isPalindrome(text,0,text.length()-1);
	}
	
	public static boolean isPalindrome(String text, int start, int end)
	{
		if(start>=end) {return true;}
		else
		{
			char last = Character.toLowerCase(text.charAt(end));
			char first = Character.toLowerCase(text.charAt(start));
			
			if(Character.isLetter(first)&&Character.isLetter(last))
			{
				if(first ==last)
				{
					return isPalindrome(text, start +1, end -1);
				}
				else
				{
					return false;
				}
				
			}
			else if(!Character.isLetter(last))
			{
				//remove that character
				return isPalindrome(text,start,end-1);
			}
			else
			{
			
				return isPalindrome(text,start+1,end);
			}
			
		}
		
	}
}
