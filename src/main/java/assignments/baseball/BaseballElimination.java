package assignments.baseball;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdOut;

public class BaseballElimination {
  
  private int numberOfTeams = 0;
  private ST<String, Integer> teams; // names and indexes
  private int [] w; // wins for each team in the division
  private int [] l; // losses for each team in the division
  private int [] r; // remaining games for each team in the division
  private int [][] g; // games left to play against any other team in the division
  
  //create a baseball division from given filename in format specified below
  public BaseballElimination(String filename) {
    if (filename == null) {
      StdOut.println("Usage:\njava BaseballElimination [data_filename]");
      return;
    }
    
    In in = new In(filename);
    numberOfTeams = in.readInt();
    teams = new ST<String, Integer>();
    w = new int [numberOfTeams];
    l = new int [numberOfTeams];
    r = new int [numberOfTeams];
    g = new int [numberOfTeams][numberOfTeams];
    
    for (int i = 0; i < numberOfTeams; i++) {
      teams.put(in.readString(), i);
      w[i] = in.readInt();
      l[i] = in.readInt();
      r[i] = in.readInt();
      for (int j = 0; j < numberOfTeams; j++)
        g[i][j] = in.readInt();
    }
  }
  // number of teams
  public int numberOfTeams() {
    return numberOfTeams;
  }
  // all teams
  public Iterable<String> teams() {
    return teams.keys();
  }
  // number of wins for given team
  public int wins(String team) {
    if (team == null || ! teams.contains(team))
      throw new IllegalArgumentException("A team name from the dataset must be specified.");
    return w[teams.get(team)]; 
  }
  // number of losses for given team
  public int losses(String team) {
    if (team == null || ! teams.contains(team))
      throw new IllegalArgumentException("A team name from the dataset must be specified.");
    return l[teams.get(team)];
  }
  // number of remaining games for given team
  public int remaining(String team) {
    if (team == null || ! teams.contains(team))
      throw new IllegalArgumentException("A team name from the dataset must be specified.");
    return r[teams.get(team)]; 
  }
  // number of remaining games between team1 and team2
  public int against(String team1, String team2) {
    if (team1 == null || team2 == null || ! teams.contains(team1) || ! teams.contains(team2))
      throw new IllegalArgumentException("Two team names from the dataset must be specified.");
    return g[teams.get(team1)][teams.get(team2)];
  }
  // is given team eliminated?
  public boolean isEliminated(String team) {
    if (team == null || ! teams.contains(team))
      throw new IllegalArgumentException("A team name from the dataset must be specified.");
    
    return trivialElimination(team) || nontrivialElimination(team);
  }
  private boolean trivialElimination(String team) {
    boolean teamEliminated = false;
    int teamWins = w[teams.get(team)];
    int teamRemaining = r[teams.get(team)];
    int teamMaxWins = teamWins + teamRemaining;
    for (int i = 0; i < teams.get(team) || i > teams.get(team); i++)
      if (w[i] > teamMaxWins) teamEliminated = true;
    return teamEliminated;
  }
  private boolean nontrivialElimination(String team) {
    boolean teamEliminated = false;
    return teamEliminated;
  }
  // subset R of teams that eliminates given team; null if not eliminated
  public Iterable<String> certificateOfElimination(String team) {
    if (team == null || ! teams.contains(team))
      throw new IllegalArgumentException("A team name from the dataset must be specified.");
    return null; // to be implemented
  }
  public static void main(String[] args) {
    BaseballElimination division = new BaseballElimination(args[0]);
    for (String team : division.teams()) {
        if (division.isEliminated(team)) {
            StdOut.print(team + " is eliminated by the subset R = { ");
            for (String t : division.certificateOfElimination(team)) {
                StdOut.print(t + " ");
            }
            StdOut.println("}");
        }
        else {
            StdOut.println(team + " is not eliminated");
        }
    }
  }
}
