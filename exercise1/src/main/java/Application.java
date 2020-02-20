public class Application {

    public static void main(String[] args) {
        new ExerciseHandler(
                new String[]{"input.csv","input.json","input.xml","input.yml"},
                "target.out",
                "Carlos")
                .executeProcess();
    }
}
