package assignments.baseball;

import edu.princeton.cs.algs4.FlowNetwork;
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
    
    if (trivialElimination(team))
      return true;
    else
      return nontrivialElimination(team);
  }
  /*
   * If the maximum number of games a team x can win is less 
   * than the number of wins of some other team, then team x 
   * is trivially eliminated.
   */
  private boolean trivialElimination(String team) {
    boolean teamEliminated = false;
    int teamWins = w[teams.get(team)];
    int teamRemainings = r[teams.get(team)];
    int teamMaxWins = teamWins + teamRemainings;
    for (int i = 0; i < numberOfTeams; i++) {
      if (i == teams.get(team)) continue;
      if (w[i] > teamMaxWins) teamEliminated = true;
    }
    return teamEliminated;
  }
  /*
   * If the condition for trivial elimination is not met a team
   * may still be mathematically eliminated. In order to find which 
   * team is eliminated we
   * 1) create a flow network
   * 2) compute the maxflow and min-cut set
   * 3) check if there are vertices in the min-cut other than the source.
   * If all edges in the maxflow that are pointing from the source vertex 
   * s are full, the team cannot be eliminated.
   * If some edges pointing from s are not full, the team is eliminated.
   * The first case, all edges from s are full, is equivalent to a 
   * min-cut set in which the source vertex is its only member.
   * The 2nd case, some edges from s are not full, is equivalent to a
   * min-cut set with other vertices other than the source vertex.
   */
  private boolean nontrivialElimination(String team) {
    boolean teamEliminated = false;
    // 1) create a flow network
    // 2) compute the maxflow and min-cut set
    // 3) check min-cut set members
    int numGameVertices = computeNumGameVertices(team);
    return teamEliminated;
  }
  private FlowNetwork createFlownetwork(String team) {
    int numTeamVertices = numberOfTeams - 1;
    int teamWins = w[teams.get(team)];
    int teamRemainings = r[teams.get(team)];
    int teamMaxWins = teamWins + teamRemainings;
    return null; // to be implemented
  }
  private int computeNumGameVertices(String team) {
    int numGameVertices = 0;
    for (int row = 0; row < numberOfTeams; row++) {
      if (row == teams.get(team)) continue;
      for (int col = row + 1; col < numberOfTeams; col++) { 
        if (col == teams.get(team)) continue;
        if (g[row][col] != 0) numGameVertices++;
      }
    }
    return numGameVertices;
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
            /*
            for (String t : division.certificateOfElimination(team)) {
                StdOut.print(t + " ");
            }
            */
            StdOut.println("}");
        }
        else {
            StdOut.println(team + " is not eliminated");
        }
    }
  }
}
