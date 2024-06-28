package com.blockchain;

import java.security.MessageDigest;
import java.util.ArrayList;

public class Block {

    public int index;
    public long timestamp;
    public String currentHash;
    public String previousHash;
    public String data;
    public ArrayList<Transaction> transactions = new ArrayList<Transaction>(); //our data will be a simple message.
    public int nonce;

    public Block(int index, String previousHash, ArrayList<Transaction> transactions){
        this.index = index;
        this.timestamp = System.currentTimeMillis();
        this.previousHash = previousHash;
        this.transactions = transactions;
        nonce = 0;
        currentHash = calculateHash();
    }

    public String calculateHash(){
        try {

            data="";
            for (int j=0; j<transactions.size();j++){
                Transaction tr = transactions.get(j);
                data = data + tr.sender+tr.recipient+tr.value;
            }
            String input = index + timestamp + previousHash + data + nonce;
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            
            StringBuffer hexString = new StringBuffer(); 
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        }         catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void mineBlock(int difficulty){
        nonce = 0;
        String target = new String(new char[difficulty]).replace('\0', '0'); 
        while (!currentHash.substring(0,  difficulty).equals(target)) {
            nonce++;
            currentHash = calculateHash();
        }
    }

    public String toString(){
        return String.format("""
                Block # : %s
                PreviousHash : %s
                Timestamp : %s
                Data : %s
                Nonce : %s
                CurrentHash : %s
                """, index, previousHash, timestamp, data, nonce, currentHash);
        
    }
}
