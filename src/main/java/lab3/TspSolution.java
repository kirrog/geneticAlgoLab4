package lab3;

import java.util.Arrays;

public class TspSolution {
    // any required fields and methods
    private int[] solutionPath;

    public TspSolution(int size) {
        this.solutionPath = new int[size];
        for (int i = 0; i < size; i++) {
            this.solutionPath[i] = i + 1;
        }
    }

    public int[] getSolutionPath() {
        return solutionPath;
    }

    public void setSolutionPath(int[] solutionPath) {
        this.solutionPath = solutionPath;
    }

    @Override
    public String toString() {
        return Arrays.toString(this.solutionPath);
    }
}
