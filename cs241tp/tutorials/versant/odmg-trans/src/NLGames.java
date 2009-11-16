import model.*;
import com.versant.fund.*;
import com.versant.trans.*;
import java.util.*;

public class NLGames
{
  public static void main(String[] args) throws InterruptedException
  {
    if (args.length != 1) {
      System.out.println("Usage: java NLGames <database>");
      System.exit(1);
    }
    database = args[0];

    TransSession session = new TransSession(database);
    for (int i = 0; i < teams.length / 2; i++) {
      Game game = new Game();
      game.team1 = teams [i*2];
      game.team2 = teams [i*2 + 1];
      session.makePersistent(game);
    }
    session.commit ();

    Thread[] update = new Thread[teams.length/2];
    Thread[] report = new Thread[teams.length/2];

    int i;
    for (i = 0; i < teams.length/2; i++) {
      update[i] = new ScoreUpdate(session, teams[i*2]);
      report[i] = new ScoreReport(session, teams[i*2]);
    }

    for (i = 0; i < update.length; i++) {
      update[i].start ();
    }

    for (i = 0; i < update.length; i++) {
      update[i].join ();
    }

    System.out.println ();
    session.commit ();

    for (i = 0; i < update.length; i++) {
      report[i].start ();
    }

    for (i = 0; i < update.length; i++) {
      report[i].join ();
    }

    session.endSession ();
  }

  static String   database;
  static boolean  done    = false;
  static Random   random  = new Random ();
  static String[] teams   = {"San Diego Padres",
    "Los Angeles Dodgers",
    "San Francisco Giants",
    "Colorado Rockies",
    "Atlanta Braves",
    "New York Mets",
    "Philadelphia Phillies",
    "Montreal Expos",
    "St. Louis Cardinals",
    "Chicago Cubs",
    "Houston Astros",
  "Cincinnati Reds"};
}

class ScoreUpdate extends Thread
{
  TransSession session;
  String       teamName;

  ScoreUpdate(TransSession s, String aName)
  {
    session = s;
    teamName = aName;
  }

  public void run()
  {
    session.setSession();
    VQLQuery vql =
      new VQLQuery(session,
          "select selfoid from model.Game where team1 = $1");
    vql.bind (teamName);
    Enumeration e = vql.execute(0, 0, Constants.IWLOCK, Constants.WLOCK);

    while (e.hasMoreElements()) {
      Game game = (Game) e.nextElement ();
      game.score1 = Math.abs(NLGames.random.nextInt()) % 4;
      game.score2 = Math.abs(NLGames.random.nextInt()) % 5;
      System.out.println("Game Update: " + game.team1 + ": " +
          game.score1 + "   " + game.team2 + ": " +
          game.score2);
    }

    session.leaveSession();
  }
}

class ScoreReport extends Thread
{
  TransSession session;
  String       teamName;

  ScoreReport(TransSession s, String aName)
  {
    session = s;
    teamName = aName;
  }

  public void run ()
  {
    session.setSession ();

    VQLQuery vql =
      new VQLQuery (session,
          "select selfoid from model.Game where team1 = $1");
    vql.bind (teamName);
    Enumeration e = vql.execute (0, 0, Constants.IWLOCK, Constants.WLOCK);

    while ( e.hasMoreElements() ) {
      Game game = (Game) e.nextElement ();

      game.score1 += Math.abs (NLGames.random.nextInt()) % 4;
      game.score2 += Math.abs (NLGames.random.nextInt()) % 5;
      if (game.score1 == game.score2) game.score2++;

      System.out.println ("Final Score: " + game.team1 + ": " +
          game.score1 + "   " + game.team2 + ": " +
          game.score2);
    }

    session.leaveSession ();
  }
}

