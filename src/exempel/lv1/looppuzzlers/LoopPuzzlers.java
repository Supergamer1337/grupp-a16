package exempel.lv1.looppuzzlers;

import static java.lang.System.out;

/*
 *  Some puzzlers
 *  General improvement of programming skills
 *
 *  See:
 *  - LoopPuzzler
 */
public class LoopPuzzlers {

    public static void main(String[] args) {
        new LoopPuzzlers().program();
    }

    enum Sub {
        A,
        B,
        C
    }

    void program() {
        for (Sub subexercise : Sub.values()) {
            out.println("Now printing sub exercise: " + subexercise);
            exe_sub(subexercise);
            out.println();
        }
    }

    private void exe_sub(Sub sub) {
        switch (sub){
            case A -> subExerciseA(10);
            case B -> subExerciseB(15);
            case C -> subExerciseC(15);
            default -> out.println("Couldn't find sub exercise");
        }
    }

    private void subExerciseA(int limit) {
        for (int i = 1; i <= limit; i++) {
            for (int j = 1; j <= limit; j++) {
                out.printf("%3d ", i * j);
            }
            out.println();
        }
    }

    private void subExerciseB(int limit) {
        for (int i = 1; i <= limit; i++) {
            for (int j = 0; j < i; j++) {
                out.print("X");
            }
            out.println();
        }
    }

    private void subExerciseC(int limit) {
        for (int i = 0; i < limit; i++) {
            for (int j = 0; j < i; j++) {
                out.print(" ");
            }
            out.println("XXXXXXXXXX");
        }
    }
}
