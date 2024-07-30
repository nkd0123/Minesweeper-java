package Rank;

import Connect.ConnectSQL;
import Model.Player;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.sql.Connection;

public class RankDao extends ConnectSQL {

    private static final Logger LOGGER = Logger.getLogger(RankDao.class.getName());

    // them nguoi choi
    public static boolean themPlyer(Player pl) {
        String sql = "INSERT INTO Player(playerName) VALUES (?)";
        try (ConnectSQL con = new ConnectSQL();
             PreparedStatement ps = con.getConnection().prepareStatement(sql)) {
            ps.setString(1, pl.getPlayerName());
            int n = ps.executeUpdate();
            return n == 1;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Failed to add player", ex);
            return false;
        }
    }

   // Cap nhap thoi gian choi
    public static boolean UpdatePlyer(Player pl) {
        String sql = "UPDATE Player SET timeWin = ? WHERE playerName = ?";
        try (ConnectSQL con = new ConnectSQL();
             PreparedStatement ps = con.getConnection().prepareStatement(sql)) {
            ps.setInt(1, pl.getTimeWin());
            ps.setString(2, pl.getPlayerName());
            int n = ps.executeUpdate();
            return n == 1;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Failed to update player", ex);
            return false;
        }
    }
 
    
   // Phuong thuc sap xep
   public static ArrayList<Player> layDSPlayer() {
    ArrayList<Player> players = new ArrayList<>();
    String sql = "SELECT Top 10 playerName, ROW_NUMBER() OVER (ORDER BY timeWin) AS xepHang FROM Player";

    try (Connection con = new ConnectSQL().getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
            Player player = new Player();
            player.setPlayerName(rs.getString("playerName"));
            player.setXepHang(rs.getInt("xepHang"));
           
            players.add(player);
        }
    } catch (SQLException ex) {
        LOGGER.log(Level.SEVERE, "Failed to retrieve players", ex);
    }

    return players;
}
    
    
// Phuong thuc Update Rank   
public static void updateRanking(ArrayList<String> playerNames) {
        String sql = "UPDATE Player SET xepHang = ? WHERE playerName = ?";
        try (Connection con = new ConnectSQL().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            int ranking = playerNames.size();
            for (String playerName : playerNames) {
                ps.setInt(1, ranking);
                ps.setString(2, playerName);
                ps.executeUpdate();
                ranking--;
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Failed to update ranking", ex);
        }
    }
}
