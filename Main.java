import java.util.*;
class User {
    String name;
    String email;
    Map<String, Integer> coreSkills = new LinkedHashMap<>();
    Map<String, Integer> advancedSkills = new LinkedHashMap<>();
    Map<String, String> certifications = new LinkedHashMap<>();
    int totalScore;
    int normalizedScore;
    public User(String name, String email) {
        this.name = name;
        this.email = email;
        coreSkills.put("C Programming", 0);
        coreSkills.put("Data Structures", 0);
        coreSkills.put("OOPs in Java", 0);
        coreSkills.put("DAA", 0);
        advancedSkills.put("AI Fundamentals", 0);
        advancedSkills.put("Machine Learning", 0);
        advancedSkills.put("Full Stack Web", 0);
        advancedSkills.put("Cloud Computing", 0);
    }
    void takeCompetencyTest() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n-------------------------------------------------");
        System.out.println("            COMPETENCY ASSESSMENT TEST");
        System.out.println("-------------------------------------------------");
        int rawCoreScore = 0, rawAdvScore = 0;
        System.out.println("\n-- CORE SKILLS --");
        for (String skill : coreSkills.keySet()) {
            System.out.print("Rate your knowledge in " + skill + " (0-10): ");
            int score = getValidScore(sc);
            coreSkills.put(skill, score);
            rawCoreScore += score;
        }
        System.out.println("\n-- ADVANCED SKILLS --");
        for (String skill : advancedSkills.keySet()) {
            System.out.print("Rate your knowledge in " + skill + " (0-10): ");
            int score = getValidScore(sc);
            advancedSkills.put(skill, score);
            rawAdvScore += score;
        }
        totalScore = rawCoreScore + rawAdvScore;
        normalizedScore = (int)(((double) totalScore / 80.0) * 100);
    }
    int getValidScore(Scanner sc) {
        int score;
        while (true) {
            try {
                score = Integer.parseInt(sc.nextLine());
                if (score >= 0 && score <= 10) {
                    break;
                } else {
                    System.out.print("Invalid. Enter a score between 0 and 10: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Enter a number: ");
            }
        }
        return score;
    }
    void generateCertifications() {
        for (String skill : coreSkills.keySet()) {
            certifications.put(skill, getLevel(coreSkills.get(skill)));
        }
        for (String skill : advancedSkills.keySet()) {
            certifications.put(skill, getLevel(advancedSkills.get(skill)));
        }
    }
    String getLevel(int score) {
        if (score >= 8) return "Advanced";
        else if (score >= 5) return "Intermediate";
        else return "Beginner";
    }
    void printFormalReport() {
        System.out.println("\n=================================================");
        System.out.println("            COMPETENCY DIAGNOSTIC REPORT");
        System.out.println("=================================================");
        System.out.println("Candidate Name     : " + name);
        System.out.println("Email Address      : " + email);
        System.out.println("-------------------------------------------------");
        System.out.println("                SCORE SUMMARY");
        System.out.println("-------------------------------------------------");
        System.out.println("Core Skills:");
        for (Map.Entry<String, Integer> entry : coreSkills.entrySet()) {
            System.out.printf("  %-20s : %2d/10\n", entry.getKey(), entry.getValue());
        }
        System.out.println("\nAdvanced Skills:");
        for (Map.Entry<String, Integer> entry : advancedSkills.entrySet()) {
            System.out.printf("  %-20s : %2d/10\n", entry.getKey(), entry.getValue());
        }
        System.out.println("\nTotal Raw Score     : " + totalScore + " out of 80");
        System.out.println("Normalized Score    : " + normalizedScore + " out of 100");
        System.out.println("\n-------------------------------------------------");
        System.out.println("            SKILLS VERIFICATION");
        System.out.println("-------------------------------------------------");
        for (Map.Entry<String, String> entry : certifications.entrySet()) {
            System.out.printf("  %-20s : %s\n", entry.getKey(), entry.getValue());
        }
        System.out.println("\n-------------------------------------------------");
        System.out.println("        JOB & COURSE RECOMMENDATIONS");
        System.out.println("-------------------------------------------------");
        if (normalizedScore >= 85) {
            System.out.println("Recommended Jobs    : Software Developer, Data Analyst, AI Engineer");
            System.out.println("Suggested Courses   : Advanced DSA, System Design, Real-Time AI Projects");
        } else if (normalizedScore >= 65) {
            System.out.println("Recommended Jobs    : Intern - Java Developer, Web Developer");
            System.out.println("Suggested Courses   : Intermediate Java, Full Stack Development, ML Basics");
        } else {
            System.out.println("Recommended Jobs    : Technical Support Intern, QA Tester");
            System.out.println("Suggested Courses   : C Fundamentals, DAA Basics, Problem Solving");
        }
        System.out.println("\n-------------------------------------------------");
        System.out.println("               RESUME SUMMARY");
        System.out.println("-------------------------------------------------");
        System.out.printf("Total Score         : %d/100\n", normalizedScore);
        if (normalizedScore >= 85)
            System.out.println("Resume Strength     : ⭐ Strong Candidate");
        else if (normalizedScore >= 65)
            System.out.println("Resume Strength     : ✅ Good Candidate");
        else
            System.out.println("Resume Strength     : ⚠️ Needs Improvement");
        System.out.println("LinkedIn Handle     : https://linkedin.com/in/" + name.toLowerCase().replaceAll(" ", ""));
        System.out.println("=================================================\n");
    }
}
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=================================================");
        System.out.println("  SMART COMPETENCY DIAGNOSTIC & JOB PORTAL");
        System.out.println("=================================================");
        System.out.print("Enter your Full Name: ");
        String name = sc.nextLine();
        System.out.print("Enter your Email ID : ");
        String email = sc.nextLine();
        User candidate = new User(name, email);
        candidate.takeCompetencyTest();
        candidate.generateCertifications();
        candidate.printFormalReport();
        System.out.println("Thank you for using the Smart Competency Platform!");
    }
}