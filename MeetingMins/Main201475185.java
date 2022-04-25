import javax.swing.text.html.HTMLDocument;
import javax.xml.crypto.Data;
import java.io.File;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.lang.reflect.Array;
import java.util.*;


public class Main201475185{

    public static ArrayList<String> ReadData(String pathname) {
        ArrayList<String> strlist = new ArrayList<String>();
        try {

            File filename = new File(pathname);
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename));
            BufferedReader br = new BufferedReader(reader);
            int j = 0;
            String line = "";
            while ((line = br.readLine()) != null) {
                strlist.add(line);
            }
            return strlist;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strlist;
    }

    public static ArrayList<ArrayList<Integer> > DataWash(ArrayList<String> Datalist) {
        ArrayList<ArrayList<Integer> > AIS = new ArrayList<ArrayList<Integer> >();
        ArrayList<Integer> IS = new ArrayList<Integer>();
        for (int i = 0; i < Datalist.size(); i++) {
            String Tstr = Datalist.get(i);
            if (Tstr.equals("A") == false) {
                IS.add(Integer.parseInt(Tstr));
            }
            if (Tstr.equals("A")) {
            	ArrayList<Integer> elemAIS = new ArrayList<Integer>(IS);
                AIS.add(elemAIS);
                IS.clear();
            }
        }
        return AIS;
    }



//%%%%%%%%%%%%%%%%%%%%%%% Procedure LongestRange() that will contain your code:
    

    public static int LongestRange(int[] A, int n){

        /* Input is array of input sequence (a_1 <= a_2 <= ... <= a_n) as A[0,1,...,n-1], that is,
        a_1 = A[0], a_2 = A[1], ..., a_n = A[n-1].
        n = number of integers in sequence A
        This procedure should return the value of the longest range (>= 1) or 0 if there is no peak.
        It should NOT return the respective longest range of peaks!
        */


        /* Here should be the description of the main ideas of your solution:
        describe the recursive relation used in your dynamic programming
        solution and outline how you implement it sequentially in your code below.

        SOME NOTATION: s.t. is abbreviation of "such that"
                       a <= b (a is smaller than or equal to b)
                       a >= b (a is greater than or equal to b)
                       max [ a , b ] denotes the larger among a and b
                       Given an array T[1,...,n] 
                       then M = max_{k : some condition C(k) holds} [ T[k] ],
                       M denotes the largest value T[k] over all indices k 
                       such that condition C(k) holds.
                       Here we denote by {k : some condition C(k) holds} the set of all 
                       elements k which make the condition C(k) hold true.
     
        ...
        ...

        Let us first define the dynamic programming (DP) array: allPeaks

        DP array is first initialised as follows: starts empty but is filled with all the peaks in a given Array. This cuts the processing down to
        just 1 pass over the array to obtain all the peaks to be re-used

        DP can be solved recursively as follows process all peaks, start at peak 1, then try the following peak and after that full pass of the map then remove it then do another pass until
        all possibilities explored at location 1, then start at location 2,3... where n is the current pass the algorithm is on. you choose Max{allPeaksMax[n-1]: allPeaks[n]} do not need a table for
        storing max, just a variable

        The main ideas of a sequential implementation of the above recursive solution 
        that shows how to fill in the DP array is as follows ...

        loop through A to fill the peaks dynamic table
        iterate starting at each position in the dynamic table and exhaust possibilities from that

   
        */

        /* Here should be the statement and description of the running time
        analysis of your sequential implementation: describe how the running time depends on
        n (length of the input sequence), and give a short argument. Use the big-O notation.

        ...

        Summarising, this argument shows that the worst case running time of this whole implementation is 
        O(???).

        ...

        */


        /* get all the peaks (regardless of condition) and then at each i, start at that index
        and work out range of peaks ie start at peak 1, then 2, then 3....
        */

        // LinkedHashmap allows for key value pairs (index, peak) in insertion order, not ordered on key like traditional hashmaps
        LinkedHashMap<ArrayList<Integer>, ArrayList<Integer>> allPeaks = new LinkedHashMap<>();
        // subPeaks allows to explore all possibilities not just the adjacent value
        LinkedHashMap<ArrayList<Integer>, ArrayList<Integer>> subPeaks = new  LinkedHashMap<>();

        // used array lists as cannot use primitive types in collections
        int peakRange = 0;
        ArrayList<Integer> peak;
        ArrayList<Integer> index;
        // look at groups of 3 as peaks can only be 3 large and check if peak, if so add to allPeaks which is the dynamic table
        for (int i = 2; i < A.length; i++) {
            peak = new ArrayList<>();
            index = new ArrayList<>();
            if (A[i - 1] > A[i - 2] && A[i - 1] > A[i]) {
                peak.add(A[i - 2]);
                peak.add(A[i - 1]);
                peak.add(A[i]);
                index.add(i - 2);
                index.add(i - 1);
                index.add(i);
                allPeaks.put(index, peak);
            }
        }


        // just return if no peaks, reduces processing
        if (allPeaks.size() == 0) {
            return peakRange;
        }

        // calculating the largest range of disjoint peaks
        int tempPeakRange;
        // go through starting at each peak
        while (allPeaks.size() > 0) {
            boolean isfirst = true;
            ArrayList<Integer> firstIndex = new ArrayList<>();
            ArrayList<Integer> firstPeak = new ArrayList<>();
            // clone allPeaks into subPeaks
            subPeaks.putAll(allPeaks);

            //go through subPeaks. We can store the first value meaning we can remove values infront using this sub-map
            while (subPeaks.size() > 0) {
                tempPeakRange = 0;
                // need to figure what is next to delete aka next value that os being passed
                boolean setToDelete = false;

                // comparison and deletion indexes and peaks
                ArrayList<Integer> lastIndex = new ArrayList<>();
                ArrayList<Integer> lastPeak = new ArrayList<>();
                ArrayList<Integer> indexToRemove = new ArrayList<>();
                ArrayList<Integer> nextIndex;
                ArrayList<Integer> nextPeak;

                // for each element in subPeaks
                for (Map.Entry<ArrayList<Integer>, ArrayList<Integer>> ele : subPeaks.entrySet()) {
                    // is this the first value to be checked, if so store it
                    // don't want to delete first element as we want to explore all options at that element first so this changes to all the elements infront of the first one
                    if (setToDelete == false) {
                        indexToRemove = ele.getKey();
                        setToDelete = true;
                    }

                    // if its first then hold it so can be deleted from allPeaks
                    if (isfirst) {
                        isfirst = false;
                        // need this to get rid of first index after each pass of allPeaks
                        firstIndex = ele.getKey();
                        firstPeak = ele.getValue();
                        lastIndex = firstIndex;
                        lastPeak = firstPeak;
                        tempPeakRange++;
                        continue;
                    }

                    nextPeak = ele.getValue();
                    nextIndex = ele.getKey();
                    // if we have no last peak then we must be on a new pass so start from our current first element
                    if (lastIndex.size() == 0) {
                        tempPeakRange++;
                        lastIndex = firstIndex;
                        lastPeak = firstPeak;
                    }
                    // rule 3, index must be seperated by 2 points
                    if (lastIndex.get(2) != nextIndex.get(0)) {
                        // rule for that first value of next peak must lie in the slope of the previous peak
                        if ((lastPeak.get(1) >= nextPeak.get(0)) && (lastPeak.get(2) <= nextPeak.get(0))) {
                            lastPeak = nextPeak;
                            lastIndex = nextIndex;
                            tempPeakRange++;
                        }
                    }

                }
                // only take the longest range, checking for max
                if (tempPeakRange > peakRange) {
                    peakRange = tempPeakRange;
                }
                // remove the next index from sub peaks
                subPeaks.remove(indexToRemove);
            }
            // remove out first element from allPeaks and reclone back in loop
            allPeaks.remove(firstIndex);
        }

        return peakRange;

    } // end of procedure LongestRange()




    public static int Computation(ArrayList<Integer> Instance, int opt){
        // opt=1 here means option 1 as in -opt1, and opt=2 means option 2 as in -opt2
        int NGounp = 0;
        int size = 0;
        int Correct = 0;
        size = Instance.size();

        int [] A = new int[size-opt];
        // NGounp = Integer.parseInt((String)Instance.get(0));
        NGounp = Instance.get(0); // NOTE: NGounp = 0 always, as it is NOT used for any purpose
                                           // It is just the first "0" in the first line before instance starts.
        for (int i = opt; i< size;i++ ){
            A[i-opt] = Instance.get(i);
        }
        int Size =A.length;
        if (NGounp >Size ){
            return (-1);
        }
        else {
            //Size
            int R = LongestRange(A,Size);
            return(R);
        }
    }

    public static String Test;


    public static void main(String[] args) {
    	if (args.length == 0) {
    		String msg = "Rerun with flag: " +
    		"\n\t -opt1 to get input from dataOne.txt " + 
    		"\n\t -opt2 to check results in dataTwo.txt";
    		System.out.println(msg);
    		return;
    	}
        Test = args[0];
        int opt = 2;
        String pathname = "dataTwo.txt";
        if (Test.equals("-opt1")) {
            opt = 1;
            pathname = "dataOne.txt";
        }


        ArrayList<String> Datalist = new ArrayList<String>();
        Datalist = ReadData(pathname);
        ArrayList<ArrayList<Integer> > AIS = DataWash(Datalist);

        int Nins = AIS.size();
        int NGounp = 0;
        int size = 0;
        if (Test.equals("-opt1")) {
            for (int t = 0; t < Nins; t++) {
                int Correct = 0;
                int Result = 0;
                ArrayList<Integer> Instance = AIS.get(t);
                Result = Computation(Instance, opt);
                System.out.println(Result);
            }
        }
        else {
            int wrong_no = 0;
            int Correct = 0;
            int Result = 0;
            ArrayList<Integer> Wrong = new ArrayList<Integer>();
            for (int t = 0; t < Nins; t++) {
                ArrayList<Integer> Instance = AIS.get(t);
                Result = Computation(Instance, opt);
                System.out.println(Result);
                Correct = Instance.get(1);
                if (Correct != Result) {
                    Wrong.add(t+1);
                    wrong_no=wrong_no+1;
                }
            }
            if (Wrong.size() > 0) {System.out.println("Index of wrong instance(s):");}
            for (int j = 0; j < Wrong.size(); j++) {
                System.out.print(Wrong.get(j));
                System.out.print(",");

                /*ArrayList Instance = (ArrayList)Wrong.get(j);
                for (int k = 0; k < Instance.size(); k++){
                    System.out.println(Instance.get(k));
                }*/
            }
            System.out.println("");
            System.out.println("Percentage of correct answers:");
            System.out.println(((double)(Nins-wrong_no) / (double)Nins)*100);

        }

    }
}
