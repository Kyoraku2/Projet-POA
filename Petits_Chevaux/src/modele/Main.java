package modele;

public class Main {
	public static void main(String[] args) {
		Board b= new Board(16);
		b.init();
		System.out.println(b.toString());
	}
}
