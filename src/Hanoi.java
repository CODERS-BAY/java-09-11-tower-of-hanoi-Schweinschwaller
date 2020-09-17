import java.util.Arrays;

public class Hanoi {
    public static void main(String[] args) {

        // change the number of Disks
        final int towerOfHanoiDifficulty = 8;
        System.out.println("Tower of Hanoi");

        int[] towerOne = new int[towerOfHanoiDifficulty];
        int[] towerTwo = new int[towerOfHanoiDifficulty];
        int[] towerThree = new int[towerOfHanoiDifficulty];

        //tower inizalisieren 0=empty [0]=bottom [towerOfHanoiDifficulty-1]=top
        for (int i = towerOfHanoiDifficulty - 1; i >= 0; i--) {
            towerOne[i] = towerOfHanoiDifficulty - i;
            towerTwo[i] = 0;
            towerThree[i] = 0;
        }
        System.out.println(Arrays.toString(towerOne));
        System.out.println(Arrays.toString(towerTwo));
        System.out.println(Arrays.toString(towerThree));

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
        moveDisk(towerOne, towerTwo, towerThree, towerOfHanoiDifficulty);

        System.out.println(Arrays.toString(towerOne));
        System.out.println(Arrays.toString(towerTwo));
        System.out.println(Arrays.toString(towerThree));
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

    public static void moveDisk(int[] towerOne, int[] towerTwo, int[] towerThree, int diskNr) {
        // check if the algorithmus works with the rule set
        if (!checkRule(towerOne) || !checkRule(towerTwo) || !checkRule(towerThree)) {
            System.out.println("rule problem");
        }
        if (diskNr == 0) {
            return;
        }
        // the secound and the thrid towers change positions.
        // if diskNr orgin is even than the first disk is placed on the secound Tower
        // else on the first tower
        moveDisk(towerOne, towerThree, towerTwo, diskNr - 1);
        // move the disc

        int threeIdx = 0;
        //find the lowest index of towerThree with out disk
        for (int i = 0; i < towerThree.length; i++) {
            if (towerThree[i] == 0) {
                threeIdx = i;
                break;
            }
        }
        //find the lowest index of towerOne with out disk
        //if the tower is full then idx is max Idx
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
        // the disk on the secound tower get moved to the third tower
        moveDisk(towerTwo, towerOne, towerThree, diskNr - 1);
    }
}
