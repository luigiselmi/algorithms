package assignments.baseball;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.FlowEdge;
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
    // row=0 and column=0 will not be used but it will be 
    // easier to handle teams' data.
    w = new int [numberOfTeams + 1]; 
    l = new int [numberOfTeams + 1];
    r = new int [numberOfTeams + 1];
    g = new int [numberOfTeams + 1][numberOfTeams + 1];
    
    for (int i = 1; i < numberOfTeams + 1; i++) {
      teams.put(in.readString(), i); // vertex 0 is the source, not a team 
      w[i] = in.readInt();
      l[i] = in.readInt();
      r[i] = in.readInt();
      for (int j = 1; j < numberOfTeams + 1; j++)
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
    for (int i = 1; i < numberOfTeams + 1; i++) {
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
    FlowNetwork G = createFlowNetwork(team);
    StdOut.print(G.toString());
    // 2) compute the maxflow and min-cut set
    // 3) check min-cut set members
    
    return teamEliminated;
  }
  /*
   * Creates the flow network. Vertex 0 is the source. Vertices from 
   * 1 to the number of teams, not including the one that is checked 
   * for elimination, represent the teams (team vertices). The following 
   * integers represent the games left (game vertices).  
   */
  private FlowNetwork createFlowNetwork(String team) {
    // game edges from source vertex to game vertices and team vertices
    Bag<FlowEdge> edges = new Bag<FlowEdge>();
    ST<String, Integer> markedTeam = new ST<String, Integer>(); 
    int vertices = numberOfTeams + 1;
    for (String rteam: teams) {
      if (rteam.equals(team)) continue;
      for (String cteam: teams) {
        if (cteam.equals(team) || cteam.equals(rteam) || markedTeam.contains(cteam)) continue;
        int gamesLeft = g[teams.get(rteam)][teams.get(cteam)]; // vertices start from 1
        if (gamesLeft > 0) {
          FlowEdge sgEdge = new FlowEdge(0,vertices,gamesLeft); // source-game edge
          FlowEdge gtEdge1 = new FlowEdge(vertices,teams.get(rteam),Double.POSITIVE_INFINITY); // 1st game-team edge
          FlowEdge gtEdge2 = new FlowEdge(vertices,teams.get(cteam),Double.POSITIVE_INFINITY); // 2nd game-team edge
          edges.add(sgEdge);
          edges.add(gtEdge1);
          edges.add(gtEdge2);
          vertices++;
        }
      }
      markedTeam.put(rteam, teams.get(rteam));
    }
    
    // team's wins and games left
    int teamWins = w[teams.get(team)];
    int teamRemainings = r[teams.get(team)];
    int teamMaxWins = teamWins + teamRemainings;
    // edges from team vertices to target vertex
    int targetVertex = teams.get(team); // the team's index is used as target
    for (String oteam: teams) {
      if (oteam.equals(team)) continue;
      int oteamCapacity = teamMaxWins - w[teams.get(oteam)];
      if (oteamCapacity >= 0) {
        FlowEdge e = new FlowEdge(teams.get(oteam), targetVertex, oteamCapacity);
        edges.add(e);
      }
    }
    
    FlowNetwork G = new FlowNetwork(vertices);
    for (FlowEdge e: edges)
      G.addEdge(e);
    
    return G; 
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
