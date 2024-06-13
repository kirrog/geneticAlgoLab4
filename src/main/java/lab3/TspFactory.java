package lab3;

import org.uncommons.watchmaker.framework.factories.AbstractCandidateFactory;

import java.util.Random;

public class TspFactory extends AbstractCandidateFactory<TspSolution> {

    int solutionSize;

    public TspFactory(int size) {
        this.solutionSize = size;
    }

    public TspSolution generateRandomCandidate(Random random) {
        TspSolution solution = new TspSolution(this.solutionSize);
        shuffleArray(solution.getSolutionPath());
        //your implementation
        return solution;
    }

    static void shuffleArray(int[] ar) {
        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }
}

