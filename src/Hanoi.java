import java.util.Arrays;

public class Hanoi {
    public static void main(String[] args) {

        // change the number of Disks here
        final int towerOfHanoiDifficulty = 4;

        System.out.println("Tower of Hanoi");
        int[] towerOne = new int[towerOfHanoiDifficulty];
        int[] towerTwo = new int[towerOfHanoiDifficulty];
        int[] towerThree = new int[towerOfHanoiDifficulty];

        //tower initialize 0==empty [0]==bottom [towerOfHanoiDifficulty-1]==top
        for (int i = towerOfHanoiDifficulty - 1; i >= 0; i--) {
            towerOne[i] = towerOfHanoiDifficulty - i;
            towerTwo[i] = 0;
            towerThree[i] = 0;
        }
        printTowers(towerOne, towerTwo, towerThree);

        //Test of checkRule
        /*if (checkRule(towerOne)) {
            System.out.println("One OK");
        }
        if (checkRule(towerTwo)) {
            System.out.println("Two OK");
        }
        if (checkRule(towerThree)) {
            System.out.println("Three OK");
        }*/
        System.out.println("Starting recusive algorithmus: ...\n");
        towerHanoi(towerOne, towerTwo, towerThree, towerOfHanoiDifficulty);

        printTowers(towerOne, towerTwo, towerThree);
    }

    public static void printTowers(int[] towerOne, int[] towerTwo, int[] towerThree) {
        System.out.println("------------");
        System.out.println(Arrays.toString(towerOne));
        System.out.println(Arrays.toString(towerTwo));
        System.out.println(Arrays.toString(towerThree));
        System.out.println("------------");
    }

    public static boolean checkRule(int[] tower) {
        //check rules of a singel Tower. if a bigger Disk is on a smaller one it returns false!
        if (tower.length != 0) {
            int check = tower.length + 1;
            for (int i = 0; i < tower.length; i++) {
                if (check < tower[i]) {
                    return false;
                }
                check = tower[i];
            }
        }
        // of the tower is empty or the disk are in correct order return true
        return true;
    }

    public static void towerHanoi(int[] towerOne, int[] towerTwo, int[] towerThree, int diskNr) {
        // check if the algorithmus works with the rule set
        if (!checkRule(towerOne) || !checkRule(towerTwo) || !checkRule(towerThree)) {
            System.out.println("violation of the rules!");
        }
        if (diskNr == 0) {
            return;
        }
        // the secound and the thrid towers change positions because: there are two ways even and odd number of disks
        // if diskNr orgin is even than the first disk is placed on the secound Tower
        // else on the third tower
        towerHanoi(towerOne, towerThree, towerTwo, diskNr - 1);

        // move the disc
        int threeIdx = 0;
        //find the lowest index of towerThree without disk
        for (int i = 0; i < towerThree.length; i++) {
            if (towerThree[i] == 0) {
                threeIdx = i;
                break;
            }
        }
        //find the lowest index of towerOne without disk
        //if the tower is full then idx is length of the tower (maxIdx+1)
        int oneIdx = towerOne.length;
        for (int i = 0; i < towerOne.length; i++) {
            if (towerOne[i] == 0) {
                oneIdx = i;
                break;
            }
        }
        // add disk to towerThree
        towerThree[threeIdx] = towerOne[oneIdx - 1];
        // remove disk form towerOne
        towerOne[oneIdx - 1] = 0;

        // to print the tower is not presentable for the user because the towers switch in the Method that is the reason to use checkRule at the begin of the methode
        //printTowers(towerOne, towerTwo, towerThree);

        // the disk on the secound tower get moved to the third tower
        towerHanoi(towerTwo, towerOne, towerThree, diskNr - 1);
        return;
    }
}