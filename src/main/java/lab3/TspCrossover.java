package lab3;

import org.uncommons.watchmaker.framework.operators.AbstractCrossover;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TspCrossover extends AbstractCrossover<TspSolution> {
    protected TspCrossover() {
        super(1);
    }

    private static final double orderedCrossoverCoefficient = 0.9;

    protected List<TspSolution> mate(TspSolution p1, TspSolution p2, int i, Random random) {
        ArrayList<TspSolution> children = new ArrayList<TspSolution>();
        // your implementation:
        if (random.nextDouble() < orderedCrossoverCoefficient) {
            int[] p1SolutionPath = p1.getSolutionPath();
            int[] p2SolutionPath = p2.getSolutionPath();
            int solutionSize = p1SolutionPath.length;
            int one_pos = random.nextInt(solutionSize-1);
            int sec_pos = random.nextInt(solutionSize-1);

            if (sec_pos < one_pos) {
                int temp = sec_pos;
                sec_pos = one_pos;
                one_pos = temp;
            }
            if (sec_pos == one_pos) {
                sec_pos += 1;
            }

            int[] tmp1 = new int[solutionSize];
            int[] tmp2 = new int[solutionSize];


            List<Integer> positions1 = new ArrayList<>(sec_pos - one_pos);
            List<Integer> positions2 = new ArrayList<>(sec_pos - one_pos);
            for (int j = one_pos; j < sec_pos; j++) {
                tmp1[j] = p1SolutionPath[j];
                tmp2[j] = p2SolutionPath[j];
                positions1.add(p1SolutionPath[j]);
                positions2.add(p2SolutionPath[j]);
            }
            int p1_pointer = sec_pos;
            int p2_pointer = sec_pos;
            for (int j = sec_pos; j < sec_pos + (solutionSize - (sec_pos - one_pos)); j++) {
                while (positions1.contains(p2SolutionPath[((p2_pointer < solutionSize) ? p2_pointer : p2_pointer - solutionSize)])) {
                    p2_pointer += 1;
                }
                tmp1[((j < solutionSize) ? j : j - solutionSize)] = p2SolutionPath[((p2_pointer < solutionSize) ? p2_pointer : p2_pointer - solutionSize)];
                p2_pointer += 1;
                while (positions2.contains(p1SolutionPath[((p1_pointer < solutionSize) ? p1_pointer : p1_pointer - solutionSize)])) {
                    p1_pointer += 1;
                }
                tmp2[((j < solutionSize) ? j : j - solutionSize)] = p1SolutionPath[((p1_pointer < solutionSize) ? p1_pointer : p1_pointer - solutionSize)];
                p1_pointer += 1;
            }

            TspSolution p1_new = new TspSolution(solutionSize);
            p1_new.setSolutionPath(tmp1);
            TspSolution p2_new = new TspSolution(solutionSize);
            p2_new.setSolutionPath(tmp2);
            children.add(p1_new);
            children.add(p2_new);
        } else {
            children.add(p1);
            children.add(p2);
        }
        return children;
    }
}
