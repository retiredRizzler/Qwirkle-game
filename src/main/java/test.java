import java.util.Scanner;
public class test {
    public static void main(String[] args) {
        System.out.println("BIENVENUE SUR LA CALCULETTE ");
        System.out.println("Entrez un premier chiffre");
        Scanner c = new Scanner(System.in);
        int premierChiffre = c.nextInt();
        System.out.println("Entrez un deuxième chiffre");
        int deuxiemeChiffre = c.nextInt();
        System.out.println("L'addition de ces deux nombre fait : " + (premierChiffre + deuxiemeChiffre));



    }
}
