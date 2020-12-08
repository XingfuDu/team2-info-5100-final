package service.lead;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import dao.Lead;

public class LeadDataHelper {
    
    private static final String LEADS_RECORDS_FILE = "leads_records.csv";
    
    // FIXME missing data: vehicleId, dealerId, Comment
    private static final String[] DATA_FIELDS_TITLE = {"First Name", "Last Name", "Email", "Phone Number", 
            "Zip Code", "Use Purpose" , "Contact Time", "Contact Preference"};
    
    private ArrayList<Lead> leads;
    
    /**
     * Constructs a LeadDataHelper object, create csv file with fields title if default file not exists.<br/>
     * Used to operate Lead data. 
     */
    public LeadDataHelper() {
        createFileIfNotExists();
    }
    
    private void createFileIfNotExists() {
        File file = new File(LEADS_RECORDS_FILE);

        if (!file.exists()) {
            try {
                file.createNewFile();
                FileWriter fw = new FileWriter(file);
                fw.append(String.join(",", DATA_FIELDS_TITLE) + "\n");
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Save Lead object to data file
     * @param lead  a Lead object to be saved 
     */
    public void save(Lead lead) {
        try {
            FileWriter fw = new FileWriter(LEADS_RECORDS_FILE, true);
            fw.append(lead + "\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Get all leads data.
     * 
     * @param cached  A boolean value decided whether to use cached data, avoid frequent file i/o. 
     * @return An ArrayList contains all the lead objects which stored in the data file.
     */
    public ArrayList<Lead> getLeads(boolean cached) {
        if(cached && leads != null) return leads;
        
        leads = new ArrayList<Lead>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(LEADS_RECORDS_FILE));
            br.readLine(); // Skip the first line (Titles)
            String line;
            while ((line = br.readLine()) != null) {
                Lead lead = rowToLead(line);
                leads.add(lead);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return leads;
    }
    
    /**
     * Get leads data which filter by specific dealer id
     * @param cached  a boolean value decided whether to use cached data, avoid frequent file i/o.
     * @param dealerId  specify dealer which the leads data belongs to. 
     * @return an ArrayList contains all leads object which belongs to the specific dealer 
     */
    public ArrayList<Lead> getLeads(boolean cached, String dealerId){
        ArrayList<Lead> leads = new ArrayList<>();
        
        for(Lead lead: getLeads(cached)) {
            if(lead.getDealerId().equals(dealerId)) {
                leads.add(lead);
            }
        }
        return leads;
    }
    
    /**
     * Convert row string to Lead object
     * 
     * @param line  a row of data stored in the file, which contains lead fields in order.
     * @return Lead object with data set
     */
    private Lead rowToLead(String line) {
        String[] data = line.split(",");
        Lead lead = new Lead();
        lead.setFirstName(data[0]);
        lead.setLastName(data[1]);
        lead.setEmailAddress(data[2]);
        lead.setPhoneNumber(data[3]);
        lead.setZipCode(data[4]);
        lead.setUsePurpose(data[5]);
        lead.setContactTime(data[6]);
        lead.setContactPreference(data[7]);
        
        // FIXME missing data: vehicleId, dealerId, Comment
        
        return lead;
    }

}