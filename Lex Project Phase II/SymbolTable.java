import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedList;

public class SymbolTable {

    public static int stcnt[] = new int[100];
    public static int count;
	public SymbolTable(String filename)throws Exception{
	HashMap<Integer, LinkedList<String>> tmap = new HashMap<Integer, LinkedList<String>>();
        LinkedList numList[] = new LinkedList[5];
        for (int i = 0; i < 5; i++) {
            numList[i] = new LinkedList();
        }
        LinkedList numList0 = new LinkedList();
        LinkedList numList1 = new LinkedList();
        LinkedList numList2 = new LinkedList();
        LinkedList numList3 = new LinkedList();
        LinkedList numList4 = new LinkedList();
        String instring;
        for (int i = 0; i < 100; i++) {
            stcnt[i] = 1;
        }
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String temp;
        int brkcnt = 0, strtcnt = 0, tempnum;
        int counts = 0;
        while ((temp = br.readLine()) != null) {
		
			temp=temp.replaceAll("\\{","{ ");
			temp=temp.replaceAll("\\}"," }");
            String bre[] = ((temp.replaceAll("(?://.*)|(/\\*(?:.|[\\n\\r])*?\\*/)", "")).trim()).split(" ");
            for (String t : bre) {
                int count = find_current_scope(t);
                if (t.equals("{") || t.equals("OPEN") || t.equals("}") || t.equals("CLOSE")) {

                } else {
					
                    char tempn[] = t.toCharArray();
                    int te = 0;
                    for (char c : tempn) {
                        te += (int) c;
                    }
                    te = te % 5;
					String test="[-]*[0-9]*";
					if(t.matches(test)){
					}
                   else if (find_all_scope(t, count, te, numList, tmap)) {
                        insert(t, count, te, numList, tmap);
                    }
                }
            }
        }
        display(tmap);
    
		
	}
    public static void insert(String instring, int count, int te, LinkedList ll[], HashMap tmap) {
        ll[te].add(instring + ":" + count);
        tmap.put(te, ll[te]);
    }

    public static boolean find_all_scope(String instring, int count, int te, LinkedList ll[], HashMap tmap) {
        int ctr = 1;
        for (int i = 0; i < 5; i++) {
            LinkedList<String> lis = new LinkedList();
            lis = (LinkedList) tmap.get(i);
            if (lis == null) {
                return true;
            }
			instring.replaceAll("[{}()]","");
            for (int j = 0; j < lis.size(); j++) {
                String temp = lis.get(j).toString();
                if (temp.equals(instring + ":" + count) ||(instring==" ")) {
                    ctr = 0;
                    break;
                } else {
                    ctr = 1;
                }
            }
            if (ctr == 0) {
                return false;
            }
        }
        if (ctr == 1) {
            return true;
        }
        return false;

    }

    public static int find_current_scope(String t) {
        if (t.contains("{") || t.contains("OPEN")) {
            count++;
            while ((stcnt[count] == 0)) {
                count++;
            }
            if (stcnt[count] == 0) {
                count++;
            }
        } else if (t.contains("}") || t.contains("CLOSE")) {
            stcnt[count] = 0;
            count--;
			
            while (stcnt[count] == 0) {
                if (count < 0) {
                    System.out.println("The code input doesn't have a balanced brackets");
                } else {
                    count--;
                }
            }		
        }
        return (count);
    }

    public static void display(HashMap hm) {
        for (int i = 0; i < 5; i++) {
            LinkedList ll = new LinkedList();
            ll = (LinkedList) hm.get(i);
            if(ll==null){
                System.out.println("BLOCK"+i+"::"+"0");
                break;
            }
            System.out.print("BLOCK" + i + ":: ");
            for (Object ii : ll) {
               
                System.out.print(" --> " + (ii.toString()).replaceAll("[{}()]",""));
            }
            System.out.println();
        }
    }
}
