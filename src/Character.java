public class Character {
    public int maxStreak = 0;
    public int fastQuestions = 0;
    public int doubleQuestions = 0;
    public String name;
    public Character(int number){
        name = Analyzer.readCellData(3+number,1,2);
        int currentStreak = 0;
        for (int i = 0; i < Analyzer.questions; i++) {
            int questionPoints = Analyzer.readCellNumber(3+number,3+(i*2),2);
            if (questionPoints > 0) {
                currentStreak++;
                if (questionPoints > 1000) {
                    doubleQuestions++;
                    if (questionPoints > 1800) {
                        fastQuestions++;
                    }
                } else if (questionPoints > 900) {
                    fastQuestions++;
                }
            }
            else{
                if (currentStreak > maxStreak){
                    maxStreak = currentStreak;
                }
                currentStreak = 0;
            }
        }
        if (currentStreak > maxStreak){
            maxStreak = currentStreak;
        }
    }
}
