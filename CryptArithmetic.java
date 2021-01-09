import java.util.*;

class CryptArithmetic{
    static boolean solved = false;
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        String word1 = "", word2 = "", result = "";
        System.out.println("\nCRYPTARITHMETIC PUZZLE SOLVER");
        System.out.println("WORD1 + WORD2 = RESULT");
        System.out.print("Enter WORD1: ");
        word1 = in.next().toUpperCase();
        System.out.print("Enter WORD2: ");
        word2 = in.next().toUpperCase();
        System.out.print("Enter RESULT: ");
        result = in.next().toUpperCase();
        if(!word1.matches("^[A-Z]*$") || !word2.matches("^[A-Z]*$") || !result.matches("^[A-Z]*$"))
            System.out.println("\nOnly Letters allowed.");
        else if(result.length() > Math.max(word1.length(), word2.length()) + 1)
            System.out.println("\n0 Solutions!");
        else{
            Set<Character> set = new HashSet<Character>();
            for(char c:word1.toCharArray())
                set.add(c);
            for(char c:word2.toCharArray())
                set.add(c);
            for(char c:result.toCharArray())
                set.add(c);
            if(set.size() > 10){
                System.out.println("\n0 solutions!");
                System.exit(0);
            }
            List<Character> letters = new ArrayList<Character>(set);
            System.out.println("\nSolutions:");
            solve(letters, new ArrayList<Integer>(), new boolean[10], new String[]{word1, word2, result});
            if(!solved)
                System.out.println("\n0 solutions!");
        }
        in.close();
    }
    static void solve(List<Character> letters, List<Integer> values, boolean[] visited, String[] words){
        if(letters.size() == values.size()){
            TreeMap<Character,Integer> map = new TreeMap<Character,Integer>();
            for(int i=0;i<letters.size();i++)
                map.put(letters.get(i), values.get(i));
            if(map.get(words[0].charAt(0)) == 0 || map.get(words[1].charAt(0)) == 0 || map.get(words[2].charAt(0)) == 0)
                return;
            String word1 = "", word2 = "", res = "";
            for(char c: words[0].toCharArray())
                word1 += map.get(c);
            for(char c: words[1].toCharArray())
                word2 += map.get(c);
            for(char c: words[2].toCharArray())
                res += map.get(c);
            if((Integer.parseInt(word1) + Integer.parseInt(word2)) == Integer.parseInt(res)){
                System.out.println(word1 + " + " + word2 + " = " + res+"\t"+map);
                solved = true;
            }
        }
        for(int i=0;i<10;i++){
            if(!visited[i]){
                visited[i] = true;
                values.add(i);
                solve(letters, values, visited, words);
                values.remove(values.size() - 1);
                visited[i] = false;
            }
        }
    }
}