/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import Schema.Contract;
import Schema.Customer;
import Schema.OTFNames;
import Schema.OneTimeFee;
import Schema.OneTimeFeeForCst;
import Schema.RatePlan;
import User.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ahmedmedhat
 */
public class HandleDB {

    private final DatabaseConnection db = DatabaseConnection.getDatabaseInstance();
    PreparedStatement ps;
    ResultSet rs;

    public boolean checkLogin(String username, String password) {
        boolean st = false;
        try {
            ps = db.getConnection().prepareStatement("select * from admin where username=? and password=?");
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            st = rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(HandleDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {

        }
        return st;
    }

    public String addUser(String username, String email, String password, int cuid) {
        String res = "false___please try again";
        try {
            //Cu_Id serial, name string, address string, cridet int
            ps = db.getConnection().prepareStatement("insert into customer values(?,?, ?, ?)");
            ps.setInt(1, cuid);
            ps.setString(2, username);
            ps.setString(3, email);
            ps.setString(4, password);
            int i = ps.executeUpdate();
            if (i > 0) {
                return "true___";
            }
        } catch (SQLException e) {
            Logger.getLogger(HandleDB.class.getName()).log(Level.SEVERE, null, e);

            if (e.getSQLState().equals("23505")) {
                String msg = e.getMessage();
                String whichError = msg.substring(msg.indexOf("(") + 1, msg.indexOf(")"));
//                System.out.println(" :  " + whichError);
                res = "false___" + whichError + " already exist";
            } else {
                res = "false___please try again";
//                System.out.println("false___please try again");
            }
        }
        return res;
    }

    public void delete(int id) {
        try {
            ps = db.getConnection().prepareStatement("delete from contract where cu_id=?;"
                    + "delete from customer where cu_id=?;");
            ps.setInt(1, id);
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(HandleDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<String> getMSISDN(int id) {
        List<String> msisdn = new ArrayList<>();
        try {
            String query = "select msisdn from cstmsisdn"
                    + " where id=?";
            ps = db.getConnection().prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            int x = 0;
            while (rs.next()) {
                x++;
                //retreive data by column name
                msisdn.add(x, rs.getString("msisdn"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HandleDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return msisdn;
    }

    // display users
    public Vector<Customer> getAllUsers() {
        Vector<Customer> customer = new Vector<Customer>();

        try {
            ps = db.getConnection().prepareStatement("select * from customer");

            rs = ps.executeQuery();
            while (rs.next()) {
                customer.add(new Customer(rs.getInt(1), rs.getString(2), rs.getString(4), rs.getString(3)));
            }
        } catch (SQLException e) {
            System.err.println(e);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e);
        }
        return customer;
    }

    // display contracts
    public Vector<Contract> getAllContracts(int cuid) {
        Vector<Contract> contract = new Vector<Contract>();

        try {
            ps = db.getConnection().prepareStatement("select con_id, fuvoiceonnet,fuvoicecrossnet, fuvoiceinternational,fusmsonnet, fusmscrossnet, fusmsinternational,fudata,rp_id,msisdn from contract where cu_id = ? ");
            ps.setInt(1, cuid);
            rs = ps.executeQuery();
            while (rs.next()) {
                contract.add(new Contract(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5),
                        rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getString(10)));
            }
        } catch (SQLException e) {
            System.err.println(e);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e);
        }
        return contract;
    }

    // getAllRatePlan
    public Vector<RatePlan> getAllRatePlan() {
        Vector<RatePlan> ratePlans = new Vector<RatePlan>();

        try {
            ps = db.getConnection().prepareStatement("select * from rateplan");
            rs = ps.executeQuery();
            while (rs.next()) {
                ratePlans.add(new RatePlan(rs.getInt(1), rs.getInt(3), rs.getString(2)));
            }
        } catch (SQLException e) {
            System.err.println(e);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e);
        }
        return ratePlans;
    }

    // getAllRatePlan
    public RatePlan getDetailsRatePlanByID(int rpid) {
        RatePlan ratePlans = new RatePlan();

        try {
            ps = db.getConnection().prepareStatement("select service_id,zone_id,fu from ratingpkg where rp_id = ?");
            ps.setInt(1, rpid);
            rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt(1) == 1 && rs.getInt(2) == 1) {
                    ratePlans.setFuVOnNet(rs.getInt(3));
                } else if (rs.getInt(1) == 1 && rs.getInt(2) == 2) {
                    ratePlans.setFuVOnCross(rs.getInt(3));
                } else if (rs.getInt(1) == 1 && rs.getInt(2) == 3) {
                    ratePlans.setFvVInter(rs.getInt(3));
                } else if (rs.getInt(1) == 2 && rs.getInt(2) == 1) {
                    ratePlans.setFuSOnNet(rs.getInt(3));
                } else if (rs.getInt(1) == 2 && rs.getInt(2) == 2) {
                    ratePlans.setFuSOnCross(rs.getInt(3));
                } else if (rs.getInt(1) == 2 && rs.getInt(2) == 3) {
                    ratePlans.setFvSInter(rs.getInt(3));
                } else {
                    ratePlans.setFvData(rs.getInt(3));
                }

            }
        } catch (SQLException e) {
            System.err.println(e);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e);
        }
        return ratePlans;
    }

    // getAllRatePlan
    public RatePlan getDetailsRatePlanAndExteralCostByID(RatePlan ratePlans) {
        try {
            ps = db.getConnection().prepareStatement("select service_id,zone_id,fu,externalcost from ratingpkg where rp_id = ?");
            ps.setInt(1, ratePlans.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt(1) == 1 && rs.getInt(2) == 1) {
                    ratePlans.setFuVOnNet(rs.getInt(3));
                    ratePlans.setExtraVOnNet(rs.getInt(4));

                } else if (rs.getInt(1) == 1 && rs.getInt(2) == 2) {
                    ratePlans.setFuVOnCross(rs.getInt(3));
                    ratePlans.setExtraVOnCross(rs.getInt(4));

                } else if (rs.getInt(1) == 1 && rs.getInt(2) == 3) {
                    ratePlans.setFvVInter(rs.getInt(3));
                    ratePlans.setExtraVInter(rs.getInt(4));

                } else if (rs.getInt(1) == 2 && rs.getInt(2) == 1) {
                    ratePlans.setFuSOnNet(rs.getInt(3));
                    ratePlans.setExtraSOnNet(rs.getInt(4));

                } else if (rs.getInt(1) == 2 && rs.getInt(2) == 2) {
                    ratePlans.setFuSOnCross(rs.getInt(3));
                    ratePlans.setExtraSOnCross(rs.getInt(4));

                } else if (rs.getInt(1) == 2 && rs.getInt(2) == 3) {
                    ratePlans.setFvSInter(rs.getInt(3));
                    ratePlans.setExtraSInter(rs.getInt(4));

                } else {
                    ratePlans.setFvData(rs.getInt(3));
                    ratePlans.setExtraData(rs.getInt(4));

                }

            }
        } catch (SQLException e) {
            System.err.println(e);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e);
        }
        return ratePlans;
    }

    // getAllRatePlan
    public Vector<RatePlan> getAllRatePlanWithAllDetails() {
        Vector<RatePlan> ratePlans = new Vector<RatePlan>();
        RatePlan ratePlan;
        try {
            ps = db.getConnection().prepareStatement("select * from rateplan");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                ratePlan = getDetailsRatePlanAndExteralCostByID(new RatePlan(rs.getInt(1), rs.getInt(3), rs.getString(2)));
                ratePlans.add(ratePlan);
            }
            System.out.println(ratePlans.size());
        } catch (SQLException e) {
            System.err.println(e);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e);
        }
        return ratePlans;
    }

    public Vector<OneTimeFee> getAllOneTimeFeePlanWithAllDetails() {
        Vector<OneTimeFee> oneTF = new Vector<OneTimeFee>();
        int i = 0;
        try {
            ps = db.getConnection().prepareStatement("select * from onetimefeebucket");
            rs = ps.executeQuery();
            while (rs.next()) {
                OneTimeFee oTF = new OneTimeFee();
                oTF.setBucketID(rs.getInt(1));
                oTF.setZoneID(rs.getInt(2));
                oTF.setServiceID(rs.getInt(3));
                oTF.setCost(rs.getInt(4));
                oTF.setQuota(rs.getInt(5));

//                OTFNames names = new OTFNames();
//                if(oTF.getZoneID() == 1){
//                    names.setZone("On Net");
//                }else if(oTF.getZoneID() == 2){
//                    names.setZone("Cross Net");
//                }else{}
                oneTF.add(i++, oTF);
            }

        } catch (SQLException e) {
            System.err.println(e);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e);
        }
        return oneTF;
    }
    
    public Vector<OneTimeFeeForCst> getOneTimeFeePlanWithAllDetailsForCustomer(String uid) {
        Vector<OneTimeFeeForCst> oneTF = new Vector<OneTimeFeeForCst>();
        int i = 0;
        try {
            ps = db.getConnection().prepareStatement("select * from contract_onetimefee where con_id in (select con_id from contract where cu_id = ?)");
            ps.setInt(1, Integer.parseInt(uid));
            rs = ps.executeQuery();
            while (rs.next()) {
                OneTimeFeeForCst oTF = new OneTimeFeeForCst();
                oTF.setContractOTFID(rs.getInt(1));
                oTF.setContractID(rs.getInt(2));
                oTF.setBucketID(rs.getInt(3));
                oTF.setConsumtion(rs.getInt(4));

//                OTFNames names = new OTFNames();
//                if(oTF.getZoneID() == 1){
//                    names.setZone("On Net");
//                }else if(oTF.getZoneID() == 2){
//                    names.setZone("Cross Net");
//                }else{}
                oneTF.add(i++, oTF);
            }

        } catch (SQLException e) {
            System.err.println(e);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e);
        }
        return oneTF;
    }

    public void addOneTimeFee(String serive, String zone, String qouta, String cost) {
        try {
            ps = db.getConnection().prepareStatement("INSERT INTO onetimefeebucket(bucket_id, zone_id, service_id, cost, quota) VALUES(DEFAULT, ?, ?, ?, ?) RETURNING bucket_id;");
            ps.setInt(1, Integer.parseInt(serive));
            ps.setInt(2, Integer.parseInt(zone));
            ps.setInt(3, Integer.parseInt(qouta));
            ps.setInt(4, Integer.parseInt(cost));
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(HandleDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void contractAddOneTimeFee(String bkId, String contId, String quota) {
        try {
            ps = db.getConnection().prepareStatement("INSERT INTO contract_onetimefee(con_otf, con_id, bucket_id, consumtion) VALUES(DEFAULT, ?, ?, ?) RETURNING con_otf;");
            ps.setInt(1, Integer.parseInt(contId));
            ps.setInt(2, Integer.parseInt(bkId));
            ps.setInt(3, Integer.parseInt(quota));
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(HandleDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String addContract(String msisdn, String rpid, String cuid) {
        String res = "false___please try again";
        try {
            RatePlan ratePlans = getDetailsRatePlanByID(Integer.parseInt(rpid));
            //Cu_Id serial, name string, address string, cridet int
            ps = db.getConnection().prepareStatement("insert into contract (msisdn,contractstate,billCycle,fuvoiceonnet,fuvoicecrossnet,fuvoiceinternational,fusmsonnet,fusmscrossnet,fusmsinternational,fudata ,rp_id ,cu_id) values(?,?, ?, ?,?,?,?,?, ?,?,?,?)");
            ps.setInt(1, Integer.parseInt(msisdn));
            ps.setString(2, "active");
            ps.setString(3, "15");
            ps.setInt(4, ratePlans.getFuVOnNet());
            ps.setInt(5, ratePlans.getFuVOnCross());
            ps.setInt(6, ratePlans.getFvVInter());
            ps.setInt(7, ratePlans.getFuSOnNet());
            ps.setInt(8, ratePlans.getFuSOnCross());
            ps.setInt(9, ratePlans.getFvSInter());
            ps.setInt(10, ratePlans.getFvData());
            ps.setInt(11, Integer.parseInt(rpid));
            ps.setInt(12, Integer.parseInt(cuid));

            int i = ps.executeUpdate();
            if (i > 0) {
                return "true___";
            }
        } catch (SQLException e) {
            Logger.getLogger(HandleDB.class.getName()).log(Level.SEVERE, null, e);

            if (e.getSQLState().equals("23505")) {
                String msg = e.getMessage();
                String whichError = msg.substring(msg.indexOf("(") + 1, msg.indexOf(")"));
//                System.out.println(" :  " + whichError);
                res = "false___" + whichError + " already exist";
            } else {
                res = "false___please try again";
//                System.out.println("false___please try again");
            }
        }
        return res;
    }

//    public int checkID(String cstid, String phoneNo, String rp1) {
//        boolean st = false;
//        User u;
//        try {
//            ps = db.getConnection().prepareStatement("select * from contract where cu_id=? and msisdn=?;");
//            ps.setInt(1, Integer.parseInt(cstid));
//            ps.setString(2, phoneNo);
//            rs = ps.executeQuery();
//            st = rs.next();
//        } catch (SQLException ex) {
//            Logger.getLogger(HandleDB.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (Exception e) {
//        }
//        if (st) {
//            RatePlan rp = new RatePlan();
//            int x1 = 0;
//            if (rp1.equalsIgnoreCase("X150")) {
//                x1 = 1;
//            } else if (rp1.equalsIgnoreCase("X250")) {
//                x1 = 2;
//            } else if (rp1.equalsIgnoreCase("X400")) {
//                x1 = 3;
//            }
//            List<Integer> l1 = new ArrayList<Integer>();
//            l1 = rp.ratePlan(x1);
//            try {
//                ps = db.getConnection().prepareStatement("select * \n"
//                        + "from customer\n"
//                        + "where cu_id=11;");
//                rs = ps.executeQuery();
//                while (rs.next()) {
//                    u = new User(rs.getInt("cs_id"), rs.getInt("credit"));
//                    
//                }
//                ps = db.getConnection().prepareStatement("update contract\n"
//                        + "set fuvoiceonnet = ?,\n"
//                        + "fuvoicecrossnet = ?,\n"
//                        + "fuvoiceinternational = ?,\n"
//                        + "fusmsonnet = ?,\n"
//                        + "fusmscrossnet = ?,\n"
//                        + "fudata = ?\n"
//                        + "where cu_id = ?;");
//                ps.setInt(1, l1.get(0));
//                ps.setInt(2, l1.get(1));
//                ps.setInt(3, l1.get(2));
//                ps.setInt(4, l1.get(3));
//                ps.setInt(5, l1.get(4));
//                ps.setInt(6, l1.get(5));
//                ps.setInt(7, Integer.parseInt(cstid));
//                rs = ps.executeQuery();
//                st = rs.next();
//            } catch (SQLException ex) {
//                Logger.getLogger(HandleDB.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (Exception e) {
//            }
//        }
//        return 0;
//    }
// public int checkID(String cstid, String phoneNo, String rp1) {
//        boolean st = false;
//        try {
//            ps = c.prepareStatement("select * from contract where cu_id=? and msisdn=?;");
//            ps.setInt(1, Integer.parseInt(cstid));
//            ps.setString(2, phoneNo);
//            rs = ps.executeQuery();
//            st = rs.next();
//        } catch (SQLException ex) {
//            Logger.getLogger(HandleDB.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (Exception e) {
//        }
//        if (st) {
//            RatePlan rp = new RatePlan();
//            int x1 = 0;
//            if (rp1.equalsIgnoreCase("X150")) {
//                x1 = 1;
//            } else if (rp1.equalsIgnoreCase("X250")) {
//                x1 = 2;
//            } else if (rp1.equalsIgnoreCase("X400")) {
//                x1 = 3;
//            }
//            List<Integer> l1 = new ArrayList<Integer>();
//            l1 = rp.ratePlan(x1);
//            try {
//                ps = c.prepareStatement("select credit \n"
//                        + "from customer\n"
//                        + "where cu_id=11;");
//                rs = ps.executeQuery();
//                while(rs.next()){
//                    
//                rs.getInt("cs_id");
//                }
//                ps = c.prepareStatement("update contract\n"
//                        + "set fuvoiceonnet = ?,\n"
//                        + "fuvoicecrossnet = ?,\n"
//                        + "fuvoiceinternational = ?,\n"
//                        + "fusmsonnet = ?,\n"
//                        + "fusmscrossnet = ?,\n"
//                        + "fudata = ?\n"
//                        + "where cu_id = ?;");
//                ps.setInt(1, l1.get(0));
//                ps.setInt(2, l1.get(1));
//                ps.setInt(3, l1.get(2));
//                ps.setInt(4, l1.get(3));
//                ps.setInt(5, l1.get(4));
//                ps.setInt(6, l1.get(5));
//                ps.setInt(7, Integer.parseInt(cstid));
//                rs = ps.executeQuery();
//                st = rs.next();
//            } catch (SQLException ex) {
//                Logger.getLogger(HandleDB.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (Exception e) {
//            }
//        }
//        return 0;
//    }
}
